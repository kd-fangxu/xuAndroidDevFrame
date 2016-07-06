package com.xu.utils.cacheManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.util.LruCache;

/**
 * 
 * @author liufeng
 */
public final class CacheManager {

	private static final String CACHE_DIR = "bsCache";

	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 50; // 50M

	private static final int CACHE_INDEX = 0;

	// singleton
	private static CacheManager cacheManager;
	private static DiskLruCache mCache;

	/**
	 * init in application
	 *
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
	@SuppressLint("NewApi")
	public synchronized static void init(Context context) {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		LruCache<String, Object> cache = new LruCache<String, Object>(cacheSize);
		LruCache<String, List<Object>> cache2 = new LruCache<String, List<Object>>(cacheSize);

		if (mCache == null) {
			File diskCacheDir = new File(getCacheDir(context, CACHE_DIR));
			// long availableSpace = OtherUtils.getAvailableSpace(diskCacheDir);
			long availableSpace = getAvailSpace();
			try {
				mCache = DiskLruCache.open(diskCacheDir, 1, 1, availableSpace > 0
						? (DISK_CACHE_SIZE > availableSpace ? availableSpace : DISK_CACHE_SIZE) : DISK_CACHE_SIZE);
			} catch (Exception e) {
				mCache = null;
				Log.e("catch", e.getMessage());
			}
		}
	}

	private static long getAvailSpace() {
		File root = Environment.getRootDirectory();
		StatFs sf = new StatFs(root.getPath());
		long blockSize = sf.getBlockSize();
		long blockCount = sf.getBlockCount();
		long availCount = sf.getAvailableBlocks();
		return availCount;
		// Log.d("", "block大小:"+ blockSize+",block数目:"+
		// blockCount+",总大小:"+blockSize*blockCount/1024+"KB");
		// Log.d("", "可用的block数目：:"+ availCount+",可用大小:"+
		// availCount*blockSize/1024+"KB");
	}

	/**
	 * 返回实例
	 * 
	 * @return
	 */
	public static CacheManager instance() {
		if (cacheManager == null) {
			cacheManager = new CacheManager();
		}
		return cacheManager;
	}

	// ensureCache
	private void ensureCache() {
		if (mCache == null || mCache.isClosed()) {
			throw new IllegalStateException("~~");
		}
	}

	private static String getCacheDir(Context context, String dirName) {
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File externalCacheDir = context.getExternalCacheDir();
			if (externalCacheDir != null) {
				cachePath = externalCacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = context.getCacheDir();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}
		// Log.e("cachePath=" + cachePath);
		return cachePath + File.separator + dirName;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized <T extends Serializable> boolean put(String key, T value) {
		return this.put(key, value, -1);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized boolean putObject(String key, Serializable value) {
		return this.put(key, value, -1);
	}

	/**
	 * 
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @param expires
	 * 
	 * @return
	 */
	public synchronized boolean putObject(String key, Serializable value, long expires) {
		ensureCache();
		boolean result = true;
		ObjectOutputStream oos = null;
		try {
			DiskLruCache.Snapshot snapshot = mCache.get(key);
			DiskLruCache.Editor editor = null;
			if (snapshot == null) {
				editor = mCache.edit(key);
			} else {
				editor = snapshot.edit();
			}
			OutputStream os = editor.newOutputStream(CACHE_INDEX);
			oos = new ObjectOutputStream(os);
			oos.writeObject(create(value, expires));
			editor.commit();
		} catch (IOException e) {
			Log.e("catch", e.getMessage());
			result = false;
		} catch (Exception e) {
			Log.e("catch", e.getMessage());
			result = false;
		} finally {
			CatchIOUtils.closeQuietly(oos);
		}
		return result;
	}

	/**
	 * 
	 * @param key
	 * 
	 * @param value
	 * 
	 * @param expires
	 * 
	 * @return
	 */
	public synchronized <T extends Serializable> boolean put(String key, T value, long expires) {
		ensureCache();
		boolean result = true;
		ObjectOutputStream oos = null;
		try {
			DiskLruCache.Snapshot snapshot = mCache.get(key);
			DiskLruCache.Editor editor = null;
			if (snapshot == null) {
				editor = mCache.edit(key);
			} else {
				editor = snapshot.edit();
			}
			OutputStream os = editor.newOutputStream(CACHE_INDEX);
			oos = new ObjectOutputStream(os);
			oos.writeObject(create(value, expires));
			editor.commit();
		} catch (IOException e) {
			Log.e("catch", e.getMessage());
			result = false;
		} catch (Exception e) {
			Log.e("catch", e.getMessage());
			result = false;
		} finally {
			CatchIOUtils.closeQuietly(oos);
		}
		return result;
	}

	/**
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T extends Serializable> T get(String key, Class<T> clazz) {
		ensureCache();
		ObjectInputStream ois = null;
		T value = null;
		try {
			DiskLruCache.Snapshot snapshot = mCache.get(key);
			if (snapshot != null) {
				InputStream is = snapshot.getInputStream(CACHE_INDEX);
				ois = new ObjectInputStream(is);
				CacheEntity ce = (CacheEntity) ois.readObject();
				if (ce.expires <= 0 || CatchDateUtils.timeSince(ce.cacheDate) < ce.expires) {
					value = (T) ce.value;
				} else {
					mCache.remove(key);
				}
			}
		} catch (Exception e) {
			Log.e("catch", e.getMessage());
		} finally {
			CatchIOUtils.closeQuietly(ois);
		}
		return value;
	}

	/**
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public synchronized Serializable getObject(String key) {
		ensureCache();
		ObjectInputStream ois = null;
		Serializable value = null;
		try {
			DiskLruCache.Snapshot snapshot = mCache.get(key);
			if (snapshot != null) {
				InputStream is = snapshot.getInputStream(CACHE_INDEX);
				ois = new ObjectInputStream(is);
				CacheEntity ce = (CacheEntity) ois.readObject();
				if (ce.expires <= 0 || CatchDateUtils.timeSince(ce.cacheDate) < ce.expires) {
					value = ce.value;
				} else {
					mCache.remove(key);
				}
			}
		} catch (Exception e) {
			Log.e("catch", e.getMessage());
		} finally {
			CatchIOUtils.closeQuietly(ois);
		}
		return value;
	}

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public synchronized boolean remove(String key) {
		ensureCache();
		boolean result = true;
		try {
			mCache.remove(key);
		} catch (IOException e) {
			Log.e("catch", e.getMessage());
			result = false;
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public long cacheSize() {
		ensureCache();
		return mCache.size();
	}

	/**
	 *
	 */
	public void clearDiskCache() {
		synchronized (this) {
			if (mCache != null && !mCache.isClosed()) {
				try {
					mCache.delete();
				} catch (Throwable e) {
					Log.e("catch", e.getMessage());
				}
				mCache = null;
			}
		}
	}

	/**
	 * 
	 */
	public void flush() {
		synchronized (this) {
			if (mCache != null) {
				try {
					mCache.flush();
				} catch (Throwable e) {
					Log.e("catch", e.getMessage());
				}
			}
		}
	}

	/**
	 * 
	 */
	public void close() {
		synchronized (this) {
			if (mCache != null) {
				try {
					if (!mCache.isClosed()) {
						mCache.close();
						mCache = null;
					}
				} catch (Throwable e) {
					Log.e("catch", e.getMessage());
				}
			}
		}
	}

	private static CacheEntity create(Serializable value, long expires) {
		CacheEntity ce = new CacheEntity();
		ce.value = value;
		ce.cacheDate = new Date();
		ce.expires = expires;
		return ce;
	}

	/**
	 * 
	 * 
	 * @author xiongwei
	 */
	private static class CacheEntity implements Serializable {
		private static final long serialVersionUID = 5149284229739114237L;

		Serializable value;
		Date cacheDate;
		long expires; // 有效
	}
}

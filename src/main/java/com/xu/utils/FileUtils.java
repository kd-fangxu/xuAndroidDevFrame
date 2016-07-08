package com.xu.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author gfx
 *
 */
public class FileUtils {



	public static String getFromRaw(Context context,int resourceId) throws IOException {
        InputStream is = context.getResources().openRawResource(resourceId);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
	}

	public static InputStream openFile(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fis;
	}

	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}

	public void savaData(final String path, InputStream is) {

		// TODO Auto-generated method stub

		FileWriteThread fs = new FileWriteThread();
		fs.setFilePath(path);
		fs.setIs(is);
		fs.execute();

	}

	public void savaData(final String path, InputStream is,
			FileSaveListener fsListener) {

		// TODO Auto-generated method stub

		FileWriteThread fs = new FileWriteThread();
		this.fsListener = fsListener;
		fs.setFilePath(path);
		fs.setIs(is);
		fs.execute();

	}

	class FileWriteThread extends AsyncTask<Void, Void, Boolean> {
		String filePath;
		InputStream is;

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public InputStream getIs() {
			return is;
		}

		public void setIs(InputStream is) {
			this.is = is;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			File file = new File(filePath);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			int BUFFER_SIZE = 1024;
			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;

			bis = new BufferedInputStream(is);

			try {
				fos = new FileOutputStream(file);
				while ((size = bis.read(buf)) != -1) {
					fos.write(buf, 0, size);
				}

				//
				fos.close();
				bis.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			if (result) {
				if (null != fsListener) {
					fsListener.onSuccess();
				}
			} else {
				if (null != fsListener) {
					fsListener.onFailed();
				}
			}

			super.onPostExecute(result);
		}
	};

	FileSaveListener fsListener;

	public interface FileSaveListener {
		public void onSuccess();

		public void onFailed();
	}
}

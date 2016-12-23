
 package com.xudev.utils.cacheManager;

import java.io.Closeable;

public class CatchIOUtils {

	/**
	 * @param c
	 */
	public static void closeQuietly(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception ignored) {
				// ignored
			}
		}
	}
	
	
}

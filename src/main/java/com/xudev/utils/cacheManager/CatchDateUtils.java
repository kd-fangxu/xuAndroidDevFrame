
 package com.xudev.utils.cacheManager;

import java.util.Date;


public class CatchDateUtils {

	/**
	 * 说明：计算date到当前时间的差�?�，单位为毫�?
	 * @param date
	 * @return
	 */
	public static long timeSince(Date date) {
		Date now = new Date();
		return now.getTime() - (date == null ? 0 : date.getTime());
	}
	
	
	
	
}

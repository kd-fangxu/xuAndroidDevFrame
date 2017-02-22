package com.xudev.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log统一管理�?
 * logutils 待替换
 * 
 * @author way
 * 
 */
@Deprecated
public class L {

	private L() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;// 是否�?要打印bug，可以在application的onCreate函数里面初始�?
	private static final String TAG = "way";

	// 下面四个是默认tag的函�?
	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}
	private static String generateTag() {
		StackTraceElement caller = new Throwable().getStackTrace()[2];
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		tag = TextUtils.isEmpty("printout") ? tag : "printout" + ":" + tag;
		return tag;
	}
	public static void e(String msg) {
		if (isDebug)
//			Log.e(TAG, msg);
		Log.e(generateTag(),msg);
//		LogUtil.e(msg);
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}

	// 下面是传入自定义tag的函�?
	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}
}
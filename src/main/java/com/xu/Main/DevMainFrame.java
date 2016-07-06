package com.xu.Main;

import android.app.Application;
import android.content.Context;

import com.xu.utils.cacheManager.CacheManager;

import org.xutils.x;

/**
 * Created by developer on 16/7/4.
 */
public class DevMainFrame {
    public static void  regester(Context context){
        x.Ext.init((Application) context);//初始化xutils3
        CacheManager.init(context);
    }
}

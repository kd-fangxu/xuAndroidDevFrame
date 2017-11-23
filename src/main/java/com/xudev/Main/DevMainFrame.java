package com.xudev.Main;

import android.app.Application;
import android.content.Context;

import com.xudev.utils.cacheManager.CacheManager;

import org.xutils.x;

/**
 * Created by developer on 16/7/4.
 */
public class DevMainFrame {
    public static void  regester(Context context){
        x.Ext.init((Application) context);//初始化xutils3
        x.Ext.setDebug(false); // 开启debug会影响性能

        CacheManager.init(context);
    }
}

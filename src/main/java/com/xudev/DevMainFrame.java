package com.xudev;

import android.app.Application;
import android.content.Context;

import com.xudev.utils.cacheManager.CacheManager;

import org.xutils.x;

/**
 * Created by developer on 16/7/4.
 *
 * @author gfx
 */
public class DevMainFrame {

    public static void init(Context context) {
        //初始化xutils3
        // 开启debug会影响性能
        x.Ext.setDebug(false);
        x.Ext.init((Application) context);
        CacheManager.init(context);
    }
}

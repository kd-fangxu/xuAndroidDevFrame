package com.kd.net.provider;

import android.content.Context;

/**
 * 配置文件 string提供者
 * Created by developer on 2016/12/23.
 */

public interface IRequestConfigStrProvider {
    /**
     * 获取全部请求Json String
     *
     * @param context
     * @return
     */
    String getConfigStr(Context context);
}

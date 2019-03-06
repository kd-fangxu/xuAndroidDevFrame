package com.xudev.net.check;

import android.content.Context;

/**
 * Created by developer on 16/8/8.
 *
 * @author gfx
 */
public interface IResultCheck {
    /**
     * 验证用户登录是否有效
     *
     * @param context
     * @param result
     * @return
     */
    boolean isUserAccessTokenEffected(Context context, String result);

}

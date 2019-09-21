package com.kd.user;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;

public class AbsUser {
    protected static final String USER = "currentuser";

    public static void clearUser() {
        SPUtils.getInstance().remove(USER);
    }

    public void saveUser() {
        String json = GsonUtils.toJson(this);
        SPUtils.getInstance().put(USER, json);
    }
}

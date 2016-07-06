package com.xu.Others;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.xutils.common.Callback;

/**
 * Created by developer on 16/7/6.
 */
public abstract class SimXhttpCallBack<T> implements Callback.CommonCallback<T> {

    @Override
    public abstract void onSuccess(T result) ;

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }


    public JsonObject convert(String result){
        JsonObject jo=new Gson().fromJson(result,JsonObject.class);
        return jo;
    }
//    public abstract void onSucceed(T result);
}

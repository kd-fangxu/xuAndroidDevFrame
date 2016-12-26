package com.xudev.ReqUrlManage.Engine;

import com.xudev.iface.OnCommonBusListener;
import com.xudev.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by developer on 2016/12/21.
 */

public class INetEngineDefaultImp implements INetEngine {
    /**
     * 默认采用xHttp实现
     *
     * @param url
     * @param params
     * @param method
     * @param isCacheFirst
     * @param commonBusListener
     */
    @Override
    public void doRequest(String url, Map<String, Object> params, String method, final boolean isCacheFirst, final OnCommonBusListener<String> commonBusListener) {
        final RequestParams reqParam = new RequestParams(url);
        if (params != null) {
            Iterator entries = params.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                reqParam.addParameter((String) entry.getKey(), entry.getValue());
            }
        }

//            final String cacheKey = reqParam.toString();
//            if (isCatcheFirst) {//缓存优先
//
//                String result = ACache.get(context).getAsString(MD5.md5(cacheKey));
//                if (result != null) {
//                    busListener.onSucceed(result);
//                    return;
//                }
//
//            }

        HttpMethod httpMethodmethod = HttpMethod.GET;
        if (method.equals("get")) {
            httpMethodmethod = HttpMethod.GET;
        } else if (method.equals("post")) {
            httpMethodmethod = HttpMethod.POST;
        } else {
            httpMethodmethod = HttpMethod.GET;
        }


        x.http().request(httpMethodmethod, reqParam, new Callback.CacheCallback<String>() {

            @Override
            public boolean onCache(String result) {
                commonBusListener.onSucceed(result);
                return isCacheFirst;
            }

            @Override
            public void onSuccess(String result) {

                commonBusListener.onSucceed(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                        busListener.onFailed(ex.getMessage());
                L.e(reqParam.toString() + ex.toString());
                commonBusListener.onFailed("网络请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }


}
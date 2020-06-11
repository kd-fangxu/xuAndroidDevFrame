package com.kd.net.engine;

import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.kd.callback.KdCallBack;
import com.kd.net.param.BaseRequestParams;
import com.kd.net.param.ParamsItem;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Created by developer on 2016/12/21.
 */

public class NetEngineDefaultImpl implements INetEngine {

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
    public AbsCancelTask doRequest(String url, BaseRequestParams params, String method, final boolean isCacheFirst, final KdCallBack<String> commonBusListener) {
        final RequestParams reqParam = new RequestParams(url);

        method = method.toLowerCase();
        HttpMethod httpMethodmethod = HttpMethod.GET;
        if (method.equals("get")) {
            httpMethodmethod = HttpMethod.GET;
        } else if (method.equals("post")) {
            httpMethodmethod = HttpMethod.POST;
        } else if (method.equals("put")) {
            httpMethodmethod = HttpMethod.PUT;
        } else if (method.equals("head")) {
            httpMethodmethod = HttpMethod.HEAD;
        } else if (method.equals("patch")) {
            httpMethodmethod = HttpMethod.PATCH;
        } else if (method.equals("move")) {
            httpMethodmethod = HttpMethod.MOVE;
        } else if (method.equals("copy")) {
            httpMethodmethod = HttpMethod.COPY;
        } else if (method.equals("delete")) {
            httpMethodmethod = HttpMethod.DELETE;
        } else if (method.equals("options")) {
            httpMethodmethod = HttpMethod.OPTIONS;
        } else if (method.equals("trace")) {
            httpMethodmethod = HttpMethod.TRACE;
        } else if (method.equals("connect")) {
            httpMethodmethod = HttpMethod.CONNECT;
        }
        /**
         * 设置参数
         */
        if (params != null) {
            for (ParamsItem items : params.getItemList()) {
                if (items.getValue() instanceof File) {
                    reqParam.setMultipart(true);
                    httpMethodmethod = HttpMethod.POST;//如果是上传类型的请求 无论配置是get post或是其他 统一用post提交
                }
                if (items.getParamType() == ParamsItem.TYPE_APPLICATION_JSON) {
                    // json格式参数
                    reqParam.setAsJsonContent(true);//设置为json内容
                    reqParam.setBodyContent(items.getValue().toString());//设置正文内容
                } else {
                    reqParam.addParameter(items.getKey(), items.getValue());
                }
            }
//      * 设置header
            for (ParamsItem paramsItem : params.getHeaderParamList()) {
                reqParam.addHeader(paramsItem.getKey(), (String) paramsItem.getValue());
            }
        }
        AbsCancelTask<Callback.Cancelable> cancelTask = new AbsCancelTask<Callback.Cancelable>() {
            @Override
            public void cancelTask() {
                taskContext.cancel();//根据不同的引擎的任务取消方法  实现不同
            }
        };
        LogUtils.e("kdRequest:doRequest==>", reqParam.toString());
        cancelTask.setTaskContext(x.http().request(httpMethodmethod, reqParam, new Callback.CacheCallback<String>() {

                    @Override
                    public boolean onCache(String result) {
//                    http://dx.oilchem.net/dxt/getMyDxtList.do?pagesize=10&showtitle=1&pgeId=308&accessToken=3878ad5a7835f77854873096ee8e9b41&page=1
                        if (isCacheFirst) {
                            if (commonBusListener != null) {
                                commonBusListener.onSucceed(result);
                            }
                        }
                        return isCacheFirst;
                    }

                    @Override
                    public void onSuccess(String result) {
                        if (commonBusListener != null && result != null) {
                            Log.e("kdRequest:onSuccess==>", result);
                            commonBusListener.onSucceed(result);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if (commonBusListener != null) {
                            Log.e("kdRequest:onError==>", ex.toString());
                            commonBusListener.onFailed(ex);
                        }

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                })
        );

        return cancelTask;
    }

}

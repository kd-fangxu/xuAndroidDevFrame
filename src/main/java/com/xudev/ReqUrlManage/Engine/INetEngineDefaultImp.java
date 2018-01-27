package com.xudev.ReqUrlManage.Engine;

import com.blankj.utilcode.util.LogUtils;
import com.xudev.ReqUrlManage.RequestParams.BaseRequestParams;
import com.xudev.ReqUrlManage.RequestParams.ParamsItem;
import com.xudev.iface.OnCommonBusListener;
import com.xudev.utils.L;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

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
    public AbsCancelTask doRequest(String url, BaseRequestParams params, String method, final boolean isCacheFirst, final OnCommonBusListener<String> commonBusListener) {
        final RequestParams reqParam = new RequestParams(url);

        HttpMethod httpMethodmethod = HttpMethod.GET;
        if (method.equals("get")) {
            httpMethodmethod = HttpMethod.GET;
        } else if (method.equals("post")) {
            httpMethodmethod = HttpMethod.POST;
        } else {
            httpMethodmethod = HttpMethod.GET;
        }
        if (params != null) {
            for (ParamsItem items : params.getItemList()) {
                if (items.getValue() instanceof File) {
                    reqParam.setMultipart(true);
                    httpMethodmethod = HttpMethod.POST;//如果是上传类型的请求 无论配置是get post或是其他 统一用post提交
                }
                reqParam.addParameter(items.getKey(), items.getValue());
            }
//            Iterator entries = params.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry entry = (Map.Entry) entries.next();
//                reqParam.addParameter((String) entry.getKey(), entry.getValue());
//            }
        }
        LogUtils.d("log_xiaoquaner", "request===>" + url);
        AbsCancelTask<Callback.Cancelable> cancelTask = new AbsCancelTask<Callback.Cancelable>() {
            @Override
            public void cancelTask() {
                taskContext.cancel();//根据不同的引擎的任务取消方法  实现不同
            }
        };
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
                            commonBusListener.onSucceed(result);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        L.e(reqParam.toString() + ex.toString());
                        if (commonBusListener != null) {
                            commonBusListener.onFailed("网络请求失败或回调方法中出现异常");
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

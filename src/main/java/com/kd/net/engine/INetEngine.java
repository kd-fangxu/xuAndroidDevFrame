package com.kd.net.engine;

import com.kd.net.param.BaseRequestParams;
import com.kd.callback.KdCallBack;

/**
 * Created by developer on 2016/12/21.
 */

public interface INetEngine {

    AbsCancelTask doRequest(String url, BaseRequestParams params, String method, boolean isCacheFirst, KdCallBack<String> commonBusListener);

}

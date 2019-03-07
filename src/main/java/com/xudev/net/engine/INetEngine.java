package com.xudev.net.engine;

import com.xudev.net.param.BaseRequestParams;
import com.xudev.callback.KdCallBack;

/**
 * Created by developer on 2016/12/21.
 */

public interface INetEngine {

    AbsCancelTask doRequest(String url, BaseRequestParams params, String method, boolean isCacheFirst, KdCallBack<String> commonBusListener);

}

package com.xudev.ReqUrlManage.Engine;

import com.xudev.ReqUrlManage.RequestParams.BaseRequestParams;
import com.xudev.iface.OnCommonBusListener;

import java.util.Map;

/**
 * Created by developer on 2016/12/21.
 */

public interface INetEngine {

    AbsCancelTask doRequest(String url, BaseRequestParams params, String method, boolean isCacheFirst, OnCommonBusListener<String> commonBusListener);

}

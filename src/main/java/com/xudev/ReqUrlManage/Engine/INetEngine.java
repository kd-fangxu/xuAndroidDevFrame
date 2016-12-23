package com.xudev.ReqUrlManage.Engine;

import com.xudev.iface.OnCommonBusListener;

import java.util.Map;

/**
 * Created by developer on 2016/12/21.
 */

public interface INetEngine {
    void doRequest(String url, Map<String, Object> params, String method,boolean isCacheFirst ,OnCommonBusListener<String> commonBusListener);
}

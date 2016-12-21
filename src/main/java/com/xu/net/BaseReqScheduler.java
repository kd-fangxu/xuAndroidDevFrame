package com.xu.net;

import com.xu.iface.OnCommonBusListener;

import java.util.Map;

/**
 * Created by developer on 2016/12/21.
 */


public class BaseReqScheduler {
    public ICommandControl iCommandControl;
//    public INetEngine iNetEngine;


    public ICommandControl getiCommandControl() {
        return iCommandControl;
    }

    public void setiCommandControl(ICommandControl iCommandControl) {
        this.iCommandControl = iCommandControl;
    }

//    public INetEngine getiNetEngine() {
//        return iNetEngine;
//    }
//
//    public void setiNetEngine(INetEngine iNetEngine) {
//        this.iNetEngine = iNetEngine;
//    }

public void doRequestByUniqueLauncherKey(String uniqueLauncherKey,Map<String,Object> params,OnCommonBusListener<String> commonBusListener){
    RequestCommandEntity requestCommandEntity=iCommandControl.getRequestCommandEntity(uniqueLauncherKey);




}


//    public void doRequest(String url, Map<String,Object> params , String Method, OnCommonBusListener<String> commonBusListener){
//        iNetEngine.doRequest( url, params, Method,commonBusListener);
//    }
}

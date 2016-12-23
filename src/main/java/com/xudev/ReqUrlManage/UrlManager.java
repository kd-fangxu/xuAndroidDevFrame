package com.xudev.ReqUrlManage;

/**
 * Created by developer on 16/7/6.
 */
public class UrlManager {

    public static String Req_Host="http://192.168.1.88:8080";

    public static String getReqUrl(String path){
        return  Req_Host+path;
    }



}

package com.kd.net.engine;

/**
 * Created by developer on 2017/4/14.
 */

public abstract class AbsResponseEntity implements  IResponseEntity {

    String resCode = null;//返回码
    String msg=null;//返回信息
    String timeTag=null;//时间戳
    String resBusStatus=null;//业务状态

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    public String getResBusStatus() {
        return resBusStatus;
    }

    public void setResBusStatus(String resBusStatus) {
        this.resBusStatus = resBusStatus;
    }


}

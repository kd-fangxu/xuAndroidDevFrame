package com.kd.net.param;

/**
 * Created by xu on 2017/6/16.
 */

public class ParamsItem {
    public static final int TYPE_APPLICATION_JSON = 1;
    String key;
    Object value;
    int paramType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getParamType() {
        return paramType;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
    }
}

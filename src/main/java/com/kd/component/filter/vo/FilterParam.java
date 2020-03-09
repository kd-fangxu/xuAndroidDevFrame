package com.kd.component.filter.vo;

/**
 * @author mac
 */
public class FilterParam {
    public FilterParam(String nameStr, String key, String paramValue) {
        this.nameStr = nameStr;
        this.paramValue = paramValue;
        this.key = key;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String nameStr;
    private String paramValue;
    private String key;
}

package com.kd.net.param;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 基础参数
 */
public abstract class BaseRequestParams {

    public List<ParamsItem> getHeaderParamList() {
        return headerParamList;
    }

    public void setHeaderParamList(List<ParamsItem> headerParamList) {
        this.headerParamList = headerParamList;
    }

    /**
     * header参数
     */
    List<ParamsItem> headerParamList = new ArrayList<>();
    List<ParamsItem> itemList = new ArrayList<>();

    public void addParam(String name, Object value) {
        ParamsItem item = new ParamsItem();
        item.setKey(name);
        item.setValue(value);
        itemList.add(item);
    }

    public void setParam(String keyName, Object value) {
        if (!hasKey(keyName)) {
            addParam(keyName, value);
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getKey().equals(keyName)) {
                    itemList.get(i).setValue(value);
                    return;
                }
            }
        }
    }

    public void addHeaderParam(String name, Object value) {
        ParamsItem item = new ParamsItem();
        item.setKey(name);
        item.setValue(value);
        headerParamList.add(item);
    }

    public void setHeaderParam(String keyName, Object value) {

        for (int i = 0; i < headerParamList.size(); i++) {
            if (headerParamList.get(i).getKey().equals(keyName)) {
                headerParamList.remove(i);
                i = i - 1;
            }
        }
        addHeaderParam(keyName, value);
    }

    public List<ParamsItem> getItemList() {
        return itemList;
    }

    public boolean hasKey(String key) {
        if (key == null) {
            return false;
        }
        for (ParamsItem item : itemList) {
            if (item.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static BaseRequestParams covertFormMap(Map<String, Object> paramsMap) {
        if (paramsMap == null) {
            return null;
        }
        BaseRequestParams baseRequestParams = new BaseRequestParams() {
        };
        Iterator entries = paramsMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            baseRequestParams.addParam((String) entry.getKey(), entry.getValue());
        }
        return baseRequestParams;
    }
}

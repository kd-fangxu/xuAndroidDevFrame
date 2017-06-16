package com.xudev.ReqUrlManage.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/6/16.
 */

public class BaseRequestParams {
    List<ParamsItem> itemList = new ArrayList<>();

    public void addParam(String name, Object value) {
        ParamsItem item = new ParamsItem();
        item.setKey(name);
        item.setValue(value);
        itemList.add(item);
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
        BaseRequestParams baseRequestParams = new BaseRequestParams();
        Iterator entries = paramsMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            baseRequestParams.addParam((String) entry.getKey(), entry.getValue());
        }
        return baseRequestParams;
    }
}

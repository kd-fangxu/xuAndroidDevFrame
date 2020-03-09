package com.kd.component.filter.vo;

import com.kd.net.param.BaseRequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mac
 */
public class FilterParamContainer {

    private Map<String, String> map = new HashMap<>();

    public void put(String key, String paramValue) {
        if (paramValue == null && map.containsKey(key)) {
            map.remove(key);
        } else {
            map.put(key, paramValue);
        }
    }

    public void fillNetParam(BaseRequestParams netParam) {
        if (map != null) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                netParam.addParam(key, value);
            }
        }
    }

}

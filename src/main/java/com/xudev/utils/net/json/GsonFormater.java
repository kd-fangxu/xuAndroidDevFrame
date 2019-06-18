package com.xudev.utils.net.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonFormater<T> {

    public T formatJs(String json) {

        Gson gson = new Gson();
        T t = gson.fromJson(json, new TypeToken<T>() {
        }.getType());
        return t;

    }
}

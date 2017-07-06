package com.xudev.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonFormatUtil<T> {

    public T formatJs(String json) {
        return new Gson().fromJson(json, new TypeToken<T>() {
        }.getType());
    }
}

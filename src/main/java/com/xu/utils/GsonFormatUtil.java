package com.xu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonFormatUtil<T> {

	public  T formatJs(String json) {
		Gson gson = new Gson();
		T t = gson.fromJson(json, new TypeToken<T>() {
		}.getType());
		return t;
	}
}

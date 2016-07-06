package com.xu.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ObjectConvertUtils<T> {
	T t;

	public T convert(String json) {
		Gson gson = new Gson();

		Log.e("json", json);
		t = gson.fromJson(json, new TypeToken<T>() {
		}.getType());
		return t;
	}
}

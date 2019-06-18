package com.kd.callback;

public interface KdCallBack<T> {

	void onSucceed(T result) ;

	void onFailed(String error);


}

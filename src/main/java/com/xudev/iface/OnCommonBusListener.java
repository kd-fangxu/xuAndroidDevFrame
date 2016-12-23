package com.xudev.iface;

public interface OnCommonBusListener<T> {

	void onSucceed(T result) ;

	void onFailed(String error);


}

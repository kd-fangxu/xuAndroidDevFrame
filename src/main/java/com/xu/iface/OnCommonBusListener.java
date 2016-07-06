package com.xu.iface;

import java.util.List;

public interface OnCommonBusListener<T> {

	void onSucceed(T result);

	void onFailed(String error);


}

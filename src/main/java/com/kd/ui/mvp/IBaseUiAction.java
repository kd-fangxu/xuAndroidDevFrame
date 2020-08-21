package com.kd.ui.mvp;

/**
 * 定义通用的ui行为
 * Created by developer on 16/6/29.
 */
public interface IBaseUiAction {
    void showToast(String msg);
    void showLoadingDialog();
}

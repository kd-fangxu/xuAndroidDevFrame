package com.xudev.Ui.Mvp;

/**
 * Created by developer on 16/6/29.
 */
public class BasePresenter<T extends IBaseUiAction> {
    public T mUiAction;//UI行为接口

    public BasePresenter(T action) {
        this.mUiAction = (T) action;
    }

    public void showToast(String msg) {

    }

    ;


}

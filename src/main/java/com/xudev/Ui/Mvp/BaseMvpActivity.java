package com.xudev.Ui.Mvp;

import android.os.Bundle;

import com.xudev.Ui.activity.XuBaseActivity;

/**
 * Created by developer on 16/6/29.
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends XuBaseActivity implements IBaseUiAction {
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);

    }

    public abstract void initPresenter();

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void showLoadingDialog() {
        super.showProgressDialog(null, "正在加载");
    }
}

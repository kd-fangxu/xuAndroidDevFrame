package com.kd.view.mvp;

import android.os.Bundle;

import com.kd.view.activity.KdBaseActivity;

/**
 * Created by developer on 16/6/29.
 */
public abstract class BaseMvpActivity<T extends BasePresenter,V extends IBaseUiAction> extends KdBaseActivity implements IBaseUiAction {
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initPresenter();
        mPresenter.attachView((V)this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    /**
     * 初始化业务类
     */
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

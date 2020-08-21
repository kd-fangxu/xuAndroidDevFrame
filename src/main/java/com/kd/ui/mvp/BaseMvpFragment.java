package com.kd.ui.mvp;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * Created by developer on 2016/12/28.
 */

public abstract class BaseMvpFragment<T extends BasePresenter, V extends IBaseUiAction> extends Fragment implements IBaseUiAction {
    private T mPresenter;

    abstract void initPresenter();

    @Override
    public void onAttach(Context context) {
        initPresenter();
        mPresenter.attachView((V) this);
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showToast(String msg) {
    }

    @Override
    public void showLoadingDialog() {

    }
}

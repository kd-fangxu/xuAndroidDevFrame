package com.xudev.Ui.Mvp;

import android.support.v4.app.Fragment;

/**
 * Created by developer on 2016/12/28.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends Fragment implements IBaseUiAction {
    private  BasePresenter basePresenter;
    abstract void initPresenter();

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showLoadingDialog() {

    }
}

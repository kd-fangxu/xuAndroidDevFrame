package com.kd.view.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by developer on 16/6/29.
 */
public class BasePresenter<T extends IBaseUiAction> {
    protected Reference<T> mViewRef;//UI行为接口


    public void showToast(String msg) {
mViewRef.get().showToast(msg);
    }

    public void attachView(T view) {

        this.mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


}

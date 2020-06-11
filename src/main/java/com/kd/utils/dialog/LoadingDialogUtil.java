package com.kd.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.xudeveframe.R;

/**
 * 全局加载框
 *
 * @author mac
 */
public class LoadingDialogUtil {

    private volatile static LoadingDialogUtil mLoadingDialogUtil;
    private int mLoadingDialogCount;
    private Dialog mDialog;

    private LoadingDialogUtil() {
    }

    ;

    public static LoadingDialogUtil getInstance() {
        if (mLoadingDialogUtil == null) {
            synchronized (LoadingDialogUtil.class) {
                if (mLoadingDialogUtil == null) {
                    mLoadingDialogUtil = new LoadingDialogUtil();
                }
            }

        }
        return mLoadingDialogUtil;
    }

    @Deprecated
    public synchronized void showLoadingDialog(Context context, String msg) {
        if (true) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View loadingView = inflater.inflate(R.layout.loading_dialog, null);
            mDialog = new Dialog(context, R.style.loading_dialog);
            mDialog.setCancelable(true);
            mDialog.setContentView(loadingView, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
            mDialog.show();
        }
        mLoadingDialogCount++;
    }


    public synchronized void showLoadingDialog(String msg) {
        try {
            LayoutInflater inflater = LayoutInflater.from(ActivityUtils.getTopActivity());
            View loadingView = inflater.inflate(R.layout.loading_dialog, null);
            mDialog = new Dialog(ActivityUtils.getTopActivity(), R.style.loading_dialog);
            mDialog.setCancelable(true);
            mDialog.setContentView(loadingView, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }

    }

    public synchronized void cancelDialog() {
        mLoadingDialogCount--;
        if (mDialog != null) {
            mDialog.dismiss();
        }

    }
}

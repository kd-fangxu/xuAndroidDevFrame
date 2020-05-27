package com.kd.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
//import com.xudev.utils.cacheManager.CacheManager;

/**
 * Created by developer on 16/8/2.
 */
public abstract class KdBaseFragment extends Fragment {

    public Handler mHandler;
    private Toast temToast;
    public MaterialDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        initToast();
    }



    @Deprecated
    @SuppressLint("ShowToast")
    public void initToast() {
        // TODO Auto-generated method stub
        temToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }


    public void showToast(String msg) {
        if (temToast == null) {
            temToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        }
        temToast.setText(msg);
        temToast.show();
    }

    public void showProgressDialog(String title, String msg) {
        if (title != null) {
            dialog = new MaterialDialog.Builder(getActivity())
                    .title(title)
                    .content(msg)
                    .progress(true, 0)
                    .show();
        } else {
            dialog = new MaterialDialog.Builder(getActivity())
                    .content(msg)
                    .progress(true, 0)
                    .show();
        }


    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onDetach() {
//        CacheManager.instance().flush();
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
        super.onDetach();
    }

    public boolean isShownInAct() {
        return isShownInAct;
    }

    public void setShownInAct(boolean shownInAct) {
        isShownInAct = shownInAct;
    }

    boolean isShownInAct = true;//是否在act中显示

    public KdBaseFragment() {
    }

    public void showSnackbar(String msg, View view) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}

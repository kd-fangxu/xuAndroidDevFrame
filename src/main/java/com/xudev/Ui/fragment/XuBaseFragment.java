package com.xudev.Ui.fragment;

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
import com.xudev.utils.cacheManager.CacheManager;

/**
 * Created by developer on 16/8/2.
 */
public abstract class XuBaseFragment extends Fragment {

    public Handler mHandler;
    private Toast temToast;
    public MaterialDialog dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHandler = new Handler();
        initToast();
        initVariables();
        setLayout();
        loadData();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initVariables();
    public abstract void setLayout();
    protected abstract void loadData();

    @SuppressLint("ShowToast")
    private void initToast() {
        // TODO Auto-generated method stub
        temToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
    }


    public void showToast(String msg) {
        temToast.setText(msg);
        temToast.show();
    }


    @Override
    public void onDetach() {
        CacheManager.instance().flush();
        if(dialog!=null){
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

    public XuBaseFragment() {
    }
    ;

//    public static XuBaseFragment newInstance() {
//        XuBaseFragment fragment = new XuBaseFragment();
//        return fragment;
//    }

    public void showSnackbar(String msg, View view) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}

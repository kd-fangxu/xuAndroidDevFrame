package com.kd.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

public abstract class KdBaseActivity extends AppCompatActivity {


    public Handler mHandler;
    private Toast temToast;
    public MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        initToast();
        setLayout(savedInstanceState);
        loadData();
    }

    public abstract void setLayout(Bundle savedInstanceState);

    public void loadData() {
    }

    public void showProgressDialog(String title, String msg) {
        if (title != null) {
            dialog = new MaterialDialog.Builder(this)
                    .title(title)
                    .content(msg)
                    .progress(true, 0)
                    .show();
        } else {
            dialog = new MaterialDialog.Builder(this)
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

    @SuppressLint("ShowToast")
    private void initToast() {
        // TODO Auto-generated method stub
        temToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }


    public void showToast(String msg) {
        if (temToast == null) {
            temToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        temToast.setText(msg);
        temToast.show();
    }


    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public void toActivity(Class<?> act, boolean isFinish) {
        startActivity(new Intent(KdBaseActivity.this, act));
        if (isFinish) {
            finish();
        }
    }

    @Override
    protected void onPause() {
//        CacheManager.instance().flush();
        if (dialog != null) {
            dialog.dismiss();
            dialog.cancel();
        }
        temToast = null;
        super.onPause();
    }
}

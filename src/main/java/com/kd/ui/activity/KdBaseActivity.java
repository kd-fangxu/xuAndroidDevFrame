package com.kd.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @author mac
 */
public abstract class KdBaseActivity extends AppCompatActivity {
    public Handler mHandler;
    public MaterialDialog dialog;
    private Toast temToast;
    private boolean loadOnCreate = true; //0 onCreate 1:onResume

    /**
     *  设置loadData oncreate加载
     * @param loadOnCreate
     */
    public void setLoadDataOnCreate(boolean loadOnCreate) {
        this.loadOnCreate = loadOnCreate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        initToast();
        setLayout(savedInstanceState);
        if (loadOnCreate) {
            loadData();
        }
    }

    public abstract void setLayout(Bundle savedInstanceState);

    public void loadData() {
    }

    public void showProgressDialog(String title, String msg) {
        try {
            if (title != null) {
                dialog = new MaterialDialog.Builder(this)
                        .title(title)
                        .content(msg)
                        .progress(true, 0)
                        .show();
            } else {
                if (msg == null) {
                    msg = "加载中..";
                }
                dialog = new MaterialDialog.Builder(this)
                        .content(msg)
                        .progress(true, 0)
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示单选列表项
     *
     * @param title
     * @param positiveText
     * @param singleChoice
     * @param itemss
     */
    public void showSingleChoiceListDialog(String title, String positiveText, MaterialDialog.ListCallbackSingleChoice singleChoice, int selectedPosition, CharSequence... itemss) {
        try {
            dialog = new MaterialDialog.Builder(this).title(title).positiveText(positiveText).items(itemss).itemsCallbackSingleChoice(selectedPosition, singleChoice).show();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void hideNavigationBar() {
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

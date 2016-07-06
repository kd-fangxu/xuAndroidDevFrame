package com.xu.activity;

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

import com.xu.utils.cacheManager.CacheManager;

public abstract class XuBaseActivity extends AppCompatActivity {


    public  Handler mHandler;
    Toast temToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getActionBar().hide();

        mHandler=new Handler();
        initToast();
//        setTheme(R.style.XuBaseActivityTheme);
        setLayout();
    }

    public abstract void setLayout();

    @SuppressLint("ShowToast")
    private void initToast() {
        // TODO Auto-generated method stub
        temToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
    }


    public void showToast(String msg) {
        temToast.setText(msg);
        temToast.show();
    }


    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void toActivity(Class<?> act, boolean isFinish) {
        startActivity(new Intent(XuBaseActivity.this, act));
        if (isFinish) {
            finish();
        }
    }

    @Override
    protected void onPause() {
        CacheManager.instance().flush();
        super.onPause();
    }
}

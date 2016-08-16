package com.xu.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by developer on 16/8/2.
 */
public class XuBaseFragment extends Fragment {
    public XuBaseFragment() {
    }
    ;


    public static XuBaseFragment newInstance(){
        XuBaseFragment fragment=new XuBaseFragment();
        return fragment;
    }
}

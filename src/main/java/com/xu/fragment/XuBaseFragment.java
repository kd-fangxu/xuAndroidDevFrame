package com.xu.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by developer on 16/8/2.
 */
public class XuBaseFragment extends Fragment {
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
    public static XuBaseFragment newInstance() {
        XuBaseFragment fragment = new XuBaseFragment();
        return fragment;
    }
}

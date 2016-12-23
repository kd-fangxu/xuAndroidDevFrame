package com.xudev.ReqUrlManage.ReqBeanProvider;

import android.content.Context;

/**
 * Created by developer on 2016/12/23.
 */

public abstract class IBaseReqBeanProImp implements IReqBeanProvider {
    public IRequestConfigStrProvider getStrProvider() {
        return strProvider;
    }

    IRequestConfigStrProvider strProvider;
    Context context;

    public IBaseReqBeanProImp(IRequestConfigStrProvider strProvider, Context context) {
        this.context = context;
        this.strProvider = strProvider;
    }

    public String getConfigStr() {
        String result = strProvider.getConfigStr(context);
        String resultStr = result.replace(" ", "");
        return resultStr;
    }

    @Override
    public abstract ReqBean getReqBean();
}

package com.xudev.ReqUrlManage.ReqBeanProvider;

import android.content.Context;

import com.xudev.ReqUrlManage.Model.RequestEnvironment;

/**
 * Created by developer on 2016/12/23.
 */

public abstract class IBaseReqBeanProImp implements IReqBeanProvider {

    IRequestConfigStrProvider strProvider;
    Context context;
    RequestEnvironment requestEnvironment;
    /**
     * 设置绝对环境前缀
     */
    public String AbsoluteHeaderStr;

    public IRequestConfigStrProvider getStrProvider() {
        return strProvider;
    }

    public void setRequestEnvironment(RequestEnvironment requestEnvironment) {
        this.requestEnvironment = requestEnvironment;
    }

    public RequestEnvironment getRequestEnvironment() {
        return requestEnvironment;
    }


    public void setAbsoluteHeaderStr(String absoluteHeaderStr) {
        this.AbsoluteHeaderStr = absoluteHeaderStr;
    }



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

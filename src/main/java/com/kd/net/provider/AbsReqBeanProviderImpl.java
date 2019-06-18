package com.kd.net.provider;

import android.content.Context;

import com.kd.net.bean.RequestEnvironment;
import com.kd.net.provider.bean.ReqBean;

/**
 * Created by developer on 2016/12/23.
 * 接口配置对象化 抽象类
 */

public abstract class AbsReqBeanProviderImpl implements IReqBeanProvider {

    /**
     * 设置绝对环境前缀
     */
    public String AbsoluteHeaderStr;
    IRequestConfigStrProvider strProvider;
    Context context;
    //环境配置
    RequestEnvironment requestEnvironment;

    public AbsReqBeanProviderImpl(IRequestConfigStrProvider strProvider, Context context) {
        this.context = context;
        this.strProvider = strProvider;
    }

    public IRequestConfigStrProvider getStrProvider() {
        return strProvider;
    }

    public RequestEnvironment getRequestEnvironment() {
        return requestEnvironment;
    }

    public void setRequestEnvironment(RequestEnvironment requestEnvironment) {
        this.requestEnvironment = requestEnvironment;
    }

    public void setAbsoluteHeaderStr(String absoluteHeaderStr) {
        this.AbsoluteHeaderStr = absoluteHeaderStr;
    }

    public String getConfigStr() {
        String result = strProvider.getConfigStr(context);
        String resultStr = result.replace(" ", "");
        return resultStr;
    }

    @Override
    public abstract ReqBean getReqBean();
}

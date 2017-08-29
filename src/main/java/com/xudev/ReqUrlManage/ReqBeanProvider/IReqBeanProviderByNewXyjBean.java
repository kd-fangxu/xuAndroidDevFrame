package com.xudev.ReqUrlManage.ReqBeanProvider;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.xudev.ReqUrlManage.Model.NewXiaoyaojiBean;
import com.xudev.ReqUrlManage.Model.XiaoYaojiApiBean;
import com.xudev.utils.GsonFormatUtil;

/**
 * Created by xu on 2017/8/29.
 */

public class IReqBeanProviderByNewXyjBean extends IBaseReqBeanProImp {
    private NewXiaoyaojiBean newXiaoyaojiBean;

    public NewXiaoyaojiBean createNewXiaoyaojiBean() throws Exception {
        if (newXiaoyaojiBean == null) {
            newXiaoyaojiBean = new Gson().fromJson(getConfigStr(), NewXiaoyaojiBean.class);
            if (newXiaoyaojiBean==null){
                throw new Exception("request配置文件解析失败");
            }
        }
        return newXiaoyaojiBean;
    }


    public IReqBeanProviderByNewXyjBean(IRequestConfigStrProvider strProvider, Context context) {
        super(strProvider, context);
    }

    @Override
    public ReqBean getReqBean() {
        try {
            if (createNewXiaoyaojiBean()!=null){

            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e);
        }
        return null;
    }
}

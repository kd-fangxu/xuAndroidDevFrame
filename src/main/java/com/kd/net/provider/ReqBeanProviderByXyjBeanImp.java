package com.kd.net.provider;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kd.net.bean.RequestEnvironment;
import com.kd.net.bean.XiaoYaojiApiBean;
import com.kd.net.provider.bean.ReqBean;
import com.kd.utils.string.PatternCheckUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author developer
 * @date 2016/12/21
 */

@Deprecated
public class ReqBeanProviderByXyjBeanImp extends AbsReqBeanProviderImpl {


    public XiaoYaojiApiBean xiaoYaojiApiBean;
    ReqBean reqBean;
    Context context;
    List<XiaoYaojiApiBean.ChildrenBean> childrenBeanList;
    List<XiaoYaojiApiBean.ModulesBean> modulesBeanList;

    public ReqBeanProviderByXyjBeanImp(IRequestConfigStrProvider strProvider, Context context) {
        super(strProvider, context);
    }

    public XiaoYaojiApiBean getXiaoYaojiApiBean() throws Exception {
        if (xiaoYaojiApiBean == null) {
            String resultStr = getConfigStr();
            xiaoYaojiApiBean = new Gson().fromJson(resultStr, XiaoYaojiApiBean.class);
            if (xiaoYaojiApiBean == null) {
                throw new Exception("request配置文件解析失败");
            }
        }

        return xiaoYaojiApiBean;
    }

    @Override
    public ReqBean getReqBean() {
        try {
            this.context = context;
            xiaoYaojiApiBean = getXiaoYaojiApiBean();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (xiaoYaojiApiBean == null) {
            return null;
        }
        reqBean = new ReqBean();
        reqBean.setMatchAppCode("");
        reqBean.setName(xiaoYaojiApiBean.getProject().getName());
        reqBean.setVersion("");
        String environmentStr = xiaoYaojiApiBean.getProject().getEnvironments();
//        "environments": "[{\"name\":\"release\",\"t\":1480901507432,\"vars\":[{\"name\":\"host\",\"value\":\"http://sc.oilchem.net\"}]}]",
        JSONArray environmentArray = null;
        try {
            environmentArray = new JSONArray(environmentStr);
        } catch (JSONException e) {
            e.printStackTrace();
            environmentArray = new JSONArray();
        }

        List<ReqBean.TaskItemBean> taskItemBeanList = new ArrayList<ReqBean.TaskItemBean>();
        childrenBeanList = new ArrayList<XiaoYaojiApiBean.ChildrenBean>();
        modulesBeanList = xiaoYaojiApiBean.getModules();

        for (XiaoYaojiApiBean.ModulesBean modulesBean : modulesBeanList) {
            for (XiaoYaojiApiBean.FoldersBean foldersBean : modulesBean.getFolders()) {
                for (XiaoYaojiApiBean.ChildrenBean childrenBean : foldersBean.getChildren()) {
                    ReqBean.TaskItemBean taskItemBean = new ReqBean.TaskItemBean();
                    String temUrl = childrenBean.getUrl();
                    if (PatternCheckUtils.isUrl(temUrl)) {//本身地址合法
                        taskItemBean.setUrl(temUrl);
                    } else {//组合地址
                        if (requestEnvironment == null) {//环境未设置 获取获取配置的第一个环境进行配置
                            List<RequestEnvironment> requestEnvironmentList = new Gson().fromJson(environmentStr, new TypeToken<List<RequestEnvironment>>() {
                            }.getType());
                            if (requestEnvironmentList != null && requestEnvironmentList.size() > 0) {
                                requestEnvironment = requestEnvironmentList.get(0);
                            }
                        }
                        if (requestEnvironment != null) {
                            if (requestEnvironment.getVars() != null) {
                                for (RequestEnvironment.VarsBean varBean : requestEnvironment.getVars()) {
                                    String tag = "$" + varBean.getName() + "$";
                                    String value = varBean.getValue();
                                    if (temUrl.contains(tag)) {
                                        if (AbsoluteHeaderStr != null && AbsoluteHeaderStr.length() > 0) {//存在绝对头部时优先设置头部
                                            temUrl = temUrl.replace(tag, AbsoluteHeaderStr);
                                        } else {
                                            temUrl = temUrl.replace(tag, value);
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                        taskItemBean.setUrl(temUrl);
                    }

                    taskItemBean.setIsCache(false);
                    taskItemBean.setRequestMethod(childrenBean.getRequestMethod());
                    taskItemBean.setTaskDesp(childrenBean.getName());
                    taskItemBean.setTaskId(childrenBean.getName());

                    String requestArgsStr = childrenBean.getRequestArgs();
                    JSONArray paramArray = null;
                    try {
                        paramArray = new JSONArray(requestArgsStr);
                        List<ReqBean.TaskItemBean.ParamsBean> paramList = new ArrayList<ReqBean.TaskItemBean.ParamsBean>();
                        if (paramArray != null) {
                            for (int i = 0; i < paramArray.length(); i++) {
                                JSONObject temJo = paramArray.getJSONObject(i);

//                              "requestArgs": "[{\"require\":\"true\",\"children\":[],\"type\":\"string\",\"name\":\"pageId\",\"description\":\"页面id\",\"testValue\":\"110\"},{\"require\":\"true\",\"children\":[],\"type\":\"string\",\"name\":\"pagesize\",\"defaultValue\":\"20\",\"description\":\"显示多少条记录\",\"testValue\":\"20\"}]"
                                ReqBean.TaskItemBean.ParamsBean paramBean = new ReqBean.TaskItemBean.ParamsBean();
                                paramBean.setKey(temJo.getString("name"));
                                if (temJo.has("description")) {
                                    paramBean.setDesc(temJo.getString("description"));
                                } else {
                                    paramBean.setDesc("无接口描述");
                                }

                                paramBean.setIsNessary(temJo.getBoolean("require"));
                                paramList.add(paramBean);
                            }

                            taskItemBean.setParams(paramList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    taskItemBeanList.add(taskItemBean);
                }

            }
        }
        reqBean.setList(taskItemBeanList);
        return reqBean;
    }
}

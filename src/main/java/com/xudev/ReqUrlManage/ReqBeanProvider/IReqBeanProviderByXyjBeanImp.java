package com.xudev.ReqUrlManage.ReqBeanProvider;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudev.ReqUrlManage.Model.RequestEnvironment;
import com.xudev.ReqUrlManage.Model.XiaoYaojiApiBean;
import com.xudev.utils.PatternCheckUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 2016/12/21.
 */

public class IReqBeanProviderByXyjBeanImp extends IBaseReqBeanProImp {


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

    public XiaoYaojiApiBean xiaoYaojiApiBean;
    ReqBean reqBean;
    Context context;
    List<XiaoYaojiApiBean.ChildrenBean> childrenBeanList;
    List<XiaoYaojiApiBean.ModulesBean> modulesBeanList;

    public IReqBeanProviderByXyjBeanImp(IRequestConfigStrProvider strProvider, Context context) {
        super(strProvider, context);
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
<<<<<<< HEAD
            for (XiaoYaojiApiBean.FoldersBean foldersBean:modulesBean.getFolders()){
              for (XiaoYaojiApiBean.ChildrenBean childrenBean :  foldersBean.getChildren()){
                  ReqBean.TaskItemBean taskItemBean=new ReqBean.TaskItemBean();
                  String temUrl=childrenBean.getUrl();
                  if (!PatternCheckUtils.isUrl(temUrl)&&environmentArray!=null){//若存在环境参数 先 先参数转换
                      Finish:for (int i = 0; i < environmentArray.length() ; i++) {
                          try {
                              JSONObject jo=environmentArray.getJSONObject(i);

                              JSONArray vars=jo.getJSONArray("vars");
                              if (vars.length()>0  ){
                                  for (int j = 0; j <vars.length() ; j++) {
                                      JSONObject temJO=vars.getJSONObject(j);
                                      if (temUrl.contains("$"+temJO.getString("name")+"$")){
                                          String tag="$"+temJO.getString("name")+"$";
                                          String replaceMentStr=temJO.getString("value");
                                          temUrl=temUrl.replace(tag,replaceMentStr);
                                          break Finish;//跳出嵌套循环
                                      }
                                  }

                              }


                          } catch (JSONException e) {
                              e.printStackTrace();
                          }

                      }
                      taskItemBean.setUrl(temUrl);
                  }else{
                      taskItemBean.setUrl(temUrl);
                  }

                  taskItemBean.setIsCache(false);
                  taskItemBean.setRequestMethod(childrenBean.getRequestMethod());
                  taskItemBean.setTaskDesp(childrenBean.getName());
                  taskItemBean.setTaskId(childrenBean.getName());

                  String requestArgsStr=childrenBean.getRequestArgs();
                  JSONArray  paramArray=null;
                  try {
                      paramArray=new JSONArray(requestArgsStr);
                      List<ReqBean.TaskItemBean.ParamsBean> paramList=new ArrayList<ReqBean.TaskItemBean.ParamsBean>();
                      if (paramArray!=null ){
                          for (int i = 0; i < paramArray.length() ; i++) {
                              JSONObject temJo=paramArray.getJSONObject(i);
=======
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
                                       temUrl= temUrl.replace(tag, value);
                                        break;
                                    }
                                }
                            }
                        }
                        taskItemBean.setUrl(temUrl);
                    }


//                    if (!PatternCheckUtils.isUrl(temUrl) && environmentArray != null) {//若存在环境参数 先 先参数转换
//                        Finish:
//                        for (int i = 0; i < environmentArray.length(); i++) {
//                            try {
//                                JSONObject jo = environmentArray.getJSONObject(i);
//
//                                JSONArray vars = jo.getJSONArray("vars");
//                                if (vars.length() > 0) {
//                                    for (int j = 0; j < vars.length(); j++) {
//                                        JSONObject temJO = vars.getJSONObject(j);
//                                        if (temUrl.contains("$" + temJO.getString("name") + "$")) {
//                                            String tag = "$" + temJO.getString("name") + "$";
//                                            String replaceMentStr = temJO.getString("value");
//                                            if (requestEnvironment != null) {
//
//                                                temUrl = temUrl.replace(tag, AbsoluteHeaderStr);//如果存在配置好的域名头 优先使用配置
//                                            } else {
//                                                temUrl = temUrl.replace(tag, replaceMentStr);
//                                            }
//                                            break Finish;
//                                        }
//                                    }
//
//                                }
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                        taskItemBean.setUrl(temUrl);
//                    } else {
//                        taskItemBean.setUrl(temUrl);
//                    }

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
>>>>>>> diff
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

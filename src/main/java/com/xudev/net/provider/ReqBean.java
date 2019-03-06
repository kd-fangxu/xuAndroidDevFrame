package com.xudev.net.provider;

import java.util.List;

/**
 * Request Command
 * Created by developer on 16/7/7.
 */
public class ReqBean {

    /**
     * name : 移动OA接口（android）
     * matchAppCode : LZOA_AND
     * version : 1.0
     * domainName : http://192.168.1.88:8080
     * list : [{"TaskId":"1","TaskDesp":"登录","Url":"/oilchem/mobile/user/login","requestMethod":"post","isCache":false,"params":[{"key":"username","desc":"用户名","isNessary":true},{"key":"pwd","desc":"密码（MD5加密）","isNessary":true},{"key":"deviceToken","desc":"设备token","isNessary":true}]},{"TaskId":"2","TaskDesp":"设备绑定申请","Url":"/oilchem/mobile/user/deviceBinding","requestMethod":"post/get","isCache":"false","params":[{"key":"userName","desc":"用户名","isNessary":true},{"key":"deviceToken","desc":"设备机器码","isNessary":true},{"key":"deviceType","desc":"设备类型 ：android/ios","isNessary":true},{"key":"deviceModel","desc":"设备型号","isNessary":true},{"key":"osVersion","desc":"系统版本号","isNessary":true}]},{"TaskId":"3","TaskDesp":"强制登录","Url":"/oilchem/mobile/user/loginReplace","requestMethod":"post/get","isCache":"false","params":[{"key":"userName","desc":"用户名","isNessary":true},{"key":"deviceToken","desc":"设备机器码","isNessary":true}]}]
     */

    private String name;
    private String matchAppCode;
    private String version;
    private String domainName;
    /**
     * TaskId : 1
     * TaskDesp : 登录
     * Url : /oilchem/mobile/user/login
     * requestMethod : post
     * isCache : false
     * params : [{"key":"username","desc":"用户名","isNessary":true},{"key":"pwd","desc":"密码（MD5加密）","isNessary":true},{"key":"deviceToken","desc":"设备token","isNessary":true}]
     */

    private List<TaskItemBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchAppCode() {
        return matchAppCode;
    }

    public void setMatchAppCode(String matchAppCode) {
        this.matchAppCode = matchAppCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<TaskItemBean> getList() {
        return list;
    }

    public void setList(List<TaskItemBean> list) {
        this.list = list;
    }

    public static class TaskItemBean {
        private String TaskId;
        private String TaskDesp;
        private String Url;
        private String requestMethod;
        private boolean isCache;
        /**
         * key : username
         * desc : 用户名
         * isNessary : true
         */

        private List<ParamsBean> params;

        public String getTaskId() {
            return TaskId;
        }

        public void setTaskId(String TaskId) {
            this.TaskId = TaskId;
        }

        public String getTaskDesp() {
            return TaskDesp;
        }

        public void setTaskDesp(String TaskDesp) {
            this.TaskDesp = TaskDesp;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getRequestMethod() {
            return requestMethod;
        }

        public void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        public boolean isIsCache() {
            return isCache;
        }

        public void setIsCache(boolean isCache) {
            this.isCache = isCache;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public static class ParamsBean {
            private String key;
            private String desc;
            private boolean isNessary;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public boolean isIsNessary() {
                return isNessary;
            }

            public void setIsNessary(boolean isNessary) {
                this.isNessary = isNessary;
            }
        }
    }
}

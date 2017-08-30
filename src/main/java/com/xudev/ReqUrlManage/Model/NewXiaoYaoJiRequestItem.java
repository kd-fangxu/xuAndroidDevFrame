package com.xudev.ReqUrlManage.Model;

import java.util.List;

/**
 * Created by xu on 2017/8/30.
 */

public class NewXiaoYaoJiRequestItem {

    /**
     * requestHeaders : []
     * requestMethod : POST
     * dataType : FORM-DATA
     * requestArgs : [{"children":[],"name":"mobile","description":"手机号","require":"true","type":"string","testValue":""}]
     * description : 发送手机验证码
     * contentType : JSON
     * url : $host1$/m/sms/sendSmsCode
     * responseArgs : [{"children":[],"name":"succeed","description":"成功标志","require":"true","type":"string","testValue":""},{"children":[],"name":"msg","description":"返回消息","require":"true","type":"string","testValue":""},{"children":[],"name":"data","description":"{}","require":"true","type":"string","testValue":""}]
     * example :
     * status : 有效
     */

    private String requestMethod;
    private String dataType;
    private String description;
    private String contentType;
    private String url;
    private String example;
    private String status;
    private List<?> requestHeaders;
    private List<RequestArgsEntity> requestArgs;
    private List<ResponseArgsEntity> responseArgs;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(List<?> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public List<RequestArgsEntity> getRequestArgs() {
        return requestArgs;
    }

    public void setRequestArgs(List<RequestArgsEntity> requestArgs) {
        this.requestArgs = requestArgs;
    }

    public List<ResponseArgsEntity> getResponseArgs() {
        return responseArgs;
    }

    public void setResponseArgs(List<ResponseArgsEntity> responseArgs) {
        this.responseArgs = responseArgs;
    }

    public static class RequestArgsEntity {
        /**
         * children : []
         * name : mobile
         * description : 手机号
         * require : true
         * type : string
         * testValue :
         */

        private String name;
        private String description;
        private String require;
        private String type;
        private String testValue;
        private List<?> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRequire() {
            return require;
        }

        public void setRequire(String require) {
            this.require = require;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTestValue() {
            return testValue;
        }

        public void setTestValue(String testValue) {
            this.testValue = testValue;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }

    public static class ResponseArgsEntity {
        /**
         * children : []
         * name : succeed
         * description : 成功标志
         * require : true
         * type : string
         * testValue :
         */

        private String name;
        private String description;
        private String require;
        private String type;
        private String testValue;
        private List<?> children;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRequire() {
            return require;
        }

        public void setRequire(String require) {
            this.require = require;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTestValue() {
            return testValue;
        }

        public void setTestValue(String testValue) {
            this.testValue = testValue;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}

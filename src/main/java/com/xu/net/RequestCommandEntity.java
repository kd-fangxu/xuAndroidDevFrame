package com.xu.net;

import java.util.List;

/**
 * Created by developer on 2016/12/21.
 */

public class RequestCommandEntity {
    /**
     * through the field to start the request
     */
    private String uniqueLauncherKey;
    private String commandDes;
    private String Url;
    private String requestMethod;
    private boolean isCache;


    public String getUniqueLauncherKey() {
        return uniqueLauncherKey;
    }

    public void setUniqueLauncherKey(String uniqueLauncherKey) {
        this.uniqueLauncherKey = uniqueLauncherKey;
    }

    public String getCommandDes() {
        return commandDes;
    }

    public void setCommandDes(String commandDes) {
        this.commandDes = commandDes;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
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

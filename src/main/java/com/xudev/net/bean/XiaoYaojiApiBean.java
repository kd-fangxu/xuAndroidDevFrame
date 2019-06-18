package com.xudev.net.bean;

import java.util.List;

/**
 * Created by developer on 2016/12/21.
 */

public class XiaoYaojiApiBean {

    private ProjectBean project;
    private List<ModulesBean> modules;

    public ProjectBean getProject() {
        return project;
    }

    public void setProject(ProjectBean project) {
        this.project = project;
    }

    public List<ModulesBean> getModules() {
        return modules;
    }

    public void setModules(List<ModulesBean> modules) {
        this.modules = modules;
    }

    public static class ProjectBean {
        /**
         * createTime : 2016-12-05 09:28:18
         * description :
         * editable : YES
         * environments : [{"name":"release","t":1480901507432,"vars":[{"name":"host","value":"http://sc.oilchem.net"}]}]
         * id : 1N4zDUswUZ
         * name : 隆众石化通
         * permission : PUBLIC
         * status : VALID
         * userId : 1La0k4RP3I
         */

        private String createTime;
        private String description;
        private String editable;
        private String environments;
        private String id;
        private String name;
        private String permission;
        private String status;
        private String userId;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEditable() {
            return editable;
        }

        public void setEditable(String editable) {
            this.editable = editable;
        }

        public String getEnvironments() {
            return environments;
        }

        public void setEnvironments(String environments) {
            this.environments = environments;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class ModulesBean {
        /**
         * id : 1N4zGX68Ac
         * lastUpdateTime : 2016-12-05 09:28:21
         * name : 默认模块
         * projectId : 1N4zDUswUZ
         */

        private String createTime;
        private String id;
        private String lastUpdateTime;
        private String name;
        private String projectId;
        private List<FoldersBean> folders;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public List<FoldersBean> getFolders() {
            return folders;
        }

        public void setFolders(List<FoldersBean> folders) {
            this.folders = folders;
        }


    }

    public static class FoldersBean {
        /**
         * children : [{"contentType":"JSON","createTime":"2016-12-21 09:35:08","dataType":"X-WWW-FORM-URLENCODED","description":"test","example":"{ \"tiaojia\": [{\"sid\": 7981778,\"rq\": \"2016\\u002F12\\u002F21 9:57:53\",\"title\": \"[二甲醚]：河南义马新源二甲醚价格上调\",\"pgeid\": 466,\"uid\": 16271},{\"sid\": 7981755,\"rq\": \"2016\\u002F12\\u002F21 9:56:42\",\"title\": \"[醋酸酯]：漳州欧硕醋酸甲酯价格动态\",\"pgeid\": 179,\"uid\": 21416},{\"sid\": 7981754,\"rq\": \"2016\\u002F12\\u002F21 9:56:38\",\"title\": \"[二甲醚]：河南义马开祥二甲醚行情上调\",\"pgeid\": 466,\"uid\": 16243},{\"sid\": 7981695,\"rq\": \"2016\\u002F12\\u002F21 9:54:02\",\"title\": \"[华东]：江苏新海石化汽油、柴油价格上调\",\"pgeid\": 199,\"uid\": 8800},{\"sid\": 7981681,\"rq\": \"2016\\u002F12\\u002F21 9:52:59\",\"title\": \"[工业萘]：江西黑猫炭黑股份有限公司工业萘价格上调\",\"pgeid\": 412,\"uid\": 20588}], \"hot\": [{\"sid\": 7975499,\"rq\": \"2016\\u002F12\\u002F19 15:42:11\",\"title\": \"[苯胺]：山东金岭苯胺明日价格上调\",\"pgeid\": 219,\"uid\": 12405},{\"sid\": 7971225,\"rq\": \"2016\\u002F12\\u002F16 17:00:19\",\"title\": \"[苯胺]：南化苯胺价格明日价格上调\",\"pgeid\": 219,\"uid\": 12406},{\"sid\": 7963476,\"rq\": \"2016\\u002F12\\u002F15 8:59:13\",\"title\": \"2017年原油非国营贸易进口允许量总量、申请条件和申请程序\",\"pgeid\": 199,\"uid\": 22657},{\"sid\": 7962676,\"rq\": \"2016\\u002F12\\u002F14 16:39:07\",\"title\": \"多重利好提振 油市价格涨疯了\",\"pgeid\": 199,\"uid\": 18328},{\"sid\": 7958583,\"rq\": \"2016\\u002F12\\u002F13 19:33:46\",\"title\": \"厉害了,word船燃!\",\"pgeid\": 206,\"uid\": 9970}], \"zhishi\": [{\"sid\": 7981796,\"rq\": \"2016\\u002F12\\u002F21 9:59:02\",\"title\": \"[动力煤]：六盘水市场动力煤行情\",\"pgeid\": 473,\"uid\": 9737},{\"sid\": 7981795,\"rq\": \"2016\\u002F12\\u002F21 9:59:01\",\"title\": \"[乙醇]：山东无棣润生玉米乙醇价格暂稳\",\"pgeid\": 142,\"uid\": 15145},{\"sid\": 7981794,\"rq\": \"2016\\u002F12\\u002F21 9:58:59\",\"title\": \"[石脑油]：东营地区亚通石化石脑油价格持稳\",\"pgeid\": 207,\"uid\": 16082},{\"sid\": 7981793,\"rq\": \"2016\\u002F12\\u002F21 9:58:56\",\"title\": \"[PE]：大庆石化聚乙烯低压C线装置开车动态\",\"pgeid\": 328,\"uid\": 14513},{\"sid\": 7981792,\"rq\": \"2016\\u002F12\\u002F21 9:58:53\",\"title\": \"[液化气]：延炼新能源民用气网拍成交快报\",\"pgeid\": 455,\"uid\": 8511},{\"sid\": 7981791,\"rq\": \"2016\\u002F12\\u002F21 9:58:52\",\"title\": \"[尿素]：河南心连心尿素价格快报\",\"pgeid\": 188,\"uid\": 505},{\"sid\": 7981790,\"rq\": \"2016\\u002F12\\u002F21 9:58:48\",\"title\": \"[硫酸]：甘肃白银集团硫酸价格快报\",\"pgeid\": 186,\"uid\": 10524},{\"sid\": 7981789,\"rq\": \"2016\\u002F12\\u002F21 9:58:38\",\"title\": \"[冰醋酸]：陕西延长石油冰醋酸价格动态\",\"pgeid\": 165,\"uid\": 19448},{\"sid\": 7981788,\"rq\": \"2016\\u002F12\\u002F21 9:58:34\",\"title\": \"[粗苯]：唐山市汇丰炼焦制气粗苯价格报道\",\"pgeid\": 406,\"uid\": 18691},{\"sid\": 7981787,\"rq\": \"2016\\u002F12\\u002F21 9:58:23\",\"title\": \"[硫酸]：甘肃金川硫酸价格快报\",\"pgeid\": 186,\"uid\": 10453},{\"sid\": 7981786,\"rq\": \"2016\\u002F12\\u002F21 9:58:20\",\"title\": \"[动力煤]：安阳市场动力煤行情无明显起伏\",\"pgeid\": 473,\"uid\": 9690},{\"sid\": 7981785,\"rq\": \"2016\\u002F12\\u002F21 9:58:19\",\"title\": \"[乙烯焦油]：广州石化裂解燃料油价格快报\",\"pgeid\": 321,\"uid\": 11123},{\"sid\": 7981784,\"rq\": \"2016\\u002F12\\u002F21 9:58:11\",\"title\": \"[山东]：淄博地区汇丰石化汽油、柴油价格快报\",\"pgeid\": 199,\"uid\": 8786},{\"sid\": 7981783,\"rq\": \"2016\\u002F12\\u002F21 9:58:03\",\"title\": \"[硫酸]：常熟欣福化工有限公司硫酸价格快报\",\"pgeid\": 186,\"uid\": 10507},{\"sid\": 7981782,\"rq\": \"2016\\u002F12\\u002F21 9:58:00\",\"title\": \"[动力煤]：武威市场动力煤行情无明显起伏\",\"pgeid\": 473,\"uid\": 9699},{\"sid\": 7981781,\"rq\": \"2016\\u002F12\\u002F21 9:57:55\",\"title\": \"[戊二烯]：天津鲁华 精双环戊二烯价格快报\",\"pgeid\": 537,\"uid\": 11120},{\"sid\": 7981780,\"rq\": \"2016\\u002F12\\u002F21 9:57:54\",\"title\": \"[硫酸]：无锡阳恒化工硫酸价格快报\",\"pgeid\": 186,\"uid\": 10506},{\"sid\": 7981779,\"rq\": \"2016\\u002F12\\u002F21 9:57:53\",\"title\": \"[减线油]：华南地区减线油价格一览表（20161221）\",\"pgeid\": 202,\"uid\": 8276},{\"sid\": 7981778,\"rq\": \"2016\\u002F12\\u002F21 9:57:53\",\"title\": \"[二甲醚]：河南义马新源二甲醚价格上调\",\"pgeid\": 466,\"uid\": 16271},{\"sid\": 7981777,\"rq\": \"2016\\u002F12\\u002F21 9:57:47\",\"title\": \"[纯苯]：青岛石化纯苯产销动态\",\"pgeid\": 132,\"uid\": 14594}] }","folderId":"1N4zdaeQKS","id":"1TPeMS0PiY","lastUpdateTime":"2016-12-21 09:59:32","moduleId":"1N4zGX68Ac","name":"获取首页界面信息","projectId":"1N4zDUswUZ","protocol":"HTTP","requestArgs":"[{\"require\":\"true\",\"children\":[],\"type\":\"string\",\"name\":\"lanmuid\",\"defaultValue\":\"\",\"description\":\"lanmu  :  tt tj \"}]","requestHeaders":"[]","requestMethod":"POST","responseArgs":"[{\"children\":[{\"children\":[],\"name\":\"sid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"rq\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"title\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"pgeid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"uid\",\"type\":\"number\",\"require\":\"true\"}],\"name\":\"tiaojia\",\"type\":\"array[object]\",\"require\":\"true\"},{\"children\":[{\"children\":[],\"name\":\"sid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"rq\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"title\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"pgeid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"uid\",\"type\":\"number\",\"require\":\"true\"}],\"name\":\"hot\",\"type\":\"array[object]\",\"require\":\"true\"},{\"children\":[{\"children\":[],\"name\":\"sid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"rq\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"title\",\"type\":\"string\",\"require\":\"true\"},{\"children\":[],\"name\":\"pgeid\",\"type\":\"number\",\"require\":\"true\"},{\"children\":[],\"name\":\"uid\",\"type\":\"number\",\"require\":\"true\"}],\"name\":\"zhishi\",\"type\":\"array[object]\",\"require\":\"true\"}]","sort":0,"status":"ENABLE","url":"$host$/home/home.do"}]
         * createTime : 2016-12-05 09:28:42
         * id : 1N4zdaeQKS
         * moduleId : 1N4zGX68Ac
         * name : 首页
         * projectId : 1N4zDUswUZ
         * sort : 0
         */

        private String createTime;
        private String id;
        private String moduleId;
        private String name;
        private String projectId;
        private int sort;
        private List<ChildrenBean> children;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }


    }

    public static class ChildrenBean {
        /**
         * contentType : JSON
         * createTime : 2016-12-21 09:35:08
         * dataType : X-WWW-FORM-URLENCODED
         * description : test
         * example : { "tiaojia": [{"sid": 7981778,"rq": "2016\u002F12\u002F21 9:57:53","title": "[二甲醚]：河南义马新源二甲醚价格上调","pgeid": 466,"uid": 16271},{"sid": 7981755,"rq": "2016\u002F12\u002F21 9:56:42","title": "[醋酸酯]：漳州欧硕醋酸甲酯价格动态","pgeid": 179,"uid": 21416},{"sid": 7981754,"rq": "2016\u002F12\u002F21 9:56:38","title": "[二甲醚]：河南义马开祥二甲醚行情上调","pgeid": 466,"uid": 16243},{"sid": 7981695,"rq": "2016\u002F12\u002F21 9:54:02","title": "[华东]：江苏新海石化汽油、柴油价格上调","pgeid": 199,"uid": 8800},{"sid": 7981681,"rq": "2016\u002F12\u002F21 9:52:59","title": "[工业萘]：江西黑猫炭黑股份有限公司工业萘价格上调","pgeid": 412,"uid": 20588}], "hot": [{"sid": 7975499,"rq": "2016\u002F12\u002F19 15:42:11","title": "[苯胺]：山东金岭苯胺明日价格上调","pgeid": 219,"uid": 12405},{"sid": 7971225,"rq": "2016\u002F12\u002F16 17:00:19","title": "[苯胺]：南化苯胺价格明日价格上调","pgeid": 219,"uid": 12406},{"sid": 7963476,"rq": "2016\u002F12\u002F15 8:59:13","title": "2017年原油非国营贸易进口允许量总量、申请条件和申请程序","pgeid": 199,"uid": 22657},{"sid": 7962676,"rq": "2016\u002F12\u002F14 16:39:07","title": "多重利好提振 油市价格涨疯了","pgeid": 199,"uid": 18328},{"sid": 7958583,"rq": "2016\u002F12\u002F13 19:33:46","title": "厉害了,word船燃!","pgeid": 206,"uid": 9970}], "zhishi": [{"sid": 7981796,"rq": "2016\u002F12\u002F21 9:59:02","title": "[动力煤]：六盘水市场动力煤行情","pgeid": 473,"uid": 9737},{"sid": 7981795,"rq": "2016\u002F12\u002F21 9:59:01","title": "[乙醇]：山东无棣润生玉米乙醇价格暂稳","pgeid": 142,"uid": 15145},{"sid": 7981794,"rq": "2016\u002F12\u002F21 9:58:59","title": "[石脑油]：东营地区亚通石化石脑油价格持稳","pgeid": 207,"uid": 16082},{"sid": 7981793,"rq": "2016\u002F12\u002F21 9:58:56","title": "[PE]：大庆石化聚乙烯低压C线装置开车动态","pgeid": 328,"uid": 14513},{"sid": 7981792,"rq": "2016\u002F12\u002F21 9:58:53","title": "[液化气]：延炼新能源民用气网拍成交快报","pgeid": 455,"uid": 8511},{"sid": 7981791,"rq": "2016\u002F12\u002F21 9:58:52","title": "[尿素]：河南心连心尿素价格快报","pgeid": 188,"uid": 505},{"sid": 7981790,"rq": "2016\u002F12\u002F21 9:58:48","title": "[硫酸]：甘肃白银集团硫酸价格快报","pgeid": 186,"uid": 10524},{"sid": 7981789,"rq": "2016\u002F12\u002F21 9:58:38","title": "[冰醋酸]：陕西延长石油冰醋酸价格动态","pgeid": 165,"uid": 19448},{"sid": 7981788,"rq": "2016\u002F12\u002F21 9:58:34","title": "[粗苯]：唐山市汇丰炼焦制气粗苯价格报道","pgeid": 406,"uid": 18691},{"sid": 7981787,"rq": "2016\u002F12\u002F21 9:58:23","title": "[硫酸]：甘肃金川硫酸价格快报","pgeid": 186,"uid": 10453},{"sid": 7981786,"rq": "2016\u002F12\u002F21 9:58:20","title": "[动力煤]：安阳市场动力煤行情无明显起伏","pgeid": 473,"uid": 9690},{"sid": 7981785,"rq": "2016\u002F12\u002F21 9:58:19","title": "[乙烯焦油]：广州石化裂解燃料油价格快报","pgeid": 321,"uid": 11123},{"sid": 7981784,"rq": "2016\u002F12\u002F21 9:58:11","title": "[山东]：淄博地区汇丰石化汽油、柴油价格快报","pgeid": 199,"uid": 8786},{"sid": 7981783,"rq": "2016\u002F12\u002F21 9:58:03","title": "[硫酸]：常熟欣福化工有限公司硫酸价格快报","pgeid": 186,"uid": 10507},{"sid": 7981782,"rq": "2016\u002F12\u002F21 9:58:00","title": "[动力煤]：武威市场动力煤行情无明显起伏","pgeid": 473,"uid": 9699},{"sid": 7981781,"rq": "2016\u002F12\u002F21 9:57:55","title": "[戊二烯]：天津鲁华 精双环戊二烯价格快报","pgeid": 537,"uid": 11120},{"sid": 7981780,"rq": "2016\u002F12\u002F21 9:57:54","title": "[硫酸]：无锡阳恒化工硫酸价格快报","pgeid": 186,"uid": 10506},{"sid": 7981779,"rq": "2016\u002F12\u002F21 9:57:53","title": "[减线油]：华南地区减线油价格一览表（20161221）","pgeid": 202,"uid": 8276},{"sid": 7981778,"rq": "2016\u002F12\u002F21 9:57:53","title": "[二甲醚]：河南义马新源二甲醚价格上调","pgeid": 466,"uid": 16271},{"sid": 7981777,"rq": "2016\u002F12\u002F21 9:57:47","title": "[纯苯]：青岛石化纯苯产销动态","pgeid": 132,"uid": 14594}] }
         * folderId : 1N4zdaeQKS
         * id : 1TPeMS0PiY
         * lastUpdateTime : 2016-12-21 09:59:32
         * moduleId : 1N4zGX68Ac
         * name : 获取首页界面信息
         * projectId : 1N4zDUswUZ
         * protocol : HTTP
         * requestArgs : [{"require":"true","children":[],"type":"string","name":"lanmuid","defaultValue":"","description":"lanmu  :  tt tj "}]
         * requestHeaders : []
         * requestMethod : POST
         * responseArgs : [{"children":[{"children":[],"name":"sid","type":"number","require":"true"},{"children":[],"name":"rq","type":"string","require":"true"},{"children":[],"name":"title","type":"string","require":"true"},{"children":[],"name":"pgeid","type":"number","require":"true"},{"children":[],"name":"uid","type":"number","require":"true"}],"name":"tiaojia","type":"array[object]","require":"true"},{"children":[{"children":[],"name":"sid","type":"number","require":"true"},{"children":[],"name":"rq","type":"string","require":"true"},{"children":[],"name":"title","type":"string","require":"true"},{"children":[],"name":"pgeid","type":"number","require":"true"},{"children":[],"name":"uid","type":"number","require":"true"}],"name":"hot","type":"array[object]","require":"true"},{"children":[{"children":[],"name":"sid","type":"number","require":"true"},{"children":[],"name":"rq","type":"string","require":"true"},{"children":[],"name":"title","type":"string","require":"true"},{"children":[],"name":"pgeid","type":"number","require":"true"},{"children":[],"name":"uid","type":"number","require":"true"}],"name":"zhishi","type":"array[object]","require":"true"}]
         * sort : 0
         * status : ENABLE
         * url : $host$/home/home.do
         */

        private String contentType;
        private String createTime;
        private String dataType;
        private String description;
        private String example;
        private String folderId;
        private String id;
        private String lastUpdateTime;
        private String moduleId;
        private String name;
        private String projectId;
        private String protocol;
        private String requestArgs;
        private String requestHeaders;
        private String requestMethod;
        private String responseArgs;
        private int sort;
        private String status;
        private String url;

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getExample() {
            return example;
        }

        public void setExample(String example) {
            this.example = example;
        }

        public String getFolderId() {
            return folderId;
        }

        public void setFolderId(String folderId) {
            this.folderId = folderId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getRequestArgs() {
            return requestArgs;
        }

        public void setRequestArgs(String requestArgs) {
            this.requestArgs = requestArgs;
        }

        public String getRequestHeaders() {
            return requestHeaders;
        }

        public void setRequestHeaders(String requestHeaders) {
            this.requestHeaders = requestHeaders;
        }

        public String getRequestMethod() {
            return requestMethod;
        }

        public void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        public String getResponseArgs() {
            return responseArgs;
        }

        public void setResponseArgs(String responseArgs) {
            this.responseArgs = responseArgs;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

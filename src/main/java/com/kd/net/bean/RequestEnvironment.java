package com.kd.net.bean;

import java.util.List;

/**
 * 环境变量
 * Created by developer on 2017/6/5.
 */

public class RequestEnvironment {

    /**
     * name : release
     * t : 1480901507432
     * vars : [{"name":"host","value":"http://sc.oilchem.net"}]
     */

    private String name;
    private List<VarsBean> vars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VarsBean> getVars() {
        return vars;
    }

    public void setVars(List<VarsBean> vars) {
        this.vars = vars;
    }

    public static class VarsBean {
        /**
         * name : host
         * value : http://sc.oilchem.net
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

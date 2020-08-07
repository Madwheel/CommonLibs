package com.iwangzhe.commonlibs.mod.biz.system.conf;


public class JBizSystemConf {
    private String[] innerAuthHosts;
    private String upgradeUrl;

    public JBizSystemConf() {
        this.innerAuthHosts = new String[]{};
        this.upgradeUrl = "";
    }

    public String getUpgradeUrl() {
        return upgradeUrl;
    }

    public void setUpgradeUrl(String upgradeUrl) {
        this.upgradeUrl = upgradeUrl;
    }

    public void setInnerAuthHosts(String[] value) {
        innerAuthHosts = value;
    }

    public String[] getInnerAuthHosts() {
        return innerAuthHosts;
    }
}

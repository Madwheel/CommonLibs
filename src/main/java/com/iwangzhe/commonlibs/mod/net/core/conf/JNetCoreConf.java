package com.iwangzhe.commonlibs.mod.net.core.conf;

public class JNetCoreConf {
    private String apiHost;
    private String cmsHost;
    private String masterstationHost;
    private String passportHost;
    private String bbsHost;

    public JNetCoreConf() {
        apiHost = "";
        cmsHost = "";
        masterstationHost = "";
        passportHost = "";
        bbsHost = "";
    }

    public String getCmsHost() {
        return cmsHost == null ? "" : cmsHost;
    }

    public void setCmsHost(String cmsHost) {
        this.cmsHost = cmsHost;
    }

    public String getMasterstationHost() {
        return masterstationHost == null ? "" : masterstationHost;
    }

    public String getPassportHost() {
        return passportHost == null ? "" : passportHost;
    }

    public void setPassportHost(String passportHost) {
        this.passportHost = passportHost;
    }

    public String getBbsHost() {
        return bbsHost == null ? "" : bbsHost;
    }

    public void setBbsHost(String bbsHost) {
        this.bbsHost = bbsHost;
    }

    public void setMasterstationHost(String masterstationHost) {
        this.masterstationHost = masterstationHost;
    }

    public void setApiHost(String value) {
        apiHost = value;
    }

    public String getApiHost() {
        return apiHost;
    }
}

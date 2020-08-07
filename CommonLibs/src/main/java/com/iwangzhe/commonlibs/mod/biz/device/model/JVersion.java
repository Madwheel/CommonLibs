package com.iwangzhe.commonlibs.mod.biz.device.model;


import com.iwangzhe.commonlibs.base.JBase;

/**
 * 描述：
 * 作者：小辉
 * 时间：2019/10/18
 */
public class JVersion extends JBase {
    private String versionName;
    private String versionApi;
    private String versionSvn;
    private String displayApiVersion;//显示的接口版本号 : d.x.y.z
    private String appApiVersion;//APP版本、Api版本组合字符串

    public JVersion() {
        this.versionName = "";
        this.versionApi = "";
        this.versionSvn = "";
        this.displayApiVersion = "";
        this.appApiVersion = "";
    }

    public String getDisplayApiVersion() {
        return displayApiVersion == null ? "" : displayApiVersion;
    }

    public void setDisplayApiVersion(String displayApiVersion) {
        this.displayApiVersion = displayApiVersion;
    }

    public String getAppApiVersion() {
        return appApiVersion == null ? "" : appApiVersion;
    }

    public void setAppApiVersion(String appApiVersion) {
        this.appApiVersion = appApiVersion;
    }

    public String getVersionName() {
        return versionName == null ? "" : versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionApi() {
        return versionApi == null ? "" : versionApi;
    }

    public void setVersionApi(String versionApi) {
        this.versionApi = versionApi;
    }

    public String getVersionSvn() {
        return versionSvn == null ? "" : versionSvn;
    }

    public void setVersionSvn(String versionSvn) {
        this.versionSvn = versionSvn;
    }
}

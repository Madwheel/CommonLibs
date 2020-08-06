package com.iwangzhe.commonlibs.mod.biz.device.model;


import com.iwangzhe.commonlibs.base.JBase;

public class JLaunch extends JBase {
    private String did;
    private double sec;
    private String ip;
    private double diff;

    public JLaunch() {
        did = "";
        sec = 0;
        ip = "";
        diff = 0;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getSec() {
        return sec;
    }

    public void setSec(double sec) {
        this.sec = sec;
    }

    public String getIp() {
        return ip == null ? "" : ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setDid(String value) {
        did = value;
    }

    public String getDid() {
        return did;
    }
}

package com.iwangzhe.commonlibs.mod.net.core.conf;


import com.iwangzhe.commonlibs.base.ConfApi;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.core.config.CoreConfigMain;

public class NetCoreConfApi extends ConfApi {
    private static NetCoreConfApi instance = null;

    protected NetCoreConfApi(ModMain main) {
        super(main);
    }

    public static NetCoreConfApi getInstance(ModMain main) {
        if (instance == null) {
            instance = new NetCoreConfApi(main);
        }
        return instance;
    }

    private JNetCoreConf mConf;

    public void born() {
        mConf = CoreConfigMain.getInstance().getModConf("mod.net.core", JNetCoreConf.class);
        if (mConf == null) {
            mConf = new JNetCoreConf();
        }
    }

    public String getApiHost() {
        return mConf.getApiHost();
    }

    public JNetCoreConf getConf() {
        return mConf;
    }
}

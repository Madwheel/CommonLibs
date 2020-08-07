package com.iwangzhe.commonlibs.mod.net.core;


import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.net.core.conf.JNetCoreConf;
import com.iwangzhe.commonlibs.mod.net.core.conf.NetCoreConfApi;
import com.iwangzhe.commonlibs.mod.net.core.control.NetCoreControlApp;

public class NetCoreMain extends ModMain {
    public NetCoreConfApi pConfApi;
    public NetCoreControlApp pControl;

    public String getModName() {
        return "NetCoreMain";
    }


    private NetCoreMain() {
        pConfApi = NetCoreConfApi.getInstance(this);
        pControl = NetCoreControlApp.getInstance(this);
    }

    private static NetCoreMain mNetCoreMain = null;

    public static NetCoreMain getInstance() {
        synchronized (NetCoreMain.class) {
            if (mNetCoreMain == null) {
                mNetCoreMain = new NetCoreMain();
            }
        }
        return mNetCoreMain;
    }


    public void born() {
        super.born();
        pConfApi.born();

    }

    public NetCoreControlApp getControlApp() {
        return pControl;
    }
}

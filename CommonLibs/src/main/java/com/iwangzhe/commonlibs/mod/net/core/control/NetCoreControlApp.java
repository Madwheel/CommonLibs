package com.iwangzhe.commonlibs.mod.net.core.control;

import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.net.core.NetCoreMain;
import com.iwangzhe.commonlibs.mod.net.core.conf.JNetCoreConf;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3113:30
 * desc   :
 */
public class NetCoreControlApp extends ControlApp {
    public NetCoreMain mMain;

    public NetCoreControlApp(NetCoreMain main) {
        super(main);
        mMain = main;
    }

    private static NetCoreControlApp mNetCoreControlApp = null;

    public static NetCoreControlApp getInstance(NetCoreMain main) {
        synchronized (NetCoreControlApp.class) {
            if (mNetCoreControlApp == null) {
                mNetCoreControlApp = new NetCoreControlApp(main);
            }
        }
        return mNetCoreControlApp;
    }

    public JNetCoreConf getConf() {
        return mMain.pConfApi.getConf();
    }
}

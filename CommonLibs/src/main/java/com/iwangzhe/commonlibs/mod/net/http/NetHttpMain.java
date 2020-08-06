package com.iwangzhe.commonlibs.mod.net.http;


import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.net.http.serv.NetHttpServApi;

public class NetHttpMain extends ModMain {
    public String getModName() {
        return "NetHttpMain";
    }

    private static NetHttpMain mNetHttpMain;

    public static NetHttpMain getInstance() {
        if (mNetHttpMain == null) {
            synchronized (NetHttpMain.class) {
                if (mNetHttpMain == null) {
                    mNetHttpMain = new NetHttpMain();
                }
            }
        }
        return mNetHttpMain;
    }


    public NetHttpServApi pServApi;

    public void born() {
        super.born();
        pServApi = NetHttpServApi.getInstance(this);

    }

    public NetHttpServApi getServApi() {
        return pServApi;
    }
}

package com.iwangzhe.commonlibs.mod.core.env;


import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.Mgr;

public class CoreEnvMain extends ModMain {
    public String getModName() {
        return "CoreEnvMain";
    }

    private static CoreEnvMain instance = new CoreEnvMain();

    private CoreEnvMain() {
    }

    public static CoreEnvMain getInstance() {
        return instance;
    }

    public void born() {
        super.born();
    }

    public String getEnvName() {
        return Mgr.getmEnv();
    }

    public String getChannelName() {
        return Mgr.getmChannel();
    }

    public boolean isDev() {
        return getEnvName().equals("dev");
    }
}



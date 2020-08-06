package com.iwangzhe.commonlibs.mod.core.signal;


import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.ModMain;

/*
import sun.misc.Signal;
import sun.misc.SignalHandler;
*/
public class CoreSignalMain extends ModMain {
    public String getModName() {
        return "CoreSignalMain";
    }

    public ControlApp getControlApp() {
        return null;
    }

    private static CoreSignalMain instance = new CoreSignalMain();

    private CoreSignalMain() {
    }

    public static CoreSignalMain getInstance() {
        return instance;
    }

    static final int MAX_T = 3;
    //static ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    private boolean mSigint = false;

    public void born() {

    }

    public void setSigintOk() {
        mSigint = true;
    }

    public boolean isSigintOk() {
        return mSigint;
    }
}




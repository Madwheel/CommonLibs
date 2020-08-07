package com.iwangzhe.commonview;

import android.app.Application;
import android.content.Context;

import com.iwangzhe.commonlibs.base.EAppPhase;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonview.adv.AdvCommonViewMain;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/410:26
 * desc   :
 */
public class AdApplication {
    private static String mEnv;
    ///TODO:
    private static String mChannel;
    private static Context sContext;
    private static String mPlat;
    private static String mVersionApi;

    private static AdApplication mAdvManager = null;

    public static AdApplication getInstance() {
        synchronized (AdApplication.class) {
            if (mAdvManager == null) {
                mAdvManager = new AdApplication();
            }
        }
        return mAdvManager;
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static String getEnv() {
        return mEnv;
    }

    public static String getChannel() {
        return mChannel;
    }

    public static String getPlat() {
        return mPlat;
    }

    public static String getVersionApi() {
        return mVersionApi;
    }

    public void init(Application application, String plat, String versionApi, String env, String channel) {
        mPlat = plat;
        mVersionApi = versionApi;
        mEnv = env;
        mChannel = channel;
        sContext = application.getApplicationContext();
        Mgr.loop();
        Mgr.setData(plat, versionApi, env, channel, sContext);
        Mgr.getAllMods();
        Mgr.setAllMods(AdvCommonViewMain.getInstance());
        Mgr.born();
        Mgr.create();
        Mgr.phase(EAppPhase.APP_PHASE_LAUNCH);
        Mgr.active();
    }
}

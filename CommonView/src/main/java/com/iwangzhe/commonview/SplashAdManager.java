package com.iwangzhe.commonview;

import android.app.Activity;

import com.iwangzhe.commonview.adv.AdvCommonViewMain;
import com.iwangzhe.commonview.adv.model.OnSplashAdListener;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/611:13
 * desc   :
 */
public class SplashAdManager {
    private static SplashAdManager mSplashAdManager = null;

    public static SplashAdManager getInstance() {
        synchronized (SplashAdManager.class) {
            if (mSplashAdManager == null) {
                mSplashAdManager = new SplashAdManager();
            }
        }
        return mSplashAdManager;
    }

    public void initSplashAd(String pageKey, String posKey) {
        AdvCommonViewMain.getInstance().getControl().initAdverts(pageKey, posKey);
    }

    public void loadAd(String pageKey, String posKey, Activity activity, OnSplashAdListener listener) {
        if (AdvCommonViewMain.getInstance().getControl().isExistAdv(pageKey, posKey)) {
            AdvCommonViewMain.getInstance().getControl().showSplashAd(pageKey, posKey, activity, listener);
        } else {
            if (listener != null) {
                listener.closeAd();
            }
        }
    }
}

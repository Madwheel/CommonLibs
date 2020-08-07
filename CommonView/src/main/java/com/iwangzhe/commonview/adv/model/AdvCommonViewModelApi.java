package com.iwangzhe.commonview.adv.model;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.base.ModelApi;
import com.iwangzhe.commonview.adv.AdvCommonViewMain;

import java.util.HashMap;
import java.util.Map;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3116:18
 * desc   :
 */
public class AdvCommonViewModelApi extends ModelApi {

    private AdvCommonViewMain mMain;
    private SplashAdInfo splashAdInfo;

    public AdvCommonViewModelApi(AdvCommonViewMain main) {
        super(main);
        mMain = main;
    }

    private static AdvCommonViewModelApi mAdvCommonViewModelApi = null;

    public static AdvCommonViewModelApi getInstance(AdvCommonViewMain main) {
        synchronized (AdvCommonViewModelApi.class) {
            if (mAdvCommonViewModelApi == null) {
                mAdvCommonViewModelApi = new AdvCommonViewModelApi(main);
            }
        }
        return mAdvCommonViewModelApi;
    }

    private Map<String, JAdvInfo> advInfoMap;

    @Override
    public void born() {
        super.born();
        advInfoMap = new HashMap<>();
        splashAdInfo = new SplashAdInfo();
    }

    public Map<String, JAdvInfo> getAdvInfoMap() {
        return advInfoMap;
    }

    public SplashAdInfo getSplashAdInfo() {
        return splashAdInfo;
    }

    public void setSplashAdInfo(SplashAdInfo splashAdInfo) {
        this.splashAdInfo = splashAdInfo;
    }

    public void setAdvInfoMap(Map<String, JAdvInfo> advInfoMap) {

        this.advInfoMap = advInfoMap;
    }
}

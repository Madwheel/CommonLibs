package com.iwangzhe.commonlibs.mod.biz.system.conf;


import android.graphics.Typeface;


import com.iwangzhe.commonlibs.base.ConfApi;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.core.config.CoreConfigMain;

import java.util.Arrays;
import java.util.List;

public class BizSystemConfApi extends ConfApi {
    private static BizSystemConfApi instance = null;

    protected BizSystemConfApi(ModMain main) {
        super(main);
    }

    public static BizSystemConfApi getInstance(ModMain main) {
        if (instance == null) {
            instance = new BizSystemConfApi(main);
        }
        return instance;
    }

    private JBizSystemConf mConf;
    private Typeface pingFang;

    public void born() {
        pingFang = Typeface.createFromAsset(Mgr.getmContext().getAssets(), "fonts/PingFang.ttf");

        mConf = CoreConfigMain.getInstance().getModConf("mod.biz.system", JBizSystemConf.class);
        if (mConf == null) {
            mConf = new JBizSystemConf();
        }
    }

    public Typeface getPingFang() {
        if (pingFang == null) {
            pingFang = Typeface.createFromAsset(Mgr.getmContext().getAssets(), "fonts/PingFang.ttf");
        }
        return pingFang;
    }

    public boolean isNotInnerAuthHost(String host) {
        List<String> hosts = Arrays.asList(mConf.getInnerAuthHosts());
        if (hosts == null) {
            return true;
        }
        return !hosts.contains(host);
    }

    public String getUpgradeUrl() {
        return mConf.getUpgradeUrl();
    }
}

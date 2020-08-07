package com.iwangzhe.commonlibs.mod.biz.device.model;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.base.ModelApi;
import com.iwangzhe.commonlibs.mod.biz.device.BizDeviceMain;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/415:21
 * desc   :
 */
public class BizDeviceModelApi extends ModelApi {
    private BizDeviceMain mMain;

    public BizDeviceModelApi(BizDeviceMain main) {
        super(main);
        mMain = main;
        mLaunch = new JLaunch();
        mVersion = new JVersion();
    }

    private static BizDeviceModelApi mBizDeviceModelApi = null;

    public static BizDeviceModelApi getInstance(BizDeviceMain main) {
        synchronized (BizDeviceModelApi.class) {
            if (mBizDeviceModelApi == null) {
                mBizDeviceModelApi = new BizDeviceModelApi(main);
            }
        }
        return mBizDeviceModelApi;
    }

    @Override
    public void born() {
        super.born();
    }

    private JLaunch mLaunch;

    public void setLaunch(JLaunch launch) {
        mLaunch = launch;
    }

    public JLaunch getLaunch() {
        return mLaunch;
    }

    private JVersion mVersion;

    public void setVersion(JVersion version) {
        mVersion = version;
    }

    public JVersion getVersion() {
        return mVersion;
    }
}

package com.iwangzhe.commonlibs.mod.biz.device;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.biz.device.control.BizDeviceControlApp;
import com.iwangzhe.commonlibs.mod.biz.device.model.BizDeviceModelApi;
import com.iwangzhe.commonlibs.mod.biz.device.serv.BizDeviceServApi;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/415:19
 * desc   :
 */
public class BizDeviceMain extends ModMain {
    @Override
    public String getModName() {
        return "BizDeviceMain";
    }

    private static BizDeviceMain mBizDeviceMain = null;

    public static BizDeviceMain getInstance() {
        synchronized (BizDeviceMain.class) {
            if (mBizDeviceMain == null) {
                mBizDeviceMain = new BizDeviceMain();
            }
        }
        return mBizDeviceMain;
    }


    public final BizDeviceModelApi pModel;
    public final BizDeviceServApi pServ;
    public final BizDeviceControlApp pControl;

    public BizDeviceMain() {
        pModel = BizDeviceModelApi.getInstance(this);
        pServ = BizDeviceServApi.getInstance(this);
        pControl = BizDeviceControlApp.getInstance(this);
    }

    public void born() {
        super.born();
        pModel.born();
        pServ.born();
        pControl.born();
    }

    public BizDeviceControlApp getControl() {
        return pControl;
    }
}

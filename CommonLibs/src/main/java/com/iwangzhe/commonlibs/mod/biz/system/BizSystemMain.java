package com.iwangzhe.commonlibs.mod.biz.system;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.biz.system.conf.BizSystemConfApi;
import com.iwangzhe.commonlibs.mod.biz.system.control.BizSysytemControlApp;
import com.iwangzhe.commonlibs.mod.biz.system.serv.BizSystemServApi;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/414:50
 * desc   :
 */
public class BizSystemMain extends ModMain {

    @Override
    public String getModName() {
        return "BizSystemMain";
    }

    private static BizSystemMain mBizSystemMain = null;

    public static BizSystemMain getInstance() {
        synchronized (BizSystemMain.class) {
            if (mBizSystemMain == null) {
                mBizSystemMain = new BizSystemMain();
            }
        }
        return mBizSystemMain;
    }

    public final BizSystemServApi pServ;
    public final BizSysytemControlApp pControl;
    public final BizSystemConfApi pConfApi;

    public BizSystemMain() {
        pConfApi = BizSystemConfApi.getInstance(this);
        pServ = BizSystemServApi.getInstance(this);
        pControl = BizSysytemControlApp.getInstance(this);
    }

    @Override
    public void born() {
        super.born();
        pConfApi.born();
        pServ.born();
        pControl.born();
    }

    public BizSysytemControlApp getControl() {
        return pControl;
    }
}

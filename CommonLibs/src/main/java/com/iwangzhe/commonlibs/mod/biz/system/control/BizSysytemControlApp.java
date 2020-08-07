package com.iwangzhe.commonlibs.mod.biz.system.control;

import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.biz.system.BizSystemMain;
import com.iwangzhe.commonlibs.mod.biz.system.control.event.NetDealer;
import com.iwangzhe.commonlibs.mod.tool.jni.JniEncryptUtil;

import java.util.Map;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/414:51
 * desc   :
 */
public class BizSysytemControlApp extends ControlApp {

    private BizSystemMain mMain;

    public BizSysytemControlApp(BizSystemMain main) {
        super(main);
        mMain = main;
    }

    private static BizSysytemControlApp mBizSysytemControlApp = null;

    public static BizSysytemControlApp getInstance(BizSystemMain main) {
        synchronized (BizSysytemControlApp.class) {
            if (mBizSysytemControlApp == null) {
                mBizSysytemControlApp = new BizSysytemControlApp(main);
            }
        }
        return mBizSysytemControlApp;
    }

    @Override
    public void born() {
        super.born();
        NetDealer netDealer = new NetDealer(mMain);
        netDealer.born();
    }

    /**
     * 将网络请求参数排序，加密并返回加密后的文本
     *
     * @param params 网络请求参数集合
     * @return 加密后的文本
     */
    public String getParamsSign(Map<String, String> params) {
        if (params == null) {
            return "";
        }
        //排序网络请求参数并生成URL
        String strParams = mMain.pServ.sortParams(params);
        //加密URL并返回
        return JniEncryptUtil.encryptParams(strParams);
    }
    public int getImgFromStr(String imgUrl) {
        return mMain.pServ.getImgFromStr(imgUrl);
    }
}

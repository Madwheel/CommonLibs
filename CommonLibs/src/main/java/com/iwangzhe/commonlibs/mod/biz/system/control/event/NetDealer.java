package com.iwangzhe.commonlibs.mod.biz.system.control.event;


import com.iwangzhe.commonlibs.base.CommonRes;
import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.JBase;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.biz.device.BizDeviceMain;
import com.iwangzhe.commonlibs.mod.biz.system.BizSystemMain;
import com.iwangzhe.commonlibs.mod.net.http.NetHttpMain;
import com.iwangzhe.commonlibs.mod.net.http.model.IWzHttpReqHook;

import java.util.Map;

import okhttp3.HttpUrl;

public class NetDealer extends ControlApp {
    private BizSystemMain mMain;

    public NetDealer(BizSystemMain main) {
        super(main);
        mMain = main;
    }

    public void born() {
        hookHttpReq();
    }

    public void hookHttpReq() {
        NetHttpMain.getInstance().getServApi().addWzHttpReqHook(new IWzHttpReqHook<JBase>() {
            @Override
            public Map<String, String> prepostParams(HttpUrl url, Map<String, String> params) {
                if (mMain.pConfApi.isNotInnerAuthHost(url.host())) {
                    return params;
                }
                //版本号等
                params.put("corp", Mgr.getmPlat());
                params.put("_uid", "");
                params.put("_version", BizDeviceMain.getInstance().getControl().getVersion().getVersionApi());//just for example
                params.put("_did", BizDeviceMain.getInstance().getControl().getLaunch().getDid());
                params.put("WZSignTime", BizDeviceMain.getInstance().getControl().getCurrentWZST(BizDeviceMain.getInstance().getControl().getLaunch().getDiff()));
                return params;
            }

            @Override
            public Map<String, String> postParams(HttpUrl url, Map<String, String> params) {
                params.put("_sign", mMain.pControl.getParamsSign(params));
                return params;
            }


            @Override
            public Map<String, String> prepostExtraHeaders(HttpUrl url, Map<String, String> params) {
                //判断url为王者财经域
                if (mMain.pConfApi.isNotInnerAuthHost(url.host())) {
                    return params;
                }
                return params;
            }

            @Override
            public Map<String, String> postExtraHeaders(HttpUrl url, Map<String, String> params) {
                return params;
            }

            @Override
            public <T> CommonRes<T> postRes(HttpUrl url, Map<String, String> params, CommonRes<T> cRes) {
                return cRes;
            }

            @Override
            public <T> CommonRes<T> prepostRes(HttpUrl url, Map<String, String> params, CommonRes<T> cRes) {
                return cRes;
            }
        });
    }

}

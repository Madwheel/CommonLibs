package com.iwangzhe.commonview.adv.serv;

import com.iwangzhe.commonlibs.base.CommonRes;
import com.iwangzhe.commonlibs.base.IResCallback;
import com.iwangzhe.commonlibs.base.JBase;
import com.iwangzhe.commonlibs.base.ServApi;
import com.iwangzhe.commonlibs.mod.net.http.NetHttpMain;
import com.iwangzhe.commonview.adv.AdvCommonViewMain;
import com.iwangzhe.commonview.adv.model.JAdvInfo;
import com.iwangzhe.commonview.constants.AppConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3115:17
 * desc   :
 */
public class AdvCommonViewServApi extends ServApi {
    private AdvCommonViewMain mMain;

    public AdvCommonViewServApi(AdvCommonViewMain main) {
        super(main);
        mMain = main;
    }

    private static AdvCommonViewServApi mAdvCommonViewServApi = null;

    public static AdvCommonViewServApi getInstance(AdvCommonViewMain main) {
        synchronized (AdvCommonViewServApi.class) {
            if (mAdvCommonViewServApi == null) {
                mAdvCommonViewServApi = new AdvCommonViewServApi(main);
            }
        }
        return mAdvCommonViewServApi;
    }

    public void getAdverts(String pageKey, String posKey, final IResCallback<JAdvInfo> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("pageKey", pageKey);
        params.put("posKey", posKey);
        NetHttpMain.getInstance().getServApi().reqGetResByWzApi(JAdvInfo.class, AppConstants.ADV_FETCH, params, new IResCallback<JAdvInfo>() {
            @Override
            public void onFinish(CommonRes<JAdvInfo> res) {
                callback.onFinish(res);
            }
        });

    }

    public void reportAdShow(int mapId, int pos, int total, final IResCallback<JBase> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("mapId", "" + mapId);
        params.put("pos", "" + pos);
        params.put("total", "" + total);
        NetHttpMain.getInstance().getServApi().reqGetResByWzApi(JBase.class, AppConstants.ADV_SHOW, params, new IResCallback<JBase>() {
            @Override
            public void onFinish(CommonRes<JBase> res) {
                callback.onFinish(res);
            }
        });
    }
}

package com.iwangzhe.commonview.adv.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.iwangzhe.commonlibs.base.CommonRes;
import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.IResCallback;
import com.iwangzhe.commonlibs.base.JBase;
import com.iwangzhe.commonlibs.mod.tool.ToolCommonLibsMain;
import com.iwangzhe.commonview.adv.AdvCommonViewMain;
import com.iwangzhe.commonview.adv.model.AdvertplanList;
import com.iwangzhe.commonview.adv.model.IAdListener;
import com.iwangzhe.commonview.adv.model.JAdvInfo;
import com.iwangzhe.commonview.adv.model.OnSlideShowListener;
import com.iwangzhe.commonview.adv.model.OnSplashAdListener;
import com.iwangzhe.commonview.adv.model.SplashAdInfo;
import com.iwangzhe.commonview.adv.view.SlideShowView;
import com.iwangzhe.commonview.adv.view.SplashAdActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3115:10
 * desc   :
 */
public class AdvCommonViewControlApp extends ControlApp {

    private AdvCommonViewMain mMain;
    private IAdListener adListener;
    private Map<String, Long> reportMap;

    public AdvCommonViewControlApp(AdvCommonViewMain main) {
        super(main);
        mMain = main;
    }

    private static AdvCommonViewControlApp mViewSlideShowControlApp = null;

    public static AdvCommonViewControlApp getInstance(AdvCommonViewMain main) {
        synchronized (AdvCommonViewControlApp.class) {
            if (mViewSlideShowControlApp == null) {
                mViewSlideShowControlApp = new AdvCommonViewControlApp(main);
            }
        }
        return mViewSlideShowControlApp;
    }

    @Override
    public void born() {
        super.born();
        reportMap = new HashMap<>();
    }

    /**
     * 初始化广告信息
     *
     * @param pageKey
     * @param posKey
     */
    public void initAdverts(final String pageKey, final String posKey) {
        mMain.pServ.getAdverts(pageKey, posKey, new IResCallback<JAdvInfo>() {
            @Override
            public void onFinish(CommonRes<JAdvInfo> res) {
                if (res.isOk()) {
                    JAdvInfo resObj = res.getResObj();
                    Map<String, JAdvInfo> advInfoMap = mMain.pModel.getAdvInfoMap();
                    advInfoMap.put(pageKey + posKey, resObj);
                    mMain.pModel.setAdvInfoMap(advInfoMap);
                    mMain.pServ.setAdvInfoToDb(pageKey + posKey, resObj);
                    if (adListener != null) {
                        adListener.onSuccess(pageKey, posKey);
                    }
                }
            }
        });
    }

    /**
     * 是否存在广告
     *
     * @param pageKey
     * @param posKey
     * @return
     */
    public boolean isExistAdv(String pageKey, String posKey) {
        CommonRes<JAdvInfo> jAdvInfoFromDb = mMain.pServ.getJAdvInfoFromDb(pageKey + posKey);
        if (jAdvInfoFromDb.isOk()) {
            JAdvInfo resObj = jAdvInfoFromDb.getResObj();
            if (resObj != null && resObj.getPlanList().size() > 0) {
                Map<String, JAdvInfo> advInfoMap = mMain.pModel.getAdvInfoMap();
                advInfoMap.put(pageKey + posKey, resObj);
                mMain.pModel.setAdvInfoMap(advInfoMap);
                return true;
            }
        }
        Map<String, JAdvInfo> advInfoMap = mMain.pModel.getAdvInfoMap();
        if (advInfoMap == null && advInfoMap.size() == 0) {
            return false;
        }
        JAdvInfo jAdvInfo = advInfoMap.get(pageKey + posKey);
        if (jAdvInfo != null && jAdvInfo.getPlanList().size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 显示广告view
     *
     * @param advView
     * @param pageKey
     * @param posKey
     */
    public void showAdvView(SlideShowView advView, String pageKey, String posKey, OnSlideShowListener listener) {
        if (advView != null) {
            JAdvInfo jAdvInfo = mMain.pModel.getAdvInfoMap().get(pageKey + posKey);
            ArrayList<AdvertplanList> planList = jAdvInfo.getPlanList();
            if (planList.size() > 0) {
                //添加图片到图片列表里
                List<String> imageUrlList = new ArrayList<>();
                List<Integer> imageMapIdList = new ArrayList<>();
                List<String> jumpUrlList = new ArrayList<>();
                for (int i = 0; i < planList.size(); i++) {
                    jumpUrlList.add(planList.get(i).getJumpUrl());
                    JSONObject jsonObject = ToolCommonLibsMain.getInstance().getControl().getJSONObject(planList.get(i).getPics());
                    String string = jsonObject.getString("img_" + jAdvInfo.getPositionInfo().getWidth() + "x" + jAdvInfo.getPositionInfo().getHeight());
                    imageUrlList.add(string);
                    imageMapIdList.add(planList.get(i).getMapId());
                }
                advView.bindData(jumpUrlList, imageUrlList, imageMapIdList, listener);
            } else {
                advView.bindData(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Integer>(), null);
            }

        }
    }

    public void setAdListener(IAdListener adListener) {
        this.adListener = adListener;
    }

    public void showSplashAd(String pageKey, String posKey, Activity activity, OnSplashAdListener listener) {
        Intent intent = new Intent(activity, SplashAdActivity.class);
        intent.putExtra("pageKey", pageKey);
        intent.putExtra("posKey", posKey);
        SplashAdActivity.setListener(listener);
        activity.startActivity(intent);
    }

    /**
     * 广告位展示统计上报
     *
     * @param mapId (可选）广告素材id
     * @param pos   位置 0，1，2，。。
     * @param total 轮播图总数（多连图）
     */
    public void reportAdShow(int mapId, int pos, int total) {
        String key = "" + mapId + pos + total;
        if (reportMap != null && reportMap.size() > 0 && reportMap.containsKey(key)) {
            Long aLong = reportMap.get(key);
            if (System.currentTimeMillis() - aLong < 5 * 1000) {
                return;
            }
        }
        reportMap.put(key, System.currentTimeMillis());
        mMain.pServ.reportAdShow(mapId, pos, total, new IResCallback<JBase>() {
            @Override
            public void onFinish(CommonRes<JBase> res) {

            }
        });
    }

    public SplashAdInfo getSplashAdInfo(String pageKey, String posKey) {
        Map<String, JAdvInfo> advInfoMap = mMain.pModel.getAdvInfoMap();
        SplashAdInfo splashAdInfo = new SplashAdInfo();
        if (advInfoMap != null && advInfoMap.size() > 0 && advInfoMap.containsKey(pageKey + posKey)) {
            JAdvInfo jAdvInfo = advInfoMap.get(pageKey + posKey);
            ArrayList<AdvertplanList> planList = jAdvInfo.getPlanList();
            if (planList.size() > 0) {
                JSONObject jsonObject = ToolCommonLibsMain.getInstance().getControl().getJSONObject(planList.get(0).getPics());
                //添加图片到图片列表里
                splashAdInfo.setUrl(planList.get(0).getJumpUrl());
                splashAdInfo.setMid(planList.get(0).getMapId());
//                splashAdInfo.setImg_1080_1600(jsonObject.getString("img_" + jAdvInfo.getPositionInfo().getWidth() + "x" + jAdvInfo.getPositionInfo().getHeight()));
//                splashAdInfo.setImg_1080_1920(jsonObject.getString("img_" + jAdvInfo.getPositionInfo().getWidth() + "x" + jAdvInfo.getPositionInfo().getHeight()));
//                splashAdInfo.setImg_1080_1920(jsonObject.getString("img_" + jAdvInfo.getPositionInfo().getWidth() + "x" + jAdvInfo.getPositionInfo().getHeight()));
                splashAdInfo.setImg_1080_1600(jsonObject.getString("img_1080x1600"));
                splashAdInfo.setImg_1080_1920(jsonObject.getString("img_1142x2208"));
                splashAdInfo.setImg_1080_1920(jsonObject.getString("img_1125x2436"));
                mMain.pModel.setSplashAdInfo(splashAdInfo);
            }
        }
        return splashAdInfo;
    }

    public void setAdvInfoMap(Map<String, JAdvInfo> advInfoMap) {
        mMain.pModel.setAdvInfoMap(advInfoMap);
    }

}

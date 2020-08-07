package com.iwangzhe.commonlibs.mod.biz.device.control;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.iwangzhe.commonlibs.base.CommonRes;
import com.iwangzhe.commonlibs.base.ControlApp;
import com.iwangzhe.commonlibs.base.EAppPhase;
import com.iwangzhe.commonlibs.base.IResCallback;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.biz.device.BizDeviceMain;
import com.iwangzhe.commonlibs.mod.biz.device.model.JLaunch;
import com.iwangzhe.commonlibs.mod.biz.device.model.JVersion;

import java.util.UUID;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/415:23
 * desc   :
 */
public class BizDeviceControlApp extends ControlApp {
    private BizDeviceMain mMain;

    public BizDeviceControlApp(BizDeviceMain main) {
        super(main);
        mMain = main;
    }

    private static BizDeviceControlApp mBizDeviceControlApp = null;

    public static BizDeviceControlApp getInstance(BizDeviceMain main) {
        synchronized (BizDeviceControlApp.class) {
            if (mBizDeviceControlApp == null) {
                mBizDeviceControlApp = new BizDeviceControlApp(main);
            }
        }
        return mBizDeviceControlApp;
    }

    @Override
    public void born() {
        super.born();
        mMain.pServ.reqClientLaunch(getDeviceId(Mgr.getmContext()), Mgr.getmPlat(), new IResCallback<JLaunch>() {
            @Override
            public void onFinish(CommonRes<JLaunch> res) {
                if (res.isOk()) {
                    JLaunch resObj = res.getResObj();
                    resObj.setDiff(mMain.pServ.getDateInterval(resObj.getSec()));
                    mMain.pServ.setCurLaunchToDb(resObj);
                    mMain.pModel.setLaunch(resObj);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Mgr.phase(EAppPhase.APP_PHASE_LAUNCH_OK);
                        }
                    });
                } else {
                    d("reqClientLaunch:FAIL:" + res.getErrorCode());
                }
            }
        });
        initModelData();
    }

    public void initModelData() {
        //1、VersionToModel
        JVersion jVersion = new JVersion();
        jVersion.setVersionApi(Mgr.getmVersionApi());
        mMain.pModel.setVersion(jVersion);
        //2、LaunchFromDbToModel
        CommonRes<JLaunch> res = mMain.pServ.getCurLaunchFromDb();
        if (res.isOk()) {
            mMain.pModel.setLaunch(res.getResObj());
        }
    }

    public JVersion getVersion() {
        return mMain.pModel.getVersion();
    }

    public void setVersion(JVersion version) {
        mMain.pModel.setVersion(version);
    }

    public JLaunch getLaunch() {
        return mMain.pModel.getLaunch();
    }

    public void setLaunch(JLaunch launch) {
        mMain.pModel.setLaunch(launch);
    }

    public String getCurrentWZST(double diff) {
        return mMain.pServ.getCurrentWZST(diff);
    }

    /**
     * 获得设备硬件标识
     *
     * @param context 上下文
     * @return 设备硬件标识
     */
    public String getDeviceId(Context context) {
        StringBuilder sbDeviceId = new StringBuilder();

        //获得设备默认IMEI（>=6.0 需要ReadPhoneState权限）
        String imei = mMain.pServ.getIMEI(context);
        //获得AndroidId（无需权限）
        String androidid = mMain.pServ.getAndroidId(context);
        //获得设备序列号（无需权限）
        String serial = mMain.pServ.getSERIAL();
        //获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        String uuid = mMain.pServ.getDeviceUUID().replace("-", "");
        //获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        String packageName = Mgr.getmContext().getPackageName();

        //追加imei
        if (imei != null && imei.length() > 0) {
            sbDeviceId.append(imei);
            sbDeviceId.append("|");
        }
        //追加androidid
        if (androidid != null && androidid.length() > 0) {
            sbDeviceId.append(androidid);
            sbDeviceId.append("|");
        }
        //追加serial
        if (serial != null && serial.length() > 0) {
            sbDeviceId.append(serial);
            sbDeviceId.append("|");
        }
        //追加硬件uuid
        if (uuid != null && uuid.length() > 0) {
            sbDeviceId.append(uuid);
        }
        //追加硬件uuid
        if (packageName != null && packageName.length() > 0) {
            sbDeviceId.append(packageName);
        }
        //生成SHA1，统一DeviceId长度
        if (sbDeviceId.length() > 0) {
            try {
                byte[] hash = mMain.pServ.getHashByString(sbDeviceId.toString());
                String sha1 = mMain.pServ.bytesToHex(hash);
                if (sha1 != null && sha1.length() > 0) {
                    //返回最终的DeviceId
                    return sha1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //如果以上硬件标识数据均无法获得，
        //则DeviceId默认使用系统随机数，这样保证DeviceId不为空
        return UUID.randomUUID().toString().replace("-", "");
    }
    public boolean isNormalWindow(Activity activity) {
        float size = mMain.pServ.ontailWindowSize(activity);
        if (Math.abs(size - 1.78) <= Math.abs(size - 2.17)) {
            return true;
        } else {
            return false;
        }
    }
}

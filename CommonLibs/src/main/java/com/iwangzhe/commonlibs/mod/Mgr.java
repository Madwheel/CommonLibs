package com.iwangzhe.commonlibs.mod;


import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


import com.iwangzhe.commonlibs.base.EAppPhase;
import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.biz.device.BizDeviceMain;
import com.iwangzhe.commonlibs.mod.biz.system.BizSystemMain;
import com.iwangzhe.commonlibs.mod.core.config.CoreConfigMain;
import com.iwangzhe.commonlibs.mod.core.env.CoreEnvMain;
import com.iwangzhe.commonlibs.mod.core.signal.CoreSignalMain;
import com.iwangzhe.commonlibs.mod.io.file.IoFileMain;
import com.iwangzhe.commonlibs.mod.io.kvdb.IoKvdbMain;
import com.iwangzhe.commonlibs.mod.io.sqlite.IoSqliteMain;
import com.iwangzhe.commonlibs.mod.net.core.NetCoreMain;
import com.iwangzhe.commonlibs.mod.net.http.NetHttpMain;
import com.iwangzhe.commonlibs.mod.tool.ToolCommonLibsMain;

import java.util.ArrayList;
import java.util.List;

public class Mgr {
    private static List<ModMain> allMods;
    private static MyHandlerThread mThread;
    private static String mPlat;
    private static String mVersionApi;
    private static String mEnv;
    private static String mChannel;
    private static Context mContext;

    public static List<ModMain> getAllMods() {
        if (allMods == null) {
            allMods = new ArrayList<>();
            //commonTool
            allMods.add(ToolCommonLibsMain.getInstance());
            allMods.add(CoreConfigMain.getInstance());
            allMods.add(CoreEnvMain.getInstance());
            allMods.add(CoreSignalMain.getInstance());
            allMods.add(NetCoreMain.getInstance());
            allMods.add(IoSqliteMain.getInstance());
            allMods.add(IoKvdbMain.getInstance());
            allMods.add(IoFileMain.getInstance());
            allMods.add(NetHttpMain.getInstance());
            allMods.add(BizSystemMain.getInstance());
            allMods.add(BizDeviceMain.getInstance());
        }

        return allMods;
    }

    public static List<ModMain> setAllMods(ModMain main) {
        if (allMods == null) {
            allMods = new ArrayList<>();
        }
        allMods.add(main);
        return allMods;
    }

    public static void born() {
        System.out.println("born ");
        for (ModMain main : allMods) {
            main.born();
        }
    }

    public static void create() {
        System.out.println("create ");
        for (ModMain main : allMods) {
            main.create();
        }
    }

    public static void active() {
        for (ModMain main : allMods) {
            main.active();
        }
    }

    public static void phase(EAppPhase phase) {
        for (ModMain main : allMods) {
            main.phase(phase);
        }
    }

    public static void deactive() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).deactive();
        }
    }

    public static void destroy() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).destroy();
        }
    }

    public static void terminate() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).terminate();
        }
    }

    public static void loop() {
        mThread = new MyHandlerThread("Mgr");
        mThread.start();
    }

    public static Handler getHandler() {
        return mThread.getHandler();
    }

    public static void setData(String plat, String versionApi, String env, String channel, Context context) {
        mPlat = plat;
        mVersionApi = versionApi;
        mEnv = env;
        mChannel = channel;
        mContext = context;
    }

    public static String getmPlat() {
        return mPlat;
    }

    public static String getmVersionApi() {
        return mVersionApi;
    }

    public static String getmEnv() {
        return mEnv;
    }

    public static String getmChannel() {
        return mChannel;
    }

    public static Context getmContext() {
        return mContext;
    }
}

class MyHandlerThread extends HandlerThread {

    Handler mHandler;

    public MyHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // process incoming messages here
                // this will run in non-ui/background thread
            }
        };
    }

    public Handler getHandler() {
        return mHandler;
    }
}
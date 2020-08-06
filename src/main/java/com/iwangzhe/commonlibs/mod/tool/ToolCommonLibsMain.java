package com.iwangzhe.commonlibs.mod.tool;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.mod.tool.control.ToolCommonLibsControlApp;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/7/3018:18
 * desc   :
 */
public class ToolCommonLibsMain extends ModMain {
    @Override
    public String getModName() {
        return "ToolCommonLibsMain";
    }

    private ToolCommonLibsControlApp pControl;


    private static ToolCommonLibsMain mToolCommonLibsMain = null;

    public static ToolCommonLibsMain getInstance() {
        synchronized (ToolCommonLibsMain.class) {
            if (mToolCommonLibsMain == null) {
                mToolCommonLibsMain = new ToolCommonLibsMain();
            }
        }
        return mToolCommonLibsMain;
    }

    public ToolCommonLibsMain() {
        pControl = ToolCommonLibsControlApp.getInstance(this);
    }

    @Override
    public void born() {
        super.born();
    }

    public ToolCommonLibsControlApp getControl() {
        return pControl;
    }
}

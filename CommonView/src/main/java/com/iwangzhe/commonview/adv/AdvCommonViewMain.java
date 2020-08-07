package com.iwangzhe.commonview.adv;


import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonview.adv.control.AdvCommonViewControlApp;
import com.iwangzhe.commonview.adv.model.AdvCommonViewModelApi;
import com.iwangzhe.commonview.adv.serv.AdvCommonViewServApi;

/**
 * author : 小辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/6/249:54
 * desc   :
 */
public class AdvCommonViewMain extends ModMain {


    @Override
    public String getModName() {
        return "AdvCommonViewMain";
    }

    private static AdvCommonViewMain mViewSlideShowMain = null;

    public static AdvCommonViewMain getInstance() {
        synchronized (AdvCommonViewMain.class) {
            if (mViewSlideShowMain == null) {
                mViewSlideShowMain = new AdvCommonViewMain();
            }
        }
        return mViewSlideShowMain;
    }

    public final AdvCommonViewServApi pServ;
    public final AdvCommonViewControlApp pControl;
    public final AdvCommonViewModelApi pModel;

    public AdvCommonViewMain() {
        pModel = AdvCommonViewModelApi.getInstance(this);
        pServ = AdvCommonViewServApi.getInstance(this);
        pControl = AdvCommonViewControlApp.getInstance(this);
    }

    @Override
    public void born() {
        super.born();
        pModel.born();
        pServ.born();
        pControl.born();
    }

    public AdvCommonViewControlApp getControl() {
        return pControl;
    }
}

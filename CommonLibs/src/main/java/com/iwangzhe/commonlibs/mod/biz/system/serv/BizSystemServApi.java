package com.iwangzhe.commonlibs.mod.biz.system.serv;

import com.iwangzhe.commonlibs.base.ModMain;
import com.iwangzhe.commonlibs.base.ServApi;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.biz.system.BizSystemMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/414:52
 * desc   :
 */
public class BizSystemServApi extends ServApi {
    private BizSystemMain mMain;

    public BizSystemServApi(BizSystemMain main) {
        super(main);
        mMain = main;
    }

    private static BizSystemServApi mBizSystemServApi = null;

    public static BizSystemServApi getInstance(BizSystemMain main) {
        synchronized (BizSystemServApi.class) {
            if (mBizSystemServApi == null) {
                mBizSystemServApi = new BizSystemServApi(main);
            }
        }
        return mBizSystemServApi;
    }

    @Override
    public void born() {
        super.born();
    }


    /**
     * 排序网络请求参数并生成URL
     *
     * @param params 网络请求参数集合
     * @return 参数排序后的URL
     */
    public String sortParams(Map<String, String> params) {
        //获得key并根据key排序
        List<String> keys = new ArrayList<>();
        for (String key : params.keySet()) {
            if (key.length() == 0) {
                continue;
            }
            //不加密下划线开头的参数
            if (key.substring(0, 1).equals("_")) {
                continue;
            }
            keys.add(key);
        }

        //判断集合长度，如果为0，直接返回空
        if (keys.size() == 0) {
            return "";
        }
        //集合升序排序
        Collections.sort(keys);
        //组织参数字符串
        StringBuilder sbParams = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            //参数key与value直接拼接，没有任何符号
            sbParams.append(keys.get(i));
            sbParams.append(params.get(keys.get(i)));
        }
        return sbParams.toString();
    }

    public int getImgFromStr(String imgUrl) {
        int img;
        img = Mgr.getmContext().getResources().getIdentifier(imgUrl, "drawable", Mgr.getmContext().getPackageName());
        return img;
    }
}

package com.iwangzhe.commonview.adv.model;

import android.content.Context;
import android.widget.ImageView;

/**
 * author : 小辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/6/2414:14
 * desc   :
 */
public interface OnSlideShowListener {
    void onItemClick(int position, String imgUrl, String url, String resourEntryName);

    void displayImage(Context context, Object path, ImageView imageView);

    void onItemSelected(int mapId, int position, int total);
}

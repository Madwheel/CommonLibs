package com.iwangzhe.commonview.adv.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iwangzhe.commonlibs.mod.tool.ToolCommonLibsMain;
import com.iwangzhe.commonview.R;
import com.iwangzhe.commonview.adv.model.OnSlideShowListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 小辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/6/249:55
 * desc   : 自定义轮播图
 */
public class SlideShowView extends RelativeLayout {
    private Context mContext;
    private RelativeLayout rl_group;
    private Banner banner;
    private LinearLayout ll_point_group;
    private List<String> mImageUrlList;
    private List<String> mRedirectUrlList;
    private ImageView[] imageViews;

    public SlideShowView(Context context) {
        this(context, null);
    }

    public SlideShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.viewgroup_slideshow, this);
        initView();
    }

    private void initView() {
        rl_group = findViewById(R.id.rl_slideshow);
        banner = findViewById(R.id.banner);
        ll_point_group = findViewById(R.id.ll_point_group);
    }

    public void bindData(List<String> jumpUrlList, List<String> imageUrlList, final List<Integer> imageMapIdList, final OnSlideShowListener listener) {
        mImageUrlList = new ArrayList<>();
        if (imageUrlList.size() > 0) {
            rl_group.setVisibility(View.VISIBLE);
            //添加图片到图片列表里
            mRedirectUrlList = jumpUrlList;
            if (!ToolCommonLibsMain.getInstance().getControl().compareList(mImageUrlList, imageUrlList)) {
                mImageUrlList.clear();
                mImageUrlList = imageUrlList;
                //添加轮播点
                loadPoint(mImageUrlList, ll_point_group);
                //设置图片加载器
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        if (listener != null) {
                            listener.displayImage(context, path, imageView);
                        }
                    }
                });
                banner.setImages(mImageUrlList);
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.Default);
                //设置自动轮播，默认为true
                if (mImageUrlList.size() > 1) {
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                    //设置图片集合
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    banner.isAutoPlay(true);
                    //设置轮播时间
                    banner.setDelayTime(3000);
                    banner.startAutoPlay();
                } else {
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
                    banner.isAutoPlay(false);
                }
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        if (listener != null) {
                            listener.onItemClick(position, mImageUrlList.get(position), mRedirectUrlList.get(position), banner.getResources().getResourceEntryName(banner.getId()));
                        }
                    }
                });
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float v, int i1) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        listener.onItemSelected(imageMapIdList.get(position), position, imageViews.length);
                        // 遍历数组让当前选中图片下的小圆点设置颜色
                        for (int i = 0; i < imageViews.length; i++) {
                            imageViews[position].setBackgroundResource(R.drawable.vp_select);
                            if (position != i) {
                                imageViews[i].setBackgroundResource(R.drawable.vp_select_none);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {
                    }
                });
                banner.start();
            }
        } else {
            rl_group.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化小点，有几张图片下面就显示几个小圆点
     */
    private void loadPoint(List<String> headerImages, LinearLayout ll_point_group) {
        if (headerImages.size() == 1) {
            ll_point_group.setVisibility(View.GONE);
        } else {
            ll_point_group.setVisibility(View.VISIBLE);
        }
        imageViews = new ImageView[headerImages.size()];
        ll_point_group.removeAllViews();
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置每个小圆点距离左边的间距
        margin.setMargins(ToolCommonLibsMain.getInstance().getControl().dip2px(mContext, 4)
                , ToolCommonLibsMain.getInstance().getControl().dip2px(mContext, 3)
                , ToolCommonLibsMain.getInstance().getControl().dip2px(mContext, 4), 0);
        for (int i = 0; i < headerImages.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            // 设置每个小圆点的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(12, 3));
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i].setBackgroundResource(R.drawable.vp_select);
            } else {
                // 其他图片都设置未选中状态
                imageViews[i].setBackgroundResource(R.drawable.vp_select_none);
            }
            ll_point_group.addView(imageViews[i], margin);
            ll_point_group.setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }
}

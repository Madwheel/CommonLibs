package com.iwangzhe.commonview.adv.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.iwangzhe.commonview.R;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/8/610:22
 * desc   :
 */
public class CountDownView extends View {

    private int textSize;
    private int roundColor;
    private float roundWidth;
    private int roundProgressColor;
    private int roundStyle;
    private String defaultText;
    private int textColor;
    //进度条进度
    private float mCurrentProgress = 0.0F;
    //倒计时时间
    private int countDowmTime = 3000;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        defaultText = type.getString(R.styleable.CountDownView_defaultText);
        textColor = type.getColor(R.styleable.CountDownView_textColor, Color.WHITE);
        textSize = type.getDimensionPixelSize(R.styleable.CountDownView_textSize, 15);
        roundColor = type.getColor(R.styleable.CountDownView_roundColor, Color.RED);
        roundWidth = type.getFloat(R.styleable.CountDownView_roundWidth, 6f);
        roundProgressColor = type.getColor(R.styleable.CountDownView_roundProgressColor, Color.WHITE);
        roundStyle = type.getInt(R.styleable.CountDownView_roundStyle, 0);
        type.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 1、画进度条
         */
        Paint mRingPaint = new Paint();
        float center = getWidth() / 2;//圆心x坐标
        float radius = center - 5;//圆环的半径

        mRingPaint.setColor(roundColor);//设置圆环颜色
        mRingPaint.setStyle(Paint.Style.STROKE);//设置圆环空心
        mRingPaint.setStrokeWidth(roundWidth);//设置圆环的宽度
        mRingPaint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(center, center, radius, mRingPaint);

        /**
         * 2、绘制中心圆形
         */
        Paint mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.GRAY);
        mCirclePaint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(center, center, radius, mCirclePaint);
        /**
         * 3、中心文字
         */
        Paint mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);//取消锯齿
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);  //设置字体
        canvas.drawText(defaultText, center, center + textSize / 2, mTextPaint);
        /**
         * 4、画白色圆环
         */
        Paint mProgressPaint = new Paint();
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        mProgressPaint.setColor(roundProgressColor);
        mProgressPaint.setStyle(Paint.Style.STROKE);//设置圆环空心
        mProgressPaint.setStrokeWidth(roundWidth);//设置圆环的宽度
        canvas.drawArc(oval, -90, mCurrentProgress - 360, false, mProgressPaint);
    }

    public void startCountDown() {
        setClickable(true);
        final ValueAnimator mValueAnimator = getValA(countDowmTime);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float process = Float.parseFloat(mValueAnimator.getAnimatedValue().toString());
                mCurrentProgress = 360 * (process / 100f);
                invalidate();
            }
        });
        mValueAnimator.start();
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //倒计时结束回调
                if (mListener != null) {
                    mListener.countDownFinished();
                }
                setClickable(true);
            }
        });
    }

    private ValueAnimator getValA(int interpolator) {
        ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0F, 100F);
        mValueAnimator.setDuration(interpolator);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(0);
        return mValueAnimator;
    }

    private OnCountDownFinishListener mListener;

    public void setOnCountDownFinishListener(OnCountDownFinishListener listener) {
        mListener = listener;
    }

    public interface OnCountDownFinishListener {
        void countDownFinished();
    }

}


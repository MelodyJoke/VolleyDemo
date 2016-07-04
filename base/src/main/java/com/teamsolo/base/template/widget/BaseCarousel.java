package com.teamsolo.base.template.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.teamsolo.base.R;
import com.teamsolo.base.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * description: carousel
 * author: Melody
 * date: 2016/6/26
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class BaseCarousel<T extends Bean> extends RelativeLayout {

    protected Context mContext;

    protected ViewFlipper mViewFlipper;

    protected LinearLayout mIndicator;

    protected List<T> mList = new ArrayList<>();

    protected int interval = 4000;

    protected LayoutInflater layoutInflater;

    protected int current = -1;

    private int emptyIndicator, fullIndicator;

    public BaseCarousel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public BaseCarousel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public BaseCarousel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseCarousel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    private void init() {
        layoutInflater = LayoutInflater.from(mContext);
        inflate(mContext, R.layout.widget_carousel, this);
        initViews();
        bindListeners();
    }

    protected void initViews() {
        mViewFlipper = (ViewFlipper) findViewById(R.id.carousel_viewFlipper);
        mIndicator = (LinearLayout) findViewById(R.id.carousel_indicator);

        mViewFlipper.setFlipInterval(interval);
        mViewFlipper.setInAnimation(mContext, R.anim.carousel_left_in);
        mViewFlipper.setOutAnimation(mContext, R.anim.carousel_left_out);

        notifyDataSetChanged();
        select(0);

        mViewFlipper.setAutoStart(true);
    }

    protected void bindListeners() {
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                select(current + 1 < mList.size() ? current + 1 : 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mViewFlipper.getOutAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public abstract void select(int position);

    public abstract void notifyDataSetChanged();

    public void setFlipInterval(int interval) {
        this.interval = interval;
        mViewFlipper.setFlipInterval(this.interval);
    }

    public void setAutoStart(boolean autoStart) {
        mViewFlipper.setAutoStart(autoStart);
    }

    public void setList(List<T> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
        select(0);
    }

    public void setEmptyIndicator(@DrawableRes int indicator) {
        emptyIndicator = indicator;
    }

    public void setFullIndicator(@DrawableRes int indicator) {
        fullIndicator = indicator;
    }

    public void stopFlipping() {
        mViewFlipper.stopFlipping();
    }

    public void startFlipping() {
        mViewFlipper.startFlipping();
    }

    public int getEmptyIndicator() {
        return emptyIndicator;
    }

    public int getFullIndicator() {
        return fullIndicator;
    }

    public boolean isFlipping() {
        return mViewFlipper.isFlipping();
    }
}

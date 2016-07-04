package com.teamsolo.base.template.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.Window;

import com.teamsolo.base.template.Application;
import com.teamsolo.base.utility.log.LogUtility;
import com.teamsolo.base.utility.strategy.BuildUtility;

import org.jetbrains.annotations.NotNull;

/**
 * description: base activity
 * author: Melody
 * date: 2016/6/19
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtility.i(getClass().getSimpleName(), "onCreate");

        mContext = this;

        // register activity
        Application.getInstance().registerActivity(this);

        // allow transitions
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP))
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LogUtility.i(getClass().getSimpleName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        LogUtility.i(getClass().getSimpleName(), "onResume");
        Application.getInstance().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LogUtility.i(getClass().getSimpleName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        LogUtility.i(getClass().getSimpleName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LogUtility.i(getClass().getSimpleName(), "onDestroy");
        Application.getInstance().unregisterActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        LogUtility.i(getClass().getSimpleName(), "onRestart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        LogUtility.i(getClass().getSimpleName(), "onNewIntent");
    }

    protected abstract void getBundle(@NotNull Intent intent);

    protected abstract void initViews();

    protected abstract void bindListeners();

    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * enable navigation tint mode
     *
     * @param color color res id
     */
    protected void enableNavigationTint(@ColorRes int color) {
        if (BuildUtility.isRequired(Build.VERSION_CODES.M))
            getWindow().setNavigationBarColor(getColor(color));
    }

    /**
     * start activity with transition
     */
    public void startActivity(Intent intent, View view, String transitionName) {
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP))
            startActivity(intent, new Explode(), new Pair<>(view, transitionName));
        else startActivity(intent);
    }

    /**
     * start activity with transition
     *
     * @param pairs view transition name pairs
     */
    @SafeVarargs
    public final void startActivity(Intent intent, Transition transition,
                                    Pair<View, String>... pairs) {
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP)) {
            getWindow().setExitTransition(transition);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, pairs);
            ActivityCompat.startActivity(this, intent, options.toBundle());
        } else super.startActivity(intent);
    }

    /**
     * start activity for result with transition
     */
    public void startActivityForResult(Intent intent, int requestCode,
                                       View view, String transitionName) {
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP))
            startActivityForResult(intent, requestCode, new Explode(),
                    new Pair<>(view, transitionName));
        else startActivityForResult(intent, requestCode);
    }

    /**
     * start activity for result with transition
     *
     * @param pairs view transition name pairs
     */
    @SafeVarargs
    public final void startActivityForResult(Intent intent, int requestCode, Transition transition,
                                             Pair<View, String>... pairs) {
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP)) {
            getWindow().setExitTransition(transition);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, pairs);
            ActivityCompat.startActivityForResult(this, intent, requestCode,
                    options.toBundle());
        } else super.startActivityForResult(intent, requestCode);
    }

    /**
     * override for transition animation
     */
    @Override
    public void onBackPressed() {
        if (BuildUtility.isRequired(Build.VERSION_CODES.LOLLIPOP)) finishAfterTransition();
        else super.onBackPressed();
    }
}

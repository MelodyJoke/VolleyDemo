package com.teamsolo.base.template;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import com.teamsolo.base.utility.log.LogUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * description: the application template
 * author: Melody
 * date: 2016/6/4
 * version: 0.0.0.1
 */
@SuppressWarnings("WeakerAccess, unused")
public abstract class Application extends android.app.Application {

    /**
     * tag
     */
    protected String TAG = "Application";

    /**
     * instance of application
     */
    @SuppressLint("StaticFieldLeak")
    private static Application instance;

    /**
     * uncaught exception handler
     */
    private UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * activity list
     */
    private List<Activity> activities;

    /**
     * activities between the from one and the aim one
     */
    private List<Activity> gapActivities;

    /**
     * the current activity
     */
    private Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        activities = new ArrayList<>();
        gapActivities = new ArrayList<>();

        // set uncaught exception handler
        uncaughtExceptionHandler = initUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    /**
     * init uncaught exception handler
     *
     * @return the uncaught exception handler
     */
    public abstract UncaughtExceptionHandler initUncaughtExceptionHandler();

    /**
     * get instance of application
     *
     * @return the instance
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * get application context
     *
     * @return the current application context
     */
    @Nullable
    public static Context getInstanceContext() {
        if (instance == null) return null;
        return instance.getApplicationContext();
    }

    /**
     * register an activity
     *
     * @param activity the new activity
     */
    public void registerActivity(Activity activity) {
        LogUtility.i(TAG, "registerActivity " + activity.toString());
        if (activities != null && !activities.contains(activity)) activities.add(activity);
    }

    /**
     * unregister an activity
     *
     * @param activity the activity to remove
     */
    public void unregisterActivity(Activity activity) {
        LogUtility.i(TAG, "unregisterActivity " + activity.toString());
        if (activities != null && activities.contains(activity)) activities.remove(activity);
    }

    /**
     * finish all activities
     */
    public void clearActivities() {
        LogUtility.i(TAG, "clearActivities");
        if (activities != null && !activities.isEmpty()) {
            for (Activity activity : activities) activity.finish();

            activities.clear();
        }
    }

    /**
     * finish all gap activities
     */
    public void clearGapActivities() {
        LogUtility.i(TAG, "clearGapActivities");
        if (gapActivities != null && !gapActivities.isEmpty()) {
            for (Activity activity : activities) activity.finish();

            gapActivities.clear();
        }
    }

    /**
     * set current activity
     *
     * @param activity the current activity
     */
    public void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    /**
     * get current activity
     *
     * @return the current activity
     */
    public Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * get uncaught exception handler
     *
     * @return the uncaught exception handler
     */
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }
}

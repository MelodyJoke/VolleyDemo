package com.teamsolo.volleydemo;

import com.teamsolo.base.template.UncaughtExceptionHandler;
import com.teamsolo.http_volley_okhttp_gson.VolleyManager;

/**
 * description: application
 * author: Melody
 * date: 2016/7/4
 * version: 0.0.0.1
 */

public class Application extends com.teamsolo.base.template.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init volley manager
        VolleyManager.init(getApplicationContext());
    }

    @Override
    public UncaughtExceptionHandler initUncaughtExceptionHandler() {
        return new UncaughtExceptionHandler() {
            @Override
            protected void subPerform() {
                // do nothing
            }
        };
    }
}

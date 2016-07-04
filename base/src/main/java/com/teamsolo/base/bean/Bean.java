package com.teamsolo.base.bean;

import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * description: bean super class
 * author: Melody
 * date: 2016/6/17
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class Bean implements Parcelable {

    /**
     * for {@link #toString()} to get string from a bean
     */
    protected static Gson GSON = new GsonBuilder().create();

    public Bean() {
    }

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Bean && toString().equals(o.toString());
    }
}

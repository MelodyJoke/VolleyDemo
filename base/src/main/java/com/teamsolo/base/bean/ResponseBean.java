package com.teamsolo.base.bean;

/**
 * description: response bean super class
 * author: Melody
 * date: 2016/6/17
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public abstract class ResponseBean extends Bean {

    /**
     * response code
     */
    protected int code;

    /**
     * response message, usually is the description or cause of {@link #code}
     */
    protected String message;

    /**
     * to prevent new instance from client
     */
    protected ResponseBean() {
    }

    protected ResponseBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.teamsolo.base.bean.implementation;

import android.os.Parcel;

import com.teamsolo.base.bean.ResponseBean;

import org.jetbrains.annotations.Contract;

/**
 * description: the default implementation of {@link ResponseBean}
 * author: Melody
 * date: 2016/6/17
 * version: 0.0.0.1
 *
 * @see ResponseBean
 */
@SuppressWarnings("unused, WeakerAccess")
public class CommonResponseBean extends ResponseBean {

    public static final Creator<CommonResponseBean> CREATOR = new Creator<CommonResponseBean>() {
        @Contract("_ -> !null")
        @Override
        public CommonResponseBean createFromParcel(Parcel in) {
            return new CommonResponseBean(in);
        }

        @Contract(value = "_ -> !null", pure = true)
        @Override
        public CommonResponseBean[] newArray(int size) {
            return new CommonResponseBean[size];
        }
    };

    /**
     * to prevent new instance from client
     */
    private CommonResponseBean() {
    }

    private CommonResponseBean(int code, String message) {
        super(code, message);
    }

    private CommonResponseBean(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
    }
}

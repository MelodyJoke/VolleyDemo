package com.teamsolo.base.utility.strategy;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * description: strategy util
 * author: Melody
 * date: 2016/6/18
 * version: 0.0.0.1
 */
@SuppressWarnings("unused, WeakerAccess")
public final class StrategyUtility {

    public static final String DEVICE_ID_UUID = "device_id_uuid";

    private StrategyUtility() {
    }

    @NonNull
    public static String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        deviceId.append("android-");

        try {
            TelephonyManager tm =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();

            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei:");
                deviceId.append(imei);
                return deviceId.toString();
            }

            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();

            if (!TextUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi:");
                deviceId.append(wifiMac);
                return deviceId.toString();
            }

            String sn = tm.getSimSerialNumber();

            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn:");
                deviceId.append(sn);
                return deviceId.toString();
            }

            String uuid = getDeviceUUID(context);

            if (!TextUtils.isEmpty(uuid)) {
                deviceId.append("id:");
                deviceId.append(uuid);
                return deviceId.toString();
            }
        } catch (Exception e) {
            deviceId.append("id:").append(getDeviceUUID(context));
        }

        return deviceId.toString();
    }

    private static String getDeviceUUID(Context context) {
        String uuid = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(DEVICE_ID_UUID, "");

        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putString(DEVICE_ID_UUID, uuid)
                    .apply();
        }

        return uuid;
    }
}

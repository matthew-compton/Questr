package com.nobr.android.questr.util;

import android.content.Context;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    private static final String PREF_ID = "userId";

    public static void clear(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .clear()
                .commit();
    }

    public static int getLoginId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_ID, -1);
    }

    public static void setLoginId(Context context, int id) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREF_ID, id)
                .commit();
    }

}
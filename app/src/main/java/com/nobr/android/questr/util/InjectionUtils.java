package com.nobr.android.questr.util;

import android.content.Context;

import com.nobr.android.questr.BaseApplication;

public class InjectionUtils {

    public static void inject(Context context) {
        inject(context, context);
    }

    public static void inject(Context context, Object obj) {
        BaseApplication.get(context).inject(obj);
    }

}
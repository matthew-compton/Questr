package com.nobr.android.questr;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.nobr.android.questr.manager.BroadcastManager;

import javax.inject.Inject;

import dagger.ObjectGraph;
import timber.log.Timber;

public class BaseApplication extends Application {

    @Inject BroadcastManager mBroadcastManager;

    private ObjectGraph mObjectGraph;

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupDagger();
        setupTimber();
        registerConnectionUpdateReceiver();
    }

    private void setupDagger() {
        mObjectGraph = ObjectGraph.create(getModule());
        inject(this);
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void registerConnectionUpdateReceiver() {
        IntentFilter connectionFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mBroadcastManager.sendConnectionUpdate();
            }
        }, connectionFilter);
    }

    public final void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public Object getModule() {
        return new BaseModule(this);
    }

}
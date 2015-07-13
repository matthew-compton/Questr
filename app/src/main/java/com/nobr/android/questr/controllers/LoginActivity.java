package com.nobr.android.questr.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.nobr.android.questr.BaseActivity;

public class LoginActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        return LoginFragment.newInstance();
    }

    @Override
    protected boolean setNavIconAsLogo() {
        return true;
    }

    @Override
    protected boolean setPostponeEnter() {
        return false;
    }

    @Override
    protected boolean setPostponeReenter() {
        return false;
    }

    @Override
    protected boolean showsNetworkConnectionDialog() {
        return true;
    }

    @Override
    protected boolean hasToolbar() {
        return true;
    }

    @Override
    protected boolean hasDrawer() {
        return false;
    }

}
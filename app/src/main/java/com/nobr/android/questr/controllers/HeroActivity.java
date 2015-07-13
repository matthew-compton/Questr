package com.nobr.android.questr.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.nobr.android.questr.BaseActivity;

public class HeroActivity extends BaseActivity {

    public static void start(Context context, int heroId) {
        Intent starter = new Intent(context, HeroActivity.class);
        starter.putExtra(HeroFragment.ARGS_HERO_ID, heroId);
        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        Intent intent = getIntent();
        int heroId = intent.getIntExtra(HeroFragment.ARGS_HERO_ID, -1);
        return HeroFragment.newInstance(heroId);
    }

    @Override
    protected boolean setNavIconAsLogo() {
        return false;
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
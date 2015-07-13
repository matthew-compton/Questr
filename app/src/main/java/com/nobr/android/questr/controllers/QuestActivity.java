package com.nobr.android.questr.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.nobr.android.questr.BaseActivity;

public class QuestActivity extends BaseActivity {

    public static void start(Context context, int questId) {
        Intent starter = new Intent(context, QuestActivity.class);
        starter.putExtra(QuestFragment.ARGS_QUEST_ID, questId);
        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        Intent intent = getIntent();
        int questId = intent.getIntExtra(QuestFragment.ARGS_QUEST_ID, -1);
        return QuestFragment.newInstance(questId);
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
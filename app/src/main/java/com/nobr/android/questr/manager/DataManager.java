package com.nobr.android.questr.manager;

import android.content.Context;
import android.os.Handler;

import com.nobr.android.questr.BaseConstants;
import com.nobr.android.questr.callbacks.AutoLoginCallback;
import com.nobr.android.questr.callbacks.LoginCallback;
import com.nobr.android.questr.callbacks.LogoutCallback;
import com.nobr.android.questr.model.Hero;
import com.nobr.android.questr.model.MockHero;
import com.nobr.android.questr.model.MockQuest;
import com.nobr.android.questr.model.Quest;
import com.nobr.android.questr.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class DataManager {

    private List<Hero> mHeroList;
    private List<Quest> mQuestList;
    private Hero mUser;

    @Inject
    public DataManager() {
        mHeroList = new ArrayList<>();
        mQuestList = new ArrayList<>();
        mUser = null;
    }

    public Quest findQuestById(int questId) {
        for (Quest quest : mQuestList) {
            if (quest.getId() == questId) {
                return quest;
            }
        }
        return null;
    }

    public Hero findHeroById(int heroId) {
        for (Hero hero : mHeroList) {
            if (hero.getId() == heroId) {
                return hero;
            }
        }
        return null;
    }

    public List<Hero> getHeroList() {
        return mHeroList;
    }

    public List<Quest> getQuestList() {
        return mQuestList;
    }

    public Hero getUser() {
        return mUser;
    }

    public void login(final AutoLoginCallback callback, int heroId) {
        Timber.i("Automatically logging in...");
        mUser = findHeroById(heroId);
        if (mUser == null) {
            callback.onAutoLoginFailure();
        } else {
            callback.onAutoLoginSuccess();
        }
    }

    public void login(final LoginCallback callback, Context context) {
        Timber.i("Logging in...");
        new Handler().postDelayed(() -> {
            boolean loginSuccessful = true;
            if (loginSuccessful) {
                mUser = new MockHero();
                mHeroList.add(mUser);
                PreferenceUtils.setLoginId(context, mUser.getId());
                callback.onLoginSuccess();
            } else {
                mUser = null;
                callback.onLoginFailure();
            }
        }, BaseConstants.LOGIN_TIME_MS);
    }

    public void logout(final LogoutCallback callback, Context context) {
        Timber.i("Logging out...");
        mUser = null;
        PreferenceUtils.clear(context);
        callback.onLogout();
    }

    public void removeIssuedQuest(Hero hero, Quest quest) {
        mQuestList.remove(quest);
        hero.removeIssuedQuest(quest);
    }

    public void addIssuedQuest(Hero hero, MockQuest quest) {
        mQuestList.add(quest);
        hero.addIssuedQuest(quest);
    }

}
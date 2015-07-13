package com.nobr.android.questr;

import com.nobr.android.questr.controllers.HeroActivity;
import com.nobr.android.questr.controllers.HeroFragment;
import com.nobr.android.questr.controllers.LoginActivity;
import com.nobr.android.questr.controllers.LoginFragment;
import com.nobr.android.questr.controllers.QuestActivity;
import com.nobr.android.questr.controllers.QuestFragment;
import com.nobr.android.questr.controllers.drawer.DrawerActivity;
import com.nobr.android.questr.controllers.general.ProfileFragment;
import com.nobr.android.questr.controllers.heroes.FindHeroesFragment;
import com.nobr.android.questr.controllers.other.SettingsFragment;
import com.nobr.android.questr.controllers.quests.AcceptedFragment;
import com.nobr.android.questr.controllers.quests.CompletedFragment;
import com.nobr.android.questr.controllers.quests.FindQuestsFragment;
import com.nobr.android.questr.controllers.quests.IssuedFragment;
import com.nobr.android.questr.manager.BroadcastManager;
import com.nobr.android.questr.manager.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                BaseApplication.class,
                BaseActivity.class,
                BaseFragment.class,

                LoginActivity.class,
                LoginFragment.class,

                DrawerActivity.class,

                ProfileFragment.class,

                FindQuestsFragment.class,
                CompletedFragment.class,
                AcceptedFragment.class,
                IssuedFragment.class,

                FindHeroesFragment.class,

                SettingsFragment.class,

                QuestActivity.class,
                QuestFragment.class,

                HeroActivity.class,
                HeroFragment.class

        },
        complete = true)
public class BaseModule {

    private final BaseApplication mApplication;

    public BaseModule(BaseApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return new DataManager();
    }

    @Provides
    @Singleton
    public BroadcastManager provideBroadcastManager() {
        return new BroadcastManager(mApplication);
    }

}
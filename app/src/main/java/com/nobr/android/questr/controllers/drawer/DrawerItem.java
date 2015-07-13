package com.nobr.android.questr.controllers.drawer;

import com.nobr.android.questr.R;

import java.util.ArrayList;
import java.util.List;

public enum DrawerItem {

    GENERAL(R.string.nav_drawer_general, DrawerItemType.HEADER),
    PROFILE(R.string.nav_drawer_profile, DrawerItemType.NAV),
    QUESTS(R.string.nav_drawer_quests, DrawerItemType.HEADER),
    COMPLETED(R.string.nav_drawer_completed, DrawerItemType.NAV),
    ACCEPTED(R.string.nav_drawer_accepted, DrawerItemType.NAV),
    ISSUED(R.string.nav_drawer_issued, DrawerItemType.NAV),
    FIND_QUESTS(R.string.nav_drawer_find_quests, DrawerItemType.NAV),
    HEROES(R.string.nav_drawer_heroes, DrawerItemType.HEADER),
    FIND_HEROES(R.string.nav_drawer_find_heroes, DrawerItemType.NAV),
    OTHER(R.string.nav_drawer_other, DrawerItemType.HEADER),
    SETTINGS(R.string.nav_drawer_settings, DrawerItemType.NAV);

    private final int mTitleResId;
    private final DrawerItemType mDrawerItemType;

    DrawerItem(int titleResId, DrawerItemType drawerItemType) {
        mTitleResId = titleResId;
        mDrawerItemType = drawerItemType;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public DrawerItemType getDrawerItemType() {
        return mDrawerItemType;
    }

    public static List<DrawerItem> getOrderedDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(GENERAL);
        drawerItems.add(PROFILE);
        drawerItems.add(QUESTS);
        drawerItems.add(FIND_QUESTS);
        drawerItems.add(COMPLETED);
        drawerItems.add(ACCEPTED);
        drawerItems.add(ISSUED);
        drawerItems.add(HEROES);
        drawerItems.add(FIND_HEROES);
        drawerItems.add(OTHER);
        drawerItems.add(SETTINGS);
        return drawerItems;
    }

}
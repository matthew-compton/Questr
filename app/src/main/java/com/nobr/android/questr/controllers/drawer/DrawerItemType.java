package com.nobr.android.questr.controllers.drawer;

import com.nobr.android.questr.R;

public enum DrawerItemType {

    HEADER(R.layout.list_item_drawer_header),
    NAV(R.layout.list_item_drawer_navigation);

    private int mLayoutResId;

    DrawerItemType(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
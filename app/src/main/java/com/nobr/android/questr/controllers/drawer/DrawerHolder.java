package com.nobr.android.questr.controllers.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nobr.android.questr.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DrawerHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.list_item_drawer_title) public TextView mTitleTextView;

    private DrawerActivity mActivity;
    private DrawerItem mDrawerItem;
    private boolean mIsSelected;

    public DrawerHolder(DrawerActivity activity, View view) {
        super(view);
        ButterKnife.inject(this, view);
        mActivity = activity;
    }

    public void bindDrawerItem(DrawerItem drawerItem, boolean isActivated) {
        mDrawerItem = drawerItem;
        itemView.setActivated(isActivated);
        mIsSelected = isActivated;
        mTitleTextView.setText(mDrawerItem.getTitleResId());
    }

    @OnClick(R.id.list_item_drawer)
    public void onClickDrawerItem() {
        switch (mDrawerItem.getDrawerItemType()) {
            case NAV:
                if (!mIsSelected) {
                    selectDrawerItem();
                }
                break;
        }
    }

    private void selectDrawerItem() {
        mActivity.setSelection(getAdapterPosition(), true);
        mActivity.closeDrawer();
    }

}
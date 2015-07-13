package com.nobr.android.questr.controllers.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerHolder> {

    public static final int DEFAULT_SELECTION = 1;

    private DrawerActivity mActivity;
    private List<DrawerItem> mDrawerItems;
    private int mSelection;

    public DrawerAdapter(DrawerActivity activity, List<DrawerItem> drawerItems) {
        mActivity = activity;
        mDrawerItems = drawerItems;
        mSelection = DEFAULT_SELECTION;
    }

    @Override
    public DrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);

        DrawerItemType drawerItemType = DrawerItemType.values()[viewType];
        View view = inflater.inflate(drawerItemType.getLayoutResId(), parent, false);

        return new DrawerHolder(mActivity, view);
    }

    @Override
    public void onBindViewHolder(DrawerHolder holder, int position) {
        DrawerItem drawerItem = mDrawerItems.get(position);
        boolean isActivated = position == mSelection;
        holder.bindDrawerItem(drawerItem, isActivated);
    }

    @Override
    public int getItemCount() {
        return mDrawerItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        DrawerItem drawerItem = mDrawerItems.get(position);
        return drawerItem.getDrawerItemType().ordinal();
    }

    public int getSelection() {
        return mSelection;
    }

    public void setSelection(int newPosition) {
        int oldPosition = mSelection;
        mSelection = newPosition;
        notifyItemChanged(oldPosition);
        notifyItemChanged(newPosition);
    }

    public List<DrawerItem> getDrawerItems() {
        return mDrawerItems;
    }

}
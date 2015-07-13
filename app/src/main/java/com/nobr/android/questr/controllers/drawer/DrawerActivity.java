package com.nobr.android.questr.controllers.drawer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.nobr.android.questr.BaseActivity;
import com.nobr.android.questr.R;
import com.nobr.android.questr.controllers.heroes.FindHeroesFragment;
import com.nobr.android.questr.controllers.general.ProfileFragment;
import com.nobr.android.questr.controllers.quests.AcceptedFragment;
import com.nobr.android.questr.controllers.quests.CompletedFragment;
import com.nobr.android.questr.controllers.quests.FindQuestsFragment;
import com.nobr.android.questr.controllers.quests.IssuedFragment;
import com.nobr.android.questr.controllers.other.SettingsFragment;
import com.nobr.android.questr.manager.DataManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;

public class DrawerActivity extends BaseActivity
        implements ProfileFragment.Callbacks {

    private static final String EXTRA_SELECTION = "DrawerActivity.Selection";

    @InjectView(R.id.activity_drawer_drawerLayout) DrawerLayout mDrawerLayout;
    @InjectView(R.id.activity_drawer_recyclerView) RecyclerView mRecyclerView;

    @Inject DataManager mDataManager;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerAdapter mDrawerAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, DrawerActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected Fragment createFragment() {
        return null;
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
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigationDrawer();

        if (savedInstanceState != null) {
            int selection = savedInstanceState.getInt(EXTRA_SELECTION, DrawerAdapter.DEFAULT_SELECTION);
            setSelection(selection, true);
        } else {
            setSelection(DrawerAdapter.DEFAULT_SELECTION, true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_SELECTION, mDrawerAdapter.getSelection());
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        mDrawerToggle.onConfigurationChanged(configuration);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mRecyclerView)) {
            mDrawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    private void setupNavigationDrawer() {
        List<DrawerItem> drawerItems = DrawerItem.getOrderedDrawerItems();
        mDrawerAdapter = new DrawerAdapter(this, drawerItems);
        mRecyclerView.setAdapter(mDrawerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawer_content_description, R.string.close_drawer_content_description) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Prevents toggle animation
                onDrawerSlide(drawerView, 0);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

        };
    }

    public void setSelection(DrawerItem drawerItem) {
        List<DrawerItem> drawerItems = mDrawerAdapter.getDrawerItems();
        mDrawerAdapter.setSelection(drawerItems.indexOf(drawerItem));
        setToolbarTitle(drawerItem);
        setSelectedFragment(drawerItem);
    }

    public void setSelection(int position, boolean changeFragments) {
        mDrawerAdapter.setSelection(position);

        List<DrawerItem> drawerItems = mDrawerAdapter.getDrawerItems();
        DrawerItem drawerItem = drawerItems.get(position);
        if (drawerItem.getDrawerItemType() == DrawerItemType.NAV) {
            setToolbarTitle(drawerItem);
        }
        if (changeFragments) {
            setSelectedFragment(drawerItem);
        }
    }

    private void setToolbarTitle(DrawerItem drawerItem) {
        String toolbarTitle = getString(drawerItem.getTitleResId());
        setTitle(toolbarTitle);
    }

    private void setSelectedFragment(DrawerItem drawerItem) {
        Fragment fragment = null;
        switch (drawerItem) {
            case PROFILE:
                fragment = ProfileFragment.newInstance();
                break;
            case COMPLETED:
                fragment = CompletedFragment.newInstance();
                break;
            case ACCEPTED:
                fragment = AcceptedFragment.newInstance();
                break;
            case ISSUED:
                fragment = IssuedFragment.newInstance();
                break;
            case FIND_QUESTS:
                fragment = FindQuestsFragment.newInstance();
                break;
            case FIND_HEROES:
                fragment = FindHeroesFragment.newInstance();
                break;
            case SETTINGS:
                fragment = SettingsFragment.newInstance();
                break;
            default:
                Timber.e("Error with selected drawer item.");
                break;
        }
        updateFragment(fragment);
    }

    private void updateFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment oldFrag = fm.findFragmentById(R.id.fragment_container);
            FragmentTransaction ft = fm.beginTransaction();
            if (oldFrag != null) {
                ft.remove(oldFrag);
            }
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mRecyclerView);
    }

    @Override
    public void onClickCompleted() {
        setSelection(DrawerItem.COMPLETED);
    }

    @Override
    public void onClickAccepted() {
        setSelection(DrawerItem.ACCEPTED);
    }

    @Override
    public void onClickIssued() {
        setSelection(DrawerItem.ISSUED);
    }

}
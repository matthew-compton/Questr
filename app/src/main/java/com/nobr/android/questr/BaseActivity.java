package com.nobr.android.questr;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;

import com.nobr.android.questr.manager.BroadcastManager;
import com.nobr.android.questr.util.AndroidUtils;
import com.nobr.android.questr.util.ConnectionUtils;
import com.nobr.android.questr.util.InjectionUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.InjectView;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    @InjectView(R.id.view_toolbar) public Toolbar mToolbar;

    private BroadcastReceiver mConnectionUpdateReceiver;

    protected abstract Fragment createFragment();

    protected abstract boolean setNavIconAsLogo();

    protected abstract boolean setPostponeEnter();

    protected abstract boolean setPostponeReenter();

    protected abstract boolean showsNetworkConnectionDialog();

    protected abstract boolean hasToolbar();

    protected abstract boolean hasDrawer();

    protected int getContentViewId() {
        return hasDrawer() ? R.layout.activity_drawer : R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setupInjection();
        setupToolbar();
        setupOverviewScreen();
        setupEnterTransition();
        setupBroadcastReceivers();
        setupInitialFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceivers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBroadcastReceivers();
    }

    private void setupInjection() {
        InjectionUtils.inject(this);
        ButterKnife.inject(this);
    }

    private void setupToolbar() {
        if (hasToolbar()) {
            mToolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (setNavIconAsLogo()) {
                mToolbar.setNavigationIcon(R.drawable.ic_logo);
            }
            setupOverflowButton();
        }
    }

    private void setupOverflowButton() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (NoSuchFieldException e) {
            Timber.e("Error with displaying overflow menu.", e);
        } catch (IllegalAccessException e) {
            Timber.e("Error with displaying overflow menu.", e);
        }
    }

    private void setupOverviewScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int color = typedValue.data;

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_overview);
            ActivityManager.TaskDescription td = new ActivityManager.TaskDescription(null, bm, color);

            setTaskDescription(td);
            bm.recycle();
        }
    }

    private void setupInitialFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            if (fragment == null) {
                return;
            }
            manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    private void setupBroadcastReceivers() {
        mConnectionUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectionUtils.checkForConnection(BaseActivity.this);
            }
        };
    }

    private void registerBroadcastReceivers() {
        if (showsNetworkConnectionDialog()) {
            registerConnectionUpdateReceiver();
            ConnectionUtils.checkForConnection(this);
        }
    }

    private void unregisterBroadcastReceivers() {
        if (showsNetworkConnectionDialog()) {
            unregisterConnectionUpdateReceiver();
        }
    }

    private void registerConnectionUpdateReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter connectionFilter = new IntentFilter(BroadcastManager.BROADCAST_ACTION_CONNECTION_UPDATE);
        broadcastManager.registerReceiver(mConnectionUpdateReceiver, connectionFilter);
    }

    private void unregisterConnectionUpdateReceiver() {
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(mConnectionUpdateReceiver);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterTransition() {
        if (setPostponeEnter() && AndroidUtils.IS_LOLLIPOP_AND_UP) {
            postponeEnterTransition();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (setPostponeReenter() && AndroidUtils.IS_LOLLIPOP_AND_UP) {
            postponeEnterTransition();
        }
    }

    public void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (AndroidUtils.IS_LOLLIPOP_AND_UP) {
                            startPostponedEnterTransition();
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
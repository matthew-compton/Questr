package com.nobr.android.questr.controllers.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.BuildConfig;
import com.nobr.android.questr.R;
import com.nobr.android.questr.callbacks.LogoutCallback;
import com.nobr.android.questr.controllers.LoginActivity;
import com.nobr.android.questr.manager.DataManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment implements LogoutCallback {

    private static final int REQUEST_DIALOG_LOGOUT = 0;

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_settings_version_text) TextView mVersionTextView;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, parent, false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_settings_logout:
                displayLogoutDialogFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DIALOG_LOGOUT) {
            mDataManager.logout(this, getActivity());
        }
    }

    private void updateUI() {
        mVersionTextView.setText(BuildConfig.VERSION_NAME);
    }

    private void displayLicensesDialogFragment() {
        LicensesDialogFragment dialog = LicensesDialogFragment.newInstance();
        dialog.show(getFragmentManager(), LicensesDialogFragment.class.getSimpleName());
    }

    private void displayAboutDialogFragment() {
        AboutDialogFragment dialog = AboutDialogFragment.newInstance();
        dialog.show(getFragmentManager(), AboutDialogFragment.class.getSimpleName());
    }

    private void displayLogoutDialogFragment() {
        LogoutDialogFragment dialog = LogoutDialogFragment.newInstance();
        dialog.setTargetFragment(this, REQUEST_DIALOG_LOGOUT);
        dialog.show(getFragmentManager(), LogoutDialogFragment.class.getSimpleName());
    }

    @OnClick(R.id.fragment_settings_licenses)
    public void onClickLicenses() {
        displayLicensesDialogFragment();
    }

    @OnClick(R.id.fragment_settings_about)
    public void onClickAbout() {
        displayAboutDialogFragment();
    }

    @Override
    public void onLogout() {
        Toast.makeText(getActivity(), getString(R.string.dialog_logout_toast), Toast.LENGTH_SHORT).show();
        LoginActivity.start(getActivity());
        getActivity().finish();
    }

}
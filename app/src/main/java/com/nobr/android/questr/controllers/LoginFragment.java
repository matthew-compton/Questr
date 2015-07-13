package com.nobr.android.questr.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.R;
import com.nobr.android.questr.callbacks.AutoLoginCallback;
import com.nobr.android.questr.callbacks.LoginCallback;
import com.nobr.android.questr.controllers.drawer.DrawerActivity;
import com.nobr.android.questr.manager.DataManager;
import com.nobr.android.questr.util.PreferenceUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginCallback, AutoLoginCallback {

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_login_loginButton) Button mLoginButton;
    @InjectView(R.id.fragment_login_progressBar) ProgressBar mProgressBar;

    private boolean hasClickedLogin;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hasClickedLogin = false;
        checkForExistingLogin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, parent, false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    private void checkForExistingLogin() {
        int heroId = PreferenceUtils.getLoginId(getActivity());
        if (heroId != -1) {
            mDataManager.login(this, heroId);
        }
    }

    private void updateUI() {
        if (hasClickedLogin) {
            mProgressBar.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mLoginButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.fragment_login_loginButton)
    public void onClickLoginButton() {
        hasClickedLogin = true;
        updateUI();
        mDataManager.login(this, getActivity());
    }

    @Override
    public void onLoginSuccess() {
        DrawerActivity.start(getActivity());
        getActivity().finish();
    }

    @Override
    public void onLoginFailure() {
        hasClickedLogin = false;
        updateUI();
        Toast.makeText(getActivity(), R.string.login_failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAutoLoginSuccess() {
        DrawerActivity.start(getActivity());
        getActivity().finish();
    }

    @Override
    public void onAutoLoginFailure() {
        // Nothing
    }

}
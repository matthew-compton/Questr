package com.nobr.android.questr.controllers.general;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.R;
import com.nobr.android.questr.manager.DataManager;
import com.nobr.android.questr.model.Hero;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment {

    @InjectView(R.id.fragment_profile_nameTextView) TextView mNameTextView;
    @InjectView(R.id.fragment_profile_experienceTextView) TextView mExperienceTextView;
    @InjectView(R.id.fragment_profile_questsCompletedTextView) TextView mQuestsCompletedTextView;
    @InjectView(R.id.fragment_profile_questsAcceptedTextView) TextView mQuestsAcceptedTextView;
    @InjectView(R.id.fragment_profile_questsIssuedTextView) TextView mQuestsIssuedTextView;

    @Inject DataManager mDataManager;

    private Hero mUser;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onClickCompleted();
        void onClickAccepted();
        void onClickIssued();
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = mDataManager.getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, parent, false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void updateUI() {
        mNameTextView.setText(mUser.getName());
        mExperienceTextView.setText(String.valueOf(mUser.getExperience()));
        mQuestsCompletedTextView.setText(String.valueOf(mUser.getCompletedQuests().size()));
        mQuestsAcceptedTextView.setText(String.valueOf(mUser.getAcceptedQuests().size()));
        mQuestsIssuedTextView.setText(String.valueOf(mUser.getIssuedQuests().size()));
    }

    @OnClick(R.id.fragment_profile_questsCompletedLayout)
    public void onClickCompleted() {
        mCallbacks.onClickCompleted();
    }

    @OnClick(R.id.fragment_profile_questsAcceptedLayout)
    public void onClickAccepted() {
        mCallbacks.onClickAccepted();
    }

    @OnClick(R.id.fragment_profile_questsIssuedLayout)
    public void onClickIssued() {
        mCallbacks.onClickIssued();
    }

}
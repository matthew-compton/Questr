package com.nobr.android.questr.controllers;

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

public class HeroFragment extends BaseFragment {

    public static final String ARGS_HERO_ID = "ARGS_HERO_ID";

    @InjectView(R.id.fragment_hero_nameTextView) TextView mNameTextView;
    @InjectView(R.id.fragment_hero_experienceTextView) TextView mExperienceTextView;
    @InjectView(R.id.fragment_hero_questsCompletedTextView) TextView mQuestsCompletedTextView;
    @InjectView(R.id.fragment_hero_questsAcceptedTextView) TextView mQuestsAcceptedTextView;
    @InjectView(R.id.fragment_hero_questsIssuedTextView) TextView mQuestsIssuedTextView;

    @Inject DataManager mDataManager;

    private Hero mHero;

    public static HeroFragment newInstance(int heroId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_HERO_ID, heroId);

        HeroFragment fragment = new HeroFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int heroId = getArguments().getInt(ARGS_HERO_ID, -1);
        mHero = mDataManager.findHeroById(heroId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, parent, false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    private void updateUI() {
        mNameTextView.setText(mHero.getName());
        mExperienceTextView.setText(String.valueOf(mHero.getExperience()));
        mQuestsCompletedTextView.setText(String.valueOf(mHero.getCompletedQuests().size()));
        mQuestsAcceptedTextView.setText(String.valueOf(mHero.getAcceptedQuests().size()));
        mQuestsIssuedTextView.setText(String.valueOf(mHero.getIssuedQuests().size()));
    }

}
package com.nobr.android.questr.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.R;
import com.nobr.android.questr.manager.DataManager;
import com.nobr.android.questr.model.Quest;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class QuestFragment extends BaseFragment {

    public static final String ARGS_QUEST_ID = "ARGS_QUEST_ID";

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_quest_nameTextView) TextView mNameTextView;
    @InjectView(R.id.fragment_quest_descriptionTextView) TextView mDescriptionTextView;
    @InjectView(R.id.fragment_quest_requirementTextView) TextView mRequirementTextView;
    @InjectView(R.id.fragment_quest_experienceRewardTextView) TextView mExperienceRewardTextView;
    @InjectView(R.id.fragment_quest_isActiveCheckBox) CheckBox mIsActiveCheckBox;
    @InjectView(R.id.fragment_quest_questGiverTextView) TextView mQuestGiverTextView;

    private Quest mQuest;

    public static QuestFragment newInstance(int questId) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_QUEST_ID, questId);

        QuestFragment fragment = new QuestFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int questId = getArguments().getInt(ARGS_QUEST_ID, -1);
        mQuest = mDataManager.findQuestById(questId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest, parent, false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    private void updateUI() {
        mNameTextView.setText(mQuest.getName());
        mDescriptionTextView.setText(mQuest.getDescription());
        mRequirementTextView.setText(mQuest.getRequirement());
        mExperienceRewardTextView.setText(String.valueOf(mQuest.getExperienceReward()));
        mIsActiveCheckBox.setChecked(mQuest.isActive());
        mQuestGiverTextView.setText(mQuest.getQuestGiver().getName());
    }

    @OnClick(R.id.fragment_quest_questGiverLayout)
    public void onClickQuestGiverLayout() {
        HeroActivity.start(getActivity(), mQuest.getQuestGiver().getId());
    }

}
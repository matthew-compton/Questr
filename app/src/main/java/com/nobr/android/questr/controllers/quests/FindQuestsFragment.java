package com.nobr.android.questr.controllers.quests;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.R;
import com.nobr.android.questr.controllers.QuestActivity;
import com.nobr.android.questr.manager.DataManager;
import com.nobr.android.questr.model.Quest;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FindQuestsFragment extends BaseFragment {

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_find_quests_emptyTextView) TextView mEmptyTextView;
    @InjectView(R.id.fragment_find_quests_recycler_view) RecyclerView mRecyclerView;

    private QuestAdapter mAdapter;

    public static FindQuestsFragment newInstance() {
        return new FindQuestsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_quests, container, false);
        ButterKnife.inject(this, view);
        setupRecyclerView();
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI() {
        List<Quest> questList = mDataManager.getQuestList();
        if (questList == null || questList.isEmpty()) {
            mEmptyTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mEmptyTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if (mAdapter == null) {
                mAdapter = new QuestAdapter(questList);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setQuestList(questList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public class QuestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.list_item_find_quest_name) TextView mNameTextView;

        private Quest mQuest;

        public QuestHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCrime(Quest quest) {
            mQuest = quest;
            mNameTextView.setText(mQuest.getName());
        }

        @Override
        public void onClick(View v) {
            QuestActivity.start(getActivity(), mQuest.getId());
        }
    }

    private class QuestAdapter extends RecyclerView.Adapter<QuestHolder> {

        private List<Quest> mQuestList;

        public QuestAdapter(List<Quest> questList) {
            mQuestList = questList;
        }

        @Override
        public QuestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_find_quest, parent, false);
            return new QuestHolder(view);
        }

        @Override
        public void onBindViewHolder(QuestHolder holder, int position) {
            Quest quest = mQuestList.get(position);
            holder.bindCrime(quest);
        }

        @Override
        public int getItemCount() {
            return mQuestList.size();
        }

        public void setQuestList(List<Quest> questList) {
            mQuestList = questList;
        }
    }

}
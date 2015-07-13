package com.nobr.android.questr.controllers.quests;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class AcceptedFragment extends BaseFragment {

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_accepted_emptyTextView) TextView mEmptyTextView;
    @InjectView(R.id.fragment_accepted_recycler_view) RecyclerView mRecyclerView;

    private QuestAdapter mAdapter;

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accepted, container, false);
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
        List<Quest> questList = mDataManager.getUser().getAcceptedQuests();
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

        @InjectView(R.id.list_item_issued_name) TextView mNameTextView;
        @InjectView(R.id.list_item_issued_delete) ImageView mDeleteImageView;

        private Quest mQuest;

        public QuestHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
            mDeleteImageView.setOnClickListener(v -> mDataManager.getUser().removeAcceptedQuest(mQuest));
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

        public QuestAdapter(List<Quest> heroList) {
            mQuestList = heroList;
        }

        @Override
        public QuestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_accepted, parent, false);
            return new QuestHolder(view);
        }

        @Override
        public void onBindViewHolder(QuestHolder holder, int position) {
            Quest hero = mQuestList.get(position);
            holder.bindCrime(hero);
        }

        @Override
        public int getItemCount() {
            return mQuestList.size();
        }

        public void setQuestList(List<Quest> heroList) {
            mQuestList = heroList;
        }
    }

}
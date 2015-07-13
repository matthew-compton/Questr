package com.nobr.android.questr.controllers.heroes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nobr.android.questr.BaseFragment;
import com.nobr.android.questr.R;
import com.nobr.android.questr.controllers.HeroActivity;
import com.nobr.android.questr.manager.DataManager;
import com.nobr.android.questr.model.Hero;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FindHeroesFragment extends BaseFragment {

    @Inject DataManager mDataManager;

    @InjectView(R.id.fragment_find_heroes_emptyTextView) TextView mEmptyTextView;
    @InjectView(R.id.fragment_find_heroes_recycler_view) RecyclerView mRecyclerView;
    private HeroAdapter mAdapter;

    public static FindHeroesFragment newInstance() {
        return new FindHeroesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_heroes, container, false);
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
        List<Hero> heroList = mDataManager.getHeroList();
        if (heroList == null || heroList.isEmpty()) {
            mEmptyTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mEmptyTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if (mAdapter == null) {
                mAdapter = new HeroAdapter(heroList);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.setHeroList(heroList);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private class HeroHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNameTextView;

        private Hero mHero;

        public HeroHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_find_hero_name);
        }

        public void bindCrime(Hero hero) {
            mHero = hero;
            mNameTextView.setText(mHero.getName());
        }

        @Override
        public void onClick(View v) {
            HeroActivity.start(getActivity(), mHero.getId());
        }
    }

    private class HeroAdapter extends RecyclerView.Adapter<HeroHolder> {

        private List<Hero> mHeroList;

        public HeroAdapter(List<Hero> heroList) {
            mHeroList = heroList;
        }

        @Override
        public HeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_find_hero, parent, false);
            return new HeroHolder(view);
        }

        @Override
        public void onBindViewHolder(HeroHolder holder, int position) {
            Hero hero = mHeroList.get(position);
            holder.bindCrime(hero);
        }

        @Override
        public int getItemCount() {
            return mHeroList.size();
        }

        public void setHeroList(List<Hero> heroList) {
            mHeroList = heroList;
        }
    }

}
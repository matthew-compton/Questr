package com.nobr.android.questr;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nobr.android.questr.util.InjectionUtils;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        setupInjection();
    }

    private void setupInjection() {
        InjectionUtils.inject(getActivity(), this);
    }

}
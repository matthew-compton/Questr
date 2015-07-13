package com.nobr.android.questr.controllers.other;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.nobr.android.questr.R;

public class ConnectionDialogFragment extends DialogFragment {

    public static ConnectionDialogFragment newInstance() {
        return new ConnectionDialogFragment();
    }

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_connection, null);
        return new AlertDialog.Builder(getActivity(), R.style.BaseDialog)
                .setView(view)
                .setCancelable(false)
                .create();
    }

}
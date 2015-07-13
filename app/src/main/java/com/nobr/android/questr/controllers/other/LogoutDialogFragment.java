package com.nobr.android.questr.controllers.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.nobr.android.questr.R;

public class LogoutDialogFragment extends DialogFragment {

    public static LogoutDialogFragment newInstance() {
        return new LogoutDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity(), R.style.BaseDialog)
                .setTitle(R.string.dialog_logout_title)
                .setMessage(R.string.dialog_logout_message)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    sendResult();
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                    dismiss();
                })
                .create();
    }

    private void sendResult() {
        if (getTargetFragment() == null) {
            return;
        }
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
    }

}
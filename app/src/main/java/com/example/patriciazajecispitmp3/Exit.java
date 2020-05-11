package com.example.patriciazajecispitmp3;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Exit extends DialogFragment { @NonNull
@Override
public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(getString(R.string.d_exit));
    builder.setMessage(getString(R.string.d_confirmation));
    builder.setIconAttribute(android.R.attr.alertDialogIcon);
    builder.setPositiveButton(android.R.string.yes, (dialog, which) -> getActivity().finish());
    builder.setNegativeButton(android.R.string.no, (dialog, which) -> {

    });

    return builder.create();
}

    @Override
    public void onStart() {
        super.onStart();
        setCancelable(false);
    }
}

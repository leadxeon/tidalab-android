package com.facebook.react.modules.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.modules.dialog.DialogModule;
/* loaded from: classes.dex */
public class AlertFragment extends DialogFragment implements DialogInterface.OnClickListener {
    public final DialogModule.AlertFragmentListener mListener;

    public AlertFragment() {
        this.mListener = null;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onClick(dialogInterface, i);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Bundle bundle2 = this.mArguments;
        AlertDialog.Builder title = new AlertDialog.Builder(activity).setTitle(bundle2.getString(DialogModule.KEY_TITLE));
        if (bundle2.containsKey("button_positive")) {
            title.setPositiveButton(bundle2.getString("button_positive"), this);
        }
        if (bundle2.containsKey("button_negative")) {
            title.setNegativeButton(bundle2.getString("button_negative"), this);
        }
        if (bundle2.containsKey("button_neutral")) {
            title.setNeutralButton(bundle2.getString("button_neutral"), this);
        }
        if (bundle2.containsKey(DialogModule.KEY_MESSAGE)) {
            title.setMessage(bundle2.getString(DialogModule.KEY_MESSAGE));
        }
        if (bundle2.containsKey(DialogModule.KEY_ITEMS)) {
            title.setItems(bundle2.getCharSequenceArray(DialogModule.KEY_ITEMS), this);
        }
        return title.create();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onDismiss(dialogInterface);
        }
    }

    @SuppressLint({"ValidFragment"})
    public AlertFragment(DialogModule.AlertFragmentListener alertFragmentListener, Bundle bundle) {
        this.mListener = alertFragmentListener;
        setArguments(bundle);
    }
}

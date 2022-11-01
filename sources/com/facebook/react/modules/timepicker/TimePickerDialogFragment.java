package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes.dex */
public class TimePickerDialogFragment extends DialogFragment {
    public DialogInterface.OnDismissListener mOnDismissListener;
    public TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        boolean z;
        Bundle bundle2 = this.mArguments;
        FragmentActivity activity = getActivity();
        TimePickerDialog.OnTimeSetListener onTimeSetListener = this.mOnTimeSetListener;
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        boolean is24HourFormat = DateFormat.is24HourFormat(activity);
        int valueOfcom$facebook$react$modules$timepicker$TimePickerMode = (bundle2 == null || bundle2.getString("mode", null) == null) ? 3 : SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$facebook$react$modules$timepicker$TimePickerMode(bundle2.getString("mode").toUpperCase(Locale.US));
        if (bundle2 != null) {
            i = bundle2.getInt(TimePickerDialogModule.ARG_HOUR, instance.get(11));
            i2 = bundle2.getInt(TimePickerDialogModule.ARG_MINUTE, instance.get(12));
            z = bundle2.getBoolean(TimePickerDialogModule.ARG_IS24HOUR, DateFormat.is24HourFormat(activity));
        } else {
            z = is24HourFormat;
        }
        if (valueOfcom$facebook$react$modules$timepicker$TimePickerMode == 1) {
            return new DismissableTimePickerDialog(activity, activity.getResources().getIdentifier("ClockTimePickerDialog", "style", activity.getPackageName()), onTimeSetListener, i, i2, z);
        }
        if (valueOfcom$facebook$react$modules$timepicker$TimePickerMode == 2) {
            return new DismissableTimePickerDialog(activity, activity.getResources().getIdentifier("SpinnerTimePickerDialog", "style", activity.getPackageName()), onTimeSetListener, i, i2, z);
        }
        return new DismissableTimePickerDialog(activity, onTimeSetListener, i, i2, z);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }
}

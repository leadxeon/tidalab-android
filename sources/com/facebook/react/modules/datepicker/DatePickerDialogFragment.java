package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import java.util.Calendar;
import java.util.Locale;
@SuppressLint({"ValidFragment"})
/* loaded from: classes.dex */
public class DatePickerDialogFragment extends DialogFragment {
    public DatePickerDialog.OnDateSetListener mOnDateSetListener;
    public DialogInterface.OnDismissListener mOnDismissListener;

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DatePicker datePicker;
        Bundle bundle2 = this.mArguments;
        FragmentActivity activity = getActivity();
        DatePickerDialog.OnDateSetListener onDateSetListener = this.mOnDateSetListener;
        Calendar instance = Calendar.getInstance();
        if (bundle2 != null && bundle2.containsKey(DatePickerDialogModule.ARG_DATE)) {
            instance.setTimeInMillis(bundle2.getLong(DatePickerDialogModule.ARG_DATE));
        }
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        int i4 = 3;
        DismissableDatePickerDialog dismissableDatePickerDialog = null;
        if (!(bundle2 == null || bundle2.getString("mode", null) == null)) {
            i4 = SolverVariable$Type$r8$EnumUnboxingUtility.valueOfcom$facebook$react$modules$datepicker$DatePickerMode(bundle2.getString("mode").toUpperCase(Locale.US));
        }
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(i4);
        if ($enumboxing$ordinal == 0) {
            dismissableDatePickerDialog = new DismissableDatePickerDialog(activity, activity.getResources().getIdentifier("CalendarDatePickerDialog", "style", activity.getPackageName()), onDateSetListener, i, i2, i3);
        } else if ($enumboxing$ordinal != 1) {
            if ($enumboxing$ordinal == 2) {
                dismissableDatePickerDialog = new DismissableDatePickerDialog(activity, onDateSetListener, i, i2, i3);
            }
            datePicker = dismissableDatePickerDialog.getDatePicker();
            if (bundle2 != null || !bundle2.containsKey(DatePickerDialogModule.ARG_MINDATE)) {
                datePicker.setMinDate(-2208988800001L);
            } else {
                instance.setTimeInMillis(bundle2.getLong(DatePickerDialogModule.ARG_MINDATE));
                instance.set(11, 0);
                instance.set(12, 0);
                instance.set(13, 0);
                instance.set(14, 0);
                datePicker.setMinDate(instance.getTimeInMillis());
            }
            if (bundle2 != null && bundle2.containsKey(DatePickerDialogModule.ARG_MAXDATE)) {
                instance.setTimeInMillis(bundle2.getLong(DatePickerDialogModule.ARG_MAXDATE));
                instance.set(11, 23);
                instance.set(12, 59);
                instance.set(13, 59);
                instance.set(14, 999);
                datePicker.setMaxDate(instance.getTimeInMillis());
            }
            return dismissableDatePickerDialog;
        } else {
            dismissableDatePickerDialog = new DismissableDatePickerDialog(activity, 16973939, onDateSetListener, i, i2, i3);
            dismissableDatePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        datePicker = dismissableDatePickerDialog.getDatePicker();
        if (bundle2 != null) {
        }
        datePicker.setMinDate(-2208988800001L);
        if (bundle2 != null) {
            instance.setTimeInMillis(bundle2.getLong(DatePickerDialogModule.ARG_MAXDATE));
            instance.set(11, 23);
            instance.set(12, 59);
            instance.set(13, 59);
            instance.set(14, 999);
            datePicker.setMaxDate(instance.getTimeInMillis());
        }
        return dismissableDatePickerDialog;
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

package com.facebook.react.modules.timepicker;

import android.app.TimePickerDialog;
import android.content.Context;
/* loaded from: classes.dex */
public class DismissableTimePickerDialog extends TimePickerDialog {
    public DismissableTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i, int i2, boolean z) {
        super(context, onTimeSetListener, i, i2, z);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
    }

    public DismissableTimePickerDialog(Context context, int i, TimePickerDialog.OnTimeSetListener onTimeSetListener, int i2, int i3, boolean z) {
        super(context, i, onTimeSetListener, i2, i3, z);
    }
}

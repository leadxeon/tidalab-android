package com.facebook.react.modules.timepicker;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = TimePickerDialogModule.FRAGMENT_TAG)
/* loaded from: classes.dex */
public class TimePickerDialogModule extends ReactContextBaseJavaModule {
    public static final String ACTION_DISMISSED = "dismissedAction";
    public static final String ACTION_TIME_SET = "timeSetAction";
    public static final String ARG_HOUR = "hour";
    public static final String ARG_IS24HOUR = "is24Hour";
    public static final String ARG_MINUTE = "minute";
    public static final String ARG_MODE = "mode";
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    public static final String FRAGMENT_TAG = "TimePickerAndroid";

    /* loaded from: classes.dex */
    public class TimePickerDialogListener implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnDismissListener {
        public final Promise mPromise;
        public boolean mPromiseResolved = false;

        public TimePickerDialogListener(Promise promise) {
            this.mPromise = promise;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.mPromiseResolved && TimePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", "dismissedAction");
                this.mPromise.resolve(writableNativeMap);
                this.mPromiseResolved = true;
            }
        }

        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public void onTimeSet(TimePicker timePicker, int i, int i2) {
            if (!this.mPromiseResolved && TimePickerDialogModule.this.getReactApplicationContext().hasActiveCatalystInstance()) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("action", TimePickerDialogModule.ACTION_TIME_SET);
                writableNativeMap.putInt(TimePickerDialogModule.ARG_HOUR, i);
                writableNativeMap.putInt(TimePickerDialogModule.ARG_MINUTE, i2);
                this.mPromise.resolve(writableNativeMap);
                this.mPromiseResolved = true;
            }
        }
    }

    public TimePickerDialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundle = new Bundle();
        if (readableMap.hasKey(ARG_HOUR) && !readableMap.isNull(ARG_HOUR)) {
            bundle.putInt(ARG_HOUR, readableMap.getInt(ARG_HOUR));
        }
        if (readableMap.hasKey(ARG_MINUTE) && !readableMap.isNull(ARG_MINUTE)) {
            bundle.putInt(ARG_MINUTE, readableMap.getInt(ARG_MINUTE));
        }
        if (readableMap.hasKey(ARG_IS24HOUR) && !readableMap.isNull(ARG_IS24HOUR)) {
            bundle.putBoolean(ARG_IS24HOUR, readableMap.getBoolean(ARG_IS24HOUR));
        }
        if (readableMap.hasKey("mode") && !readableMap.isNull("mode")) {
            bundle.putString("mode", readableMap.getString("mode"));
        }
        return bundle;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return FRAGMENT_TAG;
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || !(currentActivity instanceof FragmentActivity)) {
            promise.reject(ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to a FragmentActivity");
            return;
        }
        FragmentManager supportFragmentManager = ((FragmentActivity) currentActivity).getSupportFragmentManager();
        DialogFragment dialogFragment = (DialogFragment) supportFragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (dialogFragment != null) {
            dialogFragment.dismissInternal(false, false);
        }
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        if (readableMap != null) {
            timePickerDialogFragment.setArguments(createFragmentArguments(readableMap));
        }
        TimePickerDialogListener timePickerDialogListener = new TimePickerDialogListener(promise);
        timePickerDialogFragment.mOnDismissListener = timePickerDialogListener;
        timePickerDialogFragment.mOnTimeSetListener = timePickerDialogListener;
        timePickerDialogFragment.show(supportFragmentManager, FRAGMENT_TAG);
    }
}

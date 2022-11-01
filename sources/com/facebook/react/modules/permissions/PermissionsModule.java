package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.SparseArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;
@ReactModule(name = PermissionsModule.NAME)
/* loaded from: classes.dex */
public class PermissionsModule extends ReactContextBaseJavaModule implements PermissionListener {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "PermissionsAndroid";
    private int mRequestCode = 0;
    private final String GRANTED = "granted";
    private final String DENIED = "denied";
    private final String NEVER_ASK_AGAIN = "never_ask_again";
    private final SparseArray<Callback> mCallbacks = new SparseArray<>();

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }

    @ReactMethod
    public void checkPermission(String str, Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        boolean z = true;
        if (Build.VERSION.SDK_INT < 23) {
            if (baseContext.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                z = false;
            }
            promise.resolve(Boolean.valueOf(z));
            return;
        }
        if (baseContext.checkSelfPermission(str) != 0) {
            z = false;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mCallbacks.get(i).invoke(iArr, getPermissionAwareActivity());
        this.mCallbacks.remove(i);
        return this.mCallbacks.size() == 0;
    }

    @ReactMethod
    public void requestMultiplePermissions(ReadableArray readableArray, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int i = 0;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            String string = readableArray.getString(i2);
            String str = "granted";
            if (Build.VERSION.SDK_INT < 23) {
                if (baseContext.checkPermission(string, Process.myPid(), Process.myUid()) != 0) {
                    str = "denied";
                }
                writableNativeMap.putString(string, str);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, str);
            } else {
                arrayList.add(string);
            }
            i++;
        }
        if (readableArray.size() == i) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback(this) { // from class: com.facebook.react.modules.permissions.PermissionsModule.2
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] iArr = (int[]) objArr[0];
                    PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        String str2 = (String) arrayList.get(i3);
                        if (iArr.length > 0 && iArr[i3] == 0) {
                            writableNativeMap.putString(str2, "granted");
                        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str2)) {
                            writableNativeMap.putString(str2, "denied");
                        } else {
                            writableNativeMap.putString(str2, "never_ask_again");
                        }
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @ReactMethod
    public void requestPermission(final String str, final Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        String str2 = "granted";
        if (Build.VERSION.SDK_INT < 23) {
            if (baseContext.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                str2 = "denied";
            }
            promise.resolve(str2);
        } else if (baseContext.checkSelfPermission(str) == 0) {
            promise.resolve(str2);
        } else {
            try {
                PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
                this.mCallbacks.put(this.mRequestCode, new Callback(this) { // from class: com.facebook.react.modules.permissions.PermissionsModule.1
                    @Override // com.facebook.react.bridge.Callback
                    public void invoke(Object... objArr) {
                        int[] iArr = (int[]) objArr[0];
                        if (iArr.length > 0 && iArr[0] == 0) {
                            promise.resolve("granted");
                        } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                            promise.resolve("denied");
                        } else {
                            promise.resolve("never_ask_again");
                        }
                    }
                });
                permissionAwareActivity.requestPermissions(new String[]{str}, this.mRequestCode, this);
                this.mRequestCode++;
            } catch (IllegalStateException e) {
                promise.reject(ERROR_INVALID_ACTIVITY, e);
            }
        }
    }

    @ReactMethod
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        if (Build.VERSION.SDK_INT < 23) {
            promise.resolve(Boolean.FALSE);
            return;
        }
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }
}

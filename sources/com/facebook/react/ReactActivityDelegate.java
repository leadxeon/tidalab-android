package com.facebook.react;

import android.app.Activity;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.PermissionListener;
/* loaded from: classes.dex */
public class ReactActivityDelegate {
    public final Activity mActivity;
    public final String mMainComponentName;
    public PermissionListener mPermissionListener;
    public Callback mPermissionsCallback;
    public ReactDelegate mReactDelegate;

    public ReactActivityDelegate(ReactActivity reactActivity, String str) {
        this.mActivity = reactActivity;
        this.mMainComponentName = str;
    }

    public ReactNativeHost getReactNativeHost() {
        Activity activity = this.mActivity;
        R$dimen.assertNotNull(activity);
        return ((ReactApplication) activity.getApplication()).getReactNativeHost();
    }
}

package com.facebook.react;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
/* loaded from: classes.dex */
public class ReactDelegate {
    public final Activity mActivity;
    public DoubleTapReloadRecognizer mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    public Bundle mLaunchOptions;
    public ReactNativeHost mReactNativeHost;
    public ReactRootView mReactRootView;

    public ReactDelegate(Activity activity, ReactNativeHost reactNativeHost, String str, Bundle bundle) {
        this.mActivity = activity;
        this.mLaunchOptions = bundle;
        this.mReactNativeHost = reactNativeHost;
    }
}

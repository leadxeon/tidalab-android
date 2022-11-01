package com.facebook.react;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class ReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    public final ReactActivityDelegate mDelegate = new ReactActivityDelegate(this, getMainComponentName());

    public String getMainComponentName() {
        return null;
    }

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        ReactDelegate reactDelegate = this.mDelegate.mReactDelegate;
        if (reactDelegate.mReactNativeHost.hasInstance()) {
            ReactInstanceManager reactInstanceManager = reactDelegate.mReactNativeHost.getReactInstanceManager();
            Activity activity = reactDelegate.mActivity;
            ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
            if (currentReactContext != null) {
                currentReactContext.onActivityResult(activity, i, i2, intent);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        boolean z;
        ReactDelegate reactDelegate = this.mDelegate.mReactDelegate;
        if (reactDelegate.mReactNativeHost.hasInstance()) {
            ReactInstanceManager reactInstanceManager = reactDelegate.mReactNativeHost.getReactInstanceManager();
            Objects.requireNonNull(reactInstanceManager);
            UiThreadUtil.assertOnUiThread();
            ReactContext reactContext = reactInstanceManager.mCurrentReactContext;
            if (reactContext == null) {
                FLog.w("ReactNative", "Instance detached from instance manager");
                UiThreadUtil.assertOnUiThread();
                DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = reactInstanceManager.mDefaultBackButtonImpl;
                if (defaultHardwareBackBtnHandler != null) {
                    defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
                }
            } else {
                ((DeviceEventManagerModule) reactContext.getNativeModule(DeviceEventManagerModule.class)).emitHardwareBackPressed();
            }
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            this.mOnBackPressedDispatcher.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        final String str = reactActivityDelegate.mMainComponentName;
        final Activity activity = reactActivityDelegate.mActivity;
        R$dimen.assertNotNull(activity);
        final ReactNativeHost reactNativeHost = reactActivityDelegate.getReactNativeHost();
        ReactDelegate reactDelegate = new ReactDelegate(activity, reactNativeHost, str, null) { // from class: com.facebook.react.ReactActivityDelegate.1
        };
        reactActivityDelegate.mReactDelegate = reactDelegate;
        if (reactActivityDelegate.mMainComponentName == null) {
            return;
        }
        if (reactDelegate.mReactRootView == null) {
            Objects.requireNonNull(reactActivityDelegate);
            Activity activity2 = reactActivityDelegate.mActivity;
            R$dimen.assertNotNull(activity2);
            ReactRootView reactRootView = new ReactRootView(activity2);
            reactDelegate.mReactRootView = reactRootView;
            reactRootView.startReactApplication(reactDelegate.mReactNativeHost.getReactInstanceManager(), str, reactDelegate.mLaunchOptions);
            Activity activity3 = reactActivityDelegate.mActivity;
            R$dimen.assertNotNull(activity3);
            activity3.setContentView(reactActivityDelegate.mReactDelegate.mReactRootView);
            return;
        }
        throw new IllegalStateException("Cannot loadApp while app is already running.");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ReactDelegate reactDelegate = this.mDelegate.mReactDelegate;
        ReactRootView reactRootView = reactDelegate.mReactRootView;
        if (reactRootView != null) {
            UiThreadUtil.assertOnUiThread();
            ReactInstanceManager reactInstanceManager = reactRootView.mReactInstanceManager;
            if (reactInstanceManager != null && reactRootView.mIsAttachedToInstance) {
                UiThreadUtil.assertOnUiThread();
                synchronized (reactInstanceManager.mAttachedReactRoots) {
                    if (reactInstanceManager.mAttachedReactRoots.contains(reactRootView)) {
                        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                        reactInstanceManager.mAttachedReactRoots.remove(reactRootView);
                        if (currentReactContext != null && currentReactContext.hasActiveCatalystInstance()) {
                            reactInstanceManager.detachViewFromInstance(reactRootView, currentReactContext.getCatalystInstance());
                        }
                    }
                }
                reactRootView.mIsAttachedToInstance = false;
            }
            reactRootView.mReactInstanceManager = null;
            reactRootView.mShouldLogContentAppeared = false;
            reactDelegate.mReactRootView = null;
        }
        if (reactDelegate.mReactNativeHost.hasInstance()) {
            ReactInstanceManager reactInstanceManager2 = reactDelegate.mReactNativeHost.getReactInstanceManager();
            if (reactDelegate.mActivity == reactInstanceManager2.mCurrentActivity) {
                UiThreadUtil.assertOnUiThread();
                if (reactInstanceManager2.mUseDeveloperSupport) {
                    reactInstanceManager2.mDevSupportManager.setDevSupportEnabled(false);
                }
                LifecycleState lifecycleState = LifecycleState.BEFORE_RESUME;
                synchronized (reactInstanceManager2) {
                    ReactContext currentReactContext2 = reactInstanceManager2.getCurrentReactContext();
                    if (currentReactContext2 != null) {
                        if (reactInstanceManager2.mLifecycleState == LifecycleState.RESUMED) {
                            currentReactContext2.onHostPause();
                            reactInstanceManager2.mLifecycleState = lifecycleState;
                        }
                        if (reactInstanceManager2.mLifecycleState == lifecycleState) {
                            currentReactContext2.onHostDestroy();
                        }
                    }
                    reactInstanceManager2.mLifecycleState = LifecycleState.BEFORE_CREATE;
                }
                reactInstanceManager2.mCurrentActivity = null;
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z;
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        if (!reactActivityDelegate.getReactNativeHost().hasInstance() || !reactActivityDelegate.getReactNativeHost().getUseDeveloperSupport() || i != 90) {
            z = false;
        } else {
            keyEvent.startTracking();
            z = true;
        }
        return z || super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        boolean z;
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        if (!reactActivityDelegate.getReactNativeHost().hasInstance() || !reactActivityDelegate.getReactNativeHost().getUseDeveloperSupport() || i != 90) {
            z = false;
        } else {
            ReactInstanceManager reactInstanceManager = reactActivityDelegate.getReactNativeHost().getReactInstanceManager();
            Objects.requireNonNull(reactInstanceManager);
            UiThreadUtil.assertOnUiThread();
            reactInstanceManager.mDevSupportManager.showDevOptionsDialog();
            z = true;
        }
        return z || super.onKeyLongPress(i, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005b  */
    @Override // android.app.Activity, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onKeyUp(int r9, android.view.KeyEvent r10) {
        /*
            r8 = this;
            com.facebook.react.ReactActivityDelegate r0 = r8.mDelegate
            com.facebook.react.ReactDelegate r0 = r0.mReactDelegate
            com.facebook.react.ReactNativeHost r1 = r0.mReactNativeHost
            boolean r1 = r1.hasInstance()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0068
            com.facebook.react.ReactNativeHost r1 = r0.mReactNativeHost
            boolean r1 = r1.getUseDeveloperSupport()
            if (r1 == 0) goto L_0x0068
            r1 = 82
            if (r9 != r1) goto L_0x002c
            com.facebook.react.ReactNativeHost r0 = r0.mReactNativeHost
            com.facebook.react.ReactInstanceManager r0 = r0.getReactInstanceManager()
            java.util.Objects.requireNonNull(r0)
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            com.facebook.react.devsupport.interfaces.DevSupportManager r0 = r0.mDevSupportManager
            r0.showDevOptionsDialog()
            goto L_0x0066
        L_0x002c:
            com.facebook.react.devsupport.DoubleTapReloadRecognizer r1 = r0.mDoubleTapReloadRecognizer
            androidx.recyclerview.R$dimen.assertNotNull(r1)
            android.app.Activity r4 = r0.mActivity
            android.view.View r4 = r4.getCurrentFocus()
            r5 = 46
            if (r9 != r5) goto L_0x0058
            boolean r4 = r4 instanceof android.widget.EditText
            if (r4 != 0) goto L_0x0058
            boolean r4 = r1.mDoRefresh
            if (r4 == 0) goto L_0x0047
            r1.mDoRefresh = r3
            r1 = 1
            goto L_0x0059
        L_0x0047:
            r1.mDoRefresh = r2
            android.os.Handler r4 = new android.os.Handler
            r4.<init>()
            com.facebook.react.devsupport.DoubleTapReloadRecognizer$1 r5 = new com.facebook.react.devsupport.DoubleTapReloadRecognizer$1
            r5.<init>()
            r6 = 200(0xc8, double:9.9E-322)
            r4.postDelayed(r5, r6)
        L_0x0058:
            r1 = 0
        L_0x0059:
            if (r1 == 0) goto L_0x0068
            com.facebook.react.ReactNativeHost r0 = r0.mReactNativeHost
            com.facebook.react.ReactInstanceManager r0 = r0.getReactInstanceManager()
            com.facebook.react.devsupport.interfaces.DevSupportManager r0 = r0.mDevSupportManager
            r0.handleReloadJS()
        L_0x0066:
            r0 = 1
            goto L_0x0069
        L_0x0068:
            r0 = 0
        L_0x0069:
            if (r0 != 0) goto L_0x0073
            boolean r9 = super.onKeyUp(r9, r10)
            if (r9 == 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r2 = 0
        L_0x0073:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactActivity.onKeyUp(int, android.view.KeyEvent):boolean");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        boolean z;
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        if (reactActivityDelegate.getReactNativeHost().hasInstance()) {
            ReactInstanceManager reactInstanceManager = reactActivityDelegate.getReactNativeHost().getReactInstanceManager();
            Objects.requireNonNull(reactInstanceManager);
            UiThreadUtil.assertOnUiThread();
            ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
            if (currentReactContext == null) {
                FLog.w("ReactNative", "Instance detached from instance manager");
            } else {
                String action = intent.getAction();
                Uri data = intent.getData();
                if (data != null && ("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action))) {
                    ((DeviceEventManagerModule) currentReactContext.getNativeModule(DeviceEventManagerModule.class)).emitNewIntentReceived(data);
                }
                currentReactContext.onNewIntent(reactInstanceManager.mCurrentActivity, intent);
            }
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            super.onNewIntent(intent);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ReactDelegate reactDelegate = this.mDelegate.mReactDelegate;
        if (reactDelegate.mReactNativeHost.hasInstance()) {
            ReactInstanceManager reactInstanceManager = reactDelegate.mReactNativeHost.getReactInstanceManager();
            Activity activity = reactDelegate.mActivity;
            R$dimen.assertNotNull(reactInstanceManager.mCurrentActivity);
            boolean z = activity == reactInstanceManager.mCurrentActivity;
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Pausing an activity that is not the current activity, this is incorrect! Current activity: ");
            outline13.append(reactInstanceManager.mCurrentActivity.getClass().getSimpleName());
            outline13.append(" Paused activity: ");
            outline13.append(activity.getClass().getSimpleName());
            R$dimen.assertCondition(z, outline13.toString());
            UiThreadUtil.assertOnUiThread();
            reactInstanceManager.mDefaultBackButtonImpl = null;
            if (reactInstanceManager.mUseDeveloperSupport) {
                reactInstanceManager.mDevSupportManager.setDevSupportEnabled(false);
            }
            synchronized (reactInstanceManager) {
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                if (currentReactContext != null) {
                    if (reactInstanceManager.mLifecycleState == LifecycleState.BEFORE_CREATE) {
                        currentReactContext.onHostResume(reactInstanceManager.mCurrentActivity);
                        currentReactContext.onHostPause();
                    } else if (reactInstanceManager.mLifecycleState == LifecycleState.RESUMED) {
                        currentReactContext.onHostPause();
                    }
                }
                reactInstanceManager.mLifecycleState = LifecycleState.BEFORE_RESUME;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(final int i, final String[] strArr, final int[] iArr) {
        final ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        reactActivityDelegate.mPermissionsCallback = new Callback() { // from class: com.facebook.react.ReactActivityDelegate.2
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                PermissionListener permissionListener = ReactActivityDelegate.this.mPermissionListener;
                if (permissionListener != null && permissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
                    ReactActivityDelegate.this.mPermissionListener = null;
                }
            }
        };
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        ReactDelegate reactDelegate = reactActivityDelegate.mReactDelegate;
        if (reactDelegate.mReactNativeHost.hasInstance()) {
            if (reactDelegate.mActivity instanceof DefaultHardwareBackBtnHandler) {
                final ReactInstanceManager reactInstanceManager = reactDelegate.mReactNativeHost.getReactInstanceManager();
                Activity activity = reactDelegate.mActivity;
                Objects.requireNonNull(reactInstanceManager);
                UiThreadUtil.assertOnUiThread();
                reactInstanceManager.mDefaultBackButtonImpl = (DefaultHardwareBackBtnHandler) activity;
                UiThreadUtil.assertOnUiThread();
                reactInstanceManager.mCurrentActivity = activity;
                if (reactInstanceManager.mUseDeveloperSupport) {
                    final View decorView = activity.getWindow().getDecorView();
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    if (!decorView.isAttachedToWindow()) {
                        decorView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.facebook.react.ReactInstanceManager.4
                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewAttachedToWindow(View view) {
                                decorView.removeOnAttachStateChangeListener(this);
                                reactInstanceManager.mDevSupportManager.setDevSupportEnabled(true);
                            }

                            @Override // android.view.View.OnAttachStateChangeListener
                            public void onViewDetachedFromWindow(View view) {
                            }
                        });
                    } else {
                        reactInstanceManager.mDevSupportManager.setDevSupportEnabled(true);
                    }
                }
                reactInstanceManager.moveToResumedLifecycleState(false);
            } else {
                throw new ClassCastException("Host Activity does not implement DefaultHardwareBackBtnHandler");
            }
        }
        Callback callback = reactActivityDelegate.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            reactActivityDelegate.mPermissionsCallback = null;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        if (reactActivityDelegate.getReactNativeHost().hasInstance()) {
            ReactInstanceManager reactInstanceManager = reactActivityDelegate.getReactNativeHost().getReactInstanceManager();
            Objects.requireNonNull(reactInstanceManager);
            UiThreadUtil.assertOnUiThread();
            ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
            if (currentReactContext != null) {
                currentReactContext.onWindowFocusChange(z);
            }
        }
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        ReactActivityDelegate reactActivityDelegate = this.mDelegate;
        reactActivityDelegate.mPermissionListener = permissionListener;
        Activity activity = reactActivityDelegate.mActivity;
        R$dimen.assertNotNull(activity);
        activity.requestPermissions(strArr, i);
    }
}

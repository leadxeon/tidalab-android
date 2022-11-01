package com.facebook.react;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Trace;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.deviceinfo.DeviceInfoModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public class ReactRootView extends FrameLayout implements RootView, ReactRoot {
    public Bundle mAppProperties;
    public CustomGlobalLayoutListener mCustomGlobalLayoutListener;
    public String mInitialUITemplate;
    public boolean mIsAttachedToInstance;
    public String mJSModuleName;
    public JSTouchDispatcher mJSTouchDispatcher;
    public ReactInstanceManager mReactInstanceManager;
    public ReactRootViewEventListener mRootViewEventListener;
    public int mRootViewTag;
    public boolean mShouldLogContentAppeared;
    public final ReactAndroidHWInputDeviceHelper mAndroidHWInputDeviceHelper = new ReactAndroidHWInputDeviceHelper(this);
    public boolean mWasMeasured = false;
    public int mWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
    public int mHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
    public int mLastWidth = 0;
    public int mLastHeight = 0;
    public int mUIManagerType = 1;

    /* loaded from: classes.dex */
    public class CustomGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        public int mKeyboardHeight = 0;
        public int mDeviceRotation = 0;
        public DisplayMetrics mWindowMetrics = new DisplayMetrics();
        public DisplayMetrics mScreenMetrics = new DisplayMetrics();
        public final Rect mVisibleViewArea = new Rect();
        public final int mMinKeyboardHeightDetected = (int) PixelUtil.toPixelFromDIP(60.0f);

        public CustomGlobalLayoutListener() {
            R$style.initDisplayMetricsIfNotInitialized(ReactRootView.this.getContext().getApplicationContext());
        }

        public final WritableMap createKeyboardEventPayload(double d, double d2, double d3, double d4) {
            WritableMap createMap = Arguments.createMap();
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putDouble("height", d4);
            createMap2.putDouble("screenX", d2);
            createMap2.putDouble("width", d3);
            createMap2.putDouble("screenY", d);
            createMap.putMap("endCoordinates", createMap2);
            createMap.putString("easing", "keyboard");
            createMap.putDouble("duration", 0.0d);
            return createMap;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            String str;
            double d;
            ReactRootView reactRootView = ReactRootView.this;
            ReactInstanceManager reactInstanceManager = reactRootView.mReactInstanceManager;
            if (reactInstanceManager != null && reactRootView.mIsAttachedToInstance && reactInstanceManager.getCurrentReactContext() != null) {
                ReactRootView.this.getRootView().getWindowVisibleDisplayFrame(this.mVisibleViewArea);
                int i = R$style.sWindowDisplayMetrics.heightPixels;
                Rect rect = this.mVisibleViewArea;
                int i2 = rect.bottom;
                int i3 = i - i2;
                int i4 = this.mKeyboardHeight;
                boolean z = true;
                if (i4 != i3 && i3 > this.mMinKeyboardHeightDetected) {
                    this.mKeyboardHeight = i3;
                    ReactRootView.this.sendEvent("keyboardDidShow", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(i2), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.left), PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), PixelUtil.toDIPFromPixel(this.mKeyboardHeight)));
                } else {
                    if (i4 != 0 && i3 <= this.mMinKeyboardHeightDetected) {
                        this.mKeyboardHeight = 0;
                        ReactRootView.this.sendEvent("keyboardDidHide", createKeyboardEventPayload(PixelUtil.toDIPFromPixel(rect.height()), 0.0d, PixelUtil.toDIPFromPixel(this.mVisibleViewArea.width()), 0.0d));
                    }
                }
                int rotation = ((WindowManager) ReactRootView.this.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
                if (this.mDeviceRotation != rotation) {
                    this.mDeviceRotation = rotation;
                    if (rotation != 0) {
                        if (rotation == 1) {
                            d = -90.0d;
                            str = "landscape-primary";
                        } else if (rotation == 2) {
                            d = 180.0d;
                            str = "portrait-secondary";
                        } else if (rotation == 3) {
                            d = 90.0d;
                            str = "landscape-secondary";
                        }
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("name", str);
                        createMap.putDouble("rotationDegrees", d);
                        createMap.putBoolean("isLandscape", z);
                        ReactRootView.this.sendEvent("namedOrientationDidChange", createMap);
                    } else {
                        d = 0.0d;
                        str = "portrait-primary";
                    }
                    z = false;
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putString("name", str);
                    createMap2.putDouble("rotationDegrees", d);
                    createMap2.putBoolean("isLandscape", z);
                    ReactRootView.this.sendEvent("namedOrientationDidChange", createMap2);
                }
                R$style.initDisplayMetrics(ReactRootView.this.getContext());
                if (!this.mWindowMetrics.equals(R$style.sWindowDisplayMetrics) || !this.mScreenMetrics.equals(R$style.sScreenDisplayMetrics)) {
                    this.mWindowMetrics.setTo(R$style.sWindowDisplayMetrics);
                    this.mScreenMetrics.setTo(R$style.sScreenDisplayMetrics);
                    ((DeviceInfoModule) ReactRootView.this.mReactInstanceManager.getCurrentReactContext().getNativeModule(DeviceInfoModule.class)).emitUpdateDimensionsEvent();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public interface ReactRootViewEventListener {
        void onAttachedToReactInstance(ReactRootView reactRootView);
    }

    public ReactRootView(Context context) {
        super(context);
        setClipChildren(false);
    }

    private CustomGlobalLayoutListener getCustomGlobalLayoutListener() {
        if (this.mCustomGlobalLayoutListener == null) {
            this.mCustomGlobalLayoutListener = new CustomGlobalLayoutListener();
        }
        return this.mCustomGlobalLayoutListener;
    }

    private void setAllowImmediateUIOperationExecution(boolean z) {
        ReactContext currentReactContext;
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null && (currentReactContext = reactInstanceManager.getCurrentReactContext()) != null) {
            R$style.getUIManager(currentReactContext, getUIManagerType()).setAllowImmediateUIOperationExecution(z);
        }
    }

    public final void attachToReactInstanceManager() {
        Trace.beginSection("attachToReactInstanceManager");
        if (!this.mIsAttachedToInstance) {
            try {
                this.mIsAttachedToInstance = true;
                ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
                R$dimen.assertNotNull(reactInstanceManager);
                ReactInstanceManager reactInstanceManager2 = reactInstanceManager;
                UiThreadUtil.assertOnUiThread();
                reactInstanceManager2.mAttachedReactRoots.add(this);
                getRootViewGroup().removeAllViews();
                getRootViewGroup().setId(-1);
                ReactContext currentReactContext = reactInstanceManager2.getCurrentReactContext();
                if (reactInstanceManager2.mCreateReactContextThread == null && currentReactContext != null) {
                    reactInstanceManager2.attachRootViewToInstance(this);
                }
                getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
                Trace.endSection();
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (StackOverflowError e) {
            handleException(e);
        }
    }

    public final void dispatchJSTouchEvent(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.mJSTouchDispatcher == null) {
            FLog.w("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactNative", "Unable to handle key event as the catalyst instance has not been attached");
            return super.dispatchKeyEvent(keyEvent);
        }
        ReactAndroidHWInputDeviceHelper reactAndroidHWInputDeviceHelper = this.mAndroidHWInputDeviceHelper;
        Objects.requireNonNull(reactAndroidHWInputDeviceHelper);
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        if (action == 1 || action == 0) {
            Map<Integer, String> map = ReactAndroidHWInputDeviceHelper.KEY_EVENTS_ACTIONS;
            if (map.containsKey(Integer.valueOf(keyCode))) {
                reactAndroidHWInputDeviceHelper.dispatchEvent(map.get(Integer.valueOf(keyCode)), reactAndroidHWInputDeviceHelper.mLastFocusedViewId, action);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void finalize() throws Throwable {
        super.finalize();
        R$dimen.assertCondition(!this.mIsAttachedToInstance, "The application this ReactRootView was rendering was not unmounted before the ReactRootView was garbage collected. This usually means that your application is leaking large amounts of memory. To solve this, make sure to call ReactRootView#unmountReactApplication in the onDestroy() of your hosting Activity or in the onDestroyView() of your hosting Fragment.");
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public Bundle getAppProperties() {
        return this.mAppProperties;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getHeightMeasureSpec() {
        return this.mHeightMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public String getInitialUITemplate() {
        return this.mInitialUITemplate;
    }

    public String getJSModuleName() {
        String str = this.mJSModuleName;
        R$dimen.assertNotNull(str);
        return str;
    }

    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactInstanceManager;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public ViewGroup getRootViewGroup() {
        return this;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getRootViewTag() {
        return this.mRootViewTag;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public String getSurfaceID() {
        Bundle appProperties = getAppProperties();
        if (appProperties != null) {
            return appProperties.getString("surfaceID");
        }
        return null;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getUIManagerType() {
        return this.mUIManagerType;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public int getWidthMeasureSpec() {
        return this.mWidthMeasureSpec;
    }

    @Override // com.facebook.react.uimanager.RootView
    public void handleException(Throwable th) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || reactInstanceManager.getCurrentReactContext() == null) {
            throw new RuntimeException(th);
        }
        this.mReactInstanceManager.getCurrentReactContext().handleException(new IllegalViewOperationException(th.getMessage(), this, th));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
            getViewTreeObserver().addOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    @Override // com.facebook.react.uimanager.RootView
    public void onChildStartedNativeGesture(MotionEvent motionEvent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactNative", "Unable to dispatch touch to JS as the catalyst instance has not been attached");
        } else if (this.mJSTouchDispatcher == null) {
            FLog.w("ReactNative", "Unable to dispatch touch to JS before the dispatcher is available");
        } else {
            EventDispatcher eventDispatcher = ((UIManagerModule) this.mReactInstanceManager.getCurrentReactContext().getNativeModule(UIManagerModule.class)).getEventDispatcher();
            JSTouchDispatcher jSTouchDispatcher = this.mJSTouchDispatcher;
            if (!jSTouchDispatcher.mChildIsHandlingNativeGesture) {
                jSTouchDispatcher.dispatchCancelEvent(motionEvent, eventDispatcher);
                jSTouchDispatcher.mChildIsHandlingNativeGesture = true;
                jSTouchDispatcher.mTargetTag = -1;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsAttachedToInstance) {
            getViewTreeObserver().removeOnGlobalLayoutListener(getCustomGlobalLayoutListener());
        }
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactNative", "Unable to handle focus changed event as the catalyst instance has not been attached");
            super.onFocusChanged(z, i, rect);
            return;
        }
        ReactAndroidHWInputDeviceHelper reactAndroidHWInputDeviceHelper = this.mAndroidHWInputDeviceHelper;
        int i2 = reactAndroidHWInputDeviceHelper.mLastFocusedViewId;
        if (i2 != -1) {
            reactAndroidHWInputDeviceHelper.dispatchEvent("blur", i2, -1);
        }
        reactAndroidHWInputDeviceHelper.mLastFocusedViewId = -1;
        super.onFocusChanged(z, i, rect);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032 A[Catch: all -> 0x00b0, LOOP:0: B:16:0x002c->B:18:0x0032, LOOP_END, TryCatch #0 {all -> 0x00b0, blocks: (B:3:0x000a, B:5:0x000e, B:10:0x0016, B:14:0x0025, B:16:0x002c, B:18:0x0032, B:19:0x0050, B:23:0x0059, B:25:0x005f, B:27:0x0065, B:28:0x0083, B:30:0x008c, B:32:0x0090, B:34:0x0096, B:36:0x009a, B:38:0x009e, B:39:0x00a5), top: B:45:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: all -> 0x00b0, LOOP:1: B:25:0x005f->B:27:0x0065, LOOP_END, TryCatch #0 {all -> 0x00b0, blocks: (B:3:0x000a, B:5:0x000e, B:10:0x0016, B:14:0x0025, B:16:0x002c, B:18:0x0032, B:19:0x0050, B:23:0x0059, B:25:0x005f, B:27:0x0065, B:28:0x0083, B:30:0x008c, B:32:0x0090, B:34:0x0096, B:36:0x009a, B:38:0x009e, B:39:0x00a5), top: B:45:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008c A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:3:0x000a, B:5:0x000e, B:10:0x0016, B:14:0x0025, B:16:0x002c, B:18:0x0032, B:19:0x0050, B:23:0x0059, B:25:0x005f, B:27:0x0065, B:28:0x0083, B:30:0x008c, B:32:0x0090, B:34:0x0096, B:36:0x009a, B:38:0x009e, B:39:0x00a5), top: B:45:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0096 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:3:0x000a, B:5:0x000e, B:10:0x0016, B:14:0x0025, B:16:0x002c, B:18:0x0032, B:19:0x0050, B:23:0x0059, B:25:0x005f, B:27:0x0065, B:28:0x0083, B:30:0x008c, B:32:0x0090, B:34:0x0096, B:36:0x009a, B:38:0x009e, B:39:0x00a5), top: B:45:0x000a }] */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            r8.setAllowImmediateUIOperationExecution(r0)
            java.lang.String r1 = "ReactRootView.onMeasure"
            android.os.Trace.beginSection(r1)
            r1 = 1
            int r2 = r8.mWidthMeasureSpec     // Catch: all -> 0x00b0
            if (r9 != r2) goto L_0x0015
            int r2 = r8.mHeightMeasureSpec     // Catch: all -> 0x00b0
            if (r10 == r2) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            goto L_0x0016
        L_0x0015:
            r2 = 1
        L_0x0016:
            r8.mWidthMeasureSpec = r9     // Catch: all -> 0x00b0
            r8.mHeightMeasureSpec = r10     // Catch: all -> 0x00b0
            int r3 = android.view.View.MeasureSpec.getMode(r9)     // Catch: all -> 0x00b0
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x002a
            if (r3 != 0) goto L_0x0025
            goto L_0x002a
        L_0x0025:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch: all -> 0x00b0
            goto L_0x0050
        L_0x002a:
            r9 = 0
            r3 = 0
        L_0x002c:
            int r5 = r8.getChildCount()     // Catch: all -> 0x00b0
            if (r3 >= r5) goto L_0x0050
            android.view.View r5 = r8.getChildAt(r3)     // Catch: all -> 0x00b0
            int r6 = r5.getLeft()     // Catch: all -> 0x00b0
            int r7 = r5.getMeasuredWidth()     // Catch: all -> 0x00b0
            int r6 = r6 + r7
            int r7 = r5.getPaddingLeft()     // Catch: all -> 0x00b0
            int r6 = r6 + r7
            int r5 = r5.getPaddingRight()     // Catch: all -> 0x00b0
            int r6 = r6 + r5
            int r9 = java.lang.Math.max(r9, r6)     // Catch: all -> 0x00b0
            int r3 = r3 + 1
            goto L_0x002c
        L_0x0050:
            int r3 = android.view.View.MeasureSpec.getMode(r10)     // Catch: all -> 0x00b0
            if (r3 == r4) goto L_0x005e
            if (r3 != 0) goto L_0x0059
            goto L_0x005e
        L_0x0059:
            int r10 = android.view.View.MeasureSpec.getSize(r10)     // Catch: all -> 0x00b0
            goto L_0x0083
        L_0x005e:
            r10 = 0
        L_0x005f:
            int r3 = r8.getChildCount()     // Catch: all -> 0x00b0
            if (r0 >= r3) goto L_0x0083
            android.view.View r3 = r8.getChildAt(r0)     // Catch: all -> 0x00b0
            int r4 = r3.getTop()     // Catch: all -> 0x00b0
            int r5 = r3.getMeasuredHeight()     // Catch: all -> 0x00b0
            int r4 = r4 + r5
            int r5 = r3.getPaddingTop()     // Catch: all -> 0x00b0
            int r4 = r4 + r5
            int r3 = r3.getPaddingBottom()     // Catch: all -> 0x00b0
            int r4 = r4 + r3
            int r10 = java.lang.Math.max(r10, r4)     // Catch: all -> 0x00b0
            int r0 = r0 + 1
            goto L_0x005f
        L_0x0083:
            r8.setMeasuredDimension(r9, r10)     // Catch: all -> 0x00b0
            r8.mWasMeasured = r1     // Catch: all -> 0x00b0
            com.facebook.react.ReactInstanceManager r0 = r8.mReactInstanceManager     // Catch: all -> 0x00b0
            if (r0 == 0) goto L_0x0094
            boolean r0 = r8.mIsAttachedToInstance     // Catch: all -> 0x00b0
            if (r0 != 0) goto L_0x0094
            r8.attachToReactInstanceManager()     // Catch: all -> 0x00b0
            goto L_0x00a5
        L_0x0094:
            if (r2 != 0) goto L_0x009e
            int r0 = r8.mLastWidth     // Catch: all -> 0x00b0
            if (r0 != r9) goto L_0x009e
            int r0 = r8.mLastHeight     // Catch: all -> 0x00b0
            if (r0 == r10) goto L_0x00a5
        L_0x009e:
            int r0 = r8.mWidthMeasureSpec     // Catch: all -> 0x00b0
            int r2 = r8.mHeightMeasureSpec     // Catch: all -> 0x00b0
            r8.updateRootLayoutSpecs(r0, r2)     // Catch: all -> 0x00b0
        L_0x00a5:
            r8.mLastWidth = r9     // Catch: all -> 0x00b0
            r8.mLastHeight = r10     // Catch: all -> 0x00b0
            r8.setAllowImmediateUIOperationExecution(r1)
            android.os.Trace.endSection()
            return
        L_0x00b0:
            r9 = move-exception
            r8.setAllowImmediateUIOperationExecution(r1)
            android.os.Trace.endSection()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactRootView.onMeasure(int, int):void");
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void onStage(int i) {
        if (i == 101) {
            this.mJSTouchDispatcher = new JSTouchDispatcher(this);
            ReactRootViewEventListener reactRootViewEventListener = this.mRootViewEventListener;
            if (reactRootViewEventListener != null) {
                reactRootViewEventListener.onAttachedToReactInstance(this);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        dispatchJSTouchEvent(motionEvent);
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (this.mShouldLogContentAppeared) {
            this.mShouldLogContentAppeared = false;
            String str = this.mJSModuleName;
            if (str != null) {
                ReactMarker.logMarker(ReactMarkerConstants.CONTENT_APPEARED, str, this.mRootViewTag);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null || !this.mIsAttachedToInstance || reactInstanceManager.getCurrentReactContext() == null) {
            FLog.w("ReactNative", "Unable to handle child focus changed event as the catalyst instance has not been attached");
            super.requestChildFocus(view, view2);
            return;
        }
        ReactAndroidHWInputDeviceHelper reactAndroidHWInputDeviceHelper = this.mAndroidHWInputDeviceHelper;
        if (reactAndroidHWInputDeviceHelper.mLastFocusedViewId != view2.getId()) {
            int i = reactAndroidHWInputDeviceHelper.mLastFocusedViewId;
            if (i != -1) {
                reactAndroidHWInputDeviceHelper.dispatchEvent("blur", i, -1);
            }
            reactAndroidHWInputDeviceHelper.mLastFocusedViewId = view2.getId();
            reactAndroidHWInputDeviceHelper.dispatchEvent("focus", view2.getId(), -1);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void runApplication() {
        Trace.beginSection("ReactRootView.runApplication");
        try {
            ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
            if (reactInstanceManager != null && this.mIsAttachedToInstance) {
                ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
                if (currentReactContext != null) {
                    CatalystInstance catalystInstance = currentReactContext.getCatalystInstance();
                    String jSModuleName = getJSModuleName();
                    if (this.mWasMeasured) {
                        updateRootLayoutSpecs(this.mWidthMeasureSpec, this.mHeightMeasureSpec);
                    }
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("rootTag", getRootViewTag());
                    Bundle appProperties = getAppProperties();
                    if (appProperties != null) {
                        writableNativeMap.putMap("initialProps", Arguments.fromBundle(appProperties));
                    }
                    this.mShouldLogContentAppeared = true;
                    ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).runApplication(jSModuleName, writableNativeMap);
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    public void sendEvent(String str, WritableMap writableMap) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactInstanceManager.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
        }
    }

    public void setAppProperties(Bundle bundle) {
        UiThreadUtil.assertOnUiThread();
        this.mAppProperties = bundle;
        if (getRootViewTag() != 0) {
            runApplication();
        }
    }

    public void setEventListener(ReactRootViewEventListener reactRootViewEventListener) {
        this.mRootViewEventListener = reactRootViewEventListener;
    }

    public void setIsFabric(boolean z) {
        this.mUIManagerType = z ? 2 : 1;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setRootViewTag(int i) {
        this.mRootViewTag = i;
    }

    @Override // com.facebook.react.uimanager.ReactRoot
    public void setShouldLogContentAppeared(boolean z) {
        this.mShouldLogContentAppeared = z;
    }

    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, Bundle bundle) {
        Trace.beginSection("startReactApplication");
        try {
            UiThreadUtil.assertOnUiThread();
            R$dimen.assertCondition(this.mReactInstanceManager == null, "This root view has already been attached to a catalyst instance manager");
            this.mReactInstanceManager = reactInstanceManager;
            this.mJSModuleName = str;
            this.mAppProperties = bundle;
            this.mInitialUITemplate = null;
            reactInstanceManager.createReactContextInBackground();
            attachToReactInstanceManager();
        } finally {
            Trace.endSection();
        }
    }

    public final void updateRootLayoutSpecs(int i, int i2) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager == null) {
            FLog.w("ReactNative", "Unable to update root layout specs for uninitialized ReactInstanceManager");
            return;
        }
        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
        if (currentReactContext != null) {
            R$style.getUIManager(currentReactContext, getUIManagerType()).updateRootLayoutSpecs(getRootViewTag(), i, i2);
        }
    }
}

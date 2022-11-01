package com.swmansion.gesturehandler.react;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.modal.ReactModalHostView;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.GestureHandlerOrchestrator;
/* loaded from: classes.dex */
public class RNGestureHandlerRootHelper {
    public final ReactContext mContext;
    public final GestureHandler mJSGestureHandler;
    public final GestureHandlerOrchestrator mOrchestrator;
    public final ViewGroup mRootView;
    public boolean mShouldIntercept = false;
    public boolean mPassingTouch = false;

    /* loaded from: classes.dex */
    public class RootViewGestureHandler extends GestureHandler {
        public RootViewGestureHandler(AnonymousClass1 r2) {
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        public void onCancel() {
            RNGestureHandlerRootHelper.this.mShouldIntercept = true;
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            obtain.setAction(3);
            ViewGroup viewGroup = RNGestureHandlerRootHelper.this.mRootView;
            if (viewGroup instanceof ReactRootView) {
                ((ReactRootView) viewGroup).onChildStartedNativeGesture(obtain);
            } else {
                ((ReactModalHostView.DialogRootViewGroup) viewGroup).onChildStartedNativeGesture(obtain);
            }
        }

        @Override // com.swmansion.gesturehandler.GestureHandler
        public void onHandle(MotionEvent motionEvent) {
            if (this.mState == 0) {
                begin();
                RNGestureHandlerRootHelper.this.mShouldIntercept = false;
            }
            if (motionEvent.getActionMasked() == 1) {
                end();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
        r3 = (android.view.ViewGroup) r3;
        r7.mRootView = r3;
        android.util.Log.i("ReactNative", "[GESTURE HANDLER] Initialize gesture handler for root view " + r3);
        r7.mContext = r8;
        r8 = new com.swmansion.gesturehandler.GestureHandlerOrchestrator(r9, r2, new com.swmansion.gesturehandler.react.RNViewConfigurationHelper());
        r7.mOrchestrator = r8;
        r8.mMinAlphaForTraversal = 0.1f;
        r8 = new com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.RootViewGestureHandler(r7, null);
        r7.mJSGestureHandler = r8;
        r8.mTag = -r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x006b, code lost:
        monitor-enter(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x006c, code lost:
        r2.mHandlers.put(r8.mTag, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0073, code lost:
        monitor-exit(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0074, code lost:
        r2.attachHandlerToView(r8.mTag, r0);
        r1.registerRootHelper(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007c, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RNGestureHandlerRootHelper(com.facebook.react.bridge.ReactContext r8, android.view.ViewGroup r9) {
        /*
            r7 = this;
            r7.<init>()
            r0 = 0
            r7.mShouldIntercept = r0
            r7.mPassingTouch = r0
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            int r0 = r9.getId()
            r1 = 1
            if (r0 < r1) goto L_0x009c
            java.lang.Class<com.swmansion.gesturehandler.react.RNGestureHandlerModule> r1 = com.swmansion.gesturehandler.react.RNGestureHandlerModule.class
            com.facebook.react.bridge.NativeModule r1 = r8.getNativeModule(r1)
            com.swmansion.gesturehandler.react.RNGestureHandlerModule r1 = (com.swmansion.gesturehandler.react.RNGestureHandlerModule) r1
            com.swmansion.gesturehandler.react.RNGestureHandlerRegistry r2 = r1.getRegistry()
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            r3 = r9
        L_0x0022:
            if (r3 == 0) goto L_0x0031
            boolean r4 = r3 instanceof com.facebook.react.ReactRootView
            if (r4 != 0) goto L_0x0031
            boolean r4 = r3 instanceof com.facebook.react.views.modal.ReactModalHostView.DialogRootViewGroup
            if (r4 != 0) goto L_0x0031
            android.view.ViewParent r3 = r3.getParent()
            goto L_0x0022
        L_0x0031:
            if (r3 == 0) goto L_0x0080
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            r7.mRootView = r3
            java.lang.String r4 = "ReactNative"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[GESTURE HANDLER] Initialize gesture handler for root view "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            android.util.Log.i(r4, r3)
            r7.mContext = r8
            com.swmansion.gesturehandler.GestureHandlerOrchestrator r8 = new com.swmansion.gesturehandler.GestureHandlerOrchestrator
            com.swmansion.gesturehandler.react.RNViewConfigurationHelper r3 = new com.swmansion.gesturehandler.react.RNViewConfigurationHelper
            r3.<init>()
            r8.<init>(r9, r2, r3)
            r7.mOrchestrator = r8
            r9 = 1036831949(0x3dcccccd, float:0.1)
            r8.mMinAlphaForTraversal = r9
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper$RootViewGestureHandler r8 = new com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper$RootViewGestureHandler
            r9 = 0
            r8.<init>(r9)
            r7.mJSGestureHandler = r8
            int r9 = -r0
            r8.mTag = r9
            monitor-enter(r2)
            android.util.SparseArray<com.swmansion.gesturehandler.GestureHandler> r9 = r2.mHandlers     // Catch: all -> 0x007d
            int r3 = r8.mTag     // Catch: all -> 0x007d
            r9.put(r3, r8)     // Catch: all -> 0x007d
            monitor-exit(r2)
            int r8 = r8.mTag
            r2.attachHandlerToView(r8, r0)
            r1.registerRootHelper(r7)
            return
        L_0x007d:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        L_0x0080:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "View "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = " has not been mounted under ReactRootView"
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        L_0x009c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Expect view tag to be set for "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.<init>(com.facebook.react.bridge.ReactContext, android.view.ViewGroup):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0098, code lost:
        if (r11 == r3.mWrapperView) goto L_0x009a;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x019e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r34) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public void requestDisallowInterceptTouchEvent() {
        GestureHandler gestureHandler;
        if (this.mOrchestrator != null && !this.mPassingTouch && (gestureHandler = this.mJSGestureHandler) != null && gestureHandler.mState == 2) {
            gestureHandler.activate();
            this.mJSGestureHandler.end();
        }
    }

    public void tearDown() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[GESTURE HANDLER] Tearing down gesture handler registered for root view ");
        outline13.append(this.mRootView);
        Log.i("ReactNative", outline13.toString());
        RNGestureHandlerModule rNGestureHandlerModule = (RNGestureHandlerModule) this.mContext.getNativeModule(RNGestureHandlerModule.class);
        rNGestureHandlerModule.getRegistry().dropHandler(this.mJSGestureHandler.mTag);
        rNGestureHandlerModule.unregisterRootHelper(this);
    }
}

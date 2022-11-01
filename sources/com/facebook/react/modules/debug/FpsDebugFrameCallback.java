package com.facebook.react.modules.debug;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class FpsDebugFrameCallback extends ChoreographerCompat.FrameCallback {
    public ChoreographerCompat mChoreographer;
    public final ReactContext mReactContext;
    public TreeMap<Long, FpsInfo> mTimeToFps;
    public final UIManagerModule mUIManagerModule;
    public boolean mShouldStop = false;
    public long mFirstFrameTime = -1;
    public long mLastFrameTime = -1;
    public int mNumFrameCallbacks = 0;
    public int mExpectedNumFramesPrev = 0;
    public int m4PlusFrameStutters = 0;
    public int mNumFrameCallbacksWithBatchDispatches = 0;
    public boolean mIsRecordingFpsInfoAtEachFrame = false;
    public final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();

    /* loaded from: classes.dex */
    public static class FpsInfo {
        public final double fps;
        public final double jsFps;
        public final int totalExpectedFrames;
        public final int totalFrames;
        public final int totalJsFrames;
        public final int totalTimeMs;

        public FpsInfo(int i, int i2, int i3, int i4, double d, double d2, int i5) {
            this.totalFrames = i;
            this.totalJsFrames = i2;
            this.totalExpectedFrames = i3;
            this.fps = d;
            this.jsFps = d2;
            this.totalTimeMs = i5;
        }
    }

    public FpsDebugFrameCallback(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mUIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
        if (com.facebook.react.modules.debug.DidJSUpdateUiDuringFrameDetector.hasEventBetweenTimestamps(r8.mViewHierarchyUpdateEnqueuedEvents, r4, r27) == false) goto L_0x004d;
     */
    @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doFrame(long r27) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.debug.FpsDebugFrameCallback.doFrame(long):void");
    }

    public void stop() {
        this.mShouldStop = true;
        this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(null);
    }
}

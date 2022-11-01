package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.R$style;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.events.FabricEventEmitter;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.DeleteMountItem;
import com.facebook.react.fabric.mounting.mountitems.InsertMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.RemoveDeleteMultiMountItem;
import com.facebook.react.fabric.mounting.mountitems.RemoveMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateEventEmitterMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLayoutMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLocalDataMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePaddingMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePropsMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateStateMountItem;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import java.util.HashMap;
import java.util.Map;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes.dex */
public class FabricUIManager implements UIManager, LifecycleEventListener {
    public long mCommitStartTime;
    public int mCurrentSynchronousCommitNumber;
    public volatile boolean mDestroyed;
    public long mDispatchViewUpdatesTime;
    public long mFinishTransactionCPPTime;
    public long mFinishTransactionTime;
    public boolean mImmediatelyExecutedMountItemsOnUI;
    public long mLayoutTime;
    public long mRunStartTime;

    static {
        int i = PrinterHolder.$r8$clinit;
        DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.FABRIC_UI_MANAGER;
        FabricSoLoader.staticInit();
    }

    @DoNotStrip
    private MountItem createBatchMountItem(MountItem[] mountItemArr, int i, int i2) {
        return new BatchMountItem(mountItemArr, i, i2);
    }

    @DoNotStrip
    private MountItem createMountItem(String str, ReadableMap readableMap, Object obj, int i, int i2, boolean z) {
        FabricComponents.sComponentNames.get(str);
        throw null;
    }

    @DoNotStrip
    private MountItem deleteMountItem(int i) {
        return new DeleteMountItem(i);
    }

    @DoNotStrip
    private MountItem insertMountItem(int i, int i2, int i3) {
        return new InsertMountItem(i, i2, i3);
    }

    @DoNotStrip
    private long measure(int i, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4) {
        throw null;
    }

    @DoNotStrip
    private void preallocateView(int i, int i2, String str, ReadableMap readableMap, Object obj, boolean z) {
        throw null;
    }

    @DoNotStrip
    private MountItem removeDeleteMultiMountItem(int[] iArr) {
        return new RemoveDeleteMultiMountItem(iArr);
    }

    @DoNotStrip
    private MountItem removeMountItem(int i, int i2, int i3) {
        return new RemoveMountItem(i, i2, i3);
    }

    @DoNotStrip
    private void scheduleMountItem(MountItem mountItem, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        if (mountItem instanceof BatchMountItem) {
            this.mCommitStartTime = j;
            this.mLayoutTime = j5 - j4;
            this.mFinishTransactionCPPTime = j7 - j6;
            this.mFinishTransactionTime = SystemClock.uptimeMillis() - j6;
            this.mDispatchViewUpdatesTime = SystemClock.uptimeMillis();
        }
        throw null;
    }

    @DoNotStrip
    private MountItem updateEventEmitterMountItem(int i, Object obj) {
        return new UpdateEventEmitterMountItem(i, (EventEmitterWrapper) obj);
    }

    @DoNotStrip
    private MountItem updateLayoutMountItem(int i, int i2, int i3, int i4, int i5, int i6) {
        return new UpdateLayoutMountItem(i, i2, i3, i4, i5, i6);
    }

    @DoNotStrip
    private MountItem updateLocalDataMountItem(int i, ReadableMap readableMap) {
        return new UpdateLocalDataMountItem(i, readableMap);
    }

    @DoNotStrip
    private MountItem updatePaddingMountItem(int i, int i2, int i3, int i4, int i5) {
        return new UpdatePaddingMountItem(i, i2, i3, i4, i5);
    }

    @DoNotStrip
    private MountItem updatePropsMountItem(int i, ReadableMap readableMap) {
        return new UpdatePropsMountItem(i, readableMap);
    }

    @DoNotStrip
    private MountItem updateStateMountItem(int i, Object obj) {
        return new UpdateStateMountItem(i, (StateWrapper) obj);
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends View> int addRootView(T t, WritableMap writableMap, String str) {
        synchronized (ReactRootViewTagGenerator.class) {
            ReactRootViewTagGenerator.sNextRootViewTag += 10;
        }
        new ThemedReactContext(null, t.getContext(), ((ReactRoot) t).getSurfaceID());
        throw null;
    }

    @DoNotStrip
    public void clearJSResponder() {
        throw null;
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        throw null;
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mCommitStartTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mRunStartTime));
        hashMap.put("BatchedExecutionTime", 0L);
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.mFinishTransactionTime));
        hashMap.put("FinishFabricTransactionCPPTime", Long.valueOf(this.mFinishTransactionCPPTime));
        return hashMap;
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
        new FabricEventEmitter(this);
        throw null;
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        int i = FLog.$r8$clinit;
        if (this.mDestroyed) {
            ReactSoftException.logSoftException("FabricUIManager", new IllegalStateException("Cannot double-destroy FabricUIManager"));
        } else {
            this.mDestroyed = true;
            throw null;
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        ReactChoreographer.getInstance().removeFrameCallback$enumunboxing$(2, null);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(2, null);
    }

    @DoNotStrip
    public void onRequestEventBeat() {
        throw null;
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
    }

    @Override // com.facebook.react.bridge.UIManager
    public void sendAccessibilityEvent(int i, int i2) {
        throw null;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void setAllowImmediateUIOperationExecution(boolean z) {
        this.mImmediatelyExecutedMountItemsOnUI = z;
    }

    @DoNotStrip
    public void setJSResponder(int i, int i2, boolean z) {
        throw null;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap) {
        String str;
        int i2;
        ReactMarkerConstants reactMarkerConstants;
        int i3;
        Throwable th;
        UiThreadUtil.assertOnUiThread();
        long uptimeMillis = SystemClock.uptimeMillis();
        int i4 = this.mCurrentSynchronousCommitNumber;
        this.mCurrentSynchronousCommitNumber = i4 + 1;
        try {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START, null, i4);
            i3 = i4;
            try {
                scheduleMountItem(updatePropsMountItem(i, readableMap), i4, uptimeMillis, 0L, 0L, 0L, 0L, 0L, 0L);
                reactMarkerConstants = ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END;
                i2 = i3;
                str = null;
            } catch (Exception unused) {
                i2 = i3;
                str = null;
                reactMarkerConstants = ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END;
                ReactMarker.logFabricMarker(reactMarkerConstants, str, i2);
            } catch (Throwable th2) {
                th = th2;
                ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END, null, i3);
                throw th;
            }
        } catch (Exception unused2) {
            str = null;
            i2 = i4;
        } catch (Throwable th3) {
            th = th3;
            i3 = i4;
        }
        ReactMarker.logFabricMarker(reactMarkerConstants, str, i2);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(int i, int i2, int i3) {
        View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i2);
        View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i2);
        View.MeasureSpec.getMode(i3);
        View.MeasureSpec.getSize(i3);
        View.MeasureSpec.getMode(i3);
        View.MeasureSpec.getSize(i3);
        throw null;
    }

    @DoNotStrip
    private long measure(String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4) {
        R$style.getYogaSize(f, f2);
        R$style.getYogaMeasureMode(f, f2);
        R$style.getYogaSize(f3, f4);
        R$style.getYogaMeasureMode(f3, f4);
        throw null;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int i, String str, ReadableArray readableArray) {
        throw null;
    }
}

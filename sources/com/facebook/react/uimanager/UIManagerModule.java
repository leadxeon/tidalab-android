package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Trace;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.yoga.YogaDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@ReactModule(name = UIManagerModule.NAME)
/* loaded from: classes.dex */
public class UIManagerModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener, UIManager {
    private static final boolean DEBUG = false;
    public static final String NAME = "UIManager";
    private int mBatchId;
    private final Map<String, Object> mCustomDirectEvents;
    private final EventDispatcher mEventDispatcher;
    private final List<UIManagerModuleListener> mListeners;
    private final MemoryTrimCallback mMemoryTrimCallback;
    private final Map<String, Object> mModuleConstants;
    private final UIImplementation mUIImplementation;
    private Map<String, WritableMap> mViewManagerConstantsCache;
    private volatile int mViewManagerConstantsCacheSize;
    private final ViewManagerRegistry mViewManagerRegistry;

    /* renamed from: com.facebook.react.uimanager.UIManagerModule$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements CustomEventNamesResolver {
        public AnonymousClass1() {
        }
    }

    /* loaded from: classes.dex */
    public interface CustomEventNamesResolver {
    }

    /* loaded from: classes.dex */
    public class MemoryTrimCallback implements ComponentCallbacks2 {
        public MemoryTrimCallback(UIManagerModule uIManagerModule, AnonymousClass1 r2) {
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            if (i >= 60) {
                YogaNodePool.get().clear();
            }
        }
    }

    /* loaded from: classes.dex */
    public interface ViewManagerResolver {
    }

    static {
        int i = PrinterHolder.$r8$clinit;
        DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.UI_MANAGER;
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, int i) {
        this(reactApplicationContext, viewManagerResolver, new UIImplementationProvider(), i);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.facebook.react.bridge.WritableMap computeConstantsForViewManager(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x001b
            com.facebook.react.uimanager.UIImplementation r1 = r3.mUIImplementation
            com.facebook.react.uimanager.ViewManagerRegistry r1 = r1.mViewManagers
            java.util.Map<java.lang.String, com.facebook.react.uimanager.ViewManager> r2 = r1.mViewManagers
            java.lang.Object r2 = r2.get(r4)
            com.facebook.react.uimanager.ViewManager r2 = (com.facebook.react.uimanager.ViewManager) r2
            if (r2 == 0) goto L_0x0012
            goto L_0x001c
        L_0x0012:
            com.facebook.react.uimanager.UIManagerModule$ViewManagerResolver r2 = r1.mViewManagerResolver
            if (r2 == 0) goto L_0x001b
            com.facebook.react.uimanager.ViewManager r2 = r1.getViewManagerFromResolver(r4)
            goto L_0x001c
        L_0x001b:
            r2 = r0
        L_0x001c:
            if (r2 != 0) goto L_0x001f
            return r0
        L_0x001f:
            r2.getName()
            java.util.Map<java.lang.String, java.lang.Object> r4 = r3.mCustomDirectEvents     // Catch: all -> 0x002d
            java.util.Map r4 = com.facebook.react.uimanager.PixelUtil.createConstantsForViewManager(r2, r0, r0, r0, r4)     // Catch: all -> 0x002d
            com.facebook.react.bridge.WritableNativeMap r4 = com.facebook.react.bridge.Arguments.makeNativeMap(r4)     // Catch: all -> 0x002d
            return r4
        L_0x002d:
            r4 = move-exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIManagerModule.computeConstantsForViewManager(java.lang.String):com.facebook.react.bridge.WritableMap");
    }

    private static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        try {
            return PixelUtil.createConstants(viewManagerResolver);
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends View> int addRootView(T t, WritableMap writableMap, String str) {
        int i;
        Trace.beginSection("UIManagerModule.addRootView");
        synchronized (ReactRootViewTagGenerator.class) {
            i = ReactRootViewTagGenerator.sNextRootViewTag;
            ReactRootViewTagGenerator.sNextRootViewTag = i + 10;
        }
        ThemedReactContext themedReactContext = new ThemedReactContext(getReactApplicationContext(), t.getContext(), ((ReactRoot) t).getSurfaceID());
        final UIImplementation uIImplementation = this.mUIImplementation;
        synchronized (uIImplementation.uiImplementationThreadLock) {
            final ReactShadowNodeImpl reactShadowNodeImpl = new ReactShadowNodeImpl();
            if (I18nUtil.getInstance().isRTL(uIImplementation.mReactContext)) {
                reactShadowNodeImpl.mYogaNode.setDirection(YogaDirection.RTL);
            }
            reactShadowNodeImpl.mViewClassName = "Root";
            reactShadowNodeImpl.mReactTag = i;
            reactShadowNodeImpl.mThemedContext = themedReactContext;
            themedReactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.UIImplementation.1
                @Override // java.lang.Runnable
                public void run() {
                    ShadowNodeRegistry shadowNodeRegistry = UIImplementation.this.mShadowNodeRegistry;
                    ReactShadowNode reactShadowNode = reactShadowNodeImpl;
                    shadowNodeRegistry.mThreadAsserter.assertNow();
                    int reactTag = reactShadowNode.getReactTag();
                    shadowNodeRegistry.mTagsToCSSNodes.put(reactTag, reactShadowNode);
                    shadowNodeRegistry.mRootTags.put(reactTag, true);
                }
            });
            NativeViewHierarchyManager nativeViewHierarchyManager = uIImplementation.mOperationsQueue.mNativeViewHierarchyManager;
            synchronized (nativeViewHierarchyManager) {
                nativeViewHierarchyManager.addRootViewGroup(i, t);
            }
        }
        Trace.endSection();
        return i;
    }

    public void addUIBlock(UIBlock uIBlock) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.UIBlockOperation(uIBlock));
    }

    public void addUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.add(uIManagerModuleListener);
    }

    @ReactMethod
    public void clearJSResponder() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.ChangeJSResponderOperation(0, 0, true, false));
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.ConfigureLayoutAnimationOperation(readableMap, callback, null));
    }

    @ReactMethod
    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        if (DEBUG) {
            FLog.d("ReactNative", "(UIManager.createView) tag: " + i + ", class: " + str + ", props: " + readableMap);
            int i3 = PrinterHolder.$r8$clinit;
            DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.UI_MANAGER;
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        synchronized (uIImplementation.uiImplementationThreadLock) {
            ReactShadowNode createShadowNodeInstance = uIImplementation.mViewManagers.get(str).createShadowNodeInstance(uIImplementation.mReactContext);
            ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
            shadowNodeRegistry.mThreadAsserter.assertNow();
            ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i2);
            R$dimen.assertNotNull(reactShadowNode, "Root node with tag " + i2 + " doesn't exist");
            createShadowNodeInstance.setReactTag(i);
            createShadowNodeInstance.setViewClassName(str);
            createShadowNodeInstance.setRootTag(reactShadowNode.getReactTag());
            createShadowNodeInstance.setThemedContext(reactShadowNode.getThemedContext());
            ShadowNodeRegistry shadowNodeRegistry2 = uIImplementation.mShadowNodeRegistry;
            shadowNodeRegistry2.mThreadAsserter.assertNow();
            shadowNodeRegistry2.mTagsToCSSNodes.put(createShadowNodeInstance.getReactTag(), createShadowNodeInstance);
            ReactStylesDiffMap reactStylesDiffMap = null;
            if (readableMap != null) {
                reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
                createShadowNodeInstance.updateProperties(reactStylesDiffMap);
            }
            uIImplementation.handleCreateView(createShadowNodeInstance, reactStylesDiffMap);
        }
    }

    @ReactMethod
    public void dismissPopupMenu() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.DismissPopupMenuOperation(null));
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.assertViewExists(i, "dispatchViewManagerCommand");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.DispatchCommandOperation(i, i2, readableArray));
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int i, Dynamic dynamic, ReadableArray readableArray) {
        if (dynamic.getType() == ReadableType.Number) {
            int asInt = dynamic.asInt();
            UIManager uIManager = R$style.getUIManager(getReactApplicationContext(), R$style.getUIManagerType(i));
            if (uIManager != null) {
                uIManager.dispatchCommand(i, asInt, readableArray);
            }
        } else if (dynamic.getType() == ReadableType.String) {
            String asString = dynamic.asString();
            UIManager uIManager2 = R$style.getUIManager(getReactApplicationContext(), R$style.getUIManagerType(i));
            if (uIManager2 != null) {
                uIManager2.dispatchCommand(i, asString, readableArray);
            }
        }
    }

    @ReactMethod
    public void findSubviewIn(int i, ReadableArray readableArray, Callback callback) {
        UIImplementation uIImplementation = this.mUIImplementation;
        float round = Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0)));
        float round2 = Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1)));
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.FindTargetForTouchOperation(i, round, round2, callback, null));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        Map<String, WritableMap> map = this.mViewManagerConstantsCache;
        if (map == null || !map.containsKey(str)) {
            return computeConstantsForViewManager(str);
        }
        WritableMap writableMap = this.mViewManagerConstantsCache.get(str);
        int i = this.mViewManagerConstantsCacheSize - 1;
        this.mViewManagerConstantsCacheSize = i;
        if (i <= 0) {
            this.mViewManagerConstantsCache = null;
        }
        return writableMap;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return Arguments.makeNativeMap(R$style.of("bubblingEventTypes", R$style.getBubblingEventTypeConstants(), "directEventTypes", R$style.getDirectEventTypeConstants()));
    }

    public CustomEventNamesResolver getDirectEventNamesResolver() {
        return new AnonymousClass1();
    }

    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        Objects.requireNonNull(uIViewOperationQueue);
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchCommitStartTime));
        hashMap.put("CommitEndTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchCommitEndTime));
        hashMap.put("LayoutTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchRunStartTime));
        hashMap.put("RunEndTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchRunEndTime));
        hashMap.put("BatchedExecutionTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchBatchedExecutionTime));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(uIViewOperationQueue.mProfiledBatchNonBatchedExecutionTime));
        hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(uIViewOperationQueue.mThreadCpuTime));
        hashMap.put("CreateViewCount", Long.valueOf(uIViewOperationQueue.mCreateViewCount));
        hashMap.put("UpdatePropsCount", Long.valueOf(uIViewOperationQueue.mUpdatePropertiesOperationCount));
        return hashMap;
    }

    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }

    @Deprecated
    public ViewManagerRegistry getViewManagerRegistry_DO_NOT_USE() {
        return this.mViewManagerRegistry;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
        this.mEventDispatcher.mReactEventEmitter.register(1, (RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class));
    }

    public void invalidateNodeLayout(int i) {
        ShadowNodeRegistry shadowNodeRegistry = this.mUIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode == null) {
            FLog.w("ReactNative", "Warning : attempted to dirty a non-existent react shadow node. reactTag=" + i);
            return;
        }
        reactShadowNode.dirty();
        this.mUIImplementation.dispatchViewUpdates(-1);
    }

    @ReactMethod
    public void manageChildren(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (DEBUG) {
            FLog.d("ReactNative", "(UIManager.manageChildren) tag: " + i + ", moveFrom: " + readableArray + ", moveTo: " + readableArray2 + ", addTags: " + readableArray3 + ", atIndices: " + readableArray4 + ", removeFrom: " + readableArray5);
            int i2 = PrinterHolder.$r8$clinit;
            DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.UI_MANAGER;
        }
        this.mUIImplementation.manageChildren(i, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @ReactMethod
    public void measure(int i, Callback callback) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.MeasureOperation(i, callback, null));
    }

    @ReactMethod
    public void measureInWindow(int i, Callback callback) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.MeasureInWindowOperation(i, callback, null));
    }

    @ReactMethod
    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        Objects.requireNonNull(uIImplementation);
        try {
            uIImplementation.measureLayout(i, i2, uIImplementation.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    @Deprecated
    public void measureLayoutRelativeToParent(int i, Callback callback, Callback callback2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        Objects.requireNonNull(uIImplementation);
        try {
            uIImplementation.measureLayoutRelativeToParent(i, uIImplementation.mMeasureBuffer);
            callback2.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(uIImplementation.mMeasureBuffer[3])));
        } catch (IllegalViewOperationException e) {
            callback.invoke(e.getMessage());
        }
    }

    @Override // com.facebook.react.bridge.OnBatchCompleteListener
    public void onBatchComplete() {
        int i = this.mBatchId;
        this.mBatchId = i + 1;
        for (UIManagerModuleListener uIManagerModuleListener : this.mListeners) {
            uIManagerModuleListener.willDispatchViewUpdates(this);
        }
        try {
            this.mUIImplementation.dispatchViewUpdates(i);
        } finally {
            Trace.endSection();
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        final EventDispatcher eventDispatcher = this.mEventDispatcher;
        Objects.requireNonNull(eventDispatcher);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcher.2
            @Override // java.lang.Runnable
            public void run() {
                EventDispatcher.this.stopFrameCallback();
            }
        });
        getReactApplicationContext().unregisterComponentCallbacks(this.mMemoryTrimCallback);
        YogaNodePool.get().clear();
        Map<Class<?>, ViewManagerPropertyUpdater.ViewManagerSetter<?, ?>> map = ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP;
        ViewManagersPropertyCache.CLASS_PROPS_CACHE.clear();
        ViewManagersPropertyCache.EMPTY_PROPS_MAP.clear();
        ViewManagerPropertyUpdater.VIEW_MANAGER_SETTER_MAP.clear();
        ViewManagerPropertyUpdater.SHADOW_NODE_SETTER_MAP.clear();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        Objects.requireNonNull(this.mUIImplementation);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mIsDispatchUIFrameCallbackEnqueued = false;
        ReactChoreographer.getInstance().removeFrameCallback$enumunboxing$(2, uIViewOperationQueue.mDispatchUIFrameCallback);
        uIViewOperationQueue.flushPendingBatches();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mIsDispatchUIFrameCallbackEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback$enumunboxing$(2, uIViewOperationQueue.mDispatchUIFrameCallback);
    }

    @ReactMethod
    @Deprecated
    public void playTouchSound() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            audioManager.playSoundEffect(0);
        }
    }

    @Deprecated
    public void preComputeConstantsForViewManager(List<String> list) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : list) {
            WritableMap computeConstantsForViewManager = computeConstantsForViewManager(str);
            if (computeConstantsForViewManager != null) {
                arrayMap.put(str, computeConstantsForViewManager);
            }
        }
        this.mViewManagerConstantsCacheSize = list.size();
        this.mViewManagerConstantsCache = Collections.unmodifiableMap(arrayMap);
    }

    public void prependUIBlock(UIBlock uIBlock) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(0, new UIViewOperationQueue.UIBlockOperation(uIBlock));
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mIsProfilingNextBatch = true;
        uIViewOperationQueue.mProfiledBatchCommitStartTime = 0L;
        uIViewOperationQueue.mCreateViewCount = 0L;
        uIViewOperationQueue.mUpdatePropertiesOperationCount = 0L;
    }

    @ReactMethod
    public void removeRootView(int i) {
        UIImplementation uIImplementation = this.mUIImplementation;
        synchronized (uIImplementation.uiImplementationThreadLock) {
            uIImplementation.mShadowNodeRegistry.removeRootNode(i);
        }
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.RemoveRootViewOperation(i));
    }

    @ReactMethod
    @Deprecated
    public void removeSubviewsFromContainerWithID(int i) {
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i2 = 0; i2 < reactShadowNode.getChildCount(); i2++) {
                createArray.pushInt(i2);
            }
            uIImplementation.manageChildren(i, null, null, null, null, createArray);
            return;
        }
        throw new IllegalViewOperationException(GeneratedOutlineSupport.outline3("Trying to remove subviews of an unknown view tag: ", i));
    }

    public void removeUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.remove(uIManagerModuleListener);
    }

    @ReactMethod
    @Deprecated
    public void replaceExistingNonRootView(int i, int i2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        if (!shadowNodeRegistry.mRootTags.get(i)) {
            ShadowNodeRegistry shadowNodeRegistry2 = uIImplementation.mShadowNodeRegistry;
            shadowNodeRegistry2.mThreadAsserter.assertNow();
            if (!shadowNodeRegistry2.mRootTags.get(i2)) {
                ShadowNodeRegistry shadowNodeRegistry3 = uIImplementation.mShadowNodeRegistry;
                shadowNodeRegistry3.mThreadAsserter.assertNow();
                ReactShadowNode reactShadowNode = shadowNodeRegistry3.mTagsToCSSNodes.get(i);
                if (reactShadowNode != null) {
                    ReactShadowNode parent = reactShadowNode.getParent();
                    if (parent != null) {
                        int indexOf = parent.indexOf(reactShadowNode);
                        if (indexOf >= 0) {
                            WritableArray createArray = Arguments.createArray();
                            createArray.pushInt(i2);
                            WritableArray createArray2 = Arguments.createArray();
                            createArray2.pushInt(indexOf);
                            WritableArray createArray3 = Arguments.createArray();
                            createArray3.pushInt(indexOf);
                            uIImplementation.manageChildren(parent.getReactTag(), null, null, createArray, createArray2, createArray3);
                            return;
                        }
                        throw new IllegalStateException("Didn't find child tag in parent");
                    }
                    throw new IllegalViewOperationException(GeneratedOutlineSupport.outline3("Node is not attached to a parent: ", i));
                }
                throw new IllegalViewOperationException(GeneratedOutlineSupport.outline3("Trying to replace unknown view tag: ", i));
            }
        }
        throw new IllegalViewOperationException("Trying to add or replace a root tag!");
    }

    public int resolveRootTagFromReactTag(int i) {
        boolean z = true;
        int i2 = 0;
        if (i % 10 != 1) {
            z = false;
        }
        if (z) {
            return i;
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        if (shadowNodeRegistry.mRootTags.get(i)) {
            return i;
        }
        ShadowNodeRegistry shadowNodeRegistry2 = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry2.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry2.mTagsToCSSNodes.get(i);
        if (reactShadowNode != null) {
            i2 = reactShadowNode.getRootTag();
        } else {
            FLog.w("ReactNative", "Warning : attempted to resolve a non-existent react shadow node. reactTag=" + i);
        }
        return i2;
    }

    public View resolveView(int i) {
        UiThreadUtil.assertOnUiThread();
        return this.mUIImplementation.mOperationsQueue.mNativeViewHierarchyManager.resolveView(i);
    }

    @Override // com.facebook.react.bridge.UIManager
    @ReactMethod
    public void sendAccessibilityEvent(int i, int i2) {
        int uIManagerType = R$style.getUIManagerType(i);
        if (uIManagerType == 2) {
            UIManager uIManager = R$style.getUIManager(getReactApplicationContext(), uIManagerType);
            if (uIManager != null) {
                uIManager.sendAccessibilityEvent(i, i2);
                return;
            }
            return;
        }
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.SendAccessibilityEvent(i, i2, null));
    }

    @Override // com.facebook.react.bridge.UIManager
    public void setAllowImmediateUIOperationExecution(boolean z) {
    }

    @ReactMethod
    public void setChildren(int i, ReadableArray readableArray) {
        if (DEBUG) {
            FLog.d("ReactNative", "(UIManager.setChildren) tag: " + i + ", children: " + readableArray);
            int i2 = PrinterHolder.$r8$clinit;
            DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.UI_MANAGER;
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        synchronized (uIImplementation.uiImplementationThreadLock) {
            ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
            shadowNodeRegistry.mThreadAsserter.assertNow();
            ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
            for (int i3 = 0; i3 < readableArray.size(); i3++) {
                ReactShadowNode node = uIImplementation.mShadowNodeRegistry.getNode(readableArray.getInt(i3));
                if (node != null) {
                    reactShadowNode.addChildAt(node, i3);
                } else {
                    throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i3));
                }
            }
            NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer = uIImplementation.mNativeViewHierarchyOptimizer;
            Objects.requireNonNull(nativeViewHierarchyOptimizer);
            for (int i4 = 0; i4 < readableArray.size(); i4++) {
                nativeViewHierarchyOptimizer.addNodeToNode(reactShadowNode, nativeViewHierarchyOptimizer.mShadowNodeRegistry.getNode(readableArray.getInt(i4)), i4);
            }
        }
    }

    @ReactMethod
    public void setJSResponder(int i, boolean z) {
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode != null) {
            while (reactShadowNode.getNativeKind$enumunboxing$() == 3) {
                reactShadowNode = reactShadowNode.getParent();
            }
            UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
            uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.ChangeJSResponderOperation(reactShadowNode.getReactTag(), i, false, z));
        }
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        UIViewOperationQueue uIViewOperationQueue = this.mUIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.SetLayoutAnimationEnabledOperation(z, null));
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mUIImplementation.mOperationsQueue.mViewHierarchyUpdateDebugListener = notThreadSafeViewHierarchyUpdateDebugListener;
    }

    public void setViewLocalData(final int i, final Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnUiQueueThread();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) { // from class: com.facebook.react.uimanager.UIManagerModule.2
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIImplementation uIImplementation = UIManagerModule.this.mUIImplementation;
                int i2 = i;
                Object obj2 = obj;
                ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
                shadowNodeRegistry.mThreadAsserter.assertNow();
                ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i2);
                if (reactShadowNode == null) {
                    FLog.w("ReactNative", "Attempt to set local data for view with unknown tag: " + i2);
                    return;
                }
                reactShadowNode.setLocalData(obj2);
                if (uIImplementation.mOperationsQueue.mOperations.isEmpty()) {
                    uIImplementation.dispatchViewUpdates(-1);
                }
            }
        });
    }

    @ReactMethod
    public void showPopupMenu(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.assertViewExists(i, "showPopupMenu");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.ShowPopupMenuOperation(i, readableArray, callback, callback2));
    }

    @Override // com.facebook.react.bridge.UIManager
    public void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap) {
        int uIManagerType = R$style.getUIManagerType(i);
        if (uIManagerType == 2) {
            UIManager uIManager = R$style.getUIManager(getReactApplicationContext(), uIManagerType);
            if (uIManager != null) {
                uIManager.synchronouslyUpdateViewOnUIThread(i, readableMap);
                return;
            }
            return;
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
        Objects.requireNonNull(uIImplementation);
        UiThreadUtil.assertOnUiThread();
        uIImplementation.mOperationsQueue.mNativeViewHierarchyManager.updateProperties(i, reactStylesDiffMap);
    }

    public void updateNodeSize(int i, int i2, int i3) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode == null) {
            FLog.w("ReactNative", "Tried to update size of non-existent tag: " + i);
            return;
        }
        reactShadowNode.setStyleWidth(i2);
        reactShadowNode.setStyleHeight(i3);
        if (uIImplementation.mOperationsQueue.mOperations.isEmpty()) {
            uIImplementation.dispatchViewUpdates(-1);
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(final int i, final int i2, final int i3) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) { // from class: com.facebook.react.uimanager.UIManagerModule.4
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIImplementation uIImplementation = UIManagerModule.this.mUIImplementation;
                int i4 = i;
                int i5 = i2;
                int i6 = i3;
                ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
                shadowNodeRegistry.mThreadAsserter.assertNow();
                ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i4);
                if (reactShadowNode == null) {
                    FLog.w("ReactNative", "Tried to update non-existent root tag: " + i4);
                } else {
                    reactShadowNode.setMeasureSpecs(i5, i6);
                }
                UIManagerModule.this.mUIImplementation.dispatchViewUpdates(-1);
            }
        });
    }

    @ReactMethod
    public void updateView(final int i, String str, final ReadableMap readableMap) {
        final UIManager uIManager;
        if (DEBUG) {
            String str2 = "(UIManager.updateView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.UI_MANAGER;
        }
        int uIManagerType = R$style.getUIManagerType(i);
        if (uIManagerType == 2) {
            ReactApplicationContext reactApplicationContext = getReactApplicationContext();
            if (reactApplicationContext.hasActiveCatalystInstance() && (uIManager = R$style.getUIManager(reactApplicationContext, uIManagerType)) != null) {
                reactApplicationContext.runOnUiQueueThread(new Runnable(this) { // from class: com.facebook.react.uimanager.UIManagerModule.3
                    @Override // java.lang.Runnable
                    public void run() {
                        uIManager.synchronouslyUpdateViewOnUIThread(i, readableMap);
                    }
                });
                return;
            }
            return;
        }
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.mViewManagers.get(str);
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        if (reactShadowNode == null) {
            throw new IllegalViewOperationException(GeneratedOutlineSupport.outline3("Trying to update non-existent view with tag ", i));
        } else if (readableMap != null) {
            ReactStylesDiffMap reactStylesDiffMap = new ReactStylesDiffMap(readableMap);
            reactShadowNode.updateProperties(reactStylesDiffMap);
            if (!reactShadowNode.isVirtual()) {
                NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer = uIImplementation.mNativeViewHierarchyOptimizer;
                Objects.requireNonNull(nativeViewHierarchyOptimizer);
                if (reactShadowNode.isLayoutOnly() && !NativeViewHierarchyOptimizer.isLayoutOnlyAndCollapsable(reactStylesDiffMap)) {
                    nativeViewHierarchyOptimizer.transitionLayoutOnlyViewToNativeView(reactShadowNode, reactStylesDiffMap);
                } else if (!reactShadowNode.isLayoutOnly()) {
                    UIViewOperationQueue uIViewOperationQueue = nativeViewHierarchyOptimizer.mUIViewOperationQueue;
                    int reactTag = reactShadowNode.getReactTag();
                    uIViewOperationQueue.mUpdatePropertiesOperationCount++;
                    uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.UpdatePropertiesOperation(reactTag, reactStylesDiffMap, null));
                }
            }
        }
    }

    @ReactMethod
    @Deprecated
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        UIImplementation uIImplementation = this.mUIImplementation;
        ShadowNodeRegistry shadowNodeRegistry = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode = shadowNodeRegistry.mTagsToCSSNodes.get(i);
        ShadowNodeRegistry shadowNodeRegistry2 = uIImplementation.mShadowNodeRegistry;
        shadowNodeRegistry2.mThreadAsserter.assertNow();
        ReactShadowNode reactShadowNode2 = shadowNodeRegistry2.mTagsToCSSNodes.get(i2);
        if (reactShadowNode == null || reactShadowNode2 == null) {
            callback.invoke(Boolean.FALSE);
        } else {
            callback.invoke(Boolean.valueOf(reactShadowNode.isDescendantOf(reactShadowNode2)));
        }
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int i) {
        this(reactApplicationContext, list, new UIImplementationProvider(), i);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, UIImplementationProvider uIImplementationProvider, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new MemoryTrimCallback(this, null);
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        R$style.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcher eventDispatcher = new EventDispatcher(reactApplicationContext);
        this.mEventDispatcher = eventDispatcher;
        this.mModuleConstants = createConstants(viewManagerResolver);
        this.mCustomDirectEvents = R$style.getDirectEventTypeConstants();
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(viewManagerResolver);
        this.mViewManagerRegistry = viewManagerRegistry;
        Objects.requireNonNull(uIImplementationProvider);
        this.mUIImplementation = new UIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int i, String str, ReadableArray readableArray) {
        UIImplementation uIImplementation = this.mUIImplementation;
        uIImplementation.assertViewExists(i, "dispatchViewManagerCommand");
        UIViewOperationQueue uIViewOperationQueue = uIImplementation.mOperationsQueue;
        uIViewOperationQueue.mOperations.add(new UIViewOperationQueue.DispatchStringCommandOperation(i, str, readableArray));
    }

    private static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        try {
            return PixelUtil.createConstants(list, map, map2);
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, UIImplementationProvider uIImplementationProvider, int i) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new MemoryTrimCallback(this, null);
        this.mListeners = new ArrayList();
        this.mBatchId = 0;
        R$style.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcher eventDispatcher = new EventDispatcher(reactApplicationContext);
        this.mEventDispatcher = eventDispatcher;
        HashMap hashMap = new HashMap();
        this.mCustomDirectEvents = hashMap;
        this.mModuleConstants = createConstants(list, null, hashMap);
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(list);
        this.mViewManagerRegistry = viewManagerRegistry;
        Objects.requireNonNull(uIImplementationProvider);
        this.mUIImplementation = new UIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcher, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public <T extends View> int addRootView(T t) {
        return addRootView(t, null, null);
    }
}

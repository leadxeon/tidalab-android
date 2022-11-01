package com.facebook.react.bridge;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Trace;
import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.systrace.TraceListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
@DoNotStrip
/* loaded from: classes.dex */
public class CatalystInstanceImpl implements CatalystInstance {
    private static final AtomicInteger sNextInstanceIdForTrace = new AtomicInteger(1);
    private volatile boolean mAcceptCalls;
    private final CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener> mBridgeIdleListeners;
    private volatile boolean mDestroyed;
    private final HybridData mHybridData;
    private boolean mInitialized;
    private boolean mJSBundleHasLoaded;
    private final JSBundleLoader mJSBundleLoader;
    private final ArrayList<PendingJSCall> mJSCallsPendingInit;
    private final Object mJSCallsPendingInitLock;
    private final JSIModuleRegistry mJSIModuleRegistry;
    private final JavaScriptModuleRegistry mJSModuleRegistry;
    private volatile boolean mJSThreadDestructionComplete;
    private JavaScriptContextHolder mJavaScriptContextHolder;
    private final String mJsPendingCallsTitleForTrace;
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final NativeModuleRegistry mNativeModuleRegistry;
    private final MessageQueueThread mNativeModulesQueueThread;
    private volatile boolean mNativeModulesThreadDestructionComplete;
    private final AtomicInteger mPendingJSCalls;
    private final ReactQueueConfigurationImpl mReactQueueConfiguration;
    private String mSourceURL;
    private final TraceListener mTraceListener;
    private JSIModule mTurboModuleManagerJSIModule;
    private volatile TurboModuleRegistry mTurboModuleRegistry;

    /* renamed from: com.facebook.react.bridge.CatalystInstanceImpl$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements Runnable {

        /* renamed from: com.facebook.react.bridge.CatalystInstanceImpl$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC00031 implements Runnable {
            public RunnableC00031() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (CatalystInstanceImpl.this.mTurboModuleManagerJSIModule != null) {
                    CatalystInstanceImpl.this.mTurboModuleManagerJSIModule.onCatalystInstanceDestroy();
                }
                CatalystInstanceImpl.this.getReactQueueConfiguration().getUIQueueThread().runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.1.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AsyncTask.execute(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.1.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                CatalystInstanceImpl.this.mJavaScriptContextHolder.clear();
                                CatalystInstanceImpl.this.mHybridData.resetNative();
                                CatalystInstanceImpl.this.getReactQueueConfiguration().destroy();
                                Log.d("ReactNative", "CatalystInstanceImpl.destroy() end");
                                ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_END);
                            }
                        });
                    }
                });
            }
        }

        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CatalystInstanceImpl.this.mNativeModuleRegistry.notifyJSInstanceDestroy();
            CatalystInstanceImpl.this.mJSIModuleRegistry.notifyJSInstanceDestroy();
            boolean z = false;
            if (CatalystInstanceImpl.this.mPendingJSCalls.getAndSet(0) == 0) {
                z = true;
            }
            if (!CatalystInstanceImpl.this.mBridgeIdleListeners.isEmpty()) {
                Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                while (it.hasNext()) {
                    NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = (NotThreadSafeBridgeIdleDebugListener) it.next();
                    if (!z) {
                        notThreadSafeBridgeIdleDebugListener.onTransitionToBridgeIdle();
                    }
                    notThreadSafeBridgeIdleDebugListener.onBridgeDestroyed();
                }
            }
            CatalystInstanceImpl.this.getReactQueueConfiguration().getJSQueueThread().runOnQueue(new RunnableC00031());
        }
    }

    /* loaded from: classes.dex */
    public static class BridgeCallback implements ReactCallback {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        public BridgeCallback(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        @Override // com.facebook.react.bridge.ReactCallback
        public void decrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.decrementPendingJSCalls();
            }
        }

        @Override // com.facebook.react.bridge.ReactCallback
        public void incrementPendingJSCalls() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.incrementPendingJSCalls();
            }
        }

        @Override // com.facebook.react.bridge.ReactCallback
        public void onBatchComplete() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                catalystInstanceImpl.mNativeModuleRegistry.onBatchComplete();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private JSBundleLoader mJSBundleLoader;
        private JavaScriptExecutor mJSExecutor;
        private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
        private ReactQueueConfigurationSpec mReactQueueConfigurationSpec;
        private NativeModuleRegistry mRegistry;

        public CatalystInstanceImpl build() {
            ReactQueueConfigurationSpec reactQueueConfigurationSpec = this.mReactQueueConfigurationSpec;
            R$dimen.assertNotNull(reactQueueConfigurationSpec);
            ReactQueueConfigurationSpec reactQueueConfigurationSpec2 = reactQueueConfigurationSpec;
            JavaScriptExecutor javaScriptExecutor = this.mJSExecutor;
            R$dimen.assertNotNull(javaScriptExecutor);
            JavaScriptExecutor javaScriptExecutor2 = javaScriptExecutor;
            NativeModuleRegistry nativeModuleRegistry = this.mRegistry;
            R$dimen.assertNotNull(nativeModuleRegistry);
            NativeModuleRegistry nativeModuleRegistry2 = nativeModuleRegistry;
            JSBundleLoader jSBundleLoader = this.mJSBundleLoader;
            R$dimen.assertNotNull(jSBundleLoader);
            JSBundleLoader jSBundleLoader2 = jSBundleLoader;
            NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
            R$dimen.assertNotNull(nativeModuleCallExceptionHandler);
            return new CatalystInstanceImpl(reactQueueConfigurationSpec2, javaScriptExecutor2, nativeModuleRegistry2, jSBundleLoader2, nativeModuleCallExceptionHandler, null);
        }

        public Builder setJSBundleLoader(JSBundleLoader jSBundleLoader) {
            this.mJSBundleLoader = jSBundleLoader;
            return this;
        }

        public Builder setJSExecutor(JavaScriptExecutor javaScriptExecutor) {
            this.mJSExecutor = javaScriptExecutor;
            return this;
        }

        public Builder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
            this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
            return this;
        }

        public Builder setReactQueueConfigurationSpec(ReactQueueConfigurationSpec reactQueueConfigurationSpec) {
            this.mReactQueueConfigurationSpec = reactQueueConfigurationSpec;
            return this;
        }

        public Builder setRegistry(NativeModuleRegistry nativeModuleRegistry) {
            this.mRegistry = nativeModuleRegistry;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class JSProfilerTraceListener implements TraceListener {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        public JSProfilerTraceListener(CatalystInstanceImpl catalystInstanceImpl) {
            this.mOuter = new WeakReference<>(catalystInstanceImpl);
        }

        public void onTraceStarted() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(true);
            }
        }

        public void onTraceStopped() {
            CatalystInstanceImpl catalystInstanceImpl = this.mOuter.get();
            if (catalystInstanceImpl != null) {
                ((Systrace) catalystInstanceImpl.getJSModule(Systrace.class)).setEnabled(false);
            }
        }
    }

    /* loaded from: classes.dex */
    public class NativeExceptionHandler implements QueueThreadExceptionHandler {
        private NativeExceptionHandler() {
        }

        @Override // com.facebook.react.bridge.queue.QueueThreadExceptionHandler
        public void handleException(Exception exc) {
            CatalystInstanceImpl.this.onNativeException(exc);
        }

        public /* synthetic */ NativeExceptionHandler(CatalystInstanceImpl catalystInstanceImpl, AnonymousClass1 r2) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static class PendingJSCall {
        public NativeArray mArguments;
        public String mMethod;
        public String mModule;

        public PendingJSCall(String str, String str2, NativeArray nativeArray) {
            this.mModule = str;
            this.mMethod = str2;
            this.mArguments = nativeArray;
        }

        public void call(CatalystInstanceImpl catalystInstanceImpl) {
            NativeArray nativeArray = this.mArguments;
            if (nativeArray == null) {
                nativeArray = new WritableNativeArray();
            }
            catalystInstanceImpl.jniCallJSFunction(this.mModule, this.mMethod, nativeArray);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mModule);
            sb.append(".");
            sb.append(this.mMethod);
            sb.append("(");
            NativeArray nativeArray = this.mArguments;
            return GeneratedOutlineSupport.outline11(sb, nativeArray == null ? HttpUrl.FRAGMENT_ENCODE_SET : nativeArray.toString(), ")");
        }
    }

    static {
        ReactBridge.staticInit();
    }

    public /* synthetic */ CatalystInstanceImpl(ReactQueueConfigurationSpec reactQueueConfigurationSpec, JavaScriptExecutor javaScriptExecutor, NativeModuleRegistry nativeModuleRegistry, JSBundleLoader jSBundleLoader, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, AnonymousClass1 r6) {
        this(reactQueueConfigurationSpec, javaScriptExecutor, nativeModuleRegistry, jSBundleLoader, nativeModuleCallExceptionHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementPendingJSCalls() {
        if ((this.mPendingJSCalls.decrementAndGet() == 0) && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeIdle();
                    }
                }
            });
        }
    }

    private native long getJavaScriptContext();

    private <T extends NativeModule> String getNameFromAnnotation(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return reactModule.name();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Could not find @ReactModule annotation in ");
        outline13.append(cls.getCanonicalName());
        throw new IllegalArgumentException(outline13.toString());
    }

    private TurboModuleRegistry getTurboModuleRegistry() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementPendingJSCalls() {
        if ((this.mPendingJSCalls.getAndIncrement() == 0) && !this.mBridgeIdleListeners.isEmpty()) {
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                    while (it.hasNext()) {
                        ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeBusy();
                    }
                }
            });
        }
    }

    private static native HybridData initHybrid();

    private native void initializeBridge(ReactCallback reactCallback, JavaScriptExecutor javaScriptExecutor, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniCallJSCallback(int i, NativeArray nativeArray);

    /* JADX INFO: Access modifiers changed from: private */
    public native void jniCallJSFunction(String str, String str2, NativeArray nativeArray);

    private native void jniExtendNativeModules(Collection<JavaModuleWrapper> collection, Collection<ModuleHolder> collection2);

    private native void jniHandleMemoryPressure(int i);

    private native void jniLoadScriptFromAssets(AssetManager assetManager, String str, boolean z);

    private native void jniLoadScriptFromFile(String str, String str2, boolean z);

    private native void jniRegisterSegment(int i, String str);

    private native void jniSetSourceURL(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public void onNativeException(Exception exc) {
        this.mNativeModuleCallExceptionHandler.handleException(exc);
        this.mReactQueueConfiguration.getUIQueueThread().runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.7
            @Override // java.lang.Runnable
            public void run() {
                CatalystInstanceImpl.this.destroy();
            }
        });
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.add(notThreadSafeBridgeIdleDebugListener);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void addJSIModules(List<JSIModuleSpec> list) {
        this.mJSIModuleRegistry.registerModules(list);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void callFunction(String str, String str2, NativeArray nativeArray) {
        callFunction(new PendingJSCall(str, str2, nativeArray));
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void destroy() {
        Log.d("ReactNative", "CatalystInstanceImpl.destroy() start");
        UiThreadUtil.assertOnUiThread();
        destroyV1();
    }

    public void destroyV1() {
        Log.d("ReactNative", "CatalystInstanceImpl.destroyV1() start");
        UiThreadUtil.assertOnUiThread();
        if (!this.mDestroyed) {
            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_START);
            this.mDestroyed = true;
            this.mNativeModulesQueueThread.runOnQueue(new AnonymousClass1());
        }
    }

    public void destroyV2() {
        Log.d("ReactNative", "CatalystInstanceImpl.destroyV2() start");
        UiThreadUtil.assertOnUiThread();
        if (!this.mDestroyed) {
            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_START);
            this.mDestroyed = true;
            this.mNativeModulesThreadDestructionComplete = false;
            this.mJSThreadDestructionComplete = false;
            this.mNativeModulesQueueThread.runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d("CatalystInstanceImpl", ".destroy on native modules thread");
                    CatalystInstanceImpl.this.mNativeModuleRegistry.notifyJSInstanceDestroy();
                    CatalystInstanceImpl.this.mJSIModuleRegistry.notifyJSInstanceDestroy();
                    boolean z = false;
                    if (CatalystInstanceImpl.this.mPendingJSCalls.getAndSet(0) == 0) {
                        z = true;
                    }
                    if (!CatalystInstanceImpl.this.mBridgeIdleListeners.isEmpty()) {
                        Iterator it = CatalystInstanceImpl.this.mBridgeIdleListeners.iterator();
                        while (it.hasNext()) {
                            NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = (NotThreadSafeBridgeIdleDebugListener) it.next();
                            if (!z) {
                                notThreadSafeBridgeIdleDebugListener.onTransitionToBridgeIdle();
                            }
                            notThreadSafeBridgeIdleDebugListener.onBridgeDestroyed();
                        }
                    }
                    CatalystInstanceImpl.this.mNativeModulesThreadDestructionComplete = true;
                    Log.d("CatalystInstanceImpl", ".destroy on native modules thread finished");
                }
            });
            getReactQueueConfiguration().getJSQueueThread().runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    Log.d("CatalystInstanceImpl", ".destroy on JS thread");
                    if (CatalystInstanceImpl.this.mTurboModuleManagerJSIModule != null) {
                        CatalystInstanceImpl.this.mTurboModuleManagerJSIModule.onCatalystInstanceDestroy();
                    }
                    CatalystInstanceImpl.this.mJSThreadDestructionComplete = true;
                    Log.d("CatalystInstanceImpl", ".destroy on JS thread finished");
                }
            });
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                if (!this.mNativeModulesThreadDestructionComplete || !this.mJSThreadDestructionComplete) {
                    if (System.currentTimeMillis() - currentTimeMillis > 100) {
                        Log.w("ReactNative", "CatalystInstanceImpl.destroy() timed out waiting for Native Modules and JS thread teardown");
                        break;
                    }
                } else {
                    break;
                }
            }
            this.mJavaScriptContextHolder.clear();
            this.mHybridData.resetNative();
            getReactQueueConfiguration().destroy();
            Log.d("ReactNative", "CatalystInstanceImpl.destroy() end");
            ReactMarker.logMarker(ReactMarkerConstants.DESTROY_CATALYST_INSTANCE_END);
        }
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void extendNativeModules(NativeModuleRegistry nativeModuleRegistry) {
        this.mNativeModuleRegistry.registerModules(nativeModuleRegistry);
        jniExtendNativeModules(nativeModuleRegistry.getJavaModules(this), nativeModuleRegistry.getCxxModules());
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public native CallInvokerHolderImpl getJSCallInvokerHolder();

    @Override // com.facebook.react.bridge.CatalystInstance
    public JSIModule getJSIModule(JSIModuleType jSIModuleType) {
        return this.mJSIModuleRegistry.getModule(jSIModuleType);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        return (T) this.mJSModuleRegistry.getJavaScriptModule(this, cls);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mJavaScriptContextHolder;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public native CallInvokerHolderImpl getNativeCallInvokerHolder();

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        return (T) getNativeModule(getNameFromAnnotation(cls));
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public Collection<NativeModule> getNativeModules() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mNativeModuleRegistry.getAllModules());
        if (getTurboModuleRegistry() != null) {
            Iterator<TurboModule> it = getTurboModuleRegistry().getModules().iterator();
            while (it.hasNext()) {
                arrayList.add((NativeModule) it.next());
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public String getSourceURL() {
        return this.mSourceURL;
    }

    @Override // com.facebook.react.bridge.MemoryPressureListener
    public void handleMemoryPressure(int i) {
        if (!this.mDestroyed) {
            jniHandleMemoryPressure(i);
        }
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        String nameFromAnnotation = getNameFromAnnotation(cls);
        if (getTurboModuleRegistry() == null || !getTurboModuleRegistry().hasModule(nameFromAnnotation)) {
            return this.mNativeModuleRegistry.hasModule(nameFromAnnotation);
        }
        return true;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public boolean hasRunJSBundle() {
        boolean z;
        synchronized (this.mJSCallsPendingInitLock) {
            z = this.mJSBundleHasLoaded && this.mAcceptCalls;
        }
        return z;
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void initialize() {
        Log.d("ReactNative", "CatalystInstanceImpl.initialize()");
        R$dimen.assertCondition(!this.mInitialized, "This catalyst instance has already been initialized");
        R$dimen.assertCondition(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mNativeModulesQueueThread.runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.CatalystInstanceImpl.4
            @Override // java.lang.Runnable
            public void run() {
                CatalystInstanceImpl.this.mNativeModuleRegistry.notifyJSInstanceInitialized();
            }
        });
    }

    @Override // com.facebook.react.bridge.CatalystInstance, com.facebook.react.bridge.JSInstance
    public void invokeCallback(int i, NativeArrayInterface nativeArrayInterface) {
        if (this.mDestroyed) {
            FLog.w("ReactNative", "Invoking JS callback after bridge has been destroyed.");
        } else {
            jniCallJSCallback(i, (NativeArray) nativeArrayInterface);
        }
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
        this.mSourceURL = str;
        jniLoadScriptFromAssets(assetManager, str, z);
    }

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void loadScriptFromFile(String str, String str2, boolean z) {
        this.mSourceURL = str2;
        jniLoadScriptFromFile(str, str2, z);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void registerSegment(int i, String str) {
        jniRegisterSegment(i, str);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener) {
        this.mBridgeIdleListeners.remove(notThreadSafeBridgeIdleDebugListener);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void runJSBundle() {
        Log.d("ReactNative", "CatalystInstanceImpl.runJSBundle()");
        R$dimen.assertCondition(!this.mJSBundleHasLoaded, "JS bundle was already loaded!");
        this.mJSBundleLoader.loadScript(this);
        synchronized (this.mJSCallsPendingInitLock) {
            this.mAcceptCalls = true;
            Iterator<PendingJSCall> it = this.mJSCallsPendingInit.iterator();
            while (it.hasNext()) {
                it.next().call(this);
            }
            this.mJSCallsPendingInit.clear();
            this.mJSBundleHasLoaded = true;
        }
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public native void setGlobalVariable(String str, String str2);

    @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
    public void setSourceURLs(String str, String str2) {
        this.mSourceURL = str;
        jniSetSourceURL(str2);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public void setTurboModuleManager(JSIModule jSIModule) {
        this.mTurboModuleRegistry = (TurboModuleRegistry) jSIModule;
        this.mTurboModuleManagerJSIModule = jSIModule;
    }

    private CatalystInstanceImpl(ReactQueueConfigurationSpec reactQueueConfigurationSpec, JavaScriptExecutor javaScriptExecutor, NativeModuleRegistry nativeModuleRegistry, JSBundleLoader jSBundleLoader, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mPendingJSCalls = new AtomicInteger(0);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("pending_js_calls_instance");
        outline13.append(sNextInstanceIdForTrace.getAndIncrement());
        this.mJsPendingCallsTitleForTrace = outline13.toString();
        this.mDestroyed = false;
        this.mNativeModulesThreadDestructionComplete = false;
        this.mJSThreadDestructionComplete = false;
        this.mJSCallsPendingInit = new ArrayList<>();
        this.mJSCallsPendingInitLock = new Object();
        this.mJSIModuleRegistry = new JSIModuleRegistry();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        this.mTurboModuleRegistry = null;
        this.mTurboModuleManagerJSIModule = null;
        Log.d("ReactNative", "Initializing React Xplat Bridge.");
        Trace.beginSection("createCatalystInstanceImpl");
        this.mHybridData = initHybrid();
        ReactQueueConfigurationImpl create = ReactQueueConfigurationImpl.create(reactQueueConfigurationSpec, new NativeExceptionHandler(this, null));
        this.mReactQueueConfiguration = create;
        this.mBridgeIdleListeners = new CopyOnWriteArrayList<>();
        this.mNativeModuleRegistry = nativeModuleRegistry;
        this.mJSModuleRegistry = new JavaScriptModuleRegistry();
        this.mJSBundleLoader = jSBundleLoader;
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        MessageQueueThread nativeModulesQueueThread = create.getNativeModulesQueueThread();
        this.mNativeModulesQueueThread = nativeModulesQueueThread;
        this.mTraceListener = new JSProfilerTraceListener(this);
        Trace.endSection();
        Log.d("ReactNative", "Initializing React Xplat Bridge before initializeBridge");
        Trace.beginSection("initializeCxxBridge");
        initializeBridge(new BridgeCallback(this), javaScriptExecutor, create.getJSQueueThread(), nativeModulesQueueThread, nativeModuleRegistry.getJavaModules(this), nativeModuleRegistry.getCxxModules());
        Log.d("ReactNative", "Initializing React Xplat Bridge after initializeBridge");
        Trace.endSection();
        this.mJavaScriptContextHolder = new JavaScriptContextHolder(getJavaScriptContext());
    }

    public void callFunction(PendingJSCall pendingJSCall) {
        if (this.mDestroyed) {
            String pendingJSCall2 = pendingJSCall.toString();
            FLog.w("ReactNative", "Calling JS function after bridge has been destroyed: " + pendingJSCall2);
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                if (!this.mAcceptCalls) {
                    this.mJSCallsPendingInit.add(pendingJSCall);
                    return;
                }
            }
        }
        pendingJSCall.call(this);
    }

    @Override // com.facebook.react.bridge.CatalystInstance
    public NativeModule getNativeModule(String str) {
        TurboModule module;
        if (getTurboModuleRegistry() == null || (module = getTurboModuleRegistry().getModule(str)) == null) {
            return this.mNativeModuleRegistry.getModule(str);
        }
        return (NativeModule) module;
    }
}

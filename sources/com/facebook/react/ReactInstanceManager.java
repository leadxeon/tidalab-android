package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.CatalystInstanceImpl;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NativeModuleRegistry;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.DisabledDevSupportManager;
import com.facebook.react.devsupport.ReactInstanceManagerDevHelper;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.modules.appregistry.AppRegistry;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.fabric.ReactFabric;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.soloader.SoLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class ReactInstanceManager {
    public final Context mApplicationContext;
    public final NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
    public final JSBundleLoader mBundleLoader;
    public volatile Thread mCreateReactContextThread;
    public Activity mCurrentActivity;
    public volatile ReactContext mCurrentReactContext;
    public DefaultHardwareBackBtnHandler mDefaultBackButtonImpl;
    public final DevSupportManager mDevSupportManager;
    public final JSIModulePackage mJSIModulePackage;
    public final String mJSMainModulePath;
    public final JavaScriptExecutorFactory mJavaScriptExecutorFactory;
    public volatile LifecycleState mLifecycleState;
    public final MemoryPressureRouter mMemoryPressureRouter;
    public final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    public final List<ReactPackage> mPackages;
    public ReactContextInitParams mPendingReactContextInitParams;
    public final boolean mUseDeveloperSupport;
    public List<ViewManager> mViewManagers;
    public final Set<ReactRoot> mAttachedReactRoots = Collections.synchronizedSet(new HashSet());
    public final Object mReactContextLock = new Object();
    public final Collection<ReactInstanceEventListener> mReactInstanceEventListeners = Collections.synchronizedList(new ArrayList());
    public volatile boolean mHasStartedCreatingInitialContext = false;
    public volatile Boolean mHasStartedDestroying = Boolean.FALSE;

    /* loaded from: classes.dex */
    public class ReactContextInitParams {
        public final JSBundleLoader mJsBundleLoader;
        public final JavaScriptExecutorFactory mJsExecutorFactory;

        public ReactContextInitParams(ReactInstanceManager reactInstanceManager, JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader) {
            R$dimen.assertNotNull(javaScriptExecutorFactory);
            this.mJsExecutorFactory = javaScriptExecutorFactory;
            R$dimen.assertNotNull(jSBundleLoader);
            this.mJsBundleLoader = jSBundleLoader;
        }
    }

    /* loaded from: classes.dex */
    public interface ReactInstanceEventListener {
        void onReactContextInitialized(ReactContext reactContext);
    }

    public ReactInstanceManager(Context context, Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, JSBundleLoader jSBundleLoader, String str, List<ReactPackage> list, boolean z, NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener, LifecycleState lifecycleState, UIImplementationProvider uIImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, RedBoxHandler redBoxHandler, boolean z2, DevBundleDownloadListener devBundleDownloadListener, int i, int i2, JSIModulePackage jSIModulePackage, Map<String, ?> map) {
        DevSupportManager devSupportManager;
        Log.d("ReactNative", "ReactInstanceManager.ctor()");
        boolean z3 = SoLoader.SYSTRACE_LIBRARY_LOADING;
        try {
            SoLoader.init(context, 0);
            R$style.initDisplayMetricsIfNotInitialized(context);
            this.mApplicationContext = context;
            this.mCurrentActivity = null;
            this.mDefaultBackButtonImpl = null;
            this.mJavaScriptExecutorFactory = javaScriptExecutorFactory;
            this.mBundleLoader = jSBundleLoader;
            this.mJSMainModulePath = str;
            ArrayList arrayList = new ArrayList();
            this.mPackages = arrayList;
            this.mUseDeveloperSupport = z;
            Trace.beginSection("ReactInstanceManager.initDevSupportManager");
            ReactInstanceManagerDevHelper reactInstanceManagerDevHelper = new ReactInstanceManagerDevHelper(this) { // from class: com.facebook.react.ReactInstanceManager.2
            };
            if (!z) {
                devSupportManager = new DisabledDevSupportManager();
            } else {
                try {
                    devSupportManager = (DevSupportManager) Class.forName("com.facebook.react.devsupport.DevSupportManagerImpl").getConstructor(Context.class, ReactInstanceManagerDevHelper.class, String.class, Boolean.TYPE, RedBoxHandler.class, DevBundleDownloadListener.class, Integer.TYPE, Map.class).newInstance(context, reactInstanceManagerDevHelper, str, Boolean.TRUE, redBoxHandler, null, Integer.valueOf(i), null);
                } catch (Exception e) {
                    throw new RuntimeException("Requested enabled DevSupportManager, but DevSupportManagerImpl class was not found or could not be created", e);
                }
            }
            this.mDevSupportManager = devSupportManager;
            Trace.endSection();
            this.mBridgeIdleDebugListener = null;
            this.mLifecycleState = lifecycleState;
            this.mMemoryPressureRouter = new MemoryPressureRouter(context);
            this.mNativeModuleCallExceptionHandler = null;
            synchronized (arrayList) {
                int i3 = PrinterHolder.$r8$clinit;
                DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.RN_CORE;
                arrayList.add(new CoreModulesPackage(this, new DefaultHardwareBackBtnHandler() { // from class: com.facebook.react.ReactInstanceManager.1
                    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
                    public void invokeDefaultOnBackPressed() {
                        ReactInstanceManager.this.invokeDefaultOnBackPressed();
                    }
                }, z2, i2));
                if (z) {
                    arrayList.add(new DebugCorePackage());
                }
                arrayList.addAll(list);
            }
            this.mJSIModulePackage = jSIModulePackage;
            if (ReactChoreographer.sInstance == null) {
                ReactChoreographer.sInstance = new ReactChoreographer();
            }
            if (z) {
                devSupportManager.startInspector();
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARN: Finally extract failed */
    public static ReactApplicationContext access$1000(ReactInstanceManager reactInstanceManager, JavaScriptExecutor javaScriptExecutor, JSBundleLoader jSBundleLoader) {
        Objects.requireNonNull(reactInstanceManager);
        Log.d("ReactNative", "ReactInstanceManager.createReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_REACT_CONTEXT_START, javaScriptExecutor.getName());
        ReactApplicationContext reactApplicationContext = new ReactApplicationContext(reactInstanceManager.mApplicationContext);
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = reactInstanceManager.mNativeModuleCallExceptionHandler;
        if (nativeModuleCallExceptionHandler == null) {
            nativeModuleCallExceptionHandler = reactInstanceManager.mDevSupportManager;
        }
        reactApplicationContext.setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
        List<ReactPackage> list = reactInstanceManager.mPackages;
        NativeModuleRegistryBuilder nativeModuleRegistryBuilder = new NativeModuleRegistryBuilder(reactApplicationContext, reactInstanceManager);
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_START);
        synchronized (reactInstanceManager.mPackages) {
            for (ReactPackage reactPackage : list) {
                Trace.beginSection("createAndProcessCustomReactPackage");
                reactInstanceManager.processPackage(reactPackage, nativeModuleRegistryBuilder);
                Trace.endSection();
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.PROCESS_PACKAGES_END);
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START);
        Trace.beginSection("buildNativeModuleRegistry");
        try {
            NativeModuleRegistry nativeModuleRegistry = new NativeModuleRegistry(nativeModuleRegistryBuilder.mReactApplicationContext, nativeModuleRegistryBuilder.mModules);
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
            CatalystInstanceImpl.Builder nativeModuleCallExceptionHandler2 = new CatalystInstanceImpl.Builder().setReactQueueConfigurationSpec(ReactQueueConfigurationSpec.createDefault()).setJSExecutor(javaScriptExecutor).setRegistry(nativeModuleRegistry).setJSBundleLoader(jSBundleLoader).setNativeModuleCallExceptionHandler(nativeModuleCallExceptionHandler);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START);
            Trace.beginSection("createCatalystInstance");
            try {
                CatalystInstanceImpl build = nativeModuleCallExceptionHandler2.build();
                Trace.endSection();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
                reactApplicationContext.initializeWithInstance(build);
                JSIModulePackage jSIModulePackage = reactInstanceManager.mJSIModulePackage;
                if (jSIModulePackage != null) {
                    build.addJSIModules(jSIModulePackage.getJSIModules(reactApplicationContext, build.getJavaScriptContextHolder()));
                }
                NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = reactInstanceManager.mBridgeIdleDebugListener;
                if (notThreadSafeBridgeIdleDebugListener != null) {
                    build.addBridgeIdleDebugListener(notThreadSafeBridgeIdleDebugListener);
                }
                ReactMarker.logMarker(ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START);
                Trace.beginSection("runJSBundle");
                build.runJSBundle();
                Trace.endSection();
                return reactApplicationContext;
            } catch (Throwable th) {
                Trace.endSection();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END);
                throw th;
            }
        } catch (Throwable th2) {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END);
            throw th2;
        }
    }

    public static void access$1400(ReactInstanceManager reactInstanceManager, final ReactApplicationContext reactApplicationContext) {
        Objects.requireNonNull(reactInstanceManager);
        Log.d("ReactNative", "ReactInstanceManager.setupReactContext()");
        ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END);
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_START);
        Trace.beginSection("setupReactContext");
        synchronized (reactInstanceManager.mAttachedReactRoots) {
            synchronized (reactInstanceManager.mReactContextLock) {
                R$dimen.assertNotNull(reactApplicationContext);
                reactInstanceManager.mCurrentReactContext = reactApplicationContext;
            }
            CatalystInstance catalystInstance = reactApplicationContext.getCatalystInstance();
            R$dimen.assertNotNull(catalystInstance);
            catalystInstance.initialize();
            reactInstanceManager.mDevSupportManager.onNewReactContextCreated(reactApplicationContext);
            reactInstanceManager.mMemoryPressureRouter.mListeners.add(catalystInstance);
            synchronized (reactInstanceManager) {
                if (reactInstanceManager.mLifecycleState == LifecycleState.RESUMED) {
                    reactInstanceManager.moveToResumedLifecycleState(true);
                }
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START);
            for (ReactRoot reactRoot : reactInstanceManager.mAttachedReactRoots) {
                reactInstanceManager.attachRootViewToInstance(reactRoot);
            }
            ReactMarker.logMarker(ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END);
        }
        final ReactInstanceEventListener[] reactInstanceEventListenerArr = (ReactInstanceEventListener[]) reactInstanceManager.mReactInstanceEventListeners.toArray(new ReactInstanceEventListener[reactInstanceManager.mReactInstanceEventListeners.size()]);
        UiThreadUtil.runOnUiThread(new Runnable(reactInstanceManager) { // from class: com.facebook.react.ReactInstanceManager.6
            @Override // java.lang.Runnable
            public void run() {
                ReactInstanceEventListener[] reactInstanceEventListenerArr2;
                for (ReactInstanceEventListener reactInstanceEventListener : reactInstanceEventListenerArr) {
                    if (reactInstanceEventListener != null) {
                        reactInstanceEventListener.onReactContextInitialized(reactApplicationContext);
                    }
                }
            }
        });
        Trace.endSection();
        ReactMarker.logMarker(ReactMarkerConstants.SETUP_REACT_CONTEXT_END);
        reactApplicationContext.runOnJSQueueThread(new Runnable(reactInstanceManager) { // from class: com.facebook.react.ReactInstanceManager.7
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
                ReactMarker.logMarker(ReactMarkerConstants.CHANGE_THREAD_PRIORITY, "js_default");
            }
        });
        reactApplicationContext.runOnNativeModulesQueueThread(new Runnable(reactInstanceManager) { // from class: com.facebook.react.ReactInstanceManager.8
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(0);
            }
        });
    }

    public final void attachRootViewToInstance(final ReactRoot reactRoot) {
        Log.d("ReactNative", "ReactInstanceManager.attachRootViewToInstance()");
        Trace.beginSection("attachRootViewToInstance");
        UIManager uIManager = R$style.getUIManager(this.mCurrentReactContext, reactRoot.getUIManagerType());
        Bundle appProperties = reactRoot.getAppProperties();
        final int addRootView = uIManager.addRootView(reactRoot.getRootViewGroup(), appProperties == null ? new WritableNativeMap() : Arguments.fromBundle(appProperties), reactRoot.getInitialUITemplate());
        reactRoot.setRootViewTag(addRootView);
        if (reactRoot.getUIManagerType() == 2) {
            uIManager.updateRootLayoutSpecs(addRootView, reactRoot.getWidthMeasureSpec(), reactRoot.getHeightMeasureSpec());
            reactRoot.setShouldLogContentAppeared(true);
        } else {
            reactRoot.runApplication();
        }
        UiThreadUtil.runOnUiThread(new Runnable(this) { // from class: com.facebook.react.ReactInstanceManager.9
            @Override // java.lang.Runnable
            public void run() {
                reactRoot.onStage(101);
            }
        });
        Trace.endSection();
    }

    public void createReactContextInBackground() {
        Log.d("ReactNative", "ReactInstanceManager.createReactContextInBackground()");
        UiThreadUtil.assertOnUiThread();
        if (!this.mHasStartedCreatingInitialContext) {
            this.mHasStartedCreatingInitialContext = true;
            Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackgroundInner()");
            int i = PrinterHolder.$r8$clinit;
            DebugOverlayTag debugOverlayTag = ReactDebugOverlayTags.RN_CORE;
            UiThreadUtil.assertOnUiThread();
            if (!this.mUseDeveloperSupport || this.mJSMainModulePath == null) {
                Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackgroundFromBundleLoader()");
                JavaScriptExecutorFactory javaScriptExecutorFactory = this.mJavaScriptExecutorFactory;
                JSBundleLoader jSBundleLoader = this.mBundleLoader;
                Log.d("ReactNative", "ReactInstanceManager.recreateReactContextInBackground()");
                UiThreadUtil.assertOnUiThread();
                ReactContextInitParams reactContextInitParams = new ReactContextInitParams(this, javaScriptExecutorFactory, jSBundleLoader);
                if (this.mCreateReactContextThread == null) {
                    runCreateReactContextOnNewThread(reactContextInitParams);
                } else {
                    this.mPendingReactContextInitParams = reactContextInitParams;
                }
            } else {
                DeveloperSettings devSettings = this.mDevSupportManager.getDevSettings();
                if (this.mBundleLoader == null) {
                    this.mDevSupportManager.handleReloadJS();
                } else {
                    this.mDevSupportManager.isPackagerRunning(new PackagerStatusCallback(this, devSettings) { // from class: com.facebook.react.ReactInstanceManager.3
                    });
                }
            }
        }
    }

    public final void detachViewFromInstance(ReactRoot reactRoot, CatalystInstance catalystInstance) {
        Log.d("ReactNative", "ReactInstanceManager.detachViewFromInstance()");
        UiThreadUtil.assertOnUiThread();
        if (reactRoot.getUIManagerType() == 2) {
            ((ReactFabric) catalystInstance.getJSModule(ReactFabric.class)).unmountComponentAtNode(reactRoot.getRootViewTag());
        } else {
            ((AppRegistry) catalystInstance.getJSModule(AppRegistry.class)).unmountApplicationComponentAtRootTag(reactRoot.getRootViewTag());
        }
    }

    public ReactContext getCurrentReactContext() {
        ReactContext reactContext;
        synchronized (this.mReactContextLock) {
            reactContext = this.mCurrentReactContext;
        }
        return reactContext;
    }

    public List<ViewManager> getOrCreateViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ViewManager> list;
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_START);
        Trace.beginSection("createAllViewManagers");
        try {
            if (this.mViewManagers == null) {
                synchronized (this.mPackages) {
                    if (this.mViewManagers == null) {
                        this.mViewManagers = new ArrayList();
                        for (ReactPackage reactPackage : this.mPackages) {
                            this.mViewManagers.addAll(reactPackage.createViewManagers(reactApplicationContext));
                        }
                        list = this.mViewManagers;
                    }
                }
                return list;
            }
            list = this.mViewManagers;
            return list;
        } finally {
            Trace.endSection();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_VIEW_MANAGERS_END);
        }
    }

    public final void invokeDefaultOnBackPressed() {
        UiThreadUtil.assertOnUiThread();
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.mDefaultBackButtonImpl;
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    public final synchronized void moveToResumedLifecycleState(boolean z) {
        ReactContext currentReactContext = getCurrentReactContext();
        if (currentReactContext != null && (z || this.mLifecycleState == LifecycleState.BEFORE_RESUME || this.mLifecycleState == LifecycleState.BEFORE_CREATE)) {
            currentReactContext.onHostResume(this.mCurrentActivity);
        }
        this.mLifecycleState = LifecycleState.RESUMED;
    }

    public final void processPackage(ReactPackage reactPackage, NativeModuleRegistryBuilder nativeModuleRegistryBuilder) {
        Iterable<ModuleHolder> iterable;
        final List<NativeModule> list;
        reactPackage.getClass().getSimpleName();
        boolean z = reactPackage instanceof ReactPackageLogger;
        if (z) {
            ((ReactPackageLogger) reactPackage).startProcessPackage();
        }
        if (reactPackage instanceof LazyReactPackage) {
            final LazyReactPackage lazyReactPackage = (LazyReactPackage) reactPackage;
            ReactApplicationContext reactApplicationContext = nativeModuleRegistryBuilder.mReactApplicationContext;
            final Map<String, ReactModuleInfo> reactModuleInfos = lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos();
            final List<ModuleSpec> nativeModules = lazyReactPackage.getNativeModules(reactApplicationContext);
            iterable = new Iterable<ModuleHolder>(lazyReactPackage, nativeModules, reactModuleInfos) { // from class: com.facebook.react.LazyReactPackage.2
                public final /* synthetic */ List val$nativeModules;
                public final /* synthetic */ Map val$reactModuleInfoMap;

                {
                    this.val$nativeModules = nativeModules;
                    this.val$reactModuleInfoMap = reactModuleInfos;
                }

                @Override // java.lang.Iterable
                public Iterator<ModuleHolder> iterator() {
                    return new Iterator<ModuleHolder>() { // from class: com.facebook.react.LazyReactPackage.2.1
                        public int position = 0;

                        {
                            AnonymousClass2.this = this;
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.position < AnonymousClass2.this.val$nativeModules.size();
                        }

                        /* JADX WARN: Finally extract failed */
                        @Override // java.util.Iterator
                        public ModuleHolder next() {
                            List list2 = AnonymousClass2.this.val$nativeModules;
                            int i = this.position;
                            this.position = i + 1;
                            ModuleSpec moduleSpec = (ModuleSpec) list2.get(i);
                            String name = moduleSpec.getName();
                            ReactModuleInfo reactModuleInfo = (ReactModuleInfo) AnonymousClass2.this.val$reactModuleInfoMap.get(name);
                            if (reactModuleInfo != null) {
                                return new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                            }
                            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, name);
                            try {
                                NativeModule nativeModule = (NativeModule) moduleSpec.getProvider().get();
                                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                                return new ModuleHolder(nativeModule);
                            } catch (Throwable th) {
                                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                                throw th;
                            }
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("Cannot remove native modules from the list");
                        }
                    };
                }
            };
        } else if (reactPackage instanceof TurboReactPackage) {
            final TurboReactPackage turboReactPackage = (TurboReactPackage) reactPackage;
            final ReactApplicationContext reactApplicationContext2 = nativeModuleRegistryBuilder.mReactApplicationContext;
            final Iterator<Map.Entry<String, ReactModuleInfo>> it = turboReactPackage.getReactModuleInfoProvider().getReactModuleInfos().entrySet().iterator();
            iterable = new Iterable<ModuleHolder>() { // from class: com.facebook.react.TurboReactPackage.1
                @Override // java.lang.Iterable
                public Iterator<ModuleHolder> iterator() {
                    return new Iterator<ModuleHolder>() { // from class: com.facebook.react.TurboReactPackage.1.1
                        public Map.Entry<String, ReactModuleInfo> nextEntry = null;

                        {
                            AnonymousClass1.this = this;
                        }

                        public final void findNext() {
                            if (it.hasNext()) {
                                Map.Entry<String, ReactModuleInfo> entry = (Map.Entry) it.next();
                                entry.getValue();
                                this.nextEntry = entry;
                                return;
                            }
                            this.nextEntry = null;
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            if (this.nextEntry == null) {
                                findNext();
                            }
                            return this.nextEntry != null;
                        }

                        @Override // java.util.Iterator
                        public ModuleHolder next() {
                            if (this.nextEntry == null) {
                                findNext();
                            }
                            Map.Entry<String, ReactModuleInfo> entry = this.nextEntry;
                            if (entry != null) {
                                findNext();
                                AnonymousClass1 r4 = AnonymousClass1.this;
                                return new ModuleHolder(entry.getValue(), new ModuleHolderProvider(entry.getKey(), reactApplicationContext2));
                            }
                            throw new NoSuchElementException("ModuleHolder not found");
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("Cannot remove native modules from the list");
                        }
                    };
                }
            };
        } else {
            ReactApplicationContext reactApplicationContext3 = nativeModuleRegistryBuilder.mReactApplicationContext;
            ReactInstanceManager reactInstanceManager = nativeModuleRegistryBuilder.mReactInstanceManager;
            String str = reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.";
            if (reactPackage instanceof ReactInstancePackage) {
                list = ((ReactInstancePackage) reactPackage).createNativeModules(reactApplicationContext3, reactInstanceManager);
            } else {
                list = reactPackage.createNativeModules(reactApplicationContext3);
            }
            iterable = new Iterable<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper$1
                @Override // java.lang.Iterable
                public Iterator<ModuleHolder> iterator() {
                    return new Iterator<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper$1.1
                        public int position = 0;

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.position < list.size();
                        }

                        @Override // java.util.Iterator
                        public ModuleHolder next() {
                            List list2 = list;
                            int i = this.position;
                            this.position = i + 1;
                            return new ModuleHolder((NativeModule) list2.get(i));
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException("Cannot remove methods ");
                        }
                    };
                }
            };
        }
        for (ModuleHolder moduleHolder : iterable) {
            String name = moduleHolder.getName();
            if (nativeModuleRegistryBuilder.mModules.containsKey(name)) {
                ModuleHolder moduleHolder2 = nativeModuleRegistryBuilder.mModules.get(name);
                if (moduleHolder.getCanOverrideExistingModule()) {
                    nativeModuleRegistryBuilder.mModules.remove(moduleHolder2);
                } else {
                    StringBuilder outline16 = GeneratedOutlineSupport.outline16("Native module ", name, " tried to override ");
                    outline16.append(moduleHolder2.getClassName());
                    outline16.append(". Check the getPackages() method in MainApplication.java, it might be that module is being created twice. If this was your intention, set canOverrideExistingModule=true. This error may also be present if the package is present only once in getPackages() but is also automatically added later during build time by autolinking. Try removing the existing entry and rebuild.");
                    throw new IllegalStateException(outline16.toString());
                }
            }
            nativeModuleRegistryBuilder.mModules.put(name, moduleHolder);
        }
        if (z) {
            ((ReactPackageLogger) reactPackage).endProcessPackage();
        }
    }

    public final void runCreateReactContextOnNewThread(final ReactContextInitParams reactContextInitParams) {
        Log.d("ReactNative", "ReactInstanceManager.runCreateReactContextOnNewThread()");
        UiThreadUtil.assertOnUiThread();
        synchronized (this.mAttachedReactRoots) {
            synchronized (this.mReactContextLock) {
                if (this.mCurrentReactContext != null) {
                    tearDownReactContext(this.mCurrentReactContext);
                    this.mCurrentReactContext = null;
                }
            }
        }
        this.mCreateReactContextThread = new Thread(null, new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5
            @Override // java.lang.Runnable
            public void run() {
                ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_END);
                synchronized (ReactInstanceManager.this.mHasStartedDestroying) {
                    while (ReactInstanceManager.this.mHasStartedDestroying.booleanValue()) {
                        try {
                            ReactInstanceManager.this.mHasStartedDestroying.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                ReactInstanceManager.this.mHasStartedCreatingInitialContext = true;
                try {
                    Process.setThreadPriority(-4);
                    ReactMarker.logMarker(ReactMarkerConstants.VM_INIT);
                    final ReactApplicationContext access$1000 = ReactInstanceManager.access$1000(ReactInstanceManager.this, reactContextInitParams.mJsExecutorFactory.create(), reactContextInitParams.mJsBundleLoader);
                    ReactInstanceManager.this.mCreateReactContextThread = null;
                    ReactMarker.logMarker(ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_START);
                    Runnable runnable = new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ReactInstanceManager reactInstanceManager = ReactInstanceManager.this;
                            ReactContextInitParams reactContextInitParams2 = reactInstanceManager.mPendingReactContextInitParams;
                            if (reactContextInitParams2 != null) {
                                reactInstanceManager.runCreateReactContextOnNewThread(reactContextInitParams2);
                                ReactInstanceManager.this.mPendingReactContextInitParams = null;
                            }
                        }
                    };
                    access$1000.runOnNativeModulesQueueThread(new Runnable() { // from class: com.facebook.react.ReactInstanceManager.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ReactInstanceManager.access$1400(ReactInstanceManager.this, access$1000);
                            } catch (Exception e) {
                                ReactInstanceManager.this.mDevSupportManager.handleException(e);
                            }
                        }
                    });
                    UiThreadUtil.runOnUiThread(runnable);
                } catch (Exception e) {
                    ReactInstanceManager.this.mDevSupportManager.handleException(e);
                }
            }
        }, "create_react_context");
        ReactMarker.logMarker(ReactMarkerConstants.REACT_CONTEXT_THREAD_START);
        this.mCreateReactContextThread.start();
    }

    public final void tearDownReactContext(ReactContext reactContext) {
        Log.d("ReactNative", "ReactInstanceManager.tearDownReactContext()");
        UiThreadUtil.assertOnUiThread();
        if (this.mLifecycleState == LifecycleState.RESUMED) {
            reactContext.onHostPause();
        }
        synchronized (this.mAttachedReactRoots) {
            for (ReactRoot reactRoot : this.mAttachedReactRoots) {
                reactRoot.getRootViewGroup().removeAllViews();
                reactRoot.getRootViewGroup().setId(-1);
            }
        }
        MemoryPressureRouter memoryPressureRouter = this.mMemoryPressureRouter;
        memoryPressureRouter.mListeners.remove(reactContext.getCatalystInstance());
        reactContext.destroy();
        this.mDevSupportManager.onReactInstanceDestroyed(reactContext);
    }
}

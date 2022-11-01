package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* loaded from: classes.dex */
public class ReactContext extends ContextWrapper {
    private static final String EARLY_JS_ACCESS_EXCEPTION_MESSAGE = "Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.";
    private CatalystInstance mCatalystInstance;
    private WeakReference<Activity> mCurrentActivity;
    private NativeModuleCallExceptionHandler mExceptionHandlerWrapper;
    private LayoutInflater mInflater;
    private MessageQueueThread mJSMessageQueueThread;
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private MessageQueueThread mNativeModulesMessageQueueThread;
    private MessageQueueThread mUiMessageQueueThread;
    private final CopyOnWriteArraySet<LifecycleEventListener> mLifecycleEventListeners = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<ActivityEventListener> mActivityEventListeners = new CopyOnWriteArraySet<>();
    private final CopyOnWriteArraySet<WindowFocusChangeListener> mWindowFocusEventListeners = new CopyOnWriteArraySet<>();
    private LifecycleState mLifecycleState = LifecycleState.BEFORE_CREATE;

    /* renamed from: com.facebook.react.bridge.ReactContext$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$facebook$react$common$LifecycleState;

        static {
            LifecycleState.values();
            int[] iArr = new int[3];
            $SwitchMap$com$facebook$react$common$LifecycleState = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$common$LifecycleState[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$common$LifecycleState[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class ExceptionHandlerWrapper implements NativeModuleCallExceptionHandler {
        public ExceptionHandlerWrapper() {
        }

        @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
        public void handleException(Exception exc) {
            ReactContext.this.handleException(exc);
        }
    }

    public ReactContext(Context context) {
        super(context);
    }

    public void addActivityEventListener(ActivityEventListener activityEventListener) {
        this.mActivityEventListeners.add(activityEventListener);
    }

    public void addLifecycleEventListener(final LifecycleEventListener lifecycleEventListener) {
        int ordinal;
        this.mLifecycleEventListeners.add(lifecycleEventListener);
        if (hasActiveCatalystInstance() && (ordinal = this.mLifecycleState.ordinal()) != 0 && ordinal != 1) {
            if (ordinal == 2) {
                runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.bridge.ReactContext.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ReactContext.this.mLifecycleEventListeners.contains(lifecycleEventListener)) {
                            try {
                                lifecycleEventListener.onHostResume();
                            } catch (RuntimeException e) {
                                ReactContext.this.handleException(e);
                            }
                        }
                    }
                });
                return;
            }
            throw new RuntimeException("Unhandled lifecycle state.");
        }
    }

    public void addWindowFocusChangeListener(WindowFocusChangeListener windowFocusChangeListener) {
        this.mWindowFocusEventListeners.add(windowFocusChangeListener);
    }

    public void assertOnJSQueueThread() {
        MessageQueueThread messageQueueThread = this.mJSMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.assertIsOnThread();
    }

    public void assertOnNativeModulesQueueThread() {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.assertIsOnThread();
    }

    public void assertOnUiQueueThread() {
        MessageQueueThread messageQueueThread = this.mUiMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.assertIsOnThread();
    }

    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            catalystInstance.destroy();
        }
    }

    public CatalystInstance getCatalystInstance() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        R$dimen.assertNotNull(catalystInstance);
        return catalystInstance;
    }

    public Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = this.mCurrentActivity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public NativeModuleCallExceptionHandler getExceptionHandler() {
        if (this.mExceptionHandlerWrapper == null) {
            this.mExceptionHandlerWrapper = new ExceptionHandlerWrapper();
        }
        return this.mExceptionHandlerWrapper;
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            return (T) catalystInstance.getJSModule(cls);
        }
        throw new RuntimeException(EARLY_JS_ACCESS_EXCEPTION_MESSAGE);
    }

    public JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.mCatalystInstance.getJavaScriptContextHolder();
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            return (T) catalystInstance.getNativeModule(cls);
        }
        throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.mInflater;
    }

    public void handleException(Exception exc) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        boolean z = false;
        boolean z2 = catalystInstance != null;
        boolean z3 = z2 && !catalystInstance.isDestroyed();
        NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler = this.mNativeModuleCallExceptionHandler;
        if (nativeModuleCallExceptionHandler != null) {
            z = true;
        }
        if (!z3 || !z) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to handle Exception - catalystInstanceVariableExists: ");
            sb.append(z2);
            sb.append(" - isCatalystInstanceAlive: ");
            sb.append(!z3);
            sb.append(" - hasExceptionHandler: ");
            sb.append(z);
            FLog.e("ReactNative", sb.toString(), exc);
            throw new RuntimeException(exc);
        }
        nativeModuleCallExceptionHandler.handleException(exc);
    }

    public boolean hasActiveCatalystInstance() {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        return catalystInstance != null && !catalystInstance.isDestroyed();
    }

    public boolean hasCatalystInstance() {
        return this.mCatalystInstance != null;
    }

    public boolean hasCurrentActivity() {
        WeakReference<Activity> weakReference = this.mCurrentActivity;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        CatalystInstance catalystInstance = this.mCatalystInstance;
        if (catalystInstance != null) {
            return catalystInstance.hasNativeModule(cls);
        }
        throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
    }

    public void initializeMessageQueueThreads(ReactQueueConfiguration reactQueueConfiguration) {
        if (this.mUiMessageQueueThread == null && this.mNativeModulesMessageQueueThread == null && this.mJSMessageQueueThread == null) {
            this.mUiMessageQueueThread = reactQueueConfiguration.getUIQueueThread();
            this.mNativeModulesMessageQueueThread = reactQueueConfiguration.getNativeModulesQueueThread();
            this.mJSMessageQueueThread = reactQueueConfiguration.getJSQueueThread();
            return;
        }
        throw new IllegalStateException("Message queue threads already initialized");
    }

    public void initializeWithInstance(CatalystInstance catalystInstance) {
        if (catalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        } else if (this.mCatalystInstance == null) {
            this.mCatalystInstance = catalystInstance;
            initializeMessageQueueThreads(catalystInstance.getReactQueueConfiguration());
        } else {
            throw new IllegalStateException("ReactContext has been already initialized");
        }
    }

    public boolean isOnJSQueueThread() {
        MessageQueueThread messageQueueThread = this.mJSMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        return messageQueueThread.isOnThread();
    }

    public boolean isOnNativeModulesQueueThread() {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        return messageQueueThread.isOnThread();
    }

    public boolean isOnUiQueueThread() {
        MessageQueueThread messageQueueThread = this.mUiMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        return messageQueueThread.isOnThread();
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Iterator<ActivityEventListener> it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onActivityResult(activity, i, i2, intent);
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
    }

    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostDestroy();
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
        this.mCurrentActivity = null;
    }

    public void onHostPause() {
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_PAUSE_START);
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostPause();
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_PAUSE_END);
    }

    public void onHostResume(Activity activity) {
        this.mLifecycleState = LifecycleState.RESUMED;
        this.mCurrentActivity = new WeakReference<>(activity);
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_RESUME_START);
        Iterator<LifecycleEventListener> it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onHostResume();
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
        ReactMarker.logMarker(ReactMarkerConstants.ON_HOST_RESUME_END);
    }

    public void onNewIntent(Activity activity, Intent intent) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = new WeakReference<>(activity);
        Iterator<ActivityEventListener> it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onNewIntent(intent);
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
    }

    public void onWindowFocusChange(boolean z) {
        UiThreadUtil.assertOnUiThread();
        Iterator<WindowFocusChangeListener> it = this.mWindowFocusEventListeners.iterator();
        while (it.hasNext()) {
            try {
                it.next().onWindowFocusChange(z);
            } catch (RuntimeException e) {
                handleException(e);
            }
        }
    }

    public void removeActivityEventListener(ActivityEventListener activityEventListener) {
        this.mActivityEventListeners.remove(activityEventListener);
    }

    public void removeLifecycleEventListener(LifecycleEventListener lifecycleEventListener) {
        this.mLifecycleEventListeners.remove(lifecycleEventListener);
    }

    public void removeWindowFocusChangeListener(WindowFocusChangeListener windowFocusChangeListener) {
        this.mWindowFocusEventListeners.remove(windowFocusChangeListener);
    }

    public void resetPerfStats() {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        if (messageQueueThread != null) {
            messageQueueThread.resetPerfStats();
        }
        MessageQueueThread messageQueueThread2 = this.mJSMessageQueueThread;
        if (messageQueueThread2 != null) {
            messageQueueThread2.resetPerfStats();
        }
    }

    public void runOnJSQueueThread(Runnable runnable) {
        MessageQueueThread messageQueueThread = this.mJSMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.runOnQueue(runnable);
    }

    public void runOnNativeModulesQueueThread(Runnable runnable) {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.runOnQueue(runnable);
    }

    public void runOnUiQueueThread(Runnable runnable) {
        MessageQueueThread messageQueueThread = this.mUiMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.runOnQueue(runnable);
    }

    public void setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
    }

    public boolean startActivityForResult(Intent intent, int i, Bundle bundle) {
        Activity currentActivity = getCurrentActivity();
        R$dimen.assertNotNull(currentActivity);
        currentActivity.startActivityForResult(intent, i, bundle);
        return true;
    }

    public void assertOnNativeModulesQueueThread(String str) {
        MessageQueueThread messageQueueThread = this.mNativeModulesMessageQueueThread;
        R$dimen.assertNotNull(messageQueueThread);
        messageQueueThread.assertIsOnThread(str);
    }
}

package com.facebook.react.modules.core;

import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskRetryPolicy;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.appregistry.AppRegistry;
@ReactModule(name = HeadlessJsTaskSupportModule.NAME)
/* loaded from: classes.dex */
public class HeadlessJsTaskSupportModule extends ReactContextBaseJavaModule {
    public static final String NAME = "HeadlessJsTaskSupport";

    public HeadlessJsTaskSupportModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void notifyTaskFinished(int i) {
        HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        if (instance.isTaskRunning(i)) {
            instance.finishTask(i);
        } else {
            FLog.w(HeadlessJsTaskSupportModule.class, "Tried to finish non-active task with id %d. Did it time out?", Integer.valueOf(i));
        }
    }

    @ReactMethod
    public void notifyTaskRetry(final int i, Promise promise) {
        final HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        boolean z = false;
        if (instance.isTaskRunning(i)) {
            synchronized (instance) {
                HeadlessJsTaskConfig headlessJsTaskConfig = instance.mActiveTaskConfigs.get(Integer.valueOf(i));
                boolean z2 = headlessJsTaskConfig != null;
                R$dimen.assertCondition(z2, "Tried to retrieve non-existent task config with id " + i + ".");
                HeadlessJsTaskRetryPolicy headlessJsTaskRetryPolicy = headlessJsTaskConfig.mRetryPolicy;
                if (headlessJsTaskRetryPolicy.canRetry()) {
                    Runnable runnable = instance.mTaskTimeouts.get(i);
                    if (runnable != null) {
                        instance.mHandler.removeCallbacks(runnable);
                        instance.mTaskTimeouts.remove(i);
                    }
                    final HeadlessJsTaskConfig headlessJsTaskConfig2 = new HeadlessJsTaskConfig(headlessJsTaskConfig.mTaskKey, headlessJsTaskConfig.mData, headlessJsTaskConfig.mTimeout, headlessJsTaskConfig.mAllowedInForeground, headlessJsTaskRetryPolicy.update());
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.1
                        @Override // java.lang.Runnable
                        public void run() {
                            final HeadlessJsTaskContext headlessJsTaskContext = instance;
                            HeadlessJsTaskConfig headlessJsTaskConfig3 = headlessJsTaskConfig2;
                            final int i2 = i;
                            synchronized (headlessJsTaskContext) {
                                UiThreadUtil.assertOnUiThread();
                                ReactContext reactContext = headlessJsTaskContext.mReactContext.get();
                                R$dimen.assertNotNull(reactContext, "Tried to start a task on a react context that has already been destroyed");
                                ReactContext reactContext2 = reactContext;
                                if (reactContext2.getLifecycleState() == LifecycleState.RESUMED && !headlessJsTaskConfig3.mAllowedInForeground) {
                                    throw new IllegalStateException("Tried to start task " + headlessJsTaskConfig3.mTaskKey + " while in foreground, but this is not allowed.");
                                }
                                headlessJsTaskContext.mActiveTasks.add(Integer.valueOf(i2));
                                headlessJsTaskContext.mActiveTaskConfigs.put(Integer.valueOf(i2), new HeadlessJsTaskConfig(headlessJsTaskConfig3));
                                if (reactContext2.hasActiveCatalystInstance()) {
                                    ((AppRegistry) reactContext2.getJSModule(AppRegistry.class)).startHeadlessTask(i2, headlessJsTaskConfig3.mTaskKey, headlessJsTaskConfig3.mData);
                                } else {
                                    ReactSoftException.logSoftException("HeadlessJsTaskContext", new RuntimeException("Cannot start headless task, CatalystInstance not available"));
                                }
                                long j = headlessJsTaskConfig3.mTimeout;
                                if (j > 0) {
                                    Runnable runnable2 = new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.3
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            headlessJsTaskContext.finishTask(i2);
                                        }
                                    };
                                    headlessJsTaskContext.mTaskTimeouts.append(i2, runnable2);
                                    headlessJsTaskContext.mHandler.postDelayed(runnable2, j);
                                }
                                for (HeadlessJsTaskEventListener headlessJsTaskEventListener : headlessJsTaskContext.mHeadlessJsTaskEventListeners) {
                                    headlessJsTaskEventListener.onHeadlessJsTaskStart(i2);
                                }
                            }
                        }
                    }, headlessJsTaskRetryPolicy.getDelay());
                    z = true;
                }
            }
            promise.resolve(Boolean.valueOf(z));
            return;
        }
        FLog.w(HeadlessJsTaskSupportModule.class, "Tried to retry non-active task with id %d. Did it time out?", Integer.valueOf(i));
        promise.resolve(Boolean.FALSE);
    }
}

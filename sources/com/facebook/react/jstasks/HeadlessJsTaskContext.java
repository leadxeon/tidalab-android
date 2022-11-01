package com.facebook.react.jstasks;

import android.os.Handler;
import android.util.SparseArray;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class HeadlessJsTaskContext {
    public static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES = new WeakHashMap<>();
    public final WeakReference<ReactContext> mReactContext;
    public final Set<HeadlessJsTaskEventListener> mHeadlessJsTaskEventListeners = new CopyOnWriteArraySet();
    public final Handler mHandler = new Handler();
    public final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();
    public final Map<Integer, HeadlessJsTaskConfig> mActiveTaskConfigs = new ConcurrentHashMap();
    public final SparseArray<Runnable> mTaskTimeouts = new SparseArray<>();

    public HeadlessJsTaskContext(ReactContext reactContext) {
        new AtomicInteger(0);
        this.mReactContext = new WeakReference<>(reactContext);
    }

    public static HeadlessJsTaskContext getInstance(ReactContext reactContext) {
        WeakHashMap<ReactContext, HeadlessJsTaskContext> weakHashMap = INSTANCES;
        HeadlessJsTaskContext headlessJsTaskContext = weakHashMap.get(reactContext);
        if (headlessJsTaskContext != null) {
            return headlessJsTaskContext;
        }
        HeadlessJsTaskContext headlessJsTaskContext2 = new HeadlessJsTaskContext(reactContext);
        weakHashMap.put(reactContext, headlessJsTaskContext2);
        return headlessJsTaskContext2;
    }

    public synchronized void finishTask(final int i) {
        boolean remove = this.mActiveTasks.remove(Integer.valueOf(i));
        R$dimen.assertCondition(remove, "Tried to finish non-existent task with id " + i + ".");
        boolean z = this.mActiveTaskConfigs.remove(Integer.valueOf(i)) != null;
        R$dimen.assertCondition(z, "Tried to remove non-existent task config with id " + i + ".");
        Runnable runnable = this.mTaskTimeouts.get(i);
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mTaskTimeouts.remove(i);
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.2
            @Override // java.lang.Runnable
            public void run() {
                for (HeadlessJsTaskEventListener headlessJsTaskEventListener : HeadlessJsTaskContext.this.mHeadlessJsTaskEventListeners) {
                    headlessJsTaskEventListener.onHeadlessJsTaskFinish(i);
                }
            }
        });
    }

    public synchronized boolean isTaskRunning(int i) {
        return this.mActiveTasks.contains(Integer.valueOf(i));
    }
}

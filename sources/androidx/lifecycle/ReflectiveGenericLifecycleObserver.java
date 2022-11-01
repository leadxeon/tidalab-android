package androidx.lifecycle;

import androidx.lifecycle.ClassesInfoCache;
import androidx.lifecycle.Lifecycle;
/* loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
    public final ClassesInfoCache.CallbackInfo mInfo;
    public final Object mWrapped;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.mWrapped = obj;
        this.mInfo = ClassesInfoCache.sInstance.getInfo(obj.getClass());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        ClassesInfoCache.CallbackInfo callbackInfo = this.mInfo;
        Object obj = this.mWrapped;
        ClassesInfoCache.CallbackInfo.invokeMethodsForEvent(callbackInfo.mEventToHandlers.get(event), lifecycleOwner, event, obj);
        ClassesInfoCache.CallbackInfo.invokeMethodsForEvent(callbackInfo.mEventToHandlers.get(Lifecycle.Event.ON_ANY), lifecycleOwner, event, obj);
    }
}

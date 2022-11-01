package androidx.viewpager2.adapter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
/* loaded from: classes.dex */
public class FragmentStateAdapter$5 implements LifecycleEventObserver {
    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            throw null;
        }
    }
}

package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.reactnativecommunity.webview.RNCWebViewManager;
/* loaded from: classes.dex */
public class FullLifecycleObserverAdapter implements LifecycleEventObserver {
    public final FullLifecycleObserver mFullLifecycleObserver;
    public final LifecycleEventObserver mLifecycleEventObserver;

    public FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver, LifecycleEventObserver lifecycleEventObserver) {
        this.mFullLifecycleObserver = fullLifecycleObserver;
        this.mLifecycleEventObserver = lifecycleEventObserver;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        switch (event.ordinal()) {
            case 0:
                this.mFullLifecycleObserver.onCreate(lifecycleOwner);
                break;
            case 1:
                this.mFullLifecycleObserver.onStart(lifecycleOwner);
                break;
            case 2:
                this.mFullLifecycleObserver.onResume(lifecycleOwner);
                break;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                this.mFullLifecycleObserver.onPause(lifecycleOwner);
                break;
            case 4:
                this.mFullLifecycleObserver.onStop(lifecycleOwner);
                break;
            case 5:
                this.mFullLifecycleObserver.onDestroy(lifecycleOwner);
                break;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        LifecycleEventObserver lifecycleEventObserver = this.mLifecycleEventObserver;
        if (lifecycleEventObserver != null) {
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
        }
    }
}

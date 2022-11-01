package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SavedStateHandleController implements LifecycleEventObserver {
    public boolean mIsAttached;

    /* renamed from: androidx.lifecycle.SavedStateHandleController$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements LifecycleEventObserver {
        public final /* synthetic */ Lifecycle val$lifecycle;
        public final /* synthetic */ SavedStateRegistry val$registry;

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.val$lifecycle.removeObserver(this);
                this.val$registry.runOnNextRecreation(OnRecreation.class);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        @Override // androidx.savedstate.SavedStateRegistry.AutoRecreated
        public void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner) {
            Object obj;
            boolean z;
            if (savedStateRegistryOwner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                Objects.requireNonNull(viewModelStore);
                Iterator it = new HashSet(viewModelStore.mMap.keySet()).iterator();
                while (it.hasNext()) {
                    ViewModel viewModel = viewModelStore.mMap.get((String) it.next());
                    Lifecycle lifecycle = savedStateRegistryOwner.getLifecycle();
                    Map<String, Object> map = viewModel.mBagOfTags;
                    if (map == null) {
                        obj = null;
                    } else {
                        synchronized (map) {
                            obj = viewModel.mBagOfTags.get("androidx.lifecycle.savedstate.vm.tag");
                        }
                    }
                    SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
                    if (savedStateHandleController != null && !(z = savedStateHandleController.mIsAttached)) {
                        if (z) {
                            throw new IllegalStateException("Already attached to lifecycleOwner");
                        }
                        savedStateHandleController.mIsAttached = true;
                        lifecycle.addObserver(savedStateHandleController);
                        throw null;
                    }
                }
                if (!new HashSet(viewModelStore.mMap.keySet()).isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
        }
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.mIsAttached = false;
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }
}

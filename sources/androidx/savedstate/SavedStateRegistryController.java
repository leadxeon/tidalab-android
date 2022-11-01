package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class SavedStateRegistryController {
    public final SavedStateRegistryOwner mOwner;
    public final SavedStateRegistry mRegistry = new SavedStateRegistry();

    public SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.mOwner = savedStateRegistryOwner;
    }

    public void performRestore(Bundle bundle) {
        Lifecycle lifecycle = this.mOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.INITIALIZED) {
            lifecycle.addObserver(new Recreator(this.mOwner));
            final SavedStateRegistry savedStateRegistry = this.mRegistry;
            if (!savedStateRegistry.mRestored) {
                if (bundle != null) {
                    savedStateRegistry.mRestoredState = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
                }
                lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.savedstate.SavedStateRegistry.1
                    @Override // androidx.lifecycle.LifecycleEventObserver
                    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                        if (event == Lifecycle.Event.ON_START) {
                            savedStateRegistry.mAllowingSavingState = true;
                        } else if (event == Lifecycle.Event.ON_STOP) {
                            savedStateRegistry.mAllowingSavingState = false;
                        }
                    }
                });
                savedStateRegistry.mRestored = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    public void performSave(Bundle bundle) {
        SavedStateRegistry savedStateRegistry = this.mRegistry;
        Objects.requireNonNull(savedStateRegistry);
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = savedStateRegistry.mRestoredState;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap<String, SavedStateRegistry.SavedStateProvider>.IteratorWithAdditions iteratorWithAdditions = savedStateRegistry.mComponents.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            bundle2.putBundle((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}

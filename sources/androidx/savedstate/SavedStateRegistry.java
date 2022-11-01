package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.savedstate.Recreator;
import com.android.tools.r8.GeneratedOutlineSupport;
@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class SavedStateRegistry {
    public Recreator.SavedStateProvider mRecreatorProvider;
    public boolean mRestored;
    public Bundle mRestoredState;
    public SafeIterableMap<String, SavedStateProvider> mComponents = new SafeIterableMap<>();
    public boolean mAllowingSavingState = true;

    /* loaded from: classes.dex */
    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner);
    }

    /* loaded from: classes.dex */
    public interface SavedStateProvider {
        Bundle saveState();
    }

    public Bundle consumeRestoredStateForKey(String str) {
        if (this.mRestored) {
            Bundle bundle = this.mRestoredState;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = bundle.getBundle(str);
            this.mRestoredState.remove(str);
            if (this.mRestoredState.isEmpty()) {
                this.mRestoredState = null;
            }
            return bundle2;
        }
        throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
    }

    public void registerSavedStateProvider(String str, SavedStateProvider savedStateProvider) {
        if (this.mComponents.putIfAbsent(str, savedStateProvider) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void runOnNextRecreation(Class<? extends AutoRecreated> cls) {
        if (this.mAllowingSavingState) {
            if (this.mRecreatorProvider == null) {
                this.mRecreatorProvider = new Recreator.SavedStateProvider(this);
            }
            try {
                cls.getDeclaredConstructor(new Class[0]);
                Recreator.SavedStateProvider savedStateProvider = this.mRecreatorProvider;
                savedStateProvider.mClasses.add(cls.getName());
            } catch (NoSuchMethodException e) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Class");
                outline13.append(cls.getSimpleName());
                outline13.append(" must have default constructor in order to be automatically recreated");
                throw new IllegalArgumentException(outline13.toString(), e);
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }
}

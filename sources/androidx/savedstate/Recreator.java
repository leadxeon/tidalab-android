package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.savedstate.SavedStateRegistry;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
@SuppressLint({"RestrictedApi"})
/* loaded from: classes.dex */
public final class Recreator implements LifecycleEventObserver {
    public final SavedStateRegistryOwner mOwner;

    /* loaded from: classes.dex */
    public static final class SavedStateProvider implements SavedStateRegistry.SavedStateProvider {
        public final Set<String> mClasses = new HashSet();

        public SavedStateProvider(SavedStateRegistry savedStateRegistry) {
            savedStateRegistry.registerSavedStateProvider("androidx.savedstate.Restarter", this);
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        public Bundle saveState() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.mClasses));
            return bundle;
        }
    }

    public Recreator(SavedStateRegistryOwner savedStateRegistryOwner) {
        this.mOwner = savedStateRegistryOwner;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Class cls;
        if (event == Lifecycle.Event.ON_CREATE) {
            lifecycleOwner.getLifecycle().removeObserver(this);
            Bundle consumeRestoredStateForKey = this.mOwner.getSavedStateRegistry().consumeRestoredStateForKey("androidx.savedstate.Restarter");
            if (consumeRestoredStateForKey != null) {
                ArrayList<String> stringArrayList = consumeRestoredStateForKey.getStringArrayList("classes_to_restore");
                if (stringArrayList != null) {
                    Iterator<String> it = stringArrayList.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        try {
                            try {
                                Constructor declaredConstructor = Class.forName(next, false, Recreator.class.getClassLoader()).asSubclass(SavedStateRegistry.AutoRecreated.class).getDeclaredConstructor(new Class[0]);
                                declaredConstructor.setAccessible(true);
                                try {
                                    ((SavedStateRegistry.AutoRecreated) declaredConstructor.newInstance(new Object[0])).onRecreated(this.mOwner);
                                } catch (Exception e) {
                                    throw new RuntimeException(GeneratedOutlineSupport.outline8("Failed to instantiate ", next), e);
                                }
                            } catch (NoSuchMethodException e2) {
                                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Class");
                                outline13.append(cls.getSimpleName());
                                outline13.append(" must have default constructor in order to be automatically recreated");
                                throw new IllegalStateException(outline13.toString(), e2);
                            }
                        } catch (ClassNotFoundException e3) {
                            throw new RuntimeException(GeneratedOutlineSupport.outline9("Class ", next, " wasn't found"), e3);
                        }
                    }
                    return;
                }
                throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            }
            return;
        }
        throw new AssertionError("Next event must be ON_CREATE");
    }
}

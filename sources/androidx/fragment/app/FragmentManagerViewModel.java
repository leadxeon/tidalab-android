package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider$Factory;
import androidx.lifecycle.ViewModelStore;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class FragmentManagerViewModel extends ViewModel {
    public static final ViewModelProvider$Factory FACTORY = new AnonymousClass1();
    public final boolean mStateAutomaticallySaved;
    public final HashMap<String, Fragment> mRetainedFragments = new HashMap<>();
    public final HashMap<String, FragmentManagerViewModel> mChildNonConfigs = new HashMap<>();
    public final HashMap<String, ViewModelStore> mViewModelStores = new HashMap<>();
    public boolean mHasBeenCleared = false;
    public boolean mIsStateSaved = false;

    /* renamed from: androidx.fragment.app.FragmentManagerViewModel$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ViewModelProvider$Factory {
        public <T extends ViewModel> T create(Class<T> cls) {
            return new FragmentManagerViewModel(true);
        }
    }

    public FragmentManagerViewModel(boolean z) {
        this.mStateAutomaticallySaved = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FragmentManagerViewModel.class != obj.getClass()) {
            return false;
        }
        FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) obj;
        return this.mRetainedFragments.equals(fragmentManagerViewModel.mRetainedFragments) && this.mChildNonConfigs.equals(fragmentManagerViewModel.mChildNonConfigs) && this.mViewModelStores.equals(fragmentManagerViewModel.mViewModelStores);
    }

    public int hashCode() {
        int hashCode = this.mChildNonConfigs.hashCode();
        return this.mViewModelStores.hashCode() + ((hashCode + (this.mRetainedFragments.hashCode() * 31)) * 31);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.mHasBeenCleared = true;
    }

    public void removeRetainedFragment(Fragment fragment) {
        if (!this.mIsStateSaved) {
            if ((this.mRetainedFragments.remove(fragment.mWho) != null) && FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
            }
        } else if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
        }
    }

    public boolean shouldDestroy(Fragment fragment) {
        if (this.mRetainedFragments.containsKey(fragment.mWho) && this.mStateAutomaticallySaved) {
            return this.mHasBeenCleared;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.mRetainedFragments.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.mChildNonConfigs.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.mViewModelStores.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

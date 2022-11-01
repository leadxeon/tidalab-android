package androidx.fragment.app;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes.dex */
public class FragmentStore {
    public FragmentManagerViewModel mNonConfig;
    public final ArrayList<Fragment> mAdded = new ArrayList<>();
    public final HashMap<String, FragmentStateManager> mActive = new HashMap<>();

    public void addFragment(Fragment fragment) {
        if (!this.mAdded.contains(fragment)) {
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    public void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    public boolean containsActiveFragment(String str) {
        return this.mActive.get(str) != null;
    }

    public Fragment findActiveFragment(String str) {
        FragmentStateManager fragmentStateManager = this.mActive.get(str);
        if (fragmentStateManager != null) {
            return fragmentStateManager.mFragment;
        }
        return null;
    }

    public Fragment findFragmentByWho(String str) {
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.mFragment;
                if (!str.equals(fragment.mWho)) {
                    fragment = fragment.mChildFragmentManager.mFragmentStore.findFragmentByWho(str);
                }
                if (fragment != null) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public List<FragmentStateManager> getActiveFragmentStateManagers() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager);
            }
        }
        return arrayList;
    }

    public List<Fragment> getActiveFragments() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager.mFragment);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public FragmentStateManager getFragmentStateManager(String str) {
        return this.mActive.get(str);
    }

    public List<Fragment> getFragments() {
        ArrayList arrayList;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            arrayList = new ArrayList(this.mAdded);
        }
        return arrayList;
    }

    public void makeActive(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.mFragment;
        if (!containsActiveFragment(fragment.mWho)) {
            this.mActive.put(fragment.mWho, fragmentStateManager);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Added fragment to active set " + fragment);
            }
        }
    }

    public void makeInactive(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.mFragment;
        if (fragment.mRetainInstance) {
            this.mNonConfig.removeRetainedFragment(fragment);
        }
        if (this.mActive.put(fragment.mWho, null) != null && FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
    }

    public void removeFragment(Fragment fragment) {
        synchronized (this.mAdded) {
            this.mAdded.remove(fragment);
        }
        fragment.mAdded = false;
    }
}

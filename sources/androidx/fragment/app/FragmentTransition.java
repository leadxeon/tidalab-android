package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.collection.MapCollections;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class FragmentTransition {
    public static final int[] INVERSE_OPS = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final FragmentTransitionImpl PLATFORM_IMPL = new FragmentTransitionCompat21();
    public static final FragmentTransitionImpl SUPPORT_IMPL;

    /* loaded from: classes.dex */
    public interface Callback {
    }

    /* loaded from: classes.dex */
    public static class FragmentContainerTransition {
        public Fragment firstOut;
        public boolean firstOutIsPop;
        public BackStackRecord firstOutTransaction;
        public Fragment lastIn;
        public boolean lastInIsPop;
        public BackStackRecord lastInTransaction;
    }

    static {
        FragmentTransitionImpl fragmentTransitionImpl;
        try {
            fragmentTransitionImpl = (FragmentTransitionImpl) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            fragmentTransitionImpl = null;
        }
        SUPPORT_IMPL = fragmentTransitionImpl;
    }

    public static void addSharedElementsWithMatchingNames(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int i = arrayMap.mSize - 1; i >= 0; i--) {
            View valueAt = arrayMap.valueAt(i);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (collection.contains(valueAt.getTransitionName())) {
                arrayList.add(valueAt);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0038, code lost:
        if (r0.mAdded != false) goto L_0x0088;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0076, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0086, code lost:
        if (r0.mHidden != false) goto L_0x007e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0088, code lost:
        r9 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00a9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00c9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00e2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void addToFirstInLastOut(androidx.fragment.app.BackStackRecord r8, androidx.fragment.app.FragmentTransaction.Op r9, android.util.SparseArray<androidx.fragment.app.FragmentTransition.FragmentContainerTransition> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransition.addToFirstInLastOut(androidx.fragment.app.BackStackRecord, androidx.fragment.app.FragmentTransaction$Op, android.util.SparseArray, boolean, boolean):void");
    }

    public static void callSharedElementStartEnd(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        if (z) {
            fragment2.getEnterTransitionCallback();
        } else {
            fragment.getEnterTransitionCallback();
        }
    }

    public static boolean canHandleAll(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        ArrayList<String> arrayList;
        Fragment fragment = fragmentContainerTransition.lastIn;
        View view = fragment.mView;
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, view);
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (fragmentContainerTransition.lastInIsPop) {
            fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        } else {
            fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        }
        if (arrayList != null) {
            MapCollections.retainAllHelper(arrayMap2, arrayList);
            MapCollections.retainAllHelper(arrayMap2, arrayMap.values());
        }
        retainValues(arrayMap, arrayMap2);
        return arrayMap2;
    }

    public static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, FragmentContainerTransition fragmentContainerTransition) {
        ArrayList<String> arrayList;
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        Fragment fragment = fragmentContainerTransition.firstOut;
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.findNamedViews(arrayMap2, fragment.requireView());
        BackStackRecord backStackRecord = fragmentContainerTransition.firstOutTransaction;
        if (fragmentContainerTransition.firstOutIsPop) {
            fragment.getEnterTransitionCallback();
            arrayList = backStackRecord.mSharedElementTargetNames;
        } else {
            fragment.getExitTransitionCallback();
            arrayList = backStackRecord.mSharedElementSourceNames;
        }
        if (arrayList != null) {
            MapCollections.retainAllHelper(arrayMap2, arrayList);
        }
        MapCollections.retainAllHelper(arrayMap, arrayMap2.keySet());
        return arrayMap2;
    }

    public static FragmentTransitionImpl chooseImpl(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            fragment.getExitTransition();
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            fragment2.getEnterTransition();
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            fragment2.getSharedElementEnterTransition();
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = PLATFORM_IMPL;
        if (canHandleAll(fragmentTransitionImpl, arrayList)) {
            return fragmentTransitionImpl;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = SUPPORT_IMPL;
        if (fragmentTransitionImpl2 != null && canHandleAll(fragmentTransitionImpl2, arrayList)) {
            return fragmentTransitionImpl2;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.mView;
        if (view2 != null) {
            fragmentTransitionImpl.captureTransitioningViews(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        fragmentTransitionImpl.addTargets(obj, arrayList2);
        return arrayList2;
    }

    public static Object getEnterTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReenterTransition();
        } else {
            fragment.getEnterTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    public static Object getExitTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        Object obj = null;
        if (fragment == null) {
            return null;
        }
        if (z) {
            obj = fragment.getReturnTransition();
        } else {
            fragment.getExitTransition();
        }
        return fragmentTransitionImpl.cloneTransition(obj);
    }

    public static View getInEpicenterView(ArrayMap<String, View> arrayMap, FragmentContainerTransition fragmentContainerTransition, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        BackStackRecord backStackRecord = fragmentContainerTransition.lastInTransaction;
        if (obj == null || arrayMap == null || (arrayList = backStackRecord.mSharedElementSourceNames) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = backStackRecord.mSharedElementSourceNames.get(0);
        } else {
            str = backStackRecord.mSharedElementTargetNames.get(0);
        }
        return arrayMap.get(str);
    }

    public static Object getSharedElementTransition(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        Object obj;
        if (z) {
            obj = fragment2.getSharedElementReturnTransition();
        } else {
            fragment.getSharedElementEnterTransition();
            obj = null;
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(obj));
    }

    public static void retainValues(ArrayMap<String, String> arrayMap, ArrayMap<String, View> arrayMap2) {
        int i = arrayMap.mSize;
        while (true) {
            i--;
            if (i < 0) {
                return;
            }
            if (!arrayMap2.containsKey(arrayMap.valueAt(i))) {
                arrayMap.removeAt(i);
            }
        }
    }

    public static void setOutEpicenter(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, BackStackRecord backStackRecord) {
        String str;
        ArrayList<String> arrayList = backStackRecord.mSharedElementSourceNames;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = backStackRecord.mSharedElementTargetNames.get(0);
            } else {
                str = backStackRecord.mSharedElementSourceNames.get(0);
            }
            View view = arrayMap.get(str);
            fragmentTransitionImpl.setEpicenter(obj, view);
            if (obj2 != null) {
                fragmentTransitionImpl.setEpicenter(obj2, view);
            }
        }
    }

    public static void setViewVisibility(ArrayList<View> arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                arrayList.get(size).setVisibility(i);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:145:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x03f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void startTransitions(android.content.Context r37, androidx.fragment.app.FragmentContainer r38, java.util.ArrayList<androidx.fragment.app.BackStackRecord> r39, java.util.ArrayList<java.lang.Boolean> r40, int r41, int r42, boolean r43, androidx.fragment.app.FragmentTransition.Callback r44) {
        /*
            Method dump skipped, instructions count: 1032
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentTransition.startTransitions(android.content.Context, androidx.fragment.app.FragmentContainer, java.util.ArrayList, java.util.ArrayList, int, int, boolean, androidx.fragment.app.FragmentTransition$Callback):void");
    }
}

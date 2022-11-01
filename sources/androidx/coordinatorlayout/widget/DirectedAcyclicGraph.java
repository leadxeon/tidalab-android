package androidx.coordinatorlayout.widget;

import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import java.util.ArrayList;
import java.util.HashSet;
/* loaded from: classes.dex */
public final class DirectedAcyclicGraph<T> {
    public final Pools$Pool<ArrayList<T>> mListPool = new Pools$SimplePool(10);
    public final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap<>();
    public final ArrayList<T> mSortResult = new ArrayList<>();
    public final HashSet<T> mSortTmpMarked = new HashSet<>();

    public void addNode(T t) {
        if (!(this.mGraph.indexOfKey(t) >= 0)) {
            this.mGraph.put(t, null);
        }
    }

    public final void dfs(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (!arrayList.contains(t)) {
            if (!hashSet.contains(t)) {
                hashSet.add(t);
                ArrayList<T> orDefault = this.mGraph.getOrDefault(t, null);
                if (orDefault != null) {
                    int size = orDefault.size();
                    for (int i = 0; i < size; i++) {
                        dfs(orDefault.get(i), arrayList, hashSet);
                    }
                }
                hashSet.remove(t);
                arrayList.add(t);
                return;
            }
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
    }
}

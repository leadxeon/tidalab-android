package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.collection.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class TransitionManager {
    public static Transition sDefaultTransition = new AutoTransition();
    public static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions = new ThreadLocal<>();
    public static ArrayList<ViewGroup> sPendingTransitions = new ArrayList<>();

    /* loaded from: classes.dex */
    public static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        public ViewGroup mSceneRoot;
        public Transition mTransition;

        public MultiListener(Transition transition, ViewGroup viewGroup) {
            this.mTransition = transition;
            this.mSceneRoot = viewGroup;
        }

        /* JADX WARN: Removed duplicated region for block: B:103:0x021a  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x0247  */
        /* JADX WARN: Removed duplicated region for block: B:138:0x01f4 A[EDGE_INSN: B:138:0x01f4->B:93:0x01f4 ?: BREAK  , SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x01f9  */
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onPreDraw() {
            /*
                Method dump skipped, instructions count: 697
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionManager.MultiListener.onPreDraw():boolean");
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList<Transition> arrayList = TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }
    }

    public static ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap;
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> weakReference = sRunningTransitions.get();
        if (weakReference != null && (arrayMap = weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
        sRunningTransitions.set(new WeakReference<>(arrayMap2));
        return arrayMap2;
    }
}

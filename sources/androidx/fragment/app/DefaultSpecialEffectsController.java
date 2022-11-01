package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.collection.MapCollections;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.SpecialEffectsController;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class DefaultSpecialEffectsController extends SpecialEffectsController {

    /* loaded from: classes.dex */
    public static class AnimationInfo extends SpecialEffectsInfo {
        public FragmentAnim$AnimationOrAnimator mAnimation;
        public boolean mIsPop;
        public boolean mLoadedAnim = false;

        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mIsPop = z;
        }

        public FragmentAnim$AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            SpecialEffectsController.Operation operation = this.mOperation;
            FragmentAnim$AnimationOrAnimator loadAnimation = AppOpsManagerCompat.loadAnimation(context, operation.mFragment, operation.mFinalState == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    /* loaded from: classes.dex */
    public static class SpecialEffectsInfo {
        public final SpecialEffectsController.Operation mOperation;
        public final CancellationSignal mSignal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        public void completeSpecialEffect() {
            SpecialEffectsController.Operation operation = this.mOperation;
            if (operation.mSpecialEffectsSignals.remove(this.mSignal) && operation.mSpecialEffectsSignals.isEmpty()) {
                operation.complete();
            }
        }

        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.mFragment.mView);
            SpecialEffectsController.Operation.State state2 = this.mOperation.mFinalState;
            return from == state2 || !(from == (state = SpecialEffectsController.Operation.State.VISIBLE) || state2 == state);
        }
    }

    /* loaded from: classes.dex */
    public static class TransitionInfo extends SpecialEffectsInfo {
        public final boolean mOverlapAllowed;
        public final Object mSharedElementTransition;
        public final Object mTransition;

        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            Object obj2;
            if (operation.mFinalState == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    obj2 = operation.mFragment.getReenterTransition();
                } else {
                    operation.mFragment.getEnterTransition();
                    obj2 = null;
                }
                this.mTransition = obj2;
                if (z) {
                    Fragment.AnimationInfo animationInfo = operation.mFragment.mAnimationInfo;
                } else {
                    Fragment.AnimationInfo animationInfo2 = operation.mFragment.mAnimationInfo;
                }
                this.mOverlapAllowed = true;
            } else {
                if (z) {
                    obj = operation.mFragment.getReturnTransition();
                } else {
                    operation.mFragment.getExitTransition();
                    obj = null;
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.mFragment.getSharedElementReturnTransition();
            } else {
                operation.mFragment.getSharedElementEnterTransition();
                this.mSharedElementTransition = null;
            }
        }

        public final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (obj instanceof Transition) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.mOperation.mFragment + " is not a valid framework Transition or AndroidX Transition");
        }
    }

    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            if (!arrayList.contains(view)) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (view.getTransitionName() != null) {
                    arrayList.add(view);
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.SpecialEffectsController
    public void executeOperations(List<SpecialEffectsController.Operation> list, final boolean z) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String str;
        SpecialEffectsController.Operation.State state;
        HashMap hashMap;
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        ArrayList<String> arrayList5;
        ArrayList<String> arrayList6;
        final View view;
        boolean z2 = z;
        SpecialEffectsController.Operation.State state2 = SpecialEffectsController.Operation.State.GONE;
        SpecialEffectsController.Operation.State state3 = SpecialEffectsController.Operation.State.VISIBLE;
        final SpecialEffectsController.Operation operation = null;
        final SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation operation3 : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(operation3.mFragment.mView);
            int ordinal = operation3.mFinalState.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (!(ordinal == 2 || ordinal == 3)) {
                    }
                } else if (from != state3) {
                    operation2 = operation3;
                }
            }
            if (from == state3 && operation == null) {
                operation = operation3;
            }
        }
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        final ArrayList arrayList9 = new ArrayList(list);
        Iterator<SpecialEffectsController.Operation> it = list.iterator();
        while (it.hasNext()) {
            final SpecialEffectsController.Operation next = it.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            next.onStart();
            next.mSpecialEffectsSignals.add(cancellationSignal);
            arrayList7.add(new AnimationInfo(next, cancellationSignal, z2));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next.onStart();
            next.mSpecialEffectsSignals.add(cancellationSignal2);
            arrayList8.add(new TransitionInfo(next, cancellationSignal2, z2, !z2 ? next == operation2 : next == operation));
            next.mCompletionListeners.add(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (arrayList9.contains(next)) {
                        arrayList9.remove(next);
                        DefaultSpecialEffectsController defaultSpecialEffectsController = DefaultSpecialEffectsController.this;
                        SpecialEffectsController.Operation operation4 = next;
                        Objects.requireNonNull(defaultSpecialEffectsController);
                        operation4.mFinalState.applyState(operation4.mFragment.mView);
                    }
                }
            });
        }
        HashMap hashMap2 = new HashMap();
        Iterator it2 = arrayList8.iterator();
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        while (it2.hasNext()) {
            TransitionInfo transitionInfo = (TransitionInfo) it2.next();
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl(transitionInfo.mTransition);
                FragmentTransitionImpl handlingImpl2 = transitionInfo.getHandlingImpl(transitionInfo.mSharedElementTransition);
                if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                    if (handlingImpl == null) {
                        handlingImpl = handlingImpl2;
                    }
                    if (fragmentTransitionImpl == null) {
                        fragmentTransitionImpl = handlingImpl;
                    } else if (!(handlingImpl == null || fragmentTransitionImpl == handlingImpl)) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Mixing framework transitions and AndroidX transitions is not allowed. Fragment ");
                        outline13.append(transitionInfo.mOperation.mFragment);
                        outline13.append(" returned Transition ");
                        outline13.append(transitionInfo.mTransition);
                        outline13.append(" which uses a different Transition  type than other Fragments.");
                        throw new IllegalArgumentException(outline13.toString());
                    }
                } else {
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Mixing framework transitions and AndroidX transitions is not allowed. Fragment ");
                    outline132.append(transitionInfo.mOperation.mFragment);
                    outline132.append(" returned Transition ");
                    outline132.append(transitionInfo.mTransition);
                    outline132.append(" which uses a different Transition  type than its shared element transition ");
                    outline132.append(transitionInfo.mSharedElementTransition);
                    throw new IllegalArgumentException(outline132.toString());
                }
            }
        }
        if (fragmentTransitionImpl == null) {
            Iterator it3 = arrayList8.iterator();
            while (it3.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it3.next();
                hashMap2.put(transitionInfo2.mOperation, Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            state = state2;
            arrayList2 = arrayList7;
            arrayList = arrayList9;
            hashMap = hashMap2;
            str = "FragmentManager";
        } else {
            View view2 = new View(this.mContainer.getContext());
            Rect rect = new Rect();
            ArrayList<View> arrayList10 = new ArrayList<>();
            ArrayList<View> arrayList11 = new ArrayList<>();
            arrayList2 = arrayList7;
            ArrayMap arrayMap = new ArrayMap();
            Iterator it4 = arrayList8.iterator();
            Object obj = null;
            View view3 = null;
            final Rect rect2 = rect;
            boolean z3 = false;
            View view4 = view2;
            String str2 = "FragmentManager";
            SpecialEffectsController.Operation operation4 = operation;
            SpecialEffectsController.Operation operation5 = operation2;
            while (it4.hasNext()) {
                Object obj2 = ((TransitionInfo) it4.next()).mSharedElementTransition;
                if (!(obj2 != null) || operation4 == null || operation5 == null) {
                    arrayList10 = arrayList10;
                    state2 = state2;
                    arrayMap = arrayMap;
                    arrayList8 = arrayList8;
                    arrayList9 = arrayList9;
                    hashMap2 = hashMap2;
                    str2 = str2;
                    arrayList11 = arrayList11;
                    operation2 = operation2;
                    view4 = view4;
                    fragmentTransitionImpl = fragmentTransitionImpl;
                    rect2 = rect2;
                } else {
                    Object wrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(obj2));
                    Fragment.AnimationInfo animationInfo = operation5.mFragment.mAnimationInfo;
                    if (animationInfo == null || (arrayList3 = animationInfo.mSharedElementSourceNames) == null) {
                        arrayList3 = new ArrayList<>();
                    }
                    Fragment.AnimationInfo animationInfo2 = operation4.mFragment.mAnimationInfo;
                    if (animationInfo2 == null || (arrayList4 = animationInfo2.mSharedElementSourceNames) == null) {
                        arrayList4 = new ArrayList<>();
                    }
                    arrayList9 = arrayList9;
                    Fragment.AnimationInfo animationInfo3 = operation4.mFragment.mAnimationInfo;
                    if (animationInfo3 == null || (arrayList5 = animationInfo3.mSharedElementTargetNames) == null) {
                        arrayList5 = new ArrayList<>();
                    }
                    state2 = state2;
                    arrayList8 = arrayList8;
                    int i = 0;
                    while (i < arrayList5.size()) {
                        int indexOf = arrayList3.indexOf(arrayList5.get(i));
                        if (indexOf != -1) {
                            arrayList3.set(indexOf, arrayList4.get(i));
                        }
                        i++;
                        arrayList5 = arrayList5;
                    }
                    Fragment.AnimationInfo animationInfo4 = operation5.mFragment.mAnimationInfo;
                    if (animationInfo4 == null || (arrayList6 = animationInfo4.mSharedElementTargetNames) == null) {
                        arrayList6 = new ArrayList<>();
                    }
                    if (!z2) {
                        operation4.mFragment.getExitTransitionCallback();
                        operation5.mFragment.getEnterTransitionCallback();
                    } else {
                        operation4.mFragment.getEnterTransitionCallback();
                        operation5.mFragment.getExitTransitionCallback();
                    }
                    int i2 = 0;
                    for (int size = arrayList3.size(); i2 < size; size = size) {
                        arrayMap.put(arrayList3.get(i2), arrayList6.get(i2));
                        i2++;
                    }
                    ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
                    findNamedViews(arrayMap2, operation4.mFragment.mView);
                    MapCollections.retainAllHelper(arrayMap2, arrayList3);
                    MapCollections.retainAllHelper(arrayMap, arrayMap2.keySet());
                    final ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                    findNamedViews(arrayMap3, operation5.mFragment.mView);
                    MapCollections.retainAllHelper(arrayMap3, arrayList6);
                    MapCollections.retainAllHelper(arrayMap3, arrayMap.values());
                    FragmentTransition.retainValues(arrayMap, arrayMap3);
                    retainMatchingViews(arrayMap2, arrayMap.keySet());
                    retainMatchingViews(arrayMap3, arrayMap.values());
                    if (arrayMap.isEmpty()) {
                        arrayList10.clear();
                        arrayList11.clear();
                        obj = null;
                        rect2 = rect2;
                        arrayList10 = arrayList10;
                        arrayMap = arrayMap;
                        hashMap2 = hashMap2;
                        str2 = str2;
                        arrayList11 = arrayList11;
                        operation2 = operation2;
                        view4 = view4;
                        fragmentTransitionImpl = fragmentTransitionImpl;
                    } else {
                        FragmentTransition.callSharedElementStartEnd(operation5.mFragment, operation4.mFragment, z2, arrayMap2, true);
                        arrayList10 = arrayList10;
                        arrayMap = arrayMap;
                        arrayList11 = arrayList11;
                        str2 = str2;
                        fragmentTransitionImpl = fragmentTransitionImpl;
                        OneShotPreDrawListener.add(this.mContainer, new Runnable(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                            @Override // java.lang.Runnable
                            public void run() {
                                FragmentTransition.callSharedElementStartEnd(operation2.mFragment, operation.mFragment, z, arrayMap3, false);
                            }
                        });
                        Iterator it5 = ((MapCollections.ValuesCollection) arrayMap2.values()).iterator();
                        while (true) {
                            MapCollections.ArrayIterator arrayIterator = (MapCollections.ArrayIterator) it5;
                            if (!arrayIterator.hasNext()) {
                                break;
                            }
                            captureTransitioningViews(arrayList10, (View) arrayIterator.next());
                        }
                        if (!arrayList3.isEmpty()) {
                            View view5 = arrayMap2.get(arrayList3.get(0));
                            fragmentTransitionImpl.setEpicenter(wrapTransitionInSet, view5);
                            view3 = view5;
                        }
                        Iterator it6 = ((MapCollections.ValuesCollection) arrayMap3.values()).iterator();
                        while (true) {
                            MapCollections.ArrayIterator arrayIterator2 = (MapCollections.ArrayIterator) it6;
                            if (!arrayIterator2.hasNext()) {
                                break;
                            }
                            captureTransitioningViews(arrayList11, (View) arrayIterator2.next());
                        }
                        if (arrayList6.isEmpty() || (view = arrayMap3.get(arrayList6.get(0))) == null) {
                            rect2 = rect2;
                            view4 = view4;
                        } else {
                            rect2 = rect2;
                            OneShotPreDrawListener.add(this.mContainer, new Runnable(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                                @Override // java.lang.Runnable
                                public void run() {
                                    fragmentTransitionImpl.getBoundsOnScreen(view, rect2);
                                }
                            });
                            view4 = view4;
                            z3 = true;
                        }
                        fragmentTransitionImpl.setSharedElementTargets(wrapTransitionInSet, view4, arrayList10);
                        fragmentTransitionImpl.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList11);
                        Boolean bool = Boolean.TRUE;
                        hashMap2 = hashMap2;
                        operation = operation;
                        hashMap2.put(operation, bool);
                        operation2 = operation2;
                        hashMap2.put(operation2, bool);
                        obj = wrapTransitionInSet;
                        operation4 = operation;
                        operation5 = operation2;
                    }
                }
                state3 = state3;
                z2 = z;
            }
            SpecialEffectsController.Operation.State state4 = state2;
            SpecialEffectsController.Operation.State state5 = state3;
            arrayList = arrayList9;
            hashMap = hashMap2;
            String str3 = str2;
            SpecialEffectsController.Operation operation6 = operation2;
            View view6 = view4;
            ArrayList arrayList12 = new ArrayList();
            Iterator it7 = arrayList8.iterator();
            Object obj3 = null;
            Object obj4 = null;
            while (it7.hasNext()) {
                TransitionInfo transitionInfo3 = (TransitionInfo) it7.next();
                if (transitionInfo3.isVisibilityUnchanged()) {
                    it7 = it7;
                    operation6 = operation6;
                    hashMap.put(transitionInfo3.mOperation, Boolean.FALSE);
                    transitionInfo3.completeSpecialEffect();
                    view6 = view6;
                    obj = obj;
                    operation5 = operation5;
                    obj3 = obj3;
                    view3 = view3;
                    state5 = state5;
                    state4 = state4;
                } else {
                    it7 = it7;
                    operation6 = operation6;
                    Object cloneTransition = fragmentTransitionImpl.cloneTransition(transitionInfo3.mTransition);
                    SpecialEffectsController.Operation operation7 = transitionInfo3.mOperation;
                    boolean z4 = obj != null && (operation7 == operation4 || operation7 == operation5);
                    if (cloneTransition == null) {
                        if (!z4) {
                            hashMap.put(operation7, Boolean.FALSE);
                            transitionInfo3.completeSpecialEffect();
                        }
                        view6 = view6;
                        obj = obj;
                        obj3 = obj3;
                        view3 = view3;
                        state5 = state5;
                        state4 = state4;
                    } else {
                        obj = obj;
                        final ArrayList<View> arrayList13 = new ArrayList<>();
                        captureTransitioningViews(arrayList13, operation7.mFragment.mView);
                        if (z4) {
                            if (operation7 == operation4) {
                                arrayList13.removeAll(arrayList10);
                            } else {
                                arrayList13.removeAll(arrayList11);
                            }
                        }
                        if (arrayList13.isEmpty()) {
                            fragmentTransitionImpl.addTarget(cloneTransition, view6);
                            view6 = view6;
                            state4 = state4;
                        } else {
                            fragmentTransitionImpl.addTargets(cloneTransition, arrayList13);
                            fragmentTransitionImpl.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList13, null, null, null, null);
                            state4 = state4;
                            if (operation7.mFinalState == state4) {
                                arrayList.remove(operation7);
                                view6 = view6;
                                fragmentTransitionImpl.scheduleHideFragmentView(cloneTransition, operation7.mFragment.mView, arrayList13);
                                OneShotPreDrawListener.add(this.mContainer, new Runnable(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        FragmentTransition.setViewVisibility(arrayList13, 4);
                                    }
                                });
                            } else {
                                view6 = view6;
                            }
                        }
                        state5 = state5;
                        if (operation7.mFinalState == state5) {
                            arrayList12.addAll(arrayList13);
                            if (z3) {
                                fragmentTransitionImpl.setEpicenter(cloneTransition, rect2);
                            }
                            view3 = view3;
                        } else {
                            view3 = view3;
                            fragmentTransitionImpl.setEpicenter(cloneTransition, view3);
                        }
                        hashMap.put(operation7, Boolean.TRUE);
                        if (transitionInfo3.mOverlapAllowed) {
                            obj4 = fragmentTransitionImpl.mergeTransitionsTogether(obj4, cloneTransition, null);
                            obj3 = obj3;
                        } else {
                            obj3 = fragmentTransitionImpl.mergeTransitionsTogether(obj3, cloneTransition, null);
                        }
                    }
                    operation5 = operation6;
                }
            }
            SpecialEffectsController.Operation operation8 = operation6;
            state = state4;
            Object mergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(obj4, obj3, obj);
            Iterator it8 = arrayList8.iterator();
            while (it8.hasNext()) {
                final TransitionInfo transitionInfo4 = (TransitionInfo) it8.next();
                if (!transitionInfo4.isVisibilityUnchanged()) {
                    Object obj5 = transitionInfo4.mTransition;
                    SpecialEffectsController.Operation operation9 = transitionInfo4.mOperation;
                    boolean z5 = obj != null && (operation9 == operation4 || operation9 == operation8);
                    if (obj5 != null || z5) {
                        ViewGroup viewGroup = this.mContainer;
                        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                        if (!viewGroup.isLaidOut()) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                StringBuilder outline133 = GeneratedOutlineSupport.outline13("SpecialEffectsController: Container ");
                                outline133.append(this.mContainer);
                                outline133.append(" has not been laid out. Completing operation ");
                                outline133.append(operation9);
                                str3 = str3;
                                Log.v(str3, outline133.toString());
                            } else {
                                str3 = str3;
                            }
                            transitionInfo4.completeSpecialEffect();
                        } else {
                            fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo4.mOperation.mFragment, mergeTransitionsInSequence, transitionInfo4.mSignal, new Runnable(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                                @Override // java.lang.Runnable
                                public void run() {
                                    transitionInfo4.completeSpecialEffect();
                                }
                            });
                            operation4 = operation4;
                            operation8 = operation8;
                        }
                    } else {
                        str3 = str3;
                    }
                    operation8 = operation8;
                }
            }
            str = str3;
            ViewGroup viewGroup2 = this.mContainer;
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            if (viewGroup2.isLaidOut()) {
                FragmentTransition.setViewVisibility(arrayList12, 4);
                ArrayList<String> prepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList11);
                fragmentTransitionImpl.beginDelayedTransition(this.mContainer, mergeTransitionsInSequence);
                fragmentTransitionImpl.setNameOverridesReordered(this.mContainer, arrayList10, arrayList11, prepareSetNameOverridesReordered, arrayMap);
                FragmentTransition.setViewVisibility(arrayList12, 0);
                fragmentTransitionImpl.swapSharedElementTargets(obj, arrayList10, arrayList11);
            }
        }
        boolean containsValue = hashMap.containsValue(Boolean.TRUE);
        final ViewGroup viewGroup3 = this.mContainer;
        Context context = viewGroup3.getContext();
        ArrayList arrayList14 = new ArrayList();
        Iterator it9 = arrayList2.iterator();
        boolean z6 = false;
        while (it9.hasNext()) {
            final AnimationInfo animationInfo5 = (AnimationInfo) it9.next();
            if (animationInfo5.isVisibilityUnchanged()) {
                animationInfo5.completeSpecialEffect();
            } else {
                FragmentAnim$AnimationOrAnimator animation = animationInfo5.getAnimation(context);
                if (animation == null) {
                    animationInfo5.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList14.add(animationInfo5);
                    } else {
                        final SpecialEffectsController.Operation operation10 = animationInfo5.mOperation;
                        Fragment fragment = operation10.mFragment;
                        if (Boolean.TRUE.equals(hashMap.get(operation10))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(str, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo5.completeSpecialEffect();
                        } else {
                            final boolean z7 = operation10.mFinalState == state;
                            if (z7) {
                                arrayList.remove(operation10);
                            }
                            final View view7 = fragment.mView;
                            viewGroup3.startViewTransition(view7);
                            animator.addListener(new AnimatorListenerAdapter(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    viewGroup3.endViewTransition(view7);
                                    if (z7) {
                                        operation10.mFinalState.applyState(view7);
                                    }
                                    animationInfo5.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(view7);
                            animator.start();
                            animationInfo5.mSignal.setOnCancelListener(new CancellationSignal.OnCancelListener(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            z6 = true;
                            arrayList = arrayList;
                            state = state;
                            hashMap = hashMap;
                        }
                    }
                }
            }
        }
        Iterator it10 = arrayList14.iterator();
        while (it10.hasNext()) {
            final AnimationInfo animationInfo6 = (AnimationInfo) it10.next();
            SpecialEffectsController.Operation operation11 = animationInfo6.mOperation;
            Fragment fragment2 = operation11.mFragment;
            if (containsValue) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(str, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo6.completeSpecialEffect();
            } else if (z6) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(str, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo6.completeSpecialEffect();
            } else {
                final View view8 = fragment2.mView;
                FragmentAnim$AnimationOrAnimator animation2 = animationInfo6.getAnimation(context);
                Objects.requireNonNull(animation2);
                Animation animation3 = animation2.animation;
                Objects.requireNonNull(animation3);
                if (operation11.mFinalState != SpecialEffectsController.Operation.State.REMOVED) {
                    view8.startAnimation(animation3);
                    animationInfo6.completeSpecialEffect();
                } else {
                    viewGroup3.startViewTransition(view8);
                    FragmentAnim$EndViewTransitionAnimation fragmentAnim$EndViewTransitionAnimation = new FragmentAnim$EndViewTransitionAnimation(animation3, viewGroup3, view8);
                    fragmentAnim$EndViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation4) {
                            viewGroup3.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    AnonymousClass4 r0 = AnonymousClass4.this;
                                    viewGroup3.endViewTransition(view8);
                                    animationInfo6.completeSpecialEffect();
                                }
                            });
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation4) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation4) {
                        }
                    });
                    view8.startAnimation(fragmentAnim$EndViewTransitionAnimation);
                }
                animationInfo6.mSignal.setOnCancelListener(new CancellationSignal.OnCancelListener(this) { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view8.clearAnimation();
                        viewGroup3.endViewTransition(view8);
                        animationInfo6.completeSpecialEffect();
                    }
                });
            }
        }
        Iterator it11 = arrayList.iterator();
        while (it11.hasNext()) {
            SpecialEffectsController.Operation operation12 = (SpecialEffectsController.Operation) it11.next();
            operation12.mFinalState.applyState(operation12.mFragment.mView);
        }
        arrayList.clear();
    }

    public void findNamedViews(Map<String, View> map, View view) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        String transitionName = view.getTransitionName();
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    public void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator it = ((MapCollections.EntrySet) arrayMap.entrySet()).iterator();
        while (true) {
            MapCollections.MapIterator mapIterator = (MapCollections.MapIterator) it;
            if (mapIterator.hasNext()) {
                mapIterator.next();
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (!collection.contains(((View) mapIterator.getValue()).getTransitionName())) {
                    mapIterator.remove();
                }
            } else {
                return;
            }
        }
    }
}

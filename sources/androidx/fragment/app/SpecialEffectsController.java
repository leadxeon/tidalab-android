package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public abstract class SpecialEffectsController {
    public final ViewGroup mContainer;
    public final ArrayList<Operation> mPendingOperations = new ArrayList<>();
    public final ArrayList<Operation> mRunningOperations = new ArrayList<>();
    public boolean mOperationDirectionIsPop = false;
    public boolean mIsContainerPostponed = false;

    /* loaded from: classes.dex */
    public static class FragmentStateManagerOperation extends Operation {
        public final FragmentStateManager mFragmentStateManager;

        public FragmentStateManagerOperation(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
            super(state, lifecycleImpact, fragmentStateManager.mFragment, cancellationSignal);
            this.mFragmentStateManager = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void onStart() {
            if (this.mLifecycleImpact == Operation.LifecycleImpact.ADDING) {
                Fragment fragment = this.mFragmentStateManager.mFragment;
                View findFocus = fragment.mView.findFocus();
                if (findFocus != null) {
                    fragment.ensureAnimationInfo().mFocusedView = findFocus;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + fragment);
                    }
                }
                View requireView = this.mFragment.requireView();
                if (requireView.getParent() == null) {
                    this.mFragmentStateManager.addViewToContainer();
                    requireView.setAlpha(0.0f);
                }
                if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                    requireView.setVisibility(4);
                }
                Fragment.AnimationInfo animationInfo = fragment.mAnimationInfo;
                requireView.setAlpha(animationInfo == null ? 1.0f : animationInfo.mPostOnViewCreatedAlpha);
            }
        }
    }

    public SpecialEffectsController(ViewGroup viewGroup) {
        this.mContainer = viewGroup;
    }

    public static SpecialEffectsController getOrCreateController(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return getOrCreateController(viewGroup, fragmentManager.getSpecialEffectsControllerFactory());
    }

    public final void enqueue(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager) {
        synchronized (this.mPendingOperations) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            Operation findPendingOperation = findPendingOperation(fragmentStateManager.mFragment);
            if (findPendingOperation != null) {
                findPendingOperation.mergeWith(state, lifecycleImpact);
                return;
            }
            final FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager, cancellationSignal);
            this.mPendingOperations.add(fragmentStateManagerOperation);
            fragmentStateManagerOperation.mCompletionListeners.add(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SpecialEffectsController.this.mPendingOperations.contains(fragmentStateManagerOperation)) {
                        FragmentStateManagerOperation fragmentStateManagerOperation2 = fragmentStateManagerOperation;
                        fragmentStateManagerOperation2.mFinalState.applyState(fragmentStateManagerOperation2.mFragment.mView);
                    }
                }
            });
            fragmentStateManagerOperation.mCompletionListeners.add(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                @Override // java.lang.Runnable
                public void run() {
                    SpecialEffectsController.this.mPendingOperations.remove(fragmentStateManagerOperation);
                    SpecialEffectsController.this.mRunningOperations.remove(fragmentStateManagerOperation);
                }
            });
        }
    }

    public abstract void executeOperations(List<Operation> list, boolean z);

    public void executePendingOperations() {
        if (!this.mIsContainerPostponed) {
            ViewGroup viewGroup = this.mContainer;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (!viewGroup.isAttachedToWindow()) {
                forceCompleteAllOperations();
                this.mOperationDirectionIsPop = false;
                return;
            }
            synchronized (this.mPendingOperations) {
                if (!this.mPendingOperations.isEmpty()) {
                    ArrayList arrayList = new ArrayList(this.mRunningOperations);
                    this.mRunningOperations.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Operation operation = (Operation) it.next();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + operation);
                        }
                        operation.cancel();
                        if (!operation.mIsComplete) {
                            this.mRunningOperations.add(operation);
                        }
                    }
                    updateFinalState();
                    ArrayList arrayList2 = new ArrayList(this.mPendingOperations);
                    this.mPendingOperations.clear();
                    this.mRunningOperations.addAll(arrayList2);
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        ((Operation) it2.next()).onStart();
                    }
                    executeOperations(arrayList2, this.mOperationDirectionIsPop);
                    this.mOperationDirectionIsPop = false;
                }
            }
        }
    }

    public final Operation findPendingOperation(Fragment fragment) {
        Iterator<Operation> it = this.mPendingOperations.iterator();
        while (it.hasNext()) {
            Operation next = it.next();
            if (next.mFragment.equals(fragment) && !next.mIsCanceled) {
                return next;
            }
        }
        return null;
    }

    public void forceCompleteAllOperations() {
        String str;
        String str2;
        ViewGroup viewGroup = this.mContainer;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        boolean isAttachedToWindow = viewGroup.isAttachedToWindow();
        synchronized (this.mPendingOperations) {
            updateFinalState();
            Iterator<Operation> it = this.mPendingOperations.iterator();
            while (it.hasNext()) {
                it.next().onStart();
            }
            Iterator it2 = new ArrayList(this.mRunningOperations).iterator();
            while (it2.hasNext()) {
                Operation operation = (Operation) it2.next();
                if (FragmentManager.isLoggingEnabled(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str2 = HttpUrl.FRAGMENT_ENCODE_SET;
                    } else {
                        str2 = "Container " + this.mContainer + " is not attached to window. ";
                    }
                    sb.append(str2);
                    sb.append("Cancelling running operation ");
                    sb.append(operation);
                    Log.v("FragmentManager", sb.toString());
                }
                operation.cancel();
            }
            Iterator it3 = new ArrayList(this.mPendingOperations).iterator();
            while (it3.hasNext()) {
                Operation operation2 = (Operation) it3.next();
                if (FragmentManager.isLoggingEnabled(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str = HttpUrl.FRAGMENT_ENCODE_SET;
                    } else {
                        str = "Container " + this.mContainer + " is not attached to window. ";
                    }
                    sb2.append(str);
                    sb2.append("Cancelling pending operation ");
                    sb2.append(operation2);
                    Log.v("FragmentManager", sb2.toString());
                }
                operation2.cancel();
            }
        }
    }

    public void markPostponedState() {
        synchronized (this.mPendingOperations) {
            updateFinalState();
            this.mIsContainerPostponed = false;
            int size = this.mPendingOperations.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Operation operation = this.mPendingOperations.get(size);
                Operation.State from = Operation.State.from(operation.mFragment.mView);
                Operation.State state = operation.mFinalState;
                Operation.State state2 = Operation.State.VISIBLE;
                if (state == state2 && from != state2) {
                    this.mIsContainerPostponed = operation.mFragment.isPostponed();
                    break;
                }
                size--;
            }
        }
    }

    public final void updateFinalState() {
        Iterator<Operation> it = this.mPendingOperations.iterator();
        while (it.hasNext()) {
            Operation next = it.next();
            if (next.mLifecycleImpact == Operation.LifecycleImpact.ADDING) {
                next.mergeWith(Operation.State.from(next.mFragment.requireView().getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Operation {
        public State mFinalState;
        public final Fragment mFragment;
        public LifecycleImpact mLifecycleImpact;
        public final List<Runnable> mCompletionListeners = new ArrayList();
        public final HashSet<CancellationSignal> mSpecialEffectsSignals = new HashSet<>();
        public boolean mIsCanceled = false;
        public boolean mIsComplete = false;

        /* loaded from: classes.dex */
        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        public Operation(State state, LifecycleImpact lifecycleImpact, Fragment fragment, CancellationSignal cancellationSignal) {
            this.mFinalState = state;
            this.mLifecycleImpact = lifecycleImpact;
            this.mFragment = fragment;
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.cancel();
                }
            });
        }

        public final void cancel() {
            if (!this.mIsCanceled) {
                this.mIsCanceled = true;
                if (this.mSpecialEffectsSignals.isEmpty()) {
                    complete();
                    return;
                }
                Iterator it = new ArrayList(this.mSpecialEffectsSignals).iterator();
                while (it.hasNext()) {
                    ((CancellationSignal) it.next()).cancel();
                }
            }
        }

        public void complete() {
            if (!this.mIsComplete) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
                }
                this.mIsComplete = true;
                for (Runnable runnable : this.mCompletionListeners) {
                    runnable.run();
                }
            }
        }

        public final void mergeWith(State state, LifecycleImpact lifecycleImpact) {
            State state2 = State.REMOVED;
            int ordinal = lifecycleImpact.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("SpecialEffectsController: For fragment ");
                            outline13.append(this.mFragment);
                            outline13.append(" mFinalState = ");
                            outline13.append(this.mFinalState);
                            outline13.append(" -> REMOVED. mLifecycleImpact  = ");
                            outline13.append(this.mLifecycleImpact);
                            outline13.append(" to REMOVING.");
                            Log.v("FragmentManager", outline13.toString());
                        }
                        this.mFinalState = state2;
                        this.mLifecycleImpact = LifecycleImpact.REMOVING;
                    }
                } else if (this.mFinalState == state2) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        StringBuilder outline132 = GeneratedOutlineSupport.outline13("SpecialEffectsController: For fragment ");
                        outline132.append(this.mFragment);
                        outline132.append(" mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = ");
                        outline132.append(this.mLifecycleImpact);
                        outline132.append(" to ADDING.");
                        Log.v("FragmentManager", outline132.toString());
                    }
                    this.mFinalState = State.VISIBLE;
                    this.mLifecycleImpact = LifecycleImpact.ADDING;
                }
            } else if (this.mFinalState != state2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    StringBuilder outline133 = GeneratedOutlineSupport.outline13("SpecialEffectsController: For fragment ");
                    outline133.append(this.mFragment);
                    outline133.append(" mFinalState = ");
                    outline133.append(this.mFinalState);
                    outline133.append(" -> ");
                    outline133.append(state);
                    outline133.append(". ");
                    Log.v("FragmentManager", outline133.toString());
                }
                this.mFinalState = state;
            }
        }

        public void onStart() {
        }

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.mFinalState + "} {mLifecycleImpact = " + this.mLifecycleImpact + "} {mFragment = " + this.mFragment + "}";
        }

        /* loaded from: classes.dex */
        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            public static State from(View view) {
                if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
                    return INVISIBLE;
                }
                return from(view.getVisibility());
            }

            public void applyState(View view) {
                int ordinal = ordinal();
                if (ordinal == 0) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                    }
                } else if (ordinal == 1) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                } else if (ordinal == 2) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                } else if (ordinal == 3) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                    }
                    view.setVisibility(4);
                }
            }

            public static State from(int i) {
                if (i == 0) {
                    return VISIBLE;
                }
                if (i == 4) {
                    return INVISIBLE;
                }
                if (i == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unknown visibility ", i));
            }
        }
    }

    public static SpecialEffectsController getOrCreateController(ViewGroup viewGroup, SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        Objects.requireNonNull((FragmentManager.AnonymousClass4) specialEffectsControllerFactory);
        DefaultSpecialEffectsController defaultSpecialEffectsController = new DefaultSpecialEffectsController(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, defaultSpecialEffectsController);
        return defaultSpecialEffectsController;
    }
}

package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.SpecialEffectsController;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistryController;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class FragmentStateManager {
    public final FragmentLifecycleCallbacksDispatcher mDispatcher;
    public final Fragment mFragment;
    public final FragmentStore mFragmentStore;
    public boolean mMovingToState = false;
    public int mFragmentManagerState = -1;

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
    }

    public void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto ACTIVITY_CREATED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        Bundle bundle = fragment.mSavedFragmentState;
        fragment.mChildFragmentManager.noteStateNotSaved();
        fragment.mState = 3;
        fragment.mCalled = false;
        fragment.onActivityCreated();
        if (fragment.mCalled) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + fragment);
            }
            View view = fragment.mView;
            if (view != null) {
                Bundle bundle2 = fragment.mSavedFragmentState;
                SparseArray<Parcelable> sparseArray = fragment.mSavedViewState;
                if (sparseArray != null) {
                    view.restoreHierarchyState(sparseArray);
                    fragment.mSavedViewState = null;
                }
                if (fragment.mView != null) {
                    FragmentViewLifecycleOwner fragmentViewLifecycleOwner = fragment.mViewLifecycleOwner;
                    fragmentViewLifecycleOwner.mSavedStateRegistryController.performRestore(fragment.mSavedViewRegistryState);
                    fragment.mSavedViewRegistryState = null;
                }
                fragment.mCalled = false;
                fragment.onViewStateRestored(bundle2);
                if (!fragment.mCalled) {
                    throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment, " did not call through to super.onViewStateRestored()"));
                } else if (fragment.mView != null) {
                    fragment.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
                }
            }
            fragment.mSavedFragmentState = null;
            FragmentManager fragmentManager = fragment.mChildFragmentManager;
            fragmentManager.mStateSaved = false;
            fragmentManager.mStopped = false;
            fragmentManager.mNonConfig.mIsStateSaved = false;
            fragmentManager.dispatchStateChange(4);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment2 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentActivityCreated(fragment2, fragment2.mSavedFragmentState, false);
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment, " did not call through to super.onActivityCreated()"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
        if (r1 >= r0.mAdded.size()) goto L_0x004f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        r4 = r0.mAdded.get(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r4.mContainer != r2) goto L_0x0031;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
        r4 = r4.mView;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
        if (r4 == null) goto L_0x0031;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3 = r2.indexOfChild(r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addViewToContainer() {
        /*
            r7 = this;
            androidx.fragment.app.FragmentStore r0 = r7.mFragmentStore
            androidx.fragment.app.Fragment r1 = r7.mFragment
            java.util.Objects.requireNonNull(r0)
            android.view.ViewGroup r2 = r1.mContainer
            r3 = -1
            if (r2 != 0) goto L_0x000d
            goto L_0x004f
        L_0x000d:
            java.util.ArrayList<androidx.fragment.app.Fragment> r4 = r0.mAdded
            int r1 = r4.indexOf(r1)
            int r4 = r1 + (-1)
        L_0x0015:
            if (r4 < 0) goto L_0x0031
            java.util.ArrayList<androidx.fragment.app.Fragment> r5 = r0.mAdded
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.Fragment r5 = (androidx.fragment.app.Fragment) r5
            android.view.ViewGroup r6 = r5.mContainer
            if (r6 != r2) goto L_0x002e
            android.view.View r5 = r5.mView
            if (r5 == 0) goto L_0x002e
            int r0 = r2.indexOfChild(r5)
            int r3 = r0 + 1
            goto L_0x004f
        L_0x002e:
            int r4 = r4 + (-1)
            goto L_0x0015
        L_0x0031:
            int r1 = r1 + 1
            java.util.ArrayList<androidx.fragment.app.Fragment> r4 = r0.mAdded
            int r4 = r4.size()
            if (r1 >= r4) goto L_0x004f
            java.util.ArrayList<androidx.fragment.app.Fragment> r4 = r0.mAdded
            java.lang.Object r4 = r4.get(r1)
            androidx.fragment.app.Fragment r4 = (androidx.fragment.app.Fragment) r4
            android.view.ViewGroup r5 = r4.mContainer
            if (r5 != r2) goto L_0x0031
            android.view.View r4 = r4.mView
            if (r4 == 0) goto L_0x0031
            int r3 = r2.indexOfChild(r4)
        L_0x004f:
            androidx.fragment.app.Fragment r0 = r7.mFragment
            android.view.ViewGroup r1 = r0.mContainer
            android.view.View r0 = r0.mView
            r1.addView(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentStateManager.addViewToContainer():void");
    }

    public void attach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto ATTACHED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        Fragment fragment2 = fragment.mTarget;
        FragmentStateManager fragmentStateManager = null;
        if (fragment2 != null) {
            FragmentStateManager fragmentStateManager2 = this.mFragmentStore.getFragmentStateManager(fragment2.mWho);
            if (fragmentStateManager2 != null) {
                Fragment fragment3 = this.mFragment;
                fragment3.mTargetWho = fragment3.mTarget.mWho;
                fragment3.mTarget = null;
                fragmentStateManager = fragmentStateManager2;
            } else {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Fragment ");
                outline132.append(this.mFragment);
                outline132.append(" declared target fragment ");
                outline132.append(this.mFragment.mTarget);
                outline132.append(" that does not belong to this FragmentManager!");
                throw new IllegalStateException(outline132.toString());
            }
        } else {
            String str = fragment.mTargetWho;
            if (str != null && (fragmentStateManager = this.mFragmentStore.getFragmentStateManager(str)) == null) {
                StringBuilder outline133 = GeneratedOutlineSupport.outline13("Fragment ");
                outline133.append(this.mFragment);
                outline133.append(" declared target fragment ");
                throw new IllegalStateException(GeneratedOutlineSupport.outline11(outline133, this.mFragment.mTargetWho, " that does not belong to this FragmentManager!"));
            }
        }
        if (fragmentStateManager != null) {
            fragmentStateManager.moveToExpectedState();
        }
        Fragment fragment4 = this.mFragment;
        FragmentManager fragmentManager = fragment4.mFragmentManager;
        fragment4.mHost = fragmentManager.mHost;
        fragment4.mParentFragment = fragmentManager.mParent;
        this.mDispatcher.dispatchOnFragmentPreAttached(fragment4, false);
        Fragment fragment5 = this.mFragment;
        Iterator<Fragment.OnPreAttachedListener> it = fragment5.mOnPreAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().onPreAttached();
        }
        fragment5.mOnPreAttachedListeners.clear();
        fragment5.mChildFragmentManager.attachController(fragment5.mHost, fragment5.createFragmentContainer(), fragment5);
        fragment5.mState = 0;
        fragment5.mCalled = false;
        fragment5.onAttach(fragment5.mHost.mContext);
        if (fragment5.mCalled) {
            FragmentManager fragmentManager2 = fragment5.mFragmentManager;
            Iterator<FragmentOnAttachListener> it2 = fragmentManager2.mOnAttachListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onAttachFragment(fragmentManager2, fragment5);
            }
            FragmentManager fragmentManager3 = fragment5.mChildFragmentManager;
            fragmentManager3.mStateSaved = false;
            fragmentManager3.mStopped = false;
            fragmentManager3.mNonConfig.mIsStateSaved = false;
            fragmentManager3.dispatchStateChange(0);
            this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment5, " did not call through to super.onAttach()"));
    }

    public int computeExpectedState() {
        SpecialEffectsController.Operation operation;
        Fragment fragment = this.mFragment;
        if (fragment.mFragmentManager == null) {
            return fragment.mState;
        }
        int i = this.mFragmentManagerState;
        int ordinal = fragment.mMaxState.ordinal();
        if (ordinal == 1) {
            i = Math.min(i, 0);
        } else if (ordinal == 2) {
            i = Math.min(i, 1);
        } else if (ordinal == 3) {
            i = Math.min(i, 5);
        } else if (ordinal != 4) {
            i = Math.min(i, -1);
        }
        Fragment fragment2 = this.mFragment;
        if (fragment2.mFromLayout) {
            if (fragment2.mInLayout) {
                i = Math.max(this.mFragmentManagerState, 2);
                View view = this.mFragment.mView;
                if (view != null && view.getParent() == null) {
                    i = Math.min(i, 2);
                }
            } else {
                i = this.mFragmentManagerState < 4 ? Math.min(i, fragment2.mState) : Math.min(i, 1);
            }
        }
        if (!this.mFragment.mAdded) {
            i = Math.min(i, 1);
        }
        Fragment fragment3 = this.mFragment;
        ViewGroup viewGroup = fragment3.mContainer;
        SpecialEffectsController.Operation.LifecycleImpact lifecycleImpact = null;
        if (viewGroup != null) {
            SpecialEffectsController orCreateController = SpecialEffectsController.getOrCreateController(viewGroup, fragment3.getParentFragmentManager().getSpecialEffectsControllerFactory());
            Objects.requireNonNull(orCreateController);
            SpecialEffectsController.Operation findPendingOperation = orCreateController.findPendingOperation(this.mFragment);
            if (findPendingOperation != null) {
                lifecycleImpact = findPendingOperation.mLifecycleImpact;
            } else {
                Fragment fragment4 = this.mFragment;
                Iterator<SpecialEffectsController.Operation> it = orCreateController.mRunningOperations.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        operation = null;
                        break;
                    }
                    operation = it.next();
                    if (operation.mFragment.equals(fragment4) && !operation.mIsCanceled) {
                        break;
                    }
                }
                if (operation != null) {
                    lifecycleImpact = operation.mLifecycleImpact;
                }
            }
        }
        if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            i = Math.min(i, 6);
        } else if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            i = Math.max(i, 3);
        } else {
            Fragment fragment5 = this.mFragment;
            if (fragment5.mRemoving) {
                if (fragment5.isInBackStack()) {
                    i = Math.min(i, 1);
                } else {
                    i = Math.min(i, -1);
                }
            }
        }
        Fragment fragment6 = this.mFragment;
        if (fragment6.mDeferStart && fragment6.mState < 5) {
            i = Math.min(i, 4);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder outline15 = GeneratedOutlineSupport.outline15("computeExpectedState() of ", i, " for ");
            outline15.append(this.mFragment);
            Log.v("FragmentManager", outline15.toString());
        }
        return i;
    }

    public void create() {
        Parcelable parcelable;
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto CREATED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        if (!fragment.mIsCreated) {
            this.mDispatcher.dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
            final Fragment fragment2 = this.mFragment;
            Bundle bundle = fragment2.mSavedFragmentState;
            fragment2.mChildFragmentManager.noteStateNotSaved();
            fragment2.mState = 1;
            fragment2.mCalled = false;
            fragment2.mLifecycleRegistry.addObserver(new LifecycleEventObserver() { // from class: androidx.fragment.app.Fragment.5
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    View view;
                    if (event == Lifecycle.Event.ON_STOP && (view = fragment2.mView) != null) {
                        view.cancelPendingInputEvents();
                    }
                }
            });
            fragment2.mSavedStateRegistryController.performRestore(bundle);
            fragment2.onCreate(bundle);
            fragment2.mIsCreated = true;
            if (fragment2.mCalled) {
                fragment2.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentCreated(fragment3, fragment3.mSavedFragmentState, false);
                return;
            }
            throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment2, " did not call through to super.onCreate()"));
        }
        Bundle bundle2 = fragment.mSavedFragmentState;
        if (!(bundle2 == null || (parcelable = bundle2.getParcelable("android:support:fragments")) == null)) {
            fragment.mChildFragmentManager.restoreSaveState(parcelable);
            fragment.mChildFragmentManager.dispatchCreate();
        }
        this.mFragment.mState = 1;
    }

    public void createView() {
        String str;
        if (!this.mFragment.mFromLayout) {
            if (FragmentManager.isLoggingEnabled(3)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto CREATE_VIEW: ");
                outline13.append(this.mFragment);
                Log.d("FragmentManager", outline13.toString());
            }
            Fragment fragment = this.mFragment;
            LayoutInflater onGetLayoutInflater = fragment.onGetLayoutInflater(fragment.mSavedFragmentState);
            fragment.mLayoutInflater = onGetLayoutInflater;
            ViewGroup viewGroup = null;
            Fragment fragment2 = this.mFragment;
            ViewGroup viewGroup2 = fragment2.mContainer;
            if (viewGroup2 != null) {
                viewGroup = viewGroup2;
            } else {
                int i = fragment2.mContainerId;
                if (i != 0) {
                    if (i != -1) {
                        viewGroup = (ViewGroup) fragment2.mFragmentManager.mContainer.onFindViewById(i);
                        if (viewGroup == null) {
                            Fragment fragment3 = this.mFragment;
                            if (!fragment3.mRestored) {
                                try {
                                    str = fragment3.getResources().getResourceName(this.mFragment.mContainerId);
                                } catch (Resources.NotFoundException unused) {
                                    str = "unknown";
                                }
                                StringBuilder outline132 = GeneratedOutlineSupport.outline13("No view found for id 0x");
                                outline132.append(Integer.toHexString(this.mFragment.mContainerId));
                                outline132.append(" (");
                                outline132.append(str);
                                outline132.append(") for fragment ");
                                outline132.append(this.mFragment);
                                throw new IllegalArgumentException(outline132.toString());
                            }
                        }
                    } else {
                        StringBuilder outline133 = GeneratedOutlineSupport.outline13("Cannot create fragment ");
                        outline133.append(this.mFragment);
                        outline133.append(" for a container view with no id");
                        throw new IllegalArgumentException(outline133.toString());
                    }
                }
            }
            Fragment fragment4 = this.mFragment;
            fragment4.mContainer = viewGroup;
            fragment4.performCreateView(onGetLayoutInflater, viewGroup, fragment4.mSavedFragmentState);
            View view = this.mFragment.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment5 = this.mFragment;
                fragment5.mView.setTag(R.id.fragment_container_view_tag, fragment5);
                if (viewGroup != null) {
                    addViewToContainer();
                }
                Fragment fragment6 = this.mFragment;
                if (fragment6.mHidden) {
                    fragment6.mView.setVisibility(8);
                }
                View view2 = this.mFragment.mView;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (view2.isAttachedToWindow()) {
                    this.mFragment.mView.requestApplyInsets();
                } else {
                    final View view3 = this.mFragment.mView;
                    view3.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener(this) { // from class: androidx.fragment.app.FragmentStateManager.1
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view4) {
                            view3.removeOnAttachStateChangeListener(this);
                            View view5 = view3;
                            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                            view5.requestApplyInsets();
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view4) {
                        }
                    });
                }
                Fragment fragment7 = this.mFragment;
                fragment7.onViewCreated();
                fragment7.mChildFragmentManager.dispatchStateChange(2);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment8 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment8, fragment8.mView, fragment8.mSavedFragmentState, false);
                int visibility = this.mFragment.mView.getVisibility();
                this.mFragment.ensureAnimationInfo().mPostOnViewCreatedAlpha = this.mFragment.mView.getAlpha();
                Fragment fragment9 = this.mFragment;
                if (fragment9.mContainer != null && visibility == 0) {
                    View findFocus = fragment9.mView.findFocus();
                    if (findFocus != null) {
                        this.mFragment.ensureAnimationInfo().mFocusedView = findFocus;
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.mFragment);
                        }
                    }
                    this.mFragment.mView.setAlpha(0.0f);
                }
            }
            this.mFragment.mState = 2;
        }
    }

    public void destroy() {
        Fragment findActiveFragment;
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("movefrom CREATED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        boolean z = true;
        boolean z2 = fragment.mRemoving && !fragment.isInBackStack();
        if (z2 || this.mFragmentStore.mNonConfig.shouldDestroy(this.mFragment)) {
            FragmentHostCallback<?> fragmentHostCallback = this.mFragment.mHost;
            if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                z = this.mFragmentStore.mNonConfig.mHasBeenCleared;
            } else {
                Context context = fragmentHostCallback.mContext;
                if (context instanceof Activity) {
                    z = true ^ ((Activity) context).isChangingConfigurations();
                }
            }
            if (z2 || z) {
                FragmentManagerViewModel fragmentManagerViewModel = this.mFragmentStore.mNonConfig;
                Fragment fragment2 = this.mFragment;
                Objects.requireNonNull(fragmentManagerViewModel);
                if (FragmentManager.isLoggingEnabled(3)) {
                    Log.d("FragmentManager", "Clearing non-config state for " + fragment2);
                }
                FragmentManagerViewModel fragmentManagerViewModel2 = fragmentManagerViewModel.mChildNonConfigs.get(fragment2.mWho);
                if (fragmentManagerViewModel2 != null) {
                    fragmentManagerViewModel2.onCleared();
                    fragmentManagerViewModel.mChildNonConfigs.remove(fragment2.mWho);
                }
                ViewModelStore viewModelStore = fragmentManagerViewModel.mViewModelStores.get(fragment2.mWho);
                if (viewModelStore != null) {
                    viewModelStore.clear();
                    fragmentManagerViewModel.mViewModelStores.remove(fragment2.mWho);
                }
            }
            Fragment fragment3 = this.mFragment;
            fragment3.mChildFragmentManager.dispatchDestroy();
            fragment3.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            fragment3.mState = 0;
            fragment3.mCalled = false;
            fragment3.mIsCreated = false;
            fragment3.onDestroy();
            if (fragment3.mCalled) {
                this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
                Iterator it = ((ArrayList) this.mFragmentStore.getActiveFragmentStateManagers()).iterator();
                while (it.hasNext()) {
                    FragmentStateManager fragmentStateManager = (FragmentStateManager) it.next();
                    if (fragmentStateManager != null) {
                        Fragment fragment4 = fragmentStateManager.mFragment;
                        if (this.mFragment.mWho.equals(fragment4.mTargetWho)) {
                            fragment4.mTarget = this.mFragment;
                            fragment4.mTargetWho = null;
                        }
                    }
                }
                Fragment fragment5 = this.mFragment;
                String str = fragment5.mTargetWho;
                if (str != null) {
                    fragment5.mTarget = this.mFragmentStore.findActiveFragment(str);
                }
                this.mFragmentStore.makeInactive(this);
                return;
            }
            throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment3, " did not call through to super.onDestroy()"));
        }
        String str2 = this.mFragment.mTargetWho;
        if (!(str2 == null || (findActiveFragment = this.mFragmentStore.findActiveFragment(str2)) == null || !findActiveFragment.mRetainInstance)) {
            this.mFragment.mTarget = findActiveFragment;
        }
        this.mFragment.mState = 0;
    }

    public void destroyFragmentView() {
        View view;
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("movefrom CREATE_VIEW: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        ViewGroup viewGroup = fragment.mContainer;
        if (!(viewGroup == null || (view = fragment.mView) == null)) {
            viewGroup.removeView(view);
        }
        this.mFragment.performDestroyView();
        this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
        Fragment fragment2 = this.mFragment;
        fragment2.mContainer = null;
        fragment2.mView = null;
        fragment2.mViewLifecycleOwner = null;
        fragment2.mViewLifecycleOwnerLiveData.setValue(null);
        this.mFragment.mInLayout = false;
    }

    public void detach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("movefrom ATTACHED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        fragment.mState = -1;
        fragment.mCalled = false;
        fragment.onDetach();
        fragment.mLayoutInflater = null;
        if (fragment.mCalled) {
            FragmentManager fragmentManager = fragment.mChildFragmentManager;
            if (!fragmentManager.mDestroyed) {
                fragmentManager.dispatchDestroy();
                fragment.mChildFragmentManager = new FragmentManagerImpl();
            }
            this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
            Fragment fragment2 = this.mFragment;
            fragment2.mState = -1;
            fragment2.mHost = null;
            fragment2.mParentFragment = null;
            fragment2.mFragmentManager = null;
            if ((fragment2.mRemoving && !fragment2.isInBackStack()) || this.mFragmentStore.mNonConfig.shouldDestroy(this.mFragment)) {
                if (FragmentManager.isLoggingEnabled(3)) {
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("initState called for fragment: ");
                    outline132.append(this.mFragment);
                    Log.d("FragmentManager", outline132.toString());
                }
                Fragment fragment3 = this.mFragment;
                Objects.requireNonNull(fragment3);
                fragment3.mLifecycleRegistry = new LifecycleRegistry(fragment3);
                fragment3.mSavedStateRegistryController = new SavedStateRegistryController(fragment3);
                fragment3.mWho = UUID.randomUUID().toString();
                fragment3.mAdded = false;
                fragment3.mRemoving = false;
                fragment3.mFromLayout = false;
                fragment3.mInLayout = false;
                fragment3.mRestored = false;
                fragment3.mBackStackNesting = 0;
                fragment3.mFragmentManager = null;
                fragment3.mChildFragmentManager = new FragmentManagerImpl();
                fragment3.mHost = null;
                fragment3.mFragmentId = 0;
                fragment3.mContainerId = 0;
                fragment3.mTag = null;
                fragment3.mHidden = false;
                fragment3.mDetached = false;
                return;
            }
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment, " did not call through to super.onDetach()"));
    }

    public void ensureInflatedView() {
        Fragment fragment = this.mFragment;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto CREATE_VIEW: ");
                outline13.append(this.mFragment);
                Log.d("FragmentManager", outline13.toString());
            }
            Fragment fragment2 = this.mFragment;
            LayoutInflater onGetLayoutInflater = fragment2.onGetLayoutInflater(fragment2.mSavedFragmentState);
            fragment2.mLayoutInflater = onGetLayoutInflater;
            fragment2.performCreateView(onGetLayoutInflater, null, this.mFragment.mSavedFragmentState);
            View view = this.mFragment.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.mFragment;
                fragment3.mView.setTag(R.id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                Fragment fragment5 = this.mFragment;
                fragment5.onViewCreated();
                fragment5.mChildFragmentManager.dispatchStateChange(2);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment6 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment6, fragment6.mView, fragment6.mSavedFragmentState, false);
                this.mFragment.mState = 2;
            }
        }
    }

    public void moveToExpectedState() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        SpecialEffectsController.Operation.LifecycleImpact lifecycleImpact = SpecialEffectsController.Operation.LifecycleImpact.NONE;
        if (!this.mMovingToState) {
            try {
                this.mMovingToState = true;
                while (true) {
                    int computeExpectedState = computeExpectedState();
                    Fragment fragment = this.mFragment;
                    int i = fragment.mState;
                    if (computeExpectedState == i) {
                        if (fragment.mHiddenChanged) {
                            if (!(fragment.mView == null || (viewGroup = fragment.mContainer) == null)) {
                                SpecialEffectsController orCreateController = SpecialEffectsController.getOrCreateController(viewGroup, fragment.getParentFragmentManager().getSpecialEffectsControllerFactory());
                                if (this.mFragment.mHidden) {
                                    Objects.requireNonNull(orCreateController);
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + this.mFragment);
                                    }
                                    orCreateController.enqueue(SpecialEffectsController.Operation.State.GONE, lifecycleImpact, this);
                                } else {
                                    Objects.requireNonNull(orCreateController);
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + this.mFragment);
                                    }
                                    orCreateController.enqueue(SpecialEffectsController.Operation.State.VISIBLE, lifecycleImpact, this);
                                }
                            }
                            Fragment fragment2 = this.mFragment;
                            FragmentManager fragmentManager = fragment2.mFragmentManager;
                            if (fragmentManager != null && fragment2.mAdded && fragmentManager.isMenuAvailable(fragment2)) {
                                fragmentManager.mNeedMenuInvalidate = true;
                            }
                            Fragment fragment3 = this.mFragment;
                            fragment3.mHiddenChanged = false;
                            fragment3.onHiddenChanged();
                        }
                        return;
                    } else if (computeExpectedState > i) {
                        switch (i + 1) {
                            case 0:
                                attach();
                                continue;
                            case 1:
                                create();
                                continue;
                            case 2:
                                ensureInflatedView();
                                createView();
                                continue;
                            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                                activityCreated();
                                continue;
                            case 4:
                                if (!(fragment.mView == null || (viewGroup2 = fragment.mContainer) == null)) {
                                    SpecialEffectsController orCreateController2 = SpecialEffectsController.getOrCreateController(viewGroup2, fragment.getParentFragmentManager().getSpecialEffectsControllerFactory());
                                    SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mFragment.mView.getVisibility());
                                    Objects.requireNonNull(orCreateController2);
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + this.mFragment);
                                    }
                                    orCreateController2.enqueue(from, SpecialEffectsController.Operation.LifecycleImpact.ADDING, this);
                                }
                                this.mFragment.mState = 4;
                                continue;
                            case 5:
                                start();
                                continue;
                            case 6:
                                fragment.mState = 6;
                                continue;
                            case 7:
                                resume();
                                continue;
                            default:
                                continue;
                        }
                    } else {
                        switch (i - 1) {
                            case -1:
                                detach();
                                continue;
                            case 0:
                                destroy();
                                continue;
                            case 1:
                                destroyFragmentView();
                                this.mFragment.mState = 1;
                                continue;
                            case 2:
                                fragment.mInLayout = false;
                                fragment.mState = 2;
                                continue;
                            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                                if (FragmentManager.isLoggingEnabled(3)) {
                                    Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.mFragment);
                                }
                                Fragment fragment4 = this.mFragment;
                                if (fragment4.mView != null && fragment4.mSavedViewState == null) {
                                    saveViewState();
                                }
                                Fragment fragment5 = this.mFragment;
                                if (!(fragment5.mView == null || (viewGroup3 = fragment5.mContainer) == null)) {
                                    SpecialEffectsController orCreateController3 = SpecialEffectsController.getOrCreateController(viewGroup3, fragment5.getParentFragmentManager().getSpecialEffectsControllerFactory());
                                    Objects.requireNonNull(orCreateController3);
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + this.mFragment);
                                    }
                                    orCreateController3.enqueue(SpecialEffectsController.Operation.State.REMOVED, SpecialEffectsController.Operation.LifecycleImpact.REMOVING, this);
                                }
                                this.mFragment.mState = 3;
                                continue;
                            case 4:
                                stop();
                                continue;
                            case 5:
                                fragment.mState = 5;
                                continue;
                            case 6:
                                pause();
                                continue;
                            default:
                                continue;
                        }
                    }
                }
            } finally {
                this.mMovingToState = false;
            }
        } else if (FragmentManager.isLoggingEnabled(2)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Ignoring re-entrant call to moveToExpectedState() for ");
            outline13.append(this.mFragment);
            Log.v("FragmentManager", outline13.toString());
        }
    }

    public void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("movefrom RESUMED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        fragment.mChildFragmentManager.dispatchStateChange(5);
        if (fragment.mView != null) {
            fragment.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }
        fragment.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        fragment.mState = 6;
        fragment.mCalled = false;
        fragment.mCalled = true;
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    public void restoreState(ClassLoader classLoader) {
        Bundle bundle = this.mFragment.mSavedFragmentState;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            Fragment fragment = this.mFragment;
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            Fragment fragment2 = this.mFragment;
            fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle("android:view_registry_state");
            Fragment fragment3 = this.mFragment;
            fragment3.mTargetWho = fragment3.mSavedFragmentState.getString("android:target_state");
            Fragment fragment4 = this.mFragment;
            if (fragment4.mTargetWho != null) {
                fragment4.mTargetRequestCode = fragment4.mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            Fragment fragment5 = this.mFragment;
            Objects.requireNonNull(fragment5);
            fragment5.mUserVisibleHint = fragment5.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            Fragment fragment6 = this.mFragment;
            if (!fragment6.mUserVisibleHint) {
                fragment6.mDeferStart = true;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void resume() {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentStateManager.resume():void");
    }

    public void saveViewState() {
        if (this.mFragment.mView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray;
            }
            Bundle bundle = new Bundle();
            this.mFragment.mViewLifecycleOwner.mSavedStateRegistryController.performSave(bundle);
            if (!bundle.isEmpty()) {
                this.mFragment.mSavedViewRegistryState = bundle;
            }
        }
    }

    public void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("moveto STARTED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        fragment.mChildFragmentManager.noteStateNotSaved();
        fragment.mChildFragmentManager.execPendingActions(true);
        fragment.mState = 5;
        fragment.mCalled = false;
        fragment.onStart();
        if (fragment.mCalled) {
            LifecycleRegistry lifecycleRegistry = fragment.mLifecycleRegistry;
            Lifecycle.Event event = Lifecycle.Event.ON_START;
            lifecycleRegistry.handleLifecycleEvent(event);
            if (fragment.mView != null) {
                fragment.mViewLifecycleOwner.handleLifecycleEvent(event);
            }
            FragmentManager fragmentManager = fragment.mChildFragmentManager;
            fragmentManager.mStateSaved = false;
            fragmentManager.mStopped = false;
            fragmentManager.mNonConfig.mIsStateSaved = false;
            fragmentManager.dispatchStateChange(5);
            this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment, " did not call through to super.onStart()"));
    }

    public void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("movefrom STARTED: ");
            outline13.append(this.mFragment);
            Log.d("FragmentManager", outline13.toString());
        }
        Fragment fragment = this.mFragment;
        FragmentManager fragmentManager = fragment.mChildFragmentManager;
        fragmentManager.mStopped = true;
        fragmentManager.mNonConfig.mIsStateSaved = true;
        fragmentManager.dispatchStateChange(4);
        if (fragment.mView != null) {
            fragment.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
        fragment.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        fragment.mState = 4;
        fragment.mCalled = false;
        fragment.onStop();
        if (fragment.mCalled) {
            this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", fragment, " did not call through to super.onStop()"));
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, ClassLoader classLoader, FragmentFactory fragmentFactory, FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        Fragment instantiate = fragmentFactory.instantiate(classLoader, fragmentState.mClassName);
        this.mFragment = instantiate;
        Bundle bundle = fragmentState.mArguments;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        instantiate.setArguments(fragmentState.mArguments);
        instantiate.mWho = fragmentState.mWho;
        instantiate.mFromLayout = fragmentState.mFromLayout;
        instantiate.mRestored = true;
        instantiate.mFragmentId = fragmentState.mFragmentId;
        instantiate.mContainerId = fragmentState.mContainerId;
        instantiate.mTag = fragmentState.mTag;
        instantiate.mRetainInstance = fragmentState.mRetainInstance;
        instantiate.mRemoving = fragmentState.mRemoving;
        instantiate.mDetached = fragmentState.mDetached;
        instantiate.mHidden = fragmentState.mHidden;
        instantiate.mMaxState = Lifecycle.State.values()[fragmentState.mMaxLifecycleState];
        Bundle bundle2 = fragmentState.mSavedFragmentState;
        if (bundle2 != null) {
            instantiate.mSavedFragmentState = bundle2;
        } else {
            instantiate.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + instantiate);
        }
    }

    public FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment, FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        Bundle bundle = fragmentState.mSavedFragmentState;
        if (bundle != null) {
            fragment.mSavedFragmentState = bundle;
        } else {
            fragment.mSavedFragmentState = new Bundle();
        }
    }
}

package androidx.fragment.app;

import android.animation.Animator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.loader.app.LoaderManagerImpl;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    public static final Object USE_DEFAULT_TRANSITION = new Object();
    public boolean mAdded;
    public AnimationInfo mAnimationInfo;
    public Bundle mArguments;
    public int mBackStackNesting;
    public boolean mCalled;
    public ViewGroup mContainer;
    public int mContainerId;
    public boolean mDeferStart;
    public boolean mDetached;
    public int mFragmentId;
    public FragmentManager mFragmentManager;
    public boolean mFromLayout;
    public boolean mHidden;
    public boolean mHiddenChanged;
    public FragmentHostCallback<?> mHost;
    public boolean mInLayout;
    public boolean mIsCreated;
    public LayoutInflater mLayoutInflater;
    public Fragment mParentFragment;
    public boolean mPerformedCreateView;
    public float mPostponedAlpha;
    public boolean mRemoving;
    public boolean mRestored;
    public boolean mRetainInstance;
    public Bundle mSavedFragmentState;
    public Bundle mSavedViewRegistryState;
    public SparseArray<Parcelable> mSavedViewState;
    public String mTag;
    public Fragment mTarget;
    public int mTargetRequestCode;
    public View mView;
    public FragmentViewLifecycleOwner mViewLifecycleOwner;
    public int mState = -1;
    public String mWho = UUID.randomUUID().toString();
    public String mTargetWho = null;
    public Boolean mIsPrimaryNavigationFragment = null;
    public FragmentManager mChildFragmentManager = new FragmentManagerImpl();
    public boolean mMenuVisible = true;
    public boolean mUserVisibleHint = true;
    public Lifecycle.State mMaxState = Lifecycle.State.RESUMED;
    public MutableLiveData<LifecycleOwner> mViewLifecycleOwnerLiveData = new MutableLiveData<>();
    public final ArrayList<OnPreAttachedListener> mOnPreAttachedListeners = new ArrayList<>();
    public LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    public SavedStateRegistryController mSavedStateRegistryController = new SavedStateRegistryController(this);

    /* renamed from: androidx.fragment.app.Fragment$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass4 extends FragmentContainer {
        public AnonymousClass4() {
        }

        @Override // androidx.fragment.app.FragmentContainer
        public View onFindViewById(int i) {
            View view = Fragment.this.mView;
            if (view != null) {
                return view.findViewById(i);
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fragment ");
            outline13.append(Fragment.this);
            outline13.append(" does not have a view");
            throw new IllegalStateException(outline13.toString());
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            return Fragment.this.mView != null;
        }
    }

    /* loaded from: classes.dex */
    public static class AnimationInfo {
        public View mAnimatingAway;
        public Animator mAnimator;
        public int mEnterAnim;
        public int mExitAnim;
        public boolean mIsHideReplaced;
        public boolean mIsPop;
        public int mNextTransition;
        public int mPopEnterAnim;
        public int mPopExitAnim;
        public Object mReenterTransition;
        public Object mReturnTransition;
        public Object mSharedElementReturnTransition;
        public ArrayList<String> mSharedElementSourceNames;
        public ArrayList<String> mSharedElementTargetNames;
        public OnStartEnterTransitionListener mStartEnterTransitionListener;
        public float mPostOnViewCreatedAlpha = 1.0f;
        public View mFocusedView = null;

        public AnimationInfo() {
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            this.mReturnTransition = obj;
            this.mReenterTransition = obj;
            this.mSharedElementReturnTransition = obj;
        }
    }

    /* loaded from: classes.dex */
    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnPreAttachedListener {
        public abstract void onPreAttached();
    }

    /* loaded from: classes.dex */
    public interface OnStartEnterTransitionListener {
    }

    public Fragment() {
        new AtomicInteger();
    }

    public FragmentContainer createFragmentContainer() {
        return new AnonymousClass4();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(false);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        if (this.mSavedViewRegistryState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.mSavedViewRegistryState);
        }
        Fragment fragment = this.mTarget;
        if (fragment == null) {
            FragmentManager fragmentManager = this.mFragmentManager;
            fragment = (fragmentManager == null || (str2 = this.mTargetWho) == null) ? null : fragmentManager.mFragmentStore.findActiveFragment(str2);
        }
        if (fragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(getPopDirection());
        if (getEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(getEnterAnim());
        }
        if (getExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(getExitAnim());
        }
        if (getPopEnterAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(getPopEnterAnim());
        }
        if (getPopExitAnim() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(getPopExitAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
        }
        if (getContext() != null) {
            LoaderManager.getInstance(this).dump(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.dump(GeneratedOutlineSupport.outline8(str, "  "), fileDescriptor, printWriter, strArr);
    }

    public final AnimationInfo ensureAnimationInfo() {
        if (this.mAnimationInfo == null) {
            this.mAnimationInfo = new AnimationInfo();
        }
        return this.mAnimationInfo;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final FragmentActivity getActivity() {
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            return null;
        }
        return (FragmentActivity) fragmentHostCallback.mActivity;
    }

    public View getAnimatingAway() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.mAnimatingAway;
    }

    public final FragmentManager getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment ", this, " has not been attached yet."));
    }

    public Context getContext() {
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.mContext;
    }

    public int getEnterAnim() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mEnterAnim;
    }

    public Object getEnterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Objects.requireNonNull(animationInfo);
        return null;
    }

    public void getEnterTransitionCallback() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo != null) {
            Objects.requireNonNull(animationInfo);
        }
    }

    public int getExitAnim() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mExitAnim;
    }

    public Object getExitTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Objects.requireNonNull(animationInfo);
        return null;
    }

    public void getExitTransitionCallback() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo != null) {
            Objects.requireNonNull(animationInfo);
        }
    }

    @Deprecated
    public LayoutInflater getLayoutInflater() {
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if (fragmentHostCallback != null) {
            LayoutInflater onGetLayoutInflater = fragmentHostCallback.onGetLayoutInflater();
            onGetLayoutInflater.setFactory2(this.mChildFragmentManager.mLayoutInflaterFactory);
            return onGetLayoutInflater;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final int getMinimumMaxLifecycleState() {
        Lifecycle.State state = this.mMaxState;
        if (state == Lifecycle.State.INITIALIZED || this.mParentFragment == null) {
            return state.ordinal();
        }
        return Math.min(state.ordinal(), this.mParentFragment.getMinimumMaxLifecycleState());
    }

    public final FragmentManager getParentFragmentManager() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment ", this, " not associated with a fragment manager."));
    }

    public boolean getPopDirection() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return false;
        }
        return animationInfo.mIsPop;
    }

    public int getPopEnterAnim() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mPopEnterAnim;
    }

    public int getPopExitAnim() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.mPopExitAnim;
    }

    public Object getReenterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mReenterTransition;
        if (obj != USE_DEFAULT_TRANSITION) {
            return obj;
        }
        getExitTransition();
        return null;
    }

    public final Resources getResources() {
        return requireContext().getResources();
    }

    public Object getReturnTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mReturnTransition;
        if (obj != USE_DEFAULT_TRANSITION) {
            return obj;
        }
        getEnterTransition();
        return null;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.mRegistry;
    }

    public Object getSharedElementEnterTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Objects.requireNonNull(animationInfo);
        return null;
    }

    public Object getSharedElementReturnTransition() {
        AnimationInfo animationInfo = this.mAnimationInfo;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.mSharedElementReturnTransition;
        if (obj != USE_DEFAULT_TRANSITION) {
            return obj;
        }
        getSharedElementEnterTransition();
        return null;
    }

    public final String getString(int i) {
        return getResources().getString(i);
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if (this.mFragmentManager == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        } else if (getMinimumMaxLifecycleState() != 1) {
            FragmentManagerViewModel fragmentManagerViewModel = this.mFragmentManager.mNonConfig;
            ViewModelStore viewModelStore = fragmentManagerViewModel.mViewModelStores.get(this.mWho);
            if (viewModelStore != null) {
                return viewModelStore;
            }
            ViewModelStore viewModelStore2 = new ViewModelStore();
            fragmentManagerViewModel.mViewModelStores.put(this.mWho, viewModelStore2);
            return viewModelStore2;
        } else {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public boolean isPostponed() {
        if (this.mAnimationInfo == null) {
        }
        return false;
    }

    public final boolean isRemovingParent() {
        Fragment fragment = this.mParentFragment;
        return fragment != null && (fragment.mRemoving || fragment.isRemovingParent());
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    @Deprecated
    public void onActivityCreated() {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i + " resultCode: " + i2 + " data: " + intent);
        }
    }

    public void onAttach(Context context) {
        this.mCalled = true;
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if ((fragmentHostCallback == null ? null : fragmentHostCallback.mActivity) != null) {
            this.mCalled = false;
            onAttach();
        }
    }

    @Deprecated
    public void onAttachFragment() {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        boolean z = true;
        this.mCalled = true;
        if (!(bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null)) {
            this.mChildFragmentManager.restoreSaveState(parcelable);
            this.mChildFragmentManager.dispatchCreate();
        }
        FragmentManager fragmentManager = this.mChildFragmentManager;
        if (fragmentManager.mCurState < 1) {
            z = false;
        }
        if (!z) {
            fragmentManager.dispatchCreate();
        }
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    public Animator onCreateAnimator() {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onCreateContextMenu(contextMenu, view, contextMenuInfo);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment ", this, " not attached to an activity."));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return getLayoutInflater();
    }

    public void onHiddenChanged() {
    }

    public void onInflate(AttributeSet attributeSet, Bundle bundle) {
        this.mCalled = true;
        FragmentHostCallback<?> fragmentHostCallback = this.mHost;
        if ((fragmentHostCallback == null ? null : fragmentHostCallback.mActivity) != null) {
            this.mCalled = false;
            onInflate();
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged() {
    }

    public void onPictureInPictureModeChanged() {
    }

    public void onPrimaryNavigationFragmentChanged() {
    }

    @Deprecated
    public void onRequestPermissionsResult() {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated() {
    }

    public void onViewStateRestored(Bundle bundle) {
        this.mCalled = true;
    }

    public void performCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChildFragmentManager.noteStateNotSaved();
        boolean z = true;
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new FragmentViewLifecycleOwner(this, getViewModelStore());
        View onCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = onCreateView;
        if (onCreateView != null) {
            this.mViewLifecycleOwner.initialize();
            this.mView.setTag(R.id.view_tree_lifecycle_owner, this.mViewLifecycleOwner);
            this.mView.setTag(R.id.view_tree_view_model_store_owner, this.mViewLifecycleOwner);
            this.mView.setTag(R.id.view_tree_saved_state_registry_owner, this.mViewLifecycleOwner);
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
            return;
        }
        if (this.mViewLifecycleOwner.mLifecycleRegistry == null) {
            z = false;
        }
        if (!z) {
            this.mViewLifecycleOwner = null;
            return;
        }
        throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
    }

    public void performDestroyView() {
        this.mChildFragmentManager.dispatchStateChange(1);
        if (this.mView != null) {
            FragmentViewLifecycleOwner fragmentViewLifecycleOwner = this.mViewLifecycleOwner;
            fragmentViewLifecycleOwner.initialize();
            if (fragmentViewLifecycleOwner.mLifecycleRegistry.mState.compareTo(Lifecycle.State.CREATED) >= 0) {
                this.mViewLifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
            }
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            LoaderManagerImpl.LoaderViewModel loaderViewModel = ((LoaderManagerImpl) LoaderManager.getInstance(this)).mLoaderViewModel;
            int size = loaderViewModel.mLoaders.size();
            for (int i = 0; i < size; i++) {
                Objects.requireNonNull(loaderViewModel.mLoaders.valueAt(i));
            }
            this.mPerformedCreateView = false;
            return;
        }
        throw new SuperNotCalledException(GeneratedOutlineSupport.outline6("Fragment ", this, " did not call through to super.onDestroyView()"));
    }

    public void performLowMemory() {
        onLowMemory();
        this.mChildFragmentManager.dispatchLowMemory();
    }

    public boolean performPrepareOptionsMenu(Menu menu) {
        if (!this.mHidden) {
            return false | this.mChildFragmentManager.dispatchPrepareOptionsMenu(menu);
        }
        return false;
    }

    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment ", this, " not attached to a context."));
    }

    public final View requireView() {
        View view = this.mView;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline6("Fragment ", this, " did not return a View from onCreateView() or this was called before onCreateView()."));
    }

    public void setAnimatingAway(View view) {
        ensureAnimationInfo().mAnimatingAway = view;
    }

    public void setAnimations(int i, int i2, int i3, int i4) {
        if (this.mAnimationInfo != null || i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
            ensureAnimationInfo().mEnterAnim = i;
            ensureAnimationInfo().mExitAnim = i2;
            ensureAnimationInfo().mPopEnterAnim = i3;
            ensureAnimationInfo().mPopExitAnim = i4;
        }
    }

    public void setAnimator(Animator animator) {
        ensureAnimationInfo().mAnimator = animator;
    }

    public void setArguments(Bundle bundle) {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            if (fragmentManager == null ? false : fragmentManager.isStateSaved()) {
                throw new IllegalStateException("Fragment already added and state has been saved");
            }
        }
        this.mArguments = bundle;
    }

    public void setFocusedView(View view) {
        ensureAnimationInfo().mFocusedView = null;
    }

    public void setHideReplaced(boolean z) {
        ensureAnimationInfo().mIsHideReplaced = z;
    }

    public void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onStartEnterTransitionListener) {
        ensureAnimationInfo();
        OnStartEnterTransitionListener onStartEnterTransitionListener2 = this.mAnimationInfo.mStartEnterTransitionListener;
        if (onStartEnterTransitionListener != onStartEnterTransitionListener2) {
            if (onStartEnterTransitionListener != null && onStartEnterTransitionListener2 != null) {
                throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
            } else if (onStartEnterTransitionListener != null) {
                ((FragmentManager.StartEnterTransitionListener) onStartEnterTransitionListener).mNumPostponed++;
            }
        }
    }

    public void setPopDirection(boolean z) {
        if (this.mAnimationInfo != null) {
            ensureAnimationInfo().mIsPop = z;
        }
    }

    public void startPostponedEnterTransition() {
        if (this.mAnimationInfo != null) {
            Objects.requireNonNull(ensureAnimationInfo());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.mWho);
        if (this.mFragmentId != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        sb.append(")");
        return sb.toString();
    }

    @Deprecated
    public void onAttach() {
        this.mCalled = true;
    }

    @Deprecated
    public void onInflate() {
        this.mCalled = true;
    }
}

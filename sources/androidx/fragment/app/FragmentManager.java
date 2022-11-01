package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.Cancellable;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManagerViewModel;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransition;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider$Factory;
import androidx.lifecycle.ViewModelProvider$KeyedFactory;
import androidx.lifecycle.ViewModelProvider$OnRequeryFactory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.HttpUrl;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public abstract class FragmentManager {
    public ArrayList<BackStackRecord> mBackStack;
    public ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    public FragmentContainer mContainer;
    public ArrayList<Fragment> mCreatedMenus;
    public boolean mDestroyed;
    public boolean mExecutingActions;
    public boolean mHavePendingDeferredStart;
    public FragmentHostCallback<?> mHost;
    public boolean mNeedMenuInvalidate;
    public FragmentManagerViewModel mNonConfig;
    public OnBackPressedDispatcher mOnBackPressedDispatcher;
    public Fragment mParent;
    public ArrayList<StartEnterTransitionListener> mPostponedTransactions;
    public Fragment mPrimaryNav;
    public ActivityResultLauncher<String[]> mRequestPermissions;
    public ActivityResultLauncher<Intent> mStartActivityForResult;
    public ActivityResultLauncher<IntentSenderRequest> mStartIntentSenderForResult;
    public boolean mStateSaved;
    public boolean mStopped;
    public ArrayList<Fragment> mTmpAddedFragments;
    public ArrayList<Boolean> mTmpIsPop;
    public ArrayList<BackStackRecord> mTmpRecords;
    public final ArrayList<OpGenerator> mPendingActions = new ArrayList<>();
    public final FragmentStore mFragmentStore = new FragmentStore();
    public final FragmentLayoutInflaterFactory mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
    public final OnBackPressedCallback mOnBackPressedCallback = new OnBackPressedCallback(false) { // from class: androidx.fragment.app.FragmentManager.1
        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManager fragmentManager = FragmentManager.this;
            fragmentManager.execPendingActions(true);
            if (fragmentManager.mOnBackPressedCallback.mEnabled) {
                fragmentManager.popBackStackImmediate();
            } else {
                fragmentManager.mOnBackPressedDispatcher.onBackPressed();
            }
        }
    };
    public final AtomicInteger mBackStackIndex = new AtomicInteger();
    public final Map<String, Bundle> mResults = Collections.synchronizedMap(new HashMap());
    public final Map<String, ?> mResultListeners = Collections.synchronizedMap(new HashMap());
    public Map<Fragment, HashSet<CancellationSignal>> mExitAnimationCancellationSignals = Collections.synchronizedMap(new HashMap());
    public final FragmentTransition.Callback mFragmentTransitionCallback = new AnonymousClass2();
    public final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
    public final CopyOnWriteArrayList<FragmentOnAttachListener> mOnAttachListeners = new CopyOnWriteArrayList<>();
    public int mCurState = -1;
    public FragmentFactory mHostFragmentFactory = new FragmentFactory() { // from class: androidx.fragment.app.FragmentManager.3
        @Override // androidx.fragment.app.FragmentFactory
        public Fragment instantiate(ClassLoader classLoader, String str) {
            FragmentHostCallback<?> fragmentHostCallback = FragmentManager.this.mHost;
            Context context = fragmentHostCallback.mContext;
            Objects.requireNonNull(fragmentHostCallback);
            Object obj = Fragment.USE_DEFAULT_TRANSITION;
            try {
                return (Fragment) FragmentFactory.loadFragmentClass(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (IllegalAccessException e) {
                throw new Fragment.InstantiationException(GeneratedOutlineSupport.outline9("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
            } catch (InstantiationException e2) {
                throw new Fragment.InstantiationException(GeneratedOutlineSupport.outline9("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
            } catch (NoSuchMethodException e3) {
                throw new Fragment.InstantiationException(GeneratedOutlineSupport.outline9("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
            } catch (InvocationTargetException e4) {
                throw new Fragment.InstantiationException(GeneratedOutlineSupport.outline9("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
            }
        }
    };
    public SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory(this) { // from class: androidx.fragment.app.FragmentManager.4
    };
    public ArrayDeque<LaunchedFragmentInfo> mLaunchedFragments = new ArrayDeque<>();
    public Runnable mExecCommit = new Runnable() { // from class: androidx.fragment.app.FragmentManager.5
        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.execPendingActions(true);
        }
    };

    /* renamed from: androidx.fragment.app.FragmentManager$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements FragmentTransition.Callback {
        public AnonymousClass2() {
        }

        public void onComplete(Fragment fragment, CancellationSignal cancellationSignal) {
            boolean z;
            synchronized (cancellationSignal) {
                z = cancellationSignal.mIsCanceled;
            }
            if (!z) {
                FragmentManager fragmentManager = FragmentManager.this;
                HashSet<CancellationSignal> hashSet = fragmentManager.mExitAnimationCancellationSignals.get(fragment);
                if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
                    fragmentManager.mExitAnimationCancellationSignals.remove(fragment);
                    if (fragment.mState < 5) {
                        fragmentManager.destroyFragmentView(fragment);
                        fragmentManager.moveToState(fragment, fragmentManager.mCurState);
                    }
                }
            }
        }

        public void onStart(Fragment fragment, CancellationSignal cancellationSignal) {
            FragmentManager fragmentManager = FragmentManager.this;
            if (fragmentManager.mExitAnimationCancellationSignals.get(fragment) == null) {
                fragmentManager.mExitAnimationCancellationSignals.put(fragment, new HashSet<>());
            }
            fragmentManager.mExitAnimationCancellationSignals.get(fragment).add(cancellationSignal);
        }
    }

    /* renamed from: androidx.fragment.app.FragmentManager$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass6 implements LifecycleEventObserver {
        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                throw null;
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                throw null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        @Override // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            IntentSenderRequest intentSenderRequest2 = intentSenderRequest;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intent2 = intentSenderRequest2.mFillInIntent;
            if (!(intent2 == null || (bundleExtra = intent2.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) == null)) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                intent2.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (intent2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest2 = new IntentSenderRequest(intentSenderRequest2.mIntentSender, null, intentSenderRequest2.mFlagsMask, intentSenderRequest2.mFlagsValues);
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest2);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        public ActivityResult parseResult(int i, Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class FragmentLifecycleCallbacks {
        public abstract void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment);
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<LaunchedFragmentInfo>() { // from class: androidx.fragment.app.FragmentManager.LaunchedFragmentInfo.1
            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public LaunchedFragmentInfo[] newArray(int i) {
                return new LaunchedFragmentInfo[i];
            }
        };
        public int mRequestCode;
        public String mWho;

        public LaunchedFragmentInfo(Parcel parcel) {
            this.mWho = parcel.readString();
            this.mRequestCode = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mWho);
            parcel.writeInt(this.mRequestCode);
        }
    }

    /* loaded from: classes.dex */
    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    /* loaded from: classes.dex */
    public interface OpGenerator {
        boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* loaded from: classes.dex */
    public class PopBackStackState implements OpGenerator {
        public final int mFlags;
        public final int mId;
        public final String mName;

        public PopBackStackState(String str, int i, int i2) {
            this.mName = str;
            this.mId = i;
            this.mFlags = i2;
        }

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean generateOps(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.mPrimaryNav;
            if (fragment == null || this.mId >= 0 || this.mName != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManager.this.popBackStackState(arrayList, arrayList2, this.mName, this.mId, this.mFlags);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class StartEnterTransitionListener implements Fragment.OnStartEnterTransitionListener {
        public final boolean mIsBack;
        public int mNumPostponed;
        public final BackStackRecord mRecord;

        public void completeTransaction() {
            boolean z = this.mNumPostponed > 0;
            for (Fragment fragment : this.mRecord.mManager.getFragments()) {
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            BackStackRecord backStackRecord = this.mRecord;
            backStackRecord.mManager.completeExecute(backStackRecord, this.mIsBack, !z, true);
        }
    }

    public static boolean isLoggingEnabled(int i) {
        return Log.isLoggable("FragmentManager", i);
    }

    public FragmentStateManager addFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        FragmentStateManager createOrGetFragmentStateManager = createOrGetFragmentStateManager(fragment);
        fragment.mFragmentManager = this;
        this.mFragmentStore.makeActive(createOrGetFragmentStateManager);
        if (!fragment.mDetached) {
            this.mFragmentStore.addFragment(fragment);
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return createOrGetFragmentStateManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"SyntheticAccessor"})
    public void attachController(FragmentHostCallback<?> fragmentHostCallback, FragmentContainer fragmentContainer, final Fragment fragment) {
        if (this.mHost == null) {
            this.mHost = fragmentHostCallback;
            this.mContainer = fragmentContainer;
            this.mParent = fragment;
            if (fragment != null) {
                this.mOnAttachListeners.add(new FragmentOnAttachListener(this) { // from class: androidx.fragment.app.FragmentManager.8
                    @Override // androidx.fragment.app.FragmentOnAttachListener
                    public void onAttachFragment(FragmentManager fragmentManager, Fragment fragment2) {
                        fragment.onAttachFragment();
                    }
                });
            } else if (fragmentHostCallback instanceof FragmentOnAttachListener) {
                this.mOnAttachListeners.add((FragmentOnAttachListener) fragmentHostCallback);
            }
            if (this.mParent != null) {
                updateOnBackPressedCallbackEnabled();
            }
            if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
                OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
                OnBackPressedDispatcher onBackPressedDispatcher = onBackPressedDispatcherOwner.getOnBackPressedDispatcher();
                this.mOnBackPressedDispatcher = onBackPressedDispatcher;
                Fragment fragment2 = onBackPressedDispatcherOwner;
                if (fragment != null) {
                    fragment2 = fragment;
                }
                OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
                Objects.requireNonNull(onBackPressedDispatcher);
                Lifecycle lifecycle = fragment2.getLifecycle();
                if (lifecycle.getCurrentState() != Lifecycle.State.DESTROYED) {
                    onBackPressedCallback.mCancellables.add(new OnBackPressedDispatcher.LifecycleOnBackPressedCancellable(lifecycle, onBackPressedCallback));
                }
            }
            if (fragment != null) {
                FragmentManagerViewModel fragmentManagerViewModel = fragment.mFragmentManager.mNonConfig;
                FragmentManagerViewModel fragmentManagerViewModel2 = fragmentManagerViewModel.mChildNonConfigs.get(fragment.mWho);
                if (fragmentManagerViewModel2 == null) {
                    fragmentManagerViewModel2 = new FragmentManagerViewModel(fragmentManagerViewModel.mStateAutomaticallySaved);
                    fragmentManagerViewModel.mChildNonConfigs.put(fragment.mWho, fragmentManagerViewModel2);
                }
                this.mNonConfig = fragmentManagerViewModel2;
            } else if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) fragmentHostCallback).getViewModelStore();
                ViewModelProvider$Factory viewModelProvider$Factory = FragmentManagerViewModel.FACTORY;
                String canonicalName = FragmentManagerViewModel.class.getCanonicalName();
                if (canonicalName != null) {
                    String outline8 = GeneratedOutlineSupport.outline8("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
                    ViewModel viewModel = viewModelStore.mMap.get(outline8);
                    if (!FragmentManagerViewModel.class.isInstance(viewModel)) {
                        viewModel = viewModelProvider$Factory instanceof ViewModelProvider$KeyedFactory ? ((ViewModelProvider$KeyedFactory) viewModelProvider$Factory).create(outline8, FragmentManagerViewModel.class) : ((FragmentManagerViewModel.AnonymousClass1) viewModelProvider$Factory).create(FragmentManagerViewModel.class);
                        ViewModel put = viewModelStore.mMap.put(outline8, viewModel);
                        if (put != null) {
                            put.onCleared();
                        }
                    } else if (viewModelProvider$Factory instanceof ViewModelProvider$OnRequeryFactory) {
                        ViewModelProvider$OnRequeryFactory viewModelProvider$OnRequeryFactory = (ViewModelProvider$OnRequeryFactory) viewModelProvider$Factory;
                    }
                    this.mNonConfig = (FragmentManagerViewModel) viewModel;
                } else {
                    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                }
            } else {
                this.mNonConfig = new FragmentManagerViewModel(false);
            }
            this.mNonConfig.mIsStateSaved = isStateSaved();
            this.mFragmentStore.mNonConfig = this.mNonConfig;
            FragmentHostCallback<?> fragmentHostCallback2 = this.mHost;
            if (fragmentHostCallback2 instanceof ActivityResultRegistryOwner) {
                ActivityResultRegistry activityResultRegistry = ((ActivityResultRegistryOwner) fragmentHostCallback2).getActivityResultRegistry();
                String outline82 = GeneratedOutlineSupport.outline8("FragmentManager:", fragment != null ? GeneratedOutlineSupport.outline11(new StringBuilder(), fragment.mWho, ":") : HttpUrl.FRAGMENT_ENCODE_SET);
                this.mStartActivityForResult = activityResultRegistry.register(GeneratedOutlineSupport.outline8(outline82, "StartActivityForResult"), new ActivityResultContracts$StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.9
                    @Override // androidx.activity.result.ActivityResultCallback
                    public void onActivityResult(ActivityResult activityResult) {
                        ActivityResult activityResult2 = activityResult;
                        LaunchedFragmentInfo pollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                        if (pollFirst == null) {
                            Log.w("FragmentManager", "No Activities were started for result for " + this);
                            return;
                        }
                        String str = pollFirst.mWho;
                        int i = pollFirst.mRequestCode;
                        Fragment findFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str);
                        if (findFragmentByWho == null) {
                            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
                            return;
                        }
                        findFragmentByWho.onActivityResult(i, activityResult2.mResultCode, activityResult2.mData);
                    }
                });
                this.mStartIntentSenderForResult = activityResultRegistry.register(GeneratedOutlineSupport.outline8(outline82, "StartIntentSenderForResult"), new FragmentIntentSenderContract(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.10
                    @Override // androidx.activity.result.ActivityResultCallback
                    public void onActivityResult(ActivityResult activityResult) {
                        ActivityResult activityResult2 = activityResult;
                        LaunchedFragmentInfo pollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                        if (pollFirst == null) {
                            Log.w("FragmentManager", "No IntentSenders were started for " + this);
                            return;
                        }
                        String str = pollFirst.mWho;
                        int i = pollFirst.mRequestCode;
                        Fragment findFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str);
                        if (findFragmentByWho == null) {
                            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
                            return;
                        }
                        findFragmentByWho.onActivityResult(i, activityResult2.mResultCode, activityResult2.mData);
                    }
                });
                this.mRequestPermissions = activityResultRegistry.register(GeneratedOutlineSupport.outline8(outline82, "RequestPermissions"), new ActivityResultContract<String[], Map<String, Boolean>>() { // from class: androidx.activity.result.contract.ActivityResultContracts$RequestMultiplePermissions
                    @Override // androidx.activity.result.contract.ActivityResultContract
                    public Intent createIntent(Context context, String[] strArr) {
                        return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
                    }

                    @Override // androidx.activity.result.contract.ActivityResultContract
                    public ActivityResultContract.SynchronousResult<Map<String, Boolean>> getSynchronousResult(Context context, String[] strArr) {
                        String[] strArr2 = strArr;
                        if (strArr2 == null || strArr2.length == 0) {
                            return new ActivityResultContract.SynchronousResult<>(Collections.emptyMap());
                        }
                        ArrayMap arrayMap = new ArrayMap();
                        boolean z = true;
                        for (String str : strArr2) {
                            boolean z2 = ContextCompat.checkSelfPermission(context, str) == 0;
                            arrayMap.put(str, Boolean.valueOf(z2));
                            if (!z2) {
                                z = false;
                            }
                        }
                        if (z) {
                            return new ActivityResultContract.SynchronousResult<>(arrayMap);
                        }
                        return null;
                    }

                    @Override // androidx.activity.result.contract.ActivityResultContract
                    public Map<String, Boolean> parseResult(int i, Intent intent) {
                        if (i != -1) {
                            return Collections.emptyMap();
                        }
                        if (intent == null) {
                            return Collections.emptyMap();
                        }
                        String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                        int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                        if (intArrayExtra == null || stringArrayExtra == null) {
                            return Collections.emptyMap();
                        }
                        HashMap hashMap = new HashMap();
                        int length = stringArrayExtra.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            hashMap.put(stringArrayExtra[i2], Boolean.valueOf(intArrayExtra[i2] == 0));
                        }
                        return hashMap;
                    }
                }, new ActivityResultCallback<Map<String, Boolean>>() { // from class: androidx.fragment.app.FragmentManager.11
                    @Override // androidx.activity.result.ActivityResultCallback
                    @SuppressLint({"SyntheticAccessor"})
                    public void onActivityResult(Map<String, Boolean> map) {
                        Map<String, Boolean> map2 = map;
                        String[] strArr = (String[]) map2.keySet().toArray(new String[0]);
                        ArrayList arrayList = new ArrayList(map2.values());
                        int[] iArr = new int[arrayList.size()];
                        for (int i = 0; i < arrayList.size(); i++) {
                            iArr[i] = ((Boolean) arrayList.get(i)).booleanValue() ? 0 : -1;
                        }
                        LaunchedFragmentInfo pollFirst = FragmentManager.this.mLaunchedFragments.pollFirst();
                        if (pollFirst == null) {
                            Log.w("FragmentManager", "No permissions were requested for " + this);
                            return;
                        }
                        String str = pollFirst.mWho;
                        Fragment findFragmentByWho = FragmentManager.this.mFragmentStore.findFragmentByWho(str);
                        if (findFragmentByWho == null) {
                            Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str);
                            return;
                        }
                        findFragmentByWho.onRequestPermissionsResult();
                    }
                });
                return;
            }
            return;
        }
        throw new IllegalStateException("Already attached");
    }

    public void attachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                this.mFragmentStore.addFragment(fragment);
                if (isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                if (isMenuAvailable(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public final void cancelExitAnimation(Fragment fragment) {
        HashSet<CancellationSignal> hashSet = this.mExitAnimationCancellationSignals.get(fragment);
        if (hashSet != null) {
            Iterator<CancellationSignal> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            hashSet.clear();
            destroyFragmentView(fragment);
            this.mExitAnimationCancellationSignals.remove(fragment);
        }
    }

    public final void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    public final Set<SpecialEffectsController> collectAllSpecialEffectsController() {
        HashSet hashSet = new HashSet();
        Iterator it = ((ArrayList) this.mFragmentStore.getActiveFragmentStateManagers()).iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((FragmentStateManager) it.next()).mFragment.mContainer;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, getSpecialEffectsControllerFactory()));
            }
        }
        return hashSet;
    }

    public void completeExecute(BackStackRecord backStackRecord, boolean z, boolean z2, boolean z3) {
        if (z) {
            backStackRecord.executePopOps(z3);
        } else {
            backStackRecord.executeOps();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(backStackRecord);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.mCurState >= 1) {
            FragmentTransition.startTransitions(this.mHost.mContext, this.mContainer, arrayList, arrayList2, 0, 1, true, this.mFragmentTransitionCallback);
        }
        if (z3) {
            moveToState(this.mCurState, true);
        }
        Iterator it = ((ArrayList) this.mFragmentStore.getActiveFragments()).iterator();
        while (it.hasNext()) {
            Fragment fragment = (Fragment) it.next();
            if (fragment != null) {
                View view = fragment.mView;
            }
        }
    }

    public FragmentStateManager createOrGetFragmentStateManager(Fragment fragment) {
        FragmentStateManager fragmentStateManager = this.mFragmentStore.getFragmentStateManager(fragment.mWho);
        if (fragmentStateManager != null) {
            return fragmentStateManager;
        }
        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment);
        fragmentStateManager2.restoreState(this.mHost.mContext.getClassLoader());
        fragmentStateManager2.mFragmentManagerState = this.mCurState;
        return fragmentStateManager2;
    }

    public final void destroyFragmentView(Fragment fragment) {
        fragment.performDestroyView();
        this.mLifecycleCallbacksDispatcher.dispatchOnFragmentViewDestroyed(fragment, false);
        fragment.mContainer = null;
        fragment.mView = null;
        fragment.mViewLifecycleOwner = null;
        fragment.mViewLifecycleOwnerLiveData.setValue(null);
        fragment.mInLayout = false;
    }

    public void detachFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "remove from detach: " + fragment);
                }
                this.mFragmentStore.removeFragment(fragment);
                if (isMenuAvailable(fragment)) {
                    this.mNeedMenuInvalidate = true;
                }
                setVisibleRemovingFragment(fragment);
            }
        }
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.onConfigurationChanged(configuration);
                fragment.mChildFragmentManager.dispatchConfigurationChanged(configuration);
            }
        }
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        boolean z;
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                if (!fragment.mHidden) {
                    z = fragment.onContextItemSelected() ? true : fragment.mChildFragmentManager.dispatchContextItemSelected(menuItem);
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.mIsStateSaved = false;
        dispatchStateChange(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mCurState < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible(fragment)) {
                if (!fragment.mHidden ? fragment.mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuInflater) | false : false) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    arrayList.add(fragment);
                    z = true;
                }
            }
        }
        if (this.mCreatedMenus != null) {
            for (int i = 0; i < this.mCreatedMenus.size(); i++) {
                Fragment fragment2 = this.mCreatedMenus.get(i);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    Objects.requireNonNull(fragment2);
                }
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions(true);
        endAnimatingAwayFragments();
        dispatchStateChange(-1);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if (this.mOnBackPressedDispatcher != null) {
            Iterator<Cancellable> it = this.mOnBackPressedCallback.mCancellables.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.mStartActivityForResult;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    public void dispatchLowMemory() {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.onMultiWindowModeChanged();
                fragment.mChildFragmentManager.dispatchMultiWindowModeChanged(z);
            }
        }
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                if (!fragment.mHidden ? fragment.mChildFragmentManager.dispatchOptionsItemSelected(menuItem) : false) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mCurState >= 1) {
            for (Fragment fragment : this.mFragmentStore.getFragments()) {
                if (fragment != null && !fragment.mHidden) {
                    fragment.mChildFragmentManager.dispatchOptionsMenuClosed(menu);
                }
            }
        }
    }

    public final void dispatchParentPrimaryNavigationFragmentChanged(Fragment fragment) {
        if (fragment != null && fragment.equals(findActiveFragment(fragment.mWho))) {
            boolean isPrimaryNavigation = fragment.mFragmentManager.isPrimaryNavigation(fragment);
            Boolean bool = fragment.mIsPrimaryNavigationFragment;
            if (bool == null || bool.booleanValue() != isPrimaryNavigation) {
                fragment.mIsPrimaryNavigationFragment = Boolean.valueOf(isPrimaryNavigation);
                fragment.onPrimaryNavigationFragmentChanged();
                FragmentManager fragmentManager = fragment.mChildFragmentManager;
                fragmentManager.updateOnBackPressedCallbackEnabled();
                fragmentManager.dispatchParentPrimaryNavigationFragmentChanged(fragmentManager.mPrimaryNav);
            }
        }
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null) {
                fragment.onPictureInPictureModeChanged();
                fragment.mChildFragmentManager.dispatchPictureInPictureModeChanged(z);
            }
        }
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mCurState < 1) {
            return false;
        }
        for (Fragment fragment : this.mFragmentStore.getFragments()) {
            if (fragment != null && isParentMenuVisible(fragment) && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Finally extract failed */
    public final void dispatchStateChange(int i) {
        try {
            this.mExecutingActions = true;
            for (FragmentStateManager fragmentStateManager : this.mFragmentStore.mActive.values()) {
                if (fragmentStateManager != null) {
                    fragmentStateManager.mFragmentManagerState = i;
                }
            }
            moveToState(i, false);
            Iterator it = ((HashSet) collectAllSpecialEffectsController()).iterator();
            while (it.hasNext()) {
                ((SpecialEffectsController) it.next()).forceCompleteAllOperations();
            }
            this.mExecutingActions = false;
            execPendingActions(true);
        } catch (Throwable th) {
            this.mExecutingActions = false;
            throw th;
        }
    }

    public final void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            startPendingDeferredFragments();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String outline8 = GeneratedOutlineSupport.outline8(str, "    ");
        FragmentStore fragmentStore = this.mFragmentStore;
        Objects.requireNonNull(fragmentStore);
        String str2 = str + "    ";
        if (!fragmentStore.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                printWriter.print(str);
                if (fragmentStateManager != null) {
                    Fragment fragment = fragmentStateManager.mFragment;
                    printWriter.println(fragment);
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = fragmentStore.mAdded.size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragmentStore.mAdded.get(i).toString());
            }
        }
        ArrayList<Fragment> arrayList = this.mCreatedMenus;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(this.mCreatedMenus.get(i2).toString());
            }
        }
        ArrayList<BackStackRecord> arrayList2 = this.mBackStack;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                BackStackRecord backStackRecord = this.mBackStack.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.dump(outline8, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized (this.mPendingActions) {
            int size4 = this.mPendingActions.size();
            if (size4 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size4; i4++) {
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println((OpGenerator) this.mPendingActions.get(i4));
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
    }

    public final void endAnimatingAwayFragments() {
        Iterator it = ((HashSet) collectAllSpecialEffectsController()).iterator();
        while (it.hasNext()) {
            ((SpecialEffectsController) it.next()).forceCompleteAllOperations();
        }
    }

    public void enqueueAction(OpGenerator opGenerator, boolean z) {
        if (!z) {
            if (this.mHost == null) {
                if (this.mDestroyed) {
                    throw new IllegalStateException("FragmentManager has been destroyed");
                }
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            } else if (isStateSaved()) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.mPendingActions) {
            if (this.mHost != null) {
                this.mPendingActions.add(opGenerator);
                scheduleCommit();
            } else if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public final void ensureExecReady(boolean z) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        } else if (this.mHost == null) {
            if (this.mDestroyed) {
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            throw new IllegalStateException("FragmentManager has not been attached to a host.");
        } else if (Looper.myLooper() != this.mHost.mHandler.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        } else if (z || !isStateSaved()) {
            if (this.mTmpRecords == null) {
                this.mTmpRecords = new ArrayList<>();
                this.mTmpIsPop = new ArrayList<>();
            }
            this.mExecutingActions = true;
            try {
                executePostponedTransaction(null, null);
            } finally {
                this.mExecutingActions = false;
            }
        } else {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    /* JADX WARN: Finally extract failed */
    public boolean execPendingActions(boolean z) {
        boolean z2;
        ensureExecReady(z);
        boolean z3 = false;
        while (true) {
            ArrayList<BackStackRecord> arrayList = this.mTmpRecords;
            ArrayList<Boolean> arrayList2 = this.mTmpIsPop;
            synchronized (this.mPendingActions) {
                if (this.mPendingActions.isEmpty()) {
                    z2 = false;
                } else {
                    int size = this.mPendingActions.size();
                    z2 = false;
                    for (int i = 0; i < size; i++) {
                        z2 |= this.mPendingActions.get(i).generateOps(arrayList, arrayList2);
                    }
                    this.mPendingActions.clear();
                    this.mHost.mHandler.removeCallbacks(this.mExecCommit);
                }
            }
            if (z2) {
                this.mExecutingActions = true;
                try {
                    removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                    cleanupExec();
                    z3 = true;
                } catch (Throwable th) {
                    cleanupExec();
                    throw th;
                }
            } else {
                updateOnBackPressedCallbackEnabled();
                doPendingDeferredStart();
                this.mFragmentStore.burpActive();
                return z3;
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public void execSingleAction(OpGenerator opGenerator, boolean z) {
        if (!z || (this.mHost != null && !this.mDestroyed)) {
            ensureExecReady(z);
            ((BackStackRecord) opGenerator).generateOps(this.mTmpRecords, this.mTmpIsPop);
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
                cleanupExec();
                updateOnBackPressedCallbackEnabled();
                doPendingDeferredStart();
                this.mFragmentStore.burpActive();
            } catch (Throwable th) {
                cleanupExec();
                throw th;
            }
        }
    }

    public final void executeOpsTogether(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        ViewGroup viewGroup;
        int i3;
        ArrayList<Boolean> arrayList3 = arrayList2;
        boolean z = arrayList.get(i).mReorderingAllowed;
        ArrayList<Fragment> arrayList4 = this.mTmpAddedFragments;
        if (arrayList4 == null) {
            this.mTmpAddedFragments = new ArrayList<>();
        } else {
            arrayList4.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment fragment = this.mPrimaryNav;
        int i4 = i;
        boolean z2 = false;
        while (true) {
            int i5 = 1;
            if (i4 < i2) {
                BackStackRecord backStackRecord = arrayList.get(i4);
                int i6 = 3;
                if (!arrayList3.get(i4).booleanValue()) {
                    ArrayList<Fragment> arrayList5 = this.mTmpAddedFragments;
                    int i7 = 0;
                    while (i7 < backStackRecord.mOps.size()) {
                        FragmentTransaction.Op op = backStackRecord.mOps.get(i7);
                        int i8 = op.mCmd;
                        if (i8 != i5) {
                            if (i8 != 2) {
                                if (i8 == i6 || i8 == 6) {
                                    arrayList5.remove(op.mFragment);
                                    Fragment fragment2 = op.mFragment;
                                    if (fragment2 == fragment) {
                                        backStackRecord.mOps.add(i7, new FragmentTransaction.Op(9, fragment2));
                                        i7++;
                                        i3 = 1;
                                        fragment = null;
                                        i7 += i3;
                                        i5 = 1;
                                        i6 = 3;
                                    }
                                } else if (i8 != 7) {
                                    if (i8 == 8) {
                                        backStackRecord.mOps.add(i7, new FragmentTransaction.Op(9, fragment));
                                        i7++;
                                        fragment = op.mFragment;
                                    }
                                }
                                i3 = 1;
                                i7 += i3;
                                i5 = 1;
                                i6 = 3;
                            } else {
                                Fragment fragment3 = op.mFragment;
                                int i9 = fragment3.mContainerId;
                                boolean z3 = false;
                                for (int size = arrayList5.size() - 1; size >= 0; size--) {
                                    Fragment fragment4 = arrayList5.get(size);
                                    if (fragment4.mContainerId != i9) {
                                        i9 = i9;
                                    } else if (fragment4 == fragment3) {
                                        i9 = i9;
                                        z3 = true;
                                    } else {
                                        if (fragment4 == fragment) {
                                            i9 = i9;
                                            backStackRecord.mOps.add(i7, new FragmentTransaction.Op(9, fragment4));
                                            i7++;
                                            fragment = null;
                                        } else {
                                            i9 = i9;
                                        }
                                        FragmentTransaction.Op op2 = new FragmentTransaction.Op(3, fragment4);
                                        op2.mEnterAnim = op.mEnterAnim;
                                        op2.mPopEnterAnim = op.mPopEnterAnim;
                                        op2.mExitAnim = op.mExitAnim;
                                        op2.mPopExitAnim = op.mPopExitAnim;
                                        backStackRecord.mOps.add(i7, op2);
                                        arrayList5.remove(fragment4);
                                        i7++;
                                    }
                                }
                                if (z3) {
                                    backStackRecord.mOps.remove(i7);
                                    i7--;
                                    i3 = 1;
                                    i7 += i3;
                                    i5 = 1;
                                    i6 = 3;
                                } else {
                                    i3 = 1;
                                    op.mCmd = 1;
                                    arrayList5.add(fragment3);
                                    i7 += i3;
                                    i5 = 1;
                                    i6 = 3;
                                }
                            }
                        }
                        i3 = 1;
                        arrayList5.add(op.mFragment);
                        i7 += i3;
                        i5 = 1;
                        i6 = 3;
                    }
                } else {
                    int i10 = 1;
                    ArrayList<Fragment> arrayList6 = this.mTmpAddedFragments;
                    int size2 = backStackRecord.mOps.size() - 1;
                    while (size2 >= 0) {
                        FragmentTransaction.Op op3 = backStackRecord.mOps.get(size2);
                        int i11 = op3.mCmd;
                        if (i11 != i10) {
                            if (i11 != 3) {
                                switch (i11) {
                                    case 8:
                                        fragment = null;
                                        break;
                                    case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                                        fragment = op3.mFragment;
                                        break;
                                    case 10:
                                        op3.mCurrentMaxState = op3.mOldMaxState;
                                        break;
                                }
                                size2--;
                                i10 = 1;
                            }
                            arrayList6.add(op3.mFragment);
                            size2--;
                            i10 = 1;
                        }
                        arrayList6.remove(op3.mFragment);
                        size2--;
                        i10 = 1;
                    }
                }
                z2 = z2 || backStackRecord.mAddToBackStack;
                i4++;
                arrayList3 = arrayList2;
            } else {
                this.mTmpAddedFragments.clear();
                if (!z && this.mCurState >= 1) {
                    for (int i12 = i; i12 < i2; i12++) {
                        Iterator<FragmentTransaction.Op> it = arrayList.get(i12).mOps.iterator();
                        while (it.hasNext()) {
                            Fragment fragment5 = it.next().mFragment;
                            if (!(fragment5 == null || fragment5.mFragmentManager == null)) {
                                this.mFragmentStore.makeActive(createOrGetFragmentStateManager(fragment5));
                            }
                        }
                    }
                }
                int i13 = i;
                while (i13 < i2) {
                    BackStackRecord backStackRecord2 = arrayList.get(i13);
                    if (arrayList2.get(i13).booleanValue()) {
                        backStackRecord2.bumpBackStackNesting(-1);
                        backStackRecord2.executePopOps(i13 == i2 + (-1));
                    } else {
                        backStackRecord2.bumpBackStackNesting(1);
                        backStackRecord2.executeOps();
                    }
                    i13++;
                }
                boolean booleanValue = arrayList2.get(i2 - 1).booleanValue();
                for (int i14 = i; i14 < i2; i14++) {
                    BackStackRecord backStackRecord3 = arrayList.get(i14);
                    if (booleanValue) {
                        for (int size3 = backStackRecord3.mOps.size() - 1; size3 >= 0; size3--) {
                            Fragment fragment6 = backStackRecord3.mOps.get(size3).mFragment;
                            if (fragment6 != null) {
                                createOrGetFragmentStateManager(fragment6).moveToExpectedState();
                            }
                        }
                    } else {
                        Iterator<FragmentTransaction.Op> it2 = backStackRecord3.mOps.iterator();
                        while (it2.hasNext()) {
                            Fragment fragment7 = it2.next().mFragment;
                            if (fragment7 != null) {
                                createOrGetFragmentStateManager(fragment7).moveToExpectedState();
                            }
                        }
                    }
                }
                moveToState(this.mCurState, true);
                HashSet hashSet = new HashSet();
                for (int i15 = i; i15 < i2; i15++) {
                    Iterator<FragmentTransaction.Op> it3 = arrayList.get(i15).mOps.iterator();
                    while (it3.hasNext()) {
                        Fragment fragment8 = it3.next().mFragment;
                        if (!(fragment8 == null || (viewGroup = fragment8.mContainer) == null)) {
                            hashSet.add(SpecialEffectsController.getOrCreateController(viewGroup, getSpecialEffectsControllerFactory()));
                        }
                    }
                }
                Iterator it4 = hashSet.iterator();
                while (it4.hasNext()) {
                    SpecialEffectsController specialEffectsController = (SpecialEffectsController) it4.next();
                    specialEffectsController.mOperationDirectionIsPop = booleanValue;
                    specialEffectsController.markPostponedState();
                    specialEffectsController.executePendingOperations();
                }
                for (int i16 = i; i16 < i2; i16++) {
                    BackStackRecord backStackRecord4 = arrayList.get(i16);
                    if (arrayList2.get(i16).booleanValue() && backStackRecord4.mIndex >= 0) {
                        backStackRecord4.mIndex = -1;
                    }
                    if (backStackRecord4.mCommitRunnables != null) {
                        for (int i17 = 0; i17 < backStackRecord4.mCommitRunnables.size(); i17++) {
                            backStackRecord4.mCommitRunnables.get(i17).run();
                        }
                        backStackRecord4.mCommitRunnables = null;
                    }
                }
                if (z2 && this.mBackStackChangeListeners != null) {
                    for (int i18 = 0; i18 < this.mBackStackChangeListeners.size(); i18++) {
                        this.mBackStackChangeListeners.get(i18).onBackStackChanged();
                    }
                    return;
                }
                return;
            }
        }
    }

    public final void executePostponedTransaction(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<StartEnterTransitionListener> arrayList3 = this.mPostponedTransactions;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            StartEnterTransitionListener startEnterTransitionListener = this.mPostponedTransactions.get(i);
            if (arrayList == null || startEnterTransitionListener.mIsBack || (indexOf2 = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || arrayList2 == null || !arrayList2.get(indexOf2).booleanValue()) {
                if ((startEnterTransitionListener.mNumPostponed == 0) || (arrayList != null && startEnterTransitionListener.mRecord.interactsWith(arrayList, 0, arrayList.size()))) {
                    this.mPostponedTransactions.remove(i);
                    i--;
                    size--;
                    if (arrayList == null || startEnterTransitionListener.mIsBack || (indexOf = arrayList.indexOf(startEnterTransitionListener.mRecord)) == -1 || arrayList2 == null || !arrayList2.get(indexOf).booleanValue()) {
                        startEnterTransitionListener.completeTransaction();
                    } else {
                        BackStackRecord backStackRecord = startEnterTransitionListener.mRecord;
                        backStackRecord.mManager.completeExecute(backStackRecord, startEnterTransitionListener.mIsBack, false, false);
                    }
                }
            } else {
                this.mPostponedTransactions.remove(i);
                i--;
                size--;
                BackStackRecord backStackRecord2 = startEnterTransitionListener.mRecord;
                backStackRecord2.mManager.completeExecute(backStackRecord2, startEnterTransitionListener.mIsBack, false, false);
            }
            i++;
        }
    }

    public Fragment findActiveFragment(String str) {
        return this.mFragmentStore.findActiveFragment(str);
    }

    public Fragment findFragmentById(int i) {
        FragmentStore fragmentStore = this.mFragmentStore;
        int size = fragmentStore.mAdded.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = fragmentStore.mAdded.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            } else {
                for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                    if (fragmentStateManager != null) {
                        Fragment fragment2 = fragmentStateManager.mFragment;
                        if (fragment2.mFragmentId == i) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public Fragment findFragmentByTag(String str) {
        FragmentStore fragmentStore = this.mFragmentStore;
        Objects.requireNonNull(fragmentStore);
        int size = fragmentStore.mAdded.size();
        while (true) {
            size--;
            if (size >= 0) {
                Fragment fragment = fragmentStore.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            } else {
                for (FragmentStateManager fragmentStateManager : fragmentStore.mActive.values()) {
                    if (fragmentStateManager != null) {
                        Fragment fragment2 = fragmentStateManager.mFragment;
                        if (str.equals(fragment2.mTag)) {
                            return fragment2;
                        }
                    }
                }
                return null;
            }
        }
    }

    public final void forcePostponedTransactions() {
        Iterator it = ((HashSet) collectAllSpecialEffectsController()).iterator();
        while (it.hasNext()) {
            SpecialEffectsController specialEffectsController = (SpecialEffectsController) it.next();
            if (specialEffectsController.mIsContainerPostponed) {
                specialEffectsController.mIsContainerPostponed = false;
                specialEffectsController.executePendingOperations();
            }
        }
    }

    public final ViewGroup getFragmentContainer(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.mContainerId > 0 && this.mContainer.onHasView()) {
            View onFindViewById = this.mContainer.onFindViewById(fragment.mContainerId);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    public FragmentFactory getFragmentFactory() {
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getFragmentFactory();
        }
        return this.mHostFragmentFactory;
    }

    public List<Fragment> getFragments() {
        return this.mFragmentStore.getFragments();
    }

    public SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
        Fragment fragment = this.mParent;
        if (fragment != null) {
            return fragment.mFragmentManager.getSpecialEffectsControllerFactory();
        }
        return this.mDefaultSpecialEffectsControllerFactory;
    }

    public void hideFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
            setVisibleRemovingFragment(fragment);
        }
    }

    public final boolean isMenuAvailable(Fragment fragment) {
        FragmentManager fragmentManager = fragment.mChildFragmentManager;
        Iterator it = ((ArrayList) fragmentManager.mFragmentStore.getActiveFragments()).iterator();
        boolean z = false;
        while (it.hasNext()) {
            Fragment fragment2 = (Fragment) it.next();
            if (fragment2 != null) {
                z = fragmentManager.isMenuAvailable(fragment2);
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public boolean isParentMenuVisible(Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment == null) {
            return true;
        }
        return fragment.mMenuVisible && ((fragmentManager = fragment.mFragmentManager) == null || fragmentManager.isParentMenuVisible(fragment.mParentFragment));
    }

    public boolean isPrimaryNavigation(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.mFragmentManager;
        return fragment.equals(fragmentManager.mPrimaryNav) && isPrimaryNavigation(fragmentManager.mParent);
    }

    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
        if (r1 != 5) goto L_0x01ba;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void moveToState(final androidx.fragment.app.Fragment r17, int r18) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.moveToState(androidx.fragment.app.Fragment, int):void");
    }

    public void noteStateNotSaved() {
        if (this.mHost != null) {
            this.mStateSaved = false;
            this.mStopped = false;
            this.mNonConfig.mIsStateSaved = false;
            for (Fragment fragment : this.mFragmentStore.getFragments()) {
                if (fragment != null) {
                    fragment.mChildFragmentManager.noteStateNotSaved();
                }
            }
        }
    }

    public boolean popBackStackImmediate() {
        execPendingActions(false);
        ensureExecReady(true);
        Fragment fragment = this.mPrimaryNav;
        if (fragment != null && fragment.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean popBackStackState = popBackStackState(this.mTmpRecords, this.mTmpIsPop, null, -1, 0);
        if (popBackStackState) {
            this.mExecutingActions = true;
            try {
                removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            } finally {
                cleanupExec();
            }
        }
        updateOnBackPressedCallbackEnabled();
        doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return popBackStackState;
    }

    public boolean popBackStackState(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        ArrayList<BackStackRecord> arrayList3 = this.mBackStack;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.mBackStack.remove(size));
            arrayList2.add(Boolean.TRUE);
        } else {
            int i3 = -1;
            if (str != null || i >= 0) {
                i3 = arrayList3.size() - 1;
                while (i3 >= 0) {
                    BackStackRecord backStackRecord = this.mBackStack.get(i3);
                    if ((str != null && str.equals(backStackRecord.mName)) || (i >= 0 && i == backStackRecord.mIndex)) {
                        break;
                    }
                    i3--;
                }
                if (i3 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    while (true) {
                        i3--;
                        if (i3 < 0) {
                            break;
                        }
                        BackStackRecord backStackRecord2 = this.mBackStack.get(i3);
                        if (str == null || !str.equals(backStackRecord2.mName)) {
                            if (i < 0 || i != backStackRecord2.mIndex) {
                                break;
                            }
                        }
                    }
                }
            }
            if (i3 == this.mBackStack.size() - 1) {
                return false;
            }
            for (int size2 = this.mBackStack.size() - 1; size2 > i3; size2--) {
                arrayList.add(this.mBackStack.remove(size2));
                arrayList2.add(Boolean.TRUE);
            }
        }
        return true;
    }

    public void removeFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            this.mFragmentStore.removeFragment(fragment);
            if (isMenuAvailable(fragment)) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mRemoving = true;
            setVisibleRemovingFragment(fragment);
        }
    }

    public final void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> arrayList, ArrayList<Boolean> arrayList2) {
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == arrayList2.size()) {
                executePostponedTransaction(arrayList, arrayList2);
                int size = arrayList.size();
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    if (!arrayList.get(i).mReorderingAllowed) {
                        if (i2 != i) {
                            executeOpsTogether(arrayList, arrayList2, i2, i);
                        }
                        i2 = i + 1;
                        if (arrayList2.get(i).booleanValue()) {
                            while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).mReorderingAllowed) {
                                i2++;
                            }
                        }
                        executeOpsTogether(arrayList, arrayList2, i, i2);
                        i = i2 - 1;
                    }
                    i++;
                }
                if (i2 != size) {
                    executeOpsTogether(arrayList, arrayList2, i2, size);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error with the back stack records");
        }
    }

    public void restoreSaveState(Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.mActive != null) {
                this.mFragmentStore.mActive.clear();
                Iterator<FragmentState> it = fragmentManagerState.mActive.iterator();
                while (it.hasNext()) {
                    FragmentState next = it.next();
                    if (next != null) {
                        FragmentManagerViewModel fragmentManagerViewModel = this.mNonConfig;
                        Fragment fragment = fragmentManagerViewModel.mRetainedFragments.get(next.mWho);
                        if (fragment != null) {
                            if (isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
                            }
                            fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment, next);
                        } else {
                            fragmentStateManager = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, this.mHost.mContext.getClassLoader(), getFragmentFactory(), next);
                        }
                        Fragment fragment2 = fragmentStateManager.mFragment;
                        fragment2.mFragmentManager = this;
                        if (isLoggingEnabled(2)) {
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("restoreSaveState: active (");
                            outline13.append(fragment2.mWho);
                            outline13.append("): ");
                            outline13.append(fragment2);
                            Log.v("FragmentManager", outline13.toString());
                        }
                        fragmentStateManager.restoreState(this.mHost.mContext.getClassLoader());
                        this.mFragmentStore.makeActive(fragmentStateManager);
                        fragmentStateManager.mFragmentManagerState = this.mCurState;
                    }
                }
                FragmentManagerViewModel fragmentManagerViewModel2 = this.mNonConfig;
                Objects.requireNonNull(fragmentManagerViewModel2);
                Iterator it2 = new ArrayList(fragmentManagerViewModel2.mRetainedFragments.values()).iterator();
                while (it2.hasNext()) {
                    Fragment fragment3 = (Fragment) it2.next();
                    if (!this.mFragmentStore.containsActiveFragment(fragment3.mWho)) {
                        if (isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Discarding retained Fragment " + fragment3 + " that was not found in the set of active Fragments " + fragmentManagerState.mActive);
                        }
                        this.mNonConfig.removeRetainedFragment(fragment3);
                        fragment3.mFragmentManager = this;
                        FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment3);
                        fragmentStateManager2.mFragmentManagerState = 1;
                        fragmentStateManager2.moveToExpectedState();
                        fragment3.mRemoving = true;
                        fragmentStateManager2.moveToExpectedState();
                    }
                }
                FragmentStore fragmentStore = this.mFragmentStore;
                ArrayList<String> arrayList = fragmentManagerState.mAdded;
                fragmentStore.mAdded.clear();
                if (arrayList != null) {
                    for (String str : arrayList) {
                        Fragment findActiveFragment = fragmentStore.findActiveFragment(str);
                        if (findActiveFragment != null) {
                            if (isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + findActiveFragment);
                            }
                            fragmentStore.addFragment(findActiveFragment);
                        } else {
                            throw new IllegalStateException(GeneratedOutlineSupport.outline9("No instantiated fragment for (", str, ")"));
                        }
                    }
                }
                Fragment fragment4 = null;
                if (fragmentManagerState.mBackStack != null) {
                    this.mBackStack = new ArrayList<>(fragmentManagerState.mBackStack.length);
                    int i = 0;
                    while (true) {
                        BackStackState[] backStackStateArr = fragmentManagerState.mBackStack;
                        if (i >= backStackStateArr.length) {
                            break;
                        }
                        BackStackState backStackState = backStackStateArr[i];
                        Objects.requireNonNull(backStackState);
                        BackStackRecord backStackRecord = new BackStackRecord(this);
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            int[] iArr = backStackState.mOps;
                            if (i2 >= iArr.length) {
                                break;
                            }
                            FragmentTransaction.Op op = new FragmentTransaction.Op();
                            int i4 = i2 + 1;
                            op.mCmd = iArr[i2];
                            if (isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i3 + " base fragment #" + backStackState.mOps[i4]);
                            }
                            String str2 = backStackState.mFragmentWhos.get(i3);
                            if (str2 != null) {
                                op.mFragment = this.mFragmentStore.findActiveFragment(str2);
                            } else {
                                op.mFragment = fragment4;
                            }
                            op.mOldMaxState = Lifecycle.State.values()[backStackState.mOldMaxLifecycleStates[i3]];
                            op.mCurrentMaxState = Lifecycle.State.values()[backStackState.mCurrentMaxLifecycleStates[i3]];
                            int[] iArr2 = backStackState.mOps;
                            int i5 = i4 + 1;
                            int i6 = iArr2[i4];
                            op.mEnterAnim = i6;
                            int i7 = i5 + 1;
                            int i8 = iArr2[i5];
                            op.mExitAnim = i8;
                            int i9 = i7 + 1;
                            int i10 = iArr2[i7];
                            op.mPopEnterAnim = i10;
                            i2 = i9 + 1;
                            int i11 = iArr2[i9];
                            op.mPopExitAnim = i11;
                            backStackRecord.mEnterAnim = i6;
                            backStackRecord.mExitAnim = i8;
                            backStackRecord.mPopEnterAnim = i10;
                            backStackRecord.mPopExitAnim = i11;
                            backStackRecord.addOp(op);
                            i3++;
                            fragment4 = null;
                        }
                        backStackRecord.mTransition = backStackState.mTransition;
                        backStackRecord.mName = backStackState.mName;
                        backStackRecord.mIndex = backStackState.mIndex;
                        backStackRecord.mAddToBackStack = true;
                        backStackRecord.mBreadCrumbTitleRes = backStackState.mBreadCrumbTitleRes;
                        backStackRecord.mBreadCrumbTitleText = backStackState.mBreadCrumbTitleText;
                        backStackRecord.mBreadCrumbShortTitleRes = backStackState.mBreadCrumbShortTitleRes;
                        backStackRecord.mBreadCrumbShortTitleText = backStackState.mBreadCrumbShortTitleText;
                        backStackRecord.mSharedElementSourceNames = backStackState.mSharedElementSourceNames;
                        backStackRecord.mSharedElementTargetNames = backStackState.mSharedElementTargetNames;
                        backStackRecord.mReorderingAllowed = backStackState.mReorderingAllowed;
                        backStackRecord.bumpBackStackNesting(1);
                        if (isLoggingEnabled(2)) {
                            StringBuilder outline15 = GeneratedOutlineSupport.outline15("restoreAllState: back stack #", i, " (index ");
                            outline15.append(backStackRecord.mIndex);
                            outline15.append("): ");
                            outline15.append(backStackRecord);
                            Log.v("FragmentManager", outline15.toString());
                            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                            backStackRecord.dump("  ", printWriter, false);
                            printWriter.close();
                        }
                        this.mBackStack.add(backStackRecord);
                        i++;
                        fragment4 = null;
                    }
                } else {
                    this.mBackStack = null;
                }
                this.mBackStackIndex.set(fragmentManagerState.mBackStackIndex);
                String str3 = fragmentManagerState.mPrimaryNavActiveWho;
                if (str3 != null) {
                    Fragment findActiveFragment2 = findActiveFragment(str3);
                    this.mPrimaryNav = findActiveFragment2;
                    dispatchParentPrimaryNavigationFragmentChanged(findActiveFragment2);
                }
                ArrayList<String> arrayList2 = fragmentManagerState.mResultKeys;
                if (arrayList2 != null) {
                    for (int i12 = 0; i12 < arrayList2.size(); i12++) {
                        Bundle bundle = fragmentManagerState.mResults.get(i12);
                        bundle.setClassLoader(this.mHost.mContext.getClassLoader());
                        this.mResults.put(arrayList2.get(i12), bundle);
                    }
                }
                this.mLaunchedFragments = new ArrayDeque<>(fragmentManagerState.mLaunchedFragments);
            }
        }
    }

    public Parcelable saveAllState() {
        int i;
        BackStackState[] backStackStateArr;
        ArrayList<String> arrayList;
        int size;
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.mIsStateSaved = true;
        FragmentStore fragmentStore = this.mFragmentStore;
        Objects.requireNonNull(fragmentStore);
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(fragmentStore.mActive.size());
        Iterator<FragmentStateManager> it = fragmentStore.mActive.values().iterator();
        while (true) {
            backStackStateArr = null;
            Bundle bundle = null;
            backStackStateArr = null;
            if (!it.hasNext()) {
                break;
            }
            FragmentStateManager next = it.next();
            if (next != null) {
                Fragment fragment = next.mFragment;
                FragmentState fragmentState = new FragmentState(fragment);
                Fragment fragment2 = next.mFragment;
                if (fragment2.mState <= -1 || fragmentState.mSavedFragmentState != null) {
                    fragmentState.mSavedFragmentState = fragment2.mSavedFragmentState;
                } else {
                    Bundle bundle2 = new Bundle();
                    Fragment fragment3 = next.mFragment;
                    fragment3.onSaveInstanceState(bundle2);
                    fragment3.mSavedStateRegistryController.performSave(bundle2);
                    Parcelable saveAllState = fragment3.mChildFragmentManager.saveAllState();
                    if (saveAllState != null) {
                        bundle2.putParcelable("android:support:fragments", saveAllState);
                    }
                    next.mDispatcher.dispatchOnFragmentSaveInstanceState(next.mFragment, bundle2, false);
                    if (!bundle2.isEmpty()) {
                        bundle = bundle2;
                    }
                    if (next.mFragment.mView != null) {
                        next.saveViewState();
                    }
                    if (next.mFragment.mSavedViewState != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putSparseParcelableArray("android:view_state", next.mFragment.mSavedViewState);
                    }
                    if (next.mFragment.mSavedViewRegistryState != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBundle("android:view_registry_state", next.mFragment.mSavedViewRegistryState);
                    }
                    if (!next.mFragment.mUserVisibleHint) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBoolean("android:user_visible_hint", next.mFragment.mUserVisibleHint);
                    }
                    fragmentState.mSavedFragmentState = bundle;
                    if (next.mFragment.mTargetWho != null) {
                        if (bundle == null) {
                            fragmentState.mSavedFragmentState = new Bundle();
                        }
                        fragmentState.mSavedFragmentState.putString("android:target_state", next.mFragment.mTargetWho);
                        int i2 = next.mFragment.mTargetRequestCode;
                        if (i2 != 0) {
                            fragmentState.mSavedFragmentState.putInt("android:target_req_state", i2);
                        }
                    }
                }
                arrayList2.add(fragmentState);
                if (isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.mSavedFragmentState);
                }
            }
        }
        if (arrayList2.isEmpty()) {
            if (isLoggingEnabled(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        FragmentStore fragmentStore2 = this.mFragmentStore;
        synchronized (fragmentStore2.mAdded) {
            if (fragmentStore2.mAdded.isEmpty()) {
                arrayList = null;
            } else {
                arrayList = new ArrayList<>(fragmentStore2.mAdded.size());
                Iterator<Fragment> it2 = fragmentStore2.mAdded.iterator();
                while (it2.hasNext()) {
                    Fragment next2 = it2.next();
                    arrayList.add(next2.mWho);
                    if (isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + next2.mWho + "): " + next2);
                    }
                }
            }
        }
        ArrayList<BackStackRecord> arrayList3 = this.mBackStack;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (i = 0; i < size; i++) {
                backStackStateArr[i] = new BackStackState(this.mBackStack.get(i));
                if (isLoggingEnabled(2)) {
                    StringBuilder outline15 = GeneratedOutlineSupport.outline15("saveAllState: adding back stack #", i, ": ");
                    outline15.append(this.mBackStack.get(i));
                    Log.v("FragmentManager", outline15.toString());
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.mActive = arrayList2;
        fragmentManagerState.mAdded = arrayList;
        fragmentManagerState.mBackStack = backStackStateArr;
        fragmentManagerState.mBackStackIndex = this.mBackStackIndex.get();
        Fragment fragment4 = this.mPrimaryNav;
        if (fragment4 != null) {
            fragmentManagerState.mPrimaryNavActiveWho = fragment4.mWho;
        }
        fragmentManagerState.mResultKeys.addAll(this.mResults.keySet());
        fragmentManagerState.mResults.addAll(this.mResults.values());
        fragmentManagerState.mLaunchedFragments = new ArrayList<>(this.mLaunchedFragments);
        return fragmentManagerState;
    }

    public void scheduleCommit() {
        synchronized (this.mPendingActions) {
            ArrayList<StartEnterTransitionListener> arrayList = this.mPostponedTransactions;
            boolean z = false;
            boolean z2 = arrayList != null && !arrayList.isEmpty();
            if (this.mPendingActions.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.mHost.mHandler.removeCallbacks(this.mExecCommit);
                this.mHost.mHandler.post(this.mExecCommit);
                updateOnBackPressedCallbackEnabled();
            }
        }
    }

    public void setExitAnimationOrder(Fragment fragment, boolean z) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null && (fragmentContainer instanceof FragmentContainerView)) {
            ((FragmentContainerView) fragmentContainer).setDrawDisappearingViewsLast(!z);
        }
    }

    public void setMaxLifecycle(Fragment fragment, Lifecycle.State state) {
        if (!fragment.equals(findActiveFragment(fragment.mWho)) || !(fragment.mHost == null || fragment.mFragmentManager == this)) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        fragment.mMaxState = state;
    }

    public void setPrimaryNavigationFragment(Fragment fragment) {
        if (fragment == null || (fragment.equals(findActiveFragment(fragment.mWho)) && (fragment.mHost == null || fragment.mFragmentManager == this))) {
            Fragment fragment2 = this.mPrimaryNav;
            this.mPrimaryNav = fragment;
            dispatchParentPrimaryNavigationFragmentChanged(fragment2);
            dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public final void setVisibleRemovingFragment(Fragment fragment) {
        ViewGroup fragmentContainer = getFragmentContainer(fragment);
        if (fragmentContainer != null) {
            if (fragment.getPopExitAnim() + fragment.getPopEnterAnim() + fragment.getExitAnim() + fragment.getEnterAnim() > 0) {
                if (fragmentContainer.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    fragmentContainer.setTag(R.id.visible_removing_fragment_view_tag, fragment);
                }
                ((Fragment) fragmentContainer.getTag(R.id.visible_removing_fragment_view_tag)).setPopDirection(fragment.getPopDirection());
            }
        }
    }

    public void showFragment(Fragment fragment) {
        if (isLoggingEnabled(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public final void startPendingDeferredFragments() {
        Iterator it = ((ArrayList) this.mFragmentStore.getActiveFragmentStateManagers()).iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = (FragmentStateManager) it.next();
            Fragment fragment = fragmentStateManager.mFragment;
            if (fragment.mDeferStart) {
                if (this.mExecutingActions) {
                    this.mHavePendingDeferredStart = true;
                } else {
                    fragment.mDeferStart = false;
                    fragmentStateManager.moveToExpectedState();
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.mParent;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mParent)));
            sb.append("}");
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.mHost;
            if (fragmentHostCallback != null) {
                sb.append(fragmentHostCallback.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.mHost)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void updateOnBackPressedCallbackEnabled() {
        synchronized (this.mPendingActions) {
            boolean z = true;
            if (!this.mPendingActions.isEmpty()) {
                this.mOnBackPressedCallback.mEnabled = true;
                return;
            }
            OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
            ArrayList<BackStackRecord> arrayList = this.mBackStack;
            if ((arrayList != null ? arrayList.size() : 0) <= 0 || !isPrimaryNavigation(this.mParent)) {
                z = false;
            }
            onBackPressedCallback.mEnabled = z;
        }
    }

    public void moveToState(int i, boolean z) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.mHost == null && i != -1) {
            throw new IllegalStateException("No activity");
        } else if (z || i != this.mCurState) {
            this.mCurState = i;
            FragmentStore fragmentStore = this.mFragmentStore;
            Iterator<Fragment> it = fragmentStore.mAdded.iterator();
            while (it.hasNext()) {
                FragmentStateManager fragmentStateManager = fragmentStore.mActive.get(it.next().mWho);
                if (fragmentStateManager != null) {
                    fragmentStateManager.moveToExpectedState();
                }
            }
            Iterator<FragmentStateManager> it2 = fragmentStore.mActive.values().iterator();
            while (true) {
                boolean z2 = false;
                if (!it2.hasNext()) {
                    break;
                }
                FragmentStateManager next = it2.next();
                if (next != null) {
                    next.moveToExpectedState();
                    Fragment fragment = next.mFragment;
                    if (fragment.mRemoving && !fragment.isInBackStack()) {
                        z2 = true;
                    }
                    if (z2) {
                        fragmentStore.makeInactive(next);
                    }
                }
            }
            startPendingDeferredFragments();
            if (this.mNeedMenuInvalidate && (fragmentHostCallback = this.mHost) != null && this.mCurState == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.mNeedMenuInvalidate = false;
            }
        }
    }
}

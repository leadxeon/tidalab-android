package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.R$dimen;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner {
    public final LifecycleRegistry mLifecycleRegistry;
    public ViewModelStore mViewModelStore;
    public final ContextAwareHelper mContextAwareHelper = new ContextAwareHelper();
    public final SavedStateRegistryController mSavedStateRegistryController = new SavedStateRegistryController(this);
    public final OnBackPressedDispatcher mOnBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable() { // from class: androidx.activity.ComponentActivity.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                ComponentActivity.super.onBackPressed();
            } catch (IllegalStateException e) {
                if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                    throw e;
                }
            }
        }
    });
    public final ActivityResultRegistry mActivityResultRegistry = new ActivityResultRegistry() { // from class: androidx.activity.ComponentActivity.2
        @Override // androidx.activity.result.ActivityResultRegistry
        public <I, O> void onLaunch(final int i, ActivityResultContract<I, O> activityResultContract, I i2, ActivityOptionsCompat activityOptionsCompat) {
            Bundle bundle;
            final ComponentActivity componentActivity = ComponentActivity.this;
            final ActivityResultContract.SynchronousResult<O> synchronousResult = activityResultContract.getSynchronousResult(componentActivity, i2);
            if (synchronousResult != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.activity.ComponentActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ActivityResultCallback<?> activityResultCallback;
                        AnonymousClass2 r0 = AnonymousClass2.this;
                        int i3 = i;
                        Object obj = synchronousResult.mValue;
                        String str = r0.mRcToKey.get(Integer.valueOf(i3));
                        if (str != null) {
                            r0.mLaunchedKeys.remove(str);
                            ActivityResultRegistry.CallbackAndContract<?> callbackAndContract = r0.mKeyToCallback.get(str);
                            if (callbackAndContract == null || (activityResultCallback = callbackAndContract.mCallback) == null) {
                                r0.mPendingResults.remove(str);
                                r0.mParsedPendingResults.put(str, obj);
                                return;
                            }
                            activityResultCallback.onActivityResult(obj);
                        }
                    }
                });
                return;
            }
            Intent createIntent = activityResultContract.createIntent(componentActivity, i2);
            if (createIntent.getExtras() != null && createIntent.getExtras().getClassLoader() == null) {
                createIntent.setExtrasClassLoader(componentActivity.getClassLoader());
            }
            if (createIntent.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                Bundle bundleExtra = createIntent.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                createIntent.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                bundle = bundleExtra;
            } else if (activityOptionsCompat == null) {
                bundle = null;
            } else {
                throw null;
            }
            if ("androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(createIntent.getAction())) {
                final String[] stringArrayExtra = createIntent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                if (stringArrayExtra == null) {
                    stringArrayExtra = new String[0];
                }
                int i3 = ActivityCompat.$r8$clinit;
                for (String str : stringArrayExtra) {
                    if (TextUtils.isEmpty(str)) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
                    }
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    if (componentActivity instanceof ActivityCompat.RequestPermissionsRequestCodeValidator) {
                        ((ActivityCompat.RequestPermissionsRequestCodeValidator) componentActivity).validateRequestPermissionsRequestCode(i);
                    }
                    componentActivity.requestPermissions(stringArrayExtra, i);
                } else if (componentActivity instanceof ActivityCompat.OnRequestPermissionsResultCallback) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.core.app.ActivityCompat.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int[] iArr = new int[stringArrayExtra.length];
                            PackageManager packageManager = componentActivity.getPackageManager();
                            String packageName = componentActivity.getPackageName();
                            int length = stringArrayExtra.length;
                            for (int i4 = 0; i4 < length; i4++) {
                                iArr[i4] = packageManager.checkPermission(stringArrayExtra[i4], packageName);
                            }
                            ((OnRequestPermissionsResultCallback) componentActivity).onRequestPermissionsResult(i, stringArrayExtra, iArr);
                        }
                    });
                }
            } else if ("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(createIntent.getAction())) {
                IntentSenderRequest intentSenderRequest = (IntentSenderRequest) createIntent.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
                try {
                    IntentSender intentSender = intentSenderRequest.mIntentSender;
                    Intent intent = intentSenderRequest.mFillInIntent;
                    int i4 = intentSenderRequest.mFlagsMask;
                    int i5 = intentSenderRequest.mFlagsValues;
                    int i6 = ActivityCompat.$r8$clinit;
                    componentActivity.startIntentSenderForResult(intentSender, i, intent, i4, i5, 0, bundle);
                } catch (IntentSender.SendIntentException e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.activity.ComponentActivity.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            dispatchResult(i, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", e));
                        }
                    });
                }
            } else {
                int i7 = ActivityCompat.$r8$clinit;
                componentActivity.startActivityForResult(createIntent, i, bundle);
            }
        }
    };

    /* loaded from: classes.dex */
    public static final class NonConfigurationInstances {
        public ViewModelStore viewModelStore;
    }

    public ComponentActivity() {
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.mLifecycleRegistry = lifecycleRegistry;
        new AtomicInteger();
        if (lifecycleRegistry != null) {
            int i = Build.VERSION.SDK_INT;
            lifecycleRegistry.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.3
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_STOP) {
                        Window window = ComponentActivity.this.getWindow();
                        View peekDecorView = window != null ? window.peekDecorView() : null;
                        if (peekDecorView != null) {
                            peekDecorView.cancelPendingInputEvents();
                        }
                    }
                }
            });
            lifecycleRegistry.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.4
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        ComponentActivity.this.mContextAwareHelper.mContext = null;
                        if (!ComponentActivity.this.isChangingConfigurations()) {
                            ComponentActivity.this.getViewModelStore().clear();
                        }
                    }
                }
            });
            lifecycleRegistry.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.ComponentActivity.5
                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    ComponentActivity.this.ensureViewModelStore();
                    LifecycleRegistry lifecycleRegistry2 = ComponentActivity.this.mLifecycleRegistry;
                    lifecycleRegistry2.enforceMainThreadIfNeeded("removeObserver");
                    lifecycleRegistry2.mObserverMap.remove(this);
                }
            });
            if (i <= 23) {
                lifecycleRegistry.addObserver(new ImmLeaksCleaner(this));
                return;
            }
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    @Override // android.app.Activity
    public void addContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        super.addContentView(view, layoutParams);
    }

    public void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nonConfigurationInstances != null) {
                this.mViewModelStore = nonConfigurationInstances.viewModelStore;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new ViewModelStore();
            }
        }
    }

    @Override // androidx.activity.result.ActivityResultRegistryOwner
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.mRegistry;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public final void initViewTreeOwners() {
        getWindow().getDecorView().setTag(R.id.view_tree_lifecycle_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_view_model_store_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_saved_state_registry_owner, this);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.mActivityResultRegistry.dispatchResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mSavedStateRegistryController.performRestore(bundle);
        ContextAwareHelper contextAwareHelper = this.mContextAwareHelper;
        contextAwareHelper.mContext = this;
        for (OnContextAvailableListener onContextAvailableListener : contextAwareHelper.mListeners) {
            onContextAvailableListener.onContextAvailable(this);
        }
        super.onCreate(bundle);
        ActivityResultRegistry activityResultRegistry = this.mActivityResultRegistry;
        Objects.requireNonNull(activityResultRegistry);
        if (bundle != null) {
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (!(stringArrayList == null || integerArrayList == null)) {
                int size = stringArrayList.size();
                for (int i = 0; i < size; i++) {
                    int intValue = integerArrayList.get(i).intValue();
                    String str = stringArrayList.get(i);
                    activityResultRegistry.mRcToKey.put(Integer.valueOf(intValue), str);
                    activityResultRegistry.mKeyToRc.put(str, Integer.valueOf(intValue));
                }
                activityResultRegistry.mLaunchedKeys = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                activityResultRegistry.mRandom = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
                activityResultRegistry.mPendingResults.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
            }
        }
        ReportFragment.injectIfNeededIn(this);
    }

    @Override // android.app.Activity
    @Deprecated
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (!this.mActivityResultRegistry.dispatchResult(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr)) && Build.VERSION.SDK_INT >= 23) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances;
        ViewModelStore viewModelStore = this.mViewModelStore;
        if (viewModelStore == null && (nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance()) != null) {
            viewModelStore = nonConfigurationInstances.viewModelStore;
        }
        if (viewModelStore == null) {
            return null;
        }
        NonConfigurationInstances nonConfigurationInstances2 = new NonConfigurationInstances();
        nonConfigurationInstances2.viewModelStore = viewModelStore;
        return nonConfigurationInstances2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        LifecycleRegistry lifecycleRegistry = this.mLifecycleRegistry;
        if (lifecycleRegistry instanceof LifecycleRegistry) {
            Lifecycle.State state = Lifecycle.State.CREATED;
            lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
            lifecycleRegistry.moveToState(state);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.performSave(bundle);
        ActivityResultRegistry activityResultRegistry = this.mActivityResultRegistry;
        Objects.requireNonNull(activityResultRegistry);
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(activityResultRegistry.mRcToKey.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(activityResultRegistry.mRcToKey.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(activityResultRegistry.mLaunchedKeys));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) activityResultRegistry.mPendingResults.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", activityResultRegistry.mRandom);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (R$dimen.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for " + getComponentName());
            }
            super.reportFullyDrawn();
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        initViewTreeOwners();
        super.setContentView(i);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        initViewTreeOwners();
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view, @SuppressLint({"UnknownNullness", "MissingNullability"}) ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        super.setContentView(view, layoutParams);
    }
}

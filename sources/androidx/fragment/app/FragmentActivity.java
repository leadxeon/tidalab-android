package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
/* loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
    public boolean mCreated;
    public final FragmentController mFragments;
    public boolean mResumed;
    public final LifecycleRegistry mFragmentLifecycleRegistry = new LifecycleRegistry(this);
    public boolean mStopped = true;

    /* loaded from: classes.dex */
    public class HostCallbacks extends FragmentHostCallback<FragmentActivity> implements ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, FragmentOnAttachListener {
        public HostCallbacks() {
            super(FragmentActivity.this);
        }

        @Override // androidx.activity.result.ActivityResultRegistryOwner
        public ActivityResultRegistry getActivityResultRegistry() {
            return FragmentActivity.this.mActivityResultRegistry;
        }

        @Override // androidx.lifecycle.LifecycleOwner
        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override // androidx.activity.OnBackPressedDispatcherOwner
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.mOnBackPressedDispatcher;
        }

        @Override // androidx.lifecycle.ViewModelStoreOwner
        public ViewModelStore getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(FragmentManager fragmentManager, Fragment fragment) {
            FragmentActivity.this.onAttachFragment();
        }

        @Override // androidx.fragment.app.FragmentContainer
        public View onFindViewById(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public FragmentActivity onGetHost() {
            return FragmentActivity.this;
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public LayoutInflater onGetLayoutInflater() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public boolean onShouldSaveFragmentState(Fragment fragment) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override // androidx.fragment.app.FragmentHostCallback
        public void onSupportInvalidateOptionsMenu() {
            FragmentActivity.this.supportInvalidateOptionsMenu();
        }
    }

    public FragmentActivity() {
        HostCallbacks hostCallbacks = new HostCallbacks();
        AppOpsManagerCompat.checkNotNull(hostCallbacks, "callbacks == null");
        this.mFragments = new FragmentController(hostCallbacks);
        this.mSavedStateRegistryController.mRegistry.registerSavedStateProvider("android:support:fragments", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.fragment.app.FragmentActivity.1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public Bundle saveState() {
                Bundle bundle = new Bundle();
                do {
                } while (FragmentActivity.markState(FragmentActivity.this.getSupportFragmentManager(), Lifecycle.State.CREATED));
                FragmentActivity.this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
                Parcelable saveAllState = FragmentActivity.this.mFragments.mHost.mFragmentManager.saveAllState();
                if (saveAllState != null) {
                    bundle.putParcelable("android:support:fragments", saveAllState);
                }
                return bundle;
            }
        });
        OnContextAvailableListener onContextAvailableListener = new OnContextAvailableListener() { // from class: androidx.fragment.app.FragmentActivity.2
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context) {
                FragmentHostCallback<?> fragmentHostCallback = FragmentActivity.this.mFragments.mHost;
                fragmentHostCallback.mFragmentManager.attachController(fragmentHostCallback, fragmentHostCallback, null);
                Bundle consumeRestoredStateForKey = FragmentActivity.this.mSavedStateRegistryController.mRegistry.consumeRestoredStateForKey("android:support:fragments");
                if (consumeRestoredStateForKey != null) {
                    Parcelable parcelable = consumeRestoredStateForKey.getParcelable("android:support:fragments");
                    FragmentHostCallback<?> fragmentHostCallback2 = FragmentActivity.this.mFragments.mHost;
                    if (fragmentHostCallback2 instanceof ViewModelStoreOwner) {
                        fragmentHostCallback2.mFragmentManager.restoreSaveState(parcelable);
                        return;
                    }
                    throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
                }
            }
        };
        ContextAwareHelper contextAwareHelper = this.mContextAwareHelper;
        if (contextAwareHelper.mContext != null) {
            onContextAvailableListener.onContextAvailable(contextAwareHelper.mContext);
        }
        contextAwareHelper.mListeners.add(onContextAvailableListener);
    }

    public static boolean markState(FragmentManager fragmentManager, Lifecycle.State state) {
        Lifecycle.State state2 = Lifecycle.State.STARTED;
        boolean z = false;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null) {
                FragmentHostCallback<?> fragmentHostCallback = fragment.mHost;
                if ((fragmentHostCallback == null ? null : fragmentHostCallback.onGetHost()) != null) {
                    z |= markState(fragment.getChildFragmentManager(), state);
                }
                FragmentViewLifecycleOwner fragmentViewLifecycleOwner = fragment.mViewLifecycleOwner;
                if (fragmentViewLifecycleOwner != null) {
                    fragmentViewLifecycleOwner.initialize();
                    if (fragmentViewLifecycleOwner.mLifecycleRegistry.mState.compareTo(state2) >= 0) {
                        LifecycleRegistry lifecycleRegistry = fragment.mViewLifecycleOwner.mLifecycleRegistry;
                        lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
                        lifecycleRegistry.moveToState(state);
                        z = true;
                    }
                }
                if (fragment.mLifecycleRegistry.mState.compareTo(state2) >= 0) {
                    LifecycleRegistry lifecycleRegistry2 = fragment.mLifecycleRegistry;
                    lifecycleRegistry2.enforceMainThreadIfNeeded("setCurrentState");
                    lifecycleRegistry2.moveToState(state);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            LoaderManager.getInstance(this).dump(str2, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.mHost.mFragmentManager.dump(str, fileDescriptor, printWriter, strArr);
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.mHost.mFragmentManager;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragments.noteStateNotSaved();
        super.onActivityResult(i, i2, intent);
    }

    @Deprecated
    public void onAttachFragment() {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.noteStateNotSaved();
        this.mFragments.mHost.mFragmentManager.dispatchConfigurationChanged(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        this.mFragments.mHost.mFragmentManager.dispatchCreate();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        FragmentController fragmentController = this.mFragments;
        return onCreatePanelMenu | fragmentController.mHost.mFragmentManager.dispatchCreateOptionsMenu(menu, getMenuInflater());
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View onCreateView = this.mFragments.mHost.mFragmentManager.mLayoutInflaterFactory.onCreateView(view, str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(view, str, context, attributeSet) : onCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.mHost.mFragmentManager.dispatchDestroy();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.mHost.mFragmentManager.dispatchLowMemory();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.mFragments.mHost.mFragmentManager.dispatchOptionsItemSelected(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.mFragments.mHost.mFragmentManager.dispatchContextItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.mFragments.mHost.mFragmentManager.dispatchMultiWindowModeChanged(z);
    }

    @Override // android.app.Activity
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.mFragments.mHost.mFragmentManager.dispatchOptionsMenuClosed(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.mHost.mFragmentManager.dispatchStateChange(5);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.mFragments.mHost.mFragmentManager.dispatchPictureInPictureModeChanged(z);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        FragmentManager fragmentManager = this.mFragments.mHost.mFragmentManager;
        fragmentManager.mStateSaved = false;
        fragmentManager.mStopped = false;
        fragmentManager.mNonConfig.mIsStateSaved = false;
        fragmentManager.dispatchStateChange(7);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i == 0) {
            return super.onPreparePanel(0, view, menu) | this.mFragments.mHost.mFragmentManager.dispatchPrepareOptionsMenu(menu);
        }
        return super.onPreparePanel(i, view, menu);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.mFragments.noteStateNotSaved();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mResumed = true;
        this.mFragments.noteStateNotSaved();
        this.mFragments.mHost.mFragmentManager.execPendingActions(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            FragmentManager fragmentManager = this.mFragments.mHost.mFragmentManager;
            fragmentManager.mStateSaved = false;
            fragmentManager.mStopped = false;
            fragmentManager.mNonConfig.mIsStateSaved = false;
            fragmentManager.dispatchStateChange(4);
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.mHost.mFragmentManager.execPendingActions(true);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        FragmentManager fragmentManager2 = this.mFragments.mHost.mFragmentManager;
        fragmentManager2.mStateSaved = false;
        fragmentManager2.mStopped = false;
        fragmentManager2.mNonConfig.mIsStateSaved = false;
        fragmentManager2.dispatchStateChange(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        do {
        } while (markState(getSupportFragmentManager(), Lifecycle.State.CREATED));
        FragmentManager fragmentManager = this.mFragments.mHost.mFragmentManager;
        fragmentManager.mStopped = true;
        fragmentManager.mNonConfig.mIsStateSaved = true;
        fragmentManager.dispatchStateChange(4);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    @Override // androidx.core.app.ActivityCompat.RequestPermissionsRequestCodeValidator
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View onCreateView = this.mFragments.mHost.mFragmentManager.mLayoutInflaterFactory.onCreateView(null, str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(str, context, attributeSet) : onCreateView;
    }
}

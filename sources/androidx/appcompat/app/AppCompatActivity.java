package androidx.appcompat.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.collection.ArraySet;
import androidx.core.app.ActivityCompat;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.savedstate.SavedStateRegistry;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements AppCompatCallback {
    public AppCompatDelegate mDelegate;

    public AppCompatActivity() {
        this.mSavedStateRegistryController.mRegistry.registerSavedStateProvider("androidx:appcompat", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.appcompat.app.AppCompatActivity.1
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public Bundle saveState() {
                Bundle bundle = new Bundle();
                AppCompatActivity.this.getDelegate().onSaveInstanceState(bundle);
                return bundle;
            }
        });
        OnContextAvailableListener onContextAvailableListener = new OnContextAvailableListener() { // from class: androidx.appcompat.app.AppCompatActivity.2
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context) {
                AppCompatDelegate delegate = AppCompatActivity.this.getDelegate();
                delegate.installViewFactory();
                delegate.onCreate(AppCompatActivity.this.mSavedStateRegistryController.mRegistry.consumeRestoredStateForKey("androidx:appcompat"));
            }
        };
        ContextAwareHelper contextAwareHelper = this.mContextAwareHelper;
        if (contextAwareHelper.mContext != null) {
            onContextAvailableListener.onContextAvailable(contextAwareHelper.mContext);
        }
        contextAwareHelper.mListeners.add(onContextAvailableListener);
    }

    private void initViewTreeOwners() {
        getWindow().getDecorView().setTag(R.id.view_tree_lifecycle_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_view_model_store_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_saved_state_registry_owner, this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        getDelegate().addContentView(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(getDelegate().attachBaseContext2(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !supportActionBar.closeOptionsMenu()) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        ActionBar supportActionBar = getSupportActionBar();
        if (keyCode != 82 || supportActionBar == null || !supportActionBar.onMenuKeyEvent(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) getDelegate().findViewById(i);
    }

    public AppCompatDelegate getDelegate() {
        if (this.mDelegate == null) {
            ArraySet<WeakReference<AppCompatDelegate>> arraySet = AppCompatDelegate.sActivityDelegates;
            this.mDelegate = new AppCompatDelegateImpl(this, null, this, this);
        }
        return this.mDelegate;
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        int i = VectorEnabledTintResources.$r8$clinit;
        return super.getResources();
    }

    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    public Intent getSupportParentActivityIntent() {
        return AppOpsManagerCompat.getParentActivityIntent(this);
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getDelegate().onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.getDisplayOptions() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    public void onNightModeChanged() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        getDelegate().onPostCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    public void onPrepareSupportNavigateUpTaskStack() {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        getDelegate().onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeFinished(ActionMode actionMode) {
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public void onSupportActionModeStarted(ActionMode actionMode) {
    }

    public boolean onSupportNavigateUp() {
        Intent parentActivityIntent = AppOpsManagerCompat.getParentActivityIntent(this);
        if (parentActivityIntent == null) {
            return false;
        }
        if (shouldUpRecreateTask(parentActivityIntent)) {
            ArrayList arrayList = new ArrayList();
            Intent supportParentActivityIntent = getSupportParentActivityIntent();
            if (supportParentActivityIntent == null) {
                supportParentActivityIntent = AppOpsManagerCompat.getParentActivityIntent(this);
            }
            if (supportParentActivityIntent != null) {
                ComponentName component = supportParentActivityIntent.getComponent();
                if (component == null) {
                    component = supportParentActivityIntent.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent parentActivityIntent2 = AppOpsManagerCompat.getParentActivityIntent(this, component);
                    while (parentActivityIntent2 != null) {
                        arrayList.add(size, parentActivityIntent2);
                        parentActivityIntent2 = AppOpsManagerCompat.getParentActivityIntent(this, parentActivityIntent2.getComponent());
                    }
                    arrayList.add(supportParentActivityIntent);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            onPrepareSupportNavigateUpTaskStack();
            if (!arrayList.isEmpty()) {
                Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                Object obj = ContextCompat.sLock;
                ContextCompat.Api16Impl.startActivities(this, intentArr, null);
                try {
                    int i = ActivityCompat.$r8$clinit;
                    finishAffinity();
                    return true;
                } catch (IllegalStateException unused) {
                    finish();
                    return true;
                }
            } else {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
        } else {
            navigateUpTo(parentActivityIntent);
            return true;
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        getDelegate().setTitle(charSequence);
    }

    @Override // androidx.appcompat.app.AppCompatCallback
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        ActionBar supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !supportActionBar.openOptionsMenu()) {
            super.openOptionsMenu();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        initViewTreeOwners();
        getDelegate().setContentView(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        getDelegate().setTheme(i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        initViewTreeOwners();
        getDelegate().setContentView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initViewTreeOwners();
        getDelegate().setContentView(view, layoutParams);
    }
}

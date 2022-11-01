package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import java.lang.ref.WeakReference;
import java.util.Iterator;
/* loaded from: classes.dex */
public abstract class AppCompatDelegate {
    public static final ArraySet<WeakReference<AppCompatDelegate>> sActivityDelegates = new ArraySet<>(0);
    public static final Object sActivityDelegatesLock = new Object();

    public static void removeDelegateFromActives(AppCompatDelegate appCompatDelegate) {
        synchronized (sActivityDelegatesLock) {
            Iterator<WeakReference<AppCompatDelegate>> it = sActivityDelegates.iterator();
            while (it.hasNext()) {
                AppCompatDelegate appCompatDelegate2 = it.next().get();
                if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public Context attachBaseContext2(Context context) {
        return context;
    }

    public abstract <T extends View> T findViewById(int i);

    public int getLocalNightMode() {
        return -100;
    }

    public abstract MenuInflater getMenuInflater();

    public abstract ActionBar getSupportActionBar();

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle bundle);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int i);

    public abstract void setContentView(int i);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void setSupportActionBar(Toolbar toolbar);

    public void setTheme(int i) {
    }

    public abstract void setTitle(CharSequence charSequence);
}

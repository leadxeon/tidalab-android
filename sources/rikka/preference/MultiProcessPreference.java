package rikka.preference;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import rikka.preference.IMultiProcessPreferenceChangeListener;
/* loaded from: classes.dex */
public class MultiProcessPreference implements SharedPreferences, Handler.Callback {
    public static final Object CONTENT = new Object();
    public final ContentResolver mContentResolver;
    public final Uri mUri;
    public final Object mLock = new Object();
    public final WeakHashMap<SharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners = new WeakHashMap<>();
    public final IMultiProcessPreferenceChangeListener mListener = new AnonymousClass1();
    public final Handler mHandler = new Handler(Looper.getMainLooper(), this);

    /* renamed from: rikka.preference.MultiProcessPreference$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends IMultiProcessPreferenceChangeListener.Stub {
        public AnonymousClass1() {
        }

        @Override // rikka.preference.IMultiProcessPreferenceChangeListener
        public void onPreferenceChanged(String str) {
            Message obtain = Message.obtain();
            obtain.what = 100;
            obtain.obj = str;
            MultiProcessPreference.this.mHandler.sendMessage(obtain);
        }
    }

    /* loaded from: classes.dex */
    public class Editor implements SharedPreferences.Editor {
        public Bundle mData = new Bundle();
        public ArrayList<String> mActions = new ArrayList<>();
        public ArrayList<String> mKeys = new ArrayList<>();
        public ArrayList<Object> mValues = new ArrayList<>();

        public Editor() {
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            finish(false);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.mActions.add("clear");
            this.mKeys.add(null);
            this.mValues.add(null);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return finish(true);
        }

        public final boolean finish(boolean z) {
            this.mData.putStringArrayList("editor_actions", this.mActions);
            this.mData.putStringArrayList("editor_keys", this.mKeys);
            this.mData.putSerializable("editor_values", this.mValues);
            MultiProcessPreference multiProcessPreference = MultiProcessPreference.this;
            Bundle call = multiProcessPreference.mContentResolver.call(multiProcessPreference.mUri, z ? "editor_commit" : "editor_apply", (String) null, this.mData);
            if (call == null) {
                return false;
            }
            return call.getBoolean("result", false);
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.mActions.add("putBoolean");
            this.mKeys.add(str);
            this.mValues.add(Boolean.valueOf(z));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            this.mActions.add("putFloat");
            this.mKeys.add(str);
            this.mValues.add(Float.valueOf(f));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            this.mActions.add("putInt");
            this.mKeys.add(str);
            this.mValues.add(Integer.valueOf(i));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            this.mActions.add("putLong");
            this.mKeys.add(str);
            this.mValues.add(Long.valueOf(j));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.mActions.add("putString");
            this.mKeys.add(str);
            this.mValues.add(str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set set) {
            this.mActions.add("putStringSet");
            this.mKeys.add(str);
            this.mValues.add(set == null ? null : new HashSet(set));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.mActions.add("remove");
            this.mKeys.add(str);
            this.mValues.add(null);
            return this;
        }
    }

    public MultiProcessPreference(Context context, String str) {
        this.mContentResolver = context.getContentResolver();
        this.mUri = new Uri.Builder().scheme("content").authority(str).build();
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        Objects.requireNonNull(str);
        return this.mContentResolver.call(this.mUri, "contains", str, (Bundle) null) != null;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new Editor();
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        Bundle call = this.mContentResolver.call(this.mUri, "getAll", (String) null, (Bundle) null);
        if (call == null) {
            return null;
        }
        return (Map) call.getSerializable("result");
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getBoolean", str, (Bundle) null);
        return call == null ? z : call.getBoolean("result");
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getFloat", str, (Bundle) null);
        return call == null ? f : call.getFloat("result");
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getInt", str, (Bundle) null);
        return call == null ? i : call.getInt("result");
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getLong", str, (Bundle) null);
        return call == null ? j : call.getLong("result");
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getString", str, (Bundle) null);
        return call == null ? str2 : call.getString("result", str2);
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        Objects.requireNonNull(str);
        Bundle call = this.mContentResolver.call(this.mUri, "getStringSet", str, (Bundle) null);
        return call == null ? set : (Set) call.getSerializable("result");
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 100) {
            return false;
        }
        Object obj = message.obj;
        if (!(obj instanceof String)) {
            return false;
        }
        String str = (String) obj;
        for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.mListeners.keySet()) {
            if (onSharedPreferenceChangeListener != null) {
                onSharedPreferenceChangeListener.onSharedPreferenceChanged(this, str);
            }
        }
        return true;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            if (this.mListeners.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putBinder("data", this.mListener.asBinder());
                this.mContentResolver.call(this.mUri, "registerListener", (String) null, bundle);
            }
            this.mListeners.put(onSharedPreferenceChangeListener, CONTENT);
        }
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.mLock) {
            this.mListeners.remove(onSharedPreferenceChangeListener);
            if (this.mListeners.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putBinder("data", this.mListener.asBinder());
                this.mContentResolver.call(this.mUri, "unregisterListener", (String) null, bundle);
            }
        }
    }
}

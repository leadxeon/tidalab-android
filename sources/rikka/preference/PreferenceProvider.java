package rikka.preference;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import okhttp3.internal.ws.WebSocketProtocol;
import rikka.preference.IMultiProcessPreferenceChangeListener;
/* loaded from: classes.dex */
public abstract class PreferenceProvider extends ContentProvider implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final RemoteCallbackList<IMultiProcessPreferenceChangeListener> mListeners = new RemoteCallbackList<>();
    public SharedPreferences mSharedPreferences;

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        SharedPreferences sharedPreferences = context.getSharedPreferences("service", 0);
        this.mSharedPreferences = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        new Uri.Builder().scheme("content").authority(providerInfo.authority).build();
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1249367445:
                if (str.equals("getAll")) {
                    c = 0;
                    break;
                }
                break;
            case -1249359687:
                if (str.equals("getInt")) {
                    c = 1;
                    break;
                }
                break;
            case -1161035703:
                if (str.equals("editor_commit")) {
                    c = 2;
                    break;
                }
                break;
            case -1037975280:
                if (str.equals("unregisterListener")) {
                    c = 3;
                    break;
                }
                break;
            case -732003812:
                if (str.equals("editor_apply")) {
                    c = 4;
                    break;
                }
                break;
            case -567445985:
                if (str.equals("contains")) {
                    c = 5;
                    break;
                }
                break;
            case -198897701:
                if (str.equals("getStringSet")) {
                    c = 6;
                    break;
                }
                break;
            case -75354382:
                if (str.equals("getLong")) {
                    c = 7;
                    break;
                }
                break;
            case 804029191:
                if (str.equals("getString")) {
                    c = '\b';
                    break;
                }
                break;
            case 1101572082:
                if (str.equals("getBoolean")) {
                    c = '\t';
                    break;
                }
                break;
            case 1115161719:
                if (str.equals("registerListener")) {
                    c = '\n';
                    break;
                }
                break;
            case 1953351846:
                if (str.equals("getFloat")) {
                    c = 11;
                    break;
                }
                break;
        }
        HashSet hashSet = null;
        switch (c) {
            case 0:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("result", new HashMap(this.mSharedPreferences.getAll()));
                return bundle2;
            case 1:
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle3 = new Bundle();
                bundle3.putInt("result", this.mSharedPreferences.getInt(str2, 0));
                return bundle3;
            case 2:
                Objects.requireNonNull(bundle);
                return edit(true, bundle);
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                Objects.requireNonNull(bundle);
                IMultiProcessPreferenceChangeListener asInterface = IMultiProcessPreferenceChangeListener.Stub.asInterface(bundle.getBinder("data"));
                if (asInterface != null) {
                    synchronized (this) {
                        this.mListeners.unregister(asInterface);
                    }
                }
                return null;
            case 4:
                Objects.requireNonNull(bundle);
                return edit(false, bundle);
            case 5:
                Objects.requireNonNull(str2);
                if (this.mSharedPreferences.contains(str2)) {
                    return new Bundle();
                }
                return null;
            case 6:
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle4 = new Bundle();
                Set<String> stringSet = this.mSharedPreferences.getStringSet(str2, null);
                if (stringSet != null) {
                    hashSet = new HashSet(stringSet);
                }
                bundle4.putSerializable("result", hashSet);
                return bundle4;
            case 7:
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle5 = new Bundle();
                bundle5.putLong("result", this.mSharedPreferences.getLong(str2, 0L));
                return bundle5;
            case '\b':
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle6 = new Bundle();
                bundle6.putString("result", this.mSharedPreferences.getString(str2, null));
                return bundle6;
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle7 = new Bundle();
                bundle7.putBoolean("result", this.mSharedPreferences.getBoolean(str2, false));
                return bundle7;
            case '\n':
                Objects.requireNonNull(bundle);
                IMultiProcessPreferenceChangeListener asInterface2 = IMultiProcessPreferenceChangeListener.Stub.asInterface(bundle.getBinder("data"));
                if (asInterface2 != null) {
                    synchronized (this) {
                        this.mListeners.register(asInterface2);
                    }
                }
                return null;
            case 11:
                Objects.requireNonNull(str2);
                if (!this.mSharedPreferences.contains(str2)) {
                    return null;
                }
                Bundle bundle8 = new Bundle();
                bundle8.putFloat("result", this.mSharedPreferences.getFloat(str2, 0.0f));
                return bundle8;
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("unsupported method ", str));
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @SuppressLint({"ApplySharedPref"})
    public Bundle edit(boolean z, Bundle bundle) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        ArrayList<String> stringArrayList = bundle.getStringArrayList("editor_actions");
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList("editor_keys");
        List list = (List) bundle.getSerializable("editor_values");
        Objects.requireNonNull(stringArrayList);
        Objects.requireNonNull(stringArrayList2);
        Objects.requireNonNull(list);
        for (int i = 0; i < stringArrayList.size(); i++) {
            String str = stringArrayList.get(i);
            String str2 = stringArrayList2.get(i);
            Object obj = list.get(i);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2018571198:
                    if (str.equals("putStringSet")) {
                        c = 0;
                        break;
                    }
                    break;
                case -976920992:
                    if (str.equals("putInt")) {
                        c = 1;
                        break;
                    }
                    break;
                case -934610812:
                    if (str.equals("remove")) {
                        c = 2;
                        break;
                    }
                    break;
                case -462997504:
                    if (str.equals("putString")) {
                        c = 3;
                        break;
                    }
                    break;
                case -219689429:
                    if (str.equals("putLong")) {
                        c = 4;
                        break;
                    }
                    break;
                case 94746189:
                    if (str.equals("clear")) {
                        c = 5;
                        break;
                    }
                    break;
                case 478450201:
                    if (str.equals("putBoolean")) {
                        c = 6;
                        break;
                    }
                    break;
                case 1773932685:
                    if (str.equals("putFloat")) {
                        c = 7;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    edit.putStringSet(str2, (Set) obj);
                    break;
                case 1:
                    edit.putInt(str2, ((Integer) obj).intValue());
                    break;
                case 2:
                    edit.remove(str2);
                    break;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    edit.putString(str2, (String) obj);
                    break;
                case 4:
                    edit.putLong(str2, ((Long) obj).longValue());
                    break;
                case 5:
                    edit.clear();
                    break;
                case 6:
                    edit.putBoolean(str2, ((Boolean) obj).booleanValue());
                    break;
                case 7:
                    edit.putFloat(str2, ((Float) obj).floatValue());
                    break;
            }
        }
        if (z) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("result", edit.commit());
            return bundle2;
        }
        edit.apply();
        return null;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        int beginBroadcast = this.mListeners.beginBroadcast();
        while (beginBroadcast > 0) {
            beginBroadcast--;
            IMultiProcessPreferenceChangeListener broadcastItem = this.mListeners.getBroadcastItem(beginBroadcast);
            if (broadcastItem != null) {
                try {
                    broadcastItem.onPreferenceChanged(str);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        this.mListeners.finishBroadcast();
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}

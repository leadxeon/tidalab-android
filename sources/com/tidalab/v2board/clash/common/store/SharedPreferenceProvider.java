package com.tidalab.v2board.clash.common.store;

import android.content.SharedPreferences;
import java.util.Set;
/* compiled from: Providers.kt */
/* loaded from: classes.dex */
public final class SharedPreferenceProvider implements StoreProvider {
    public final SharedPreferences preferences;

    public SharedPreferenceProvider(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public boolean getBoolean(String str, boolean z) {
        return this.preferences.getBoolean(str, z);
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public int getInt(String str, int i) {
        return this.preferences.getInt(str, i);
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public long getLong(String str, long j) {
        return this.preferences.getLong(str, j);
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public String getString(String str, String str2) {
        return this.preferences.getString(str, str2);
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public Set<String> getStringSet(String str, Set<String> set) {
        return this.preferences.getStringSet(str, set);
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public void setBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public void setInt(String str, int i) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public void setLong(String str, long j) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public void setString(String str, String str2) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    @Override // com.tidalab.v2board.clash.common.store.StoreProvider
    public void setStringSet(String str, Set<String> set) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putStringSet(str, set);
        edit.apply();
    }
}

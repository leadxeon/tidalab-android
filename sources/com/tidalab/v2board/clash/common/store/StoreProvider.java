package com.tidalab.v2board.clash.common.store;

import java.util.Set;
/* compiled from: StoreProvider.kt */
/* loaded from: classes.dex */
public interface StoreProvider {
    boolean getBoolean(String str, boolean z);

    int getInt(String str, int i);

    long getLong(String str, long j);

    String getString(String str, String str2);

    Set<String> getStringSet(String str, Set<String> set);

    void setBoolean(String str, boolean z);

    void setInt(String str, int i);

    void setLong(String str, long j);

    void setString(String str, String str2);

    void setStringSet(String str, Set<String> set);
}

package com.tidalab.v2board.clash.common.id;
/* compiled from: UndefinedIds.kt */
/* loaded from: classes.dex */
public final class UndefinedIds {
    public static final UndefinedIds INSTANCE = new UndefinedIds();
    public static int current;

    public final synchronized int next() {
        int i;
        i = ((current & 16777215) + 1) | 335544320;
        current = i;
        return i;
    }
}

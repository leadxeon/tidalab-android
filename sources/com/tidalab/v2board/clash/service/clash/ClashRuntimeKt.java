package com.tidalab.v2board.clash.service.clash;

import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: ClashRuntime.kt */
/* loaded from: classes.dex */
public final class ClashRuntimeKt {
    public static final Mutex globalLock = MutexKt.Mutex$default(false, 1);
}

package com.tidalab.v2board.clash.service.data;

import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: PendingDao.kt */
/* loaded from: classes.dex */
public interface PendingDao {
    Object exists(UUID uuid, Continuation<? super Boolean> continuation);

    Object insert(Pending pending, Continuation<? super Unit> continuation);

    Object queryAllUUIDs(Continuation<? super List<UUID>> continuation);

    Object queryByUUID(UUID uuid, Continuation<? super Pending> continuation);

    Object remove(UUID uuid, Continuation<? super Unit> continuation);

    Object update(Pending pending, Continuation<? super Unit> continuation);
}

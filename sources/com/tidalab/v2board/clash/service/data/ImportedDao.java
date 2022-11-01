package com.tidalab.v2board.clash.service.data;

import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: ImportedDao.kt */
/* loaded from: classes.dex */
public interface ImportedDao {
    Object exists(UUID uuid, Continuation<? super Boolean> continuation);

    Object insert(Imported imported, Continuation<? super Long> continuation);

    Object queryAllUUIDs(Continuation<? super List<UUID>> continuation);

    Object queryByUUID(UUID uuid, Continuation<? super Imported> continuation);

    Object remove(UUID uuid, Continuation<? super Unit> continuation);

    Object update(Imported imported, Continuation<? super Unit> continuation);
}

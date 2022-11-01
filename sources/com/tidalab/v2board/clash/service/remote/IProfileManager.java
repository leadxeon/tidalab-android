package com.tidalab.v2board.clash.service.remote;

import com.tidalab.v2board.clash.service.model.Profile;
import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: IProfileManager.kt */
/* loaded from: classes.dex */
public interface IProfileManager {
    Object clone(UUID uuid, Continuation<? super UUID> continuation);

    Object commit(UUID uuid, IFetchObserver iFetchObserver, Continuation<? super Unit> continuation);

    Object create(Profile.Type type, String str, String str2, Continuation<? super UUID> continuation);

    Object delete(UUID uuid, Continuation<? super Unit> continuation);

    Object patch(UUID uuid, String str, String str2, long j, Continuation<? super Unit> continuation);

    Object queryActive(Continuation<? super Profile> continuation);

    Object queryAll(Continuation<? super List<Profile>> continuation);

    Object queryByUUID(UUID uuid, Continuation<? super Profile> continuation);

    Object release(UUID uuid, Continuation<? super Unit> continuation);

    Object setActive(Profile profile, Continuation<? super Unit> continuation);

    Object update(UUID uuid, Continuation<? super Unit> continuation);
}

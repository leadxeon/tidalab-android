package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.data.ImportedDao;
import com.tidalab.v2board.clash.service.data.PendingDao;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileManager$queryAll$uuids$1", f = "ProfileManager.kt", l = {137, 137}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileManager$queryAll$uuids$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends UUID>>, Object> {
    public Object L$0;
    public int label;

    public ProfileManager$queryAll$uuids$1(Continuation<? super ProfileManager$queryAll$uuids$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileManager$queryAll$uuids$1(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends UUID>> continuation) {
        return new ProfileManager$queryAll$uuids$1(continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Collection collection;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ImportedDao ImportedDao = InputKt.ImportedDao();
            this.label = 1;
            obj = ImportedDao.queryAllUUIDs(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            collection = (Collection) this.L$0;
            InputKt.throwOnFailure(obj);
            return ArraysKt___ArraysKt.toList(ArraysKt___ArraysKt.toMutableSet(ArraysKt___ArraysKt.plus(collection, (Iterable) obj)));
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Collection collection2 = (Collection) obj;
        PendingDao PendingDao = InputKt.PendingDao();
        this.L$0 = collection2;
        this.label = 2;
        Object queryAllUUIDs = PendingDao.queryAllUUIDs(this);
        if (queryAllUUIDs == coroutineSingletons) {
            return coroutineSingletons;
        }
        collection = collection2;
        obj = queryAllUUIDs;
        return ArraysKt___ArraysKt.toList(ArraysKt___ArraysKt.toMutableSet(ArraysKt___ArraysKt.plus(collection, (Iterable) obj)));
    }
}

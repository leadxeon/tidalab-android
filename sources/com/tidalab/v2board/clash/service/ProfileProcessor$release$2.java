package com.tidalab.v2board.clash.service;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.data.PendingDao;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
/* compiled from: ProfileProcessor.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileProcessor$release$2", f = "ProfileProcessor.kt", l = {198, 157}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileProcessor$release$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ UUID $uuid;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileProcessor$release$2(UUID uuid, Context context, Continuation<? super ProfileProcessor$release$2> continuation) {
        super(2, continuation);
        this.$uuid = uuid;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileProcessor$release$2(this.$uuid, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return new ProfileProcessor$release$2(this.$uuid, this.$context, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Mutex mutex;
        UUID uuid;
        Context context;
        Mutex mutex2;
        Context context2;
        UUID uuid2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                mutex = ProfileProcessor.profileLock;
                UUID uuid3 = this.$uuid;
                Context context3 = this.$context;
                this.L$0 = mutex;
                this.L$1 = uuid3;
                this.L$2 = context3;
                this.label = 1;
                if (mutex.lock(null, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                uuid = uuid3;
                context = context3;
            } else if (i == 1) {
                context = (Context) this.L$2;
                uuid = (UUID) this.L$1;
                mutex = (Mutex) this.L$0;
                InputKt.throwOnFailure(obj);
            } else if (i == 2) {
                context2 = (Context) this.L$2;
                uuid2 = (UUID) this.L$1;
                mutex2 = (Mutex) this.L$0;
                try {
                    InputKt.throwOnFailure(obj);
                    Boolean valueOf = Boolean.valueOf(FilesKt__UtilsKt.deleteRecursively(FilesKt__UtilsKt.resolve(InputKt.getPendingDir(context2), uuid2.toString())));
                    mutex2.unlock(null);
                    return valueOf;
                } catch (Throwable th) {
                    th = th;
                    mutex2.unlock(null);
                    throw th;
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            PendingDao PendingDao = InputKt.PendingDao();
            this.L$0 = mutex;
            this.L$1 = uuid;
            this.L$2 = context;
            this.label = 2;
            if (PendingDao.remove(uuid, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            mutex2 = mutex;
            context2 = context;
            uuid2 = uuid;
            Boolean valueOf2 = Boolean.valueOf(FilesKt__UtilsKt.deleteRecursively(FilesKt__UtilsKt.resolve(InputKt.getPendingDir(context2), uuid2.toString())));
            mutex2.unlock(null);
            return valueOf2;
        } catch (Throwable th2) {
            th = th2;
            mutex2 = mutex;
            mutex2.unlock(null);
            throw th;
        }
    }
}

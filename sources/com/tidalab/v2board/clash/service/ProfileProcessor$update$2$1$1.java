package com.tidalab.v2board.clash.service;

import android.util.Log;
import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.service.remote.IFetchObserver;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
/* compiled from: ProfileProcessor.kt */
/* loaded from: classes.dex */
public final class ProfileProcessor$update$2$1$1 extends Lambda implements Function1<FetchStatus, Unit> {
    public final /* synthetic */ Ref$ObjectRef<IFetchObserver> $cb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileProcessor$update$2$1$1(Ref$ObjectRef<IFetchObserver> ref$ObjectRef) {
        super(1);
        this.$cb = ref$ObjectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(FetchStatus fetchStatus) {
        FetchStatus fetchStatus2 = fetchStatus;
        try {
            IFetchObserver iFetchObserver = this.$cb.element;
            if (iFetchObserver != null) {
                iFetchObserver.updateStatus(fetchStatus2);
            }
        } catch (Exception e) {
            this.$cb.element = null;
            Log.w("ClashForAndroid", Intrinsics.stringPlus("Report fetch status: ", e), e);
        }
        return Unit.INSTANCE;
    }
}

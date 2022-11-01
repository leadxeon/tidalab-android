package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$3$2$all$1", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$3$2$all$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends String>>, Object> {
    public final /* synthetic */ AccessControlDesign $design;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$3$2$all$1(AccessControlDesign accessControlDesign, Continuation<? super AccessControlActivity$main$3$2$all$1> continuation) {
        super(2, continuation);
        this.$design = accessControlDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlActivity$main$3$2$all$1(this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends String>> continuation) {
        Continuation<? super List<? extends String>> continuation2 = continuation;
        AccessControlDesign accessControlDesign = this.$design;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        List<AppInfo> list = accessControlDesign.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        return arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        List<AppInfo> list = this.$design.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        return arrayList;
    }
}

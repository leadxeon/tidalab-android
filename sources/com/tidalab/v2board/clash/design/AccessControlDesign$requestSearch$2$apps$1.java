package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.AccessControlDesign$requestSearch$2$apps$1", f = "AccessControlDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlDesign$requestSearch$2$apps$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AppInfo>>, Object> {
    public final /* synthetic */ String $keyword;
    public final /* synthetic */ AccessControlDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlDesign$requestSearch$2$apps$1(AccessControlDesign accessControlDesign, String str, Continuation<? super AccessControlDesign$requestSearch$2$apps$1> continuation) {
        super(2, continuation);
        this.this$0 = accessControlDesign;
        this.$keyword = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlDesign$requestSearch$2$apps$1(this.this$0, this.$keyword, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AppInfo>> continuation) {
        Continuation<? super List<? extends AppInfo>> continuation2 = continuation;
        AccessControlDesign accessControlDesign = this.this$0;
        String str = this.$keyword;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        List<AppInfo> list = accessControlDesign.adapter.apps;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            AppInfo appInfo = (AppInfo) obj;
            boolean z = true;
            if (!StringsKt__IndentKt.contains(appInfo.label, str, true) && !StringsKt__IndentKt.contains(appInfo.packageName, str, true)) {
                z = false;
            }
            if (Boolean.valueOf(z).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        List<AppInfo> list = this.this$0.adapter.apps;
        String str = this.$keyword;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            AppInfo appInfo = (AppInfo) obj2;
            boolean z = true;
            if (!StringsKt__IndentKt.contains(appInfo.label, str, true) && !StringsKt__IndentKt.contains(appInfo.packageName, str, true)) {
                z = false;
            }
            if (Boolean.valueOf(z).booleanValue()) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }
}

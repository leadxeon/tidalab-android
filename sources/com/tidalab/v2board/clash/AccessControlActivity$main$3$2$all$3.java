package com.tidalab.v2board.clash;

import android.content.ClipData;
import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$3$2$all$3", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$3$2$all$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Set<? extends String>>, Object> {
    public final /* synthetic */ ClipData $data;
    public final /* synthetic */ AccessControlDesign $design;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$3$2$all$3(ClipData clipData, AccessControlDesign accessControlDesign, Continuation<? super AccessControlActivity$main$3$2$all$3> continuation) {
        super(2, continuation);
        this.$data = clipData;
        this.$design = accessControlDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlActivity$main$3$2$all$3(this.$data, this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Set<? extends String>> continuation) {
        Continuation<? super Set<? extends String>> continuation2 = continuation;
        ClipData clipData = this.$data;
        AccessControlDesign accessControlDesign = this.$design;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        Set set = ArraysKt___ArraysKt.toSet(StringsKt__IndentKt.split$default(clipData.getItemAt(0).getText(), new String[]{"\n"}, false, 0, 6));
        List<AppInfo> list = accessControlDesign.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        Set mutableSet = ArraysKt___ArraysKt.toMutableSet(arrayList);
        TypeIntrinsics.asMutableCollection(mutableSet).retainAll(InputKt.convertToSetForSetOperationWith(set, mutableSet));
        return mutableSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        Set set = ArraysKt___ArraysKt.toSet(StringsKt__IndentKt.split$default(this.$data.getItemAt(0).getText(), new String[]{"\n"}, false, 0, 6));
        List<AppInfo> list = this.$design.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        Set mutableSet = ArraysKt___ArraysKt.toMutableSet(arrayList);
        TypeIntrinsics.asMutableCollection(mutableSet).retainAll(InputKt.convertToSetForSetOperationWith(set, mutableSet));
        return mutableSet;
    }
}

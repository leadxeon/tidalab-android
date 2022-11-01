package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$3$2$all$2", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$3$2$all$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Set<? extends String>>, Object> {
    public final /* synthetic */ AccessControlDesign $design;
    public final /* synthetic */ Set<String> $selected;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$3$2$all$2(AccessControlDesign accessControlDesign, Set<String> set, Continuation<? super AccessControlActivity$main$3$2$all$2> continuation) {
        super(2, continuation);
        this.$design = accessControlDesign;
        this.$selected = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlActivity$main$3$2$all$2(this.$design, this.$selected, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Set<? extends String>> continuation) {
        LinkedHashSet linkedHashSet;
        Continuation<? super Set<? extends String>> continuation2 = continuation;
        AccessControlDesign accessControlDesign = this.$design;
        Set<String> set = this.$selected;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        List<AppInfo> list = accessControlDesign.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        Set set2 = ArraysKt___ArraysKt.toSet(arrayList);
        Collection<?> convertToSetForSetOperationWith = InputKt.convertToSetForSetOperationWith(set, set2);
        if (convertToSetForSetOperationWith.isEmpty()) {
            return ArraysKt___ArraysKt.toSet(set2);
        }
        if (convertToSetForSetOperationWith instanceof Set) {
            linkedHashSet = new LinkedHashSet();
            for (Object obj : set2) {
                if (!convertToSetForSetOperationWith.contains(obj)) {
                    linkedHashSet.add(obj);
                }
            }
        } else {
            linkedHashSet = new LinkedHashSet(set2);
            linkedHashSet.removeAll(convertToSetForSetOperationWith);
        }
        return linkedHashSet;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LinkedHashSet linkedHashSet;
        InputKt.throwOnFailure(obj);
        List<AppInfo> list = this.$design.adapter.apps;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(list, 10));
        for (AppInfo appInfo : list) {
            arrayList.add(appInfo.packageName);
        }
        Set set = ArraysKt___ArraysKt.toSet(arrayList);
        Collection<?> convertToSetForSetOperationWith = InputKt.convertToSetForSetOperationWith(this.$selected, set);
        if (convertToSetForSetOperationWith.isEmpty()) {
            return ArraysKt___ArraysKt.toSet(set);
        }
        if (convertToSetForSetOperationWith instanceof Set) {
            linkedHashSet = new LinkedHashSet();
            for (Object obj2 : set) {
                if (!convertToSetForSetOperationWith.contains(obj2)) {
                    linkedHashSet.add(obj2);
                }
            }
        } else {
            linkedHashSet = new LinkedHashSet(set);
            linkedHashSet.removeAll(convertToSetForSetOperationWith);
        }
        return linkedHashSet;
    }
}

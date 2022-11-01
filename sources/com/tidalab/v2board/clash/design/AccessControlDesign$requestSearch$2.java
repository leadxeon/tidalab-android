package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.model.AppInfo;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.AccessControlDesign$requestSearch$2", f = "AccessControlDesign.kt", l = {113, 120, 128, 130}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlDesign$requestSearch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public int label;
    public final /* synthetic */ AccessControlDesign this$0;

    /* compiled from: AccessControlDesign.kt */
    /* renamed from: com.tidalab.v2board.clash.design.AccessControlDesign$requestSearch$2$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass6 extends PropertyReference1Impl {
        public static final AnonymousClass6 INSTANCE = new AnonymousClass6();

        public AnonymousClass6() {
            super(AppInfo.class, "packageName", "getPackageName()Ljava/lang/String;", 0);
        }

        @Override // kotlin.reflect.KProperty1
        public Object get(Object obj) {
            return ((AppInfo) obj).packageName;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlDesign$requestSearch$2(AccessControlDesign accessControlDesign, Continuation<? super AccessControlDesign$requestSearch$2> continuation) {
        super(2, continuation);
        this.this$0 = accessControlDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AccessControlDesign$requestSearch$2 accessControlDesign$requestSearch$2 = new AccessControlDesign$requestSearch$2(this.this$0, continuation);
        accessControlDesign$requestSearch$2.L$0 = obj;
        return accessControlDesign$requestSearch$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        AccessControlDesign$requestSearch$2 accessControlDesign$requestSearch$2 = new AccessControlDesign$requestSearch$2(this.this$0, continuation);
        accessControlDesign$requestSearch$2.L$0 = coroutineScope;
        return accessControlDesign$requestSearch$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x017c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0196 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x019c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0197 -> B:9:0x002c). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.AccessControlDesign$requestSearch$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

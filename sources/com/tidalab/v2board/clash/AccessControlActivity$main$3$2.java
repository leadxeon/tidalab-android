package com.tidalab.v2board.clash;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$3$2", f = "AccessControlActivity.kt", l = {47, 47, 50, 57, 62, 65, 72, 79, 89, 94}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlActivity$main$3$2 extends SuspendLambda implements Function2<AccessControlDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ AccessControlDesign $design;
    public final /* synthetic */ Set<String> $selected;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ AccessControlActivity this$0;

    /* compiled from: AccessControlActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.AccessControlActivity$main$3$2$1", f = "AccessControlActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.AccessControlActivity$main$3$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ClipboardManager $clipboard;
        public final /* synthetic */ Set<String> $selected;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Set<String> set, ClipboardManager clipboardManager, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$selected = set;
            this.$clipboard = clipboardManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$selected, this.$clipboard, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            Set<String> set = this.$selected;
            ClipboardManager clipboardManager = this.$clipboard;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            ClipData newPlainText = ClipData.newPlainText("packages", ArraysKt___ArraysKt.joinToString$default(set, "\n", null, null, 0, null, null, 62));
            if (clipboardManager == null) {
                return null;
            }
            clipboardManager.setPrimaryClip(newPlainText);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            ClipData newPlainText = ClipData.newPlainText("packages", ArraysKt___ArraysKt.joinToString$default(this.$selected, "\n", null, null, 0, null, null, 62));
            ClipboardManager clipboardManager = this.$clipboard;
            if (clipboardManager == null) {
                return null;
            }
            clipboardManager.setPrimaryClip(newPlainText);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlActivity$main$3$2(AccessControlDesign accessControlDesign, AccessControlActivity accessControlActivity, Set<String> set, Continuation<? super AccessControlActivity$main$3$2> continuation) {
        super(2, continuation);
        this.$design = accessControlDesign;
        this.this$0 = accessControlActivity;
        this.$selected = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AccessControlActivity$main$3$2 accessControlActivity$main$3$2 = new AccessControlActivity$main$3$2(this.$design, this.this$0, this.$selected, continuation);
        accessControlActivity$main$3$2.L$0 = obj;
        return accessControlActivity$main$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(AccessControlDesign.Request request, Continuation<? super Unit> continuation) {
        AccessControlActivity$main$3$2 accessControlActivity$main$3$2 = new AccessControlActivity$main$3$2(this.$design, this.this$0, this.$selected, continuation);
        accessControlActivity$main$3$2.L$0 = request;
        return accessControlActivity$main$3$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015b A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.AccessControlActivity$main$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

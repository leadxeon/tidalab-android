package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.remote.FilesClient;
import java.util.Stack;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: FilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.FilesActivity$main$2$2", f = "FilesActivity.kt", l = {67, 76, 79, 81, 91, 102, 109, 111, 113, 118, 124, 129, 132}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesActivity$main$2$2 extends SuspendLambda implements Function2<FilesDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ FilesClient $client;
    public final /* synthetic */ FilesDesign $design;
    public final /* synthetic */ String $root;
    public final /* synthetic */ Stack<String> $stack;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ FilesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesActivity$main$2$2(Stack<String> stack, FilesActivity filesActivity, FilesClient filesClient, FilesDesign filesDesign, String str, Continuation<? super FilesActivity$main$2$2> continuation) {
        super(2, continuation);
        this.$stack = stack;
        this.this$0 = filesActivity;
        this.$client = filesClient;
        this.$design = filesDesign;
        this.$root = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FilesActivity$main$2$2 filesActivity$main$2$2 = new FilesActivity$main$2$2(this.$stack, this.this$0, this.$client, this.$design, this.$root, continuation);
        filesActivity$main$2$2.L$0 = obj;
        return filesActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(FilesDesign.Request request, Continuation<? super Unit> continuation) {
        return ((FilesActivity$main$2$2) create(request, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0212 A[Catch: Exception -> 0x0051, TryCatch #0 {Exception -> 0x0051, blocks: (B:9:0x0022, B:11:0x002b, B:14:0x0035, B:16:0x003e, B:18:0x0047, B:19:0x004c, B:22:0x005b, B:24:0x0063, B:26:0x006b, B:27:0x0072, B:28:0x0079, B:30:0x007d, B:31:0x008a, B:33:0x0090, B:36:0x00bc, B:38:0x00c0, B:41:0x00de, B:43:0x00e2, B:47:0x00f9, B:50:0x011b, B:52:0x011f, B:54:0x0125, B:59:0x0131, B:63:0x0147, B:65:0x014f, B:68:0x0153, B:71:0x0167, B:73:0x016b, B:75:0x0172, B:78:0x018c, B:82:0x019a, B:85:0x01c3, B:88:0x01c8, B:91:0x01e7, B:94:0x01ec, B:96:0x01f0, B:100:0x020e, B:102:0x0212, B:105:0x0231), top: B:116:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x025e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x011a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014f A[Catch: Exception -> 0x0051, TryCatch #0 {Exception -> 0x0051, blocks: (B:9:0x0022, B:11:0x002b, B:14:0x0035, B:16:0x003e, B:18:0x0047, B:19:0x004c, B:22:0x005b, B:24:0x0063, B:26:0x006b, B:27:0x0072, B:28:0x0079, B:30:0x007d, B:31:0x008a, B:33:0x0090, B:36:0x00bc, B:38:0x00c0, B:41:0x00de, B:43:0x00e2, B:47:0x00f9, B:50:0x011b, B:52:0x011f, B:54:0x0125, B:59:0x0131, B:63:0x0147, B:65:0x014f, B:68:0x0153, B:71:0x0167, B:73:0x016b, B:75:0x0172, B:78:0x018c, B:82:0x019a, B:85:0x01c3, B:88:0x01c8, B:91:0x01e7, B:94:0x01ec, B:96:0x01f0, B:100:0x020e, B:102:0x0212, B:105:0x0231), top: B:116:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0166 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016b A[Catch: Exception -> 0x0051, TryCatch #0 {Exception -> 0x0051, blocks: (B:9:0x0022, B:11:0x002b, B:14:0x0035, B:16:0x003e, B:18:0x0047, B:19:0x004c, B:22:0x005b, B:24:0x0063, B:26:0x006b, B:27:0x0072, B:28:0x0079, B:30:0x007d, B:31:0x008a, B:33:0x0090, B:36:0x00bc, B:38:0x00c0, B:41:0x00de, B:43:0x00e2, B:47:0x00f9, B:50:0x011b, B:52:0x011f, B:54:0x0125, B:59:0x0131, B:63:0x0147, B:65:0x014f, B:68:0x0153, B:71:0x0167, B:73:0x016b, B:75:0x0172, B:78:0x018c, B:82:0x019a, B:85:0x01c3, B:88:0x01c8, B:91:0x01e7, B:94:0x01ec, B:96:0x01f0, B:100:0x020e, B:102:0x0212, B:105:0x0231), top: B:116:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01c3 A[Catch: Exception -> 0x0051, TryCatch #0 {Exception -> 0x0051, blocks: (B:9:0x0022, B:11:0x002b, B:14:0x0035, B:16:0x003e, B:18:0x0047, B:19:0x004c, B:22:0x005b, B:24:0x0063, B:26:0x006b, B:27:0x0072, B:28:0x0079, B:30:0x007d, B:31:0x008a, B:33:0x0090, B:36:0x00bc, B:38:0x00c0, B:41:0x00de, B:43:0x00e2, B:47:0x00f9, B:50:0x011b, B:52:0x011f, B:54:0x0125, B:59:0x0131, B:63:0x0147, B:65:0x014f, B:68:0x0153, B:71:0x0167, B:73:0x016b, B:75:0x0172, B:78:0x018c, B:82:0x019a, B:85:0x01c3, B:88:0x01c8, B:91:0x01e7, B:94:0x01ec, B:96:0x01f0, B:100:0x020e, B:102:0x0212, B:105:0x0231), top: B:116:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c7 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instructions count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.FilesActivity$main$2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

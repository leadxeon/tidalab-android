package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.adapter.FileAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.File;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.FilesDesign$swapFiles$2", f = "FilesDesign.kt", l = {43}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesDesign$swapFiles$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ boolean $currentInBaseDir;
    public final /* synthetic */ List<File> $files;
    public int label;
    public final /* synthetic */ FilesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesDesign$swapFiles$2(FilesDesign filesDesign, List<File> list, boolean z, Continuation<? super FilesDesign$swapFiles$2> continuation) {
        super(2, continuation);
        this.this$0 = filesDesign;
        this.$files = list;
        this.$currentInBaseDir = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesDesign$swapFiles$2(this.this$0, this.$files, this.$currentInBaseDir, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new FilesDesign$swapFiles$2(this.this$0, this.$files, this.$currentInBaseDir, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            FileAdapter fileAdapter = this.this$0.adapter;
            MutablePropertyReference0Impl mutablePropertyReference0Impl = new MutablePropertyReference0Impl(this.this$0.adapter) { // from class: com.tidalab.v2board.clash.design.FilesDesign$swapFiles$2.1
                @Override // kotlin.reflect.KMutableProperty0
                public Object get() {
                    return ((FileAdapter) this.receiver).files;
                }

                @Override // kotlin.reflect.KMutableProperty0
                public void set(Object obj2) {
                    ((FileAdapter) this.receiver).files = (List) obj2;
                }
            };
            List<File> list = this.$files;
            this.label = 1;
            if (InputKt.swapDataSet(fileAdapter, mutablePropertyReference0Impl, list, true, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.binding.setCurrentInBaseDir(this.$currentInBaseDir);
        return Unit.INSTANCE;
    }
}

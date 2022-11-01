package com.tidalab.v2board.clash.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.collections.CollectionsKt__IteratorsJVMKt$iterator$1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationKt$verifyApk$apkAbi$2 extends Lambda implements Function1<String, Sequence<? extends ZipEntry>> {
    public static final ApplicationKt$verifyApk$apkAbi$2 INSTANCE = new ApplicationKt$verifyApk$apkAbi$2();

    public ApplicationKt$verifyApk$apkAbi$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Sequence<? extends ZipEntry> invoke(String str) {
        return InputKt.asSequence(new CollectionsKt__IteratorsJVMKt$iterator$1(new ZipFile(str).entries()));
    }
}

package com.tidalab.v2board.clash.util;

import java.io.File;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationKt$verifyApk$apkAbi$1 extends Lambda implements Function1<String, Boolean> {
    public static final ApplicationKt$verifyApk$apkAbi$1 INSTANCE = new ApplicationKt$verifyApk$apkAbi$1();

    public ApplicationKt$verifyApk$apkAbi$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Boolean invoke(String str) {
        return Boolean.valueOf(new File(str).exists());
    }
}

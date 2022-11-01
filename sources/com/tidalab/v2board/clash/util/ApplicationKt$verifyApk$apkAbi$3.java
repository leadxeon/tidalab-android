package com.tidalab.v2board.clash.util;

import java.util.zip.ZipEntry;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationKt$verifyApk$apkAbi$3 extends Lambda implements Function1<ZipEntry, MatchResult> {
    public final /* synthetic */ Regex $regexNativeLibrary;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApplicationKt$verifyApk$apkAbi$3(Regex regex) {
        super(1);
        this.$regexNativeLibrary = regex;
    }

    @Override // kotlin.jvm.functions.Function1
    public MatchResult invoke(ZipEntry zipEntry) {
        return this.$regexNativeLibrary.matchEntire(zipEntry.getName());
    }
}

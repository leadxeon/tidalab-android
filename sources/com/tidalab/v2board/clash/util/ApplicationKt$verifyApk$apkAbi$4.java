package com.tidalab.v2board.clash.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
/* compiled from: Application.kt */
/* loaded from: classes.dex */
public final class ApplicationKt$verifyApk$apkAbi$4 extends Lambda implements Function1<MatchResult, String> {
    public static final ApplicationKt$verifyApk$apkAbi$4 INSTANCE = new ApplicationKt$verifyApk$apkAbi$4();

    public ApplicationKt$verifyApk$apkAbi$4() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public String invoke(MatchResult matchResult) {
        MatchGroup matchGroup = matchResult.getGroups().get(1);
        if (matchGroup == null) {
            return null;
        }
        return matchGroup.value;
    }
}

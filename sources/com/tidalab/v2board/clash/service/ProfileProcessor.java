package com.tidalab.v2board.clash.service;

import android.net.Uri;
import com.tidalab.v2board.clash.service.data.Pending;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: ProfileProcessor.kt */
/* loaded from: classes.dex */
public final class ProfileProcessor {
    public static final ProfileProcessor INSTANCE = new ProfileProcessor();
    public static final Mutex profileLock = MutexKt.Mutex$default(false, 1);
    public static final Mutex processLock = MutexKt.Mutex$default(false, 1);

    public static final void access$enforceFieldValid(ProfileProcessor profileProcessor, Pending pending) {
        String scheme;
        Uri parse = Uri.parse(pending.source);
        String lowerCase = (parse == null || (scheme = parse.getScheme()) == null) ? null : scheme.toLowerCase(Locale.getDefault());
        if (!StringsKt__IndentKt.isBlank(pending.name)) {
            boolean z = true;
            if (!(pending.source.length() == 0) || pending.type == Profile.Type.File) {
                if (pending.source.length() <= 0) {
                    z = false;
                }
                if (!z || Intrinsics.areEqual(lowerCase, "https") || Intrinsics.areEqual(lowerCase, "http") || Intrinsics.areEqual(lowerCase, "content")) {
                    long j = pending.interval;
                    if (j != 0 && TimeUnit.MILLISECONDS.toMinutes(j) < 15) {
                        throw new IllegalArgumentException("Invalid interval");
                    }
                    return;
                }
                throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported url ", pending.source));
            }
            throw new IllegalArgumentException("Invalid url");
        }
        throw new IllegalArgumentException("Empty name");
    }
}

package com.tidalab.v2board.clash.core.bridge;

import androidx.annotation.Keep;
/* compiled from: TunInterface.kt */
@Keep
/* loaded from: classes.dex */
public interface TunInterface {
    void markSocket(int i);

    int querySocketUid(int i, String str, String str2);
}

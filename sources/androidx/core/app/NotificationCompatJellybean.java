package androidx.core.app;

import android.os.Bundle;
/* loaded from: classes.dex */
public class NotificationCompatJellybean {
    public static final Object sExtrasLock = new Object();

    public static Bundle[] toBundleArray(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        if (remoteInputArr.length <= 0) {
            return bundleArr;
        }
        RemoteInput remoteInput = remoteInputArr[0];
        new Bundle();
        throw null;
    }
}

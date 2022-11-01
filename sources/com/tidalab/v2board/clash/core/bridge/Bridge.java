package com.tidalab.v2board.clash.core.bridge;

import android.app.Application;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Keep;
import com.tidalab.v2board.clash.common.Global;
import java.io.File;
import kotlin.Unit;
import kotlin.io.FilesKt__UtilsKt;
import kotlinx.coroutines.CompletableDeferred;
/* compiled from: Bridge.kt */
@Keep
/* loaded from: classes.dex */
public final class Bridge {
    public static final Bridge INSTANCE;

    static {
        Bridge bridge = new Bridge();
        INSTANCE = bridge;
        System.loadLibrary("bridge");
        Application application = Global.INSTANCE.getApplication();
        ParcelFileDescriptor.open(new File(application.getPackageCodePath()), 268435456).detachFd();
        File resolve = FilesKt__UtilsKt.resolve(application.getFilesDir(), "clash");
        resolve.mkdirs();
        bridge.nativeInit(resolve.getAbsolutePath(), application.getPackageManager().getPackageInfo(application.getPackageName(), 0).versionName, Build.VERSION.SDK_INT);
    }

    private Bridge() {
    }

    private final native void nativeInit(String str, String str2, int i);

    public final native void nativeClearOverride(int i);

    public final native void nativeFetchAndValid(FetchCallback fetchCallback, String str, String str2, boolean z);

    public final native void nativeForceGc();

    public final native void nativeHealthCheck(CompletableDeferred<Unit> completableDeferred, String str);

    public final native void nativeHealthCheckAll();

    public final native void nativeInstallSideloadGeoip(byte[] bArr);

    public final native void nativeLoad(CompletableDeferred<Unit> completableDeferred, String str);

    public final native void nativeNotifyDnsChanged(String str);

    public final native void nativeNotifyInstalledAppChanged(String str);

    public final native boolean nativePatchSelector(String str, String str2);

    public final native String nativeQueryConfiguration();

    public final native String nativeQueryGroup(String str, String str2);

    public final native String nativeQueryGroupNames(boolean z);

    public final native String nativeQueryProviders();

    public final native long nativeQueryTrafficNow();

    public final native long nativeQueryTrafficTotal();

    public final native String nativeQueryTunnelState();

    public final native String nativeReadOverride(int i);

    public final native void nativeReset();

    public final native String nativeStartHttp(String str);

    public final native void nativeStartTun(int i, int i2, String str, String str2, TunInterface tunInterface);

    public final native void nativeStopHttp();

    public final native void nativeStopTun();

    public final native void nativeSubscribeLogcat(LogcatInterface logcatInterface);

    public final native void nativeSuspend(boolean z);

    public final native void nativeUpdateProvider(CompletableDeferred<Unit> completableDeferred, String str, String str2);

    public final native void nativeWriteOverride(int i, String str);
}

package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
@DoNotOptimize
@TargetApi(21)
/* loaded from: classes.dex */
public final class SysUtil$LollipopSysdeps {
    private SysUtil$LollipopSysdeps() {
    }

    @DoNotOptimize
    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
        int i;
        try {
            Os.posix_fallocate(fileDescriptor, 0L, j);
        } catch (ErrnoException e) {
            if (e.errno != OsConstants.EOPNOTSUPP && (i = e.errno) != OsConstants.ENOSYS && i != OsConstants.EINVAL) {
                throw new IOException(e.toString(), e);
            }
        }
    }

    @DoNotOptimize
    public static String[] getSupportedAbis() {
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        try {
            if (Os.readlink("/proc/self/exe").contains("64")) {
                treeSet.add("arm64-v8a");
                treeSet.add("x86_64");
            } else {
                treeSet.add("armeabi-v7a");
                treeSet.add("x86");
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (ErrnoException e) {
            Log.e("SysUtil", String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()));
            return Build.SUPPORTED_ABIS;
        }
    }
}

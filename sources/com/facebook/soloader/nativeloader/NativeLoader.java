package com.facebook.soloader.nativeloader;

import com.facebook.soloader.NativeLoaderToSoLoaderDelegate;
import com.facebook.soloader.SoLoader;
/* loaded from: classes.dex */
public class NativeLoader {
    public static NativeLoaderToSoLoaderDelegate sDelegate;

    public static boolean loadLibrary(String str) {
        synchronized (NativeLoader.class) {
            if (sDelegate == null) {
                throw new IllegalStateException("NativeLoader has not been initialized.  To use standard native library loading, call NativeLoader.init(new SystemDelegate()).");
            }
        }
        return SoLoader.loadLibrary(str);
    }
}

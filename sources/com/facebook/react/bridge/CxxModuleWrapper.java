package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;
import java.io.IOException;
@DoNotStrip
/* loaded from: classes.dex */
public class CxxModuleWrapper extends CxxModuleWrapperBase {
    public CxxModuleWrapper(HybridData hybridData) {
        super(hybridData);
    }

    public static CxxModuleWrapper makeDso(String str, String str2) {
        SoLoader.loadLibrary(str);
        SoLoader.assertInitialized();
        try {
            return makeDsoNative(SoLoader.unpackLibraryBySoName(System.mapLibraryName(str)).getAbsolutePath(), str2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static native CxxModuleWrapper makeDsoNative(String str, String str2);
}

package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
/* loaded from: classes.dex */
public abstract class SoSource {
    public abstract int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException;

    public void prepare(int i) throws IOException {
    }

    public String toString() {
        return getClass().getName();
    }

    public abstract File unpackLibrary(String str) throws IOException;
}

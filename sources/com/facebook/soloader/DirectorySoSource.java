package com.facebook.soloader;

import android.os.StrictMode;
import android.os.Trace;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.soloader.SoLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class DirectorySoSource extends SoSource {
    public final int flags;
    public final File soDirectory;

    public DirectorySoSource(File file, int i) {
        this.soDirectory = file;
        this.flags = i;
    }

    @Override // com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
    }

    public int loadLibraryFrom(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            Log.d("SoLoader", str + " not found on " + file.getCanonicalPath());
            return 0;
        }
        Log.d("SoLoader", str + " found on " + file.getCanonicalPath());
        if ((i & 1) == 0 || (this.flags & 2) == 0) {
            if ((this.flags & 1) != 0) {
                boolean z = SoLoader.SYSTRACE_LIBRARY_LOADING;
                if (z) {
                    Api18TraceUtils.beginTraceSection("SoLoader.getElfDependencies[", file2.getName(), "]");
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    String[] extract_DT_NEEDED = R$style.extract_DT_NEEDED(fileInputStream.getChannel());
                    fileInputStream.close();
                    if (z) {
                        Trace.endSection();
                    }
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Loading lib dependencies: ");
                    outline13.append(Arrays.toString(extract_DT_NEEDED));
                    Log.d("SoLoader", outline13.toString());
                    for (String str2 : extract_DT_NEEDED) {
                        if (!str2.startsWith("/")) {
                            SoLoader.loadLibraryBySoName(str2, null, null, (i | 1) & (-3), threadPolicy);
                        }
                    }
                } catch (Throwable th) {
                    if (SoLoader.SYSTRACE_LIBRARY_LOADING) {
                        Trace.endSection();
                    }
                    throw th;
                }
            } else {
                Log.d("SoLoader", "Not resolving dependencies for " + str);
            }
            try {
                ((SoLoader.AnonymousClass1) SoLoader.sSoFileLoader).load(file2.getAbsolutePath(), i);
                return 1;
            } catch (UnsatisfiedLinkError e) {
                if (e.getMessage().contains("bad ELF magic")) {
                    Log.d("SoLoader", "Corrupted lib file detected");
                    return 3;
                }
                throw e;
            }
        } else {
            Log.d("SoLoader", str + " loaded implicitly");
            return 2;
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String toString() {
        String str;
        try {
            str = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            str = this.soDirectory.getName();
        }
        return getClass().getName() + "[root = " + str + " flags = " + this.flags + ']';
    }

    @Override // com.facebook.soloader.SoSource
    public File unpackLibrary(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}

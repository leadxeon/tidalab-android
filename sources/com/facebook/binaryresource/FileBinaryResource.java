package com.facebook.binaryresource;

import java.io.File;
/* loaded from: classes.dex */
public class FileBinaryResource {
    public final File mFile;

    public FileBinaryResource(File file) {
        this.mFile = file;
    }

    public static FileBinaryResource createOrNull(File file) {
        return new FileBinaryResource(file);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FileBinaryResource)) {
            return false;
        }
        return this.mFile.equals(((FileBinaryResource) obj).mFile);
    }

    public int hashCode() {
        return this.mFile.hashCode();
    }

    public long size() {
        return this.mFile.length();
    }
}

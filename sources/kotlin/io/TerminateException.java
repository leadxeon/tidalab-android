package kotlin.io;

import java.io.File;
/* compiled from: Utils.kt */
/* loaded from: classes.dex */
public final class TerminateException extends FileSystemException {
    public TerminateException(File file) {
        super(file, null, null);
    }
}

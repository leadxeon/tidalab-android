package kotlin.io;

import java.io.File;
/* compiled from: Exceptions.kt */
/* loaded from: classes.dex */
public final class FileAlreadyExistsException extends FileSystemException {
    public FileAlreadyExistsException(File file, File file2, String str) {
        super(file, file2, str);
    }
}

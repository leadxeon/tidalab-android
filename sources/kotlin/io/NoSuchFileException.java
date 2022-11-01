package kotlin.io;

import java.io.File;
/* compiled from: Exceptions.kt */
/* loaded from: classes.dex */
public final class NoSuchFileException extends FileSystemException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoSuchFileException(File file, File file2, String str, int i) {
        super(file, null, (i & 4) != 0 ? null : str);
        int i2 = i & 2;
    }
}

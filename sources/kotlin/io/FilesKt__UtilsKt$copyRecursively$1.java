package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* compiled from: Utils.kt */
/* loaded from: classes.dex */
public final class FilesKt__UtilsKt$copyRecursively$1 extends Lambda implements Function2 {
    public static final FilesKt__UtilsKt$copyRecursively$1 INSTANCE = new FilesKt__UtilsKt$copyRecursively$1();

    public FilesKt__UtilsKt$copyRecursively$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        File file = (File) obj;
        throw ((IOException) obj2);
    }
}

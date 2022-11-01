package kotlin.io;

import java.io.File;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* compiled from: Utils.kt */
/* loaded from: classes.dex */
public final class FilesKt__UtilsKt$copyRecursively$2 extends Lambda implements Function2<File, IOException, Unit> {
    public final /* synthetic */ Function2 $onError;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesKt__UtilsKt$copyRecursively$2(Function2 function2) {
        super(2);
        this.$onError = function2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Unit invoke(File file, IOException iOException) {
        File file2 = file;
        if (((OnErrorAction) this.$onError.invoke(file2, iOException)) != OnErrorAction.TERMINATE) {
            return Unit.INSTANCE;
        }
        throw new TerminateException(file2);
    }
}

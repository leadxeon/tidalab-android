package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.model.File;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: FilesDesign.kt */
/* loaded from: classes.dex */
public /* synthetic */ class FilesDesign$adapter$1 extends FunctionReferenceImpl implements Function1<File, Unit> {
    public FilesDesign$adapter$1(FilesDesign filesDesign) {
        super(1, filesDesign, FilesDesign.class, "requestOpen", "requestOpen(Lcom/tidalab/v2board/clash/design/model/File;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(File file) {
        File file2 = file;
        FilesDesign filesDesign = (FilesDesign) this.receiver;
        Objects.requireNonNull(filesDesign);
        if (file2.isDirectory) {
            filesDesign.requests.mo14trySendJP2dKIU(new FilesDesign.Request.OpenDirectory(file2));
        } else {
            filesDesign.requests.mo14trySendJP2dKIU(new FilesDesign.Request.OpenFile(file2));
        }
        return Unit.INSTANCE;
    }
}

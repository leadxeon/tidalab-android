package kotlin.io;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.File;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FilePathComponents.kt */
/* loaded from: classes.dex */
public final class FilePathComponents {
    public final File root;
    public final List<File> segments;

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(File file, List<? extends File> list) {
        this.root = file;
        this.segments = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePathComponents)) {
            return false;
        }
        FilePathComponents filePathComponents = (FilePathComponents) obj;
        return Intrinsics.areEqual(this.root, filePathComponents.root) && Intrinsics.areEqual(this.segments, filePathComponents.segments);
    }

    public int hashCode() {
        File file = this.root;
        int i = 0;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        List<File> list = this.segments;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FilePathComponents(root=");
        outline13.append(this.root);
        outline13.append(", segments=");
        outline13.append(this.segments);
        outline13.append(")");
        return outline13.toString();
    }
}

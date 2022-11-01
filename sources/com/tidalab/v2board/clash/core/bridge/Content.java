package com.tidalab.v2board.clash.core.bridge;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.Keep;
import com.tidalab.v2board.clash.common.Global;
import java.io.FileNotFoundException;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Content.kt */
@Keep
/* loaded from: classes.dex */
public final class Content {
    public static final Content INSTANCE = new Content();

    private Content() {
    }

    public static final int open(String str) {
        Uri parse = Uri.parse(str);
        if (Intrinsics.areEqual(parse.getScheme(), "content")) {
            ParcelFileDescriptor openFileDescriptor = Global.INSTANCE.getApplication().getContentResolver().openFileDescriptor(parse, "r");
            Integer valueOf = openFileDescriptor == null ? null : Integer.valueOf(openFileDescriptor.detachFd());
            if (valueOf != null) {
                return valueOf.intValue();
            }
            throw new FileNotFoundException(parse + " not found");
        }
        throw new UnsupportedOperationException(Intrinsics.stringPlus("Unsupported scheme ", parse.getScheme()));
    }
}

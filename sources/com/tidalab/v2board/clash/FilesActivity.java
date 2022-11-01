package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.FilesDesign;
import kotlinx.coroutines.channels.SendChannel;
/* compiled from: FilesActivity.kt */
/* loaded from: classes.dex */
public final class FilesActivity extends BaseActivity<FilesDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a4 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetch(com.tidalab.v2board.clash.design.FilesDesign r10, com.tidalab.v2board.clash.remote.FilesClient r11, java.util.Stack<java.lang.String> r12, java.lang.String r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.FilesActivity.fetch(com.tidalab.v2board.clash.design.FilesDesign, com.tidalab.v2board.clash.remote.FilesClient, java.util.Stack, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0119 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0196  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0191 -> B:54:0x0194). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 409
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.FilesActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.facebook.react.ReactActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        SendChannel sendChannel;
        FilesDesign filesDesign = (FilesDesign) this.design;
        if (filesDesign != null && (sendChannel = filesDesign.requests) != null) {
            sendChannel.mo14trySendJP2dKIU(FilesDesign.Request.PopStack.INSTANCE);
        }
    }
}

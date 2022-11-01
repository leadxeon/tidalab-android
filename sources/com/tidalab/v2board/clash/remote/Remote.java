package com.tidalab.v2board.clash.remote;

import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.coroutines.channels.Channel;
/* compiled from: Remote.kt */
/* loaded from: classes.dex */
public final class Remote {
    public static final Broadcasts broadcasts;
    public static final Service service;
    public static final Remote INSTANCE = new Remote();
    public static final Channel<Boolean> visible = InputKt.Channel$default(-1, null, null, 6);

    static {
        Global global = Global.INSTANCE;
        broadcasts = new Broadcasts(global.getApplication());
        service = new Service(global.getApplication(), Remote$service$1.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0210  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0139 -> B:44:0x013d). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$run(com.tidalab.v2board.clash.remote.Remote r14, kotlin.coroutines.Continuation r15) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.remote.Remote.access$run(com.tidalab.v2board.clash.remote.Remote, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

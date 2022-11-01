package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
/* compiled from: ConfigurationModule.kt */
/* loaded from: classes.dex */
public final class ConfigurationModule extends Module<LoadException> {
    public final Channel<Unit> reload = InputKt.Channel$default(-1, null, null, 6);
    public final ServiceStore store;

    /* compiled from: ConfigurationModule.kt */
    /* loaded from: classes.dex */
    public static final class LoadException {
        public final String message;

        public LoadException(String str) {
            this.message = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof LoadException) && Intrinsics.areEqual(this.message, ((LoadException) obj).message);
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("LoadException(message=");
            outline13.append(this.message);
            outline13.append(')');
            return outline13.toString();
        }
    }

    public ConfigurationModule(Service service) {
        super(service);
        this.store = new ServiceStore(service);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:2|(2:4|(4:6|8|88|9))|7|8|88|9|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x009b, code lost:
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0102 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x010e A[Catch: Exception -> 0x024b, TryCatch #0 {Exception -> 0x024b, blocks: (B:34:0x0106, B:36:0x010e, B:39:0x0116, B:42:0x011d, B:75:0x0245, B:76:0x024a), top: B:86:0x0106 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x013b A[Catch: Exception -> 0x009b, TryCatch #1 {Exception -> 0x009b, blocks: (B:14:0x004b, B:17:0x0066, B:19:0x007f, B:21:0x0095, B:46:0x0137, B:48:0x013b, B:52:0x0173, B:55:0x018d, B:56:0x0198, B:58:0x019e, B:60:0x01bb, B:61:0x01bf, B:62:0x01ce, B:64:0x01d4, B:65:0x01e0, B:72:0x023c, B:73:0x0241), top: B:88:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x018c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x019e A[Catch: Exception -> 0x009b, TryCatch #1 {Exception -> 0x009b, blocks: (B:14:0x004b, B:17:0x0066, B:19:0x007f, B:21:0x0095, B:46:0x0137, B:48:0x013b, B:52:0x0173, B:55:0x018d, B:56:0x0198, B:58:0x019e, B:60:0x01bb, B:61:0x01bf, B:62:0x01ce, B:64:0x01d4, B:65:0x01e0, B:72:0x023c, B:73:0x0241), top: B:88:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01d4 A[Catch: Exception -> 0x009b, LOOP:1: B:62:0x01ce->B:64:0x01d4, LOOP_END, TryCatch #1 {Exception -> 0x009b, blocks: (B:14:0x004b, B:17:0x0066, B:19:0x007f, B:21:0x0095, B:46:0x0137, B:48:0x013b, B:52:0x0173, B:55:0x018d, B:56:0x0198, B:58:0x019e, B:60:0x01bb, B:61:0x01bf, B:62:0x01ce, B:64:0x01d4, B:65:0x01e0, B:72:0x023c, B:73:0x0241), top: B:88:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01f9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x023c A[Catch: Exception -> 0x009b, TRY_ENTER, TryCatch #1 {Exception -> 0x009b, blocks: (B:14:0x004b, B:17:0x0066, B:19:0x007f, B:21:0x0095, B:46:0x0137, B:48:0x013b, B:52:0x0173, B:55:0x018d, B:56:0x0198, B:58:0x019e, B:60:0x01bb, B:61:0x01bf, B:62:0x01ce, B:64:0x01d4, B:65:0x01e0, B:72:0x023c, B:73:0x0241), top: B:88:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0245 A[Catch: Exception -> 0x024b, TRY_ENTER, TryCatch #0 {Exception -> 0x024b, blocks: (B:34:0x0106, B:36:0x010e, B:39:0x0116, B:42:0x011d, B:75:0x0245, B:76:0x024a), top: B:86:0x0106 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x026c A[RETURN] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v16 */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5, types: [kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x011c -> B:26:0x00cc). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x01f7 -> B:15:0x004e). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instructions count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.ConfigurationModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}

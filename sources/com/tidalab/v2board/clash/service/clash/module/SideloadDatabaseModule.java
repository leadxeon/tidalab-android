package com.tidalab.v2board.clash.service.clash.module;

import android.app.Service;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
/* compiled from: SideloadDatabaseModule.kt */
/* loaded from: classes.dex */
public final class SideloadDatabaseModule extends Module<LoadException> {
    public String current = HttpUrl.FRAGMENT_ENCODE_SET;
    public final ServiceStore store;

    /* compiled from: SideloadDatabaseModule.kt */
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

    public SideloadDatabaseModule(Service service) {
        super(service);
        this.store = new ServiceStore(service);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:2|(2:4|(9:6|8|(1:(1:(1:(1:(1:(3:15|54|55)(2:16|17))(3:18|60|61))(3:19|66|67))(9:20|35|(4:37|(3:70|39|(1:41))|42|(3:44|45|(1:47)(1:48)))|49|27|68|28|31|(1:33)(9:34|35|(0)|49|27|68|28|31|(0)(0))))(1:21))(2:22|(1:24)(1:25))|26|27|68|28|31|(0)(0)))|7|8|(0)(0)|26|27|68|28|31|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ea, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00eb, code lost:
        r6.handleBuilderException(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00f4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0110  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00f5 -> B:35:0x00fb). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.clash.module.Module
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object run(kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.clash.module.SideloadDatabaseModule.run(kotlin.coroutines.Continuation):java.lang.Object");
    }
}

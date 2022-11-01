package com.tidalab.v2board.clash.log;

import androidx.collection.CircularArray;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.core.model.LogMessage;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: LogcatCache.kt */
/* loaded from: classes.dex */
public final class LogcatCache {
    public int appended;
    public final CircularArray<LogMessage> array = new CircularArray<>(128);
    public final Mutex lock = MutexKt.Mutex$default(false, 1);
    public int removed;

    /* compiled from: LogcatCache.kt */
    /* loaded from: classes.dex */
    public static final class Snapshot {
        public final int appended;
        public final List<LogMessage> messages;
        public final int removed;

        public Snapshot(List<LogMessage> list, int i, int i2) {
            this.messages = list;
            this.removed = i;
            this.appended = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Snapshot)) {
                return false;
            }
            Snapshot snapshot = (Snapshot) obj;
            return Intrinsics.areEqual(this.messages, snapshot.messages) && this.removed == snapshot.removed && this.appended == snapshot.appended;
        }

        public int hashCode() {
            return (((this.messages.hashCode() * 31) + this.removed) * 31) + this.appended;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Snapshot(messages=");
            outline13.append(this.messages);
            outline13.append(", removed=");
            outline13.append(this.removed);
            outline13.append(", appended=");
            outline13.append(this.appended);
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005d A[Catch: all -> 0x0083, TryCatch #0 {all -> 0x0083, blocks: (B:18:0x0053, B:20:0x005d, B:21:0x006d), top: B:27:0x0053 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object append(com.tidalab.v2board.clash.core.model.LogMessage r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.log.LogcatCache$append$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.log.LogcatCache$append$1 r0 = (com.tidalab.v2board.clash.log.LogcatCache$append$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.log.LogcatCache$append$1 r0 = new com.tidalab.v2board.clash.log.LogcatCache$append$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r4) goto L_0x0034
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r1 = r0.L$1
            com.tidalab.v2board.clash.core.model.LogMessage r1 = (com.tidalab.v2board.clash.core.model.LogMessage) r1
            java.lang.Object r0 = r0.L$0
            com.tidalab.v2board.clash.log.LogcatCache r0 = (com.tidalab.v2board.clash.log.LogcatCache) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x0053
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.lock
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r0 = r7.lock(r3, r0)
            if (r0 != r1) goto L_0x0050
            return r1
        L_0x0050:
            r0 = r5
            r1 = r6
            r6 = r7
        L_0x0053:
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r7 = r0.array     // Catch: all -> 0x0083
            int r7 = r7.size()     // Catch: all -> 0x0083
            r2 = 128(0x80, float:1.794E-43)
            if (r7 < r2) goto L_0x006d
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r7 = r0.array     // Catch: all -> 0x0083
            r7.removeFromStart(r4)     // Catch: all -> 0x0083
            int r7 = r0.removed     // Catch: all -> 0x0083
            int r7 = r7 + r4
            r0.removed = r7     // Catch: all -> 0x0083
            int r7 = r0.appended     // Catch: all -> 0x0083
            int r7 = r7 + (-1)
            r0.appended = r7     // Catch: all -> 0x0083
        L_0x006d:
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r7 = r0.array     // Catch: all -> 0x0083
            r7.addLast(r1)     // Catch: all -> 0x0083
            int r7 = r0.appended     // Catch: all -> 0x0083
            int r1 = r7 + 1
            r0.appended = r1     // Catch: all -> 0x0083
            java.lang.Integer r0 = new java.lang.Integer     // Catch: all -> 0x0083
            r0.<init>(r7)     // Catch: all -> 0x0083
            r6.unlock(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0083:
            r7 = move-exception
            r6.unlock(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.log.LogcatCache.append(com.tidalab.v2board.clash.core.model.LogMessage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b A[Catch: all -> 0x00b6, TryCatch #0 {all -> 0x00b6, blocks: (B:19:0x0052, B:21:0x0056, B:24:0x005c, B:26:0x006b, B:28:0x007b, B:30:0x0081, B:31:0x0093, B:32:0x0098, B:33:0x0099, B:35:0x009d, B:36:0x00a7, B:37:0x00a9), top: B:43:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d A[Catch: all -> 0x00b6, TryCatch #0 {all -> 0x00b6, blocks: (B:19:0x0052, B:21:0x0056, B:24:0x005c, B:26:0x006b, B:28:0x007b, B:30:0x0081, B:31:0x0093, B:32:0x0098, B:33:0x0099, B:35:0x009d, B:36:0x00a7, B:37:0x00a9), top: B:43:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7 A[Catch: all -> 0x00b6, TryCatch #0 {all -> 0x00b6, blocks: (B:19:0x0052, B:21:0x0056, B:24:0x005c, B:26:0x006b, B:28:0x007b, B:30:0x0081, B:31:0x0093, B:32:0x0098, B:33:0x0099, B:35:0x009d, B:36:0x00a7, B:37:0x00a9), top: B:43:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object snapshot(boolean r11, kotlin.coroutines.Continuation<? super com.tidalab.v2board.clash.log.LogcatCache.Snapshot> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.tidalab.v2board.clash.log.LogcatCache$snapshot$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.tidalab.v2board.clash.log.LogcatCache$snapshot$1 r0 = (com.tidalab.v2board.clash.log.LogcatCache$snapshot$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.log.LogcatCache$snapshot$1 r0 = new com.tidalab.v2board.clash.log.LogcatCache$snapshot$1
            r0.<init>(r10, r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r4) goto L_0x0032
            boolean r11 = r0.Z$0
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            com.tidalab.v2board.clash.log.LogcatCache r0 = (com.tidalab.v2board.clash.log.LogcatCache) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            goto L_0x0050
        L_0x0032:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x003a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            kotlinx.coroutines.sync.Mutex r12 = r10.lock
            r0.L$0 = r10
            r0.L$1 = r12
            r0.Z$0 = r11
            r0.label = r4
            java.lang.Object r0 = r12.lock(r3, r0)
            if (r0 != r1) goto L_0x004e
            return r1
        L_0x004e:
            r0 = r10
            r1 = r12
        L_0x0050:
            if (r11 != 0) goto L_0x005c
            int r12 = r0.removed     // Catch: all -> 0x00b6
            if (r12 != 0) goto L_0x005c
            int r12 = r0.appended     // Catch: all -> 0x00b6
            if (r12 != 0) goto L_0x005c
            r5 = r3
            goto L_0x00b2
        L_0x005c:
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r12 = r0.array     // Catch: all -> 0x00b6
            int r12 = r12.size()     // Catch: all -> 0x00b6
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: all -> 0x00b6
            r2.<init>(r12)     // Catch: all -> 0x00b6
            r4 = 0
            r5 = 0
        L_0x0069:
            if (r5 >= r12) goto L_0x0099
            java.lang.Integer r6 = new java.lang.Integer     // Catch: all -> 0x00b6
            r6.<init>(r5)     // Catch: all -> 0x00b6
            int r6 = r6.intValue()     // Catch: all -> 0x00b6
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r7 = r0.array     // Catch: all -> 0x00b6
            java.util.Objects.requireNonNull(r7)     // Catch: all -> 0x00b6
            if (r6 < 0) goto L_0x0093
            int r8 = r7.size()     // Catch: all -> 0x00b6
            if (r6 >= r8) goto L_0x0093
            E[] r8 = r7.mElements     // Catch: all -> 0x00b6
            int r9 = r7.mHead     // Catch: all -> 0x00b6
            int r9 = r9 + r6
            int r6 = r7.mCapacityBitmask     // Catch: all -> 0x00b6
            r6 = r6 & r9
            r6 = r8[r6]     // Catch: all -> 0x00b6
            com.tidalab.v2board.clash.core.model.LogMessage r6 = (com.tidalab.v2board.clash.core.model.LogMessage) r6     // Catch: all -> 0x00b6
            r2.add(r6)     // Catch: all -> 0x00b6
            int r5 = r5 + 1
            goto L_0x0069
        L_0x0093:
            java.lang.ArrayIndexOutOfBoundsException r11 = new java.lang.ArrayIndexOutOfBoundsException     // Catch: all -> 0x00b6
            r11.<init>()     // Catch: all -> 0x00b6
            throw r11     // Catch: all -> 0x00b6
        L_0x0099:
            int r12 = r0.removed     // Catch: all -> 0x00b6
            if (r11 == 0) goto L_0x00a7
            androidx.collection.CircularArray<com.tidalab.v2board.clash.core.model.LogMessage> r11 = r0.array     // Catch: all -> 0x00b6
            int r11 = r11.size()     // Catch: all -> 0x00b6
            int r5 = r0.appended     // Catch: all -> 0x00b6
            int r11 = r11 + r5
            goto L_0x00a9
        L_0x00a7:
            int r11 = r0.appended     // Catch: all -> 0x00b6
        L_0x00a9:
            com.tidalab.v2board.clash.log.LogcatCache$Snapshot r5 = new com.tidalab.v2board.clash.log.LogcatCache$Snapshot     // Catch: all -> 0x00b6
            r5.<init>(r2, r12, r11)     // Catch: all -> 0x00b6
            r0.removed = r4     // Catch: all -> 0x00b6
            r0.appended = r4     // Catch: all -> 0x00b6
        L_0x00b2:
            r1.unlock(r3)
            return r5
        L_0x00b6:
            r11 = move-exception
            r1.unlock(r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.log.LogcatCache.snapshot(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

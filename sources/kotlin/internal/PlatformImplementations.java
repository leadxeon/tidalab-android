package kotlin.internal;

import java.lang.reflect.Method;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
/* compiled from: PlatformImplementations.kt */
/* loaded from: classes.dex */
public class PlatformImplementations {

    /* compiled from: PlatformImplementations.kt */
    /* loaded from: classes.dex */
    public static final class ReflectThrowable {
        public static final Method addSuppressed;

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual(r5, java.lang.Throwable.class) != false) goto L_0x002c;
         */
        static {
            /*
                java.lang.Class<java.lang.Throwable> r0 = java.lang.Throwable.class
                java.lang.reflect.Method[] r1 = r0.getMethods()
                int r2 = r1.length
                r3 = 0
                r4 = 0
            L_0x0009:
                r5 = 0
                if (r4 >= r2) goto L_0x0033
                r6 = r1[r4]
                java.lang.String r7 = r6.getName()
                java.lang.String r8 = "addSuppressed"
                boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r8)
                r8 = 1
                if (r7 == 0) goto L_0x002b
                java.lang.Class[] r7 = r6.getParameterTypes()
                int r9 = r7.length
                if (r9 != r8) goto L_0x0024
                r5 = r7[r3]
            L_0x0024:
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
                if (r5 == 0) goto L_0x002b
                goto L_0x002c
            L_0x002b:
                r8 = 0
            L_0x002c:
                if (r8 == 0) goto L_0x0030
                r5 = r6
                goto L_0x0033
            L_0x0030:
                int r4 = r4 + 1
                goto L_0x0009
            L_0x0033:
                kotlin.internal.PlatformImplementations.ReflectThrowable.addSuppressed = r5
                int r0 = r1.length
            L_0x0036:
                if (r3 >= r0) goto L_0x004a
                r2 = r1[r3]
                java.lang.String r2 = r2.getName()
                java.lang.String r4 = "getSuppressed"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
                if (r2 == 0) goto L_0x0047
                goto L_0x004a
            L_0x0047:
                int r3 = r3 + 1
                goto L_0x0036
            L_0x004a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementations.ReflectThrowable.<clinit>():void");
        }
    }

    public void addSuppressed(Throwable th, Throwable th2) {
        Method method = ReflectThrowable.addSuppressed;
        if (method != null) {
            method.invoke(th, th2);
        }
    }

    public Random defaultPlatformRandom() {
        return new FallbackThreadLocalRandom();
    }
}

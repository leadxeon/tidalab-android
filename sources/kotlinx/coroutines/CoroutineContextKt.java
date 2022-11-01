package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
/* compiled from: CoroutineContext.kt */
/* loaded from: classes.dex */
public final class CoroutineContextKt {
    public static final boolean useCoroutinesScheduler;

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
        if (r0.equals("on") != false) goto L_0x0053;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
        if (r0.equals(okhttp3.HttpUrl.FRAGMENT_ENCODE_SET) != false) goto L_0x0053;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.scheduler"
            java.lang.String r0 = com.tidalab.v2board.clash.design.dialog.InputKt.systemProp(r0)
            if (r0 == 0) goto L_0x0053
            int r1 = r0.hashCode()
            if (r1 == 0) goto L_0x002a
            r2 = 3551(0xddf, float:4.976E-42)
            if (r1 == r2) goto L_0x0021
            r2 = 109935(0x1ad6f, float:1.54052E-40)
            if (r1 != r2) goto L_0x0033
            java.lang.String r1 = "off"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0054
        L_0x0021:
            java.lang.String r1 = "on"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0033
            goto L_0x0053
        L_0x002a:
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0033
            goto L_0x0053
        L_0x0033:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "System property 'kotlinx.coroutines.scheduler' has unrecognized value '"
            r1.append(r2)
            r1.append(r0)
            r0 = 39
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0053:
            r0 = 1
        L_0x0054:
            kotlinx.coroutines.CoroutineContextKt.useCoroutinesScheduler = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.<clinit>():void");
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext plus = coroutineScope.getCoroutineContext().plus(coroutineContext);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        CoroutineDispatcher coroutineDispatcher = Dispatchers.Default;
        return (plus == coroutineDispatcher || plus.get(ContinuationInterceptor.Key.$$INSTANCE) != null) ? plus : plus.plus(coroutineDispatcher);
    }

    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        UndispatchedCoroutine<?> undispatchedCoroutine = null;
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (!(coroutineContext.get(UndispatchedMarker.INSTANCE) != null)) {
            return null;
        }
        CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) continuation;
        while (true) {
            if (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
                if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                    undispatchedCoroutine = (UndispatchedCoroutine) coroutineStackFrame;
                    break;
                }
            } else {
                break;
            }
        }
        if (undispatchedCoroutine != null) {
            undispatchedCoroutine.savedContext = coroutineContext;
            undispatchedCoroutine.savedOldValue = obj;
        }
        return undispatchedCoroutine;
    }
}

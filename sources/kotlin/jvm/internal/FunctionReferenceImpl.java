package kotlin.jvm.internal;

import kotlin.jvm.internal.CallableReference;
/* loaded from: classes.dex */
public class FunctionReferenceImpl extends FunctionReference {
    public FunctionReferenceImpl(int i, Class cls, String str, String str2, int i2) {
        super(i, CallableReference.NoReceiver.INSTANCE, cls, str, str2, i2);
    }

    public FunctionReferenceImpl(int i, Object obj, Class cls, String str, String str2, int i2) {
        super(i, obj, cls, str, str2, i2);
    }
}

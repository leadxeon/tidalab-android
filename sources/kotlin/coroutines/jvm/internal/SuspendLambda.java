package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Reflection;
/* compiled from: ContinuationImpl.kt */
/* loaded from: classes.dex */
public abstract class SuspendLambda extends ContinuationImpl implements FunctionBase<Object> {
    public final int arity;

    public SuspendLambda(int i, Continuation<Object> continuation) {
        super(continuation);
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if (this.completion == null) {
            return Reflection.factory.renderLambdaToString(this);
        }
        return super.toString();
    }
}

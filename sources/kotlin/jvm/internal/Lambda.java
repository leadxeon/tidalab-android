package kotlin.jvm.internal;

import java.io.Serializable;
/* compiled from: Lambda.kt */
/* loaded from: classes.dex */
public abstract class Lambda<R> implements FunctionBase<R>, Serializable {
    public final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        return Reflection.factory.renderLambdaToString(this);
    }
}

package com.facebook.common.internal;
/* loaded from: classes.dex */
public final class Throwables {
    public static void propagateIfPossible(Throwable th) {
        if (Error.class.isInstance(th)) {
            throw ((Throwable) Error.class.cast(th));
        } else if (RuntimeException.class.isInstance(th)) {
            throw ((Throwable) RuntimeException.class.cast(th));
        }
    }
}

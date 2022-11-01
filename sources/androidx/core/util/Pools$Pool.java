package androidx.core.util;
/* loaded from: classes.dex */
public interface Pools$Pool<T> {
    T acquire();

    boolean release(T t);
}

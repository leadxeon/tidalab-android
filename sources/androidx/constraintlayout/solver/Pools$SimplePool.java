package androidx.constraintlayout.solver;
/* loaded from: classes.dex */
public class Pools$SimplePool<T> {
    public final Object[] mPool;
    public int mPoolSize;

    public Pools$SimplePool(int i) {
        if (i > 0) {
            this.mPool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public T acquire() {
        int i = this.mPoolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.mPool;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.mPoolSize = i - 1;
        return t;
    }

    public boolean release(T t) {
        int i = this.mPoolSize;
        Object[] objArr = this.mPool;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.mPoolSize = i + 1;
        return true;
    }
}

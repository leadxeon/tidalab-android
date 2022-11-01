package androidx.core.util;
/* loaded from: classes.dex */
public class Pools$SimplePool<T> implements Pools$Pool<T> {
    public final Object[] mPool;
    public int mPoolSize;

    public Pools$SimplePool(int i) {
        if (i > 0) {
            this.mPool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    @Override // androidx.core.util.Pools$Pool
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

    @Override // androidx.core.util.Pools$Pool
    public boolean release(T t) {
        int i;
        boolean z;
        int i2 = 0;
        while (true) {
            i = this.mPoolSize;
            if (i2 >= i) {
                z = false;
                break;
            } else if (this.mPool[i2] == t) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (!z) {
            Object[] objArr = this.mPool;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = t;
            this.mPoolSize = i + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }
}

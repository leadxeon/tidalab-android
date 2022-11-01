package kotlinx.coroutines.internal;
/* compiled from: ArrayQueue.kt */
/* loaded from: classes.dex */
public class ArrayQueue<T> {
    public Object[] elements = new Object[16];
    public int head;
    public int tail;
}

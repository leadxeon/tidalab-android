package androidx.lifecycle;
/* loaded from: classes.dex */
public class MutableLiveData<T> extends LiveData<T> {
    public void setValue(T t) {
        LiveData.assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        dispatchingValue(null);
    }
}

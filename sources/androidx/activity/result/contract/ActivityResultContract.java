package androidx.activity.result.contract;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public abstract class ActivityResultContract<I, O> {

    /* loaded from: classes.dex */
    public static final class SynchronousResult<T> {
        @SuppressLint({"UnknownNullness"})
        public final T mValue;

        public SynchronousResult(@SuppressLint({"UnknownNullness"}) T t) {
            this.mValue = t;
        }
    }

    public abstract Intent createIntent(Context context, @SuppressLint({"UnknownNullness"}) I i);

    public SynchronousResult<O> getSynchronousResult(Context context, @SuppressLint({"UnknownNullness"}) I i) {
        return null;
    }

    @SuppressLint({"UnknownNullness"})
    public abstract O parseResult(int i, Intent intent);
}

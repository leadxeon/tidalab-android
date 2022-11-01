package com.facebook.imagepipeline.memory;

import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import java.util.LinkedList;
import java.util.Queue;
/* loaded from: classes.dex */
public class Bucket<V> {
    public final boolean mFixBucketsReinitialization;
    public final Queue mFreeList;
    public int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;

    public Bucket(int i, int i2, int i3, boolean z) {
        boolean z2 = true;
        R$dimen.checkState(i > 0);
        R$dimen.checkState(i2 >= 0);
        R$dimen.checkState(i3 < 0 ? false : z2);
        this.mItemSize = i;
        this.mMaxLength = i2;
        this.mFreeList = new LinkedList();
        this.mInUseLength = i3;
        this.mFixBucketsReinitialization = z;
    }

    public void addToFreeList(V v) {
        this.mFreeList.add(v);
    }

    public void decrementInUseCount() {
        R$dimen.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
    }

    public V pop() {
        return (V) this.mFreeList.poll();
    }

    public void release(V v) {
        boolean z = false;
        if (this.mFixBucketsReinitialization) {
            if (this.mInUseLength > 0) {
                z = true;
            }
            R$dimen.checkState(z);
            this.mInUseLength--;
            addToFreeList(v);
            return;
        }
        int i = this.mInUseLength;
        if (i > 0) {
            this.mInUseLength = i - 1;
            addToFreeList(v);
            return;
        }
        Object[] objArr = {v};
        int i2 = FLog.$r8$clinit;
        Log.println(6, "unknown:BUCKET", FLog.formatString("Tried to release value %s from an empty bucket!", objArr));
    }
}

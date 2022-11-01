package com.facebook.react.common;

import com.android.tools.r8.GeneratedOutlineSupport;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class LongArray {
    public long[] mArray;
    public int mLength = 0;

    public LongArray(int i) {
        this.mArray = new long[i];
    }

    public void add(long j) {
        int i = this.mLength;
        if (i == this.mArray.length) {
            long[] jArr = new long[Math.max(i + 1, (int) (i * 1.8d))];
            System.arraycopy(this.mArray, 0, jArr, 0, this.mLength);
            this.mArray = jArr;
        }
        long[] jArr2 = this.mArray;
        int i2 = this.mLength;
        this.mLength = i2 + 1;
        jArr2[i2] = j;
    }

    public long get(int i) {
        if (i < this.mLength) {
            return this.mArray[i];
        }
        StringBuilder outline15 = GeneratedOutlineSupport.outline15(HttpUrl.FRAGMENT_ENCODE_SET, i, " >= ");
        outline15.append(this.mLength);
        throw new IndexOutOfBoundsException(outline15.toString());
    }
}

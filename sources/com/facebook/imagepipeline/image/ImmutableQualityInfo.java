package com.facebook.imagepipeline.image;
/* loaded from: classes.dex */
public class ImmutableQualityInfo implements QualityInfo {
    public static final QualityInfo FULL_QUALITY = new ImmutableQualityInfo(Integer.MAX_VALUE, true, true);
    public boolean mIsOfFullQuality;
    public boolean mIsOfGoodEnoughQuality;
    public int mQuality;

    public ImmutableQualityInfo(int i, boolean z, boolean z2) {
        this.mQuality = i;
        this.mIsOfGoodEnoughQuality = z;
        this.mIsOfFullQuality = z2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableQualityInfo)) {
            return false;
        }
        ImmutableQualityInfo immutableQualityInfo = (ImmutableQualityInfo) obj;
        return this.mQuality == immutableQualityInfo.mQuality && this.mIsOfGoodEnoughQuality == immutableQualityInfo.mIsOfGoodEnoughQuality && this.mIsOfFullQuality == immutableQualityInfo.mIsOfFullQuality;
    }

    public int hashCode() {
        int i = 0;
        int i2 = this.mQuality ^ (this.mIsOfGoodEnoughQuality ? 4194304 : 0);
        if (this.mIsOfFullQuality) {
            i = 8388608;
        }
        return i2 ^ i;
    }
}

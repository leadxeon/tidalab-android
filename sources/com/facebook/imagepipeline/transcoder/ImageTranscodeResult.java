package com.facebook.imagepipeline.transcoder;
/* loaded from: classes.dex */
public class ImageTranscodeResult {
    public final int mTranscodeStatus;

    public ImageTranscodeResult(int i) {
        this.mTranscodeStatus = i;
    }

    public String toString() {
        return String.format(null, "Status: %d", Integer.valueOf(this.mTranscodeStatus));
    }
}

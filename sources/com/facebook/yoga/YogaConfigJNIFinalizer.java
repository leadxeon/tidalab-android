package com.facebook.yoga;
/* loaded from: classes.dex */
public class YogaConfigJNIFinalizer extends YogaConfigJNIBase {
    public void finalize() throws Throwable {
        try {
            long j = this.mNativePointer;
            if (j != 0) {
                this.mNativePointer = 0L;
                YogaNative.jni_YGConfigFreeJNI(j);
            }
        } finally {
            super.finalize();
        }
    }
}

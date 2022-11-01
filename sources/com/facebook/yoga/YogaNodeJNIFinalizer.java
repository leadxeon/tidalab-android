package com.facebook.yoga;
/* loaded from: classes.dex */
public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
    public YogaNodeJNIFinalizer() {
    }

    public void finalize() throws Throwable {
        try {
            long j = this.mNativePointer;
            if (j != 0) {
                this.mNativePointer = 0L;
                YogaNative.jni_YGNodeFreeJNI(j);
            }
        } finally {
            super.finalize();
        }
    }

    public YogaNodeJNIFinalizer(YogaConfigJNIBase yogaConfigJNIBase) {
        super(YogaNative.jni_YGNodeNewWithConfigJNI(yogaConfigJNIBase.mNativePointer));
    }
}

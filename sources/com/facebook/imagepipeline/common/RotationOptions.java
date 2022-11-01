package com.facebook.imagepipeline.common;

import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class RotationOptions {
    public static final RotationOptions ROTATION_OPTIONS_AUTO_ROTATE = new RotationOptions(-1, false);
    public static final RotationOptions ROTATION_OPTIONS_DISABLE_ROTATION = new RotationOptions(-2, false);
    public static final RotationOptions ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME = new RotationOptions(-1, true);
    public final boolean mDeferUntilRendered;
    public final int mRotation;

    public RotationOptions(int i, boolean z) {
        this.mRotation = i;
        this.mDeferUntilRendered = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RotationOptions)) {
            return false;
        }
        RotationOptions rotationOptions = (RotationOptions) obj;
        return this.mRotation == rotationOptions.mRotation && this.mDeferUntilRendered == rotationOptions.mDeferUntilRendered;
    }

    public int getForcedAngle() {
        if (!useImageMetadata()) {
            return this.mRotation;
        }
        throw new IllegalStateException("Rotation is set to use EXIF");
    }

    public int hashCode() {
        Integer valueOf = Integer.valueOf(this.mRotation);
        Boolean valueOf2 = Boolean.valueOf(this.mDeferUntilRendered);
        int i = 0;
        int hashCode = valueOf == null ? 0 : valueOf.hashCode();
        if (valueOf2 != null) {
            i = valueOf2.hashCode();
        }
        return R$dimen.hashCode(hashCode, i);
    }

    public boolean rotationEnabled() {
        return this.mRotation != -2;
    }

    public String toString() {
        return String.format(null, "%d defer:%b", Integer.valueOf(this.mRotation), Boolean.valueOf(this.mDeferUntilRendered));
    }

    public boolean useImageMetadata() {
        return this.mRotation == -1;
    }
}

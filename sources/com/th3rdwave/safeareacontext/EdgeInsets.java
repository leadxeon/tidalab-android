package com.th3rdwave.safeareacontext;
/* loaded from: classes.dex */
public class EdgeInsets {
    public float bottom;
    public float left;
    public float right;
    public float top;

    public EdgeInsets(float f, float f2, float f3, float f4) {
        this.top = f;
        this.right = f2;
        this.bottom = f3;
        this.left = f4;
    }

    public boolean equalsToEdgeInsets(EdgeInsets edgeInsets) {
        if (this == edgeInsets) {
            return true;
        }
        return this.top == edgeInsets.top && this.right == edgeInsets.right && this.bottom == edgeInsets.bottom && this.left == edgeInsets.left;
    }
}

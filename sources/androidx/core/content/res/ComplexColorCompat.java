package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.graphics.Shader;
/* loaded from: classes.dex */
public final class ComplexColorCompat {
    public int mColor;
    public final ColorStateList mColorStateList;
    public final Shader mShader;

    public ComplexColorCompat(Shader shader, ColorStateList colorStateList, int i) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x01c4, code lost:
        throw new org.xmlpull.v1.XmlPullParserException(r2.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.core.content.res.ComplexColorCompat createFromXml(android.content.res.Resources r29, int r30, android.content.res.Resources.Theme r31) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            Method dump skipped, instructions count: 645
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.res.ComplexColorCompat.createFromXml(android.content.res.Resources, int, android.content.res.Resources$Theme):androidx.core.content.res.ComplexColorCompat");
    }

    public boolean isGradient() {
        return this.mShader != null;
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        return this.mShader == null && (colorStateList = this.mColorStateList) != null && colorStateList.isStateful();
    }

    public boolean onStateChanged(int[] iArr) {
        if (isStateful()) {
            ColorStateList colorStateList = this.mColorStateList;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.mColor) {
                this.mColor = colorForState;
                return true;
            }
        }
        return false;
    }
}

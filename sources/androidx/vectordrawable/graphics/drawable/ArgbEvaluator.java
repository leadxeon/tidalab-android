package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {
    public static final ArgbEvaluator sInstance = new ArgbEvaluator();

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        float f2 = ((intValue >> 24) & 255) / 255.0f;
        int intValue2 = ((Integer) obj2).intValue();
        float pow = (float) Math.pow(((intValue >> 16) & 255) / 255.0f, 2.2d);
        float pow2 = (float) Math.pow(((intValue >> 8) & 255) / 255.0f, 2.2d);
        float pow3 = (float) Math.pow((intValue & 255) / 255.0f, 2.2d);
        float pow4 = (float) Math.pow(((intValue2 >> 16) & 255) / 255.0f, 2.2d);
        float outline0 = GeneratedOutlineSupport.outline0(((intValue2 >> 24) & 255) / 255.0f, f2, f, f2);
        float outline02 = GeneratedOutlineSupport.outline0(pow4, pow, f, pow);
        float outline03 = GeneratedOutlineSupport.outline0((float) Math.pow(((intValue2 >> 8) & 255) / 255.0f, 2.2d), pow2, f, pow2);
        float outline04 = GeneratedOutlineSupport.outline0((float) Math.pow((intValue2 & 255) / 255.0f, 2.2d), pow3, f, pow3);
        int round = Math.round(((float) Math.pow(outline02, 0.45454545454545453d)) * 255.0f) << 16;
        return Integer.valueOf(Math.round(((float) Math.pow(outline04, 0.45454545454545453d)) * 255.0f) | round | (Math.round(outline0 * 255.0f) << 24) | (Math.round(((float) Math.pow(outline03, 0.45454545454545453d)) * 255.0f) << 8));
    }
}

package androidx.vectordrawable.graphics.drawable;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.graphics.PathParser$PathDataNode;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.HttpUrl;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes.dex */
public class AnimatorInflaterCompat {

    /* loaded from: classes.dex */
    public static class PathDataEvaluator implements TypeEvaluator<PathParser$PathDataNode[]> {
        public PathParser$PathDataNode[] mNodeArray;

        @Override // android.animation.TypeEvaluator
        public PathParser$PathDataNode[] evaluate(float f, PathParser$PathDataNode[] pathParser$PathDataNodeArr, PathParser$PathDataNode[] pathParser$PathDataNodeArr2) {
            PathParser$PathDataNode[] pathParser$PathDataNodeArr3 = pathParser$PathDataNodeArr;
            PathParser$PathDataNode[] pathParser$PathDataNodeArr4 = pathParser$PathDataNodeArr2;
            if (AppOpsManagerCompat.canMorph(pathParser$PathDataNodeArr3, pathParser$PathDataNodeArr4)) {
                if (!AppOpsManagerCompat.canMorph(this.mNodeArray, pathParser$PathDataNodeArr3)) {
                    this.mNodeArray = AppOpsManagerCompat.deepCopyNodes(pathParser$PathDataNodeArr3);
                }
                for (int i = 0; i < pathParser$PathDataNodeArr3.length; i++) {
                    PathParser$PathDataNode pathParser$PathDataNode = this.mNodeArray[i];
                    PathParser$PathDataNode pathParser$PathDataNode2 = pathParser$PathDataNodeArr3[i];
                    PathParser$PathDataNode pathParser$PathDataNode3 = pathParser$PathDataNodeArr4[i];
                    Objects.requireNonNull(pathParser$PathDataNode);
                    pathParser$PathDataNode.mType = pathParser$PathDataNode2.mType;
                    int i2 = 0;
                    while (true) {
                        float[] fArr = pathParser$PathDataNode2.mParams;
                        if (i2 < fArr.length) {
                            pathParser$PathDataNode.mParams[i2] = (pathParser$PathDataNode3.mParams[i2] * f) + ((1.0f - f) * fArr[i2]);
                            i2++;
                        }
                    }
                }
                return this.mNodeArray;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:178:0x034d, code lost:
        if (r26 == null) goto L_0x0377;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x034f, code lost:
        if (r13 == null) goto L_0x0377;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0351, code lost:
        r1 = new android.animation.Animator[r13.size()];
        r2 = r13.iterator();
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0360, code lost:
        if (r2.hasNext() == false) goto L_0x036e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0362, code lost:
        r3 = r3 + 1;
        r1[r3] = (android.animation.Animator) r2.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x036e, code lost:
        if (r27 != 0) goto L_0x0374;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0370, code lost:
        r26.playTogether(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0374, code lost:
        r26.playSequentially(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0377, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.animation.Animator createAnimatorFromXml(android.content.Context r21, android.content.res.Resources r22, android.content.res.Resources.Theme r23, org.xmlpull.v1.XmlPullParser r24, android.util.AttributeSet r25, android.animation.AnimatorSet r26, int r27, float r28) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 888
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    public static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f);
        }
        return Keyframe.ofObject(f);
    }

    public static PropertyValuesHolder getPVH(TypedArray typedArray, int i, int i2, int i3, String str) {
        int i4;
        int i5;
        int i6;
        float f;
        float f2;
        float f3;
        PropertyValuesHolder propertyValuesHolder;
        TypedValue peekValue = typedArray.peekValue(i2);
        boolean z = peekValue != null;
        int i7 = z ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i3);
        boolean z2 = peekValue2 != null;
        int i8 = z2 ? peekValue2.type : 0;
        if (i == 4) {
            i = ((!z || !isColorType(i7)) && (!z2 || !isColorType(i8))) ? 0 : 3;
        }
        boolean z3 = i == 0;
        PropertyValuesHolder propertyValuesHolder2 = null;
        if (i == 2) {
            String string = typedArray.getString(i2);
            String string2 = typedArray.getString(i3);
            PathParser$PathDataNode[] createNodesFromPathData = AppOpsManagerCompat.createNodesFromPathData(string);
            PathParser$PathDataNode[] createNodesFromPathData2 = AppOpsManagerCompat.createNodesFromPathData(string2);
            if (createNodesFromPathData == null && createNodesFromPathData2 == null) {
                return null;
            }
            if (createNodesFromPathData != null) {
                PathDataEvaluator pathDataEvaluator = new PathDataEvaluator();
                if (createNodesFromPathData2 == null) {
                    propertyValuesHolder = PropertyValuesHolder.ofObject(str, pathDataEvaluator, createNodesFromPathData);
                } else if (AppOpsManagerCompat.canMorph(createNodesFromPathData, createNodesFromPathData2)) {
                    propertyValuesHolder = PropertyValuesHolder.ofObject(str, pathDataEvaluator, createNodesFromPathData, createNodesFromPathData2);
                } else {
                    throw new InflateException(" Can't morph from " + string + " to " + string2);
                }
                return propertyValuesHolder;
            } else if (createNodesFromPathData2 != null) {
                return PropertyValuesHolder.ofObject(str, new PathDataEvaluator(), createNodesFromPathData2);
            } else {
                return null;
            }
        } else {
            ArgbEvaluator argbEvaluator = i == 3 ? ArgbEvaluator.sInstance : null;
            if (z3) {
                if (z) {
                    if (i7 == 5) {
                        f2 = typedArray.getDimension(i2, 0.0f);
                    } else {
                        f2 = typedArray.getFloat(i2, 0.0f);
                    }
                    if (z2) {
                        if (i8 == 5) {
                            f3 = typedArray.getDimension(i3, 0.0f);
                        } else {
                            f3 = typedArray.getFloat(i3, 0.0f);
                        }
                        propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f2, f3);
                    } else {
                        propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f2);
                    }
                } else {
                    if (i8 == 5) {
                        f = typedArray.getDimension(i3, 0.0f);
                    } else {
                        f = typedArray.getFloat(i3, 0.0f);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str, f);
                }
            } else if (z) {
                if (i7 == 5) {
                    i5 = (int) typedArray.getDimension(i2, 0.0f);
                } else if (isColorType(i7)) {
                    i5 = typedArray.getColor(i2, 0);
                } else {
                    i5 = typedArray.getInt(i2, 0);
                }
                if (z2) {
                    if (i8 == 5) {
                        i6 = (int) typedArray.getDimension(i3, 0.0f);
                    } else if (isColorType(i8)) {
                        i6 = typedArray.getColor(i3, 0);
                    } else {
                        i6 = typedArray.getInt(i3, 0);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i5, i6);
                } else {
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i5);
                }
            } else {
                if (z2) {
                    if (i8 == 5) {
                        i4 = (int) typedArray.getDimension(i3, 0.0f);
                    } else if (isColorType(i8)) {
                        i4 = typedArray.getColor(i3, 0);
                    } else {
                        i4 = typedArray.getInt(i3, 0);
                    }
                    propertyValuesHolder2 = PropertyValuesHolder.ofInt(str, i4);
                }
                if (propertyValuesHolder2 == null && argbEvaluator != null) {
                    propertyValuesHolder2.setEvaluator(argbEvaluator);
                    return propertyValuesHolder2;
                }
            }
            return propertyValuesHolder2 == null ? propertyValuesHolder2 : propertyValuesHolder2;
        }
    }

    public static boolean isColorType(int i) {
        return i >= 28 && i <= 31;
    }

    public static ValueAnimator loadAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        TypedArray typedArray;
        ValueAnimator valueAnimator2;
        TypedArray typedArray2;
        ValueAnimator valueAnimator3;
        PropertyValuesHolder propertyValuesHolder;
        TypedArray obtainAttributes = AppOpsManagerCompat.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray obtainAttributes2 = AppOpsManagerCompat.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        ValueAnimator valueAnimator4 = valueAnimator == null ? new ValueAnimator() : valueAnimator;
        int i = 300;
        if (AppOpsManagerCompat.hasAttribute(xmlPullParser, "duration")) {
            i = obtainAttributes.getInt(1, 300);
        }
        long j = i;
        int i2 = 0;
        long j2 = !AppOpsManagerCompat.hasAttribute(xmlPullParser, "startOffset") ? 0 : obtainAttributes.getInt(2, 0);
        int i3 = !AppOpsManagerCompat.hasAttribute(xmlPullParser, "valueType") ? 4 : obtainAttributes.getInt(7, 4);
        if (AppOpsManagerCompat.hasAttribute(xmlPullParser, "valueFrom") && AppOpsManagerCompat.hasAttribute(xmlPullParser, "valueTo")) {
            if (i3 == 4) {
                TypedValue peekValue = obtainAttributes.peekValue(5);
                boolean z = peekValue != null;
                int i4 = z ? peekValue.type : 0;
                TypedValue peekValue2 = obtainAttributes.peekValue(6);
                boolean z2 = peekValue2 != null;
                i3 = ((!z || !isColorType(i4)) && (!z2 || !isColorType(z2 ? peekValue2.type : 0))) ? 0 : 3;
            }
            PropertyValuesHolder pvh = getPVH(obtainAttributes, i3, 5, 6, HttpUrl.FRAGMENT_ENCODE_SET);
            if (pvh != null) {
                valueAnimator4.setValues(pvh);
            }
        }
        valueAnimator4.setDuration(j);
        valueAnimator4.setStartDelay(j2);
        valueAnimator4.setRepeatCount(!AppOpsManagerCompat.hasAttribute(xmlPullParser, "repeatCount") ? 0 : obtainAttributes.getInt(3, 0));
        valueAnimator4.setRepeatMode(!AppOpsManagerCompat.hasAttribute(xmlPullParser, "repeatMode") ? 1 : obtainAttributes.getInt(4, 1));
        if (obtainAttributes2 != null) {
            ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator4;
            String namedString = AppOpsManagerCompat.getNamedString(obtainAttributes2, xmlPullParser, "pathData", 1);
            if (namedString != null) {
                String namedString2 = AppOpsManagerCompat.getNamedString(obtainAttributes2, xmlPullParser, "propertyXName", 2);
                String namedString3 = AppOpsManagerCompat.getNamedString(obtainAttributes2, xmlPullParser, "propertyYName", 3);
                if (namedString2 == null && namedString3 == null) {
                    throw new InflateException(obtainAttributes2.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                Path createPathFromPathData = AppOpsManagerCompat.createPathFromPathData(namedString);
                float f2 = 0.5f * f;
                PathMeasure pathMeasure = new PathMeasure(createPathFromPathData, false);
                ArrayList arrayList = new ArrayList();
                arrayList.add(Float.valueOf(0.0f));
                float f3 = 0.0f;
                do {
                    f3 += pathMeasure.getLength();
                    arrayList.add(Float.valueOf(f3));
                } while (pathMeasure.nextContour());
                PathMeasure pathMeasure2 = new PathMeasure(createPathFromPathData, false);
                int min = Math.min(100, ((int) (f3 / f2)) + 1);
                float[] fArr = new float[min];
                float[] fArr2 = new float[min];
                float[] fArr3 = new float[2];
                float f4 = f3 / (min - 1);
                valueAnimator2 = valueAnimator4;
                typedArray = obtainAttributes;
                int i5 = 0;
                float f5 = 0.0f;
                while (true) {
                    propertyValuesHolder = null;
                    if (i2 >= min) {
                        break;
                    }
                    pathMeasure2.getPosTan(f5 - ((Float) arrayList.get(i5)).floatValue(), fArr3, null);
                    fArr[i2] = fArr3[0];
                    fArr2[i2] = fArr3[1];
                    f5 += f4;
                    int i6 = i5 + 1;
                    if (i6 < arrayList.size() && f5 > ((Float) arrayList.get(i6)).floatValue()) {
                        pathMeasure2.nextContour();
                        i5 = i6;
                    }
                    i2++;
                    min = min;
                }
                PropertyValuesHolder ofFloat = namedString2 != null ? PropertyValuesHolder.ofFloat(namedString2, fArr) : null;
                if (namedString3 != null) {
                    propertyValuesHolder = PropertyValuesHolder.ofFloat(namedString3, fArr2);
                }
                if (ofFloat == null) {
                    i2 = 0;
                    objectAnimator.setValues(propertyValuesHolder);
                } else {
                    i2 = 0;
                    if (propertyValuesHolder == null) {
                        objectAnimator.setValues(ofFloat);
                    } else {
                        objectAnimator.setValues(ofFloat, propertyValuesHolder);
                    }
                }
            } else {
                valueAnimator2 = valueAnimator4;
                typedArray = obtainAttributes;
                objectAnimator.setPropertyName(AppOpsManagerCompat.getNamedString(obtainAttributes2, xmlPullParser, "propertyName", 0));
            }
        } else {
            valueAnimator2 = valueAnimator4;
            typedArray = obtainAttributes;
        }
        if (!AppOpsManagerCompat.hasAttribute(xmlPullParser, "interpolator")) {
            typedArray2 = typedArray;
        } else {
            typedArray2 = typedArray;
            i2 = typedArray2.getResourceId(i2, i2);
        }
        if (i2 > 0) {
            valueAnimator3 = valueAnimator2;
            valueAnimator3.setInterpolator(AnimationUtils.loadInterpolator(context, i2));
        } else {
            valueAnimator3 = valueAnimator2;
        }
        typedArray2.recycle();
        if (obtainAttributes2 != null) {
            obtainAttributes2.recycle();
        }
        return valueAnimator3;
    }
}

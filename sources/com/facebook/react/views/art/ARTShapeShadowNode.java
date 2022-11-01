package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes.dex */
public class ARTShapeShadowNode extends ARTVirtualNode {
    public float[] mBrushData;
    public Path mPath;
    public float[] mStrokeColor;
    public float[] mStrokeDash;
    public float mStrokeWidth = 1.0f;
    public int mStrokeCap = 1;
    public int mStrokeJoin = 1;

    @Override // com.facebook.react.views.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mPath != null) {
                if (setupFillPaint(paint, f2)) {
                    canvas.drawPath(this.mPath, paint);
                }
                if (setupStrokePaint(paint, f2)) {
                    canvas.drawPath(this.mPath, paint);
                }
                canvas.restore();
            } else {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
        }
        markUpdateSeen();
    }

    @ReactProp(name = "fill")
    public void setFill(ReadableArray readableArray) {
        this.mBrushData = R$style.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(name = "d")
    public void setShapePath(ReadableArray readableArray) {
        float[] floatArray = R$style.toFloatArray(readableArray);
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < floatArray.length) {
            int i2 = i + 1;
            int i3 = (int) floatArray[i];
            if (i3 != 0) {
                boolean z = true;
                if (i3 == 1) {
                    path.close();
                    i = i2;
                } else if (i3 == 2) {
                    int i4 = i2 + 1;
                    float f = floatArray[i2];
                    float f2 = this.mScale;
                    i = i4 + 1;
                    path.lineTo(f * f2, floatArray[i4] * f2);
                } else if (i3 == 3) {
                    int i5 = i2 + 1;
                    float f3 = floatArray[i2];
                    float f4 = this.mScale;
                    int i6 = i5 + 1;
                    float f5 = floatArray[i5] * f4;
                    int i7 = i6 + 1;
                    int i8 = i7 + 1;
                    float f6 = floatArray[i7] * f4;
                    int i9 = i8 + 1;
                    i = i9 + 1;
                    path.cubicTo(f3 * f4, f5, floatArray[i6] * f4, f6, floatArray[i8] * f4, floatArray[i9] * f4);
                } else if (i3 == 4) {
                    int i10 = i2 + 1;
                    float f7 = floatArray[i2];
                    float f8 = this.mScale;
                    float f9 = f7 * f8;
                    int i11 = i10 + 1;
                    float f10 = floatArray[i10] * f8;
                    int i12 = i11 + 1;
                    float f11 = floatArray[i11] * f8;
                    int i13 = i12 + 1;
                    float degrees = (float) Math.toDegrees(floatArray[i12]);
                    int i14 = i13 + 1;
                    float degrees2 = (float) Math.toDegrees(floatArray[i13]);
                    i = i14 + 1;
                    if (floatArray[i14] == 1.0f) {
                        z = false;
                    }
                    float f12 = degrees2 - degrees;
                    if (Math.abs(f12) >= 360.0f) {
                        path.addCircle(f9, f10, f11, z ? Path.Direction.CCW : Path.Direction.CW);
                    } else {
                        float f13 = f12 % 360.0f;
                        if (f13 < 0.0f) {
                            f13 += 360.0f;
                        }
                        if (z && f13 < 360.0f) {
                            f13 = (360.0f - f13) * (-1.0f);
                        }
                        path.arcTo(new RectF(f9 - f11, f10 - f11, f9 + f11, f10 + f11), degrees, f13);
                    }
                } else {
                    throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline3("Unrecognized drawing instruction ", i3));
                }
            } else {
                int i15 = i2 + 1;
                float f14 = floatArray[i2];
                float f15 = this.mScale;
                i = i15 + 1;
                path.moveTo(f14 * f15, floatArray[i15] * f15);
            }
        }
        this.mPath = path;
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(ReadableArray readableArray) {
        this.mStrokeColor = R$style.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int i) {
        this.mStrokeCap = i;
        markUpdated();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(ReadableArray readableArray) {
        this.mStrokeDash = R$style.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int i) {
        this.mStrokeJoin = i;
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float f) {
        this.mStrokeWidth = f;
        markUpdated();
    }

    public boolean setupFillPaint(Paint paint, float f) {
        float[] fArr;
        float[] fArr2 = this.mBrushData;
        int i = 0;
        if (fArr2 == null || fArr2.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        float[] fArr3 = this.mBrushData;
        int i2 = (int) fArr3[0];
        if (i2 != 0) {
            if (i2 != 1) {
                FLog.w("ReactNative", "ART: Color type " + i2 + " not supported!");
            } else {
                int i3 = 5;
                if (fArr3.length < 5) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("[ARTShapeShadowNode setupFillPaint] expects 5 elements, received ");
                    outline13.append(this.mBrushData.length);
                    FLog.w("ReactNative", outline13.toString());
                    return false;
                }
                float f2 = fArr3[1];
                float f3 = this.mScale;
                float f4 = f2 * f3;
                float f5 = fArr3[2] * f3;
                float f6 = fArr3[3] * f3;
                float f7 = fArr3[4] * f3;
                int length = (fArr3.length - 5) / 5;
                int[] iArr = null;
                if (length > 0) {
                    int[] iArr2 = new int[length];
                    float[] fArr4 = new float[length];
                    while (i < length) {
                        float[] fArr5 = this.mBrushData;
                        fArr4[i] = fArr5[(length * 4) + i3 + i];
                        int i4 = (i * 4) + i3;
                        iArr2[i] = Color.argb((int) (fArr5[i4 + 3] * 255.0f), (int) (fArr5[i4 + 0] * 255.0f), (int) (fArr5[i4 + 1] * 255.0f), (int) (fArr5[i4 + 2] * 255.0f));
                        i++;
                        i3 = 5;
                    }
                    iArr = iArr2;
                    fArr = fArr4;
                } else {
                    fArr = null;
                }
                paint.setShader(new LinearGradient(f4, f5, f6, f7, iArr, fArr, Shader.TileMode.CLAMP));
            }
            return true;
        }
        paint.setARGB((int) (fArr3.length > 4 ? fArr3[4] * f * 255.0f : f * 255.0f), (int) (fArr3[1] * 255.0f), (int) (fArr3[2] * 255.0f), (int) (fArr3[3] * 255.0f));
        return true;
    }

    public boolean setupStrokePaint(Paint paint, float f) {
        float[] fArr;
        if (this.mStrokeWidth == 0.0f || (fArr = this.mStrokeColor) == null || fArr.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.STROKE);
        int i = this.mStrokeCap;
        if (i == 0) {
            paint.setStrokeCap(Paint.Cap.BUTT);
        } else if (i == 1) {
            paint.setStrokeCap(Paint.Cap.ROUND);
        } else if (i == 2) {
            paint.setStrokeCap(Paint.Cap.SQUARE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("strokeCap "), this.mStrokeCap, " unrecognized"));
        }
        int i2 = this.mStrokeJoin;
        if (i2 == 0) {
            paint.setStrokeJoin(Paint.Join.MITER);
        } else if (i2 == 1) {
            paint.setStrokeJoin(Paint.Join.ROUND);
        } else if (i2 == 2) {
            paint.setStrokeJoin(Paint.Join.BEVEL);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("strokeJoin "), this.mStrokeJoin, " unrecognized"));
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        float[] fArr2 = this.mStrokeColor;
        paint.setARGB((int) (fArr2.length > 3 ? fArr2[3] * f * 255.0f : f * 255.0f), (int) (fArr2[0] * 255.0f), (int) (fArr2[1] * 255.0f), (int) (fArr2[2] * 255.0f));
        float[] fArr3 = this.mStrokeDash;
        if (fArr3 != null && fArr3.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        return true;
    }
}

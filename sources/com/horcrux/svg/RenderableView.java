package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public abstract class RenderableView extends VirtualView {
    public static RenderableView contextElement;
    public static final Pattern regex = Pattern.compile("[0-9.-]+");
    public ReadableArray fill;
    public ArrayList<String> mAttributeList;
    public ArrayList<String> mLastMergedList;
    public ArrayList<Object> mOriginProperties;
    public ArrayList<String> mPropList;
    public ReadableArray stroke;
    public SVGLength[] strokeDasharray;
    public int vectorEffect = 0;
    public SVGLength strokeWidth = new SVGLength(1.0d);
    public float strokeOpacity = 1.0f;
    public float strokeMiterlimit = 4.0f;
    public float strokeDashoffset = 0.0f;
    public Paint.Cap strokeLinecap = Paint.Cap.BUTT;
    public Paint.Join strokeLinejoin = Paint.Join.MITER;
    public float fillOpacity = 1.0f;
    public Path.FillType fillRule = Path.FillType.WINDING;

    public RenderableView(ReactContext reactContext) {
        super(reactContext);
        setPivotX(0.0f);
        setPivotY(0.0f);
    }

    private ArrayList<String> getAttributeList() {
        return this.mAttributeList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0182, code lost:
        if (com.horcrux.svg.RNSVGMarkerPosition.isZero(r12) != false) goto L_0x01af;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0185, code lost:
        r12 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01ad, code lost:
        if (com.horcrux.svg.RNSVGMarkerPosition.isZero(r12) != false) goto L_0x01af;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01eb  */
    @Override // com.horcrux.svg.VirtualView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r21, android.graphics.Paint r22, float r23) {
        /*
            Method dump skipped, instructions count: 829
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RenderableView.draw(android.graphics.Canvas, android.graphics.Paint, float):void");
    }

    public Region getRegion(Path path, RectF rectF) {
        Region region = new Region();
        region.setPath(path, new Region((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom)));
        return region;
    }

    @Override // com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        Region region;
        Region region2;
        if (((VirtualView) this).mPath == null || !this.mInvertible || !this.mTransformInvertible || ((VirtualView) this).mPointerEvents == PointerEvents.NONE) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        initBounds();
        Region region3 = this.mRegion;
        if ((region3 != null && region3.contains(round, round2)) || ((region = this.mStrokeRegion) != null && (region.contains(round, round2) || ((region2 = this.mMarkerRegion) != null && region2.contains(round, round2))))) {
            if (getClipPath() == null || this.mClipRegion.contains(round, round2)) {
                return getId();
            }
            return -1;
        }
        return -1;
    }

    public void initBounds() {
        if (this.mRegion == null && this.mFillPath != null) {
            RectF rectF = new RectF();
            this.mFillBounds = rectF;
            this.mFillPath.computeBounds(rectF, true);
            this.mRegion = getRegion(this.mFillPath, this.mFillBounds);
        }
        if (this.mRegion == null && ((VirtualView) this).mPath != null) {
            RectF rectF2 = new RectF();
            this.mFillBounds = rectF2;
            ((VirtualView) this).mPath.computeBounds(rectF2, true);
            this.mRegion = getRegion(((VirtualView) this).mPath, this.mFillBounds);
        }
        if (this.mStrokeRegion == null && this.mStrokePath != null) {
            RectF rectF3 = new RectF();
            this.mStrokeBounds = rectF3;
            this.mStrokePath.computeBounds(rectF3, true);
            this.mStrokeRegion = getRegion(this.mStrokePath, this.mStrokeBounds);
        }
        if (this.mMarkerRegion == null && this.mMarkerPath != null) {
            RectF rectF4 = new RectF();
            this.mMarkerBounds = rectF4;
            this.mMarkerPath.computeBounds(rectF4, true);
            this.mMarkerRegion = getRegion(this.mMarkerPath, this.mMarkerBounds);
        }
        Path clipPath = getClipPath();
        if (clipPath != null && this.mClipRegionPath != clipPath) {
            this.mClipRegionPath = clipPath;
            RectF rectF5 = new RectF();
            this.mClipBounds = rectF5;
            clipPath.computeBounds(rectF5, true);
            this.mClipRegion = getRegion(clipPath, this.mClipBounds);
        }
    }

    public void mergeProperties(RenderableView renderableView) {
        ArrayList<String> attributeList = renderableView.getAttributeList();
        if (!(attributeList == null || attributeList.size() == 0)) {
            this.mOriginProperties = new ArrayList<>();
            this.mAttributeList = this.mPropList == null ? new ArrayList<>() : new ArrayList<>(this.mPropList);
            int size = attributeList.size();
            for (int i = 0; i < size; i++) {
                try {
                    String str = attributeList.get(i);
                    Field field = getClass().getField(str);
                    Object obj = field.get(renderableView);
                    this.mOriginProperties.add(field.get(this));
                    ArrayList<String> arrayList = this.mAttributeList;
                    if (!(arrayList != null && arrayList.contains(str))) {
                        this.mAttributeList.add(str);
                        field.set(this, obj);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            this.mLastMergedList = attributeList;
        }
    }

    @Override // com.horcrux.svg.VirtualView
    public void render(Canvas canvas, Paint paint, float f) {
        MaskView maskView;
        if (this.mMask != null) {
            maskView = (MaskView) getSvgView().mDefinedMasks.get(this.mMask);
        } else {
            maskView = null;
        }
        if (maskView != null) {
            Rect clipBounds = canvas.getClipBounds();
            int height = clipBounds.height();
            int width = clipBounds.width();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Bitmap createBitmap3 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            Canvas canvas3 = new Canvas(createBitmap);
            Canvas canvas4 = new Canvas(createBitmap3);
            canvas3.clipRect((float) relativeOnWidth(maskView.mX), (float) relativeOnHeight(maskView.mY), (float) relativeOnWidth(maskView.mW), (float) relativeOnHeight(maskView.mH));
            Paint paint2 = new Paint(1);
            maskView.setupGlyphContext(canvas3);
            Path clipPath = maskView.getClipPath(canvas3, paint2);
            if (clipPath != null) {
                canvas3.clipPath(clipPath);
            }
            maskView.drawGroup(canvas3, paint2, 1.0f);
            int i = width * height;
            int[] iArr = new int[i];
            createBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i2 = 0;
            while (i2 < i) {
                int i3 = iArr[i2];
                int i4 = i3 & 255;
                int i5 = i3 >>> 24;
                double d = ((i4 * 0.144d) + ((((i3 >> 8) & 255) * 0.587d) + (((i3 >> 16) & 255) * 0.299d))) / 255.0d;
                if (d <= 0.0d) {
                    d = 0.0d;
                } else if (d >= 1.0d) {
                    d = 1.0d;
                }
                iArr[i2] = ((int) (i5 * d)) << 24;
                i2++;
                i = i;
                paint2 = paint2;
            }
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            draw(canvas2, paint, f);
            paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas4.drawBitmap(createBitmap2, 0.0f, 0.0f, (Paint) null);
            canvas4.drawBitmap(createBitmap, 0.0f, 0.0f, paint2);
            canvas.drawBitmap(createBitmap3, 0.0f, 0.0f, paint);
            return;
        }
        draw(canvas, paint, f);
    }

    public void resetProperties() {
        ArrayList<String> arrayList = this.mLastMergedList;
        if (arrayList != null && this.mOriginProperties != null) {
            try {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    getClass().getField(this.mLastMergedList.get(size)).set(this, this.mOriginProperties.get(size));
                }
                this.mLastMergedList = null;
                this.mOriginProperties = null;
                this.mAttributeList = this.mPropList;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @ReactProp(name = "fill")
    public void setFill(Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.fill = null;
            invalidate();
            return;
        }
        ReadableType type = dynamic.getType();
        int i = 0;
        if (type.equals(ReadableType.Number)) {
            this.fill = JavaOnlyArray.of(0, Integer.valueOf(dynamic.asInt()));
        } else if (type.equals(ReadableType.Array)) {
            this.fill = dynamic.asArray();
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            javaOnlyArray.pushInt(0);
            Matcher matcher = regex.matcher(dynamic.asString());
            while (matcher.find()) {
                double parseDouble = Double.parseDouble(matcher.group());
                i++;
                if (i < 3) {
                    parseDouble /= 255.0d;
                }
                javaOnlyArray.pushDouble(parseDouble);
            }
            this.fill = javaOnlyArray;
        }
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(float f) {
        this.fillOpacity = f;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(int i) {
        if (i == 0) {
            this.fillRule = Path.FillType.EVEN_ODD;
        } else if (i != 1) {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("fillRule ", i, " unrecognized"));
        }
        invalidate();
    }

    @Override // android.view.View
    public void setId(int i) {
        super.setId(i);
        RenderableViewManager.setRenderableView(i, this);
    }

    @ReactProp(name = "propList")
    public void setPropList(ReadableArray readableArray) {
        if (readableArray != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mAttributeList = arrayList;
            this.mPropList = arrayList;
            for (int i = 0; i < readableArray.size(); i++) {
                this.mPropList.add(readableArray.getString(i));
            }
        }
        invalidate();
    }

    @ReactProp(name = "stroke")
    public void setStroke(Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.stroke = null;
            invalidate();
            return;
        }
        ReadableType type = dynamic.getType();
        int i = 0;
        if (type.equals(ReadableType.Number)) {
            this.stroke = JavaOnlyArray.of(0, Integer.valueOf(dynamic.asInt()));
        } else if (type.equals(ReadableType.Array)) {
            this.stroke = dynamic.asArray();
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            javaOnlyArray.pushInt(0);
            Matcher matcher = regex.matcher(dynamic.asString());
            while (matcher.find()) {
                double parseDouble = Double.parseDouble(matcher.group());
                i++;
                if (i < 3) {
                    parseDouble /= 255.0d;
                }
                javaOnlyArray.pushDouble(parseDouble);
            }
            this.stroke = javaOnlyArray;
        }
        invalidate();
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(ReadableArray readableArray) {
        if (readableArray != null) {
            int size = readableArray.size();
            this.strokeDasharray = new SVGLength[size];
            for (int i = 0; i < size; i++) {
                this.strokeDasharray[i] = SVGLength.from(readableArray.getDynamic(i));
            }
        } else {
            this.strokeDasharray = null;
        }
        invalidate();
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(float f) {
        this.strokeDashoffset = f * this.mScale;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(int i) {
        if (i == 0) {
            this.strokeLinecap = Paint.Cap.BUTT;
        } else if (i == 1) {
            this.strokeLinecap = Paint.Cap.ROUND;
        } else if (i == 2) {
            this.strokeLinecap = Paint.Cap.SQUARE;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("strokeLinecap ", i, " unrecognized"));
        }
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(int i) {
        if (i == 0) {
            this.strokeLinejoin = Paint.Join.MITER;
        } else if (i == 1) {
            this.strokeLinejoin = Paint.Join.ROUND;
        } else if (i == 2) {
            this.strokeLinejoin = Paint.Join.BEVEL;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline4("strokeLinejoin ", i, " unrecognized"));
        }
        invalidate();
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(float f) {
        this.strokeMiterlimit = f;
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(float f) {
        this.strokeOpacity = f;
        invalidate();
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(Dynamic dynamic) {
        this.strokeWidth = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(int i) {
        this.vectorEffect = i;
        invalidate();
    }

    public boolean setupFillPaint(Paint paint, float f) {
        ReadableArray readableArray = this.fill;
        if (readableArray == null || readableArray.size() <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(385);
        paint.setStyle(Paint.Style.FILL);
        setupPaint(paint, f, this.fill);
        return true;
    }

    public final void setupPaint(Paint paint, float f, ReadableArray readableArray) {
        int i;
        float f2;
        float f3;
        float[] fArr;
        char c;
        int[] iArr;
        int i2;
        Brush brush;
        Canvas canvas;
        ReadableArray readableArray2;
        RenderableView renderableView;
        ReadableArray readableArray3;
        int i3 = readableArray.getInt(0);
        if (i3 != 0) {
            if (i3 == 1) {
                Brush brush2 = getSvgView().mDefinedBrushes.get(readableArray.getString(1));
                if (brush2 != null) {
                    RectF rectF = this.mBox;
                    float f4 = this.mScale;
                    if (!brush2.mUseObjectBoundingBox) {
                        rectF = new RectF(brush2.mUserSpaceBoundingBox);
                    }
                    float width = rectF.width();
                    float height = rectF.height();
                    if (brush2.mUseObjectBoundingBox) {
                        f2 = rectF.left;
                        f3 = rectF.top;
                    } else {
                        f3 = 0.0f;
                        f2 = 0.0f;
                    }
                    RectF rectF2 = new RectF(f2, f3, width + f2, height + f3);
                    float width2 = rectF2.width();
                    float height2 = rectF2.height();
                    float f5 = rectF2.left;
                    float f6 = rectF2.top;
                    float textSize = paint.getTextSize();
                    if (brush2.mType == 3) {
                        double d = width2;
                        double val = brush2.getVal(brush2.mPoints[0], d, f4, textSize);
                        double d2 = height2;
                        double val2 = brush2.getVal(brush2.mPoints[1], d2, f4, textSize);
                        double val3 = brush2.getVal(brush2.mPoints[2], d, f4, textSize);
                        double val4 = brush2.getVal(brush2.mPoints[3], d2, f4, textSize);
                        if (val3 > 1.0d && val4 > 1.0d) {
                            Bitmap createBitmap = Bitmap.createBitmap((int) val3, (int) val4, Bitmap.Config.ARGB_8888);
                            Canvas canvas2 = new Canvas(createBitmap);
                            PatternView patternView = brush2.mPattern;
                            Objects.requireNonNull(patternView);
                            float f7 = patternView.mMinX;
                            float f8 = patternView.mScale;
                            float f9 = patternView.mMinY;
                            RectF rectF3 = new RectF(f7 * f8, f9 * f8, (f7 + patternView.mVbWidth) * f8, (f9 + patternView.mVbHeight) * f8);
                            if (rectF3.width() <= 0.0f || rectF3.height() <= 0.0f) {
                                canvas = canvas2;
                                brush = brush2;
                            } else {
                                RectF rectF4 = new RectF((float) val, (float) val2, (float) val3, (float) val4);
                                brush = brush2;
                                PatternView patternView2 = brush.mPattern;
                                canvas = canvas2;
                                canvas.concat(PathParser.getTransform(rectF3, rectF4, patternView2.mAlign, patternView2.mMeetOrSlice));
                            }
                            if (brush.mUseContentObjectBoundingBoxUnits) {
                                canvas.scale(width2 / f4, height2 / f4);
                            }
                            PatternView patternView3 = brush.mPattern;
                            Paint paint2 = new Paint();
                            patternView3.setupGlyphContext(canvas);
                            Path clipPath = patternView3.getClipPath(canvas, paint2);
                            if (clipPath != null) {
                                canvas.clipPath(clipPath);
                            }
                            patternView3.drawGroup(canvas, paint2, f);
                            Matrix matrix = new Matrix();
                            Matrix matrix2 = brush.mMatrix;
                            if (matrix2 != null) {
                                matrix.preConcat(matrix2);
                            }
                            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
                            BitmapShader bitmapShader = new BitmapShader(createBitmap, tileMode, tileMode);
                            bitmapShader.setLocalMatrix(matrix);
                            paint.setShader(bitmapShader);
                            return;
                        }
                        return;
                    }
                    int size = brush2.mColors.size();
                    if (size == 0) {
                        FLog.w("ReactNative", "Gradient contains no stops");
                        return;
                    }
                    int i4 = size / 2;
                    int[] iArr2 = new int[i4];
                    float[] fArr2 = new float[i4];
                    ReadableArray readableArray4 = brush2.mColors;
                    for (int i5 = 0; i5 < i4; i5++) {
                        int i6 = i5 * 2;
                        fArr2[i5] = (float) readableArray4.getDouble(i6);
                        iArr2[i5] = (readableArray4.getInt(i6 + 1) & 16777215) | (Math.round((i2 >>> 24) * f) << 24);
                    }
                    if (i4 == 1) {
                        c = 0;
                        iArr = new int[]{iArr2[0], iArr2[0]};
                        fArr = new float[]{fArr2[0], fArr2[0]};
                        FLog.w("ReactNative", "Gradient contains only one stop");
                    } else {
                        c = 0;
                        iArr = iArr2;
                        fArr = fArr2;
                    }
                    int i7 = brush2.mType;
                    if (i7 == 1) {
                        double d3 = width2;
                        double d4 = f5;
                        double val5 = brush2.getVal(brush2.mPoints[c], d3, f4, textSize) + d4;
                        double d5 = height2;
                        double d6 = f6;
                        Shader linearGradient = new LinearGradient((float) val5, (float) (brush2.getVal(brush2.mPoints[1], d5, f4, textSize) + d6), (float) (d4 + brush2.getVal(brush2.mPoints[2], d3, f4, textSize)), (float) (brush2.getVal(brush2.mPoints[3], d5, f4, textSize) + d6), iArr, fArr, Shader.TileMode.CLAMP);
                        if (brush2.mMatrix != null) {
                            Matrix matrix3 = new Matrix();
                            matrix3.preConcat(brush2.mMatrix);
                            linearGradient.setLocalMatrix(matrix3);
                        }
                        paint.setShader(linearGradient);
                    } else if (i7 == 2) {
                        double d7 = width2;
                        double val6 = brush2.getVal(brush2.mPoints[2], d7, f4, textSize);
                        double d8 = height2;
                        double val7 = brush2.getVal(brush2.mPoints[3], d8, f4, textSize) / val6;
                        Shader radialGradient = new RadialGradient((float) (brush2.getVal(brush2.mPoints[4], d7, f4, textSize) + f5), (float) ((f6 / val7) + brush2.getVal(brush2.mPoints[5], d8 / val7, f4, textSize)), (float) val6, iArr, fArr, Shader.TileMode.CLAMP);
                        Matrix matrix4 = new Matrix();
                        matrix4.preScale(1.0f, (float) val7);
                        Matrix matrix5 = brush2.mMatrix;
                        if (matrix5 != null) {
                            matrix4.preConcat(matrix5);
                        }
                        radialGradient.setLocalMatrix(matrix4);
                        paint.setShader(radialGradient);
                    }
                }
            } else if (i3 == 2) {
                paint.setColor(getSvgView().mTintColor);
            } else if (i3 == 3) {
                RenderableView renderableView2 = contextElement;
                if (!(renderableView2 == null || (readableArray2 = renderableView2.fill) == null)) {
                    setupPaint(paint, f, readableArray2);
                }
            } else if (i3 == 4 && (renderableView = contextElement) != null && (readableArray3 = renderableView.stroke) != null) {
                setupPaint(paint, f, readableArray3);
            }
        } else if (readableArray.size() == 2) {
            paint.setColor((readableArray.getInt(1) & 16777215) | (Math.round((i >>> 24) * f) << 24));
        } else {
            paint.setARGB((int) (readableArray.size() > 4 ? readableArray.getDouble(4) * f * 255.0d : 255.0f * f), (int) (readableArray.getDouble(1) * 255.0d), (int) (readableArray.getDouble(2) * 255.0d), (int) (readableArray.getDouble(3) * 255.0d));
        }
    }

    public boolean setupStrokePaint(Paint paint, float f) {
        ReadableArray readableArray;
        paint.reset();
        double relativeOnOther = relativeOnOther(this.strokeWidth);
        if (relativeOnOther == 0.0d || (readableArray = this.stroke) == null || readableArray.size() == 0) {
            return false;
        }
        paint.setFlags(385);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.strokeLinecap);
        paint.setStrokeJoin(this.strokeLinejoin);
        paint.setStrokeMiter(this.strokeMiterlimit * this.mScale);
        paint.setStrokeWidth((float) relativeOnOther);
        setupPaint(paint, f, this.stroke);
        SVGLength[] sVGLengthArr = this.strokeDasharray;
        if (sVGLengthArr == null) {
            return true;
        }
        int length = sVGLengthArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) relativeOnOther(this.strokeDasharray[i]);
        }
        paint.setPathEffect(new DashPathEffect(fArr, this.strokeDashoffset));
        return true;
    }
}

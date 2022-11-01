package com.google.android.material.shape;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.graphics.drawable.TintAwareDrawable;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.util.BitSet;
import java.util.Objects;
/* loaded from: classes.dex */
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    public static final String TAG = MaterialShapeDrawable.class.getSimpleName();
    public static final Paint clearPaint = new Paint(1);
    public final BitSet containsIncompatibleShadowOp;
    public final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    public MaterialShapeDrawableState drawableState;
    public final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    public final Paint fillPaint;
    public final RectF insetRectF;
    public final Matrix matrix;
    public final Path path;
    public final RectF pathBounds;
    public boolean pathDirty;
    public final Path pathInsetByStroke;
    public final ShapeAppearancePathProvider pathProvider;
    public final ShapeAppearancePathProvider.PathListener pathShadowListener;
    public final RectF rectF;
    public final Region scratchRegion;
    public boolean shadowBitmapDrawingEnable;
    public final ShadowRenderer shadowRenderer;
    public final Paint strokePaint;
    public ShapeAppearanceModel strokeShapeAppearance;
    public PorterDuffColorFilter strokeTintFilter;
    public PorterDuffColorFilter tintFilter;
    public final Region transparentRegion;

    /* renamed from: com.google.android.material.shape.MaterialShapeDrawable$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements ShapeAppearancePathProvider.PathListener {
        public AnonymousClass1() {
        }
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public final void calculatePath(RectF rectF, Path path) {
        calculatePathForSize(rectF, path);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            Matrix matrix = this.matrix;
            float f = this.drawableState.scale;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.matrix);
        }
        path.computeBounds(this.pathBounds, true);
    }

    public final void calculatePathForSize(RectF rectF, Path path) {
        ShapeAppearancePathProvider shapeAppearancePathProvider = this.pathProvider;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        shapeAppearancePathProvider.calculatePath(materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolation, rectF, this.pathShadowListener, path);
    }

    public final PorterDuffColorFilter calculateTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        int color;
        int compositeElevationOverlayIfNeeded;
        if (colorStateList == null || mode == null) {
            return (!z || (compositeElevationOverlayIfNeeded = compositeElevationOverlayIfNeeded((color = paint.getColor()))) == color) ? null : new PorterDuffColorFilter(compositeElevationOverlayIfNeeded, PorterDuff.Mode.SRC_IN);
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = compositeElevationOverlayIfNeeded(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public int compositeElevationOverlayIfNeeded(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + materialShapeDrawableState.translationZ + materialShapeDrawableState.parentAbsoluteElevation;
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
        return elevationOverlayProvider != null ? elevationOverlayProvider.compositeOverlayIfNeeded(i, f) : i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00eb, code lost:
        if ((!isRoundRect() && !r12.path.isConvex() && android.os.Build.VERSION.SDK_INT < 29) != false) goto L_0x00ed;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01af  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r13) {
        /*
            Method dump skipped, instructions count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.shape.MaterialShapeDrawable.draw(android.graphics.Canvas):void");
    }

    public final void drawCompatShadow(Canvas canvas) {
        if (this.containsIncompatibleShadowOp.cardinality() > 0) {
            Log.w(TAG, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.drawableState.shadowCompatOffset != 0) {
            canvas.drawPath(this.path, this.shadowRenderer.shadowPaint);
        }
        for (int i = 0; i < 4; i++) {
            ShapePath.ShadowCompatOperation shadowCompatOperation = this.cornerShadowOperation[i];
            ShadowRenderer shadowRenderer = this.shadowRenderer;
            int i2 = this.drawableState.shadowCompatRadius;
            Matrix matrix = ShapePath.ShadowCompatOperation.IDENTITY_MATRIX;
            shadowCompatOperation.draw(matrix, shadowRenderer, i2, canvas);
            this.edgeShadowOperation[i].draw(matrix, this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
        }
        if (this.shadowBitmapDrawingEnable) {
            int shadowOffsetX = getShadowOffsetX();
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate(-shadowOffsetX, -shadowOffsetY);
            canvas.drawPath(this.path, clearPaint);
            canvas.translate(shadowOffsetX, shadowOffsetY);
        }
    }

    public final void drawShape(Canvas canvas, Paint paint, Path path, ShapeAppearanceModel shapeAppearanceModel, RectF rectF) {
        if (shapeAppearanceModel.isRoundRect(rectF)) {
            float cornerSize = shapeAppearanceModel.topRightCornerSize.getCornerSize(rectF) * this.drawableState.interpolation;
            canvas.drawRoundRect(rectF, cornerSize, cornerSize, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    public RectF getBoundsAsRectF() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.drawableState.shadowCompatMode != 2) {
            if (isRoundRect()) {
                outline.setRoundRect(getBounds(), getTopLeftCornerResolvedSize() * this.drawableState.interpolation);
                return;
            }
            calculatePath(getBoundsAsRectF(), this.path);
            if (this.path.isConvex() || Build.VERSION.SDK_INT >= 29) {
                try {
                    outline.setConvexPath(this.path);
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.drawableState.padding;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public int getShadowOffsetX() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        return (int) (Math.sin(Math.toRadians(materialShapeDrawableState.shadowCompatRotation)) * materialShapeDrawableState.shadowCompatOffset);
    }

    public int getShadowOffsetY() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        return (int) (Math.cos(Math.toRadians(materialShapeDrawableState.shadowCompatRotation)) * materialShapeDrawableState.shadowCompatOffset);
    }

    public final float getStrokeInsetLength() {
        if (hasStroke()) {
            return this.strokePaint.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public float getTopLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.topLeftCornerSize.getCornerSize(getBoundsAsRectF());
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public final boolean hasStroke() {
        Paint.Style style = this.drawableState.paintStyle;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f;
    }

    public void initializeElevationOverlay(Context context) {
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    public boolean isRoundRect() {
        return this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.drawableState.tintList) != null && colorStateList.isStateful()) || (((colorStateList2 = this.drawableState.strokeTintList) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.drawableState.strokeColor) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.drawableState.fillColor) != null && colorStateList4.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.pathDirty = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        boolean z = updateColorsForState(iArr) || updateTintFilter();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.alpha != i) {
            materialShapeDrawableState.alpha = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.drawableState.colorFilter = colorFilter;
        super.invalidateSelf();
    }

    public void setElevation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.elevation != f) {
            materialShapeDrawableState.elevation = f;
            updateZ();
        }
    }

    public void setFillColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.fillColor != colorStateList) {
            materialShapeDrawableState.fillColor = colorStateList;
            onStateChange(getState());
        }
    }

    public void setInterpolation(float f) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.interpolation != f) {
            materialShapeDrawableState.interpolation = f;
            this.pathDirty = true;
            invalidateSelf();
        }
    }

    public void setShadowColor(int i) {
        this.shadowRenderer.setShadowColor(i);
        this.drawableState.useTintColorForShadow = false;
        super.invalidateSelf();
    }

    @Override // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        invalidateSelf();
    }

    public void setStroke(float f, int i) {
        this.drawableState.strokeWidth = f;
        invalidateSelf();
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.strokeColor != colorStateList) {
            materialShapeDrawableState.strokeColor = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.drawableState.tintList = colorStateList;
        updateTintFilter();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        if (materialShapeDrawableState.tintMode != mode) {
            materialShapeDrawableState.tintMode = mode;
            updateTintFilter();
            super.invalidateSelf();
        }
    }

    public final boolean updateColorsForState(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.drawableState.fillColor == null || color2 == (colorForState2 = this.drawableState.fillColor.getColorForState(iArr, (color2 = this.fillPaint.getColor())))) {
            z = false;
        } else {
            this.fillPaint.setColor(colorForState2);
            z = true;
        }
        if (this.drawableState.strokeColor == null || color == (colorForState = this.drawableState.strokeColor.getColorForState(iArr, (color = this.strokePaint.getColor())))) {
            return z;
        }
        this.strokePaint.setColor(colorForState);
        return true;
    }

    public final boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.strokeTintFilter;
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        this.tintFilter = calculateTintFilter(materialShapeDrawableState.tintList, materialShapeDrawableState.tintMode, this.fillPaint, true);
        MaterialShapeDrawableState materialShapeDrawableState2 = this.drawableState;
        this.strokeTintFilter = calculateTintFilter(materialShapeDrawableState2.strokeTintList, materialShapeDrawableState2.tintMode, this.strokePaint, false);
        MaterialShapeDrawableState materialShapeDrawableState3 = this.drawableState;
        if (materialShapeDrawableState3.useTintColorForShadow) {
            this.shadowRenderer.setShadowColor(materialShapeDrawableState3.tintList.getColorForState(getState(), 0));
        }
        return !Objects.equals(porterDuffColorFilter, this.tintFilter) || !Objects.equals(porterDuffColorFilter2, this.strokeTintFilter);
    }

    public final void updateZ() {
        MaterialShapeDrawableState materialShapeDrawableState = this.drawableState;
        float f = materialShapeDrawableState.elevation + materialShapeDrawableState.translationZ;
        materialShapeDrawableState.shadowCompatRadius = (int) Math.ceil(0.75f * f);
        this.drawableState.shadowCompatOffset = (int) Math.ceil(f * 0.25f);
        updateTintFilter();
        super.invalidateSelf();
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel, null));
    }

    public MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawableState) {
        ShapeAppearancePathProvider shapeAppearancePathProvider;
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint = new Paint(1);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        this.strokePaint = paint2;
        this.shadowRenderer = new ShadowRenderer();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            shapeAppearancePathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
        } else {
            shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        }
        this.pathProvider = shapeAppearancePathProvider;
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.drawableState = materialShapeDrawableState;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = clearPaint;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new AnonymousClass1();
    }

    public void setStroke(float f, ColorStateList colorStateList) {
        this.drawableState.strokeWidth = f;
        invalidateSelf();
        setStrokeColor(colorStateList);
    }

    /* loaded from: classes.dex */
    public static final class MaterialShapeDrawableState extends Drawable.ConstantState {
        public int alpha;
        public ColorFilter colorFilter;
        public float elevation;
        public ElevationOverlayProvider elevationOverlayProvider;
        public ColorStateList fillColor;
        public float interpolation;
        public Rect padding;
        public Paint.Style paintStyle;
        public float parentAbsoluteElevation;
        public float scale;
        public int shadowCompatMode;
        public int shadowCompatOffset;
        public int shadowCompatRadius;
        public int shadowCompatRotation;
        public ShapeAppearanceModel shapeAppearanceModel;
        public ColorStateList strokeColor;
        public ColorStateList strokeTintList;
        public float strokeWidth;
        public ColorStateList tintList;
        public PorterDuff.Mode tintMode;
        public float translationZ;
        public boolean useTintColorForShadow;

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel, ElevationOverlayProvider elevationOverlayProvider) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = 255;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearanceModel = shapeAppearanceModel;
            this.elevationOverlayProvider = null;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this);
            materialShapeDrawable.pathDirty = true;
            return materialShapeDrawable;
        }

        public MaterialShapeDrawableState(MaterialShapeDrawableState materialShapeDrawableState) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = 255;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearanceModel = materialShapeDrawableState.shapeAppearanceModel;
            this.elevationOverlayProvider = materialShapeDrawableState.elevationOverlayProvider;
            this.strokeWidth = materialShapeDrawableState.strokeWidth;
            this.colorFilter = materialShapeDrawableState.colorFilter;
            this.fillColor = materialShapeDrawableState.fillColor;
            this.strokeColor = materialShapeDrawableState.strokeColor;
            this.tintMode = materialShapeDrawableState.tintMode;
            this.tintList = materialShapeDrawableState.tintList;
            this.alpha = materialShapeDrawableState.alpha;
            this.scale = materialShapeDrawableState.scale;
            this.shadowCompatOffset = materialShapeDrawableState.shadowCompatOffset;
            this.shadowCompatMode = materialShapeDrawableState.shadowCompatMode;
            this.useTintColorForShadow = materialShapeDrawableState.useTintColorForShadow;
            this.interpolation = materialShapeDrawableState.interpolation;
            this.parentAbsoluteElevation = materialShapeDrawableState.parentAbsoluteElevation;
            this.elevation = materialShapeDrawableState.elevation;
            this.translationZ = materialShapeDrawableState.translationZ;
            this.shadowCompatRadius = materialShapeDrawableState.shadowCompatRadius;
            this.shadowCompatRotation = materialShapeDrawableState.shadowCompatRotation;
            this.strokeTintList = materialShapeDrawableState.strokeTintList;
            this.paintStyle = materialShapeDrawableState.paintStyle;
            if (materialShapeDrawableState.padding != null) {
                this.padding = new Rect(materialShapeDrawableState.padding);
            }
        }
    }

    public MaterialShapeDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        this(ShapeAppearanceModel.builder(context, attributeSet, i, i2, new AbsoluteCornerSize(0)).build());
    }
}

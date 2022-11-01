package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import java.util.Objects;
/* loaded from: classes.dex */
public class ShapeAppearanceModel {
    public static final CornerSize PILL = new RelativeCornerSize(0.5f);
    public EdgeTreatment bottomEdge;
    public CornerTreatment bottomLeftCorner;
    public CornerSize bottomLeftCornerSize;
    public CornerTreatment bottomRightCorner;
    public CornerSize bottomRightCornerSize;
    public EdgeTreatment leftEdge;
    public EdgeTreatment rightEdge;
    public EdgeTreatment topEdge;
    public CornerTreatment topLeftCorner;
    public CornerSize topLeftCornerSize;
    public CornerTreatment topRightCorner;
    public CornerSize topRightCornerSize;

    public ShapeAppearanceModel(Builder builder, AnonymousClass1 r2) {
        this.topLeftCorner = builder.topLeftCorner;
        this.topRightCorner = builder.topRightCorner;
        this.bottomRightCorner = builder.bottomRightCorner;
        this.bottomLeftCorner = builder.bottomLeftCorner;
        this.topLeftCornerSize = builder.topLeftCornerSize;
        this.topRightCornerSize = builder.topRightCornerSize;
        this.bottomRightCornerSize = builder.bottomRightCornerSize;
        this.bottomLeftCornerSize = builder.bottomLeftCornerSize;
        this.topEdge = builder.topEdge;
        this.rightEdge = builder.rightEdge;
        this.bottomEdge = builder.bottomEdge;
        this.leftEdge = builder.leftEdge;
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2, CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        return builder(context, resourceId, resourceId2, cornerSize);
    }

    public static CornerSize getCornerSize(TypedArray typedArray, int i, CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return cornerSize;
        }
        int i2 = peekValue.type;
        if (i2 == 5) {
            return new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics()));
        }
        return i2 == 6 ? new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f)) : cornerSize;
    }

    public boolean isRoundRect(RectF rectF) {
        boolean z = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.topLeftCornerSize.getCornerSize(rectF);
        return z && ((this.topRightCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.topRightCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.bottomLeftCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.bottomLeftCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.bottomRightCornerSize.getCornerSize(rectF) > cornerSize ? 1 : (this.bottomRightCornerSize.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0) && ((this.topRightCorner instanceof RoundedCornerTreatment) && (this.topLeftCorner instanceof RoundedCornerTreatment) && (this.bottomRightCorner instanceof RoundedCornerTreatment) && (this.bottomLeftCorner instanceof RoundedCornerTreatment));
    }

    public ShapeAppearanceModel withCornerSize(float f) {
        Builder builder = new Builder(this);
        builder.setAllCornerSizes(f);
        return builder.build();
    }

    public static Builder builder(Context context, int i, int i2, CornerSize cornerSize) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.ShapeAppearance);
        try {
            int i3 = obtainStyledAttributes.getInt(0, 0);
            int i4 = obtainStyledAttributes.getInt(3, i3);
            int i5 = obtainStyledAttributes.getInt(4, i3);
            int i6 = obtainStyledAttributes.getInt(2, i3);
            int i7 = obtainStyledAttributes.getInt(1, i3);
            CornerSize cornerSize2 = getCornerSize(obtainStyledAttributes, 5, cornerSize);
            CornerSize cornerSize3 = getCornerSize(obtainStyledAttributes, 8, cornerSize2);
            CornerSize cornerSize4 = getCornerSize(obtainStyledAttributes, 9, cornerSize2);
            CornerSize cornerSize5 = getCornerSize(obtainStyledAttributes, 7, cornerSize2);
            CornerSize cornerSize6 = getCornerSize(obtainStyledAttributes, 6, cornerSize2);
            Builder builder = new Builder();
            CornerTreatment createCornerTreatment = R$style.createCornerTreatment(i4);
            builder.topLeftCorner = createCornerTreatment;
            Builder.compatCornerTreatmentSize(createCornerTreatment);
            builder.topLeftCornerSize = cornerSize3;
            CornerTreatment createCornerTreatment2 = R$style.createCornerTreatment(i5);
            builder.topRightCorner = createCornerTreatment2;
            Builder.compatCornerTreatmentSize(createCornerTreatment2);
            builder.topRightCornerSize = cornerSize4;
            CornerTreatment createCornerTreatment3 = R$style.createCornerTreatment(i6);
            builder.bottomRightCorner = createCornerTreatment3;
            Builder.compatCornerTreatmentSize(createCornerTreatment3);
            builder.bottomRightCornerSize = cornerSize5;
            CornerTreatment createCornerTreatment4 = R$style.createCornerTreatment(i7);
            builder.bottomLeftCorner = createCornerTreatment4;
            Builder.compatCornerTreatmentSize(createCornerTreatment4);
            builder.bottomLeftCornerSize = cornerSize6;
            return builder;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        public EdgeTreatment bottomEdge;
        public CornerTreatment bottomLeftCorner;
        public CornerSize bottomLeftCornerSize;
        public CornerTreatment bottomRightCorner;
        public CornerSize bottomRightCornerSize;
        public EdgeTreatment leftEdge;
        public EdgeTreatment rightEdge;
        public EdgeTreatment topEdge;
        public CornerTreatment topLeftCorner;
        public CornerSize topLeftCornerSize;
        public CornerTreatment topRightCorner;
        public CornerSize topRightCornerSize;

        public Builder() {
            this.topLeftCorner = new RoundedCornerTreatment();
            this.topRightCorner = new RoundedCornerTreatment();
            this.bottomRightCorner = new RoundedCornerTreatment();
            this.bottomLeftCorner = new RoundedCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = new EdgeTreatment();
            this.rightEdge = new EdgeTreatment();
            this.bottomEdge = new EdgeTreatment();
            this.leftEdge = new EdgeTreatment();
        }

        public static float compatCornerTreatmentSize(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                Objects.requireNonNull((RoundedCornerTreatment) cornerTreatment);
                return -1.0f;
            }
            if (cornerTreatment instanceof CutCornerTreatment) {
                Objects.requireNonNull((CutCornerTreatment) cornerTreatment);
            }
            return -1.0f;
        }

        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this, null);
        }

        public Builder setAllCornerSizes(float f) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f);
            this.topRightCornerSize = new AbsoluteCornerSize(f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setBottomLeftCornerSize(float f) {
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setBottomRightCornerSize(float f) {
            this.bottomRightCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setTopLeftCornerSize(float f) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder setTopRightCornerSize(float f) {
            this.topRightCornerSize = new AbsoluteCornerSize(f);
            return this;
        }

        public Builder(ShapeAppearanceModel shapeAppearanceModel) {
            this.topLeftCorner = new RoundedCornerTreatment();
            this.topRightCorner = new RoundedCornerTreatment();
            this.bottomRightCorner = new RoundedCornerTreatment();
            this.bottomLeftCorner = new RoundedCornerTreatment();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = new EdgeTreatment();
            this.rightEdge = new EdgeTreatment();
            this.bottomEdge = new EdgeTreatment();
            this.leftEdge = new EdgeTreatment();
            this.topLeftCorner = shapeAppearanceModel.topLeftCorner;
            this.topRightCorner = shapeAppearanceModel.topRightCorner;
            this.bottomRightCorner = shapeAppearanceModel.bottomRightCorner;
            this.bottomLeftCorner = shapeAppearanceModel.bottomLeftCorner;
            this.topLeftCornerSize = shapeAppearanceModel.topLeftCornerSize;
            this.topRightCornerSize = shapeAppearanceModel.topRightCornerSize;
            this.bottomRightCornerSize = shapeAppearanceModel.bottomRightCornerSize;
            this.bottomLeftCornerSize = shapeAppearanceModel.bottomLeftCornerSize;
            this.topEdge = shapeAppearanceModel.topEdge;
            this.rightEdge = shapeAppearanceModel.rightEdge;
            this.bottomEdge = shapeAppearanceModel.bottomEdge;
            this.leftEdge = shapeAppearanceModel.leftEdge;
        }
    }

    public ShapeAppearanceModel() {
        this.topLeftCorner = new RoundedCornerTreatment();
        this.topRightCorner = new RoundedCornerTreatment();
        this.bottomRightCorner = new RoundedCornerTreatment();
        this.bottomLeftCorner = new RoundedCornerTreatment();
        this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
        this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
        this.topEdge = new EdgeTreatment();
        this.rightEdge = new EdgeTreatment();
        this.bottomEdge = new EdgeTreatment();
        this.leftEdge = new EdgeTreatment();
    }
}

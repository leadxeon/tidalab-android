package androidx.swiperefreshlayout.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
/* loaded from: classes.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {
    public Animator mAnimator;
    public boolean mFinishing;
    public Resources mResources;
    public final Ring mRing;
    public float mRotation;
    public float mRotationCount;
    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final int[] COLORS = {-16777216};

    /* loaded from: classes.dex */
    public static class Ring {
        public Path mArrow;
        public int mArrowHeight;
        public final Paint mArrowPaint;
        public int mArrowWidth;
        public final Paint mCirclePaint;
        public int mColorIndex;
        public int[] mColors;
        public int mCurrentColor;
        public final Paint mPaint;
        public float mRingCenterRadius;
        public boolean mShowArrow;
        public float mStartingEndTrim;
        public float mStartingRotation;
        public float mStartingStartTrim;
        public final RectF mTempBounds = new RectF();
        public float mStartTrim = 0.0f;
        public float mEndTrim = 0.0f;
        public float mRotation = 0.0f;
        public float mStrokeWidth = 5.0f;
        public float mArrowScale = 1.0f;
        public int mAlpha = 255;

        public Ring() {
            Paint paint = new Paint();
            this.mPaint = paint;
            Paint paint2 = new Paint();
            this.mArrowPaint = paint2;
            Paint paint3 = new Paint();
            this.mCirclePaint = paint3;
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint2.setStyle(Paint.Style.FILL);
            paint2.setAntiAlias(true);
            paint3.setColor(0);
        }

        public void setColorIndex(int i) {
            this.mColorIndex = i;
            this.mCurrentColor = this.mColors[i];
        }

        public void setShowArrow(boolean z) {
            if (this.mShowArrow != z) {
                this.mShowArrow = z;
            }
        }
    }

    public CircularProgressDrawable(Context context) {
        Objects.requireNonNull(context);
        this.mResources = context.getResources();
        final Ring ring = new Ring();
        this.mRing = ring;
        ring.mColors = COLORS;
        ring.setColorIndex(0);
        ring.mStrokeWidth = 2.5f;
        ring.mPaint.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircularProgressDrawable.this.updateRingColor(floatValue, ring);
                CircularProgressDrawable.this.applyTransformation(floatValue, ring, false);
                CircularProgressDrawable.this.invalidateSelf();
            }
        });
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(LINEAR_INTERPOLATOR);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: androidx.swiperefreshlayout.widget.CircularProgressDrawable.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                CircularProgressDrawable.this.applyTransformation(1.0f, ring, true);
                Ring ring2 = ring;
                ring2.mStartingStartTrim = ring2.mStartTrim;
                ring2.mStartingEndTrim = ring2.mEndTrim;
                ring2.mStartingRotation = ring2.mRotation;
                ring2.setColorIndex((ring2.mColorIndex + 1) % ring2.mColors.length);
                CircularProgressDrawable circularProgressDrawable = CircularProgressDrawable.this;
                if (circularProgressDrawable.mFinishing) {
                    circularProgressDrawable.mFinishing = false;
                    animator.cancel();
                    animator.setDuration(1332L);
                    animator.start();
                    ring.setShowArrow(false);
                    return;
                }
                circularProgressDrawable.mRotationCount += 1.0f;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                CircularProgressDrawable.this.mRotationCount = 0.0f;
            }
        });
        this.mAnimator = ofFloat;
    }

    public void applyTransformation(float f, Ring ring, boolean z) {
        float f2;
        float f3;
        if (this.mFinishing) {
            updateRingColor(f, ring);
            float floor = (float) (Math.floor(ring.mStartingRotation / 0.8f) + 1.0d);
            float f4 = ring.mStartingStartTrim;
            float f5 = ring.mStartingEndTrim;
            ring.mStartTrim = (((f5 - 0.01f) - f4) * f) + f4;
            ring.mEndTrim = f5;
            float f6 = ring.mStartingRotation;
            ring.mRotation = GeneratedOutlineSupport.outline0(floor, f6, f, f6);
        } else if (f != 1.0f || z) {
            float f7 = ring.mStartingRotation;
            if (f < 0.5f) {
                f2 = ring.mStartingStartTrim;
                f3 = (MATERIAL_INTERPOLATOR.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + f2;
            } else {
                float f8 = ring.mStartingStartTrim + 0.79f;
                f2 = f8 - (((1.0f - MATERIAL_INTERPOLATOR.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f3 = f8;
            }
            ring.mStartTrim = f2;
            ring.mEndTrim = f3;
            ring.mRotation = (0.20999998f * f) + f7;
            this.mRotation = (f + this.mRotationCount) * 216.0f;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        Ring ring = this.mRing;
        RectF rectF = ring.mTempBounds;
        float f = ring.mRingCenterRadius;
        float f2 = (ring.mStrokeWidth / 2.0f) + f;
        if (f <= 0.0f) {
            f2 = (Math.min(bounds.width(), bounds.height()) / 2.0f) - Math.max((ring.mArrowWidth * ring.mArrowScale) / 2.0f, ring.mStrokeWidth / 2.0f);
        }
        rectF.set(bounds.centerX() - f2, bounds.centerY() - f2, bounds.centerX() + f2, bounds.centerY() + f2);
        float f3 = ring.mStartTrim;
        float f4 = ring.mRotation;
        float f5 = (f3 + f4) * 360.0f;
        float f6 = ((ring.mEndTrim + f4) * 360.0f) - f5;
        ring.mPaint.setColor(ring.mCurrentColor);
        ring.mPaint.setAlpha(ring.mAlpha);
        float f7 = ring.mStrokeWidth / 2.0f;
        rectF.inset(f7, f7);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, ring.mCirclePaint);
        float f8 = -f7;
        rectF.inset(f8, f8);
        canvas.drawArc(rectF, f5, f6, false, ring.mPaint);
        if (ring.mShowArrow) {
            Path path = ring.mArrow;
            if (path == null) {
                Path path2 = new Path();
                ring.mArrow = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            ring.mArrow.moveTo(0.0f, 0.0f);
            ring.mArrow.lineTo(ring.mArrowWidth * ring.mArrowScale, 0.0f);
            Path path3 = ring.mArrow;
            float f9 = ring.mArrowScale;
            path3.lineTo((ring.mArrowWidth * f9) / 2.0f, ring.mArrowHeight * f9);
            ring.mArrow.offset((rectF.centerX() + (Math.min(rectF.width(), rectF.height()) / 2.0f)) - ((ring.mArrowWidth * ring.mArrowScale) / 2.0f), (ring.mStrokeWidth / 2.0f) + rectF.centerY());
            ring.mArrow.close();
            ring.mArrowPaint.setColor(ring.mCurrentColor);
            ring.mArrowPaint.setAlpha(ring.mAlpha);
            canvas.save();
            canvas.rotate(f5 + f6, rectF.centerX(), rectF.centerY());
            canvas.drawPath(ring.mArrow, ring.mArrowPaint);
            canvas.restore();
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mRing.mAlpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mAnimator.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mRing.mAlpha = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setSizeParameters(float f, float f2, float f3, float f4) {
        Ring ring = this.mRing;
        float f5 = this.mResources.getDisplayMetrics().density;
        float f6 = f2 * f5;
        ring.mStrokeWidth = f6;
        ring.mPaint.setStrokeWidth(f6);
        ring.mRingCenterRadius = f * f5;
        ring.setColorIndex(0);
        ring.mArrowWidth = (int) (f3 * f5);
        ring.mArrowHeight = (int) (f4 * f5);
    }

    public void setStyle(int i) {
        if (i == 0) {
            setSizeParameters(11.0f, 3.0f, 12.0f, 6.0f);
        } else {
            setSizeParameters(7.5f, 2.5f, 10.0f, 5.0f);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.mAnimator.cancel();
        Ring ring = this.mRing;
        float f = ring.mStartTrim;
        ring.mStartingStartTrim = f;
        float f2 = ring.mEndTrim;
        ring.mStartingEndTrim = f2;
        ring.mStartingRotation = ring.mRotation;
        if (f2 != f) {
            this.mFinishing = true;
            this.mAnimator.setDuration(666L);
            this.mAnimator.start();
            return;
        }
        ring.setColorIndex(0);
        Ring ring2 = this.mRing;
        ring2.mStartingStartTrim = 0.0f;
        ring2.mStartingEndTrim = 0.0f;
        ring2.mStartingRotation = 0.0f;
        ring2.mStartTrim = 0.0f;
        ring2.mEndTrim = 0.0f;
        ring2.mRotation = 0.0f;
        this.mAnimator.setDuration(1332L);
        this.mAnimator.start();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mAnimator.cancel();
        this.mRotation = 0.0f;
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        Ring ring = this.mRing;
        ring.mStartingStartTrim = 0.0f;
        ring.mStartingEndTrim = 0.0f;
        ring.mStartingRotation = 0.0f;
        ring.mStartTrim = 0.0f;
        ring.mEndTrim = 0.0f;
        ring.mRotation = 0.0f;
        invalidateSelf();
    }

    public void updateRingColor(float f, Ring ring) {
        if (f > 0.75f) {
            float f2 = (f - 0.75f) / 0.25f;
            int[] iArr = ring.mColors;
            int i = ring.mColorIndex;
            int i2 = iArr[i];
            int i3 = iArr[(i + 1) % iArr.length];
            int i4 = (i2 >> 24) & 255;
            int i5 = (i2 >> 16) & 255;
            int i6 = (i2 >> 8) & 255;
            int i7 = i2 & 255;
            ring.mCurrentColor = ((i4 + ((int) ((((i3 >> 24) & 255) - i4) * f2))) << 24) | ((i5 + ((int) ((((i3 >> 16) & 255) - i5) * f2))) << 16) | ((i6 + ((int) ((((i3 >> 8) & 255) - i6) * f2))) << 8) | (i7 + ((int) (f2 * ((i3 & 255) - i7))));
            return;
        }
        ring.mCurrentColor = ring.mColors[ring.mColorIndex];
    }
}

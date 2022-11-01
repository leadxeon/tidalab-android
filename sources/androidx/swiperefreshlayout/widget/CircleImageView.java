package androidx.swiperefreshlayout.widget;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class CircleImageView extends ImageView {
    public Animation.AnimationListener mListener;
    public int mShadowRadius;

    public CircleImageView(Context context, int i) {
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        this.mShadowRadius = (int) (3.5f * f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setElevation(f * 4.0f);
        shapeDrawable.getPaint().setColor(i);
        setBackground(shapeDrawable);
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }
}

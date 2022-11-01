package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ChangeBounds extends Transition {
    public int[] mTempLocation = new int[2];
    public static final String[] sTransitionProperties = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> DRAWABLE_ORIGIN_PROPERTY = new Property<Drawable, PointF>(PointF.class, "boundsOrigin") { // from class: androidx.transition.ChangeBounds.1
        public Rect mBounds = new Rect();

        @Override // android.util.Property
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.mBounds);
            Rect rect = this.mBounds;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        public void set(Drawable drawable, PointF pointF) {
            Drawable drawable2 = drawable;
            PointF pointF2 = pointF;
            drawable2.copyBounds(this.mBounds);
            this.mBounds.offsetTo(Math.round(pointF2.x), Math.round(pointF2.y));
            drawable2.setBounds(this.mBounds);
        }
    };
    public static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "topLeft") { // from class: androidx.transition.ChangeBounds.2
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(ViewBounds viewBounds) {
            return null;
        }

        @Override // android.util.Property
        public void set(ViewBounds viewBounds, PointF pointF) {
            ViewBounds viewBounds2 = viewBounds;
            PointF pointF2 = pointF;
            Objects.requireNonNull(viewBounds2);
            viewBounds2.mLeft = Math.round(pointF2.x);
            int round = Math.round(pointF2.y);
            viewBounds2.mTop = round;
            int i = viewBounds2.mTopLeftCalls + 1;
            viewBounds2.mTopLeftCalls = i;
            if (i == viewBounds2.mBottomRightCalls) {
                ViewUtils.setLeftTopRightBottom(viewBounds2.mView, viewBounds2.mLeft, round, viewBounds2.mRight, viewBounds2.mBottom);
                viewBounds2.mTopLeftCalls = 0;
                viewBounds2.mBottomRightCalls = 0;
            }
        }
    };
    public static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "bottomRight") { // from class: androidx.transition.ChangeBounds.3
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(ViewBounds viewBounds) {
            return null;
        }

        @Override // android.util.Property
        public void set(ViewBounds viewBounds, PointF pointF) {
            ViewBounds viewBounds2 = viewBounds;
            PointF pointF2 = pointF;
            Objects.requireNonNull(viewBounds2);
            viewBounds2.mRight = Math.round(pointF2.x);
            int round = Math.round(pointF2.y);
            viewBounds2.mBottom = round;
            int i = viewBounds2.mBottomRightCalls + 1;
            viewBounds2.mBottomRightCalls = i;
            if (viewBounds2.mTopLeftCalls == i) {
                ViewUtils.setLeftTopRightBottom(viewBounds2.mView, viewBounds2.mLeft, viewBounds2.mTop, viewBounds2.mRight, round);
                viewBounds2.mTopLeftCalls = 0;
                viewBounds2.mBottomRightCalls = 0;
            }
        }
    };
    public static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "bottomRight") { // from class: androidx.transition.ChangeBounds.4
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            ViewUtils.setLeftTopRightBottom(view2, view2.getLeft(), view2.getTop(), Math.round(pointF2.x), Math.round(pointF2.y));
        }
    };
    public static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "topLeft") { // from class: androidx.transition.ChangeBounds.5
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            ViewUtils.setLeftTopRightBottom(view2, Math.round(pointF2.x), Math.round(pointF2.y), view2.getRight(), view2.getBottom());
        }
    };
    public static final Property<View, PointF> POSITION_PROPERTY = new Property<View, PointF>(PointF.class, "position") { // from class: androidx.transition.ChangeBounds.6
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            int round = Math.round(pointF2.x);
            int round2 = Math.round(pointF2.y);
            ViewUtils.setLeftTopRightBottom(view2, round, round2, view2.getWidth() + round, view2.getHeight() + round2);
        }
    };
    public static RectEvaluator sRectEvaluator = new RectEvaluator();

    /* loaded from: classes.dex */
    public static class ViewBounds {
        public int mBottom;
        public int mBottomRightCalls;
        public int mLeft;
        public int mRight;
        public int mTop;
        public int mTopLeftCalls;
        public View mView;

        public ViewBounds(View view) {
            this.mView = view;
        }
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public final void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        ObjectAnimator objectAnimator;
        ChangeBounds changeBounds;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view = transitionValues2.view;
        Rect rect = (Rect) transitionValues.values.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) transitionValues2.values.get("android:changeBounds:bounds");
        int i2 = rect.left;
        int i3 = rect2.left;
        int i4 = rect.top;
        int i5 = rect2.top;
        int i6 = rect.right;
        int i7 = rect2.right;
        int i8 = rect.bottom;
        int i9 = rect2.bottom;
        int i10 = i6 - i2;
        int i11 = i8 - i4;
        int i12 = i7 - i3;
        int i13 = i9 - i5;
        Rect rect3 = (Rect) transitionValues.values.get("android:changeBounds:clip");
        Rect rect4 = (Rect) transitionValues2.values.get("android:changeBounds:clip");
        if ((i10 == 0 || i11 == 0) && (i12 == 0 || i13 == 0)) {
            i = 0;
        } else {
            i = (i2 == i3 && i4 == i5) ? 0 : 1;
            if (!(i6 == i7 && i8 == i9)) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        if (i <= 0) {
            return null;
        }
        ViewUtils.setLeftTopRightBottom(view, i2, i4, i6, i8);
        if (i != 2) {
            changeBounds = this;
            if (i2 == i3 && i4 == i5) {
                objectAnimator = R$dimen.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, changeBounds.mPathMotion.getPath(i6, i8, i7, i9));
            } else {
                objectAnimator = R$dimen.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, changeBounds.mPathMotion.getPath(i2, i4, i3, i5));
            }
        } else if (i10 == i12 && i11 == i13) {
            changeBounds = this;
            objectAnimator = R$dimen.ofPointF(view, POSITION_PROPERTY, changeBounds.mPathMotion.getPath(i2, i4, i3, i5));
        } else {
            changeBounds = this;
            ViewBounds viewBounds = new ViewBounds(view);
            ObjectAnimator ofPointF = R$dimen.ofPointF(viewBounds, TOP_LEFT_PROPERTY, changeBounds.mPathMotion.getPath(i2, i4, i3, i5));
            ObjectAnimator ofPointF2 = R$dimen.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, changeBounds.mPathMotion.getPath(i6, i8, i7, i9));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(ofPointF, ofPointF2);
            animatorSet.addListener(new AnimatorListenerAdapter(changeBounds, viewBounds) { // from class: androidx.transition.ChangeBounds.7
                private ViewBounds mViewBounds;
                public final /* synthetic */ ViewBounds val$viewBounds;

                {
                    this.val$viewBounds = viewBounds;
                    this.mViewBounds = viewBounds;
                }
            });
            objectAnimator = animatorSet;
        }
        if (view.getParent() instanceof ViewGroup) {
            final ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ViewGroupUtils.suppressLayout(viewGroup4, true);
            changeBounds.addListener(new TransitionListenerAdapter(changeBounds) { // from class: androidx.transition.ChangeBounds.9
                public boolean mCanceled = false;

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionCancel(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, false);
                    this.mCanceled = true;
                }

                @Override // androidx.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    if (!this.mCanceled) {
                        ViewGroupUtils.suppressLayout(viewGroup4, false);
                    }
                    transition.removeListener(this);
                }

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionPause(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, false);
                }

                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionResume(Transition transition) {
                    ViewGroupUtils.suppressLayout(viewGroup4, true);
                }
            });
        }
        return objectAnimator;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }
}

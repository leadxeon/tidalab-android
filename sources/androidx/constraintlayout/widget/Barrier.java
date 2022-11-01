package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
/* loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public androidx.constraintlayout.solver.widgets.Barrier mBarrier;
    public int mIndicatedType;
    public int mResolvedType;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public int getMargin() {
        return this.mBarrier.mMargin;
    }

    public int getType() {
        return this.mIndicatedType;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void init(AttributeSet attributeSet) {
        super.init(attributeSet);
        this.mBarrier = new androidx.constraintlayout.solver.widgets.Barrier();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 15) {
                    setType(obtainStyledAttributes.getInt(index, 0));
                } else if (index == 14) {
                    this.mBarrier.mAllowsGoneWidget = obtainStyledAttributes.getBoolean(index, true);
                } else if (index == 16) {
                    this.mBarrier.mMargin = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
        }
        this.mHelperWidget = this.mBarrier;
        validateParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
        int i = this.mIndicatedType;
        this.mResolvedType = i;
        if (z) {
            if (i == 5) {
                this.mResolvedType = 1;
            } else if (i == 6) {
                this.mResolvedType = 0;
            }
        } else if (i == 5) {
            this.mResolvedType = 0;
        } else if (i == 6) {
            this.mResolvedType = 1;
        }
        if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.Barrier) {
            ((androidx.constraintlayout.solver.widgets.Barrier) constraintWidget).mBarrierType = this.mResolvedType;
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mBarrier.mAllowsGoneWidget = z;
    }

    public void setDpMargin(int i) {
        this.mBarrier.mMargin = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.mBarrier.mMargin = i;
    }

    public void setType(int i) {
        this.mIndicatedType = i;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }
}

package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class BasicMeasure {
    public ConstraintWidgetContainer constraintWidgetContainer;
    public final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    public Measure mMeasure = new Measure();

    /* loaded from: classes.dex */
    public static class Measure {
        public int horizontalBehavior;
        public int horizontalDimension;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public boolean useCurrentDimensions;
        public int verticalBehavior;
        public int verticalDimension;
    }

    /* loaded from: classes.dex */
    public interface Measurer {
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    public final boolean measure(Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour$enumunboxing$();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour$enumunboxing$();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.useCurrentDimensions = z;
        boolean z2 = true;
        boolean z3 = measure.horizontalBehavior == 3;
        boolean z4 = measure.verticalBehavior == 3;
        boolean z5 = z3 && constraintWidget.mDimensionRatio > 0.0f;
        boolean z6 = z4 && constraintWidget.mDimensionRatio > 0.0f;
        if (z5 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            measure.horizontalBehavior = 1;
        }
        if (z6 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            measure.verticalBehavior = 1;
        }
        ((ConstraintLayout.Measurer) measurer).measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        Measure measure2 = this.mMeasure;
        constraintWidget.hasBaseline = measure2.measuredHasBaseline;
        int i = measure2.measuredBaseline;
        constraintWidget.mBaselineDistance = i;
        if (i <= 0) {
            z2 = false;
        }
        constraintWidget.hasBaseline = z2;
        measure2.useCurrentDimensions = false;
        return measure2.measuredNeedsSolverPass;
    }

    public final void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2) {
        int i3 = constraintWidgetContainer.mMinWidth;
        int i4 = constraintWidgetContainer.mMinHeight;
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.mWidth = i;
        int i5 = constraintWidgetContainer.mMinWidth;
        if (i < i5) {
            constraintWidgetContainer.mWidth = i5;
        }
        constraintWidgetContainer.mHeight = i2;
        int i6 = constraintWidgetContainer.mMinHeight;
        if (i2 < i6) {
            constraintWidgetContainer.mHeight = i6;
        }
        constraintWidgetContainer.setMinWidth(i3);
        constraintWidgetContainer.setMinHeight(i4);
        this.constraintWidgetContainer.layout();
    }
}

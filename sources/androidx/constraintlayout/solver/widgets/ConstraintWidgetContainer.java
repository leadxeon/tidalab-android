package androidx.constraintlayout.solver.widgets;

import androidx.cardview.R$color;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    public int mPaddingLeft;
    public int mPaddingTop;
    public BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);
    public DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public BasicMeasure.Measurer mMeasurer = null;
    public boolean mIsRtl = false;
    public LinearSystem mSystem = new LinearSystem();
    public int mHorizontalChainsSize = 0;
    public int mVerticalChainsSize = 0;
    public ChainHead[] mVerticalChainsArray = new ChainHead[4];
    public ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    public int mOptimizationLevel = 263;
    public boolean mWidthMeasuredTooSmall = false;
    public boolean mHeightMeasuredTooSmall = false;

    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            int i2 = this.mHorizontalChainsSize + 1;
            ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
            if (i2 >= chainHeadArr.length) {
                this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
            }
            ChainHead[] chainHeadArr2 = this.mHorizontalChainsArray;
            int i3 = this.mHorizontalChainsSize;
            chainHeadArr2[i3] = new ChainHead(constraintWidget, 0, this.mIsRtl);
            this.mHorizontalChainsSize = i3 + 1;
        } else if (i == 1) {
            int i4 = this.mVerticalChainsSize + 1;
            ChainHead[] chainHeadArr3 = this.mVerticalChainsArray;
            if (i4 >= chainHeadArr3.length) {
                this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr3, chainHeadArr3.length * 2);
            }
            ChainHead[] chainHeadArr4 = this.mVerticalChainsArray;
            int i5 = this.mVerticalChainsSize;
            chainHeadArr4[i5] = new ChainHead(constraintWidget, 1, this.mIsRtl);
            this.mVerticalChainsSize = i5 + 1;
        }
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        addToSolver(linearSystem);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            boolean[] zArr = constraintWidget.mIsInBarrier;
            zArr[0] = false;
            zArr[1] = false;
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = this.mChildren.get(i2);
                if (constraintWidget2 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget2;
                    for (int i3 = 0; i3 < barrier.mWidgetsCount; i3++) {
                        ConstraintWidget constraintWidget3 = barrier.mWidgets[i3];
                        int i4 = barrier.mBarrierType;
                        if (i4 == 0 || i4 == 1) {
                            constraintWidget3.mIsInBarrier[0] = true;
                        } else if (i4 == 2 || i4 == 3) {
                            constraintWidget3.mIsInBarrier[1] = true;
                        }
                    }
                }
            }
        }
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = this.mChildren.get(i5);
            Objects.requireNonNull(constraintWidget4);
            if ((constraintWidget4 instanceof VirtualLayout) || (constraintWidget4 instanceof Guideline)) {
                constraintWidget4.addToSolver(linearSystem);
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            ConstraintWidget constraintWidget5 = this.mChildren.get(i6);
            if (constraintWidget5 instanceof ConstraintWidgetContainer) {
                int[] iArr = constraintWidget5.mListDimensionBehaviors;
                int i7 = iArr[0];
                int i8 = iArr[1];
                if (i7 == 2) {
                    iArr[0] = 1;
                }
                if (i8 == 2) {
                    iArr[1] = 1;
                }
                constraintWidget5.addToSolver(linearSystem);
                if (i7 == 2) {
                    constraintWidget5.setHorizontalDimensionBehaviour$enumunboxing$(i7);
                }
                if (i8 == 2) {
                    constraintWidget5.setVerticalDimensionBehaviour$enumunboxing$(i8);
                }
            } else {
                constraintWidget5.mHorizontalResolution = -1;
                constraintWidget5.mVerticalResolution = -1;
                if (this.mListDimensionBehaviors[0] != 2 && constraintWidget5.mListDimensionBehaviors[0] == 4) {
                    int i9 = constraintWidget5.mLeft.mMargin;
                    int width = getWidth() - constraintWidget5.mRight.mMargin;
                    ConstraintAnchor constraintAnchor = constraintWidget5.mLeft;
                    constraintAnchor.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor);
                    ConstraintAnchor constraintAnchor2 = constraintWidget5.mRight;
                    constraintAnchor2.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor2);
                    linearSystem.addEquality(constraintWidget5.mLeft.mSolverVariable, i9);
                    linearSystem.addEquality(constraintWidget5.mRight.mSolverVariable, width);
                    constraintWidget5.mHorizontalResolution = 2;
                    constraintWidget5.mX = i9;
                    int i10 = width - i9;
                    constraintWidget5.mWidth = i10;
                    int i11 = constraintWidget5.mMinWidth;
                    if (i10 < i11) {
                        constraintWidget5.mWidth = i11;
                    }
                }
                if (this.mListDimensionBehaviors[1] != 2 && constraintWidget5.mListDimensionBehaviors[1] == 4) {
                    int i12 = constraintWidget5.mTop.mMargin;
                    int height = getHeight() - constraintWidget5.mBottom.mMargin;
                    ConstraintAnchor constraintAnchor3 = constraintWidget5.mTop;
                    constraintAnchor3.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor3);
                    ConstraintAnchor constraintAnchor4 = constraintWidget5.mBottom;
                    constraintAnchor4.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor4);
                    linearSystem.addEquality(constraintWidget5.mTop.mSolverVariable, i12);
                    linearSystem.addEquality(constraintWidget5.mBottom.mSolverVariable, height);
                    if (constraintWidget5.mBaselineDistance > 0 || constraintWidget5.mVisibility == 8) {
                        ConstraintAnchor constraintAnchor5 = constraintWidget5.mBaseline;
                        constraintAnchor5.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor5);
                        linearSystem.addEquality(constraintWidget5.mBaseline.mSolverVariable, constraintWidget5.mBaselineDistance + i12);
                    }
                    constraintWidget5.mVerticalResolution = 2;
                    constraintWidget5.mY = i12;
                    int i13 = height - i12;
                    constraintWidget5.mHeight = i13;
                    int i14 = constraintWidget5.mMinHeight;
                    if (i13 < i14) {
                        constraintWidget5.mHeight = i14;
                    }
                }
                if (!((constraintWidget5 instanceof VirtualLayout) || (constraintWidget5 instanceof Guideline))) {
                    constraintWidget5.addToSolver(linearSystem);
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            R$color.applyChainConstraints(this, linearSystem, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            R$color.applyChainConstraints(this, linearSystem, 1);
        }
        return true;
    }

    public boolean directMeasureWithOrientation(boolean z, int i) {
        boolean z2;
        DependencyGraph dependencyGraph = this.mDependencyGraph;
        boolean z3 = true;
        boolean z4 = z & true;
        int dimensionBehaviour$enumunboxing$ = dependencyGraph.container.getDimensionBehaviour$enumunboxing$(0);
        int dimensionBehaviour$enumunboxing$2 = dependencyGraph.container.getDimensionBehaviour$enumunboxing$(1);
        int x = dependencyGraph.container.getX();
        int y = dependencyGraph.container.getY();
        if (z4 && (dimensionBehaviour$enumunboxing$ == 2 || dimensionBehaviour$enumunboxing$2 == 2)) {
            Iterator<WidgetRun> it = dependencyGraph.mRuns.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WidgetRun next = it.next();
                if (next.orientation == i && !next.supportsWrapComputation()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && dimensionBehaviour$enumunboxing$ == 2) {
                    ConstraintWidgetContainer constraintWidgetContainer = dependencyGraph.container;
                    constraintWidgetContainer.mListDimensionBehaviors[0] = 1;
                    constraintWidgetContainer.setWidth(dependencyGraph.computeWrap(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = dependencyGraph.container;
                    constraintWidgetContainer2.horizontalRun.dimension.resolve(constraintWidgetContainer2.getWidth());
                }
            } else if (z4 && dimensionBehaviour$enumunboxing$2 == 2) {
                ConstraintWidgetContainer constraintWidgetContainer3 = dependencyGraph.container;
                constraintWidgetContainer3.mListDimensionBehaviors[1] = 1;
                constraintWidgetContainer3.setHeight(dependencyGraph.computeWrap(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = dependencyGraph.container;
                constraintWidgetContainer4.verticalRun.dimension.resolve(constraintWidgetContainer4.getHeight());
            }
        }
        if (i == 0) {
            ConstraintWidgetContainer constraintWidgetContainer5 = dependencyGraph.container;
            int[] iArr = constraintWidgetContainer5.mListDimensionBehaviors;
            if (iArr[0] == 1 || iArr[0] == 4) {
                int width = constraintWidgetContainer5.getWidth() + x;
                dependencyGraph.container.horizontalRun.end.resolve(width);
                dependencyGraph.container.horizontalRun.dimension.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            ConstraintWidgetContainer constraintWidgetContainer6 = dependencyGraph.container;
            int[] iArr2 = constraintWidgetContainer6.mListDimensionBehaviors;
            if (iArr2[1] == 1 || iArr2[1] == 4) {
                int height = constraintWidgetContainer6.getHeight() + y;
                dependencyGraph.container.verticalRun.end.resolve(height);
                dependencyGraph.container.verticalRun.dimension.resolve(height - y);
                z2 = true;
            }
            z2 = false;
        }
        dependencyGraph.measureWidgets();
        Iterator<WidgetRun> it2 = dependencyGraph.mRuns.iterator();
        while (it2.hasNext()) {
            WidgetRun next2 = it2.next();
            if (next2.orientation == i && (next2.widget != dependencyGraph.container || next2.resolved)) {
                next2.applyToWidget();
            }
        }
        Iterator<WidgetRun> it3 = dependencyGraph.mRuns.iterator();
        while (it3.hasNext()) {
            WidgetRun next3 = it3.next();
            if (next3.orientation == i && (z2 || next3.widget != dependencyGraph.container)) {
                if (!next3.start.resolved || !next3.end.resolved || (!(next3 instanceof ChainRun) && !next3.dimension.resolved)) {
                    z3 = false;
                    break;
                }
            }
        }
        dependencyGraph.container.setHorizontalDimensionBehaviour$enumunboxing$(dimensionBehaviour$enumunboxing$);
        dependencyGraph.container.setVerticalDimensionBehaviour$enumunboxing$(dimensionBehaviour$enumunboxing$2);
        return z3;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.mNeedBuildGraph = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01da  */
    /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void layout() {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    @Override // androidx.constraintlayout.solver.widgets.WidgetContainer, androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        super.reset();
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        LinearSystem.OPTIMIZED_ENGINE = Optimizer.enabled(i, 256);
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            this.mChildren.get(i).updateFromRuns(z, z2);
        }
    }
}

package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class VerticalWidgetRun extends WidgetRun {
    public DependencyNode baseline;
    public DimensionDependency baselineDimension = null;

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.baseline = dependencyNode;
        this.start.type = DependencyNode.Type.TOP;
        this.end.type = DependencyNode.Type.BOTTOM;
        dependencyNode.type = DependencyNode.Type.BASELINE;
        this.orientation = 1;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4 = this.widget;
        if (constraintWidget4.measured) {
            this.dimension.resolve(constraintWidget4.getHeight());
        }
        if (!this.dimension.resolved) {
            this.dimensionBehavior = this.widget.getVerticalDimensionBehaviour$enumunboxing$();
            if (this.widget.hasBaseline) {
                this.baselineDimension = new BaselineDimensionDependency(this);
            }
            int i = this.dimensionBehavior;
            if (i != 3) {
                if (i == 4 && (constraintWidget3 = this.widget.mParent) != null && constraintWidget3.getVerticalDimensionBehaviour$enumunboxing$() == 1) {
                    int height = (constraintWidget3.getHeight() - this.widget.mTop.getMargin()) - this.widget.mBottom.getMargin();
                    addTarget(this.start, constraintWidget3.verticalRun.start, this.widget.mTop.getMargin());
                    addTarget(this.end, constraintWidget3.verticalRun.end, -this.widget.mBottom.getMargin());
                    this.dimension.resolve(height);
                    return;
                } else if (this.dimensionBehavior == 1) {
                    this.dimension.resolve(this.widget.getHeight());
                }
            }
        } else if (this.dimensionBehavior == 4 && (constraintWidget2 = this.widget.mParent) != null && constraintWidget2.getVerticalDimensionBehaviour$enumunboxing$() == 1) {
            addTarget(this.start, constraintWidget2.verticalRun.start, this.widget.mTop.getMargin());
            addTarget(this.end, constraintWidget2.verticalRun.end, -this.widget.mBottom.getMargin());
            return;
        }
        DimensionDependency dimensionDependency = this.dimension;
        boolean z = dimensionDependency.resolved;
        if (z) {
            ConstraintWidget constraintWidget5 = this.widget;
            if (constraintWidget5.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.mListAnchors;
                if (constraintAnchorArr[2].mTarget != null && constraintAnchorArr[3].mTarget != null) {
                    if (constraintWidget5.isInVerticalChain()) {
                        this.start.margin = this.widget.mListAnchors[2].getMargin();
                        this.end.margin = -this.widget.mListAnchors[3].getMargin();
                    } else {
                        DependencyNode target = getTarget(this.widget.mListAnchors[2]);
                        if (target != null) {
                            DependencyNode dependencyNode = this.start;
                            int margin = this.widget.mListAnchors[2].getMargin();
                            dependencyNode.targets.add(target);
                            dependencyNode.margin = margin;
                            target.dependencies.add(dependencyNode);
                        }
                        DependencyNode target2 = getTarget(this.widget.mListAnchors[3]);
                        if (target2 != null) {
                            DependencyNode dependencyNode2 = this.end;
                            dependencyNode2.targets.add(target2);
                            dependencyNode2.margin = -this.widget.mListAnchors[3].getMargin();
                            target2.dependencies.add(dependencyNode2);
                        }
                        this.start.delegateToWidgetRun = true;
                        this.end.delegateToWidgetRun = true;
                    }
                    ConstraintWidget constraintWidget6 = this.widget;
                    if (constraintWidget6.hasBaseline) {
                        addTarget(this.baseline, this.start, constraintWidget6.mBaselineDistance);
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[2].mTarget != null) {
                    DependencyNode target3 = getTarget(constraintAnchorArr[2]);
                    if (target3 != null) {
                        DependencyNode dependencyNode3 = this.start;
                        int margin2 = this.widget.mListAnchors[2].getMargin();
                        dependencyNode3.targets.add(target3);
                        dependencyNode3.margin = margin2;
                        target3.dependencies.add(dependencyNode3);
                        addTarget(this.end, this.start, this.dimension.value);
                        ConstraintWidget constraintWidget7 = this.widget;
                        if (constraintWidget7.hasBaseline) {
                            addTarget(this.baseline, this.start, constraintWidget7.mBaselineDistance);
                            return;
                        }
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[3].mTarget != null) {
                    DependencyNode target4 = getTarget(constraintAnchorArr[3]);
                    if (target4 != null) {
                        DependencyNode dependencyNode4 = this.end;
                        dependencyNode4.targets.add(target4);
                        dependencyNode4.margin = -this.widget.mListAnchors[3].getMargin();
                        target4.dependencies.add(dependencyNode4);
                        addTarget(this.start, this.end, -this.dimension.value);
                    }
                    ConstraintWidget constraintWidget8 = this.widget;
                    if (constraintWidget8.hasBaseline) {
                        addTarget(this.baseline, this.start, constraintWidget8.mBaselineDistance);
                        return;
                    }
                    return;
                } else if (constraintAnchorArr[4].mTarget != null) {
                    DependencyNode target5 = getTarget(constraintAnchorArr[4]);
                    if (target5 != null) {
                        DependencyNode dependencyNode5 = this.baseline;
                        dependencyNode5.targets.add(target5);
                        dependencyNode5.margin = 0;
                        target5.dependencies.add(dependencyNode5);
                        addTarget(this.start, this.baseline, -this.widget.mBaselineDistance);
                        addTarget(this.end, this.start, this.dimension.value);
                        return;
                    }
                    return;
                } else if (!(constraintWidget5 instanceof Helper) && constraintWidget5.mParent != null && constraintWidget5.getAnchor(ConstraintAnchor.Type.CENTER).mTarget == null) {
                    ConstraintWidget constraintWidget9 = this.widget;
                    addTarget(this.start, constraintWidget9.mParent.verticalRun.start, constraintWidget9.getY());
                    addTarget(this.end, this.start, this.dimension.value);
                    ConstraintWidget constraintWidget10 = this.widget;
                    if (constraintWidget10.hasBaseline) {
                        addTarget(this.baseline, this.start, constraintWidget10.mBaselineDistance);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
        if (z || this.dimensionBehavior != 3) {
            dimensionDependency.dependencies.add(this);
            if (dimensionDependency.resolved) {
                update(this);
            }
        } else {
            ConstraintWidget constraintWidget11 = this.widget;
            int i2 = constraintWidget11.mMatchConstraintDefaultHeight;
            if (i2 == 2) {
                ConstraintWidget constraintWidget12 = constraintWidget11.mParent;
                if (constraintWidget12 != null) {
                    DimensionDependency dimensionDependency2 = constraintWidget12.verticalRun.dimension;
                    dimensionDependency.targets.add(dimensionDependency2);
                    dimensionDependency2.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency3 = this.dimension;
                    dimensionDependency3.delegateToWidgetRun = true;
                    dimensionDependency3.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                }
            } else if (i2 == 3 && !constraintWidget11.isInVerticalChain()) {
                ConstraintWidget constraintWidget13 = this.widget;
                if (constraintWidget13.mMatchConstraintDefaultWidth != 3) {
                    DimensionDependency dimensionDependency4 = constraintWidget13.horizontalRun.dimension;
                    this.dimension.targets.add(dimensionDependency4);
                    dimensionDependency4.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency5 = this.dimension;
                    dimensionDependency5.delegateToWidgetRun = true;
                    dimensionDependency5.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                }
            }
        }
        ConstraintWidget constraintWidget14 = this.widget;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget14.mListAnchors;
        if (constraintAnchorArr2[2].mTarget != null && constraintAnchorArr2[3].mTarget != null) {
            if (constraintWidget14.isInVerticalChain()) {
                this.start.margin = this.widget.mListAnchors[2].getMargin();
                this.end.margin = -this.widget.mListAnchors[3].getMargin();
            } else {
                DependencyNode target6 = getTarget(this.widget.mListAnchors[2]);
                DependencyNode target7 = getTarget(this.widget.mListAnchors[3]);
                target6.dependencies.add(this);
                if (target6.resolved) {
                    update(this);
                }
                target7.dependencies.add(this);
                if (target7.resolved) {
                    update(this);
                }
                this.mRunType = 4;
            }
            if (this.widget.hasBaseline) {
                addTarget(this.baseline, this.start, 1, this.baselineDimension);
            }
        } else if (constraintAnchorArr2[2].mTarget != null) {
            DependencyNode target8 = getTarget(constraintAnchorArr2[2]);
            if (target8 != null) {
                DependencyNode dependencyNode6 = this.start;
                int margin3 = this.widget.mListAnchors[2].getMargin();
                dependencyNode6.targets.add(target8);
                dependencyNode6.margin = margin3;
                target8.dependencies.add(dependencyNode6);
                addTarget(this.end, this.start, 1, this.dimension);
                if (this.widget.hasBaseline) {
                    addTarget(this.baseline, this.start, 1, this.baselineDimension);
                }
                if (this.dimensionBehavior == 3) {
                    ConstraintWidget constraintWidget15 = this.widget;
                    if (constraintWidget15.mDimensionRatio > 0.0f) {
                        HorizontalWidgetRun horizontalWidgetRun = constraintWidget15.horizontalRun;
                        if (horizontalWidgetRun.dimensionBehavior == 3) {
                            horizontalWidgetRun.dimension.dependencies.add(this.dimension);
                            this.dimension.targets.add(this.widget.horizontalRun.dimension);
                            this.dimension.updateDelegate = this;
                        }
                    }
                }
            }
        } else if (constraintAnchorArr2[3].mTarget != null) {
            DependencyNode target9 = getTarget(constraintAnchorArr2[3]);
            if (target9 != null) {
                DependencyNode dependencyNode7 = this.end;
                dependencyNode7.targets.add(target9);
                dependencyNode7.margin = -this.widget.mListAnchors[3].getMargin();
                target9.dependencies.add(dependencyNode7);
                addTarget(this.start, this.end, -1, this.dimension);
                if (this.widget.hasBaseline) {
                    addTarget(this.baseline, this.start, 1, this.baselineDimension);
                }
            }
        } else if (constraintAnchorArr2[4].mTarget != null) {
            DependencyNode target10 = getTarget(constraintAnchorArr2[4]);
            if (target10 != null) {
                DependencyNode dependencyNode8 = this.baseline;
                dependencyNode8.targets.add(target10);
                dependencyNode8.margin = 0;
                target10.dependencies.add(dependencyNode8);
                addTarget(this.start, this.baseline, -1, this.baselineDimension);
                addTarget(this.end, this.start, 1, this.dimension);
            }
        } else if (!(constraintWidget14 instanceof Helper) && (constraintWidget = constraintWidget14.mParent) != null) {
            addTarget(this.start, constraintWidget.verticalRun.start, constraintWidget14.getY());
            addTarget(this.end, this.start, 1, this.dimension);
            if (this.widget.hasBaseline) {
                addTarget(this.baseline, this.start, 1, this.baselineDimension);
            }
            if (this.dimensionBehavior == 3) {
                ConstraintWidget constraintWidget16 = this.widget;
                if (constraintWidget16.mDimensionRatio > 0.0f) {
                    HorizontalWidgetRun horizontalWidgetRun2 = constraintWidget16.horizontalRun;
                    if (horizontalWidgetRun2.dimensionBehavior == 3) {
                        horizontalWidgetRun2.dimension.dependencies.add(this.dimension);
                        this.dimension.targets.add(this.widget.horizontalRun.dimension);
                        this.dimension.updateDelegate = this;
                    }
                }
            }
        }
        if (this.dimension.targets.size() == 0) {
            this.dimension.readyToSolve = true;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.widget.mY = dependencyNode.value;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    public void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.dimension.resolved = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return this.dimensionBehavior != 3 || this.widget.mMatchConstraintDefaultHeight == 0;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("VerticalRun ");
        outline13.append(this.widget.mDebugName);
        return outline13.toString();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DimensionDependency dimensionDependency;
        int i;
        float f;
        float f2;
        float f3;
        int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mRunType);
        if ($enumboxing$ordinal == 1 || $enumboxing$ordinal == 2 || $enumboxing$ordinal != 3) {
            DimensionDependency dimensionDependency2 = this.dimension;
            if (dimensionDependency2.readyToSolve && !dimensionDependency2.resolved && this.dimensionBehavior == 3) {
                ConstraintWidget constraintWidget = this.widget;
                int i2 = constraintWidget.mMatchConstraintDefaultHeight;
                if (i2 == 2) {
                    ConstraintWidget constraintWidget2 = constraintWidget.mParent;
                    if (constraintWidget2 != null) {
                        if (constraintWidget2.verticalRun.dimension.resolved) {
                            dimensionDependency2.resolve((int) ((dimensionDependency.value * constraintWidget.mMatchConstraintPercentHeight) + 0.5f));
                        }
                    }
                } else if (i2 == 3) {
                    DimensionDependency dimensionDependency3 = constraintWidget.horizontalRun.dimension;
                    if (dimensionDependency3.resolved) {
                        int i3 = constraintWidget.mDimensionRatioSide;
                        if (i3 == -1) {
                            f3 = dimensionDependency3.value;
                            f2 = constraintWidget.mDimensionRatio;
                        } else if (i3 == 0) {
                            f = dimensionDependency3.value * constraintWidget.mDimensionRatio;
                            i = (int) (f + 0.5f);
                            dimensionDependency2.resolve(i);
                        } else if (i3 != 1) {
                            i = 0;
                            dimensionDependency2.resolve(i);
                        } else {
                            f3 = dimensionDependency3.value;
                            f2 = constraintWidget.mDimensionRatio;
                        }
                        f = f3 / f2;
                        i = (int) (f + 0.5f);
                        dimensionDependency2.resolve(i);
                    }
                }
            }
            DependencyNode dependencyNode = this.start;
            if (dependencyNode.readyToSolve) {
                DependencyNode dependencyNode2 = this.end;
                if (dependencyNode2.readyToSolve) {
                    if (!dependencyNode.resolved || !dependencyNode2.resolved || !this.dimension.resolved) {
                        if (!this.dimension.resolved && this.dimensionBehavior == 3) {
                            ConstraintWidget constraintWidget3 = this.widget;
                            if (constraintWidget3.mMatchConstraintDefaultWidth == 0 && !constraintWidget3.isInVerticalChain()) {
                                int i4 = this.start.targets.get(0).value;
                                DependencyNode dependencyNode3 = this.start;
                                int i5 = i4 + dependencyNode3.margin;
                                int i6 = this.end.targets.get(0).value + this.end.margin;
                                dependencyNode3.resolve(i5);
                                this.end.resolve(i6);
                                this.dimension.resolve(i6 - i5);
                                return;
                            }
                        }
                        if (!this.dimension.resolved && this.dimensionBehavior == 3 && this.matchConstraintsType == 1 && this.start.targets.size() > 0 && this.end.targets.size() > 0) {
                            int i7 = (this.end.targets.get(0).value + this.end.margin) - (this.start.targets.get(0).value + this.start.margin);
                            DimensionDependency dimensionDependency4 = this.dimension;
                            int i8 = dimensionDependency4.wrapValue;
                            if (i7 < i8) {
                                dimensionDependency4.resolve(i7);
                            } else {
                                dimensionDependency4.resolve(i8);
                            }
                        }
                        if (this.dimension.resolved && this.start.targets.size() > 0 && this.end.targets.size() > 0) {
                            DependencyNode dependencyNode4 = this.start.targets.get(0);
                            DependencyNode dependencyNode5 = this.end.targets.get(0);
                            int i9 = dependencyNode4.value;
                            DependencyNode dependencyNode6 = this.start;
                            i9 = dependencyNode6.margin + i9;
                            int i10 = dependencyNode5.value;
                            i10 = this.end.margin + i10;
                            float f4 = this.widget.mVerticalBiasPercent;
                            if (dependencyNode4 == dependencyNode5) {
                                f4 = 0.5f;
                            }
                            dependencyNode6.resolve((int) ((((i10 - i9) - this.dimension.value) * f4) + i9 + 0.5f));
                            this.end.resolve(this.start.value + this.dimension.value);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        ConstraintWidget constraintWidget4 = this.widget;
        updateRunCenter(constraintWidget4.mTop, constraintWidget4.mBottom, 1);
    }
}

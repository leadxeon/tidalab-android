package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class HorizontalWidgetRun extends WidgetRun {
    public static int[] tempDimensions = new int[2];

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.type = DependencyNode.Type.LEFT;
        this.end.type = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4 = this.widget;
        if (constraintWidget4.measured) {
            this.dimension.resolve(constraintWidget4.getWidth());
        }
        if (!this.dimension.resolved) {
            int horizontalDimensionBehaviour$enumunboxing$ = this.widget.getHorizontalDimensionBehaviour$enumunboxing$();
            this.dimensionBehavior = horizontalDimensionBehaviour$enumunboxing$;
            if (horizontalDimensionBehaviour$enumunboxing$ != 3) {
                if (horizontalDimensionBehaviour$enumunboxing$ == 4 && (((constraintWidget3 = this.widget.mParent) != null && constraintWidget3.getHorizontalDimensionBehaviour$enumunboxing$() == 1) || constraintWidget3.getHorizontalDimensionBehaviour$enumunboxing$() == 4)) {
                    int width = (constraintWidget3.getWidth() - this.widget.mLeft.getMargin()) - this.widget.mRight.getMargin();
                    addTarget(this.start, constraintWidget3.horizontalRun.start, this.widget.mLeft.getMargin());
                    addTarget(this.end, constraintWidget3.horizontalRun.end, -this.widget.mRight.getMargin());
                    this.dimension.resolve(width);
                    return;
                } else if (this.dimensionBehavior == 1) {
                    this.dimension.resolve(this.widget.getWidth());
                }
            }
        } else if (this.dimensionBehavior == 4 && (((constraintWidget2 = this.widget.mParent) != null && constraintWidget2.getHorizontalDimensionBehaviour$enumunboxing$() == 1) || constraintWidget2.getHorizontalDimensionBehaviour$enumunboxing$() == 4)) {
            addTarget(this.start, constraintWidget2.horizontalRun.start, this.widget.mLeft.getMargin());
            addTarget(this.end, constraintWidget2.horizontalRun.end, -this.widget.mRight.getMargin());
            return;
        }
        DimensionDependency dimensionDependency = this.dimension;
        if (dimensionDependency.resolved) {
            ConstraintWidget constraintWidget5 = this.widget;
            if (constraintWidget5.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.mListAnchors;
                if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                    if (constraintAnchorArr[0].mTarget != null) {
                        DependencyNode target = getTarget(constraintAnchorArr[0]);
                        if (target != null) {
                            DependencyNode dependencyNode = this.start;
                            int margin = this.widget.mListAnchors[0].getMargin();
                            dependencyNode.targets.add(target);
                            dependencyNode.margin = margin;
                            target.dependencies.add(dependencyNode);
                            addTarget(this.end, this.start, this.dimension.value);
                            return;
                        }
                        return;
                    } else if (constraintAnchorArr[1].mTarget != null) {
                        DependencyNode target2 = getTarget(constraintAnchorArr[1]);
                        if (target2 != null) {
                            DependencyNode dependencyNode2 = this.end;
                            dependencyNode2.targets.add(target2);
                            dependencyNode2.margin = -this.widget.mListAnchors[1].getMargin();
                            target2.dependencies.add(dependencyNode2);
                            addTarget(this.start, this.end, -this.dimension.value);
                            return;
                        }
                        return;
                    } else if (!(constraintWidget5 instanceof Helper) && constraintWidget5.mParent != null && constraintWidget5.getAnchor(ConstraintAnchor.Type.CENTER).mTarget == null) {
                        ConstraintWidget constraintWidget6 = this.widget;
                        addTarget(this.start, constraintWidget6.mParent.horizontalRun.start, constraintWidget6.getX());
                        addTarget(this.end, this.start, this.dimension.value);
                        return;
                    } else {
                        return;
                    }
                } else if (constraintWidget5.isInHorizontalChain()) {
                    this.start.margin = this.widget.mListAnchors[0].getMargin();
                    this.end.margin = -this.widget.mListAnchors[1].getMargin();
                    return;
                } else {
                    DependencyNode target3 = getTarget(this.widget.mListAnchors[0]);
                    if (target3 != null) {
                        DependencyNode dependencyNode3 = this.start;
                        int margin2 = this.widget.mListAnchors[0].getMargin();
                        dependencyNode3.targets.add(target3);
                        dependencyNode3.margin = margin2;
                        target3.dependencies.add(dependencyNode3);
                    }
                    DependencyNode target4 = getTarget(this.widget.mListAnchors[1]);
                    if (target4 != null) {
                        DependencyNode dependencyNode4 = this.end;
                        dependencyNode4.targets.add(target4);
                        dependencyNode4.margin = -this.widget.mListAnchors[1].getMargin();
                        target4.dependencies.add(dependencyNode4);
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                }
            }
        }
        if (this.dimensionBehavior == 3) {
            ConstraintWidget constraintWidget7 = this.widget;
            int i = constraintWidget7.mMatchConstraintDefaultWidth;
            if (i == 2) {
                ConstraintWidget constraintWidget8 = constraintWidget7.mParent;
                if (constraintWidget8 != null) {
                    DimensionDependency dimensionDependency2 = constraintWidget8.verticalRun.dimension;
                    dimensionDependency.targets.add(dimensionDependency2);
                    dimensionDependency2.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency3 = this.dimension;
                    dimensionDependency3.delegateToWidgetRun = true;
                    dimensionDependency3.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                }
            } else if (i == 3) {
                if (constraintWidget7.mMatchConstraintDefaultHeight == 3) {
                    this.start.updateDelegate = this;
                    this.end.updateDelegate = this;
                    VerticalWidgetRun verticalWidgetRun = constraintWidget7.verticalRun;
                    verticalWidgetRun.start.updateDelegate = this;
                    verticalWidgetRun.end.updateDelegate = this;
                    dimensionDependency.updateDelegate = this;
                    if (constraintWidget7.isInVerticalChain()) {
                        this.dimension.targets.add(this.widget.verticalRun.dimension);
                        this.widget.verticalRun.dimension.dependencies.add(this.dimension);
                        VerticalWidgetRun verticalWidgetRun2 = this.widget.verticalRun;
                        verticalWidgetRun2.dimension.updateDelegate = this;
                        this.dimension.targets.add(verticalWidgetRun2.start);
                        this.dimension.targets.add(this.widget.verticalRun.end);
                        this.widget.verticalRun.start.dependencies.add(this.dimension);
                        this.widget.verticalRun.end.dependencies.add(this.dimension);
                    } else if (this.widget.isInHorizontalChain()) {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                        this.dimension.dependencies.add(this.widget.verticalRun.dimension);
                    } else {
                        this.widget.verticalRun.dimension.targets.add(this.dimension);
                    }
                } else {
                    DimensionDependency dimensionDependency4 = constraintWidget7.verticalRun.dimension;
                    dimensionDependency.targets.add(dimensionDependency4);
                    dimensionDependency4.dependencies.add(this.dimension);
                    this.widget.verticalRun.start.dependencies.add(this.dimension);
                    this.widget.verticalRun.end.dependencies.add(this.dimension);
                    DimensionDependency dimensionDependency5 = this.dimension;
                    dimensionDependency5.delegateToWidgetRun = true;
                    dimensionDependency5.dependencies.add(this.start);
                    this.dimension.dependencies.add(this.end);
                    this.start.targets.add(this.dimension);
                    this.end.targets.add(this.dimension);
                }
            }
        }
        ConstraintWidget constraintWidget9 = this.widget;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget9.mListAnchors;
        if (constraintAnchorArr2[0].mTarget == null || constraintAnchorArr2[1].mTarget == null) {
            if (constraintAnchorArr2[0].mTarget != null) {
                DependencyNode target5 = getTarget(constraintAnchorArr2[0]);
                if (target5 != null) {
                    DependencyNode dependencyNode5 = this.start;
                    int margin3 = this.widget.mListAnchors[0].getMargin();
                    dependencyNode5.targets.add(target5);
                    dependencyNode5.margin = margin3;
                    target5.dependencies.add(dependencyNode5);
                    addTarget(this.end, this.start, 1, this.dimension);
                }
            } else if (constraintAnchorArr2[1].mTarget != null) {
                DependencyNode target6 = getTarget(constraintAnchorArr2[1]);
                if (target6 != null) {
                    DependencyNode dependencyNode6 = this.end;
                    dependencyNode6.targets.add(target6);
                    dependencyNode6.margin = -this.widget.mListAnchors[1].getMargin();
                    target6.dependencies.add(dependencyNode6);
                    addTarget(this.start, this.end, -1, this.dimension);
                }
            } else if (!(constraintWidget9 instanceof Helper) && (constraintWidget = constraintWidget9.mParent) != null) {
                addTarget(this.start, constraintWidget.horizontalRun.start, constraintWidget9.getX());
                addTarget(this.end, this.start, 1, this.dimension);
            }
        } else if (constraintWidget9.isInHorizontalChain()) {
            this.start.margin = this.widget.mListAnchors[0].getMargin();
            this.end.margin = -this.widget.mListAnchors[1].getMargin();
        } else {
            DependencyNode target7 = getTarget(this.widget.mListAnchors[0]);
            DependencyNode target8 = getTarget(this.widget.mListAnchors[1]);
            target7.dependencies.add(this);
            if (target7.resolved) {
                update(this);
            }
            target8.dependencies.add(this);
            if (target8.resolved) {
                update(this);
            }
            this.mRunType = 4;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.widget.mX = dependencyNode.value;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    public final void computeInsetRatio(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 == -1) {
            int i8 = (int) ((i7 * f) + 0.5f);
            int i9 = (int) ((i6 / f) + 0.5f);
            if (i8 <= i6 && i7 <= i7) {
                iArr[0] = i8;
                iArr[1] = i7;
            } else if (i6 <= i6 && i9 <= i7) {
                iArr[0] = i6;
                iArr[1] = i9;
            }
        } else if (i5 == 0) {
            iArr[0] = (int) ((i7 * f) + 0.5f);
            iArr[1] = i7;
        } else if (i5 == 1) {
            iArr[0] = i6;
            iArr[1] = (int) ((i6 * f) + 0.5f);
        }
    }

    public void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.dimension.resolved = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return this.dimensionBehavior != 3 || this.widget.mMatchConstraintDefaultWidth == 0;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("HorizontalRun ");
        outline13.append(this.widget.mDebugName);
        return outline13.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x028d, code lost:
        if (r15 != 1) goto L_0x02ef;
     */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r18) {
        /*
            Method dump skipped, instructions count: 1033
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}

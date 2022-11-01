package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class DependencyGraph {
    public ConstraintWidgetContainer container;
    public ConstraintWidgetContainer mContainer;
    public boolean mNeedBuildGraph = true;
    public boolean mNeedRedoMeasures = true;
    public ArrayList<WidgetRun> mRuns = new ArrayList<>();
    public BasicMeasure.Measurer mMeasurer = null;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    public ArrayList<RunGroup> mGroups = new ArrayList<>();

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        new ArrayList();
        this.container = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    public final void applyGroup(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList<RunGroup> arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.run;
        if (widgetRun.runGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.container;
            if (!(widgetRun == constraintWidgetContainer.horizontalRun || widgetRun == constraintWidgetContainer.verticalRun)) {
                if (runGroup == null) {
                    runGroup = new RunGroup(widgetRun, i2);
                    arrayList.add(runGroup);
                }
                widgetRun.runGroup = runGroup;
                runGroup.runs.add(widgetRun);
                for (Dependency dependency : widgetRun.start.dependencies) {
                    if (dependency instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency, i, 0, dependencyNode2, arrayList, runGroup);
                    }
                }
                for (Dependency dependency2 : widgetRun.end.dependencies) {
                    if (dependency2 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency2, i, 1, dependencyNode2, arrayList, runGroup);
                    }
                }
                if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                        if (dependency3 instanceof DependencyNode) {
                            applyGroup((DependencyNode) dependency3, i, 2, dependencyNode2, arrayList, runGroup);
                        }
                    }
                }
                for (DependencyNode dependencyNode3 : widgetRun.start.targets) {
                    applyGroup(dependencyNode3, i, 0, dependencyNode2, arrayList, runGroup);
                }
                for (DependencyNode dependencyNode4 : widgetRun.end.targets) {
                    applyGroup(dependencyNode4, i, 1, dependencyNode2, arrayList, runGroup);
                }
                if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    for (DependencyNode dependencyNode5 : ((VerticalWidgetRun) widgetRun).baseline.targets) {
                        applyGroup(dependencyNode5, i, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
        }
    }

    public final boolean basicMeasureWidgets(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        int i2;
        int i3;
        int i4;
        Iterator<ConstraintWidget> it = constraintWidgetContainer.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            int[] iArr = next.mListDimensionBehaviors;
            int i5 = iArr[0];
            int i6 = iArr[1];
            if (next.mVisibility == 8) {
                next.measured = true;
            } else {
                float f = next.mMatchConstraintPercentWidth;
                if (f < 1.0f && i5 == 3) {
                    next.mMatchConstraintDefaultWidth = 2;
                }
                float f2 = next.mMatchConstraintPercentHeight;
                if (f2 < 1.0f && i6 == 3) {
                    next.mMatchConstraintDefaultHeight = 2;
                }
                if (next.mDimensionRatio > 0.0f) {
                    if (i5 == 3 && (i6 == 2 || i6 == 1)) {
                        next.mMatchConstraintDefaultWidth = 3;
                    } else if (i6 == 3 && (i5 == 2 || i5 == 1)) {
                        next.mMatchConstraintDefaultHeight = 3;
                    } else if (i5 == 3 && i6 == 3) {
                        if (next.mMatchConstraintDefaultWidth == 0) {
                            next.mMatchConstraintDefaultWidth = 3;
                        }
                        if (next.mMatchConstraintDefaultHeight == 0) {
                            next.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                int i7 = (i5 == 3 && next.mMatchConstraintDefaultWidth == 1 && (next.mLeft.mTarget == null || next.mRight.mTarget == null)) ? 2 : i5;
                int i8 = (i6 == 3 && next.mMatchConstraintDefaultHeight == 1 && (next.mTop.mTarget == null || next.mBottom.mTarget == null)) ? 2 : i6;
                HorizontalWidgetRun horizontalWidgetRun = next.horizontalRun;
                horizontalWidgetRun.dimensionBehavior = i7;
                int i9 = next.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i9;
                VerticalWidgetRun verticalWidgetRun = next.verticalRun;
                verticalWidgetRun.dimensionBehavior = i8;
                int i10 = next.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i10;
                if ((i7 == 4 || i7 == 1 || i7 == 2) && (i8 == 4 || i8 == 1 || i8 == 2)) {
                    int width = next.getWidth();
                    if (i7 == 4) {
                        i = (constraintWidgetContainer.getWidth() - next.mLeft.mMargin) - next.mRight.mMargin;
                        i2 = 1;
                    } else {
                        i = width;
                        i2 = i7;
                    }
                    int height = next.getHeight();
                    if (i8 == 4) {
                        i3 = (constraintWidgetContainer.getHeight() - next.mTop.mMargin) - next.mBottom.mMargin;
                        i4 = 1;
                    } else {
                        i3 = height;
                        i4 = i8;
                    }
                    measure$enumunboxing$(next, i2, i, i4, i3);
                    next.horizontalRun.dimension.resolve(next.getWidth());
                    next.verticalRun.dimension.resolve(next.getHeight());
                    next.measured = true;
                } else {
                    if (i7 == 3 && (i8 == 2 || i8 == 1)) {
                        if (i9 == 3) {
                            if (i8 == 2) {
                                measure$enumunboxing$(next, 2, 0, 2, 0);
                            }
                            int height2 = next.getHeight();
                            measure$enumunboxing$(next, 1, (int) ((height2 * next.mDimensionRatio) + 0.5f), 1, height2);
                            next.horizontalRun.dimension.resolve(next.getWidth());
                            next.verticalRun.dimension.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i9 == 1) {
                            measure$enumunboxing$(next, 2, 0, i8, 0);
                            next.horizontalRun.dimension.wrapValue = next.getWidth();
                        } else if (i9 == 2) {
                            int[] iArr2 = constraintWidgetContainer.mListDimensionBehaviors;
                            if (iArr2[0] == 1 || iArr2[0] == 4) {
                                measure$enumunboxing$(next, 1, (int) ((f * constraintWidgetContainer.getWidth()) + 0.5f), i8, next.getHeight());
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = next.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                measure$enumunboxing$(next, 2, 0, i8, 0);
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (i8 == 3 && (i7 == 2 || i7 == 1)) {
                        if (i10 == 3) {
                            if (i7 == 2) {
                                measure$enumunboxing$(next, 2, 0, 2, 0);
                            }
                            int width2 = next.getWidth();
                            float f3 = next.mDimensionRatio;
                            if (next.mDimensionRatioSide == -1) {
                                f3 = 1.0f / f3;
                            }
                            measure$enumunboxing$(next, 1, width2, 1, (int) ((width2 * f3) + 0.5f));
                            next.horizontalRun.dimension.resolve(next.getWidth());
                            next.verticalRun.dimension.resolve(next.getHeight());
                            next.measured = true;
                        } else if (i10 == 1) {
                            measure$enumunboxing$(next, i7, 0, 2, 0);
                            next.verticalRun.dimension.wrapValue = next.getHeight();
                        } else if (i10 == 2) {
                            int[] iArr3 = constraintWidgetContainer.mListDimensionBehaviors;
                            if (iArr3[1] == 1 || iArr3[1] == 4) {
                                measure$enumunboxing$(next, i7, next.getWidth(), 1, (int) ((f2 * constraintWidgetContainer.getHeight()) + 0.5f));
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = next.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                measure$enumunboxing$(next, 2, 0, i8, 0);
                                next.horizontalRun.dimension.resolve(next.getWidth());
                                next.verticalRun.dimension.resolve(next.getHeight());
                                next.measured = true;
                            }
                        }
                    }
                    if (i7 == 3 && i8 == 3) {
                        if (i9 == 1 || i10 == 1) {
                            measure$enumunboxing$(next, 2, 0, 2, 0);
                            next.horizontalRun.dimension.wrapValue = next.getWidth();
                            next.verticalRun.dimension.wrapValue = next.getHeight();
                        } else if (i10 == 2 && i9 == 2) {
                            int[] iArr4 = constraintWidgetContainer.mListDimensionBehaviors;
                            if (iArr4[0] == 1 || iArr4[0] == 1) {
                                if (iArr4[1] == 1 || iArr4[1] == 1) {
                                    measure$enumunboxing$(next, 1, (int) ((f * constraintWidgetContainer.getWidth()) + 0.5f), 1, (int) ((f2 * constraintWidgetContainer.getHeight()) + 0.5f));
                                    next.horizontalRun.dimension.resolve(next.getWidth());
                                    next.verticalRun.dimension.resolve(next.getHeight());
                                    next.measured = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void buildGraph() {
        ArrayList<WidgetRun> arrayList = this.mRuns;
        arrayList.clear();
        this.mContainer.horizontalRun.clear();
        this.mContainer.verticalRun.clear();
        arrayList.add(this.mContainer.horizontalRun);
        arrayList.add(this.mContainer.verticalRun);
        Iterator<ConstraintWidget> it = this.mContainer.mChildren.iterator();
        HashSet hashSet = null;
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (next instanceof Guideline) {
                arrayList.add(new GuidelineReference(next));
            } else {
                if (next.isInHorizontalChain()) {
                    if (next.horizontalChainRun == null) {
                        next.horizontalChainRun = new ChainRun(next, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.horizontalChainRun);
                } else {
                    arrayList.add(next.horizontalRun);
                }
                if (next.isInVerticalChain()) {
                    if (next.verticalChainRun == null) {
                        next.verticalChainRun = new ChainRun(next, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(next.verticalChainRun);
                } else {
                    arrayList.add(next.verticalRun);
                }
                if (next instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(next));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<WidgetRun> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        Iterator<WidgetRun> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            WidgetRun next2 = it3.next();
            if (next2.widget != this.mContainer) {
                next2.apply();
            }
        }
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.container.horizontalRun, 0, this.mGroups);
        findGroup(this.container.verticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0064 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int computeWrap(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r18, int r19) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.DependencyGraph.computeWrap(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, int):int");
    }

    public final void findGroup(WidgetRun widgetRun, int i, ArrayList<RunGroup> arrayList) {
        for (Dependency dependency : widgetRun.start.dependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.dependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.dependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    public final void measure$enumunboxing$(ConstraintWidget constraintWidget, int i, int i2, int i3, int i4) {
        BasicMeasure.Measure measure = this.mMeasure;
        measure.horizontalBehavior = i;
        measure.verticalBehavior = i3;
        measure.horizontalDimension = i2;
        measure.verticalDimension = i4;
        ((ConstraintLayout.Measurer) this.mMeasurer).measure(constraintWidget, measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        BasicMeasure.Measure measure2 = this.mMeasure;
        constraintWidget.hasBaseline = measure2.measuredHasBaseline;
        int i5 = measure2.measuredBaseline;
        constraintWidget.mBaselineDistance = i5;
        constraintWidget.hasBaseline = i5 > 0;
    }

    public void measureWidgets() {
        DimensionDependency dimensionDependency;
        Iterator<ConstraintWidget> it = this.container.mChildren.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            if (!next.measured) {
                int[] iArr = next.mListDimensionBehaviors;
                boolean z = false;
                int i = iArr[0];
                int i2 = iArr[1];
                int i3 = next.mMatchConstraintDefaultWidth;
                int i4 = next.mMatchConstraintDefaultHeight;
                boolean z2 = i == 2 || (i == 3 && i3 == 1);
                if (i2 == 2 || (i2 == 3 && i4 == 1)) {
                    z = true;
                }
                DimensionDependency dimensionDependency2 = next.horizontalRun.dimension;
                boolean z3 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = next.verticalRun.dimension;
                boolean z4 = dimensionDependency3.resolved;
                if (z3 && z4) {
                    measure$enumunboxing$(next, 1, dimensionDependency2.value, 1, dimensionDependency3.value);
                    next.measured = true;
                } else if (z3 && z) {
                    measure$enumunboxing$(next, 1, dimensionDependency2.value, 2, dimensionDependency3.value);
                    if (i2 == 3) {
                        next.verticalRun.dimension.wrapValue = next.getHeight();
                    } else {
                        next.verticalRun.dimension.resolve(next.getHeight());
                        next.measured = true;
                    }
                } else if (z4 && z2) {
                    measure$enumunboxing$(next, 2, dimensionDependency2.value, 1, dimensionDependency3.value);
                    if (i == 3) {
                        next.horizontalRun.dimension.wrapValue = next.getWidth();
                    } else {
                        next.horizontalRun.dimension.resolve(next.getWidth());
                        next.measured = true;
                    }
                }
                if (next.measured && (dimensionDependency = next.verticalRun.baselineDimension) != null) {
                    dimensionDependency.resolve(next.mBaselineDistance);
                }
            }
        }
    }
}

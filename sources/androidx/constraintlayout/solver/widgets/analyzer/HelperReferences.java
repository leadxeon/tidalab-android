package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.Barrier;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
/* loaded from: classes.dex */
public class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    public final void addDependency(DependencyNode dependencyNode) {
        this.start.dependencies.add(dependencyNode);
        dependencyNode.targets.add(this.start);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget instanceof Barrier) {
            DependencyNode dependencyNode = this.start;
            dependencyNode.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) constraintWidget;
            int i = barrier.mBarrierType;
            boolean z = barrier.mAllowsGoneWidget;
            int i2 = 0;
            if (i == 0) {
                dependencyNode.type = DependencyNode.Type.LEFT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget2 = barrier.mWidgets[i2];
                    if (z || constraintWidget2.mVisibility != 8) {
                        DependencyNode dependencyNode2 = constraintWidget2.horizontalRun.start;
                        dependencyNode2.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode2);
                    }
                    i2++;
                }
                addDependency(this.widget.horizontalRun.start);
                addDependency(this.widget.horizontalRun.end);
            } else if (i == 1) {
                dependencyNode.type = DependencyNode.Type.RIGHT;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget3 = barrier.mWidgets[i2];
                    if (z || constraintWidget3.mVisibility != 8) {
                        DependencyNode dependencyNode3 = constraintWidget3.horizontalRun.end;
                        dependencyNode3.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode3);
                    }
                    i2++;
                }
                addDependency(this.widget.horizontalRun.start);
                addDependency(this.widget.horizontalRun.end);
            } else if (i == 2) {
                dependencyNode.type = DependencyNode.Type.TOP;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget4 = barrier.mWidgets[i2];
                    if (z || constraintWidget4.mVisibility != 8) {
                        DependencyNode dependencyNode4 = constraintWidget4.verticalRun.start;
                        dependencyNode4.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode4);
                    }
                    i2++;
                }
                addDependency(this.widget.verticalRun.start);
                addDependency(this.widget.verticalRun.end);
            } else if (i == 3) {
                dependencyNode.type = DependencyNode.Type.BOTTOM;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget5 = barrier.mWidgets[i2];
                    if (z || constraintWidget5.mVisibility != 8) {
                        DependencyNode dependencyNode5 = constraintWidget5.verticalRun.end;
                        dependencyNode5.dependencies.add(this.start);
                        this.start.targets.add(dependencyNode5);
                    }
                    i2++;
                }
                addDependency(this.widget.verticalRun.start);
                addDependency(this.widget.verticalRun.end);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget instanceof Barrier) {
            int i = ((Barrier) constraintWidget).mBarrierType;
            if (i == 0 || i == 1) {
                constraintWidget.mX = this.start.value;
            } else {
                constraintWidget.mY = this.start.value;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        this.start.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        Barrier barrier = (Barrier) this.widget;
        int i = barrier.mBarrierType;
        int i2 = 0;
        int i3 = -1;
        for (DependencyNode dependencyNode : this.start.targets) {
            int i4 = dependencyNode.value;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            this.start.resolve(i3 + barrier.mMargin);
        } else {
            this.start.resolve(i2 + barrier.mMargin);
        }
    }
}

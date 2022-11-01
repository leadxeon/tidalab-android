package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Guideline;
/* loaded from: classes.dex */
public class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.horizontalRun.clear();
        constraintWidget.verticalRun.clear();
        this.orientation = ((Guideline) constraintWidget).mOrientation;
    }

    public final void addDependency(DependencyNode dependencyNode) {
        this.start.dependencies.add(dependencyNode);
        dependencyNode.targets.add(this.start);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void apply() {
        ConstraintWidget constraintWidget = this.widget;
        Guideline guideline = (Guideline) constraintWidget;
        int i = guideline.mRelativeBegin;
        int i2 = guideline.mRelativeEnd;
        if (guideline.mOrientation == 1) {
            if (i != -1) {
                this.start.targets.add(constraintWidget.mParent.horizontalRun.start);
                this.widget.mParent.horizontalRun.start.dependencies.add(this.start);
                this.start.margin = i;
            } else if (i2 != -1) {
                this.start.targets.add(constraintWidget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
                this.start.margin = -i2;
            } else {
                DependencyNode dependencyNode = this.start;
                dependencyNode.delegateToWidgetRun = true;
                dependencyNode.targets.add(constraintWidget.mParent.horizontalRun.end);
                this.widget.mParent.horizontalRun.end.dependencies.add(this.start);
            }
            addDependency(this.widget.horizontalRun.start);
            addDependency(this.widget.horizontalRun.end);
            return;
        }
        if (i != -1) {
            this.start.targets.add(constraintWidget.mParent.verticalRun.start);
            this.widget.mParent.verticalRun.start.dependencies.add(this.start);
            this.start.margin = i;
        } else if (i2 != -1) {
            this.start.targets.add(constraintWidget.mParent.verticalRun.end);
            this.widget.mParent.verticalRun.end.dependencies.add(this.start);
            this.start.margin = -i2;
        } else {
            DependencyNode dependencyNode2 = this.start;
            dependencyNode2.delegateToWidgetRun = true;
            dependencyNode2.targets.add(constraintWidget.mParent.verticalRun.end);
            this.widget.mParent.verticalRun.end.dependencies.add(this.start);
        }
        addDependency(this.widget.verticalRun.start);
        addDependency(this.widget.verticalRun.end);
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        ConstraintWidget constraintWidget = this.widget;
        if (((Guideline) constraintWidget).mOrientation == 1) {
            constraintWidget.mX = this.start.value;
        } else {
            constraintWidget.mY = this.start.value;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void clear() {
        this.start.clear();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((dependencyNode.targets.get(0).value * ((Guideline) this.widget).mRelativePercent) + 0.5f));
        }
    }
}

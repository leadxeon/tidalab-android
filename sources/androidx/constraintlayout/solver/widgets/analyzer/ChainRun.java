package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    public int chainStyle;
    public ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        WidgetRun widgetRun;
        int i2;
        WidgetRun widgetRun2;
        this.orientation = i;
        ConstraintWidget constraintWidget2 = this.widget;
        constraintWidget2 = constraintWidget2.getPreviousChainMember(i);
        while (constraintWidget2 != null) {
            constraintWidget2 = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget2;
        ArrayList<WidgetRun> arrayList = this.widgets;
        int i3 = this.orientation;
        if (i3 == 0) {
            widgetRun = constraintWidget2.horizontalRun;
        } else {
            widgetRun = i3 == 1 ? constraintWidget2.verticalRun : null;
        }
        arrayList.add(widgetRun);
        ConstraintWidget nextChainMember = constraintWidget2.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            ArrayList<WidgetRun> arrayList2 = this.widgets;
            int i4 = this.orientation;
            if (i4 == 0) {
                widgetRun2 = nextChainMember.horizontalRun;
            } else {
                widgetRun2 = i4 == 1 ? nextChainMember.verticalRun : null;
            }
            arrayList2.add(widgetRun2);
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            int i5 = this.orientation;
            if (i5 == 0) {
                next.widget.horizontalChainRun = this;
            } else if (i5 == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.mParent).mIsRtl) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList3 = this.widgets;
            this.widget = arrayList3.get(arrayList3.size() - 1).widget;
        }
        if (this.orientation == 0) {
            i2 = this.widget.mHorizontalChainStyle;
        } else {
            i2 = this.widget.mVerticalChainStyle;
        }
        this.chainStyle = i2;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = this.widgets.get(0).widget;
            ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    DependencyNode dependencyNode = this.start;
                    dependencyNode.targets.add(target);
                    dependencyNode.margin = margin;
                    target.dependencies.add(dependencyNode);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    DependencyNode dependencyNode2 = this.end;
                    dependencyNode2.targets.add(target2);
                    dependencyNode2.margin = -margin2;
                    target2.dependencies.add(dependencyNode2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    DependencyNode dependencyNode3 = this.start;
                    dependencyNode3.targets.add(target3);
                    dependencyNode3.margin = margin3;
                    target3.dependencies.add(dependencyNode3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    DependencyNode dependencyNode4 = this.end;
                    dependencyNode4.targets.add(target4);
                    dependencyNode4.margin = -margin4;
                    target4.dependencies.add(dependencyNode4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    public final ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            ConstraintWidget constraintWidget = this.widgets.get(i).widget;
            if (constraintWidget.mVisibility != 8) {
                return constraintWidget;
            }
        }
        return null;
    }

    public final ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            ConstraintWidget constraintWidget = this.widgets.get(size).widget;
            if (constraintWidget.mVisibility != 8) {
                return constraintWidget;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            j = widgetRun.end.margin + widgetRun.getWrapDimension() + j + widgetRun.start.margin;
        }
        return j;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ChainRun ");
        outline13.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        String sb = outline13.toString();
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            String outline8 = GeneratedOutlineSupport.outline8(sb, "<");
            sb = GeneratedOutlineSupport.outline8(outline8 + it.next(), "> ");
        }
        return sb;
    }

    /* JADX WARN: Code restructure failed: missing block: B:275:0x03eb, code lost:
        r9 = r9 - r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d7  */
    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(androidx.constraintlayout.solver.widgets.analyzer.Dependency r26) {
        /*
            Method dump skipped, instructions count: 1042
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.analyzer.ChainRun.update(androidx.constraintlayout.solver.widgets.analyzer.Dependency):void");
    }
}

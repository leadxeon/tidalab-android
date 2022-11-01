package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class Barrier extends HelperWidget {
    public int mBarrierType = 0;
    public boolean mAllowsGoneWidget = true;
    public int mMargin = 0;

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem) {
        Object[] objArr;
        boolean z;
        int i;
        int i2;
        int i3;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        constraintAnchorArr[0] = this.mLeft;
        constraintAnchorArr[2] = this.mTop;
        constraintAnchorArr[1] = this.mRight;
        constraintAnchorArr[3] = this.mBottom;
        int i4 = 0;
        while (true) {
            objArr = this.mListAnchors;
            if (i4 >= objArr.length) {
                break;
            }
            objArr[i4].mSolverVariable = linearSystem.createObjectVariable(objArr[i4]);
            i4++;
        }
        int i5 = this.mBarrierType;
        if (i5 >= 0 && i5 < 4) {
            ConstraintAnchor constraintAnchor = objArr[i5];
            for (int i6 = 0; i6 < this.mWidgetsCount; i6++) {
                ConstraintWidget constraintWidget = this.mWidgets[i6];
                if ((this.mAllowsGoneWidget || constraintWidget.allowedInBarrier()) && ((((i2 = this.mBarrierType) == 0 || i2 == 1) && constraintWidget.getHorizontalDimensionBehaviour$enumunboxing$() == 3 && constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget != null) || (((i3 = this.mBarrierType) == 2 || i3 == 3) && constraintWidget.getVerticalDimensionBehaviour$enumunboxing$() == 3 && constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null))) {
                    z = true;
                    break;
                }
            }
            z = false;
            boolean z2 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
            boolean z3 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
            boolean z4 = !z && (((i = this.mBarrierType) == 0 && z2) || ((i == 2 && z3) || ((i == 1 && z2) || (i == 3 && z3))));
            int i7 = 5;
            if (!z4) {
                i7 = 4;
            }
            for (int i8 = 0; i8 < this.mWidgetsCount; i8++) {
                ConstraintWidget constraintWidget2 = this.mWidgets[i8];
                if (this.mAllowsGoneWidget || constraintWidget2.allowedInBarrier()) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintWidget2.mListAnchors[this.mBarrierType]);
                    ConstraintAnchor[] constraintAnchorArr2 = constraintWidget2.mListAnchors;
                    int i9 = this.mBarrierType;
                    constraintAnchorArr2[i9].mSolverVariable = createObjectVariable;
                    int i10 = (constraintAnchorArr2[i9].mTarget == null || constraintAnchorArr2[i9].mTarget.mOwner != this) ? 0 : constraintAnchorArr2[i9].mMargin + 0;
                    if (i9 == 0 || i9 == 2) {
                        ArrayRow createRow = linearSystem.createRow();
                        SolverVariable createSlackVariable = linearSystem.createSlackVariable();
                        createSlackVariable.strength = 0;
                        createRow.createRowLowerThan(constraintAnchor.mSolverVariable, createObjectVariable, createSlackVariable, this.mMargin - i10);
                        linearSystem.addConstraint(createRow);
                    } else {
                        ArrayRow createRow2 = linearSystem.createRow();
                        SolverVariable createSlackVariable2 = linearSystem.createSlackVariable();
                        createSlackVariable2.strength = 0;
                        createRow2.createRowGreaterThan(constraintAnchor.mSolverVariable, createObjectVariable, createSlackVariable2, this.mMargin + i10);
                        linearSystem.addConstraint(createRow2);
                    }
                    linearSystem.addEquality(constraintAnchor.mSolverVariable, createObjectVariable, this.mMargin + i10, i7);
                }
            }
            int i11 = this.mBarrierType;
            if (i11 == 0) {
                linearSystem.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
            } else if (i11 == 1) {
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
            } else if (i11 == 2) {
                linearSystem.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
            } else if (i11 == 3) {
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
                linearSystem.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public String toString() {
        String outline11 = GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("[Barrier] "), this.mDebugName, " {");
        for (int i = 0; i < this.mWidgetsCount; i++) {
            ConstraintWidget constraintWidget = this.mWidgets[i];
            if (i > 0) {
                outline11 = GeneratedOutlineSupport.outline8(outline11, ", ");
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(outline11);
            outline13.append(constraintWidget.mDebugName);
            outline11 = outline13.toString();
        }
        return GeneratedOutlineSupport.outline8(outline11, "}");
    }
}

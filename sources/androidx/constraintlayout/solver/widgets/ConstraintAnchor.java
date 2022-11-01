package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.SolverVariable;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.HashSet;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ConstraintAnchor {
    public final ConstraintWidget mOwner;
    public SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final Type mType;
    public HashSet<ConstraintAnchor> mDependents = null;
    public int mMargin = 0;
    public int mGoneMargin = -1;

    /* loaded from: classes.dex */
    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.mOwner = constraintWidget;
        this.mType = type;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
        if (r6.mOwner.hasBaseline == false) goto L_0x0040;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003c, code lost:
        if (r4 != r10) goto L_0x003e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0056, code lost:
        if (r4 != r10) goto L_0x0040;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006f, code lost:
        if (r4 != r2) goto L_0x0040;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor r7, int r8, int r9, boolean r10) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != 0) goto L_0x0007
            r6.reset()
            return r0
        L_0x0007:
            r1 = 0
            if (r10 != 0) goto L_0x0075
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_Y
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.CENTER_X
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r3 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r4 = r7.mType
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r5 = r6.mType
            if (r4 != r5) goto L_0x0025
            if (r5 != r3) goto L_0x003e
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r7.mOwner
            boolean r10 = r10.hasBaseline
            if (r10 == 0) goto L_0x0040
            androidx.constraintlayout.solver.widgets.ConstraintWidget r10 = r6.mOwner
            boolean r10 = r10.hasBaseline
            if (r10 != 0) goto L_0x003e
            goto L_0x0040
        L_0x0025:
            int r5 = r5.ordinal()
            switch(r5) {
                case 0: goto L_0x0040;
                case 1: goto L_0x005b;
                case 2: goto L_0x0042;
                case 3: goto L_0x005b;
                case 4: goto L_0x0042;
                case 5: goto L_0x0040;
                case 6: goto L_0x0038;
                case 7: goto L_0x0040;
                case 8: goto L_0x0040;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r8 = r6.mType
            java.lang.String r8 = r8.name()
            r7.<init>(r8)
            throw r7
        L_0x0038:
            if (r4 == r3) goto L_0x0040
            if (r4 == r2) goto L_0x0040
            if (r4 == r10) goto L_0x0040
        L_0x003e:
            r10 = 1
            goto L_0x0072
        L_0x0040:
            r10 = 0
            goto L_0x0072
        L_0x0042:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.TOP
            if (r4 == r2) goto L_0x004d
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r2 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.BOTTOM
            if (r4 != r2) goto L_0x004b
            goto L_0x004d
        L_0x004b:
            r2 = 0
            goto L_0x004e
        L_0x004d:
            r2 = 1
        L_0x004e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r7.mOwner
            boolean r3 = r3 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r3 == 0) goto L_0x0059
            if (r2 != 0) goto L_0x003e
            if (r4 != r10) goto L_0x0040
            goto L_0x003e
        L_0x0059:
            r10 = r2
            goto L_0x0072
        L_0x005b:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.LEFT
            if (r4 == r10) goto L_0x0066
            androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type r10 = androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type.RIGHT
            if (r4 != r10) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            r10 = 0
            goto L_0x0067
        L_0x0066:
            r10 = 1
        L_0x0067:
            androidx.constraintlayout.solver.widgets.ConstraintWidget r3 = r7.mOwner
            boolean r3 = r3 instanceof androidx.constraintlayout.solver.widgets.Guideline
            if (r3 == 0) goto L_0x0072
            if (r10 != 0) goto L_0x003e
            if (r4 != r2) goto L_0x0040
            goto L_0x003e
        L_0x0072:
            if (r10 != 0) goto L_0x0075
            return r1
        L_0x0075:
            r6.mTarget = r7
            java.util.HashSet<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r10 = r7.mDependents
            if (r10 != 0) goto L_0x0082
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            r7.mDependents = r10
        L_0x0082:
            androidx.constraintlayout.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            java.util.HashSet<androidx.constraintlayout.solver.widgets.ConstraintAnchor> r7 = r7.mDependents
            r7.add(r6)
            if (r8 <= 0) goto L_0x008e
            r6.mMargin = r8
            goto L_0x0090
        L_0x008e:
            r6.mMargin = r1
        L_0x0090:
            r6.mGoneMargin = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintAnchor.connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, boolean):boolean");
    }

    public int getMargin() {
        ConstraintAnchor constraintAnchor;
        if (this.mOwner.mVisibility == 8) {
            return 0;
        }
        int i = this.mGoneMargin;
        return (i <= -1 || (constraintAnchor = this.mTarget) == null || constraintAnchor.mOwner.mVisibility != 8) ? this.mMargin : i;
    }

    public boolean hasCenteredDependents() {
        ConstraintAnchor constraintAnchor;
        HashSet<ConstraintAnchor> hashSet = this.mDependents;
        if (hashSet == null) {
            return false;
        }
        Iterator<ConstraintAnchor> it = hashSet.iterator();
        while (it.hasNext()) {
            ConstraintAnchor next = it.next();
            switch (next.mType.ordinal()) {
                case 0:
                case 5:
                case 6:
                case 7:
                case 8:
                    constraintAnchor = null;
                    break;
                case 1:
                    constraintAnchor = next.mOwner.mRight;
                    break;
                case 2:
                    constraintAnchor = next.mOwner.mBottom;
                    break;
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    constraintAnchor = next.mOwner.mLeft;
                    break;
                case 4:
                    constraintAnchor = next.mOwner.mTop;
                    break;
                default:
                    throw new AssertionError(next.mType.name());
            }
            if (constraintAnchor.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnected() {
        return this.mTarget != null;
    }

    public void reset() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.mTarget;
        if (!(constraintAnchor == null || (hashSet = constraintAnchor.mDependents) == null)) {
            hashSet.remove(this);
        }
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = -1;
    }

    public void resetSolverVariable() {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(1);
        } else {
            solverVariable.reset();
        }
    }

    public String toString() {
        return this.mOwner.mDebugName + ":" + this.mType.toString();
    }
}

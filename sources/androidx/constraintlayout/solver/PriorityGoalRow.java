package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.Comparator;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    public int TABLE_SIZE = 128;
    public SolverVariable[] arrayGoals = new SolverVariable[128];
    public SolverVariable[] sortArray = new SolverVariable[128];
    public int numGoals = 0;
    public GoalVariableAccessor accessor = new GoalVariableAccessor(this);

    /* loaded from: classes.dex */
    public class GoalVariableAccessor implements Comparable {
        public SolverVariable variable;

        public GoalVariableAccessor(PriorityGoalRow priorityGoalRow) {
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.variable.id - ((SolverVariable) obj).id;
        }

        public String toString() {
            String str = "[ ";
            if (this.variable != null) {
                for (int i = 0; i < 9; i++) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
                    outline13.append(this.variable.goalStrengthVector[i]);
                    outline13.append(" ");
                    str = outline13.toString();
                }
            }
            return str + "] " + this.variable;
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        this.accessor.variable = solverVariable;
        Arrays.fill(solverVariable.goalStrengthVector, 0.0f);
        solverVariable.goalStrengthVector[solverVariable.strength] = 1.0f;
        addToGoal(solverVariable);
    }

    public final void addToGoal(SolverVariable solverVariable) {
        int i;
        int i2 = this.numGoals + 1;
        SolverVariable[] solverVariableArr = this.arrayGoals;
        if (i2 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.arrayGoals = solverVariableArr2;
            this.sortArray = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.arrayGoals;
        int i3 = this.numGoals;
        solverVariableArr3[i3] = solverVariable;
        int i4 = i3 + 1;
        this.numGoals = i4;
        if (i4 > 1 && solverVariableArr3[i4 - 1].id > solverVariable.id) {
            int i5 = 0;
            while (true) {
                i = this.numGoals;
                if (i5 >= i) {
                    break;
                }
                this.sortArray[i5] = this.arrayGoals[i5];
                i5++;
            }
            Arrays.sort(this.sortArray, 0, i, new Comparator<SolverVariable>(this) { // from class: androidx.constraintlayout.solver.PriorityGoalRow.1
                @Override // java.util.Comparator
                public int compare(SolverVariable solverVariable2, SolverVariable solverVariable3) {
                    return solverVariable2.id - solverVariable3.id;
                }
            });
            for (int i6 = 0; i6 < this.numGoals; i6++) {
                this.arrayGoals[i6] = this.sortArray[i6];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void clear() {
        this.numGoals = 0;
        this.constantValue = 0.0f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0053, code lost:
        if (r8 < r7) goto L_0x0057;
     */
    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.constraintlayout.solver.SolverVariable getPivotCandidate(androidx.constraintlayout.solver.LinearSystem r11, boolean[] r12) {
        /*
            r10 = this;
            r11 = 0
            r0 = -1
            r1 = 0
            r2 = -1
        L_0x0004:
            int r3 = r10.numGoals
            if (r1 >= r3) goto L_0x005d
            androidx.constraintlayout.solver.SolverVariable[] r3 = r10.arrayGoals
            r4 = r3[r1]
            int r5 = r4.id
            boolean r5 = r12[r5]
            if (r5 == 0) goto L_0x0013
            goto L_0x005a
        L_0x0013:
            androidx.constraintlayout.solver.PriorityGoalRow$GoalVariableAccessor r5 = r10.accessor
            r5.variable = r4
            r4 = 8
            r6 = 1
            if (r2 != r0) goto L_0x0039
            java.util.Objects.requireNonNull(r5)
        L_0x001f:
            if (r4 < 0) goto L_0x0035
            androidx.constraintlayout.solver.SolverVariable r3 = r5.variable
            float[] r3 = r3.goalStrengthVector
            r3 = r3[r4]
            r7 = 0
            int r8 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x002d
            goto L_0x0035
        L_0x002d:
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x0032
            goto L_0x0036
        L_0x0032:
            int r4 = r4 + (-1)
            goto L_0x001f
        L_0x0035:
            r6 = 0
        L_0x0036:
            if (r6 == 0) goto L_0x005a
            goto L_0x0059
        L_0x0039:
            r3 = r3[r2]
            java.util.Objects.requireNonNull(r5)
        L_0x003e:
            if (r4 < 0) goto L_0x0056
            float[] r7 = r3.goalStrengthVector
            r7 = r7[r4]
            androidx.constraintlayout.solver.SolverVariable r8 = r5.variable
            float[] r8 = r8.goalStrengthVector
            r8 = r8[r4]
            int r9 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0051
            int r4 = r4 + (-1)
            goto L_0x003e
        L_0x0051:
            int r3 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r6 = 0
        L_0x0057:
            if (r6 == 0) goto L_0x005a
        L_0x0059:
            r2 = r1
        L_0x005a:
            int r1 = r1 + 1
            goto L_0x0004
        L_0x005d:
            if (r2 != r0) goto L_0x0061
            r11 = 0
            return r11
        L_0x0061:
            androidx.constraintlayout.solver.SolverVariable[] r11 = r10.arrayGoals
            r11 = r11[r2]
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.PriorityGoalRow.getPivotCandidate(androidx.constraintlayout.solver.LinearSystem, boolean[]):androidx.constraintlayout.solver.SolverVariable");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        r5.numGoals = r2 - 1;
        r6.inGoal = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000c, code lost:
        r2 = r5.numGoals;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        if (r1 >= (r2 - 1)) goto L_0x001c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0012, code lost:
        r2 = r5.arrayGoals;
        r3 = r1 + 1;
        r2[r1] = r2[r3];
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void removeGoal(androidx.constraintlayout.solver.SolverVariable r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r5.numGoals
            if (r1 >= r2) goto L_0x0026
            androidx.constraintlayout.solver.SolverVariable[] r2 = r5.arrayGoals
            r2 = r2[r1]
            if (r2 != r6) goto L_0x0023
        L_0x000c:
            int r2 = r5.numGoals
            int r3 = r2 + (-1)
            if (r1 >= r3) goto L_0x001c
            androidx.constraintlayout.solver.SolverVariable[] r2 = r5.arrayGoals
            int r3 = r1 + 1
            r4 = r2[r3]
            r2[r1] = r4
            r1 = r3
            goto L_0x000c
        L_0x001c:
            int r2 = r2 + (-1)
            r5.numGoals = r2
            r6.inGoal = r0
            return
        L_0x0023:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.PriorityGoalRow.removeGoal(androidx.constraintlayout.solver.SolverVariable):void");
    }

    @Override // androidx.constraintlayout.solver.ArrayRow
    public String toString() {
        String str = HttpUrl.FRAGMENT_ENCODE_SET + " goal -> (" + this.constantValue + ") : ";
        for (int i = 0; i < this.numGoals; i++) {
            this.accessor.variable = this.arrayGoals[i];
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(str);
            outline13.append(this.accessor);
            outline13.append(" ");
            str = outline13.toString();
        }
        return str;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow
    public void updateFromRow(ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.variable;
        if (solverVariable != null) {
            ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
            int currentSize = arrayRowVariables.getCurrentSize();
            for (int i = 0; i < currentSize; i++) {
                SolverVariable variable = arrayRowVariables.getVariable(i);
                float variableValue = arrayRowVariables.getVariableValue(i);
                GoalVariableAccessor goalVariableAccessor = this.accessor;
                goalVariableAccessor.variable = variable;
                boolean z2 = true;
                if (variable.inGoal) {
                    for (int i2 = 0; i2 < 9; i2++) {
                        float[] fArr = goalVariableAccessor.variable.goalStrengthVector;
                        fArr[i2] = (solverVariable.goalStrengthVector[i2] * variableValue) + fArr[i2];
                        if (Math.abs(fArr[i2]) < 1.0E-4f) {
                            goalVariableAccessor.variable.goalStrengthVector[i2] = 0.0f;
                        } else {
                            z2 = false;
                        }
                    }
                    if (z2) {
                        PriorityGoalRow.this.removeGoal(goalVariableAccessor.variable);
                    }
                    z2 = false;
                } else {
                    for (int i3 = 0; i3 < 9; i3++) {
                        float f = solverVariable.goalStrengthVector[i3];
                        if (f != 0.0f) {
                            float f2 = f * variableValue;
                            if (Math.abs(f2) < 1.0E-4f) {
                                f2 = 0.0f;
                            }
                            goalVariableAccessor.variable.goalStrengthVector[i3] = f2;
                        } else {
                            goalVariableAccessor.variable.goalStrengthVector[i3] = 0.0f;
                        }
                    }
                }
                if (z2) {
                    addToGoal(variable);
                }
                this.constantValue = (arrayRow.constantValue * variableValue) + this.constantValue;
            }
            removeGoal(solverVariable);
        }
    }
}

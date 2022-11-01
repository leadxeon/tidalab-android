package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    public final Cache mCache;
    public final ArrayRow mRow;
    public int SIZE = 16;
    public int[] keys = new int[16];
    public int[] nextKeys = new int[16];
    public int[] variables = new int[16];
    public float[] values = new float[16];
    public int[] previous = new int[16];
    public int[] next = new int[16];
    public int mCount = 0;
    public int head = -1;

    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
        clear();
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        if (f <= -0.001f || f >= 0.001f) {
            int indexOf = indexOf(solverVariable);
            if (indexOf == -1) {
                put(solverVariable, f);
                return;
            }
            float[] fArr = this.values;
            fArr[indexOf] = fArr[indexOf] + f;
            if (fArr[indexOf] > -0.001f && fArr[indexOf] < 0.001f) {
                fArr[indexOf] = 0.0f;
                remove(solverVariable, z);
            }
        }
    }

    public final void addToHashMap(SolverVariable solverVariable, int i) {
        int[] iArr;
        int i2 = solverVariable.id % 16;
        int[] iArr2 = this.keys;
        int i3 = iArr2[i2];
        if (i3 == -1) {
            iArr2[i2] = i;
        } else {
            while (true) {
                iArr = this.nextKeys;
                if (iArr[i3] == -1) {
                    break;
                }
                i3 = iArr[i3];
            }
            iArr[i3] = i;
        }
        this.nextKeys[i] = -1;
    }

    public final void addVariable(int i, SolverVariable solverVariable, float f) {
        this.variables[i] = solverVariable.id;
        this.values[i] = f;
        this.previous[i] = -1;
        this.next[i] = -1;
        solverVariable.addToRow(this.mRow);
        solverVariable.usageInRowCount++;
        this.mCount++;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void clear() {
        int i = this.mCount;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                variable.removeFromRow(this.mRow);
            }
        }
        for (int i3 = 0; i3 < this.SIZE; i3++) {
            this.variables[i3] = -1;
            this.nextKeys[i3] = -1;
        }
        for (int i4 = 0; i4 < 16; i4++) {
            this.keys[i4] = -1;
        }
        this.mCount = 0;
        this.head = -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f) {
        int i = this.mCount;
        int i2 = this.head;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.values;
            fArr[i2] = fArr[i2] / f;
            i2 = this.next[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            return this.values[indexOf];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.mCount;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i) {
        int i2 = this.mCount;
        if (i2 == 0) {
            return null;
        }
        int i3 = this.head;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i && i3 != -1) {
                return this.mCache.mIndexedVariables[this.variables[i3]];
            }
            i3 = this.next[i3];
            if (i3 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i) {
        int i2 = this.mCount;
        int i3 = this.head;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 == i) {
                return this.values[i3];
            }
            i3 = this.next[i3];
            if (i3 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    public int indexOf(SolverVariable solverVariable) {
        int[] iArr;
        if (this.mCount == 0) {
            return -1;
        }
        int i = solverVariable.id;
        int i2 = this.keys[i % 16];
        if (i2 == -1) {
            return -1;
        }
        if (this.variables[i2] == i) {
            return i2;
        }
        while (true) {
            iArr = this.nextKeys;
            if (iArr[i2] == -1 || this.variables[iArr[i2]] == i) {
                break;
            }
            i2 = iArr[i2];
        }
        if (iArr[i2] != -1 && this.variables[iArr[i2]] == i) {
            return iArr[i2];
        }
        return -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i = this.mCount;
        int i2 = this.head;
        for (int i3 = 0; i3 < i; i3++) {
            float[] fArr = this.values;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.next[i2];
            if (i2 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f) {
        if (f <= -0.001f || f >= 0.001f) {
            int i = 0;
            if (this.mCount == 0) {
                addVariable(0, solverVariable, f);
                addToHashMap(solverVariable, 0);
                this.head = 0;
                return;
            }
            int indexOf = indexOf(solverVariable);
            if (indexOf != -1) {
                this.values[indexOf] = f;
                return;
            }
            int i2 = this.mCount + 1;
            int i3 = this.SIZE;
            if (i2 >= i3) {
                int i4 = i3 * 2;
                this.variables = Arrays.copyOf(this.variables, i4);
                this.values = Arrays.copyOf(this.values, i4);
                this.previous = Arrays.copyOf(this.previous, i4);
                this.next = Arrays.copyOf(this.next, i4);
                this.nextKeys = Arrays.copyOf(this.nextKeys, i4);
                for (int i5 = this.SIZE; i5 < i4; i5++) {
                    this.variables[i5] = -1;
                    this.nextKeys[i5] = -1;
                }
                this.SIZE = i4;
            }
            int i6 = this.mCount;
            int i7 = this.head;
            int i8 = -1;
            for (int i9 = 0; i9 < i6; i9++) {
                int[] iArr = this.variables;
                int i10 = iArr[i7];
                int i11 = solverVariable.id;
                if (i10 == i11) {
                    this.values[i7] = f;
                    return;
                }
                if (iArr[i7] < i11) {
                    i8 = i7;
                }
                i7 = this.next[i7];
                if (i7 == -1) {
                    break;
                }
            }
            while (true) {
                if (i >= this.SIZE) {
                    i = -1;
                    break;
                } else if (this.variables[i] == -1) {
                    break;
                } else {
                    i++;
                }
            }
            addVariable(i, solverVariable, f);
            if (i8 != -1) {
                this.previous[i] = i8;
                int[] iArr2 = this.next;
                iArr2[i] = iArr2[i8];
                iArr2[i8] = i;
            } else {
                this.previous[i] = -1;
                if (this.mCount > 0) {
                    this.next[i] = this.head;
                    this.head = i;
                } else {
                    this.next[i] = -1;
                }
            }
            int[] iArr3 = this.next;
            if (iArr3[i] != -1) {
                this.previous[iArr3[i]] = i;
            }
            addToHashMap(solverVariable, i);
            return;
        }
        remove(solverVariable, true);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z) {
        int[] iArr;
        int indexOf = indexOf(solverVariable);
        if (indexOf == -1) {
            return 0.0f;
        }
        int i = solverVariable.id;
        int i2 = i % 16;
        int[] iArr2 = this.keys;
        int i3 = iArr2[i2];
        if (i3 != -1) {
            if (this.variables[i3] == i) {
                int[] iArr3 = this.nextKeys;
                iArr2[i2] = iArr3[i3];
                iArr3[i3] = -1;
            } else {
                while (true) {
                    iArr = this.nextKeys;
                    if (iArr[i3] == -1 || this.variables[iArr[i3]] == i) {
                        break;
                    }
                    i3 = iArr[i3];
                }
                int i4 = iArr[i3];
                if (i4 != -1 && this.variables[i4] == i) {
                    iArr[i3] = iArr[i4];
                    iArr[i4] = -1;
                }
            }
        }
        float f = this.values[indexOf];
        if (this.head == indexOf) {
            this.head = this.next[indexOf];
        }
        this.variables[indexOf] = -1;
        int[] iArr4 = this.previous;
        if (iArr4[indexOf] != -1) {
            int[] iArr5 = this.next;
            iArr5[iArr4[indexOf]] = iArr5[indexOf];
        }
        int[] iArr6 = this.next;
        if (iArr6[indexOf] != -1) {
            iArr4[iArr6[indexOf]] = iArr4[indexOf];
        }
        this.mCount--;
        solverVariable.usageInRowCount--;
        if (z) {
            solverVariable.removeFromRow(this.mRow);
        }
        return f;
    }

    public String toString() {
        String str;
        String str2;
        String str3 = hashCode() + " { ";
        int i = this.mCount;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable = getVariable(i2);
            if (variable != null) {
                String str4 = str3 + variable + " = " + getVariableValue(i2) + " ";
                int indexOf = indexOf(variable);
                String outline8 = GeneratedOutlineSupport.outline8(str4, "[p: ");
                if (this.previous[indexOf] != -1) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13(outline8);
                    outline13.append(this.mCache.mIndexedVariables[this.variables[this.previous[indexOf]]]);
                    str = outline13.toString();
                } else {
                    str = GeneratedOutlineSupport.outline8(outline8, "none");
                }
                String outline82 = GeneratedOutlineSupport.outline8(str, ", n: ");
                if (this.next[indexOf] != -1) {
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13(outline82);
                    outline132.append(this.mCache.mIndexedVariables[this.variables[this.next[indexOf]]]);
                    str2 = outline132.toString();
                } else {
                    str2 = GeneratedOutlineSupport.outline8(outline82, "none");
                }
                str3 = GeneratedOutlineSupport.outline8(str2, "]");
            }
        }
        return GeneratedOutlineSupport.outline8(str3, " }");
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.variable);
        remove(arrayRow.variable, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int i = solverVariableValues.mCount;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int[] iArr = solverVariableValues.variables;
            if (iArr[i3] != -1) {
                add(this.mCache.mIndexedVariables[iArr[i3]], solverVariableValues.values[i3] * f, z);
                i2++;
            }
            i3++;
        }
        return f;
    }
}

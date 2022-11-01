package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    public final Cache mCache;
    public final ArrayRow mRow;
    public int currentSize = 0;
    public int ROW_SIZE = 8;
    public int[] mArrayIndices = new int[8];
    public int[] mArrayNextIndices = new int[8];
    public float[] mArrayValues = new float[8];
    public int mHead = -1;
    public int mLast = -1;
    public boolean mDidFillOnce = false;

    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        if (f <= -0.001f || f >= 0.001f) {
            int i = this.mHead;
            if (i == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f;
                this.mArrayIndices[0] = solverVariable.id;
                this.mArrayNextIndices[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.mRow);
                this.currentSize++;
                if (!this.mDidFillOnce) {
                    int i2 = this.mLast + 1;
                    this.mLast = i2;
                    int[] iArr = this.mArrayIndices;
                    if (i2 >= iArr.length) {
                        this.mDidFillOnce = true;
                        this.mLast = iArr.length - 1;
                        return;
                    }
                    return;
                }
                return;
            }
            int i3 = -1;
            for (int i4 = 0; i != -1 && i4 < this.currentSize; i4++) {
                int[] iArr2 = this.mArrayIndices;
                int i5 = iArr2[i];
                int i6 = solverVariable.id;
                if (i5 == i6) {
                    float[] fArr = this.mArrayValues;
                    float f2 = fArr[i] + f;
                    if (f2 > -0.001f && f2 < 0.001f) {
                        f2 = 0.0f;
                    }
                    fArr[i] = f2;
                    if (f2 == 0.0f) {
                        if (i == this.mHead) {
                            this.mHead = this.mArrayNextIndices[i];
                        } else {
                            int[] iArr3 = this.mArrayNextIndices;
                            iArr3[i3] = iArr3[i];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.mRow);
                        }
                        if (this.mDidFillOnce) {
                            this.mLast = i;
                        }
                        solverVariable.usageInRowCount--;
                        this.currentSize--;
                        return;
                    }
                    return;
                }
                if (iArr2[i] < i6) {
                    i3 = i;
                }
                i = this.mArrayNextIndices[i];
            }
            int i7 = this.mLast;
            i7++;
            if (this.mDidFillOnce) {
                int[] iArr4 = this.mArrayIndices;
                if (iArr4[i7] != -1) {
                    i7 = iArr4.length;
                }
            }
            int[] iArr5 = this.mArrayIndices;
            if (i7 >= iArr5.length && this.currentSize < iArr5.length) {
                int i8 = 0;
                while (true) {
                    int[] iArr6 = this.mArrayIndices;
                    if (i8 >= iArr6.length) {
                        break;
                    } else if (iArr6[i8] == -1) {
                        i7 = i8;
                        break;
                    } else {
                        i8++;
                    }
                }
            }
            int[] iArr7 = this.mArrayIndices;
            if (i7 >= iArr7.length) {
                i7 = iArr7.length;
                int i9 = this.ROW_SIZE * 2;
                this.ROW_SIZE = i9;
                this.mDidFillOnce = false;
                this.mLast = i7 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, i9);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
            }
            this.mArrayIndices[i7] = solverVariable.id;
            this.mArrayValues[i7] = f;
            if (i3 != -1) {
                int[] iArr8 = this.mArrayNextIndices;
                iArr8[i7] = iArr8[i3];
                iArr8[i3] = i7;
            } else {
                this.mArrayNextIndices[i7] = this.mHead;
                this.mHead = i7;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (!this.mDidFillOnce) {
                this.mLast++;
            }
            int i10 = this.mLast;
            int[] iArr9 = this.mArrayIndices;
            if (i10 >= iArr9.length) {
                this.mDidFillOnce = true;
                this.mLast = iArr9.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final void clear() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
            i = this.mArrayNextIndices[i];
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.currentSize = 0;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        int i = this.mHead;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
            i = this.mArrayNextIndices[i];
        }
        return false;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] / f;
            i = this.mArrayNextIndices[i];
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final float get(SolverVariable solverVariable) {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.currentSize;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[i2]];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i) {
        int i2 = this.mHead;
        for (int i3 = 0; i2 != -1 && i3 < this.currentSize; i3++) {
            if (i3 == i) {
                return this.mArrayValues[i2];
            }
            i2 = this.mArrayNextIndices[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i = this.mHead;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            float[] fArr = this.mArrayValues;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.mArrayNextIndices[i];
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i = this.mHead;
        if (i == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable.id;
            this.mArrayNextIndices[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            this.currentSize++;
            if (!this.mDidFillOnce) {
                int i2 = this.mLast + 1;
                this.mLast = i2;
                int[] iArr = this.mArrayIndices;
                if (i2 >= iArr.length) {
                    this.mDidFillOnce = true;
                    this.mLast = iArr.length - 1;
                    return;
                }
                return;
            }
            return;
        }
        int i3 = -1;
        for (int i4 = 0; i != -1 && i4 < this.currentSize; i4++) {
            int[] iArr2 = this.mArrayIndices;
            int i5 = iArr2[i];
            int i6 = solverVariable.id;
            if (i5 == i6) {
                this.mArrayValues[i] = f;
                return;
            }
            if (iArr2[i] < i6) {
                i3 = i;
            }
            i = this.mArrayNextIndices[i];
        }
        int i7 = this.mLast;
        i7++;
        if (this.mDidFillOnce) {
            int[] iArr3 = this.mArrayIndices;
            if (iArr3[i7] != -1) {
                i7 = iArr3.length;
            }
        }
        int[] iArr4 = this.mArrayIndices;
        if (i7 >= iArr4.length && this.currentSize < iArr4.length) {
            int i8 = 0;
            while (true) {
                int[] iArr5 = this.mArrayIndices;
                if (i8 >= iArr5.length) {
                    break;
                } else if (iArr5[i8] == -1) {
                    i7 = i8;
                    break;
                } else {
                    i8++;
                }
            }
        }
        int[] iArr6 = this.mArrayIndices;
        if (i7 >= iArr6.length) {
            i7 = iArr6.length;
            int i9 = this.ROW_SIZE * 2;
            this.ROW_SIZE = i9;
            this.mDidFillOnce = false;
            this.mLast = i7 - 1;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, i9);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.ROW_SIZE);
        }
        this.mArrayIndices[i7] = solverVariable.id;
        this.mArrayValues[i7] = f;
        if (i3 != -1) {
            int[] iArr7 = this.mArrayNextIndices;
            iArr7[i7] = iArr7[i3];
            iArr7[i3] = i7;
        } else {
            this.mArrayNextIndices[i7] = this.mHead;
            this.mHead = i7;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        int i10 = this.currentSize + 1;
        this.currentSize = i10;
        if (!this.mDidFillOnce) {
            this.mLast++;
        }
        int[] iArr8 = this.mArrayIndices;
        if (i10 >= iArr8.length) {
            this.mDidFillOnce = true;
        }
        if (this.mLast >= iArr8.length) {
            this.mDidFillOnce = true;
            this.mLast = iArr8.length - 1;
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final float remove(SolverVariable solverVariable, boolean z) {
        int i = this.mHead;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.currentSize) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                if (i == this.mHead) {
                    this.mHead = this.mArrayNextIndices[i];
                } else {
                    int[] iArr = this.mArrayNextIndices;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    solverVariable.removeFromRow(this.mRow);
                }
                solverVariable.usageInRowCount--;
                this.currentSize--;
                this.mArrayIndices[i] = -1;
                if (this.mDidFillOnce) {
                    this.mLast = i;
                }
                return this.mArrayValues[i];
            }
            i = this.mArrayNextIndices[i];
            i2++;
            i3 = i;
        }
        return 0.0f;
    }

    public String toString() {
        int i = this.mHead;
        String str = HttpUrl.FRAGMENT_ENCODE_SET;
        for (int i2 = 0; i != -1 && i2 < this.currentSize; i2++) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13(GeneratedOutlineSupport.outline8(str, " -> "));
            outline13.append(this.mArrayValues[i]);
            outline13.append(" : ");
            StringBuilder outline132 = GeneratedOutlineSupport.outline13(outline13.toString());
            outline132.append(this.mCache.mIndexedVariables[this.mArrayIndices[i]]);
            str = outline132.toString();
            i = this.mArrayNextIndices[i];
        }
        return str;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.variable);
        remove(arrayRow.variable, z);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i = 0; i < currentSize; i++) {
            SolverVariable variable = arrayRowVariables.getVariable(i);
            add(variable, arrayRowVariables.get(variable) * f, z);
        }
        return f;
    }
}

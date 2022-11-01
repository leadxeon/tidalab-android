package androidx.constraintlayout.solver;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Arrays;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class SolverVariable {
    public static int uniqueErrorId = 1;
    public float computedValue;
    public boolean inGoal;
    public int mType;
    public int id = -1;
    public int definitionId = -1;
    public int strength = 0;
    public boolean isFinalValue = false;
    public float[] strengthVector = new float[9];
    public float[] goalStrengthVector = new float[9];
    public ArrayRow[] mClientEquations = new ArrayRow[16];
    public int mClientEquationsCount = 0;
    public int usageInRowCount = 0;

    public SolverVariable(int i) {
        this.mType = i;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i = 0;
        while (true) {
            int i2 = this.mClientEquationsCount;
            if (i >= i2) {
                ArrayRow[] arrayRowArr = this.mClientEquations;
                if (i2 >= arrayRowArr.length) {
                    this.mClientEquations = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.mClientEquations;
                int i3 = this.mClientEquationsCount;
                arrayRowArr2[i3] = arrayRow;
                this.mClientEquationsCount = i3 + 1;
                return;
            } else if (this.mClientEquations[i] != arrayRow) {
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000d, code lost:
        if (r1 >= (r0 - 1)) goto L_0x0019;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x000f, code lost:
        r5 = r4.mClientEquations;
        r2 = r1 + 1;
        r5[r1] = r5[r2];
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
        r4.mClientEquationsCount--;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void removeFromRow(androidx.constraintlayout.solver.ArrayRow r5) {
        /*
            r4 = this;
            int r0 = r4.mClientEquationsCount
            r1 = 0
        L_0x0003:
            if (r1 >= r0) goto L_0x0023
            androidx.constraintlayout.solver.ArrayRow[] r2 = r4.mClientEquations
            r2 = r2[r1]
            if (r2 != r5) goto L_0x0020
        L_0x000b:
            int r5 = r0 + (-1)
            if (r1 >= r5) goto L_0x0019
            androidx.constraintlayout.solver.ArrayRow[] r5 = r4.mClientEquations
            int r2 = r1 + 1
            r3 = r5[r2]
            r5[r1] = r3
            r1 = r2
            goto L_0x000b
        L_0x0019:
            int r5 = r4.mClientEquationsCount
            int r5 = r5 + (-1)
            r4.mClientEquationsCount = r5
            return
        L_0x0020:
            int r1 = r1 + 1
            goto L_0x0003
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.SolverVariable.removeFromRow(androidx.constraintlayout.solver.ArrayRow):void");
    }

    public void reset() {
        this.mType = 5;
        this.strength = 0;
        this.id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2] = null;
        }
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.goalStrengthVector, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].updateFromFinalVariable(this, false);
        }
        this.mClientEquationsCount = 0;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(HttpUrl.FRAGMENT_ENCODE_SET);
        outline13.append(this.id);
        return outline13.toString();
    }

    public final void updateReferencesWithNewDefinition(ArrayRow arrayRow) {
        int i = this.mClientEquationsCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.mClientEquations[i2].updateFromRow(arrayRow, false);
        }
        this.mClientEquationsCount = 0;
    }
}

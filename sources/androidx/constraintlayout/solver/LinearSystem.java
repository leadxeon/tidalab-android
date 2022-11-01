package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = true;
    public static int POOL_SIZE = 1000;
    public final Cache mCache;
    public Row mGoal;
    public ArrayRow[] mRows;
    public Row mTempGoal;
    public int mVariablesID = 0;
    public int TABLE_SIZE = 32;
    public int mMaxColumns = 32;
    public boolean newgraphOptimizer = false;
    public boolean[] mAlreadyTestedCandidates = new boolean[32];
    public int mNumColumns = 1;
    public int mNumRows = 0;
    public int mMaxRows = 32;
    public SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
    public int mPoolVariablesCount = 0;

    /* loaded from: classes.dex */
    public interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);
    }

    /* loaded from: classes.dex */
    public class ValuesRow extends ArrayRow {
        public ValuesRow(LinearSystem linearSystem, Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.mRows = null;
        this.mRows = new ArrayRow[32];
        releaseRows();
        Cache cache = new Cache();
        this.mCache = cache;
        this.mGoal = new PriorityGoalRow(cache);
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this, cache);
        } else {
            this.mTempGoal = new ArrayRow(cache);
        }
    }

    public final SolverVariable acquireSolverVariable$enumunboxing$(int i, String str) {
        SolverVariable acquire = this.mCache.solverVariablePool.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(i);
            acquire.mType = i;
        } else {
            acquire.reset();
            acquire.mType = i;
        }
        int i2 = this.mPoolVariablesCount;
        int i3 = POOL_SIZE;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            POOL_SIZE = i4;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, i4);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i5 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = i5 + 1;
        solverVariableArr[i5] = acquire;
        return acquire;
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2, int i3) {
        ArrayRow createRow = createRow();
        if (solverVariable2 == solverVariable3) {
            createRow.variables.put(solverVariable, 1.0f);
            createRow.variables.put(solverVariable4, 1.0f);
            createRow.variables.put(solverVariable2, -2.0f);
        } else if (f == 0.5f) {
            createRow.variables.put(solverVariable, 1.0f);
            createRow.variables.put(solverVariable2, -1.0f);
            createRow.variables.put(solverVariable3, -1.0f);
            createRow.variables.put(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                createRow.constantValue = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            createRow.variables.put(solverVariable, -1.0f);
            createRow.variables.put(solverVariable2, 1.0f);
            createRow.constantValue = i;
        } else if (f >= 1.0f) {
            createRow.variables.put(solverVariable4, -1.0f);
            createRow.variables.put(solverVariable3, 1.0f);
            createRow.constantValue = -i2;
        } else {
            float f2 = 1.0f - f;
            createRow.variables.put(solverVariable, f2 * 1.0f);
            createRow.variables.put(solverVariable2, f2 * (-1.0f));
            createRow.variables.put(solverVariable3, (-1.0f) * f);
            createRow.variables.put(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                createRow.constantValue = (i2 * f) + ((-i) * f2);
            }
        }
        if (i3 != 8) {
            createRow.addError(this, i3);
        }
        addConstraint(createRow);
    }

    public void addConstraint(ArrayRow arrayRow) {
        boolean z;
        boolean z2;
        SolverVariable pickPivotInVariables;
        boolean z3 = true;
        if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (!arrayRow.isSimpleDefinition) {
            if (this.mRows.length != 0) {
                boolean z4 = false;
                while (!z4) {
                    int currentSize = arrayRow.variables.getCurrentSize();
                    for (int i = 0; i < currentSize; i++) {
                        SolverVariable variable = arrayRow.variables.getVariable(i);
                        if (variable.definitionId != -1 || variable.isFinalValue) {
                            arrayRow.variablesToUpdate.add(variable);
                        }
                    }
                    if (arrayRow.variablesToUpdate.size() > 0) {
                        Iterator<SolverVariable> it = arrayRow.variablesToUpdate.iterator();
                        while (it.hasNext()) {
                            SolverVariable next = it.next();
                            if (next.isFinalValue) {
                                arrayRow.updateFromFinalVariable(next, true);
                            } else {
                                arrayRow.updateFromRow(this.mRows[next.definitionId], true);
                            }
                        }
                        arrayRow.variablesToUpdate.clear();
                    } else {
                        z4 = true;
                    }
                }
            }
            if (!(arrayRow.variable == null && arrayRow.constantValue == 0.0f && arrayRow.variables.getCurrentSize() == 0)) {
                float f = arrayRow.constantValue;
                if (f < 0.0f) {
                    arrayRow.constantValue = f * (-1.0f);
                    arrayRow.variables.invert();
                }
                int currentSize2 = arrayRow.variables.getCurrentSize();
                SolverVariable solverVariable = null;
                SolverVariable solverVariable2 = null;
                float f2 = 0.0f;
                boolean z5 = false;
                float f3 = 0.0f;
                boolean z6 = false;
                for (int i2 = 0; i2 < currentSize2; i2++) {
                    float variableValue = arrayRow.variables.getVariableValue(i2);
                    SolverVariable variable2 = arrayRow.variables.getVariable(i2);
                    if (variable2.mType == 1) {
                        if (solverVariable == null) {
                            z5 = arrayRow.isNew(variable2);
                        } else if (f2 > variableValue) {
                            z5 = arrayRow.isNew(variable2);
                        } else if (!z5 && arrayRow.isNew(variable2)) {
                            solverVariable = variable2;
                            f2 = variableValue;
                            z5 = true;
                        }
                        solverVariable = variable2;
                        f2 = variableValue;
                    } else if (solverVariable == null && variableValue < 0.0f) {
                        if (solverVariable2 == null) {
                            z6 = arrayRow.isNew(variable2);
                        } else if (f3 > variableValue) {
                            z6 = arrayRow.isNew(variable2);
                        } else if (!z6 && arrayRow.isNew(variable2)) {
                            solverVariable2 = variable2;
                            f3 = variableValue;
                            z6 = true;
                        }
                        solverVariable2 = variable2;
                        f3 = variableValue;
                    }
                }
                if (solverVariable == null) {
                    solverVariable = solverVariable2;
                }
                if (solverVariable == null) {
                    z2 = true;
                } else {
                    arrayRow.pivot(solverVariable);
                    z2 = false;
                }
                if (arrayRow.variables.getCurrentSize() == 0) {
                    arrayRow.isSimpleDefinition = true;
                }
                if (z2) {
                    if (this.mNumColumns + 1 >= this.mMaxColumns) {
                        increaseTableSize();
                    }
                    SolverVariable acquireSolverVariable$enumunboxing$ = acquireSolverVariable$enumunboxing$(3, null);
                    int i3 = this.mVariablesID + 1;
                    this.mVariablesID = i3;
                    this.mNumColumns++;
                    acquireSolverVariable$enumunboxing$.id = i3;
                    this.mCache.mIndexedVariables[i3] = acquireSolverVariable$enumunboxing$;
                    arrayRow.variable = acquireSolverVariable$enumunboxing$;
                    addRow(arrayRow);
                    ArrayRow arrayRow2 = (ArrayRow) this.mTempGoal;
                    Objects.requireNonNull(arrayRow2);
                    arrayRow2.variable = null;
                    arrayRow2.variables.clear();
                    for (int i4 = 0; i4 < arrayRow.variables.getCurrentSize(); i4++) {
                        arrayRow2.variables.add(arrayRow.variables.getVariable(i4), arrayRow.variables.getVariableValue(i4), true);
                    }
                    optimize(this.mTempGoal);
                    if (acquireSolverVariable$enumunboxing$.definitionId == -1) {
                        if (arrayRow.variable == acquireSolverVariable$enumunboxing$ && (pickPivotInVariables = arrayRow.pickPivotInVariables(null, acquireSolverVariable$enumunboxing$)) != null) {
                            arrayRow.pivot(pickPivotInVariables);
                        }
                        if (!arrayRow.isSimpleDefinition) {
                            arrayRow.variable.updateReferencesWithNewDefinition(arrayRow);
                        }
                        this.mNumRows--;
                    }
                    z = true;
                } else {
                    z = false;
                }
                SolverVariable solverVariable3 = arrayRow.variable;
                if (solverVariable3 == null || (solverVariable3.mType != 1 && arrayRow.constantValue < 0.0f)) {
                    z3 = false;
                }
                if (!z3) {
                    return;
                }
            } else {
                return;
            }
        } else {
            z = false;
        }
        if (!z) {
            addRow(arrayRow);
        }
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        if (i2 == 8 && solverVariable2.isFinalValue && solverVariable.definitionId == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i);
            return null;
        }
        ArrayRow createRow = createRow();
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            createRow.constantValue = i;
        }
        if (!z) {
            createRow.variables.put(solverVariable, -1.0f);
            createRow.variables.put(solverVariable2, 1.0f);
        } else {
            createRow.variables.put(solverVariable, 1.0f);
            createRow.variables.put(solverVariable2, -1.0f);
        }
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            createRow.variables.put(createErrorVariable(i2, null), (int) (createRow.variables.get(createSlackVariable) * (-1.0f)));
        }
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i, int i2) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i);
        if (i2 != 8) {
            createRow.variables.put(createErrorVariable(i2, null), (int) (createRow.variables.get(createSlackVariable) * (-1.0f)));
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i != 8) {
            createRow.addError(this, i);
        }
        addConstraint(createRow);
    }

    public final void addRow(ArrayRow arrayRow) {
        if (OPTIMIZED_ENGINE) {
            ArrayRow[] arrayRowArr = this.mRows;
            int i = this.mNumRows;
            if (arrayRowArr[i] != null) {
                this.mCache.optimizedArrayRowPool.release(arrayRowArr[i]);
            }
        } else {
            ArrayRow[] arrayRowArr2 = this.mRows;
            int i2 = this.mNumRows;
            if (arrayRowArr2[i2] != null) {
                this.mCache.arrayRowPool.release(arrayRowArr2[i2]);
            }
        }
        ArrayRow[] arrayRowArr3 = this.mRows;
        int i3 = this.mNumRows;
        arrayRowArr3[i3] = arrayRow;
        SolverVariable solverVariable = arrayRow.variable;
        solverVariable.definitionId = i3;
        this.mNumRows = i3 + 1;
        solverVariable.updateReferencesWithNewDefinition(arrayRow);
    }

    public final void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow arrayRow = this.mRows[i];
            arrayRow.variable.computedValue = arrayRow.constantValue;
        }
    }

    public SolverVariable createErrorVariable(int i, String str) {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$enumunboxing$ = acquireSolverVariable$enumunboxing$(4, str);
        int i2 = this.mVariablesID + 1;
        this.mVariablesID = i2;
        this.mNumColumns++;
        acquireSolverVariable$enumunboxing$.id = i2;
        acquireSolverVariable$enumunboxing$.strength = i;
        this.mCache.mIndexedVariables[i2] = acquireSolverVariable$enumunboxing$;
        this.mGoal.addError(acquireSolverVariable$enumunboxing$);
        return acquireSolverVariable$enumunboxing$;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.mSolverVariable;
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable();
                solverVariable = constraintAnchor.mSolverVariable;
            }
            int i = solverVariable.id;
            if (i == -1 || i > this.mVariablesID || this.mCache.mIndexedVariables[i] == null) {
                if (i != -1) {
                    solverVariable.reset();
                }
                int i2 = this.mVariablesID + 1;
                this.mVariablesID = i2;
                this.mNumColumns++;
                solverVariable.id = i2;
                solverVariable.mType = 1;
                this.mCache.mIndexedVariables[i2] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow;
        if (OPTIMIZED_ENGINE) {
            arrayRow = this.mCache.optimizedArrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ValuesRow(this, this.mCache);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            } else {
                arrayRow.variable = null;
                arrayRow.variables.clear();
                arrayRow.constantValue = 0.0f;
                arrayRow.isSimpleDefinition = false;
            }
        } else {
            arrayRow = this.mCache.arrayRowPool.acquire();
            if (arrayRow == null) {
                arrayRow = new ArrayRow(this.mCache);
                ARRAY_ROW_CREATION++;
            } else {
                arrayRow.variable = null;
                arrayRow.variables.clear();
                arrayRow.constantValue = 0.0f;
                arrayRow.isSimpleDefinition = false;
            }
        }
        SolverVariable.uniqueErrorId++;
        return arrayRow;
    }

    public SolverVariable createSlackVariable() {
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable acquireSolverVariable$enumunboxing$ = acquireSolverVariable$enumunboxing$(3, null);
        int i = this.mVariablesID + 1;
        this.mVariablesID = i;
        this.mNumColumns++;
        acquireSolverVariable$enumunboxing$.id = i;
        this.mCache.mIndexedVariables[i] = acquireSolverVariable$enumunboxing$;
        return acquireSolverVariable$enumunboxing$;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).mSolverVariable;
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    public final void increaseTableSize() {
        int i = this.TABLE_SIZE * 2;
        this.TABLE_SIZE = i;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, i);
        Cache cache = this.mCache;
        cache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(cache.mIndexedVariables, this.TABLE_SIZE);
        int i2 = this.TABLE_SIZE;
        this.mAlreadyTestedCandidates = new boolean[i2];
        this.mMaxColumns = i2;
        this.mMaxRows = i2;
    }

    public void minimizeGoal(Row row) throws Exception {
        float f;
        boolean z;
        int i = 0;
        while (true) {
            f = 0.0f;
            if (i >= this.mNumRows) {
                z = false;
                break;
            }
            ArrayRow[] arrayRowArr = this.mRows;
            if (arrayRowArr[i].variable.mType != 1 && arrayRowArr[i].constantValue < 0.0f) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            boolean z2 = false;
            int i2 = 0;
            while (!z2) {
                i2++;
                float f2 = Float.MAX_VALUE;
                int i3 = 0;
                int i4 = -1;
                int i5 = -1;
                int i6 = 0;
                while (i3 < this.mNumRows) {
                    ArrayRow arrayRow = this.mRows[i3];
                    if (arrayRow.variable.mType != 1 && !arrayRow.isSimpleDefinition && arrayRow.constantValue < f) {
                        int i7 = 1;
                        while (i7 < this.mNumColumns) {
                            SolverVariable solverVariable = this.mCache.mIndexedVariables[i7];
                            float f3 = arrayRow.variables.get(solverVariable);
                            if (f3 > f) {
                                for (int i8 = 0; i8 < 9; i8++) {
                                    float f4 = solverVariable.strengthVector[i8] / f3;
                                    if ((f4 < f2 && i8 == i6) || i8 > i6) {
                                        i6 = i8;
                                        f2 = f4;
                                        i4 = i3;
                                        i5 = i7;
                                    }
                                }
                            }
                            i7++;
                            f = 0.0f;
                        }
                    }
                    i3++;
                    f = 0.0f;
                }
                if (i4 != -1) {
                    ArrayRow arrayRow2 = this.mRows[i4];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(this.mCache.mIndexedVariables[i5]);
                    SolverVariable solverVariable2 = arrayRow2.variable;
                    solverVariable2.definitionId = i4;
                    solverVariable2.updateReferencesWithNewDefinition(arrayRow2);
                } else {
                    z2 = true;
                }
                if (i2 > this.mNumColumns / 2) {
                    z2 = true;
                }
                f = 0.0f;
            }
        }
        optimize(row);
        computeValues();
    }

    public final int optimize(Row row) {
        for (int i = 0; i < this.mNumColumns; i++) {
            this.mAlreadyTestedCandidates[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            if (i2 >= this.mNumColumns * 2) {
                return i2;
            }
            SolverVariable solverVariable = ((ArrayRow) row).variable;
            if (solverVariable != null) {
                this.mAlreadyTestedCandidates[solverVariable.id] = true;
            }
            SolverVariable pivotCandidate = row.getPivotCandidate(this, this.mAlreadyTestedCandidates);
            if (pivotCandidate != null) {
                boolean[] zArr = this.mAlreadyTestedCandidates;
                int i3 = pivotCandidate.id;
                if (zArr[i3]) {
                    return i2;
                }
                zArr[i3] = true;
            }
            if (pivotCandidate != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.mNumRows; i5++) {
                    ArrayRow arrayRow = this.mRows[i5];
                    if (arrayRow.variable.mType != 1 && !arrayRow.isSimpleDefinition && arrayRow.variables.contains(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.constantValue) / f2;
                            if (f3 < f) {
                                i4 = i5;
                                f = f3;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    ArrayRow arrayRow2 = this.mRows[i4];
                    arrayRow2.variable.definitionId = -1;
                    arrayRow2.pivot(pivotCandidate);
                    SolverVariable solverVariable2 = arrayRow2.variable;
                    solverVariable2.definitionId = i4;
                    solverVariable2.updateReferencesWithNewDefinition(arrayRow2);
                }
            } else {
                z = true;
            }
        }
        return i2;
    }

    public final void releaseRows() {
        int i = 0;
        if (OPTIMIZED_ENGINE) {
            while (true) {
                ArrayRow[] arrayRowArr = this.mRows;
                if (i < arrayRowArr.length) {
                    ArrayRow arrayRow = arrayRowArr[i];
                    if (arrayRow != null) {
                        this.mCache.optimizedArrayRowPool.release(arrayRow);
                    }
                    this.mRows[i] = null;
                    i++;
                } else {
                    return;
                }
            }
        } else {
            while (true) {
                ArrayRow[] arrayRowArr2 = this.mRows;
                if (i < arrayRowArr2.length) {
                    ArrayRow arrayRow2 = arrayRowArr2[i];
                    if (arrayRow2 != null) {
                        this.mCache.arrayRowPool.release(arrayRow2);
                    }
                    this.mRows[i] = null;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void reset() {
        Cache cache;
        int i = 0;
        while (true) {
            cache = this.mCache;
            SolverVariable[] solverVariableArr = cache.mIndexedVariables;
            if (i >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i++;
        }
        Pools$SimplePool<SolverVariable> pools$SimplePool = cache.solverVariablePool;
        SolverVariable[] solverVariableArr2 = this.mPoolVariables;
        int i2 = this.mPoolVariablesCount;
        Objects.requireNonNull(pools$SimplePool);
        if (i2 > solverVariableArr2.length) {
            i2 = solverVariableArr2.length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable solverVariable2 = solverVariableArr2[i3];
            int i4 = pools$SimplePool.mPoolSize;
            Object[] objArr = pools$SimplePool.mPool;
            if (i4 < objArr.length) {
                objArr[i4] = solverVariable2;
                pools$SimplePool.mPoolSize = i4 + 1;
            }
        }
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i5 = 0; i5 < this.mNumRows; i5++) {
            Objects.requireNonNull(this.mRows[i5]);
        }
        releaseRows();
        this.mNumRows = 0;
        if (OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this, this.mCache);
        } else {
            this.mTempGoal = new ArrayRow(this.mCache);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        int i2 = solverVariable.definitionId;
        if (i2 == -1) {
            solverVariable.setFinalValue(this, i);
        } else if (i2 != -1) {
            ArrayRow arrayRow = this.mRows[i2];
            if (arrayRow.isSimpleDefinition) {
                arrayRow.constantValue = i;
            } else if (arrayRow.variables.getCurrentSize() == 0) {
                arrayRow.isSimpleDefinition = true;
                arrayRow.constantValue = i;
            } else {
                ArrayRow createRow = createRow();
                if (i < 0) {
                    createRow.constantValue = i * (-1);
                    createRow.variables.put(solverVariable, 1.0f);
                } else {
                    createRow.constantValue = i;
                    createRow.variables.put(solverVariable, -1.0f);
                }
                addConstraint(createRow);
            }
        } else {
            ArrayRow createRow2 = createRow();
            createRow2.variable = solverVariable;
            float f = i;
            solverVariable.computedValue = f;
            createRow2.constantValue = f;
            createRow2.isSimpleDefinition = true;
            addConstraint(createRow2);
        }
    }
}

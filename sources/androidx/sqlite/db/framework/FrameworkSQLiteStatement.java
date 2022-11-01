package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
/* loaded from: classes.dex */
public class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    public final SQLiteStatement mDelegate;

    public FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.mDelegate = sQLiteStatement;
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public long executeInsert() {
        return this.mDelegate.executeInsert();
    }

    @Override // androidx.sqlite.db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        return this.mDelegate.executeUpdateDelete();
    }
}

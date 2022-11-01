package androidx.sqlite.db;
/* loaded from: classes.dex */
public interface SupportSQLiteStatement extends SupportSQLiteProgram {
    long executeInsert();

    int executeUpdateDelete();
}

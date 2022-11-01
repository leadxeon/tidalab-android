package androidx.sqlite.db;
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    public final String mQuery;

    public SimpleSQLiteQuery(String str) {
        this.mQuery = str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }
}

package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
/* loaded from: classes.dex */
public abstract class EntityInsertionAdapter<T> extends SharedSQLiteStatement {
    public EntityInsertionAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    public final void insert(T t) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t);
            acquire.executeInsert();
            if (acquire == this.mStmt) {
                this.mLock.set(false);
            }
        } catch (Throwable th) {
            release(acquire);
            throw th;
        }
    }
}

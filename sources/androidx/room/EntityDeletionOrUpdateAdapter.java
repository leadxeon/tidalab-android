package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
/* loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter<T> extends SharedSQLiteStatement {
    public EntityDeletionOrUpdateAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    public abstract void bind(SupportSQLiteStatement supportSQLiteStatement, T t);

    public final int handle(T t) {
        SupportSQLiteStatement acquire = acquire();
        try {
            bind(acquire, t);
            int executeUpdateDelete = acquire.executeUpdateDelete();
            if (acquire == this.mStmt) {
                this.mLock.set(false);
            }
            return executeUpdateDelete;
        } catch (Throwable th) {
            release(acquire);
            throw th;
        }
    }
}

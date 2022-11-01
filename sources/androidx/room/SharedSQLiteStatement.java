package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class SharedSQLiteStatement {
    public final RoomDatabase mDatabase;
    public final AtomicBoolean mLock = new AtomicBoolean(false);
    public volatile SupportSQLiteStatement mStmt;

    public SharedSQLiteStatement(RoomDatabase roomDatabase) {
        this.mDatabase = roomDatabase;
    }

    public SupportSQLiteStatement acquire() {
        this.mDatabase.assertNotMainThread();
        if (!this.mLock.compareAndSet(false, true)) {
            return createNewStatement();
        }
        if (this.mStmt == null) {
            this.mStmt = createNewStatement();
        }
        return this.mStmt;
    }

    public final SupportSQLiteStatement createNewStatement() {
        String createQuery = createQuery();
        RoomDatabase roomDatabase = this.mDatabase;
        roomDatabase.assertNotMainThread();
        roomDatabase.assertNotSuspendingTransaction();
        return roomDatabase.mOpenHelper.getWritableDatabase().compileStatement(createQuery);
    }

    public abstract String createQuery();

    public void release(SupportSQLiteStatement supportSQLiteStatement) {
        if (supportSQLiteStatement == this.mStmt) {
            this.mLock.set(false);
        }
    }
}

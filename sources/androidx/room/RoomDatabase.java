package androidx.room;

import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public abstract class RoomDatabase {
    public boolean mAllowMainThreadQueries;
    public AutoCloser mAutoCloser;
    @Deprecated
    public List<Callback> mCallbacks;
    @Deprecated
    public volatile SupportSQLiteDatabase mDatabase;
    public SupportSQLiteOpenHelper mOpenHelper;
    public Executor mQueryExecutor;
    public Executor mTransactionExecutor;
    public boolean mWriteAheadLoggingEnabled;
    public final ReentrantReadWriteLock mCloseLock = new ReentrantReadWriteLock();
    public final ThreadLocal<Integer> mSuspendingTransactionId = new ThreadLocal<>();
    public final Map<String, Object> mBackingFieldMap = Collections.synchronizedMap(new HashMap());
    public final InvalidationTracker mInvalidationTracker = createInvalidationTracker();
    public final Map<Class<?>, Object> mTypeConverters = new HashMap();

    /* loaded from: classes.dex */
    public static abstract class Callback {
    }

    /* loaded from: classes.dex */
    public static class MigrationContainer {
        public HashMap<Integer, TreeMap<Integer, Migration>> mMigrations = new HashMap<>();
    }

    public void assertNotMainThread() {
        if (!this.mAllowMainThreadQueries) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
            }
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.mSuspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated
    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser == null) {
            internalBeginTransaction();
        } else {
            Objects.requireNonNull(autoCloser);
            throw null;
        }
    }

    public abstract InvalidationTracker createInvalidationTracker();

    public abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    @Deprecated
    public void endTransaction() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser == null) {
            internalEndTransaction();
        } else {
            Objects.requireNonNull(autoCloser);
            throw null;
        }
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return Collections.emptyMap();
    }

    public boolean inTransaction() {
        return this.mOpenHelper.getWritableDatabase().inTransaction();
    }

    public final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mInvalidationTracker.syncTriggers(writableDatabase);
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    public final void internalEndTransaction() {
        this.mOpenHelper.getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            InvalidationTracker invalidationTracker = this.mInvalidationTracker;
            if (!invalidationTracker.mPendingRefresh.compareAndSet(false, true)) {
                return;
            }
            if (invalidationTracker.mAutoCloser == null) {
                invalidationTracker.mDatabase.mQueryExecutor.execute(invalidationTracker.mRefreshRunnable);
                return;
            }
            throw null;
        }
    }

    public boolean isOpen() {
        AutoCloser autoCloser = this.mAutoCloser;
        if (autoCloser != null) {
            return !autoCloser.mManuallyClosed;
        }
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        return supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen();
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery, cancellationSignal);
        }
        return this.mOpenHelper.getWritableDatabase().query(supportSQLiteQuery);
    }

    @Deprecated
    public void setTransactionSuccessful() {
        this.mOpenHelper.getWritableDatabase().setTransactionSuccessful();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T unwrapOpenHelper(Class<T> cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return (T) unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }
}

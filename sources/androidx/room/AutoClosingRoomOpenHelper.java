package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    public final AutoClosingSupportSQLiteDatabase mAutoClosingDb;
    public final SupportSQLiteOpenHelper mDelegateOpenHelper;

    /* loaded from: classes.dex */
    public static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        public final AutoCloser mAutoCloser;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw null;
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.mAutoClosingDb.close();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mDelegateOpenHelper.getDatabaseName();
    }

    @Override // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegateOpenHelper;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        Objects.requireNonNull(this.mAutoClosingDb.mAutoCloser);
        throw null;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.mDelegateOpenHelper.setWriteAheadLoggingEnabled(z);
    }
}

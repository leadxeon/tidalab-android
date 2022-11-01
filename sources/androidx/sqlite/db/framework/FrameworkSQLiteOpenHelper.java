package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public final SupportSQLiteOpenHelper.Callback mCallback;
    public final Context mContext;
    public OpenHelper mDelegate;
    public final Object mLock = new Object();
    public final String mName;
    public final boolean mUseNoBackupDirectory;
    public boolean mWriteAheadLoggingEnabled;

    /* loaded from: classes.dex */
    public static class OpenHelper extends SQLiteOpenHelper {
        public final SupportSQLiteOpenHelper.Callback mCallback;
        public final FrameworkSQLiteDatabase[] mDbRef;
        public boolean mMigrated;

        public OpenHelper(Context context, String str, final FrameworkSQLiteDatabase[] frameworkSQLiteDatabaseArr, final SupportSQLiteOpenHelper.Callback callback) {
            super(context, str, null, callback.version, new DatabaseErrorHandler() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.1
                @Override // android.database.DatabaseErrorHandler
                public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    SupportSQLiteOpenHelper.Callback callback2 = SupportSQLiteOpenHelper.Callback.this;
                    FrameworkSQLiteDatabase wrappedDb = OpenHelper.getWrappedDb(frameworkSQLiteDatabaseArr, sQLiteDatabase);
                    Objects.requireNonNull(callback2);
                    Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + wrappedDb.getPath());
                    if (!wrappedDb.isOpen()) {
                        callback2.deleteDatabaseFile(wrappedDb.getPath());
                        return;
                    }
                    List<Pair<String, String>> list = null;
                    try {
                        try {
                            list = wrappedDb.getAttachedDbs();
                        } finally {
                            if (list != null) {
                                for (Pair<String, String> next : list) {
                                    callback2.deleteDatabaseFile((String) next.second);
                                }
                            } else {
                                callback2.deleteDatabaseFile(wrappedDb.getPath());
                            }
                        }
                    } catch (SQLiteException unused) {
                    }
                    try {
                        wrappedDb.close();
                    } catch (IOException unused2) {
                    }
                }
            });
            this.mCallback = callback;
            this.mDbRef = frameworkSQLiteDatabaseArr;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.mDbRef[0] = null;
        }

        public FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            return getWrappedDb(this.mDbRef, sQLiteDatabase);
        }

        public synchronized SupportSQLiteDatabase getWritableSupportDatabase() {
            this.mMigrated = false;
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (this.mMigrated) {
                close();
                return getWritableSupportDatabase();
            }
            return getWrappedDb(writableDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.mCallback.onConfigure(getWrappedDb(this.mDbRef, sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.mCallback.onCreate(getWrappedDb(this.mDbRef, sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.mMigrated = true;
            this.mCallback.onDowngrade(getWrappedDb(this.mDbRef, sQLiteDatabase), i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (!this.mMigrated) {
                this.mCallback.onOpen(getWrappedDb(this.mDbRef, sQLiteDatabase));
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.mMigrated = true;
            this.mCallback.onUpgrade(getWrappedDb(this.mDbRef, sQLiteDatabase), i, i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x000c, code lost:
            if ((r1.mDelegate == r3) == false) goto L_0x000e;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static androidx.sqlite.db.framework.FrameworkSQLiteDatabase getWrappedDb(androidx.sqlite.db.framework.FrameworkSQLiteDatabase[] r2, android.database.sqlite.SQLiteDatabase r3) {
            /*
                r0 = 0
                r1 = r2[r0]
                if (r1 == 0) goto L_0x000e
                android.database.sqlite.SQLiteDatabase r1 = r1.mDelegate
                if (r1 != r3) goto L_0x000b
                r1 = 1
                goto L_0x000c
            L_0x000b:
                r1 = 0
            L_0x000c:
                if (r1 != 0) goto L_0x0015
            L_0x000e:
                androidx.sqlite.db.framework.FrameworkSQLiteDatabase r1 = new androidx.sqlite.db.framework.FrameworkSQLiteDatabase
                r1.<init>(r3)
                r2[r0] = r1
            L_0x0015:
                r2 = r2[r0]
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.getWrappedDb(androidx.sqlite.db.framework.FrameworkSQLiteDatabase[], android.database.sqlite.SQLiteDatabase):androidx.sqlite.db.framework.FrameworkSQLiteDatabase");
        }
    }

    public FrameworkSQLiteOpenHelper(Context context, String str, SupportSQLiteOpenHelper.Callback callback, boolean z) {
        this.mContext = context;
        this.mName = str;
        this.mCallback = callback;
        this.mUseNoBackupDirectory = z;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        getDelegate().close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.mName;
    }

    public final OpenHelper getDelegate() {
        OpenHelper openHelper;
        synchronized (this.mLock) {
            if (this.mDelegate == null) {
                FrameworkSQLiteDatabase[] frameworkSQLiteDatabaseArr = new FrameworkSQLiteDatabase[1];
                if (Build.VERSION.SDK_INT < 23 || this.mName == null || !this.mUseNoBackupDirectory) {
                    this.mDelegate = new OpenHelper(this.mContext, this.mName, frameworkSQLiteDatabaseArr, this.mCallback);
                } else {
                    this.mDelegate = new OpenHelper(this.mContext, new File(this.mContext.getNoBackupFilesDir(), this.mName).getAbsolutePath(), frameworkSQLiteDatabaseArr, this.mCallback);
                }
                this.mDelegate.setWriteAheadLoggingEnabled(this.mWriteAheadLoggingEnabled);
            }
            openHelper = this.mDelegate;
        }
        return openHelper;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getWritableSupportDatabase();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        synchronized (this.mLock) {
            OpenHelper openHelper = this.mDelegate;
            if (openHelper != null) {
                openHelper.setWriteAheadLoggingEnabled(z);
            }
            this.mWriteAheadLoggingEnabled = z;
        }
    }
}

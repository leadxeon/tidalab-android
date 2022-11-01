package androidx.sqlite.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public final int version;

        public Callback(int i) {
            this.version = i;
        }

        public final void deleteDatabaseFile(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                Log.w("SupportSQLite", "deleting the database file: " + str);
                try {
                    SQLiteDatabase.deleteDatabase(new File(str));
                } catch (Exception e) {
                    Log.w("SupportSQLite", "delete failed: ", e);
                }
            }
        }

        public abstract void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);
    }

    /* loaded from: classes.dex */
    public interface Factory {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);
}

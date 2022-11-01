package androidx.room;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class DatabaseConfiguration {
    public final Context context;
    public final int journalMode;
    public final RoomDatabase.MigrationContainer migrationContainer;
    public final String name;
    public final Executor queryExecutor;
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;
    public final List<Object> typeConverters;

    @SuppressLint({"LambdaLast"})
    public DatabaseConfiguration(Context context, String str, SupportSQLiteOpenHelper.Factory factory, RoomDatabase.MigrationContainer migrationContainer, List list, boolean z, int i, Executor executor, Executor executor2, boolean z2, boolean z3, boolean z4, Set set, String str2, File file, Callable callable, List list2) {
        this.sqliteOpenHelperFactory = factory;
        this.context = context;
        this.name = str;
        this.migrationContainer = migrationContainer;
        this.journalMode = i;
        this.queryExecutor = executor;
        this.transactionExecutor = executor2;
        this.typeConverters = list2 == null ? Collections.emptyList() : list2;
    }

    public boolean isMigrationRequired(int i, int i2) {
        if (i > i2) {
        }
        return true;
    }
}

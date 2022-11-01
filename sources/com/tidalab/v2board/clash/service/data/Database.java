package com.tidalab.v2board.clash.service.data;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.AutoClosingRoomOpenHelper;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomDatabase;
import androidx.room.SQLiteCopyOpenHelper;
import androidx.room.TransactionExecutor;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.data.migrations.MigrationsKt;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: Database.kt */
/* loaded from: classes.dex */
public abstract class Database extends RoomDatabase {
    public static final Companion Companion = new Companion(null);
    public static SoftReference<Database> softDatabase = new SoftReference<>(null);

    /* compiled from: Database.kt */
    /* loaded from: classes.dex */
    public static final class Companion {

        /* compiled from: Database.kt */
        @DebugMetadata(c = "com.tidalab.v2board.clash.service.data.Database$Companion$1", f = "Database.kt", l = {44}, m = "invokeSuspend")
        /* renamed from: com.tidalab.v2board.clash.service.data.Database$Companion$1  reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public int label;

            public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    InputKt.throwOnFailure(obj);
                    Application application = Global.INSTANCE.getApplication();
                    this.label = 1;
                    if (((Function2) MigrationsKt.LEGACY_MIGRATION).invoke(application, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (i == 1) {
                    InputKt.throwOnFailure(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final synchronized Database getDatabase() {
            Database database;
            database = Database.softDatabase.get();
            if (database == null) {
                database = open(Global.INSTANCE.getApplication());
                Companion companion = Database.Companion;
                Database.softDatabase = new SoftReference<>(database);
            }
            return database;
        }

        public final Database open(Context context) {
            Context applicationContext = context.getApplicationContext();
            RoomDatabase.MigrationContainer migrationContainer = new RoomDatabase.MigrationContainer();
            Migration[] migrationArr = MigrationsKt.MIGRATIONS;
            Migration[] migrationArr2 = MigrationsKt.MIGRATIONS;
            Migration[] migrationArr3 = (Migration[]) Arrays.copyOf(migrationArr2, migrationArr2.length);
            HashSet hashSet = new HashSet();
            for (Migration migration : migrationArr3) {
                Objects.requireNonNull(migration);
                hashSet.add(0);
                hashSet.add(0);
            }
            for (Migration migration2 : migrationArr3) {
                Objects.requireNonNull(migration2);
                TreeMap<Integer, Migration> treeMap = migrationContainer.mMigrations.get(0);
                if (treeMap == null) {
                    treeMap = new TreeMap<>();
                    migrationContainer.mMigrations.put(0, treeMap);
                }
                Migration migration3 = treeMap.get(0);
                if (migration3 != null) {
                    Log.w("ROOM", "Overriding migration " + migration3 + " with " + migration2);
                }
                treeMap.put(0, migration2);
            }
            if (applicationContext != null) {
                Executor executor = ArchTaskExecutor.sIOThreadExecutor;
                FrameworkSQLiteOpenHelperFactory frameworkSQLiteOpenHelperFactory = new FrameworkSQLiteOpenHelperFactory();
                ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
                int i = (activityManager == null || activityManager.isLowRamDevice()) ? 2 : 3;
                DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(applicationContext, "profiles", frameworkSQLiteOpenHelperFactory, migrationContainer, null, false, i, executor, executor, false, true, false, null, null, null, null, null);
                String name = Database.class.getPackage().getName();
                String canonicalName = Database.class.getCanonicalName();
                if (!name.isEmpty()) {
                    canonicalName = canonicalName.substring(name.length() + 1);
                }
                String str = canonicalName.replace('.', '_') + "_Impl";
                try {
                    RoomDatabase roomDatabase = (RoomDatabase) Class.forName(name.isEmpty() ? str : name + "." + str, true, Database.class.getClassLoader()).newInstance();
                    SupportSQLiteOpenHelper createOpenHelper = roomDatabase.createOpenHelper(databaseConfiguration);
                    roomDatabase.mOpenHelper = createOpenHelper;
                    SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) roomDatabase.unwrapOpenHelper(SQLiteCopyOpenHelper.class, createOpenHelper);
                    if (sQLiteCopyOpenHelper != null) {
                        sQLiteCopyOpenHelper.mDatabaseConfiguration = databaseConfiguration;
                    }
                    if (((AutoClosingRoomOpenHelper) roomDatabase.unwrapOpenHelper(AutoClosingRoomOpenHelper.class, roomDatabase.mOpenHelper)) == null) {
                        boolean z = i == 3;
                        roomDatabase.mOpenHelper.setWriteAheadLoggingEnabled(z);
                        roomDatabase.mCallbacks = null;
                        roomDatabase.mQueryExecutor = executor;
                        roomDatabase.mTransactionExecutor = new TransactionExecutor(executor);
                        roomDatabase.mAllowMainThreadQueries = false;
                        roomDatabase.mWriteAheadLoggingEnabled = z;
                        Map<Class<?>, List<Class<?>>> requiredTypeConverters = roomDatabase.getRequiredTypeConverters();
                        BitSet bitSet = new BitSet();
                        for (Map.Entry<Class<?>, List<Class<?>>> entry : requiredTypeConverters.entrySet()) {
                            Class<?> key = entry.getKey();
                            for (Class<?> cls : entry.getValue()) {
                                int size = databaseConfiguration.typeConverters.size() - 1;
                                while (true) {
                                    if (size < 0) {
                                        size = -1;
                                        break;
                                    } else if (cls.isAssignableFrom(databaseConfiguration.typeConverters.get(size).getClass())) {
                                        bitSet.set(size);
                                        break;
                                    } else {
                                        size--;
                                    }
                                }
                                if (size >= 0) {
                                    roomDatabase.mTypeConverters.put(cls, databaseConfiguration.typeConverters.get(size));
                                } else {
                                    throw new IllegalArgumentException("A required type converter (" + cls + ") for " + key.getCanonicalName() + " is missing in the database configuration.");
                                }
                            }
                        }
                        for (int size2 = databaseConfiguration.typeConverters.size() - 1; size2 >= 0; size2--) {
                            if (!bitSet.get(size2)) {
                                throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size2) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                            }
                        }
                        return (Database) roomDatabase;
                    }
                    Objects.requireNonNull(roomDatabase.mInvalidationTracker);
                    throw null;
                } catch (ClassNotFoundException unused) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("cannot find implementation for ");
                    outline13.append(Database.class.getCanonicalName());
                    outline13.append(". ");
                    outline13.append(str);
                    outline13.append(" does not exist");
                    throw new RuntimeException(outline13.toString());
                } catch (IllegalAccessException unused2) {
                    StringBuilder outline132 = GeneratedOutlineSupport.outline13("Cannot access the constructor");
                    outline132.append(Database.class.getCanonicalName());
                    throw new RuntimeException(outline132.toString());
                } catch (InstantiationException unused3) {
                    StringBuilder outline133 = GeneratedOutlineSupport.outline13("Failed to create an instance of ");
                    outline133.append(Database.class.getCanonicalName());
                    throw new RuntimeException(outline133.toString());
                }
            } else {
                throw new IllegalArgumentException("Cannot provide null context for the database.");
            }
        }
    }

    static {
        Global global = Global.INSTANCE;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        InputKt.launch$default(global, Dispatchers.IO, null, new Companion.AnonymousClass1(null), 2, null);
    }

    public abstract ImportedDao openImportedDao();

    public abstract PendingDao openPendingDao();

    public abstract SelectionDao openSelectionProxyDao();
}

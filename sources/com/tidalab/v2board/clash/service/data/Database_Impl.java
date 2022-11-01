package com.tidalab.v2board.clash.service.data;

import android.content.Context;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class Database_Impl extends Database {
    public static final /* synthetic */ int $r8$clinit = 0;
    public volatile ImportedDao _importedDao;
    public volatile PendingDao _pendingDao;
    public volatile SelectionDao _selectionDao;

    /* renamed from: com.tidalab.v2board.clash.service.data.Database_Impl$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 extends RoomOpenHelper.Delegate {
        public AnonymousClass1(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `imported` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `source` TEXT NOT NULL, `interval` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`uuid`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `pending` (`uuid` TEXT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `source` TEXT NOT NULL, `interval` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`uuid`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `selections` (`uuid` TEXT NOT NULL, `proxy` TEXT NOT NULL, `selected` TEXT NOT NULL, PRIMARY KEY(`uuid`, `proxy`), FOREIGN KEY(`uuid`) REFERENCES `imported`(`uuid`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '344f4abf0a10cb27a43e94dd31b449c9')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(6);
            hashMap.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, 1));
            hashMap.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
            hashMap.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
            hashMap.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, 1));
            hashMap.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
            hashMap.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("imported", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "imported");
            if (!tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(false, "imported(com.tidalab.v2board.clash.service.data.Imported).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
            HashMap hashMap2 = new HashMap(6);
            hashMap2.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, 1));
            hashMap2.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, 1));
            hashMap2.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
            hashMap2.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, 1));
            hashMap2.put("interval", new TableInfo.Column("interval", "INTEGER", true, 0, null, 1));
            hashMap2.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo2 = new TableInfo("pending", hashMap2, new HashSet(0), new HashSet(0));
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "pending");
            if (!tableInfo2.equals(read2)) {
                return new RoomOpenHelper.ValidationResult(false, "pending(com.tidalab.v2board.clash.service.data.Pending).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
            }
            HashMap hashMap3 = new HashMap(3);
            hashMap3.put("uuid", new TableInfo.Column("uuid", "TEXT", true, 1, null, 1));
            hashMap3.put("proxy", new TableInfo.Column("proxy", "TEXT", true, 2, null, 1));
            hashMap3.put("selected", new TableInfo.Column("selected", "TEXT", true, 0, null, 1));
            HashSet hashSet = new HashSet(1);
            hashSet.add(new TableInfo.ForeignKey("imported", "CASCADE", "CASCADE", Arrays.asList("uuid"), Arrays.asList("uuid")));
            TableInfo tableInfo3 = new TableInfo("selections", hashMap3, hashSet, new HashSet(0));
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "selections");
            if (tableInfo3.equals(read3)) {
                return new RoomOpenHelper.ValidationResult(true, null);
            }
            return new RoomOpenHelper.ValidationResult(false, "selections(com.tidalab.v2board.clash.service.data.Selection).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
        }
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "imported", "pending", "selections");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        RoomOpenHelper roomOpenHelper = new RoomOpenHelper(databaseConfiguration, new AnonymousClass1(1), "344f4abf0a10cb27a43e94dd31b449c9", "08c06d320345bb682d17f10faa6f9e6f");
        Context context = databaseConfiguration.context;
        String str = databaseConfiguration.name;
        if (context != null) {
            FrameworkSQLiteOpenHelperFactory frameworkSQLiteOpenHelperFactory = (FrameworkSQLiteOpenHelperFactory) databaseConfiguration.sqliteOpenHelperFactory;
            return new FrameworkSQLiteOpenHelper(context, str, roomOpenHelper, false);
        }
        throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
    }

    @Override // androidx.room.RoomDatabase
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(ImportedDao.class, Collections.emptyList());
        hashMap.put(PendingDao.class, Collections.emptyList());
        hashMap.put(SelectionDao.class, Collections.emptyList());
        return hashMap;
    }

    @Override // com.tidalab.v2board.clash.service.data.Database
    public ImportedDao openImportedDao() {
        ImportedDao importedDao;
        if (this._importedDao != null) {
            return this._importedDao;
        }
        synchronized (this) {
            if (this._importedDao == null) {
                this._importedDao = new ImportedDao_Impl(this);
            }
            importedDao = this._importedDao;
        }
        return importedDao;
    }

    @Override // com.tidalab.v2board.clash.service.data.Database
    public PendingDao openPendingDao() {
        PendingDao pendingDao;
        if (this._pendingDao != null) {
            return this._pendingDao;
        }
        synchronized (this) {
            if (this._pendingDao == null) {
                this._pendingDao = new PendingDao_Impl(this);
            }
            pendingDao = this._pendingDao;
        }
        return pendingDao;
    }

    @Override // com.tidalab.v2board.clash.service.data.Database
    public SelectionDao openSelectionProxyDao() {
        SelectionDao selectionDao;
        if (this._selectionDao != null) {
            return this._selectionDao;
        }
        synchronized (this) {
            if (this._selectionDao == null) {
                this._selectionDao = new SelectionDao_Impl(this);
            }
            selectionDao = this._selectionDao;
        }
        return selectionDao;
    }
}

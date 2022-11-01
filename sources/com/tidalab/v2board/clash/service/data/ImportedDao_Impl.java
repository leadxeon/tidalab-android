package com.tidalab.v2board.clash.service.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.recyclerview.R$dimen;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* loaded from: classes.dex */
public final class ImportedDao_Impl implements ImportedDao {
    public final Converters __converters = new Converters();
    public final RoomDatabase __db;
    public final EntityInsertionAdapter<Imported> __insertionAdapterOfImported;
    public final SharedSQLiteStatement __preparedStmtOfRemove;
    public final EntityDeletionOrUpdateAdapter<Imported> __updateAdapterOfImported;

    public ImportedDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfImported = new EntityInsertionAdapter<Imported>(roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Imported imported) {
                Imported imported2 = imported;
                Converters converters = ImportedDao_Impl.this.__converters;
                UUID uuid = imported2.uuid;
                Objects.requireNonNull(converters);
                supportSQLiteStatement.bindString(1, uuid.toString());
                String str = imported2.name;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                Converters converters2 = ImportedDao_Impl.this.__converters;
                Profile.Type type = imported2.type;
                Objects.requireNonNull(converters2);
                String name = type.name();
                if (name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, name);
                }
                String str2 = imported2.source;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str2);
                }
                supportSQLiteStatement.bindLong(5, imported2.interval);
                supportSQLiteStatement.bindLong(6, imported2.createdAt);
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `imported` (`uuid`,`name`,`type`,`source`,`interval`,`createdAt`) VALUES (?,?,?,?,?,?)";
            }
        };
        this.__updateAdapterOfImported = new EntityDeletionOrUpdateAdapter<Imported>(roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Imported imported) {
                Imported imported2 = imported;
                Converters converters = ImportedDao_Impl.this.__converters;
                UUID uuid = imported2.uuid;
                Objects.requireNonNull(converters);
                supportSQLiteStatement.bindString(1, uuid.toString());
                String str = imported2.name;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                Converters converters2 = ImportedDao_Impl.this.__converters;
                Profile.Type type = imported2.type;
                Objects.requireNonNull(converters2);
                String name = type.name();
                if (name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, name);
                }
                String str2 = imported2.source;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str2);
                }
                supportSQLiteStatement.bindLong(5, imported2.interval);
                supportSQLiteStatement.bindLong(6, imported2.createdAt);
                Converters converters3 = ImportedDao_Impl.this.__converters;
                UUID uuid2 = imported2.uuid;
                Objects.requireNonNull(converters3);
                supportSQLiteStatement.bindString(7, uuid2.toString());
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `imported` SET `uuid` = ?,`name` = ?,`type` = ?,`source` = ?,`interval` = ?,`createdAt` = ? WHERE `uuid` = ?";
            }
        };
        this.__preparedStmtOfRemove = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM imported WHERE uuid = ?";
            }
        };
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object exists(UUID uuid, Continuation<? super Boolean> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT EXISTS(SELECT 1 FROM imported WHERE uuid = ?)", 1);
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<Boolean>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.9
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                boolean z = false;
                Boolean bool = null;
                Cursor query = DBUtil.query(ImportedDao_Impl.this.__db, acquire, false, null);
                try {
                    if (query.moveToFirst()) {
                        Integer valueOf = query.isNull(0) ? null : Integer.valueOf(query.getInt(0));
                        if (valueOf != null) {
                            if (valueOf.intValue() != 0) {
                                z = true;
                            }
                            bool = Boolean.valueOf(z);
                        }
                    }
                    return bool;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object insert(final Imported imported, Continuation<? super Long> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Long>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                ImportedDao_Impl.this.__db.beginTransaction();
                try {
                    EntityInsertionAdapter<Imported> entityInsertionAdapter = ImportedDao_Impl.this.__insertionAdapterOfImported;
                    Imported imported2 = imported;
                    SupportSQLiteStatement acquire = entityInsertionAdapter.acquire();
                    entityInsertionAdapter.bind(acquire, imported2);
                    long executeInsert = acquire.executeInsert();
                    if (acquire == entityInsertionAdapter.mStmt) {
                        entityInsertionAdapter.mLock.set(false);
                    }
                    ImportedDao_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(executeInsert);
                } finally {
                    ImportedDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object queryAllUUIDs(Continuation<? super List<UUID>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT uuid FROM imported ORDER BY createdAt", 0);
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<List<UUID>>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.8
            @Override // java.util.concurrent.Callable
            public List<UUID> call() throws Exception {
                Cursor query = DBUtil.query(ImportedDao_Impl.this.__db, acquire, false, null);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.isNull(0) ? null : query.getString(0);
                        Objects.requireNonNull(ImportedDao_Impl.this.__converters);
                        arrayList.add(UUID.fromString(string));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object queryByUUID(UUID uuid, Continuation<? super Imported> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM imported WHERE uuid = ?", 1);
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<Imported>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Imported call() throws Exception {
                Imported imported = null;
                String string = null;
                Cursor query = DBUtil.query(ImportedDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = R$dimen.getColumnIndexOrThrow(query, "uuid");
                    int columnIndexOrThrow2 = R$dimen.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = R$dimen.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow4 = R$dimen.getColumnIndexOrThrow(query, "source");
                    int columnIndexOrThrow5 = R$dimen.getColumnIndexOrThrow(query, "interval");
                    int columnIndexOrThrow6 = R$dimen.getColumnIndexOrThrow(query, "createdAt");
                    if (query.moveToFirst()) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        Objects.requireNonNull(ImportedDao_Impl.this.__converters);
                        UUID fromString = UUID.fromString(string2);
                        String string3 = query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2);
                        String string4 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                        Objects.requireNonNull(ImportedDao_Impl.this.__converters);
                        Profile.Type valueOf = Profile.Type.valueOf(string4);
                        if (!query.isNull(columnIndexOrThrow4)) {
                            string = query.getString(columnIndexOrThrow4);
                        }
                        imported = new Imported(fromString, string3, valueOf, string, query.getLong(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6));
                    }
                    return imported;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object remove(final UUID uuid, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = ImportedDao_Impl.this.__preparedStmtOfRemove.acquire();
                Converters converters = ImportedDao_Impl.this.__converters;
                UUID uuid2 = uuid;
                Objects.requireNonNull(converters);
                acquire.bindString(1, uuid2.toString());
                ImportedDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    ImportedDao_Impl.this.__db.setTransactionSuccessful();
                    Unit unit = Unit.INSTANCE;
                    ImportedDao_Impl.this.__db.endTransaction();
                    SharedSQLiteStatement sharedSQLiteStatement = ImportedDao_Impl.this.__preparedStmtOfRemove;
                    if (acquire == sharedSQLiteStatement.mStmt) {
                        sharedSQLiteStatement.mLock.set(false);
                    }
                    return unit;
                } catch (Throwable th) {
                    ImportedDao_Impl.this.__db.endTransaction();
                    ImportedDao_Impl.this.__preparedStmtOfRemove.release(acquire);
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.ImportedDao
    public Object update(final Imported imported, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.ImportedDao_Impl.5
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                ImportedDao_Impl.this.__db.beginTransaction();
                try {
                    ImportedDao_Impl.this.__updateAdapterOfImported.handle(imported);
                    ImportedDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    ImportedDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }
}

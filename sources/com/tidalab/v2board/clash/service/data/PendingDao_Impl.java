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
public final class PendingDao_Impl implements PendingDao {
    public final Converters __converters = new Converters();
    public final RoomDatabase __db;
    public final EntityInsertionAdapter<Pending> __insertionAdapterOfPending;
    public final SharedSQLiteStatement __preparedStmtOfRemove;
    public final EntityDeletionOrUpdateAdapter<Pending> __updateAdapterOfPending;

    public PendingDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPending = new EntityInsertionAdapter<Pending>(roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Pending pending) {
                Pending pending2 = pending;
                Converters converters = PendingDao_Impl.this.__converters;
                UUID uuid = pending2.uuid;
                Objects.requireNonNull(converters);
                supportSQLiteStatement.bindString(1, uuid.toString());
                String str = pending2.name;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                Converters converters2 = PendingDao_Impl.this.__converters;
                Profile.Type type = pending2.type;
                Objects.requireNonNull(converters2);
                String name = type.name();
                if (name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, name);
                }
                String str2 = pending2.source;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str2);
                }
                supportSQLiteStatement.bindLong(5, pending2.interval);
                supportSQLiteStatement.bindLong(6, pending2.createdAt);
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `pending` (`uuid`,`name`,`type`,`source`,`interval`,`createdAt`) VALUES (?,?,?,?,?,?)";
            }
        };
        this.__updateAdapterOfPending = new EntityDeletionOrUpdateAdapter<Pending>(roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Pending pending) {
                Pending pending2 = pending;
                Converters converters = PendingDao_Impl.this.__converters;
                UUID uuid = pending2.uuid;
                Objects.requireNonNull(converters);
                supportSQLiteStatement.bindString(1, uuid.toString());
                String str = pending2.name;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                Converters converters2 = PendingDao_Impl.this.__converters;
                Profile.Type type = pending2.type;
                Objects.requireNonNull(converters2);
                String name = type.name();
                if (name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, name);
                }
                String str2 = pending2.source;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str2);
                }
                supportSQLiteStatement.bindLong(5, pending2.interval);
                supportSQLiteStatement.bindLong(6, pending2.createdAt);
                Converters converters3 = PendingDao_Impl.this.__converters;
                UUID uuid2 = pending2.uuid;
                Objects.requireNonNull(converters3);
                supportSQLiteStatement.bindString(7, uuid2.toString());
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR REPLACE `pending` SET `uuid` = ?,`name` = ?,`type` = ?,`source` = ?,`interval` = ?,`createdAt` = ? WHERE `uuid` = ?";
            }
        };
        this.__preparedStmtOfRemove = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM pending WHERE uuid = ?";
            }
        };
    }

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object exists(UUID uuid, Continuation<? super Boolean> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT EXISTS(SELECT 1 FROM pending WHERE uuid = ?)", 1);
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<Boolean>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.8
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                boolean z = false;
                Boolean bool = null;
                Cursor query = DBUtil.query(PendingDao_Impl.this.__db, acquire, false, null);
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

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object insert(final Pending pending, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                PendingDao_Impl.this.__db.beginTransaction();
                try {
                    PendingDao_Impl.this.__insertionAdapterOfPending.insert(pending);
                    PendingDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    PendingDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object queryAllUUIDs(Continuation<? super List<UUID>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT uuid FROM pending ORDER BY createdAt", 0);
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<List<UUID>>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.9
            @Override // java.util.concurrent.Callable
            public List<UUID> call() throws Exception {
                Cursor query = DBUtil.query(PendingDao_Impl.this.__db, acquire, false, null);
                try {
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.isNull(0) ? null : query.getString(0);
                        Objects.requireNonNull(PendingDao_Impl.this.__converters);
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

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object queryByUUID(UUID uuid, Continuation<? super Pending> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM pending WHERE uuid = ?", 1);
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<Pending>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Pending call() throws Exception {
                Pending pending = null;
                String string = null;
                Cursor query = DBUtil.query(PendingDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = R$dimen.getColumnIndexOrThrow(query, "uuid");
                    int columnIndexOrThrow2 = R$dimen.getColumnIndexOrThrow(query, "name");
                    int columnIndexOrThrow3 = R$dimen.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow4 = R$dimen.getColumnIndexOrThrow(query, "source");
                    int columnIndexOrThrow5 = R$dimen.getColumnIndexOrThrow(query, "interval");
                    int columnIndexOrThrow6 = R$dimen.getColumnIndexOrThrow(query, "createdAt");
                    if (query.moveToFirst()) {
                        String string2 = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        Objects.requireNonNull(PendingDao_Impl.this.__converters);
                        UUID fromString = UUID.fromString(string2);
                        String string3 = query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2);
                        String string4 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                        Objects.requireNonNull(PendingDao_Impl.this.__converters);
                        Profile.Type valueOf = Profile.Type.valueOf(string4);
                        if (!query.isNull(columnIndexOrThrow4)) {
                            string = query.getString(columnIndexOrThrow4);
                        }
                        pending = new Pending(fromString, string3, valueOf, string, query.getLong(columnIndexOrThrow5), query.getLong(columnIndexOrThrow6));
                    }
                    return pending;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object remove(final UUID uuid, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.6
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement acquire = PendingDao_Impl.this.__preparedStmtOfRemove.acquire();
                Converters converters = PendingDao_Impl.this.__converters;
                UUID uuid2 = uuid;
                Objects.requireNonNull(converters);
                acquire.bindString(1, uuid2.toString());
                PendingDao_Impl.this.__db.beginTransaction();
                try {
                    acquire.executeUpdateDelete();
                    PendingDao_Impl.this.__db.setTransactionSuccessful();
                    Unit unit = Unit.INSTANCE;
                    PendingDao_Impl.this.__db.endTransaction();
                    SharedSQLiteStatement sharedSQLiteStatement = PendingDao_Impl.this.__preparedStmtOfRemove;
                    if (acquire == sharedSQLiteStatement.mStmt) {
                        sharedSQLiteStatement.mLock.set(false);
                    }
                    return unit;
                } catch (Throwable th) {
                    PendingDao_Impl.this.__db.endTransaction();
                    PendingDao_Impl.this.__preparedStmtOfRemove.release(acquire);
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.PendingDao
    public Object update(final Pending pending, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.PendingDao_Impl.5
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                PendingDao_Impl.this.__db.beginTransaction();
                try {
                    PendingDao_Impl.this.__updateAdapterOfPending.handle(pending);
                    PendingDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    PendingDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }
}

package com.tidalab.v2board.clash.service.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.recyclerview.R$dimen;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* loaded from: classes.dex */
public final class SelectionDao_Impl implements SelectionDao {
    public final Converters __converters = new Converters();
    public final RoomDatabase __db;
    public final EntityInsertionAdapter<Selection> __insertionAdapterOfSelection;
    public final SharedSQLiteStatement __preparedStmtOfRemoveSelected;

    public SelectionDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSelection = new EntityInsertionAdapter<Selection>(roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.SelectionDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Selection selection) {
                Selection selection2 = selection;
                Converters converters = SelectionDao_Impl.this.__converters;
                UUID uuid = selection2.uuid;
                Objects.requireNonNull(converters);
                supportSQLiteStatement.bindString(1, uuid.toString());
                String str = selection2.proxy;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                String str2 = selection2.selected;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `selections` (`uuid`,`proxy`,`selected`) VALUES (?,?,?)";
            }
        };
        this.__preparedStmtOfRemoveSelected = new SharedSQLiteStatement(this, roomDatabase) { // from class: com.tidalab.v2board.clash.service.data.SelectionDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM selections WHERE uuid = ? AND proxy = ?";
            }
        };
    }

    @Override // com.tidalab.v2board.clash.service.data.SelectionDao
    public Object querySelections(UUID uuid, Continuation<? super List<Selection>> continuation) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM selections WHERE uuid = ?", 1);
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        return CoroutinesRoom.execute(this.__db, false, new CancellationSignal(), new Callable<List<Selection>>() { // from class: com.tidalab.v2board.clash.service.data.SelectionDao_Impl.3
            @Override // java.util.concurrent.Callable
            public List<Selection> call() throws Exception {
                Cursor query = DBUtil.query(SelectionDao_Impl.this.__db, acquire, false, null);
                try {
                    int columnIndexOrThrow = R$dimen.getColumnIndexOrThrow(query, "uuid");
                    int columnIndexOrThrow2 = R$dimen.getColumnIndexOrThrow(query, "proxy");
                    int columnIndexOrThrow3 = R$dimen.getColumnIndexOrThrow(query, "selected");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        String string = query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow);
                        Objects.requireNonNull(SelectionDao_Impl.this.__converters);
                        arrayList.add(new Selection(UUID.fromString(string), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3)));
                    }
                    return arrayList;
                } finally {
                    query.close();
                    acquire.release();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.SelectionDao
    public void removeSelected(UUID uuid, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveSelected.acquire();
        Objects.requireNonNull(this.__converters);
        acquire.bindString(1, uuid.toString());
        acquire.bindString(2, str);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            SharedSQLiteStatement sharedSQLiteStatement = this.__preparedStmtOfRemoveSelected;
            if (acquire == sharedSQLiteStatement.mStmt) {
                sharedSQLiteStatement.mLock.set(false);
            }
        }
    }

    @Override // com.tidalab.v2board.clash.service.data.SelectionDao
    public Object removeSelections(final UUID uuid, final List<String> list, Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: com.tidalab.v2board.clash.service.data.SelectionDao_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                StringBuilder outline16 = GeneratedOutlineSupport.outline16("DELETE FROM selections WHERE uuid = ", "?", " AND proxy in (");
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    outline16.append("?");
                    if (i < size - 1) {
                        outline16.append(",");
                    }
                }
                outline16.append(")");
                String sb = outline16.toString();
                RoomDatabase roomDatabase = SelectionDao_Impl.this.__db;
                roomDatabase.assertNotMainThread();
                roomDatabase.assertNotSuspendingTransaction();
                SupportSQLiteStatement compileStatement = roomDatabase.mOpenHelper.getWritableDatabase().compileStatement(sb);
                Converters converters = SelectionDao_Impl.this.__converters;
                UUID uuid2 = uuid;
                Objects.requireNonNull(converters);
                compileStatement.bindString(1, uuid2.toString());
                int i2 = 2;
                for (String str : list) {
                    if (str == null) {
                        compileStatement.bindNull(i2);
                    } else {
                        compileStatement.bindString(i2, str);
                    }
                    i2++;
                }
                SelectionDao_Impl.this.__db.beginTransaction();
                try {
                    compileStatement.executeUpdateDelete();
                    SelectionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SelectionDao_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.data.SelectionDao
    public void setSelected(Selection selection) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSelection.insert(selection);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}

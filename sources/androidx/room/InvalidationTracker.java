package androidx.room;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public class InvalidationTracker {
    public static final String[] TRIGGERS = {"UPDATE", "DELETE", "INSERT"};
    public AutoCloser mAutoCloser;
    public volatile SupportSQLiteStatement mCleanupStatement;
    public final RoomDatabase mDatabase;
    public ObservedTableTracker mObservedTableTracker;
    public final String[] mTableNames;
    public Map<String, Set<String>> mViewTables;
    public AtomicBoolean mPendingRefresh = new AtomicBoolean(false);
    public volatile boolean mInitialized = false;
    @SuppressLint({"RestrictedApi"})
    public final SafeIterableMap<Observer, ObserverWrapper> mObserverMap = new SafeIterableMap<>();
    public Runnable mRefreshRunnable = new Runnable() { // from class: androidx.room.InvalidationTracker.1
        /* JADX WARN: Finally extract failed */
        public final Set<Integer> checkUpdatedTable() {
            HashSet hashSet = new HashSet();
            Cursor query = InvalidationTracker.this.mDatabase.query(new SimpleSQLiteQuery("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"), null);
            while (query.moveToNext()) {
                try {
                    hashSet.add(Integer.valueOf(query.getInt(0)));
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
            if (!hashSet.isEmpty()) {
                InvalidationTracker.this.mCleanupStatement.executeUpdateDelete();
            }
            return hashSet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:48:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x00fd A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x00fe  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 266
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.InvalidationTracker.AnonymousClass1.run():void");
        }
    };
    public final HashMap<String, Integer> mTableIdLookup = new HashMap<>();

    /* loaded from: classes.dex */
    public static class ObservedTableTracker {
        public boolean mNeedsSync;
        public boolean mPendingSync;
        public final long[] mTableObservers;
        public final int[] mTriggerStateChanges;
        public final boolean[] mTriggerStates;

        public ObservedTableTracker(int i) {
            long[] jArr = new long[i];
            this.mTableObservers = jArr;
            boolean[] zArr = new boolean[i];
            this.mTriggerStates = zArr;
            this.mTriggerStateChanges = new int[i];
            Arrays.fill(jArr, 0L);
            Arrays.fill(zArr, false);
        }

        public int[] getTablesToSync() {
            synchronized (this) {
                if (this.mNeedsSync && !this.mPendingSync) {
                    int length = this.mTableObservers.length;
                    int i = 0;
                    while (true) {
                        int i2 = 1;
                        if (i < length) {
                            boolean z = this.mTableObservers[i] > 0;
                            boolean[] zArr = this.mTriggerStates;
                            if (z != zArr[i]) {
                                int[] iArr = this.mTriggerStateChanges;
                                if (!z) {
                                    i2 = 2;
                                }
                                iArr[i] = i2;
                            } else {
                                this.mTriggerStateChanges[i] = 0;
                            }
                            zArr[i] = z;
                            i++;
                        } else {
                            this.mPendingSync = true;
                            this.mNeedsSync = false;
                            return this.mTriggerStateChanges;
                        }
                    }
                }
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Observer {
        public abstract void onInvalidated(Set<String> set);
    }

    /* loaded from: classes.dex */
    public static class ObserverWrapper {
        public final Observer mObserver;
        public final Set<String> mSingleTableSet;
        public final int[] mTableIds;
        public final String[] mTableNames;
    }

    public InvalidationTracker(RoomDatabase roomDatabase, Map<String, String> map, Map<String, Set<String>> map2, String... strArr) {
        this.mDatabase = roomDatabase;
        this.mObservedTableTracker = new ObservedTableTracker(strArr.length);
        this.mViewTables = map2;
        Collections.newSetFromMap(new IdentityHashMap());
        int length = strArr.length;
        this.mTableNames = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.US;
            String lowerCase = str.toLowerCase(locale);
            this.mTableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str2 = map.get(strArr[i]);
            if (str2 != null) {
                this.mTableNames[i] = str2.toLowerCase(locale);
            } else {
                this.mTableNames[i] = lowerCase;
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Locale locale2 = Locale.US;
            String lowerCase2 = entry.getValue().toLowerCase(locale2);
            if (this.mTableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = entry.getKey().toLowerCase(locale2);
                HashMap<String, Integer> hashMap = this.mTableIdLookup;
                hashMap.put(lowerCase3, hashMap.get(lowerCase2));
            }
        }
    }

    public boolean ensureInitialization() {
        if (!this.mDatabase.isOpen()) {
            return false;
        }
        if (!this.mInitialized) {
            this.mDatabase.mOpenHelper.getWritableDatabase();
        }
        if (this.mInitialized) {
            return true;
        }
        Log.e("ROOM", "database is not initialized even though it is open");
        return false;
    }

    public final void startTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        String[] strArr;
        supportSQLiteDatabase.execSQL("INSERT OR IGNORE INTO room_table_modification_log VALUES(" + i + ", 0)");
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("CREATE TEMP TRIGGER IF NOT EXISTS ");
            sb.append("`");
            sb.append("room_table_modification_trigger_");
            sb.append(str);
            sb.append("_");
            sb.append(str2);
            sb.append("`");
            sb.append(" AFTER ");
            sb.append(str2);
            sb.append(" ON `");
            sb.append(str);
            sb.append("` BEGIN UPDATE ");
            sb.append("room_table_modification_log");
            sb.append(" SET ");
            sb.append("invalidated");
            sb.append(" = 1");
            sb.append(" WHERE ");
            sb.append("table_id");
            sb.append(" = ");
            sb.append(i);
            sb.append(" AND ");
            sb.append("invalidated");
            sb.append(" = 0");
            sb.append("; END");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public final void stopTrackingTable(SupportSQLiteDatabase supportSQLiteDatabase, int i) {
        String[] strArr;
        String str = this.mTableNames[i];
        StringBuilder sb = new StringBuilder();
        for (String str2 : TRIGGERS) {
            sb.setLength(0);
            sb.append("DROP TRIGGER IF EXISTS ");
            sb.append("`");
            sb.append("room_table_modification_trigger_");
            sb.append(str);
            sb.append("_");
            sb.append(str2);
            sb.append("`");
            supportSQLiteDatabase.execSQL(sb.toString());
        }
    }

    public void syncTriggers(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (!supportSQLiteDatabase.inTransaction()) {
            while (true) {
                try {
                    ReentrantReadWriteLock.ReadLock readLock = this.mDatabase.mCloseLock.readLock();
                    readLock.lock();
                    try {
                        int[] tablesToSync = this.mObservedTableTracker.getTablesToSync();
                        if (tablesToSync != null) {
                            int length = tablesToSync.length;
                            if (supportSQLiteDatabase.isWriteAheadLoggingEnabled()) {
                                supportSQLiteDatabase.beginTransactionNonExclusive();
                            } else {
                                supportSQLiteDatabase.beginTransaction();
                            }
                            for (int i = 0; i < length; i++) {
                                int i2 = tablesToSync[i];
                                if (i2 == 1) {
                                    startTrackingTable(supportSQLiteDatabase, i);
                                } else if (i2 == 2) {
                                    stopTrackingTable(supportSQLiteDatabase, i);
                                }
                            }
                            supportSQLiteDatabase.setTransactionSuccessful();
                            supportSQLiteDatabase.endTransaction();
                            ObservedTableTracker observedTableTracker = this.mObservedTableTracker;
                            synchronized (observedTableTracker) {
                                observedTableTracker.mPendingSync = false;
                            }
                        } else {
                            return;
                        }
                    } finally {
                        readLock.unlock();
                    }
                } catch (SQLiteException | IllegalStateException e) {
                    Log.e("ROOM", "Cannot run invalidation tracker. Is the db closed?", e);
                    return;
                }
            }
        }
    }
}

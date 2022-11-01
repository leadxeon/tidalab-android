package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    public static final TreeMap<Integer, RoomSQLiteQuery> sQueryPool = new TreeMap<>();
    public int mArgCount;
    public final int[] mBindingTypes;
    public final byte[][] mBlobBindings;
    public final int mCapacity;
    public final double[] mDoubleBindings;
    public final long[] mLongBindings;
    public volatile String mQuery;
    public final String[] mStringBindings;

    public RoomSQLiteQuery(int i) {
        this.mCapacity = i;
        int i2 = i + 1;
        this.mBindingTypes = new int[i2];
        this.mLongBindings = new long[i2];
        this.mDoubleBindings = new double[i2];
        this.mStringBindings = new String[i2];
        this.mBlobBindings = new byte[i2];
    }

    public static RoomSQLiteQuery acquire(String str, int i) {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = sQueryPool;
        synchronized (treeMap) {
            Map.Entry<Integer, RoomSQLiteQuery> ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                treeMap.remove(ceilingEntry.getKey());
                RoomSQLiteQuery value = ceilingEntry.getValue();
                value.mQuery = str;
                value.mArgCount = i;
                return value;
            }
            RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(i);
            roomSQLiteQuery.mQuery = str;
            roomSQLiteQuery.mArgCount = i;
            return roomSQLiteQuery;
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int i, byte[] bArr) {
        this.mBindingTypes[i] = 5;
        this.mBlobBindings[i] = bArr;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int i, double d) {
        this.mBindingTypes[i] = 3;
        this.mDoubleBindings[i] = d;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int i, long j) {
        this.mBindingTypes[i] = 2;
        this.mLongBindings[i] = j;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int i) {
        this.mBindingTypes[i] = 1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int i, String str) {
        this.mBindingTypes[i] = 4;
        this.mStringBindings[i] = str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        for (int i = 1; i <= this.mArgCount; i++) {
            int i2 = this.mBindingTypes[i];
            if (i2 == 1) {
                supportSQLiteProgram.bindNull(i);
            } else if (i2 == 2) {
                supportSQLiteProgram.bindLong(i, this.mLongBindings[i]);
            } else if (i2 == 3) {
                supportSQLiteProgram.bindDouble(i, this.mDoubleBindings[i]);
            } else if (i2 == 4) {
                supportSQLiteProgram.bindString(i, this.mStringBindings[i]);
            } else if (i2 == 5) {
                supportSQLiteProgram.bindBlob(i, this.mBlobBindings[i]);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    public void release() {
        TreeMap<Integer, RoomSQLiteQuery> treeMap = sQueryPool;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.mCapacity), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator<Integer> it = treeMap.descendingKeySet().iterator();
                while (true) {
                    size--;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                }
            }
        }
    }
}

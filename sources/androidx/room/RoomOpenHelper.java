package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
/* loaded from: classes.dex */
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    public DatabaseConfiguration mConfiguration;
    public final Delegate mDelegate;

    /* loaded from: classes.dex */
    public static abstract class Delegate {
        public final int version;

        public Delegate(int i) {
            this.version = i;
        }

        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase);
    }

    /* loaded from: classes.dex */
    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate, String str, String str2) {
        super(delegate.version);
        this.mConfiguration = databaseConfiguration;
        this.mDelegate = delegate;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006d A[ORIG_RETURN, RETURN] */
    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(androidx.sqlite.db.SupportSQLiteDatabase r4) {
        /*
            r3 = this;
            r0 = r4
            androidx.sqlite.db.framework.FrameworkSQLiteDatabase r0 = (androidx.sqlite.db.framework.FrameworkSQLiteDatabase) r0
            androidx.sqlite.db.SimpleSQLiteQuery r1 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r2 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
            r1.<init>(r2)
            android.database.Cursor r0 = r0.query(r1)
            boolean r1 = r0.moveToFirst()     // Catch: all -> 0x006e
            r2 = 0
            if (r1 == 0) goto L_0x001d
            int r1 = r0.getInt(r2)     // Catch: all -> 0x006e
            if (r1 != 0) goto L_0x001d
            r1 = 1
            goto L_0x001e
        L_0x001d:
            r1 = 0
        L_0x001e:
            r0.close()
            androidx.room.RoomOpenHelper$Delegate r0 = r3.mDelegate
            r0.createAllTables(r4)
            if (r1 != 0) goto L_0x0048
            androidx.room.RoomOpenHelper$Delegate r0 = r3.mDelegate
            androidx.room.RoomOpenHelper$ValidationResult r0 = r0.onValidateSchema(r4)
            boolean r1 = r0.isValid
            if (r1 == 0) goto L_0x0033
            goto L_0x0048
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r1 = "Pre-packaged database has an invalid schema: "
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r1)
            java.lang.String r0 = r0.expectedFoundMsg
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        L_0x0048:
            r3.updateIdentity(r4)
            androidx.room.RoomOpenHelper$Delegate r4 = r3.mDelegate
            com.tidalab.v2board.clash.service.data.Database_Impl$1 r4 = (com.tidalab.v2board.clash.service.data.Database_Impl.AnonymousClass1) r4
            com.tidalab.v2board.clash.service.data.Database_Impl r0 = com.tidalab.v2board.clash.service.data.Database_Impl.this
            int r1 = com.tidalab.v2board.clash.service.data.Database_Impl.$r8$clinit
            java.util.List<androidx.room.RoomDatabase$Callback> r0 = r0.mCallbacks
            if (r0 == 0) goto L_0x006d
            int r0 = r0.size()
        L_0x005b:
            if (r2 >= r0) goto L_0x006d
            com.tidalab.v2board.clash.service.data.Database_Impl r1 = com.tidalab.v2board.clash.service.data.Database_Impl.this
            java.util.List<androidx.room.RoomDatabase$Callback> r1 = r1.mCallbacks
            java.lang.Object r1 = r1.get(r2)
            androidx.room.RoomDatabase$Callback r1 = (androidx.room.RoomDatabase.Callback) r1
            java.util.Objects.requireNonNull(r1)
            int r2 = r2 + 1
            goto L_0x005b
        L_0x006d:
            return
        L_0x006e:
            r4 = move-exception
            r0.close()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.onCreate(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onOpen(androidx.sqlite.db.SupportSQLiteDatabase r8) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.onOpen(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007c, code lost:
        r0 = r4;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017d A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0078 A[EDGE_INSN: B:79:0x0078->B:34:0x0078 ?: BREAK  , SYNTHETIC] */
    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUpgrade(androidx.sqlite.db.SupportSQLiteDatabase r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.onUpgrade(androidx.sqlite.db.SupportSQLiteDatabase, int, int):void");
    }

    public final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '344f4abf0a10cb27a43e94dd31b449c9')");
    }
}

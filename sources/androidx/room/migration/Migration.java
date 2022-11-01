package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;
/* loaded from: classes.dex */
public abstract class Migration {
    public abstract void migrate(SupportSQLiteDatabase supportSQLiteDatabase);
}

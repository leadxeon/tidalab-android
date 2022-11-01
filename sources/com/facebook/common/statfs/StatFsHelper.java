package com.facebook.common.statfs;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.facebook.common.internal.Throwables;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class StatFsHelper {
    public static final long RESTAT_INTERVAL_MS = TimeUnit.MINUTES.toMillis(2);
    public static StatFsHelper sStatsFsHelper;
    public volatile File mExternalPath;
    public volatile File mInternalPath;
    public long mLastRestatTime;
    public volatile StatFs mInternalStatFs = null;
    public volatile StatFs mExternalStatFs = null;
    public volatile boolean mInitialized = false;
    public final Lock lock = new ReentrantLock();

    public final void ensureInitialized() {
        if (!this.mInitialized) {
            this.lock.lock();
            try {
                if (!this.mInitialized) {
                    this.mInternalPath = Environment.getDataDirectory();
                    this.mExternalPath = Environment.getExternalStorageDirectory();
                    updateStats();
                    this.mInitialized = true;
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    public final void updateStats() {
        this.mInternalStatFs = updateStatsHelper(this.mInternalStatFs, this.mInternalPath);
        this.mExternalStatFs = updateStatsHelper(this.mExternalStatFs, this.mExternalPath);
        this.mLastRestatTime = SystemClock.uptimeMillis();
    }

    public final StatFs updateStatsHelper(StatFs statFs, File file) {
        statFs = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            if (statFs == null) {
                statFs = new StatFs(file.getAbsolutePath());
            } else {
                statFs.restat(file.getAbsolutePath());
            }
            return statFs;
        } catch (IllegalArgumentException unused) {
            return statFs;
        } catch (Throwable th) {
            Throwables.propagateIfPossible(th);
            throw new RuntimeException(th);
        }
    }
}

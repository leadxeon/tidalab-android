package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import androidx.constraintlayout.solver.SolverVariable$Type$r8$EnumUnboxingUtility;
import androidx.recyclerview.R$dimen;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class JobScheduler {
    public final Executor mExecutor;
    public final JobRunnable mJobRunnable;
    public final int mMinimumJobIntervalMs;
    public final Runnable mDoJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.1
        @Override // java.lang.Runnable
        public void run() {
            EncodedImage encodedImage;
            int i;
            JobScheduler jobScheduler = JobScheduler.this;
            Objects.requireNonNull(jobScheduler);
            long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (jobScheduler) {
                encodedImage = jobScheduler.mEncodedImage;
                i = jobScheduler.mStatus;
                jobScheduler.mEncodedImage = null;
                jobScheduler.mStatus = 0;
                jobScheduler.mJobState = 3;
                jobScheduler.mJobStartTime = uptimeMillis;
            }
            try {
                if (JobScheduler.shouldProcess(encodedImage, i)) {
                    jobScheduler.mJobRunnable.run(encodedImage, i);
                }
            } finally {
                if (encodedImage != null) {
                    encodedImage.close();
                }
                jobScheduler.onJobFinished();
            }
        }
    };
    public final Runnable mSubmitJobRunnable = new Runnable() { // from class: com.facebook.imagepipeline.producers.JobScheduler.2
        @Override // java.lang.Runnable
        public void run() {
            JobScheduler jobScheduler = JobScheduler.this;
            jobScheduler.mExecutor.execute(jobScheduler.mDoJobRunnable);
        }
    };
    public EncodedImage mEncodedImage = null;
    public int mStatus = 0;
    public int mJobState = 1;
    public long mJobSubmitTime = 0;
    public long mJobStartTime = 0;

    /* loaded from: classes.dex */
    public interface JobRunnable {
        void run(EncodedImage encodedImage, int i);
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int i) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = i;
    }

    public static boolean shouldProcess(EncodedImage encodedImage, int i) {
        return BaseConsumer.isLast(i) || BaseConsumer.statusHasFlag(i, 4) || EncodedImage.isValid(encodedImage);
    }

    public void clearJob() {
        EncodedImage encodedImage;
        synchronized (this) {
            encodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mStatus = 0;
        }
        if (encodedImage != null) {
            encodedImage.close();
        }
    }

    public final void enqueueJob(long j) {
        if (j > 0) {
            if (R$dimen.sJobStarterExecutor == null) {
                R$dimen.sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            R$dimen.sJobStarterExecutor.schedule(this.mSubmitJobRunnable, j, TimeUnit.MILLISECONDS);
            return;
        }
        this.mSubmitJobRunnable.run();
    }

    public final void onJobFinished() {
        boolean z;
        long j;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            z = true;
            if (this.mJobState == 4) {
                j = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                this.mJobSubmitTime = uptimeMillis;
                this.mJobState = 2;
            } else {
                this.mJobState = 1;
                j = 0;
                z = false;
            }
        }
        if (z) {
            enqueueJob(j - uptimeMillis);
        }
    }

    public boolean scheduleJob() {
        long j;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            boolean z = false;
            if (!shouldProcess(this.mEncodedImage, this.mStatus)) {
                return false;
            }
            int $enumboxing$ordinal = SolverVariable$Type$r8$EnumUnboxingUtility.$enumboxing$ordinal(this.mJobState);
            if ($enumboxing$ordinal != 0) {
                if ($enumboxing$ordinal == 2) {
                    this.mJobState = 4;
                }
                j = 0;
            } else {
                j = Math.max(this.mJobStartTime + this.mMinimumJobIntervalMs, uptimeMillis);
                this.mJobSubmitTime = uptimeMillis;
                this.mJobState = 2;
                z = true;
            }
            if (z) {
                enqueueJob(j - uptimeMillis);
            }
            return true;
        }
    }

    public boolean updateJob(EncodedImage encodedImage, int i) {
        EncodedImage encodedImage2;
        if (!shouldProcess(encodedImage, i)) {
            return false;
        }
        synchronized (this) {
            encodedImage2 = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mStatus = i;
        }
        if (encodedImage2 == null) {
            return true;
        }
        encodedImage2.close();
        return true;
    }
}

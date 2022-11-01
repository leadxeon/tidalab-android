package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
/* compiled from: Job.kt */
/* loaded from: classes.dex */
public interface ParentJob extends Job {
    CancellationException getChildJobCancellationCause();
}

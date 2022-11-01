package kotlinx.coroutines;
/* compiled from: Job.kt */
/* loaded from: classes.dex */
public interface ChildJob extends Job {
    void parentCancelled(ParentJob parentJob);
}

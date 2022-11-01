package kotlinx.coroutines.scheduling;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class NanoTimeSource extends SchedulerTimeSource {
    public static final NanoTimeSource INSTANCE = new NanoTimeSource();

    @Override // kotlinx.coroutines.scheduling.SchedulerTimeSource
    public long nanoTime() {
        return System.nanoTime();
    }
}

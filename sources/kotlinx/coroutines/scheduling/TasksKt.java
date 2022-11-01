package kotlinx.coroutines.scheduling;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.TimeUnit;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class TasksKt {
    public static final int CORE_POOL_SIZE;
    public static final long IDLE_WORKER_KEEP_ALIVE_NS;
    public static final int MAX_POOL_SIZE;
    public static final long WORK_STEALING_TIME_RESOLUTION_NS = InputKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 0L, 0L, 12, (Object) null);
    public static SchedulerTimeSource schedulerTimeSource;

    static {
        InputKt.systemProp$default("kotlinx.coroutines.scheduler.blocking.parallelism", 16, 0, 0, 12, (Object) null);
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        int systemProp$default = InputKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", i < 2 ? 2 : i, 1, 0, 8, (Object) null);
        CORE_POOL_SIZE = systemProp$default;
        MAX_POOL_SIZE = InputKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", RangesKt___RangesKt.coerceIn(i * 128, systemProp$default, 2097150), 0, 2097150, 4, (Object) null);
        IDLE_WORKER_KEEP_ALIVE_NS = TimeUnit.SECONDS.toNanos(InputKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 0L, 0L, 12, (Object) null));
        schedulerTimeSource = NanoTimeSource.INSTANCE;
    }
}

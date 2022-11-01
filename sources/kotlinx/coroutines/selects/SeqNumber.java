package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
/* compiled from: Select.kt */
/* loaded from: classes.dex */
public final class SeqNumber {
    public static final /* synthetic */ AtomicLongFieldUpdater number$FU = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");
    private volatile /* synthetic */ long number = 1;
}

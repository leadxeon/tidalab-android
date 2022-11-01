package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.random.AbstractPlatformRandom;
/* compiled from: PlatformThreadLocalRandom.kt */
/* loaded from: classes.dex */
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    @Override // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        return ThreadLocalRandom.current();
    }
}

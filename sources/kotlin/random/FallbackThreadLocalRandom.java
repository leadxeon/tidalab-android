package kotlin.random;

import java.util.Random;
/* compiled from: PlatformRandom.kt */
/* loaded from: classes.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
    public final FallbackThreadLocalRandom$implStorage$1 implStorage = new ThreadLocal<Random>() { // from class: kotlin.random.FallbackThreadLocalRandom$implStorage$1
        @Override // java.lang.ThreadLocal
        public Random initialValue() {
            return new Random();
        }
    };

    @Override // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        return get();
    }
}

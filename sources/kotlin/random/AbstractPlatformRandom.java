package kotlin.random;

import java.util.Random;
/* compiled from: PlatformRandom.kt */
/* loaded from: classes.dex */
public abstract class AbstractPlatformRandom extends Random {
    public abstract Random getImpl();

    @Override // kotlin.random.Random
    public int nextInt() {
        return getImpl().nextInt();
    }
}

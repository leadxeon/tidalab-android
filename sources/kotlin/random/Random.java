package kotlin.random;

import java.io.Serializable;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: Random.kt */
/* loaded from: classes.dex */
public abstract class Random {
    public static final Default Default = new Default(null);
    public static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    /* compiled from: Random.kt */
    /* loaded from: classes.dex */
    public static final class Default extends Random implements Serializable {
        public Default(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }
    }

    public abstract int nextInt();
}

package kotlin.internal.jdk8;

import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
/* compiled from: JDK8PlatformImplementations.kt */
/* loaded from: classes.dex */
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {
    @Override // kotlin.internal.PlatformImplementations
    public Random defaultPlatformRandom() {
        return new PlatformThreadLocalRandom();
    }
}

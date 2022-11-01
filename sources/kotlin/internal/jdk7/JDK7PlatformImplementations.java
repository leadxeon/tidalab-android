package kotlin.internal.jdk7;

import kotlin.internal.PlatformImplementations;
/* compiled from: JDK7PlatformImplementations.kt */
/* loaded from: classes.dex */
public class JDK7PlatformImplementations extends PlatformImplementations {
    @Override // kotlin.internal.PlatformImplementations
    public void addSuppressed(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }
}

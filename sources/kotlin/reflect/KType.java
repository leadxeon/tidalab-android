package kotlin.reflect;

import java.util.List;
/* compiled from: KType.kt */
/* loaded from: classes.dex */
public interface KType {
    List<KTypeProjection> getArguments();

    KClass getClassifier();

    boolean isMarkedNullable();
}

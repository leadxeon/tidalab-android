package kotlinx.serialization;

import kotlin.jvm.internal.Intrinsics;
/* compiled from: SerializationException.kt */
/* loaded from: classes.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(int i) {
        super(Intrinsics.stringPlus("An unknown field for index ", Integer.valueOf(i)));
    }
}

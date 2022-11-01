package kotlinx.serialization;
/* compiled from: SerializationException.kt */
/* loaded from: classes.dex */
public class SerializationException extends IllegalArgumentException {
    public SerializationException(String str) {
        super(str);
    }

    public SerializationException(String str, Throwable th) {
        super(str, th);
    }
}

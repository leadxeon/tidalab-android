package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
/* compiled from: PluginHelperInterfaces.kt */
/* loaded from: classes.dex */
public interface GeneratedSerializer<T> extends KSerializer<T> {
    KSerializer<?>[] childSerializers();

    KSerializer<?>[] typeParametersSerializers();
}

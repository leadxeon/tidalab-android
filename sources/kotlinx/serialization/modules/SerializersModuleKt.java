package kotlinx.serialization.modules;

import kotlin.collections.EmptyMap;
/* compiled from: SerializersModule.kt */
/* loaded from: classes.dex */
public final class SerializersModuleKt {
    public static final SerializersModule EmptySerializersModule;

    static {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        EmptySerializersModule = new SerialModuleImpl(emptyMap, emptyMap, emptyMap, emptyMap);
    }
}

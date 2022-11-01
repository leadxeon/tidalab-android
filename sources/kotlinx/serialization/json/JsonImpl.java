package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.PolymorphismValidator;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
/* compiled from: Json.kt */
/* loaded from: classes.dex */
public final class JsonImpl extends Json {
    public JsonImpl(JsonConfiguration jsonConfiguration, SerializersModule serializersModule) {
        super(jsonConfiguration, serializersModule, null);
        SerializersModule serializersModule2 = SerializersModuleKt.EmptySerializersModule;
        if (!Intrinsics.areEqual(serializersModule, SerializersModuleKt.EmptySerializersModule)) {
            serializersModule.dumpTo(new PolymorphismValidator(jsonConfiguration.useArrayPolymorphism, jsonConfiguration.classDiscriminator));
        }
    }
}

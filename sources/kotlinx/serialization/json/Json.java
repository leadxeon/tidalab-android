package kotlinx.serialization.json;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import kotlinx.serialization.json.internal.JsonLexer;
import kotlinx.serialization.json.internal.PolymorphicKt;
import kotlinx.serialization.json.internal.StreamingJsonDecoder;
import kotlinx.serialization.json.internal.WriteMode;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: Json.kt */
/* loaded from: classes.dex */
public abstract class Json {
    public static final Default Default = new Default(null);
    public final JsonConfiguration configuration;
    public final DescriptorSchemaCache schemaCache = new DescriptorSchemaCache();
    public final SerializersModule serializersModule;

    /* compiled from: Json.kt */
    /* loaded from: classes.dex */
    public static final class Default extends Json {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Default(kotlin.jvm.internal.DefaultConstructorMarker r13) {
            /*
                r12 = this;
                kotlinx.serialization.json.JsonConfiguration r13 = new kotlinx.serialization.json.JsonConfiguration
                r1 = 0
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                r7 = 0
                r8 = 0
                r10 = 0
                r11 = 1
                java.lang.String r6 = "    "
                java.lang.String r9 = "type"
                r0 = r13
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                kotlinx.serialization.modules.SerializersModule r0 = kotlinx.serialization.modules.SerializersModuleKt.EmptySerializersModule
                kotlinx.serialization.modules.SerializersModule r0 = kotlinx.serialization.modules.SerializersModuleKt.EmptySerializersModule
                r1 = 0
                r12.<init>(r13, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.Json.Default.<init>(kotlin.jvm.internal.DefaultConstructorMarker):void");
        }
    }

    public Json(JsonConfiguration jsonConfiguration, SerializersModule serializersModule, DefaultConstructorMarker defaultConstructorMarker) {
        this.configuration = jsonConfiguration;
        this.serializersModule = serializersModule;
    }

    public final <T> T decodeFromString(DeserializationStrategy<T> deserializationStrategy, String str) {
        JsonLexer jsonLexer = new JsonLexer(str);
        T t = (T) PolymorphicKt.decodeSerializableValuePolymorphic(new StreamingJsonDecoder(this, WriteMode.OBJ, jsonLexer), deserializationStrategy);
        if (jsonLexer.consumeNextToken() == 10) {
            return t;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected EOF, but had ");
        outline13.append(str.charAt(jsonLexer.currentPosition - 1));
        outline13.append(" instead");
        throw InputKt.JsonDecodingException(jsonLexer.currentPosition, outline13.toString(), str);
    }
}

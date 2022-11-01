package kotlinx.serialization.json.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: StreamingJsonEncoder.kt */
/* loaded from: classes.dex */
public final class StreamingJsonEncoder extends AbstractEncoder implements JsonEncoder {
    public final Composer composer;
    public final JsonConfiguration configuration;
    public boolean forceQuoting;
    public final Json json;
    public final WriteMode mode;
    public final JsonEncoder[] modeReuseCache;
    public final SerializersModule serializersModule;
    public boolean writePolymorphic;

    public StreamingJsonEncoder(Composer composer, Json json, WriteMode writeMode, JsonEncoder[] jsonEncoderArr) {
        this.composer = composer;
        this.json = json;
        this.mode = writeMode;
        this.modeReuseCache = jsonEncoderArr;
        this.serializersModule = json.serializersModule;
        this.configuration = json.configuration;
        int ordinal = writeMode.ordinal();
        if (jsonEncoderArr == null) {
            return;
        }
        if (jsonEncoderArr[ordinal] != null || jsonEncoderArr[ordinal] != this) {
            jsonEncoderArr[ordinal] = this;
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor serialDescriptor) {
        WriteMode switchMode = PolymorphicKt.switchMode(this.json, serialDescriptor);
        char c = switchMode.begin;
        if (c != 0) {
            this.composer.print(c);
            Composer composer = this.composer;
            composer.writingFirst = true;
            composer.level++;
        }
        if (this.writePolymorphic) {
            this.writePolymorphic = false;
            this.composer.nextItem();
            encodeString(this.configuration.classDiscriminator);
            this.composer.print(':');
            this.composer.space();
            encodeString(serialDescriptor.getSerialName());
        }
        if (this.mode == switchMode) {
            return this;
        }
        JsonEncoder[] jsonEncoderArr = this.modeReuseCache;
        JsonEncoder jsonEncoder = jsonEncoderArr == null ? null : jsonEncoderArr[switchMode.ordinal()];
        return jsonEncoder == null ? new StreamingJsonEncoder(this.composer, this.json, switchMode, this.modeReuseCache) : jsonEncoder;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean z) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(z));
        } else {
            this.composer.sb.append(String.valueOf(z));
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte b) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) b));
        } else {
            this.composer.print(b);
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeChar(char c) {
        encodeString(String.valueOf(c));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double d) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(d));
        } else {
            this.composer.sb.append(String.valueOf(d));
        }
        if (!this.configuration.allowSpecialFloatingPointValues) {
            if (!(!Double.isInfinite(d) && !Double.isNaN(d))) {
                throw InputKt.InvalidFloatingPointEncoded(Double.valueOf(d), this.composer.sb.toString());
            }
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public boolean encodeElement(SerialDescriptor serialDescriptor, int i) {
        int ordinal = this.mode.ordinal();
        if (ordinal != 1) {
            boolean z = false;
            if (ordinal == 2) {
                Composer composer = this.composer;
                if (!composer.writingFirst) {
                    if (i % 2 == 0) {
                        composer.print(',');
                        this.composer.nextItem();
                        z = true;
                    } else {
                        composer.print(':');
                        this.composer.space();
                    }
                    this.forceQuoting = z;
                } else {
                    this.forceQuoting = true;
                    composer.nextItem();
                }
            } else if (ordinal != 3) {
                Composer composer2 = this.composer;
                if (!composer2.writingFirst) {
                    composer2.print(',');
                }
                this.composer.nextItem();
                encodeString(serialDescriptor.getElementName(i));
                this.composer.print(':');
                this.composer.space();
            } else {
                if (i == 0) {
                    this.forceQuoting = true;
                }
                if (i == 1) {
                    this.composer.print(',');
                    this.composer.space();
                    this.forceQuoting = false;
                }
            }
        } else {
            Composer composer3 = this.composer;
            if (!composer3.writingFirst) {
                composer3.print(',');
            }
            this.composer.nextItem();
        }
        return true;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeEnum(SerialDescriptor serialDescriptor, int i) {
        encodeString(serialDescriptor.getElementName(i));
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float f) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(f));
        } else {
            this.composer.sb.append(String.valueOf(f));
        }
        if (!this.configuration.allowSpecialFloatingPointValues) {
            if (!(!Float.isInfinite(f) && !Float.isNaN(f))) {
                throw InputKt.InvalidFloatingPointEncoded(Float.valueOf(f), this.composer.sb.toString());
            }
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public Encoder encodeInline(SerialDescriptor serialDescriptor) {
        if (!StreamingJsonEncoderKt.isUnsignedNumber(serialDescriptor)) {
            return this;
        }
        JsonStringBuilder jsonStringBuilder = this.composer.sb;
        Json json = this.json;
        return new StreamingJsonEncoder(new ComposerForUnsignedNumbers(jsonStringBuilder, json), json, this.mode, null);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeInt(int i) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(i));
        } else {
            this.composer.print(i);
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeLong(long j) {
        if (this.forceQuoting) {
            encodeString(String.valueOf(j));
        } else {
            this.composer.print(j);
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        this.composer.sb.append("null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        if (!(serializationStrategy instanceof AbstractPolymorphicSerializer) || getJson().configuration.useArrayPolymorphism) {
            serializationStrategy.serialize(this, t);
            return;
        }
        Objects.requireNonNull(t, "null cannot be cast to non-null type kotlin.Any");
        SerializationStrategy findPolymorphicSerializer = InputKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) serializationStrategy, this, t);
        String str = getJson().configuration.classDiscriminator;
        SerialKind kind = findPolymorphicSerializer.getDescriptor().getKind();
        if (kind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        } else if (kind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        } else if (!(kind instanceof PolymorphicKind)) {
            this.writePolymorphic = true;
            findPolymorphicSerializer.serialize(this, t);
        } else {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeShort(short s) {
        if (this.forceQuoting) {
            encodeString(String.valueOf((int) s));
        } else {
            this.composer.print(s);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008e A[LOOP:1: B:11:0x003b->B:23:0x008e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0090 A[EDGE_INSN: B:31:0x0090->B:24:0x0090 ?: BREAK  , SYNTHETIC] */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void encodeString(java.lang.String r12) {
        /*
            r11 = this;
            kotlinx.serialization.json.internal.Composer r0 = r11.composer
            kotlinx.serialization.json.internal.JsonStringBuilder r0 = r0.sb
            java.util.Objects.requireNonNull(r0)
            int r1 = r12.length()
            int r1 = r1 + 2
            r0.ensureAdditionalCapacity(r1)
            char[] r1 = r0.array
            int r2 = r0.size
            int r3 = r2 + 1
            r4 = 34
            r1[r2] = r4
            int r2 = r12.length()
            r5 = 0
            r12.getChars(r5, r2, r1, r3)
            int r2 = r2 + r3
            if (r3 >= r2) goto L_0x00a1
            r6 = r3
        L_0x0026:
            int r7 = r6 + 1
            char r8 = r1[r6]
            byte[] r9 = kotlinx.serialization.json.internal.StringOpsKt.ESCAPE_MARKERS
            int r10 = r9.length
            if (r8 >= r10) goto L_0x009c
            byte r8 = r9[r8]
            if (r8 == 0) goto L_0x009c
            int r1 = r6 - r3
            int r2 = r12.length()
            if (r1 >= r2) goto L_0x0090
        L_0x003b:
            int r3 = r1 + 1
            int r7 = r6 + 2
            r0.ensureTotalCapacity(r7)
            char r1 = r12.charAt(r1)
            byte[] r8 = kotlinx.serialization.json.internal.StringOpsKt.ESCAPE_MARKERS
            int r9 = r8.length
            if (r1 >= r9) goto L_0x0083
            byte r8 = r8[r1]
            if (r8 != 0) goto L_0x0057
            char[] r7 = r0.array
            int r8 = r6 + 1
            char r1 = (char) r1
            r7[r6] = r1
            goto L_0x008a
        L_0x0057:
            r9 = 1
            if (r8 != r9) goto L_0x0076
            java.lang.String[] r7 = kotlinx.serialization.json.internal.StringOpsKt.ESCAPE_STRINGS
            r1 = r7[r1]
            int r7 = r1.length()
            int r7 = r7 + r6
            r0.ensureTotalCapacity(r7)
            char[] r7 = r0.array
            int r8 = r1.length()
            r1.getChars(r5, r8, r7, r6)
            int r1 = r1.length()
            int r1 = r1 + r6
            r6 = r1
            goto L_0x008b
        L_0x0076:
            char[] r1 = r0.array
            r9 = 92
            r1[r6] = r9
            int r6 = r6 + 1
            char r8 = (char) r8
            r1[r6] = r8
            r6 = r7
            goto L_0x008b
        L_0x0083:
            char[] r7 = r0.array
            int r8 = r6 + 1
            char r1 = (char) r1
            r7[r6] = r1
        L_0x008a:
            r6 = r8
        L_0x008b:
            if (r3 < r2) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r1 = r3
            goto L_0x003b
        L_0x0090:
            int r12 = r6 + 1
            r0.ensureTotalCapacity(r12)
            char[] r1 = r0.array
            r1[r6] = r4
            r0.size = r12
            goto L_0x00a7
        L_0x009c:
            if (r7 < r2) goto L_0x009f
            goto L_0x00a1
        L_0x009f:
            r6 = r7
            goto L_0x0026
        L_0x00a1:
            int r12 = r2 + 1
            r1[r2] = r4
            r0.size = r12
        L_0x00a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonEncoder.encodeString(java.lang.String):void");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(SerialDescriptor serialDescriptor) {
        Composer composer;
        if (this.mode.end != 0) {
            composer.level--;
            this.composer.nextItem();
            this.composer.print(this.mode.end);
        }
    }

    @Override // kotlinx.serialization.json.JsonEncoder
    public Json getJson() {
        return this.json;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        return this.configuration.encodeDefaults;
    }
}

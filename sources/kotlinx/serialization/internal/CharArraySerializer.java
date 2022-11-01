package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class CharArraySerializer extends PrimitiveArraySerializer<Character, char[], CharArrayBuilder> implements KSerializer<char[]> {
    public static final CharArraySerializer INSTANCE = new CharArraySerializer();

    public CharArraySerializer() {
        super(CharSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((char[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public char[] empty() {
        return new char[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        CharArrayBuilder charArrayBuilder = (CharArrayBuilder) obj;
        char decodeCharElement = compositeDecoder.decodeCharElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(charArrayBuilder, 0, 1, null);
        char[] cArr = charArrayBuilder.buffer;
        int i2 = charArrayBuilder.position;
        charArrayBuilder.position = i2 + 1;
        cArr[i2] = decodeCharElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new CharArrayBuilder((char[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, char[] cArr, int i) {
        char[] cArr2 = cArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeCharElement(this.descriptor, i2, cArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}

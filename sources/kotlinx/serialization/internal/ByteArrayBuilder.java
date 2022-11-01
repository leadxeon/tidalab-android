package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class ByteArrayBuilder extends PrimitiveArrayBuilder<byte[]> {
    public byte[] buffer;
    public int position;

    public ByteArrayBuilder(byte[] bArr) {
        this.buffer = bArr;
        this.position = bArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public byte[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        byte[] bArr = this.buffer;
        if (bArr.length < i) {
            int length = bArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(bArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}

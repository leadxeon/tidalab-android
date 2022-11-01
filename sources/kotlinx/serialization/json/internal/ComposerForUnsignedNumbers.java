package kotlinx.serialization.json.internal;

import kotlin.ULong;
import kotlinx.serialization.json.Json;
/* compiled from: Composers.kt */
/* loaded from: classes.dex */
public final class ComposerForUnsignedNumbers extends Composer {
    public ComposerForUnsignedNumbers(JsonStringBuilder jsonStringBuilder, Json json) {
        super(jsonStringBuilder, json);
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long j) {
        this.sb.append(ULong.m12toStringimpl(j));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte b) {
        this.sb.append(String.valueOf(b & 255));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short s) {
        this.sb.append(String.valueOf(s & 65535));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int i) {
        this.sb.append(String.valueOf(i & 4294967295L));
    }
}

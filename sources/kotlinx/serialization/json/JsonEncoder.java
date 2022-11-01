package kotlinx.serialization.json;

import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: JsonEncoder.kt */
/* loaded from: classes.dex */
public interface JsonEncoder extends Encoder, CompositeEncoder {
    Json getJson();
}

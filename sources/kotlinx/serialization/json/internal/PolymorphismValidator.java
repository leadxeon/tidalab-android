package kotlinx.serialization.json.internal;

import kotlinx.serialization.modules.SerializersModuleCollector;
/* compiled from: PolymorphismValidator.kt */
/* loaded from: classes.dex */
public final class PolymorphismValidator implements SerializersModuleCollector {
    public final String discriminator;
    public final boolean useArrayPolymorphism;

    public PolymorphismValidator(boolean z, String str) {
        this.useArrayPolymorphism = z;
        this.discriminator = str;
    }
}

package kotlinx.serialization.json;

import com.android.tools.r8.GeneratedOutlineSupport;
/* compiled from: JsonConfiguration.kt */
/* loaded from: classes.dex */
public final class JsonConfiguration {
    public final boolean allowSpecialFloatingPointValues;
    public final boolean allowStructuredMapKeys;
    public final String classDiscriminator;
    public final boolean coerceInputValues;
    public final boolean encodeDefaults;
    public final boolean ignoreUnknownKeys;
    public final boolean isLenient;
    public final boolean prettyPrint;
    public final String prettyPrintIndent;
    public final boolean useAlternativeNames;
    public final boolean useArrayPolymorphism;

    public JsonConfiguration(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str, boolean z6, boolean z7, String str2, boolean z8, boolean z9) {
        this.encodeDefaults = z;
        this.ignoreUnknownKeys = z2;
        this.isLenient = z3;
        this.allowStructuredMapKeys = z4;
        this.prettyPrint = z5;
        this.prettyPrintIndent = str;
        this.coerceInputValues = z6;
        this.useArrayPolymorphism = z7;
        this.classDiscriminator = str2;
        this.allowSpecialFloatingPointValues = z8;
        this.useAlternativeNames = z9;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("JsonConfiguration(encodeDefaults=");
        outline13.append(this.encodeDefaults);
        outline13.append(", ignoreUnknownKeys=");
        outline13.append(this.ignoreUnknownKeys);
        outline13.append(", isLenient=");
        outline13.append(this.isLenient);
        outline13.append(", allowStructuredMapKeys=");
        outline13.append(this.allowStructuredMapKeys);
        outline13.append(", prettyPrint=");
        outline13.append(this.prettyPrint);
        outline13.append(", prettyPrintIndent='");
        outline13.append(this.prettyPrintIndent);
        outline13.append("', coerceInputValues=");
        outline13.append(this.coerceInputValues);
        outline13.append(", useArrayPolymorphism=");
        outline13.append(this.useArrayPolymorphism);
        outline13.append(", classDiscriminator='");
        outline13.append(this.classDiscriminator);
        outline13.append("', allowSpecialFloatingPointValues=");
        outline13.append(this.allowSpecialFloatingPointValues);
        outline13.append(')');
        return outline13.toString();
    }
}

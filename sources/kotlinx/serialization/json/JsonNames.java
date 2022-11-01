package kotlinx.serialization.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: JsonNames.kt */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonNames {
    String[] names();
}

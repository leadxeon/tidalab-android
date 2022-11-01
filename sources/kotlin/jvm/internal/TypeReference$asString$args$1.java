package kotlin.jvm.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;
/* compiled from: TypeReference.kt */
/* loaded from: classes.dex */
public final class TypeReference$asString$args$1 extends Lambda implements Function1<KTypeProjection, CharSequence> {
    public final /* synthetic */ TypeReference this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeReference$asString$args$1(TypeReference typeReference) {
        super(1);
        this.this$0 = typeReference;
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(KTypeProjection kTypeProjection) {
        String str;
        KTypeProjection kTypeProjection2 = kTypeProjection;
        Objects.requireNonNull(this.this$0);
        if (kTypeProjection2.variance == null) {
            return "*";
        }
        KType kType = kTypeProjection2.type;
        if (!(kType instanceof TypeReference)) {
            kType = null;
        }
        TypeReference typeReference = (TypeReference) kType;
        if (typeReference == null || (str = typeReference.asString()) == null) {
            str = String.valueOf(kTypeProjection2.type);
        }
        KVariance kVariance = kTypeProjection2.variance;
        if (kVariance != null) {
            int ordinal = kVariance.ordinal();
            if (ordinal == 0) {
                return str;
            }
            if (ordinal == 1) {
                return GeneratedOutlineSupport.outline8("in ", str);
            }
            if (ordinal == 2) {
                return GeneratedOutlineSupport.outline8("out ", str);
            }
        }
        throw new NoWhenBranchMatchedException();
    }
}

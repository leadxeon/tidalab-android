package kotlin.jvm.internal;

import java.io.Serializable;
import java.util.Objects;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public abstract class CallableReference implements KCallable, Serializable {
    public final boolean isTopLevel;
    public final String name;
    public final Class owner;
    public final Object receiver;
    public transient KCallable reflected;
    public final String signature;

    /* loaded from: classes.dex */
    public static class NoReceiver implements Serializable {
        public static final NoReceiver INSTANCE = new NoReceiver();
    }

    public CallableReference() {
        this.receiver = NoReceiver.INSTANCE;
        this.owner = null;
        this.name = null;
        this.signature = null;
        this.isTopLevel = false;
    }

    public KCallable compute() {
        KCallable kCallable = this.reflected;
        if (kCallable != null) {
            return kCallable;
        }
        KCallable computeReflected = computeReflected();
        this.reflected = computeReflected;
        return computeReflected;
    }

    public abstract KCallable computeReflected();

    public KDeclarationContainer getOwner() {
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (!this.isTopLevel) {
            return Reflection.getOrCreateKotlinClass(cls);
        }
        Objects.requireNonNull(Reflection.factory);
        return new PackageReference(cls, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }
}

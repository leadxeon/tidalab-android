package kotlinx.coroutines.channels;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Channel.kt */
/* loaded from: classes.dex */
public final class ChannelResult<T> {
    public static final Companion Companion = new Companion(null);
    public static final Failed failed = new Failed();
    public final Object holder;

    /* compiled from: Channel.kt */
    /* loaded from: classes.dex */
    public static final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Closed(");
            outline13.append(this.cause);
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* compiled from: Channel.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* compiled from: Channel.kt */
    /* loaded from: classes.dex */
    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    public /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ChannelResult) && Intrinsics.areEqual(this.holder, ((ChannelResult) obj).holder);
    }

    public int hashCode() {
        Object obj = this.holder;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        Object obj = this.holder;
        if (obj instanceof Closed) {
            return obj.toString();
        }
        return "Value(" + obj + ')';
    }
}

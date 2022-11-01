package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class JobSupportKt {
    public static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    public static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
    public static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");
    public static final Symbol SEALED = new Symbol("SEALED");
    public static final Empty EMPTY_NEW = new Empty(false);
    public static final Empty EMPTY_ACTIVE = new Empty(true);

    public static final Object unboxState(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }
}

package kotlinx.coroutines.selects;

import kotlinx.coroutines.internal.Symbol;
/* compiled from: Select.kt */
/* loaded from: classes.dex */
public final class SelectKt {
    public static final Object NOT_SELECTED = new Symbol("NOT_SELECTED");
    public static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
    public static final Object UNDECIDED = new Symbol("UNDECIDED");
    public static final Object RESUMED = new Symbol("RESUMED");
    public static final SeqNumber selectOpSequenceNumber = new SeqNumber();
}

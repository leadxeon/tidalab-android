package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
/* compiled from: Select.kt */
/* loaded from: classes.dex */
public interface SelectClause1<Q> {
    <R> void registerSelectClause1(SelectInstance<? super R> selectInstance, Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2);
}

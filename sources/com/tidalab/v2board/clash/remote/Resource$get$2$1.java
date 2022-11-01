package com.tidalab.v2board.clash.remote;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Resource.kt */
/* loaded from: classes.dex */
public final class Resource$get$2$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ Resource$get$2$callback$1 $callback;
    public final /* synthetic */ Resource<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Resource$get$2$1(Resource<T> resource, Resource$get$2$callback$1 resource$get$2$callback$1) {
        super(1);
        this.this$0 = resource;
        this.$callback = resource$get$2$callback$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        Resource<T> resource = this.this$0;
        Resource$get$2$callback$1 resource$get$2$callback$1 = this.$callback;
        synchronized (resource) {
            resource.pending.remove(resource$get$2$callback$1);
        }
        return Unit.INSTANCE;
    }
}

package kotlinx.serialization.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class TripleSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public final /* synthetic */ TripleSerializer<A, B, C> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TripleSerializer$descriptor$1(TripleSerializer<A, B, C> tripleSerializer) {
        super(1);
        this.this$0 = tripleSerializer;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "first", this.this$0.aSerializer.getDescriptor(), null, false, 12);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "second", this.this$0.bSerializer.getDescriptor(), null, false, 12);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "third", this.this$0.cSerializer.getDescriptor(), null, false, 12);
        return Unit.INSTANCE;
    }
}

package kotlinx.serialization;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.StringSerializer;
/* compiled from: PolymorphicSerializer.kt */
/* loaded from: classes.dex */
public final class PolymorphicSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public final /* synthetic */ PolymorphicSerializer<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PolymorphicSerializer$descriptor$1(PolymorphicSerializer<T> polymorphicSerializer) {
        super(1);
        this.this$0 = polymorphicSerializer;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        SerialDescriptor buildSerialDescriptor;
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "type", StringSerializer.descriptor, null, false, 12);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("kotlinx.serialization.Polymorphic<");
        outline13.append((Object) this.this$0.baseClass.getSimpleName());
        outline13.append('>');
        buildSerialDescriptor = InputKt.buildSerialDescriptor(outline13.toString(), SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "value", buildSerialDescriptor, null, false, 12);
        return Unit.INSTANCE;
    }
}

package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.descriptors.StructureKind;
/* compiled from: Enums.kt */
/* loaded from: classes.dex */
public final class EnumSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public final /* synthetic */ String $serialName;
    public final /* synthetic */ EnumSerializer<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumSerializer$descriptor$1(EnumSerializer<T> enumSerializer, String str) {
        super(1);
        this.this$0 = enumSerializer;
        this.$serialName = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        SerialDescriptor buildSerialDescriptor;
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        Enum[] enumArr = this.this$0.values;
        String str = this.$serialName;
        for (Enum r0 : enumArr) {
            StringBuilder outline14 = GeneratedOutlineSupport.outline14(str, '.');
            outline14.append(r0.name());
            buildSerialDescriptor = InputKt.buildSerialDescriptor(outline14.toString(), StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
            ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, r0.name(), buildSerialDescriptor, null, false, 12);
        }
        return Unit.INSTANCE;
    }
}

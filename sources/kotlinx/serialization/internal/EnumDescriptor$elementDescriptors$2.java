package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.descriptors.StructureKind;
/* compiled from: Enums.kt */
/* loaded from: classes.dex */
public final class EnumDescriptor$elementDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {
    public final /* synthetic */ int $elementsCount;
    public final /* synthetic */ String $name;
    public final /* synthetic */ EnumDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumDescriptor$elementDescriptors$2(int i, String str, EnumDescriptor enumDescriptor) {
        super(0);
        this.$elementsCount = i;
        this.$name = str;
        this.this$0 = enumDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    public SerialDescriptor[] invoke() {
        SerialDescriptor buildSerialDescriptor;
        int i = this.$elementsCount;
        SerialDescriptor[] serialDescriptorArr = new SerialDescriptor[i];
        for (int i2 = 0; i2 < i; i2++) {
            buildSerialDescriptor = InputKt.buildSerialDescriptor(this.$name + '.' + this.this$0.names[i2], StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
            serialDescriptorArr[i2] = buildSerialDescriptor;
        }
        return serialDescriptorArr;
    }
}

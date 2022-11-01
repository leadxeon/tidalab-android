package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Lazy;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorKt$elementNames$1$1;
import kotlinx.serialization.descriptors.SerialDescriptorKt$special$$inlined$Iterable$2;
import kotlinx.serialization.descriptors.SerialKind;
/* compiled from: Enums.kt */
/* loaded from: classes.dex */
public final class EnumDescriptor extends PluginGeneratedSerialDescriptor {
    public final Lazy elementDescriptors$delegate;
    public final SerialKind kind = SerialKind.ENUM.INSTANCE;

    public EnumDescriptor(String str, int i) {
        super(str, null, i);
        this.elementDescriptors$delegate = InputKt.lazy(new EnumDescriptor$elementDescriptors$2(i, str, this));
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SerialDescriptor)) {
            return false;
        }
        SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
        return serialDescriptor.getKind() == SerialKind.ENUM.INSTANCE && Intrinsics.areEqual(this.serialName, serialDescriptor.getSerialName()) && Intrinsics.areEqual(Platform_commonKt.cachedSerialNames(this), Platform_commonKt.cachedSerialNames(serialDescriptor));
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor, kotlinx.serialization.descriptors.SerialDescriptor
    public SerialDescriptor getElementDescriptor(int i) {
        return ((SerialDescriptor[]) this.elementDescriptors$delegate.getValue())[i];
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor, kotlinx.serialization.descriptors.SerialDescriptor
    public SerialKind getKind() {
        return this.kind;
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public int hashCode() {
        int hashCode = this.serialName.hashCode();
        int i = 1;
        SerialDescriptorKt$elementNames$1$1 serialDescriptorKt$elementNames$1$1 = new SerialDescriptorKt$elementNames$1$1(this);
        while (serialDescriptorKt$elementNames$1$1.hasNext()) {
            int i2 = i * 31;
            String str = (String) serialDescriptorKt$elementNames$1$1.next();
            i = i2 + (str != null ? str.hashCode() : 0);
        }
        return (hashCode * 31) + i;
    }

    @Override // kotlinx.serialization.internal.PluginGeneratedSerialDescriptor
    public String toString() {
        return ArraysKt___ArraysKt.joinToString$default(new SerialDescriptorKt$special$$inlined$Iterable$2(this), ", ", Intrinsics.stringPlus(this.serialName, "("), ")", 0, null, null, 56);
    }
}

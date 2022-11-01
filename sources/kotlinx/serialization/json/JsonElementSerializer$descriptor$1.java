package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonElementSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {
    public static final JsonElementSerializer$descriptor$1 INSTANCE = new JsonElementSerializer$descriptor$1();

    public JsonElementSerializer$descriptor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        ClassSerialDescriptorBuilder classSerialDescriptorBuilder2 = classSerialDescriptorBuilder;
        final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc r0 = $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc.INSTANCE$0;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonPrimitive", new SerialDescriptor(r0) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            public final /* synthetic */ Function0<SerialDescriptor> $deferred;
            public final Lazy original$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$deferred = r0;
                this.original$delegate = InputKt.lazy(r0);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String str) {
                return getOriginal().getElementIndex(str);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            public final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return false;
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return false;
            }
        }, null, false, 12);
        final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc r02 = $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc.INSTANCE$1;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonNull", new SerialDescriptor(r02) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            public final /* synthetic */ Function0<SerialDescriptor> $deferred;
            public final Lazy original$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$deferred = r02;
                this.original$delegate = InputKt.lazy(r02);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String str) {
                return getOriginal().getElementIndex(str);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            public final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return false;
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return false;
            }
        }, null, false, 12);
        final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc r03 = $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc.INSTANCE$2;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonLiteral", new SerialDescriptor(r03) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            public final /* synthetic */ Function0<SerialDescriptor> $deferred;
            public final Lazy original$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$deferred = r03;
                this.original$delegate = InputKt.lazy(r03);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String str) {
                return getOriginal().getElementIndex(str);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            public final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return false;
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return false;
            }
        }, null, false, 12);
        final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc r04 = $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc.INSTANCE$3;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonObject", new SerialDescriptor(r04) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            public final /* synthetic */ Function0<SerialDescriptor> $deferred;
            public final Lazy original$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$deferred = r04;
                this.original$delegate = InputKt.lazy(r04);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String str) {
                return getOriginal().getElementIndex(str);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            public final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return false;
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return false;
            }
        }, null, false, 12);
        final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc r05 = $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc.INSTANCE$4;
        ClassSerialDescriptorBuilder.element$default(classSerialDescriptorBuilder2, "JsonArray", new SerialDescriptor(r05) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1
            public final /* synthetic */ Function0<SerialDescriptor> $deferred;
            public final Lazy original$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$deferred = r05;
                this.original$delegate = InputKt.lazy(r05);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String str) {
                return getOriginal().getElementIndex(str);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            public final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isInline() {
                return false;
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return false;
            }
        }, null, false, 12);
        return Unit.INSTANCE;
    }
}

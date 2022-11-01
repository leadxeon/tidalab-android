package com.tidalab.v2board.clash.core;

import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.core.bridge.ClashException;
import com.tidalab.v2board.clash.core.bridge.FetchCallback;
import com.tidalab.v2board.clash.core.model.FetchStatus;
import com.tidalab.v2board.clash.core.model.FetchStatus$$serializer;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.File;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonImpl;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: Clash.kt */
/* loaded from: classes.dex */
public final class Clash {
    public static final Json ConfigurationOverrideJson;
    public static final Clash INSTANCE = new Clash();

    /* compiled from: Clash.kt */
    /* loaded from: classes.dex */
    public enum OverrideSlot {
        Persist,
        Session
    }

    static {
        boolean z;
        Json.Default r0 = Json.Default;
        JsonConfiguration jsonConfiguration = r0.configuration;
        boolean z2 = jsonConfiguration.encodeDefaults;
        boolean z3 = jsonConfiguration.isLenient;
        boolean z4 = jsonConfiguration.allowStructuredMapKeys;
        boolean z5 = jsonConfiguration.prettyPrint;
        String str = jsonConfiguration.prettyPrintIndent;
        boolean z6 = jsonConfiguration.coerceInputValues;
        boolean z7 = jsonConfiguration.useArrayPolymorphism;
        String str2 = jsonConfiguration.classDiscriminator;
        boolean z8 = jsonConfiguration.allowSpecialFloatingPointValues;
        boolean z9 = jsonConfiguration.useAlternativeNames;
        SerializersModule serializersModule = r0.serializersModule;
        Unit unit = Unit.INSTANCE;
        if (!z7 || Intrinsics.areEqual(str2, "type")) {
            if (!z5) {
                if (!Intrinsics.areEqual(str, "    ")) {
                    throw new IllegalArgumentException("Indent should not be specified when default printing mode is used".toString());
                }
            } else if (!Intrinsics.areEqual(str, "    ")) {
                int i = 0;
                while (true) {
                    boolean z10 = true;
                    if (i >= str.length()) {
                        z = true;
                        break;
                    }
                    char charAt = str.charAt(i);
                    if (!(charAt == ' ' || charAt == '\t' || charAt == '\r' || charAt == '\n')) {
                        z10 = false;
                    }
                    if (!z10) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Only whitespace, tab, newline and carriage return are allowed as pretty print symbols. Had ", str).toString());
                }
            }
            ConfigurationOverrideJson = new JsonImpl(new JsonConfiguration(false, true, z3, z4, z5, str, z6, z7, str2, z8, z9), serializersModule);
            return;
        }
        throw new IllegalArgumentException("Class discriminator should not be specified when array polymorphism is specified".toString());
    }

    public final void clearOverride(OverrideSlot overrideSlot) {
        Bridge.INSTANCE.nativeClearOverride(overrideSlot.ordinal());
    }

    public final CompletableDeferred<Unit> fetchAndValid(File file, String str, boolean z, final Function1<? super FetchStatus, Unit> function1) {
        final CompletableDeferred<Unit> CompletableDeferred$default = InputKt.CompletableDeferred$default(null, 1);
        Bridge.INSTANCE.nativeFetchAndValid(new FetchCallback() { // from class: com.tidalab.v2board.clash.core.Clash$fetchAndValid$1$1
            @Override // com.tidalab.v2board.clash.core.bridge.FetchCallback
            public void complete(String str2) {
                if (str2 != null) {
                    CompletableDeferred$default.completeExceptionally(new ClashException(str2));
                } else {
                    CompletableDeferred$default.complete(Unit.INSTANCE);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.tidalab.v2board.clash.core.bridge.FetchCallback
            public void report(String str2) {
                Function1<FetchStatus, Unit> function12 = function1;
                Json.Default r1 = Json.Default;
                Objects.requireNonNull(FetchStatus.CREATOR);
                function12.invoke(r1.decodeFromString(FetchStatus$$serializer.INSTANCE, str2));
            }
        }, file.getAbsolutePath(), str, z);
        return CompletableDeferred$default;
    }

    public final void notifyDnsChanged(List<String> list) {
        Bridge.INSTANCE.nativeNotifyDnsChanged(ArraysKt___ArraysKt.joinToString$default(list, ",", null, null, 0, null, null, 62));
    }
}

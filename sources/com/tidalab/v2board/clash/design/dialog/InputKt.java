package com.tidalab.v2board.clash.design.dialog;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.common.constants.Metadata;
import com.tidalab.v2board.clash.common.constants.Permissions;
import com.tidalab.v2board.clash.design.Design;
import com.tidalab.v2board.clash.design.databinding.DialogTextFieldBinding;
import com.tidalab.v2board.clash.design.databinding.PreferenceCategoryBinding;
import com.tidalab.v2board.clash.design.databinding.PreferenceClickableBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.AppInfo;
import com.tidalab.v2board.clash.design.preference.ClickableKt$clickable$1;
import com.tidalab.v2board.clash.design.preference.ClickablePreference;
import com.tidalab.v2board.clash.design.preference.EditableListOverlayResult;
import com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$1;
import com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$2;
import com.tidalab.v2board.clash.design.preference.EditableTextKt$editableText$impl$1;
import com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$1;
import com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$2;
import com.tidalab.v2board.clash.design.preference.EditableTextListKt$editableTextList$impl$1;
import com.tidalab.v2board.clash.design.preference.EditableTextListPreference;
import com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$1;
import com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$2;
import com.tidalab.v2board.clash.design.preference.EditableTextMapKt$editableTextMap$impl$1;
import com.tidalab.v2board.clash.design.preference.EditableTextMapPreference;
import com.tidalab.v2board.clash.design.preference.EditableTextPreference;
import com.tidalab.v2board.clash.design.preference.NullableTextAdapter;
import com.tidalab.v2board.clash.design.preference.OverlayKt$requestEditableListOverlay$2;
import com.tidalab.v2board.clash.design.preference.Preference;
import com.tidalab.v2board.clash.design.preference.PreferenceScreen;
import com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$1;
import com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$2;
import com.tidalab.v2board.clash.design.preference.SelectableListKt$selectableList$impl$1;
import com.tidalab.v2board.clash.design.preference.SelectableListPreference;
import com.tidalab.v2board.clash.design.preference.TextAdapter;
import com.tidalab.v2board.clash.design.store.UiStore;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.design.util.$$Lambda$ViewKt$Leq_OYUMGGHh0dLHMsbHrbNfgyY;
import com.tidalab.v2board.clash.design.util.AppBarElevationController;
import com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$2;
import com.tidalab.v2board.clash.design.util.ToastKt$showExceptionToast$2;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.ClashService;
import com.tidalab.v2board.clash.service.TunService;
import com.tidalab.v2board.clash.service.data.Database;
import com.tidalab.v2board.clash.service.data.ImportedDao;
import com.tidalab.v2board.clash.service.data.PendingDao;
import com.tidalab.v2board.clash.service.data.SelectionDao;
import com.tidalab.v2board.clash.service.util.CoroutineKt$cancelAndJoinBlocking$1;
import com.tidalab.v2board.clash.util.ContentKt$copyContentTo$2;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import kotlin.Lazy;
import kotlin.Result;
import kotlin.SynchronizedLazyImpl;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.io.FilePathComponents;
import kotlin.io.FileTreeWalk;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.KClass;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KType;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.BlockingCoroutine;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandlerImplKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DispatchedCoroutine;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Incomplete;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.LazyStandaloneCoroutine;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import kotlinx.coroutines.channels.ArrayChannel;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ConflatedChannel;
import kotlinx.coroutines.channels.LinkedListChannel;
import kotlinx.coroutines.channels.RendezvousChannel;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.internal.UndeliveredElementException;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt__SerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorImpl;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.InlineClassDescriptor;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.internal.CharMappings;
import kotlinx.serialization.json.internal.JsonDecodingException;
import kotlinx.serialization.json.internal.JsonEncodingException;
import kotlinx.serialization.json.internal.JsonLexer;
import kotlinx.serialization.json.internal.StringOpsKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import okhttp3.HttpUrl;
/* compiled from: Input.kt */
/* loaded from: classes.dex */
public final class InputKt {
    public static Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2) {
        BufferOverflow bufferOverflow2 = BufferOverflow.SUSPEND;
        boolean z = false;
        if ((i2 & 1) != 0) {
            i = 0;
        }
        BufferOverflow bufferOverflow3 = (i2 & 2) != 0 ? bufferOverflow2 : null;
        int i3 = i2 & 4;
        int i4 = 1;
        if (i == -2) {
            if (bufferOverflow3 == bufferOverflow2) {
                Objects.requireNonNull(Channel.Factory);
                i4 = Channel.Factory.CHANNEL_DEFAULT_CAPACITY;
            }
            return new ArrayChannel(i4, bufferOverflow3, null);
        } else if (i == -1) {
            if (bufferOverflow3 == bufferOverflow2) {
                z = true;
            }
            if (z) {
                return new ConflatedChannel(null);
            }
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        } else if (i != 0) {
            if (i == Integer.MAX_VALUE) {
                return new LinkedListChannel(null);
            }
            if (i == 1 && bufferOverflow3 == BufferOverflow.DROP_OLDEST) {
                return new ConflatedChannel(null);
            }
            return new ArrayChannel(i, bufferOverflow3, null);
        } else if (bufferOverflow3 == bufferOverflow2) {
            return new RendezvousChannel(null);
        } else {
            return new ArrayChannel(1, bufferOverflow3, null);
        }
    }

    public static CompletableDeferred CompletableDeferred$default(Job job, int i) {
        int i2 = i & 1;
        return new CompletableDeferredImpl(null);
    }

    public static final CoroutineScope CoroutineScope(CoroutineContext coroutineContext) {
        int i = Job.$r8$clinit;
        CoroutineContext coroutineContext2 = (CoroutineDispatcher) coroutineContext;
        if (coroutineContext2.get(Job.Key.$$INSTANCE) == null) {
            coroutineContext2 = coroutineContext2.plus(new JobImpl(null));
        }
        return new ContextScope(coroutineContext2);
    }

    public static final ImportedDao ImportedDao() {
        return Database.Companion.getDatabase().openImportedDao();
    }

    public static final <T> SerialDescriptor InlinePrimitiveDescriptor(String str, final KSerializer<T> kSerializer) {
        return new InlineClassDescriptor(str, new GeneratedSerializer<T>() { // from class: kotlinx.serialization.internal.InlineClassDescriptorKt$InlinePrimitiveDescriptor$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlinx.serialization.internal.GeneratedSerializer
            public KSerializer<?>[] childSerializers() {
                return new KSerializer[]{kSerializer};
            }

            @Override // kotlinx.serialization.DeserializationStrategy
            public T deserialize(Decoder decoder) {
                throw new IllegalStateException("unsupported".toString());
            }

            @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
            public SerialDescriptor getDescriptor() {
                throw new IllegalStateException("unsupported".toString());
            }

            @Override // kotlinx.serialization.SerializationStrategy
            public void serialize(Encoder encoder, T t) {
                throw new IllegalStateException("unsupported".toString());
            }

            @Override // kotlinx.serialization.internal.GeneratedSerializer
            public KSerializer<?>[] typeParametersSerializers() {
                return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
            }
        });
    }

    public static final JsonDecodingException InvalidFloatingPointDecoded(Number number, String str, String str2) {
        return JsonDecodingException(-1, "Unexpected special floating-point value " + number + " with key " + str + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + minify(str2, -1));
    }

    public static final JsonEncodingException InvalidFloatingPointEncoded(Number number, String str) {
        return new JsonEncodingException("Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + minify(str, -1));
    }

    public static final JsonEncodingException InvalidKeyKindException(SerialDescriptor serialDescriptor) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Value of type '");
        outline13.append(serialDescriptor.getSerialName());
        outline13.append("' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '");
        outline13.append(serialDescriptor.getKind());
        outline13.append("'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.");
        return new JsonEncodingException(outline13.toString());
    }

    public static final JsonDecodingException JsonDecodingException(int i, String str) {
        if (i >= 0) {
            str = "Unexpected JSON token at offset " + i + ": " + str;
        }
        return new JsonDecodingException(str);
    }

    public static final PendingDao PendingDao() {
        return Database.Companion.getDatabase().openPendingDao();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlinx.serialization.descriptors.SerialDescriptor PrimitiveSerialDescriptor(java.lang.String r13, kotlinx.serialization.descriptors.PrimitiveKind r14) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.PrimitiveSerialDescriptor(java.lang.String, kotlinx.serialization.descriptors.PrimitiveKind):kotlinx.serialization.descriptors.SerialDescriptor");
    }

    public static final SelectionDao SelectionDao() {
        return Database.Companion.getDatabase().openSelectionProxyDao();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$requestEditTextList(java.util.List r6, android.content.Context r7, com.tidalab.v2board.clash.design.preference.TextAdapter r8, java.lang.CharSequence r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$1 r0 = (com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$1 r0 = new com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$1
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r4) goto L_0x0030
            java.lang.Object r6 = r0.L$1
            com.tidalab.v2board.clash.design.adapter.EditableTextListAdapter r6 = (com.tidalab.v2board.clash.design.adapter.EditableTextListAdapter) r6
            java.lang.Object r7 = r0.L$0
            java.util.List r7 = (java.util.List) r7
            throwOnFailure(r10)
            goto L_0x0066
        L_0x0030:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0038:
            throwOnFailure(r10)
            com.tidalab.v2board.clash.design.adapter.EditableTextListAdapter r10 = new com.tidalab.v2board.clash.design.adapter.EditableTextListAdapter
            if (r6 != 0) goto L_0x0041
            r2 = r3
            goto L_0x0046
        L_0x0041:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r6)
        L_0x0046:
            if (r2 != 0) goto L_0x004d
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L_0x004d:
            r10.<init>(r7, r2, r8)
            com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$result$1 r8 = new com.tidalab.v2board.clash.design.preference.EditableTextListKt$requestEditTextList$result$1
            r8.<init>(r7, r9, r10, r3)
            r0.L$0 = r6
            r0.L$1 = r10
            r0.label = r4
            java.lang.Object r7 = requestEditableListOverlay(r7, r10, r9, r8, r0)
            if (r7 != r1) goto L_0x0062
            goto L_0x0080
        L_0x0062:
            r5 = r7
            r7 = r6
            r6 = r10
            r10 = r5
        L_0x0066:
            com.tidalab.v2board.clash.design.preference.EditableListOverlayResult r10 = (com.tidalab.v2board.clash.design.preference.EditableListOverlayResult) r10
            int r8 = r10.ordinal()
            if (r8 == 0) goto L_0x007f
            if (r8 == r4) goto L_0x007b
            r6 = 2
            if (r8 != r6) goto L_0x0075
            r1 = r3
            goto L_0x0080
        L_0x0075:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L_0x007b:
            java.util.List<T> r6 = r6.values
            r1 = r6
            goto L_0x0080
        L_0x007f:
            r1 = r7
        L_0x0080:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.access$requestEditTextList(java.util.List, android.content.Context, com.tidalab.v2board.clash.design.preference.TextAdapter, java.lang.CharSequence, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$requestEditTextMap(java.util.Map r6, android.content.Context r7, com.tidalab.v2board.clash.design.preference.TextAdapter r8, com.tidalab.v2board.clash.design.preference.TextAdapter r9, java.lang.CharSequence r10, kotlin.coroutines.Continuation r11) {
        /*
            boolean r0 = r11 instanceof com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$1 r0 = (com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$1 r0 = new com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$1
            r0.<init>(r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0058
            if (r2 == r4) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r6 = r0.L$1
            com.tidalab.v2board.clash.design.adapter.EditableTextMapAdapter r6 = (com.tidalab.v2board.clash.design.adapter.EditableTextMapAdapter) r6
            java.lang.Object r7 = r0.L$0
            java.util.Map r7 = (java.util.Map) r7
            throwOnFailure(r11)
            r1 = r7
            goto L_0x0098
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            java.lang.Object r6 = r0.L$4
            r10 = r6
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            java.lang.Object r6 = r0.L$3
            r9 = r6
            com.tidalab.v2board.clash.design.preference.TextAdapter r9 = (com.tidalab.v2board.clash.design.preference.TextAdapter) r9
            java.lang.Object r6 = r0.L$2
            r8 = r6
            com.tidalab.v2board.clash.design.preference.TextAdapter r8 = (com.tidalab.v2board.clash.design.preference.TextAdapter) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            android.content.Context r7 = (android.content.Context) r7
            java.lang.Object r6 = r0.L$0
            java.util.Map r6 = (java.util.Map) r6
            throwOnFailure(r11)
            goto L_0x0077
        L_0x0058:
            throwOnFailure(r11)
            kotlinx.coroutines.Dispatchers r11 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.Default
            com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$editableValue$1 r2 = new com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$editableValue$1
            r2.<init>(r6, r5)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.label = r4
            java.lang.Object r11 = withContext(r11, r2, r0)
            if (r11 != r1) goto L_0x0077
            goto L_0x00b2
        L_0x0077:
            java.util.List r11 = (java.util.List) r11
            com.tidalab.v2board.clash.design.adapter.EditableTextMapAdapter r2 = new com.tidalab.v2board.clash.design.adapter.EditableTextMapAdapter
            r2.<init>(r7, r11, r8, r9)
            com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$result$1 r8 = new com.tidalab.v2board.clash.design.preference.EditableTextMapKt$requestEditTextMap$result$1
            r8.<init>(r7, r10, r2, r5)
            r0.L$0 = r6
            r0.L$1 = r2
            r0.L$2 = r5
            r0.L$3 = r5
            r0.L$4 = r5
            r0.label = r3
            java.lang.Object r11 = requestEditableListOverlay(r7, r2, r10, r8, r0)
            if (r11 != r1) goto L_0x0096
            goto L_0x00b2
        L_0x0096:
            r1 = r6
            r6 = r2
        L_0x0098:
            com.tidalab.v2board.clash.design.preference.EditableListOverlayResult r11 = (com.tidalab.v2board.clash.design.preference.EditableListOverlayResult) r11
            int r7 = r11.ordinal()
            if (r7 == 0) goto L_0x00b2
            if (r7 == r4) goto L_0x00ac
            if (r7 != r3) goto L_0x00a6
            r1 = r5
            goto L_0x00b2
        L_0x00a6:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L_0x00ac:
            java.util.List<kotlin.Pair<K, V>> r6 = r6.values
            java.util.Map r1 = kotlin.collections.ArraysKt___ArraysKt.toMap(r6)
        L_0x00b2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.access$requestEditTextMap(java.util.Map, android.content.Context, com.tidalab.v2board.clash.design.preference.TextAdapter, com.tidalab.v2board.clash.design.preference.TextAdapter, java.lang.CharSequence, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void access$verify(Encoder encoder) {
        if ((encoder instanceof JsonEncoder ? (JsonEncoder) encoder : null) == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got ", Reflection.getOrCreateKotlinClass(encoder.getClass())));
        }
    }

    public static final void addElement(PreferenceScreen preferenceScreen, Preference preference) {
        preferenceScreen.getRoot().addView(preference.getView(), new LinearLayout.LayoutParams(-1, -2));
    }

    public static final void addSuppressed(Throwable th, Throwable th2) {
        if (th != th2) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
        }
    }

    public static final void applyFrom(ActivityBarLayout activityBarLayout, final Context context) {
        if (context instanceof Activity) {
            ImageView imageView = (ImageView) activityBarLayout.findViewById(R.id.activity_bar_close_view);
            if (imageView != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$ActivityBarKt$gjKvWYZgKybiF904q00W7Fuf4uA
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ((Activity) context).onBackPressed();
                    }
                });
            }
            TextView textView = (TextView) activityBarLayout.findViewById(R.id.activity_bar_title_view);
            if (textView != null) {
                textView.setText(((Activity) context).getTitle());
            }
        }
    }

    public static final void applyLinearAdapter(RecyclerView recyclerView, Context context, RecyclerView.Adapter<?> adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(1, false));
        recyclerView.setAdapter(adapter);
    }

    public static final JsonDecoder asJsonDecoder(Decoder decoder) {
        JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
        if (jsonDecoder != null) {
            return jsonDecoder;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got ", Reflection.getOrCreateKotlinClass(decoder.getClass())));
    }

    public static final <T> Sequence<T> asSequence(final Iterator<? extends T> it) {
        Sequence<T> sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 = new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                return it;
            }
        };
        return sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 instanceof ConstrainedOnceSequence ? sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 : new ConstrainedOnceSequence(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1);
    }

    public static final void bindAppBarElevation(RecyclerView recyclerView, final ActivityBarLayout activityBarLayout) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.tidalab.v2board.clash.design.util.ElevationKt$bindAppBarElevation$1
            public final AppBarElevationController controller;

            {
                this.controller = new AppBarElevationController(ActivityBarLayout.this);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                this.controller.setElevated(!(recyclerView2.computeHorizontalScrollOffset() == 0 && recyclerView2.computeVerticalScrollOffset() == 0));
            }
        });
    }

    public static final SerialDescriptor buildClassSerialDescriptor(String str, SerialDescriptor[] serialDescriptorArr, Function1<? super ClassSerialDescriptorBuilder, Unit> function1) {
        if (!StringsKt__IndentKt.isBlank(str)) {
            ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(str);
            function1.invoke(classSerialDescriptorBuilder);
            return new SerialDescriptorImpl(str, StructureKind.CLASS.INSTANCE, classSerialDescriptorBuilder.elementNames.size(), ArraysKt___ArraysKt.toList(serialDescriptorArr), classSerialDescriptorBuilder);
        }
        throw new IllegalArgumentException("Blank serial names are prohibited".toString());
    }

    public static final SerialDescriptor buildSerialDescriptor(String str, SerialKind serialKind, SerialDescriptor[] serialDescriptorArr, Function1<? super ClassSerialDescriptorBuilder, Unit> function1) {
        if (!(!StringsKt__IndentKt.isBlank(str))) {
            throw new IllegalArgumentException("Blank serial names are prohibited".toString());
        } else if (!Intrinsics.areEqual(serialKind, StructureKind.CLASS.INSTANCE)) {
            ClassSerialDescriptorBuilder classSerialDescriptorBuilder = new ClassSerialDescriptorBuilder(str);
            function1.invoke(classSerialDescriptorBuilder);
            return new SerialDescriptorImpl(str, serialKind, classSerialDescriptorBuilder.elementNames.size(), ArraysKt___ArraysKt.toList(serialDescriptorArr), classSerialDescriptorBuilder);
        } else {
            throw new IllegalArgumentException("For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> UndeliveredElementException callUndeliveredElementCatchingException(Function1<? super E, Unit> function1, E e, UndeliveredElementException undeliveredElementException) {
        try {
            function1.invoke(e);
        } catch (Throwable th) {
            if (undeliveredElementException == null || undeliveredElementException.getCause() == th) {
                return new UndeliveredElementException(Intrinsics.stringPlus("Exception in undelivered element handler for ", e), th);
            }
            addSuppressed(undeliveredElementException, th);
        }
        return undeliveredElementException;
    }

    public static /* synthetic */ UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function1, Object obj, UndeliveredElementException undeliveredElementException, int i) {
        int i2 = i & 2;
        return callUndeliveredElementCatchingException(function1, obj, null);
    }

    public static /* synthetic */ void cancel$default(Job job, CancellationException cancellationException, int i, Object obj) {
        int i2 = i & 1;
        job.cancel(null);
    }

    public static final void cancelAndJoinBlocking(CoroutineScope coroutineScope) {
        runBlocking$default(null, new CoroutineKt$cancelAndJoinBlocking$1(coroutineScope, null), 1, null);
    }

    public static final void category(PreferenceScreen preferenceScreen, int i) {
        LayoutInflater from = LayoutInflater.from(preferenceScreen.getContext());
        ViewGroup root = preferenceScreen.getRoot();
        int i2 = PreferenceCategoryBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        PreferenceCategoryBinding preferenceCategoryBinding = (PreferenceCategoryBinding) ViewDataBinding.inflateInternal(from, R.layout.preference_category, root, false, null);
        preferenceCategoryBinding.textView.setText(preferenceScreen.getContext().getString(i));
        preferenceScreen.getRoot().addView(preferenceCategoryBinding.mRoot, new LinearLayout.LayoutParams(-1, -2));
    }

    public static final byte charToTokenClass(char c) {
        if (c < '~') {
            return CharMappings.CHAR_TO_TOKEN[c];
        }
        return (byte) 0;
    }

    public static final int checkRadix(int i) {
        if (2 <= i && 36 >= i) {
            return i;
        }
        StringBuilder outline15 = GeneratedOutlineSupport.outline15("radix ", i, " was not in valid range ");
        outline15.append(new IntRange(2, 36));
        throw new IllegalArgumentException(outline15.toString());
    }

    public static ClickablePreference clickable$default(PreferenceScreen preferenceScreen, int i, Integer num, Integer num2, Function1 function1, int i2) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        if ((i2 & 4) != 0) {
            num2 = null;
        }
        if ((i2 & 8) != 0) {
            function1 = ClickableKt$clickable$1.INSTANCE;
        }
        LayoutInflater from = LayoutInflater.from(preferenceScreen.getContext());
        ViewGroup root = preferenceScreen.getRoot();
        int i3 = PreferenceClickableBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        final PreferenceClickableBinding preferenceClickableBinding = (PreferenceClickableBinding) ViewDataBinding.inflateInternal(from, R.layout.preference_clickable, root, false, null);
        ClickablePreference clickableKt$clickable$impl$1 = new ClickablePreference() { // from class: com.tidalab.v2board.clash.design.preference.ClickableKt$clickable$impl$1
            @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
            public void clicked(final Function0<Unit> function0) {
                PreferenceClickableBinding.this.mRoot.setOnClickListener(new View.OnClickListener() { // from class: com.tidalab.v2board.clash.design.preference.-$$Lambda$ClickableKt$clickable$impl$1$YNJMmGJwxLOUFPcDyCV3DsL5Bfs
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Function0.this.invoke();
                    }
                });
            }

            @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
            public CharSequence getTitle() {
                return PreferenceClickableBinding.this.titleView.getText();
            }

            @Override // com.tidalab.v2board.clash.design.preference.Preference
            public View getView() {
                return PreferenceClickableBinding.this.mRoot;
            }

            @Override // com.tidalab.v2board.clash.design.preference.Preference
            public void setEnabled(boolean z) {
                getView().setEnabled(z);
                getView().setClickable(z);
                getView().setFocusable(z);
                getView().setAlpha(z ? 1.0f : 0.33f);
            }

            @Override // com.tidalab.v2board.clash.design.preference.ClickablePreference
            public void setSummary(CharSequence charSequence) {
                PreferenceClickableBinding.this.summaryView.setText(charSequence);
                PreferenceClickableBinding.this.summaryView.setVisibility(charSequence == null ? 8 : 0);
            }
        };
        preferenceClickableBinding.titleView.setText(preferenceScreen.getContext().getText(i));
        if (num != null) {
            preferenceClickableBinding.iconView.setBackground(PathParser.getDrawableCompat(preferenceScreen.getContext(), num.intValue()));
        }
        if (num2 != null) {
            clickableKt$clickable$impl$1.setSummary(preferenceScreen.getContext().getText(num2.intValue()));
        } else {
            clickableKt$clickable$impl$1.setSummary(null);
        }
        function1.invoke(clickableKt$clickable$impl$1);
        addElement(preferenceScreen, clickableKt$clickable$impl$1);
        return clickableKt$clickable$impl$1;
    }

    public static final void closeFinally(Closeable closeable, Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                addSuppressed(th, th2);
            }
        }
    }

    public static final <T> int collectionSizeOrDefault(Iterable<? extends T> iterable, int i) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    public static final <T extends Comparable<?>> int compareValues(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        return t.compareTo(t2);
    }

    public static final <T> int compareValuesByImpl$ComparisonsKt__ComparisonsKt(T t, T t2, Function1<? super T, ? extends Comparable<?>>[] function1Arr) {
        for (Function1<? super T, ? extends Comparable<?>> function1 : function1Arr) {
            int compareValues = compareValues((Comparable) function1.invoke(t), (Comparable) function1.invoke(t2));
            if (compareValues != 0) {
                return compareValues;
            }
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x018a, code lost:
        if (r4 == false) goto L_0x018c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0111, code lost:
        if (r11 == false) goto L_0x0113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0155, code lost:
        if (r12 == false) goto L_0x0157;
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a2 A[Catch: NoSuchFieldException -> 0x01a5, TRY_LEAVE, TryCatch #2 {NoSuchFieldException -> 0x01a5, blocks: (B:94:0x016b, B:96:0x0174, B:101:0x0187, B:104:0x018d, B:107:0x0193, B:110:0x019a, B:111:0x019e, B:113:0x01a2), top: B:131:0x016b }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0151 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x014c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlinx.serialization.KSerializer<T> constructSerializerForGivenTypeArgs(kotlin.reflect.KClass<T> r16, kotlinx.serialization.KSerializer<java.lang.Object>... r17) {
        /*
            Method dump skipped, instructions count: 478
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.constructSerializerForGivenTypeArgs(kotlin.reflect.KClass, kotlinx.serialization.KSerializer[]):kotlinx.serialization.KSerializer");
    }

    public static final <T> Collection<T> convertToSetForSetOperationWith(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        if (iterable instanceof Set) {
            return (Collection) iterable;
        }
        if (!(iterable instanceof Collection)) {
            return ArraysKt___ArraysKt.toHashSet(iterable);
        }
        if (((Collection) iterable2).size() < 2) {
            return (Collection) iterable;
        }
        Collection<T> collection = (Collection) iterable;
        return collection.size() > 2 && (collection instanceof ArrayList) ? ArraysKt___ArraysKt.toHashSet(iterable) : collection;
    }

    public static final Object copyContentTo(ContentResolver contentResolver, Uri uri, Uri uri2, Continuation<? super Unit> continuation) {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = withContext(Dispatchers.IO, new ContentKt$copyContentTo$2(contentResolver, uri, uri2, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    public static Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
        return objArr2;
    }

    public static final long copyTo(InputStream inputStream, OutputStream outputStream, int i) {
        byte[] bArr = new byte[i];
        int read = inputStream.read(bArr);
        long j = 0;
        while (read >= 0) {
            outputStream.write(bArr, 0, read);
            j += read;
            read = inputStream.read(bArr);
        }
        return j;
    }

    public static final <R> Object coroutineScope(Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation.getContext(), continuation);
        return startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
    }

    public static final <R, T> Continuation<Unit> createCoroutineUnintercepted(final Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, final R r, final Continuation<? super T> continuation) {
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).create(r, continuation);
        }
        final CoroutineContext context = continuation.getContext();
        if (context == EmptyCoroutineContext.INSTANCE) {
            return new RestrictedContinuationImpl(continuation) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
                public int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public Object invokeSuspend(Object obj) {
                    int i = this.label;
                    if (i == 0) {
                        this.label = 1;
                        InputKt.throwOnFailure(obj);
                        Function2 function22 = function2;
                        Objects.requireNonNull(function22, "null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(function22, 2);
                        return function22.invoke(r, this);
                    } else if (i == 1) {
                        this.label = 2;
                        InputKt.throwOnFailure(obj);
                        return obj;
                    } else {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                }
            };
        }
        return new ContinuationImpl(continuation, context) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            public int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public Object invokeSuspend(Object obj) {
                int i = this.label;
                if (i == 0) {
                    this.label = 1;
                    InputKt.throwOnFailure(obj);
                    Function2 function22 = function2;
                    Objects.requireNonNull(function22, "null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                    TypeIntrinsics.beforeCheckcastToFunctionOfArity(function22, 2);
                    return function22.invoke(r, this);
                } else if (i == 1) {
                    this.label = 2;
                    InputKt.throwOnFailure(obj);
                    return obj;
                } else {
                    throw new IllegalStateException("This coroutine had already completed".toString());
                }
            }
        };
    }

    public static /* synthetic */ Object decodeSerializableElement$default(CompositeDecoder compositeDecoder, SerialDescriptor serialDescriptor, int i, DeserializationStrategy deserializationStrategy, Object obj, int i2, Object obj2) {
        int i3 = i2 & 8;
        return compositeDecoder.decodeSerializableElement(serialDescriptor, i, deserializationStrategy, null);
    }

    public static <T> T decodeSerializableValue(Decoder decoder, DeserializationStrategy<T> deserializationStrategy) {
        return deserializationStrategy.deserialize(decoder);
    }

    public static final Object delay(long j, Continuation<? super Unit> continuation) {
        if (j <= 0) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        if (j < Long.MAX_VALUE) {
            CoroutineContext coroutineContext = cancellableContinuationImpl.context;
            int i = ContinuationInterceptor.$r8$clinit;
            CoroutineContext.Element element = coroutineContext.get(ContinuationInterceptor.Key.$$INSTANCE);
            Delay delay = element instanceof Delay ? (Delay) element : null;
            if (delay == null) {
                delay = DefaultExecutorKt.DefaultDelay;
            }
            delay.scheduleResumeAfterDelay(j, cancellableContinuationImpl);
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : Unit.INSTANCE;
    }

    public static EditableTextPreference editableText$default(PreferenceScreen preferenceScreen, KMutableProperty0 kMutableProperty0, NullableTextAdapter nullableTextAdapter, int i, Integer num, Integer num2, Integer num3, Function1 function1, int i2) {
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        if ((i2 & 32) != 0) {
            num3 = null;
        }
        if ((i2 & 64) != 0) {
            function1 = EditableTextKt$editableText$1.INSTANCE;
        }
        EditableTextKt$editableText$impl$1 editableTextKt$editableText$impl$1 = new EditableTextKt$editableText$impl$1(preferenceScreen, i, null);
        if (num2 != null) {
            editableTextKt$editableText$impl$1.placeholder = preferenceScreen.getContext().getText(num2.intValue());
        }
        if (num3 != null) {
            editableTextKt$editableText$impl$1.empty = preferenceScreen.getContext().getText(num3.intValue());
        }
        function1.invoke(editableTextKt$editableText$impl$1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new EditableTextKt$editableText$2(editableTextKt$editableText$impl$1, nullableTextAdapter, kMutableProperty0, preferenceScreen, null), 2, null);
        return editableTextKt$editableText$impl$1;
    }

    public static EditableTextListPreference editableTextList$default(PreferenceScreen preferenceScreen, KMutableProperty0 kMutableProperty0, TextAdapter textAdapter, int i, Integer num, Integer num2, Function1 function1, int i2) {
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        if ((i2 & 32) != 0) {
            function1 = EditableTextListKt$editableTextList$1.INSTANCE;
        }
        EditableTextListKt$editableTextList$impl$1 editableTextListKt$editableTextList$impl$1 = new EditableTextListKt$editableTextList$impl$1(preferenceScreen, i, null);
        if (num2 != null) {
            editableTextListKt$editableTextList$impl$1.placeholder = preferenceScreen.getContext().getText(num2.intValue());
        }
        function1.invoke(editableTextListKt$editableTextList$impl$1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new EditableTextListKt$editableTextList$2(editableTextListKt$editableTextList$impl$1, kMutableProperty0, preferenceScreen, textAdapter, null), 2, null);
        return editableTextListKt$editableTextList$impl$1;
    }

    public static EditableTextMapPreference editableTextMap$default(PreferenceScreen preferenceScreen, KMutableProperty0 kMutableProperty0, TextAdapter textAdapter, TextAdapter textAdapter2, int i, Integer num, Integer num2, Function1 function1, int i2) {
        Integer num3 = (i2 & 32) != 0 ? null : num2;
        Function1 function12 = (i2 & 64) != 0 ? EditableTextMapKt$editableTextMap$1.INSTANCE : function1;
        EditableTextMapKt$editableTextMap$impl$1 editableTextMapKt$editableTextMap$impl$1 = new EditableTextMapKt$editableTextMap$impl$1(preferenceScreen, i, null);
        if (num3 != null) {
            editableTextMapKt$editableTextMap$impl$1.placeholder = preferenceScreen.getContext().getText(num3.intValue());
        }
        function12.invoke(editableTextMapKt$editableTextMap$impl$1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new EditableTextMapKt$editableTextMap$2(editableTextMapKt$editableTextMap$impl$1, kMutableProperty0, preferenceScreen, textAdapter, textAdapter2, null), 2, null);
        return editableTextMapKt$editableTextMap$impl$1;
    }

    public static final String elapsedIntervalString(long j, Context context) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long days = timeUnit.toDays(j);
        long hours = timeUnit.toHours(j);
        long minutes = timeUnit.toMinutes(j);
        return days > 0 ? context.getString(R.string.format_days_ago, Long.valueOf(days)) : hours > 0 ? context.getString(R.string.format_hours_ago, Long.valueOf(hours)) : minutes > 0 ? context.getString(R.string.format_minutes_ago, Long.valueOf(minutes)) : context.getString(R.string.recently);
    }

    public static <T> void encodeNullableSerializableValue(Encoder encoder, SerializationStrategy<? super T> serializationStrategy, T t) {
        if (serializationStrategy.getDescriptor().isNullable()) {
            encoder.encodeSerializableValue(serializationStrategy, t);
        } else if (t == null) {
            encoder.encodeNull();
        } else {
            encoder.encodeNotNullMark();
            encoder.encodeSerializableValue(serializationStrategy, t);
        }
    }

    public static final void ensureActive(CoroutineContext coroutineContext) {
        int i = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null && !job.isActive()) {
            throw job.getCancellationException();
        }
    }

    public static final boolean equals(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (!z) {
            return false;
        }
        char upperCase = Character.toUpperCase(c);
        char upperCase2 = Character.toUpperCase(c2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }

    public static final <T> Sequence<T> filter(Sequence<? extends T> sequence, Function1<? super T, Boolean> function1) {
        return new FilteringSequence(sequence, true, function1);
    }

    public static final <T> DeserializationStrategy<? extends T> findPolymorphicSerializer(AbstractPolymorphicSerializer<T> abstractPolymorphicSerializer, CompositeDecoder compositeDecoder, String str) {
        DeserializationStrategy<? extends T> polymorphic = compositeDecoder.getSerializersModule().getPolymorphic((KClass) abstractPolymorphicSerializer.getBaseClass(), str);
        if (polymorphic != null) {
            return polymorphic;
        }
        throwSubtypeNotRegistered(str, abstractPolymorphicSerializer.getBaseClass());
        throw null;
    }

    public static final String format(Date date, Context context, boolean z, boolean z2) {
        Locale preferredLocale = PathParser.getPreferredLocale(context.getResources().getConfiguration());
        if (z && z2) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", preferredLocale).format(date);
        }
        if (z) {
            return new SimpleDateFormat("yyyy-MM-dd", preferredLocale).format(date);
        }
        return z2 ? new SimpleDateFormat("HH:mm:ss.SSS", preferredLocale).format(date) : HttpUrl.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ String format$default(Date date, Context context, boolean z, boolean z2, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return format(date, context, z, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0077 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x005d -> B:29:0x0078). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x006b -> B:26:0x006e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object generateProfileUUID(kotlin.coroutines.Continuation<? super java.util.UUID> r6) {
        /*
            boolean r0 = r6 instanceof com.tidalab.v2board.clash.service.util.DatabaseKt$generateProfileUUID$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.tidalab.v2board.clash.service.util.DatabaseKt$generateProfileUUID$1 r0 = (com.tidalab.v2board.clash.service.util.DatabaseKt$generateProfileUUID$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.util.DatabaseKt$generateProfileUUID$1 r0 = new com.tidalab.v2board.clash.service.util.DatabaseKt$generateProfileUUID$1
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L_0x003e
            if (r2 == r3) goto L_0x0036
            if (r2 != r4) goto L_0x002e
            java.lang.Object r2 = r0.L$0
            java.util.UUID r2 = (java.util.UUID) r2
            throwOnFailure(r6)
            goto L_0x006e
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            java.lang.Object r2 = r0.L$0
            java.util.UUID r2 = (java.util.UUID) r2
            throwOnFailure(r6)
            goto L_0x0057
        L_0x003e:
            throwOnFailure(r6)
            java.util.UUID r6 = java.util.UUID.randomUUID()
        L_0x0045:
            com.tidalab.v2board.clash.service.data.ImportedDao r2 = ImportedDao()
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r2 = r2.exists(r6, r0)
            if (r2 != r1) goto L_0x0054
            return r1
        L_0x0054:
            r5 = r2
            r2 = r6
            r6 = r5
        L_0x0057:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x0078
            com.tidalab.v2board.clash.service.data.PendingDao r6 = PendingDao()
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r6 = r6.exists(r2, r0)
            if (r6 != r1) goto L_0x006e
            return r1
        L_0x006e:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            return r2
        L_0x0078:
            java.util.UUID r6 = java.util.UUID.randomUUID()
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.generateProfileUUID(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Boolean getBooleanOrNull(JsonPrimitive jsonPrimitive) {
        String content = jsonPrimitive.getContent();
        String[] strArr = StringOpsKt.ESCAPE_STRINGS;
        if (StringsKt__IndentKt.equals(content, "true", true)) {
            return Boolean.TRUE;
        }
        if (StringsKt__IndentKt.equals(content, "false", true)) {
            return Boolean.FALSE;
        }
        return null;
    }

    public static final String getClassSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final Long getDirectoryLastModified(File file) {
        Long l;
        Iterator<File> it = new FileTreeWalk(file, 1).iterator();
        if (!it.hasNext()) {
            l = null;
        } else {
            Long valueOf = Long.valueOf(it.next().lastModified());
            while (it.hasNext()) {
                Long valueOf2 = Long.valueOf(it.next().lastModified());
                if (valueOf.compareTo(valueOf2) < 0) {
                    valueOf = valueOf2;
                }
            }
            l = valueOf;
        }
        return l;
    }

    public static final String getHexAddress(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final Spanned getHtml(Context context, int i) {
        String string = context.getString(i);
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(string, 63);
        }
        return Html.fromHtml(string);
    }

    public static final File getImportedDir(Context context) {
        return FilesKt__UtilsKt.resolve(context.getFilesDir(), "imported");
    }

    public static final int getInt(JsonPrimitive jsonPrimitive) {
        return Integer.parseInt(jsonPrimitive.getContent());
    }

    public static final <T> Class<T> getJavaClass(KClass<T> kClass) {
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) kClass).getJClass();
        Objects.requireNonNull(cls, "null cannot be cast to non-null type java.lang.Class<T>");
        return cls;
    }

    public static final <T> Class<T> getJavaObjectType(KClass<T> kClass) {
        Class<T> cls = (Class<T>) ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (!cls.isPrimitive()) {
            return cls;
        }
        String name = cls.getName();
        switch (name.hashCode()) {
            case -1325958191:
                return name.equals("double") ? Double.class : cls;
            case 104431:
                return name.equals("int") ? Integer.class : cls;
            case 3039496:
                return name.equals("byte") ? Byte.class : cls;
            case 3052374:
                return name.equals("char") ? Character.class : cls;
            case 3327612:
                return name.equals("long") ? Long.class : cls;
            case 3625364:
                return name.equals("void") ? Void.class : cls;
            case 64711720:
                return name.equals("boolean") ? Boolean.class : cls;
            case 97526364:
                return name.equals("float") ? Float.class : cls;
            case 109413500:
                return name.equals("short") ? Short.class : cls;
            default:
                return cls;
        }
    }

    public static final Job getJob(CoroutineContext coroutineContext) {
        int i = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            return job;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Current context doesn't contain Job in it: ", coroutineContext).toString());
    }

    public static final JsonPrimitive getJsonPrimitive(JsonElement jsonElement) {
        JsonPrimitive jsonPrimitive = jsonElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Element ");
        outline13.append(Reflection.getOrCreateKotlinClass(jsonElement.getClass()));
        outline13.append(" is not a ");
        outline13.append("JsonPrimitive");
        throw new IllegalArgumentException(outline13.toString());
    }

    public static final File getLogsDir(Context context) {
        return FilesKt__UtilsKt.resolve(context.getCacheDir(), "logs");
    }

    public static final <T> CancellableContinuationImpl<T> getOrCreateCancellableContinuation(Continuation<? super T> continuation) {
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl<>(continuation, 1);
        }
        CancellableContinuationImpl<T> claimReusableCancellableContinuation = ((DispatchedContinuation) continuation).claimReusableCancellableContinuation();
        claimReusableCancellableContinuation = null;
        if (claimReusableCancellableContinuation == null || !claimReusableCancellableContinuation.resetStateReusable()) {
        }
        return claimReusableCancellableContinuation == null ? new CancellableContinuationImpl<>(continuation, 2) : claimReusableCancellableContinuation;
    }

    public static final String getPackageName(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return null;
        }
        if (!Intrinsics.areEqual(data.getScheme(), "package")) {
            data = null;
        }
        if (data == null) {
            return null;
        }
        return data.getSchemeSpecificPart();
    }

    public static final File getPendingDir(Context context) {
        return FilesKt__UtilsKt.resolve(context.getFilesDir(), "pending");
    }

    public static final int getPixels(Context context, int i) {
        return context.getResources().getDimensionPixelSize(i);
    }

    public static final File getProcessingDir(Context context) {
        return FilesKt__UtilsKt.resolve(context.getFilesDir(), "processing");
    }

    public static final ViewGroup getRoot(Context context) {
        if (context instanceof Activity) {
            return (ViewGroup) ((Activity) context).findViewById(16908290);
        }
        return null;
    }

    public static final int getRootLength$FilesKt__FilePathComponentsKt(String str) {
        int indexOf$default;
        int indexOf$default2 = StringsKt__IndentKt.indexOf$default((CharSequence) str, File.separatorChar, 0, false, 4);
        if (indexOf$default2 == 0) {
            if (str.length() > 1) {
                char charAt = str.charAt(1);
                char c = File.separatorChar;
                if (charAt == c && (indexOf$default = StringsKt__IndentKt.indexOf$default((CharSequence) str, c, 2, false, 4)) >= 0) {
                    int indexOf$default3 = StringsKt__IndentKt.indexOf$default((CharSequence) str, File.separatorChar, indexOf$default + 1, false, 4);
                    return indexOf$default3 >= 0 ? indexOf$default3 + 1 : str.length();
                }
            }
            return 1;
        } else if (indexOf$default2 > 0 && str.charAt(indexOf$default2 - 1) == ':') {
            return indexOf$default2 + 1;
        } else {
            if (indexOf$default2 != -1 || !StringsKt__IndentKt.endsWith$default(str, ':', false, 2)) {
                return 0;
            }
            return str.length();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Object;)TS; */
    /* renamed from: getSegment-impl  reason: not valid java name */
    public static final Segment m8getSegmentimpl(Object obj) {
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            return (Segment) obj;
        }
        throw new IllegalStateException("Does not contain segment".toString());
    }

    public static final void handleCoroutineException(CoroutineContext coroutineContext, Throwable th) {
        try {
            int i = CoroutineExceptionHandler.$r8$clinit;
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.get(CoroutineExceptionHandler.Key.$$INSTANCE);
            if (coroutineExceptionHandler == null) {
                CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(coroutineContext, th);
            } else {
                coroutineExceptionHandler.handleException(coroutineContext, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                addSuppressed(runtimeException, th);
                th = runtimeException;
            }
            CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(coroutineContext, th);
        }
    }

    public static final int hashCodeImpl(SerialDescriptor serialDescriptor, SerialDescriptor[] serialDescriptorArr) {
        int hashCode = (serialDescriptor.getSerialName().hashCode() * 31) + Arrays.hashCode(serialDescriptorArr);
        int elementsCount = serialDescriptor.getElementsCount();
        int i = 1;
        while (true) {
            int i2 = 0;
            if (!(elementsCount > 0)) {
                break;
            }
            elementsCount--;
            int i3 = i * 31;
            String serialName = serialDescriptor.getElementDescriptor(serialDescriptor.getElementsCount() - elementsCount).getSerialName();
            if (serialName != null) {
                i2 = serialName.hashCode();
            }
            i = i3 + i2;
        }
        int elementsCount2 = serialDescriptor.getElementsCount();
        int i4 = 1;
        while (true) {
            if (!(elementsCount2 > 0)) {
                return (((hashCode * 31) + i) * 31) + i4;
            }
            elementsCount2--;
            int i5 = i4 * 31;
            SerialKind kind = serialDescriptor.getElementDescriptor(serialDescriptor.getElementsCount() - elementsCount2).getKind();
            i4 = i5 + (kind != null ? kind.hashCode() : 0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
        if (r2 != null) goto L_0x0026;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlin.coroutines.Continuation<T> intercepted(kotlin.coroutines.Continuation<? super T> r2) {
        /*
            boolean r0 = r2 instanceof kotlin.coroutines.jvm.internal.ContinuationImpl
            if (r0 != 0) goto L_0x0006
            r0 = 0
            goto L_0x0007
        L_0x0006:
            r0 = r2
        L_0x0007:
            kotlin.coroutines.jvm.internal.ContinuationImpl r0 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r0
            if (r0 == 0) goto L_0x0028
            kotlin.coroutines.Continuation<java.lang.Object> r2 = r0.intercepted
            if (r2 == 0) goto L_0x0010
            goto L_0x0028
        L_0x0010:
            kotlin.coroutines.CoroutineContext r2 = r0._context
            int r1 = kotlin.coroutines.ContinuationInterceptor.$r8$clinit
            kotlin.coroutines.ContinuationInterceptor$Key r1 = kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r1)
            kotlin.coroutines.ContinuationInterceptor r2 = (kotlin.coroutines.ContinuationInterceptor) r2
            if (r2 == 0) goto L_0x0025
            kotlin.coroutines.Continuation r2 = r2.interceptContinuation(r0)
            if (r2 == 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r2 = r0
        L_0x0026:
            r0.intercepted = r2
        L_0x0028:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.intercepted(kotlin.coroutines.Continuation):kotlin.coroutines.Continuation");
    }

    public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job job, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return job.invokeOnCompletion(z, z2, function1);
    }

    public static final boolean isActive(CoroutineScope coroutineScope) {
        CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
        int i = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job == null) {
            return true;
        }
        return job.isActive();
    }

    public static final boolean isCancellableMode(int i) {
        return i == 1 || i == 2;
    }

    public static final boolean isWhitespace(char c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    public static Job launch$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        AbstractCoroutine abstractCoroutine;
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineStart coroutineStart2 = (i & 2) != 0 ? CoroutineStart.DEFAULT : null;
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineContext);
        Objects.requireNonNull(coroutineStart2);
        if (coroutineStart2 == CoroutineStart.LAZY) {
            abstractCoroutine = new LazyStandaloneCoroutine(newCoroutineContext, function2);
        } else {
            abstractCoroutine = new StandaloneCoroutine(newCoroutineContext, true);
        }
        abstractCoroutine.start(coroutineStart2, abstractCoroutine, function2);
        return abstractCoroutine;
    }

    public static final <T> Lazy<T> lazy(Function0<? extends T> function0) {
        return new SynchronizedLazyImpl(function0, null, 2);
    }

    public static final int mapCapacity(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final String minify(String str, int i) {
        if (str.length() < 200) {
            return str;
        }
        String str2 = ".....";
        if (i == -1) {
            int length = str.length() - 60;
            return length <= 0 ? str : Intrinsics.stringPlus(str2, str.substring(length));
        }
        int i2 = i - 30;
        int i3 = i + 30;
        String str3 = i2 <= 0 ? HttpUrl.FRAGMENT_ENCODE_SET : str2;
        if (i3 >= str.length()) {
            str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(str3);
        if (i2 < 0) {
            i2 = 0;
        }
        int length2 = str.length();
        if (i3 > length2) {
            i3 = length2;
        }
        outline13.append(str.substring(i2, i3));
        outline13.append(str2);
        return outline13.toString();
    }

    public static final int mod(int i, int i2) {
        int i3 = i % i2;
        return i3 >= 0 ? i3 : i3 + i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <H extends androidx.recyclerview.widget.RecyclerView.ViewHolder, T> java.lang.Object patchDataSet(androidx.recyclerview.widget.RecyclerView.Adapter<H> r12, kotlin.reflect.KMutableProperty0<java.util.List<T>> r13, java.util.List<? extends T> r14, boolean r15, kotlin.jvm.functions.Function1<? super T, ? extends java.lang.Object> r16, kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            r0 = r17
            boolean r1 = r0 instanceof com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$1
            if (r1 == 0) goto L_0x0015
            r1 = r0
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$1 r1 = (com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$1 r1 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$1
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x0045
            if (r3 == r5) goto L_0x0035
            if (r3 != r4) goto L_0x002d
            throwOnFailure(r0)
            goto L_0x008e
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            java.lang.Object r3 = r1.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r5 = r1.L$1
            kotlin.reflect.KMutableProperty0 r5 = (kotlin.reflect.KMutableProperty0) r5
            java.lang.Object r6 = r1.L$0
            androidx.recyclerview.widget.RecyclerView$Adapter r6 = (androidx.recyclerview.widget.RecyclerView.Adapter) r6
            throwOnFailure(r0)
            goto L_0x006a
        L_0x0045:
            throwOnFailure(r0)
            kotlinx.coroutines.Dispatchers r0 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.Default
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$result$1 r3 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$result$1
            r11 = 0
            r6 = r3
            r7 = r13
            r8 = r14
            r9 = r15
            r10 = r16
            r6.<init>(r7, r8, r9, r10, r11)
            r6 = r12
            r1.L$0 = r6
            r1.L$1 = r7
            r1.L$2 = r8
            r1.label = r5
            java.lang.Object r0 = withContext(r0, r3, r1)
            if (r0 != r2) goto L_0x0068
            return r2
        L_0x0068:
            r5 = r7
            r3 = r8
        L_0x006a:
            androidx.recyclerview.widget.DiffUtil$DiffResult r0 = (androidx.recyclerview.widget.DiffUtil.DiffResult) r0
            kotlinx.coroutines.Dispatchers r7 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r7 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$3 r8 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$patchDataSet$3
            r9 = 0
            r12 = r8
            r13 = r5
            r14 = r3
            r15 = r0
            r16 = r6
            r17 = r9
            r12.<init>(r13, r14, r15, r16, r17)
            r0 = 0
            r1.L$0 = r0
            r1.L$1 = r0
            r1.L$2 = r0
            r1.label = r4
            java.lang.Object r0 = withContext(r7, r8, r1)
            if (r0 != r2) goto L_0x008e
            return r2
        L_0x008e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.patchDataSet(androidx.recyclerview.widget.RecyclerView$Adapter, kotlin.reflect.KMutableProperty0, java.util.List, boolean, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object patchDataSet$default(RecyclerView.Adapter adapter, KMutableProperty0 kMutableProperty0, List list, boolean z, Function1 function1, Continuation continuation, int i) {
        boolean z2 = (i & 4) != 0 ? false : z;
        if ((i & 8) != 0) {
            function1 = RecyclerViewKt$patchDataSet$2.INSTANCE;
        }
        return patchDataSet(adapter, kMutableProperty0, list, z2, function1, continuation);
    }

    /* renamed from: plus-FjFbRPM  reason: not valid java name */
    public static final Object m9plusFjFbRPM(Object obj, E e) {
        if (obj == null) {
            return e;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(e);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(e);
        return arrayList;
    }

    public static final byte[] readGeoipDatabaseFrom(Context context, String str) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(str, 128).metaData;
            Metadata metadata = Metadata.INSTANCE;
            String string = bundle.getString(Metadata.GEOIP_FILE_NAME);
            if (string == null) {
                return null;
            }
            InputStream open = context.createPackageContext(str, 0).getResources().getAssets().open(string);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, open.available()));
            copyTo(open, byteArrayOutputStream, 8192);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            closeFinally(open, null);
            return byteArray;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("ClashForAndroid", "Sideload geoip: " + str + " not found", e);
            return null;
        }
    }

    public static final <T> Object recoverResult(Object obj, Continuation<? super T> continuation) {
        return obj instanceof CompletedExceptionally ? new Result.Failure(((CompletedExceptionally) obj).cause) : obj;
    }

    public static final Object requestEditableListOverlay(Context context, RecyclerView.Adapter<?> adapter, CharSequence charSequence, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super EditableListOverlayResult> continuation) {
        return coroutineScope(new OverlayKt$requestEditableListOverlay$2(context, adapter, charSequence, function1, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object requestModelTextInput(android.content.Context r9, java.lang.String r10, java.lang.CharSequence r11, java.lang.CharSequence r12, java.lang.CharSequence r13, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.Boolean> r14, kotlin.coroutines.Continuation<? super java.lang.String> r15) {
        /*
            boolean r0 = r15 instanceof com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$1
            if (r0 == 0) goto L_0x0013
            r0 = r15
            com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$1 r0 = (com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$1 r0 = new com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$1
            r0.<init>(r15)
        L_0x0018:
            r8 = r0
            java.lang.Object r15 = r8.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0030
            if (r1 != r2) goto L_0x0028
            throwOnFailure(r15)
            goto L_0x0043
        L_0x0028:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0030:
            throwOnFailure(r15)
            r4 = 0
            r8.label = r2
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            java.lang.Object r15 = requestModelTextInput(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r15 != r0) goto L_0x0043
            return r0
        L_0x0043:
            java.lang.String r15 = (java.lang.String) r15
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.requestModelTextInput(android.content.Context, java.lang.String, java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final boolean resolveThemedBoolean(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data != 0;
    }

    public static final int resolveThemedColor(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    public static final int resolveThemedResourceId(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public static final <T> void resume(DispatchedTask<? super T> dispatchedTask, Continuation<? super T> continuation, boolean z) {
        Object obj;
        Object takeState$kotlinx_coroutines_core = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exceptionalResult$kotlinx_coroutines_core = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            obj = new Result.Failure(exceptionalResult$kotlinx_coroutines_core);
        } else {
            obj = dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        }
        if (z) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Continuation<T> continuation2 = dispatchedContinuation.continuation;
            Object obj2 = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation2.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
            UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation2, context, updateThreadContext) : null;
            try {
                dispatchedContinuation.continuation.resumeWith(obj);
                Unit unit = Unit.INSTANCE;
            } finally {
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
            }
        } else {
            continuation.resumeWith(obj);
        }
    }

    public static final <T> T runBlocking(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        CoroutineContext coroutineContext2;
        EventLoop eventLoop;
        Thread currentThread = Thread.currentThread();
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key.$$INSTANCE);
        CompletedExceptionally completedExceptionally = null;
        if (continuationInterceptor == null) {
            ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
            eventLoop = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
            coroutineContext2 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext.plus(eventLoop));
        } else {
            if (continuationInterceptor instanceof EventLoop) {
                EventLoop eventLoop2 = (EventLoop) continuationInterceptor;
            }
            ThreadLocalEventLoop threadLocalEventLoop2 = ThreadLocalEventLoop.INSTANCE;
            eventLoop = ThreadLocalEventLoop.ref.get();
            coroutineContext2 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext);
        }
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(coroutineContext2, currentThread, eventLoop);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        EventLoop eventLoop3 = blockingCoroutine.eventLoop;
        if (eventLoop3 != null) {
            int i = EventLoop.$r8$clinit;
            eventLoop3.incrementUseCount(false);
        }
        while (!Thread.interrupted()) {
            EventLoop eventLoop4 = blockingCoroutine.eventLoop;
            long processNextEvent = eventLoop4 == null ? Long.MAX_VALUE : eventLoop4.processNextEvent();
            if (!(blockingCoroutine.getState$kotlinx_coroutines_core() instanceof Incomplete)) {
                EventLoop eventLoop5 = blockingCoroutine.eventLoop;
                if (eventLoop5 != null) {
                    int i2 = EventLoop.$r8$clinit;
                    eventLoop5.decrementUseCount(false);
                }
                T t = (T) JobSupportKt.unboxState(blockingCoroutine.getState$kotlinx_coroutines_core());
                if (t instanceof CompletedExceptionally) {
                    completedExceptionally = (CompletedExceptionally) t;
                }
                if (completedExceptionally == null) {
                    return t;
                }
                throw completedExceptionally.cause;
            }
            LockSupport.parkNanos(blockingCoroutine, processNextEvent);
        }
        InterruptedException interruptedException = new InterruptedException();
        blockingCoroutine.cancelImpl$kotlinx_coroutines_core(interruptedException);
        throw interruptedException;
    }

    public static /* synthetic */ Object runBlocking$default(CoroutineContext coroutineContext, Function2 function2, int i, Object obj) throws InterruptedException {
        return runBlocking((i & 1) != 0 ? EmptyCoroutineContext.INSTANCE : null, function2);
    }

    public static final <T> SelectableListPreference<T> selectableList(PreferenceScreen preferenceScreen, KMutableProperty0<T> kMutableProperty0, T[] tArr, Integer[] numArr, int i, Integer num, Function1<? super SelectableListPreference<T>, Unit> function1) {
        SelectableListKt$selectableList$impl$1 selectableListKt$selectableList$impl$1 = new SelectableListKt$selectableList$impl$1(preferenceScreen, i, num, numArr);
        function1.invoke(selectableListKt$selectableList$impl$1);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        launch$default(preferenceScreen, MainDispatcherLoader.dispatcher, null, new SelectableListKt$selectableList$2(selectableListKt$selectableList$impl$1, tArr, kMutableProperty0, preferenceScreen, numArr, null), 2, null);
        return selectableListKt$selectableList$impl$1;
    }

    public static /* synthetic */ SelectableListPreference selectableList$default(PreferenceScreen preferenceScreen, KMutableProperty0 kMutableProperty0, Object[] objArr, Integer[] numArr, int i, Integer num, Function1 function1, int i2) {
        int i3 = i2 & 16;
        if ((i2 & 32) != 0) {
            function1 = SelectableListKt$selectableList$1.INSTANCE;
        }
        return selectableList(preferenceScreen, kMutableProperty0, objArr, numArr, i, null, function1);
    }

    public static final void sendBroadcastSelf(Context context, Intent intent) {
        Intent intent2 = intent.setPackage(context.getPackageName());
        Permissions permissions = Permissions.INSTANCE;
        context.sendBroadcast(intent2, Permissions.RECEIVE_SELF_BROADCASTS);
    }

    public static final void sendProfileChanged(Context context, UUID uuid) {
        Intents intents = Intents.INSTANCE;
        sendBroadcastSelf(context, new Intent(Intents.ACTION_PROFILE_CHANGED).putExtra("uuid", uuid.toString()));
    }

    public static final KSerializer<Object> serializer(KType kType) {
        SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;
        return serializer(SerializersModuleKt.EmptySerializersModule, kType);
    }

    public static final <T> KSerializer<T> serializerOrNull(KClass<T> kClass) {
        KSerializer<T> constructSerializerForGivenTypeArgs = constructSerializerForGivenTypeArgs(kClass, new KSerializer[0]);
        return constructSerializerForGivenTypeArgs == null ? (KSerializer<T>) PrimitivesKt.BUILTIN_SERIALIZERS.get(kClass) : constructSerializerForGivenTypeArgs;
    }

    public static void setOnInsertsChangedListener$default(View view, final boolean z, final Function1 function1, int i) {
        if ((i & 1) != 0) {
            z = true;
        }
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$InsertsKt$oBasDNLNpPOeu8PyT6eZq85DLhA
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                Insets insets;
                Function1 function12 = Function1.this;
                boolean z2 = z;
                WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, null);
                androidx.core.graphics.Insets insets2 = windowInsetsCompat.getInsets(7);
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (view2.getLayoutDirection() == 0) {
                    insets = new Insets(insets2.left, insets2.top, insets2.right, insets2.bottom);
                } else {
                    insets = new Insets(insets2.right, insets2.top, insets2.left, insets2.bottom);
                }
                if (z2) {
                    Context context = view2.getContext();
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    int pixels = InputKt.getPixels(context, R.dimen.surface_landscape_min_width);
                    int i2 = displayMetrics.widthPixels;
                    int i3 = displayMetrics.heightPixels;
                    if (i2 > i3 && i2 > pixels) {
                        if (i3 >= pixels) {
                            pixels = i3;
                        }
                        if (i2 <= pixels) {
                            pixels = i2;
                        }
                        int i4 = i2 - pixels;
                        int i5 = insets.start;
                        int i6 = insets.end;
                        int i7 = i5 + i6;
                        if (i4 < i7) {
                            i4 = i7;
                        }
                        int i8 = i4 / 2;
                        if (i8 >= i5) {
                            i5 = i8;
                        }
                        if (i8 >= i6) {
                            i6 = i8;
                        }
                        insets = new Insets(i5, insets.top, i6, insets.bottom);
                    }
                }
                function12.invoke(insets);
                return windowInsetsCompat.toWindowInsets();
            }
        });
        view.requestApplyInsets();
    }

    public static final Object showExceptionToast(Design<?> design, Exception exc, Continuation<? super Unit> continuation) {
        String message = exc.getMessage();
        if (message == null) {
            message = "Unknown";
        }
        Object showToast = design.showToast(message, ToastDuration.Long, new ToastKt$showExceptionToast$2(message), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (showToast != coroutineSingletons) {
            showToast = Unit.INSTANCE;
        }
        return showToast == coroutineSingletons ? showToast : Unit.INSTANCE;
    }

    public static final Intent startClashService(Context context) {
        if (new UiStore(context).getEnableVpn()) {
            Intent prepare = VpnService.prepare(context);
            if (prepare != null) {
                return prepare;
            }
            PathParser.startForegroundServiceCompat(context, PathParser.getIntent(Reflection.getOrCreateKotlinClass(TunService.class)));
            return null;
        }
        PathParser.startForegroundServiceCompat(context, PathParser.getIntent(Reflection.getOrCreateKotlinClass(ClashService.class)));
        return null;
    }

    public static final <R, T> void startCoroutineCancellable(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation, Function1<? super Throwable, Unit> function1) {
        try {
            DispatchedContinuationKt.resumeCancellableWith(intercepted(createCoroutineUnintercepted(function2, r, continuation)), Unit.INSTANCE, function1);
        } catch (Throwable th) {
            continuation.resumeWith(new Result.Failure(th));
        }
    }

    public static /* synthetic */ void startCoroutineCancellable$default(Function2 function2, Object obj, Continuation continuation, Function1 function1, int i) {
        int i2 = i & 4;
        startCoroutineCancellable(function2, obj, continuation, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <R, T> void startCoroutineUnintercepted(Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2, R r, Continuation<? super T> continuation) {
        try {
            if (function2 != null) {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
                Object invoke = function2.invoke(r, continuation);
                if (invoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    continuation.resumeWith(invoke);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
        } catch (Throwable th) {
            continuation.resumeWith(new Result.Failure(th));
        }
    }

    public static final <T, R> Object startUndispatchedOrReturn(ScopeCoroutine<? super T> scopeCoroutine, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        Object obj;
        Object makeCompletingOnce$kotlinx_coroutines_core;
        try {
        } catch (Throwable th) {
            obj = new CompletedExceptionally(th, false, 2);
        }
        if (function2 != null) {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
            obj = function2.invoke(r, scopeCoroutine);
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (obj == coroutineSingletons || (makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(obj)) == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return coroutineSingletons;
            }
            if (!(makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
            }
            throw ((CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core).cause;
        }
        throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
    }

    public static final void stopClashService(Context context) {
        Intents intents = Intents.INSTANCE;
        sendBroadcastSelf(context, new Intent(Intents.ACTION_CLASH_REQUEST_STOP));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <H extends androidx.recyclerview.widget.RecyclerView.ViewHolder, T> java.lang.Object swapDataSet(androidx.recyclerview.widget.RecyclerView.Adapter<H> r6, kotlin.reflect.KMutableProperty0<java.util.List<T>> r7, java.util.List<? extends T> r8, boolean r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$1 r0 = (com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$1 r0 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$1
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0045
            if (r2 == r4) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            throwOnFailure(r10)
            goto L_0x0083
        L_0x002b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0033:
            java.lang.Object r6 = r0.L$2
            r8 = r6
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r6 = r0.L$1
            r7 = r6
            kotlin.reflect.KMutableProperty0 r7 = (kotlin.reflect.KMutableProperty0) r7
            java.lang.Object r6 = r0.L$0
            androidx.recyclerview.widget.RecyclerView$Adapter r6 = (androidx.recyclerview.widget.RecyclerView.Adapter) r6
            throwOnFailure(r10)
            goto L_0x0060
        L_0x0045:
            throwOnFailure(r10)
            kotlinx.coroutines.Dispatchers r10 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.Default
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$ignore$1 r2 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$ignore$1
            r2.<init>(r9, r7, r8, r5)
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r4
            java.lang.Object r10 = withContext(r10, r2, r0)
            if (r10 != r1) goto L_0x0060
            return r1
        L_0x0060:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r9 = r10.booleanValue()
            if (r9 == 0) goto L_0x006b
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006b:
            kotlinx.coroutines.Dispatchers r9 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r9 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher
            com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$2 r10 = new com.tidalab.v2board.clash.design.util.RecyclerViewKt$swapDataSet$2
            r10.<init>(r7, r8, r6, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r6 = withContext(r9, r10, r0)
            if (r6 != r1) goto L_0x0083
            return r1
        L_0x0083:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.swapDataSet(androidx.recyclerview.widget.RecyclerView$Adapter, kotlin.reflect.KMutableProperty0, java.util.List, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final String systemProp(String str) {
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static int systemProp$default(String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return (int) systemProp(str, i, i2, i3);
    }

    public static final Void throwInvalidFloatingPointDecoded(JsonLexer jsonLexer, Number number) {
        throw JsonDecodingException(jsonLexer.currentPosition, "Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'", jsonLexer.source);
    }

    public static final void throwMissingFieldException(int i, int i2, SerialDescriptor serialDescriptor) {
        ArrayList arrayList = new ArrayList();
        int i3 = (~i) & i2;
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if ((i3 & 1) != 0) {
                arrayList.add(serialDescriptor.getElementName(i4));
            }
            i3 >>>= 1;
            if (i5 < 32) {
                i4 = i5;
            } else {
                throw new MissingFieldException(arrayList, serialDescriptor.getSerialName());
            }
        }
    }

    public static final void throwOnFailure(Object obj) {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }

    public static final Void throwSubtypeNotRegistered(String str, KClass<?> kClass) {
        String str2;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("in the scope of '");
        outline13.append((Object) kClass.getSimpleName());
        outline13.append('\'');
        String sb = outline13.toString();
        if (str == null) {
            str2 = Intrinsics.stringPlus("Class discriminator was missing and no default polymorphic serializers were registered ", sb);
        } else {
            str2 = "Class '" + ((Object) str) + "' is not registered for polymorphic serialization " + sb + ".\nMark the base class as 'sealed' or register the serializer explicitly.";
        }
        throw new SerializationException(str2);
    }

    public static final AppInfo toAppInfo(PackageInfo packageInfo, PackageManager packageManager) {
        return new AppInfo(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(packageManager).toString(), packageInfo.applicationInfo.loadIcon(packageManager), packageInfo.firstInstallTime, packageInfo.lastUpdateTime);
    }

    public static final FilePathComponents toComponents(File file) {
        List list;
        String path = file.getPath();
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path);
        String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        String substring2 = path.substring(rootLength$FilesKt__FilePathComponentsKt);
        if (substring2.length() == 0) {
            list = EmptyList.INSTANCE;
        } else {
            List<String> split$StringsKt__StringsKt = StringsKt__IndentKt.split$StringsKt__StringsKt(substring2, String.valueOf(new char[]{File.separatorChar}[0]), false, 0);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault(split$StringsKt__StringsKt, 10));
            for (String str : split$StringsKt__StringsKt) {
                arrayList.add(new File(str));
            }
            list = arrayList;
        }
        return new FilePathComponents(new File(substring), list);
    }

    public static final String toDebugString(Continuation<?> continuation) {
        Object obj;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            obj = continuation + '@' + getHexAddress(continuation);
        } catch (Throwable th) {
            obj = new Result.Failure(th);
        }
        if (Result.m11exceptionOrNullimpl(obj) != null) {
            obj = ((Object) continuation.getClass().getName()) + '@' + getHexAddress(continuation);
        }
        return (String) obj;
    }

    public static final <T> List<T> toList(Sequence<? extends T> sequence) {
        return ArraysKt___ArraysKt.optimizeReadOnlyList(toMutableList(sequence));
    }

    public static final <T> List<T> toMutableList(Sequence<? extends T> sequence) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends T> it = sequence.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static final <T> Object toState(Object obj, Function1<? super Throwable, Unit> function1) {
        Throwable th = Result.m11exceptionOrNullimpl(obj);
        if (th == null) {
            return function1 != null ? new CompletedWithCancellation(obj, function1) : obj;
        }
        return new CompletedExceptionally(th, false, 2);
    }

    public static /* synthetic */ Object toState$default(Object obj, Function1 function1, int i) {
        int i2 = i & 1;
        return toState(obj, null);
    }

    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ Integer.MIN_VALUE, i2 ^ Integer.MIN_VALUE);
    }

    public static final int ulongCompare(long j, long j2) {
        return ((j ^ Long.MIN_VALUE) > (j2 ^ Long.MIN_VALUE) ? 1 : ((j ^ Long.MIN_VALUE) == (j2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083 A[PHI: r10 
      PHI: (r10v3 java.lang.Object) = (r10v8 java.lang.Object), (r10v1 java.lang.Object) binds: [B:25:0x0080, B:13:0x0033] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0084 -> B:29:0x0087). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object withClash(kotlin.coroutines.CoroutineContext r8, kotlin.jvm.functions.Function2<? super com.tidalab.v2board.clash.service.remote.IClashManager, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super T> r10) {
        /*
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.util.RemoteKt$withClash$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.util.RemoteKt$withClash$1 r0 = (com.tidalab.v2board.clash.util.RemoteKt$withClash$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.util.RemoteKt$withClash$1 r0 = new com.tidalab.v2board.clash.util.RemoteKt$withClash$1
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            r5 = 0
            if (r2 == 0) goto L_0x004e
            if (r2 == r3) goto L_0x0042
            if (r2 != r4) goto L_0x003a
            java.lang.Object r8 = r0.L$2
            com.tidalab.v2board.clash.service.remote.IRemoteService r8 = (com.tidalab.v2board.clash.service.remote.IRemoteService) r8
            java.lang.Object r9 = r0.L$1
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            java.lang.Object r2 = r0.L$0
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            throwOnFailure(r10)     // Catch: DeadObjectException -> 0x0037
            goto L_0x0083
        L_0x0037:
            r10 = r9
            r9 = r2
            goto L_0x0087
        L_0x003a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0042:
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r0.L$0
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            throwOnFailure(r10)
            goto L_0x0069
        L_0x004e:
            throwOnFailure(r10)
        L_0x0051:
            com.tidalab.v2board.clash.remote.Remote r10 = com.tidalab.v2board.clash.remote.Remote.INSTANCE
            com.tidalab.v2board.clash.remote.Service r10 = com.tidalab.v2board.clash.remote.Remote.service
            com.tidalab.v2board.clash.remote.Resource<com.tidalab.v2board.clash.service.remote.IRemoteService> r10 = r10.remote
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r10 = r10.get(r0)
            if (r10 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0069:
            com.tidalab.v2board.clash.service.remote.IRemoteService r10 = (com.tidalab.v2board.clash.service.remote.IRemoteService) r10
            com.tidalab.v2board.clash.service.remote.IClashManager r2 = r10.clash()
            com.tidalab.v2board.clash.util.RemoteKt$withClash$2 r6 = new com.tidalab.v2board.clash.util.RemoteKt$withClash$2     // Catch: DeadObjectException -> 0x0084
            r6.<init>(r8, r2, r5)     // Catch: DeadObjectException -> 0x0084
            r0.L$0 = r9     // Catch: DeadObjectException -> 0x0084
            r0.L$1 = r8     // Catch: DeadObjectException -> 0x0084
            r0.L$2 = r10     // Catch: DeadObjectException -> 0x0084
            r0.label = r4     // Catch: DeadObjectException -> 0x0084
            java.lang.Object r10 = withContext(r9, r6, r0)     // Catch: DeadObjectException -> 0x0084
            if (r10 != r1) goto L_0x0083
            return r1
        L_0x0083:
            return r10
        L_0x0084:
            r7 = r10
            r10 = r8
            r8 = r7
        L_0x0087:
            java.lang.String r2 = "Remote services panic"
            java.lang.String r6 = "ClashForAndroid"
            android.util.Log.w(r6, r2, r5)
            com.tidalab.v2board.clash.remote.Remote r2 = com.tidalab.v2board.clash.remote.Remote.INSTANCE
            com.tidalab.v2board.clash.remote.Service r2 = com.tidalab.v2board.clash.remote.Remote.service
            com.tidalab.v2board.clash.remote.Resource<com.tidalab.v2board.clash.service.remote.IRemoteService> r2 = r2.remote
            monitor-enter(r2)
            T r6 = r2.value     // Catch: all -> 0x009f
            if (r6 != r8) goto L_0x009b
            r2.value = r5     // Catch: all -> 0x009f
        L_0x009b:
            monitor-exit(r2)
            r8 = r9
            r9 = r10
            goto L_0x0051
        L_0x009f:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.withClash(kotlin.coroutines.CoroutineContext, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Object withClash$default(CoroutineContext coroutineContext, Function2 function2, Continuation continuation, int i) {
        CoroutineDispatcher coroutineDispatcher;
        if ((i & 1) != 0) {
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            coroutineDispatcher = Dispatchers.IO;
        } else {
            coroutineDispatcher = null;
        }
        return withClash(coroutineDispatcher, function2, continuation);
    }

    public static final <T> Object withContext(CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        CoroutineContext context = continuation.getContext();
        CoroutineContext plus = context.plus(coroutineContext);
        ensureActive(plus);
        if (plus == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(plus, continuation);
            return startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        }
        int i = ContinuationInterceptor.$r8$clinit;
        ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
        if (Intrinsics.areEqual(plus.get(key), context.get(key))) {
            UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(plus, continuation);
            Object updateThreadContext = ThreadContextKt.updateThreadContext(plus, null);
            try {
                return startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
            } finally {
                ThreadContextKt.restoreThreadContext(plus, updateThreadContext);
            }
        } else {
            DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(plus, continuation);
            startCoroutineCancellable$default(function2, dispatchedCoroutine, dispatchedCoroutine, null, 4);
            return dispatchedCoroutine.getResult();
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$configureImpl$1] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.appcompat.app.AppCompatDialog] */
    /* JADX WARN: Type inference failed for: r6v3, types: [kotlin.Unit, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object withModelProgressBar(android.content.Context r6, kotlin.jvm.functions.Function2<? super com.tidalab.v2board.clash.design.dialog.ModelProgressBarScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$1 r0 = (com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$1 r0 = new com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$1
            r0.<init>(r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            java.lang.Object r6 = r0.L$0
            androidx.appcompat.app.AlertDialog r6 = (androidx.appcompat.app.AlertDialog) r6
            throwOnFailure(r8)     // Catch: all -> 0x0076
            goto L_0x0070
        L_0x002b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0033:
            throwOnFailure(r8)
            android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r6)
            int r2 = com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBinding.$r8$clinit
            androidx.databinding.DataBinderMapper r2 = androidx.databinding.DataBindingUtil.sMapper
            r2 = 2131492939(0x7f0c004b, float:1.8609344E38)
            r4 = 0
            r5 = 0
            androidx.databinding.ViewDataBinding r8 = androidx.databinding.ViewDataBinding.inflateInternal(r8, r2, r4, r5, r4)
            com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBinding r8 = (com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBinding) r8
            com.google.android.material.dialog.MaterialAlertDialogBuilder r2 = new com.google.android.material.dialog.MaterialAlertDialogBuilder
            r2.<init>(r6)
            androidx.appcompat.app.AlertController$AlertParams r6 = r2.P
            r6.mCancelable = r5
            android.view.View r6 = r8.mRoot
            r2.setView(r6)
            androidx.appcompat.app.AlertDialog r6 = r2.show()
            com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$configureImpl$1 r2 = new com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$configureImpl$1
            r2.<init>()
            com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$scopeImpl$1 r8 = new com.tidalab.v2board.clash.design.dialog.ProgressKt$withModelProgressBar$scopeImpl$1
            r8.<init>()
            r0.L$0 = r6     // Catch: all -> 0x0076
            r0.label = r3     // Catch: all -> 0x0076
            java.lang.Object r7 = r7.invoke(r8, r0)     // Catch: all -> 0x0076
            if (r7 != r1) goto L_0x0070
            return r1
        L_0x0070:
            r6.dismiss()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0076:
            r7 = move-exception
            r6.dismiss()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.withModelProgressBar(android.content.Context, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0065 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083 A[PHI: r10 
      PHI: (r10v3 java.lang.Object) = (r10v8 java.lang.Object), (r10v1 java.lang.Object) binds: [B:25:0x0080, B:13:0x0033] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0084 -> B:29:0x0087). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> java.lang.Object withProfile(kotlin.coroutines.CoroutineContext r8, kotlin.jvm.functions.Function2<? super com.tidalab.v2board.clash.service.remote.IProfileManager, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r9, kotlin.coroutines.Continuation<? super T> r10) {
        /*
            boolean r0 = r10 instanceof com.tidalab.v2board.clash.util.RemoteKt$withProfile$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            com.tidalab.v2board.clash.util.RemoteKt$withProfile$1 r0 = (com.tidalab.v2board.clash.util.RemoteKt$withProfile$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.util.RemoteKt$withProfile$1 r0 = new com.tidalab.v2board.clash.util.RemoteKt$withProfile$1
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            r5 = 0
            if (r2 == 0) goto L_0x004e
            if (r2 == r3) goto L_0x0042
            if (r2 != r4) goto L_0x003a
            java.lang.Object r8 = r0.L$2
            com.tidalab.v2board.clash.service.remote.IRemoteService r8 = (com.tidalab.v2board.clash.service.remote.IRemoteService) r8
            java.lang.Object r9 = r0.L$1
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            java.lang.Object r2 = r0.L$0
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            throwOnFailure(r10)     // Catch: DeadObjectException -> 0x0037
            goto L_0x0083
        L_0x0037:
            r10 = r9
            r9 = r2
            goto L_0x0087
        L_0x003a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0042:
            java.lang.Object r8 = r0.L$1
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            java.lang.Object r9 = r0.L$0
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            throwOnFailure(r10)
            goto L_0x0069
        L_0x004e:
            throwOnFailure(r10)
        L_0x0051:
            com.tidalab.v2board.clash.remote.Remote r10 = com.tidalab.v2board.clash.remote.Remote.INSTANCE
            com.tidalab.v2board.clash.remote.Service r10 = com.tidalab.v2board.clash.remote.Remote.service
            com.tidalab.v2board.clash.remote.Resource<com.tidalab.v2board.clash.service.remote.IRemoteService> r10 = r10.remote
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r10 = r10.get(r0)
            if (r10 != r1) goto L_0x0066
            return r1
        L_0x0066:
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0069:
            com.tidalab.v2board.clash.service.remote.IRemoteService r10 = (com.tidalab.v2board.clash.service.remote.IRemoteService) r10
            com.tidalab.v2board.clash.service.remote.IProfileManager r2 = r10.profile()
            com.tidalab.v2board.clash.util.RemoteKt$withProfile$2 r6 = new com.tidalab.v2board.clash.util.RemoteKt$withProfile$2     // Catch: DeadObjectException -> 0x0084
            r6.<init>(r8, r2, r5)     // Catch: DeadObjectException -> 0x0084
            r0.L$0 = r9     // Catch: DeadObjectException -> 0x0084
            r0.L$1 = r8     // Catch: DeadObjectException -> 0x0084
            r0.L$2 = r10     // Catch: DeadObjectException -> 0x0084
            r0.label = r4     // Catch: DeadObjectException -> 0x0084
            java.lang.Object r10 = withContext(r9, r6, r0)     // Catch: DeadObjectException -> 0x0084
            if (r10 != r1) goto L_0x0083
            return r1
        L_0x0083:
            return r10
        L_0x0084:
            r7 = r10
            r10 = r8
            r8 = r7
        L_0x0087:
            java.lang.String r2 = "Remote services panic"
            java.lang.String r6 = "ClashForAndroid"
            android.util.Log.w(r6, r2, r5)
            com.tidalab.v2board.clash.remote.Remote r2 = com.tidalab.v2board.clash.remote.Remote.INSTANCE
            com.tidalab.v2board.clash.remote.Service r2 = com.tidalab.v2board.clash.remote.Remote.service
            com.tidalab.v2board.clash.remote.Resource<com.tidalab.v2board.clash.service.remote.IRemoteService> r2 = r2.remote
            monitor-enter(r2)
            T r6 = r2.value     // Catch: all -> 0x009f
            if (r6 != r8) goto L_0x009b
            r2.value = r5     // Catch: all -> 0x009f
        L_0x009b:
            monitor-exit(r2)
            r8 = r9
            r9 = r10
            goto L_0x0051
        L_0x009f:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.dialog.InputKt.withProfile(kotlin.coroutines.CoroutineContext, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Object withProfile$default(CoroutineContext coroutineContext, Function2 function2, Continuation continuation, int i) {
        CoroutineDispatcher coroutineDispatcher;
        if ((i & 1) != 0) {
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            coroutineDispatcher = Dispatchers.IO;
        } else {
            coroutineDispatcher = null;
        }
        return withProfile(coroutineDispatcher, function2, continuation);
    }

    public static final JsonDecodingException JsonDecodingException(int i, String str, String str2) {
        return JsonDecodingException(i, str + "\nJSON input: " + minify(str2, i));
    }

    public static final void bindAppBarElevation(ObservableScrollView observableScrollView, ActivityBarLayout activityBarLayout) {
        final AppBarElevationController appBarElevationController = new AppBarElevationController(activityBarLayout);
        observableScrollView.scrollChangedListeners.add(new ObservableScrollView.OnScrollChangedListener() { // from class: com.tidalab.v2board.clash.design.util.-$$Lambda$ElevationKt$tznYEgeqT7-VBxuE6bSbuIPZ9GA
            @Override // com.tidalab.v2board.clash.design.view.ObservableScrollView.OnScrollChangedListener
            public final void onChanged(ObservableScrollView observableScrollView2, int i, int i2, int i3, int i4) {
                AppBarElevationController.this.setElevated(!(observableScrollView2.getScrollX() == 0 && observableScrollView2.getScrollY() == 0));
            }
        });
    }

    public static void cancel$default(CoroutineScope coroutineScope, CancellationException cancellationException, int i) {
        int i2 = i & 1;
        CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
        int i3 = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            job.cancel(null);
            return;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Scope cannot be cancelled because it does not have a job: ", coroutineScope).toString());
    }

    public static final KSerializer<Object> serializer(SerializersModule serializersModule, KType kType) {
        KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt = SerializersKt__SerializersKt.serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, kType, true);
        if (serializerByKTypeImpl$SerializersKt__SerializersKt != null) {
            return serializerByKTypeImpl$SerializersKt__SerializersKt;
        }
        KClass<Object> kclass = Platform_commonKt.kclass(kType);
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Serializer for class '");
        outline13.append((Object) kclass.getSimpleName());
        outline13.append("' is not found.\nMark the class as @Serializable or provide the serializer explicitly.");
        throw new SerializationException(outline13.toString());
    }

    public static /* synthetic */ long systemProp$default(String str, long j, long j2, long j3, int i, Object obj) {
        if ((i & 4) != 0) {
            j2 = 1;
        }
        if ((i & 8) != 0) {
            j3 = Long.MAX_VALUE;
        }
        return systemProp(str, j, j2, j3);
    }

    public static final <T> SerializationStrategy<T> findPolymorphicSerializer(AbstractPolymorphicSerializer<T> abstractPolymorphicSerializer, Encoder encoder, T t) {
        SerializationStrategy<T> polymorphic = encoder.getSerializersModule().getPolymorphic((KClass<? super KClass<T>>) abstractPolymorphicSerializer.getBaseClass(), (KClass<T>) t);
        if (polymorphic != null) {
            return polymorphic;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(t.getClass());
        KClass<T> baseClass = abstractPolymorphicSerializer.getBaseClass();
        String simpleName = ((ClassReference) orCreateKotlinClass).getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(orCreateKotlinClass);
        }
        throwSubtypeNotRegistered(simpleName, baseClass);
        throw null;
    }

    public static final long systemProp(String str, long j, long j2, long j3) {
        String systemProp = systemProp(str);
        if (systemProp == null) {
            return j;
        }
        Long longOrNull = StringsKt__IndentKt.toLongOrNull(systemProp);
        if (longOrNull != null) {
            long longValue = longOrNull.longValue();
            boolean z = false;
            if (j2 <= longValue && longValue <= j3) {
                z = true;
            }
            if (z) {
                return longValue;
            }
            throw new IllegalStateException(("System property '" + str + "' should be in range " + j2 + ".." + j3 + ", but is '" + longValue + '\'').toString());
        }
        throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + systemProp + '\'').toString());
    }

    public static final Object requestModelTextInput(Context context, final String str, CharSequence charSequence, CharSequence charSequence2, final CharSequence charSequence3, final CharSequence charSequence4, final Function1<? super String, Boolean> function1, Continuation<? super String> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup root = getRoot(context);
        int i = DialogTextFieldBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        final DialogTextFieldBinding dialogTextFieldBinding = (DialogTextFieldBinding) ViewDataBinding.inflateInternal(from, R.layout.dialog_text_field, root, false, null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.P.mTitle = charSequence;
        materialAlertDialogBuilder.setView(dialogTextFieldBinding.mRoot);
        materialAlertDialogBuilder.P.mCancelable = true;
        materialAlertDialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$3$builder$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                String obj;
                Editable text = DialogTextFieldBinding.this.textField.getText();
                String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
                if (!(text == null || (obj = text.toString()) == null)) {
                    str2 = obj;
                }
                if (function1.invoke(str2).booleanValue()) {
                    cancellableContinuationImpl.resumeWith(str2);
                } else {
                    cancellableContinuationImpl.resumeWith(str);
                }
            }
        });
        materialAlertDialogBuilder.setNegativeButton(R.string.cancel, InputKt$requestModelTextInput$3$builder$2.INSTANCE);
        DialogInterface.OnDismissListener inputKt$requestModelTextInput$3$builder$3 = new DialogInterface.OnDismissListener() { // from class: com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$3$builder$3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                if (!cancellableContinuationImpl.isCompleted()) {
                    cancellableContinuationImpl.resumeWith(str);
                }
            }
        };
        AlertController.AlertParams alertParams = materialAlertDialogBuilder.P;
        alertParams.mOnDismissListener = inputKt$requestModelTextInput$3$builder$3;
        if (charSequence2 != null) {
            DialogInterface.OnClickListener inputKt$requestModelTextInput$3$1 = new DialogInterface.OnClickListener() { // from class: com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$3$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    cancellableContinuationImpl.resumeWith(null);
                }
            };
            alertParams.mNeutralButtonText = charSequence2;
            alertParams.mNeutralButtonListener = inputKt$requestModelTextInput$3$1;
        }
        final AlertDialog create = materialAlertDialogBuilder.create();
        cancellableContinuationImpl.invokeOnCancellation(new InputKt$requestModelTextInput$3$2(create));
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$3$3
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                CharSequence charSequence5 = charSequence3;
                if (charSequence5 != null) {
                    dialogTextFieldBinding.textLayout.setHint(charSequence5);
                }
                final DialogTextFieldBinding dialogTextFieldBinding2 = dialogTextFieldBinding;
                TextInputEditText textInputEditText = dialogTextFieldBinding2.textField;
                final CharSequence charSequence6 = charSequence4;
                String str2 = str;
                final Function1<String, Boolean> function12 = function1;
                final AlertDialog alertDialog = create;
                dialogTextFieldBinding2.textLayout.setErrorEnabled(charSequence6 != null);
                textInputEditText.addTextChangedListener(new TextWatcher() { // from class: com.tidalab.v2board.clash.design.dialog.InputKt$requestModelTextInput$3$3$onShow$lambda-1$$inlined$doOnTextChanged$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence7, int i2, int i3, int i4) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence7, int i2, int i3, int i4) {
                        String obj;
                        Function1 function13 = Function1.this;
                        String str3 = HttpUrl.FRAGMENT_ENCODE_SET;
                        if (!(charSequence7 == null || (obj = charSequence7.toString()) == null)) {
                            str3 = obj;
                        }
                        if (!((Boolean) function13.invoke(str3)).booleanValue()) {
                            CharSequence charSequence8 = charSequence6;
                            if (charSequence8 != null) {
                                dialogTextFieldBinding2.textLayout.setError(charSequence8);
                            }
                            alertDialog.getButton(-1).setEnabled(false);
                            return;
                        }
                        if (charSequence6 != null) {
                            dialogTextFieldBinding2.textLayout.setError(null);
                        }
                        alertDialog.getButton(-1).setEnabled(true);
                    }
                });
                textInputEditText.setText(str2);
                textInputEditText.setSelection(0, str2 == null ? 0 : str2.length());
                textInputEditText.post(new $$Lambda$ViewKt$Leq_OYUMGGHh0dLHMsbHrbNfgyY(textInputEditText));
            }
        });
        create.show();
        return cancellableContinuationImpl.getResult();
    }
}

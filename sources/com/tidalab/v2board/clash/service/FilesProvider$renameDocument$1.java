package com.tidalab.v2board.clash.service;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.document.Document;
import com.tidalab.v2board.clash.service.document.FileDocument;
import com.tidalab.v2board.clash.service.document.Path;
import com.tidalab.v2board.clash.service.document.Paths;
import com.tidalab.v2board.clash.service.document.Picker;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: FilesProvider.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.FilesProvider$renameDocument$1", f = "FilesProvider.kt", l = {96}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FilesProvider$renameDocument$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    public final /* synthetic */ String $documentId;
    public final /* synthetic */ String $name;
    public Object L$0;
    public int label;
    public final /* synthetic */ FilesProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FilesProvider$renameDocument$1(String str, FilesProvider filesProvider, String str2, Continuation<? super FilesProvider$renameDocument$1> continuation) {
        super(2, continuation);
        this.$documentId = str;
        this.this$0 = filesProvider;
        this.$name = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FilesProvider$renameDocument$1(this.$documentId, this.this$0, this.$name, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return new FilesProvider$renameDocument$1(this.$documentId, this.this$0, this.$name, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Path path;
        Collection collection;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            String str = this.$documentId;
            if (str == null) {
                str = "/";
            }
            Path resolve = Paths.resolve(str);
            if (resolve.relative != null) {
                Picker access$getPicker = FilesProvider.access$getPicker(this.this$0);
                this.L$0 = resolve;
                this.label = 1;
                Object pick = access$getPicker.pick(resolve, true, this);
                if (pick == coroutineSingletons) {
                    return coroutineSingletons;
                }
                path = resolve;
                obj = pick;
            } else {
                throw new IllegalArgumentException(Intrinsics.stringPlus("unable to rename ", this.$documentId));
            }
        } else if (i == 1) {
            path = (Path) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Document document = (Document) obj;
        if (document instanceof FileDocument) {
            FileDocument fileDocument = (FileDocument) document;
            File parentFile = fileDocument.file.getParentFile();
            int i2 = 0;
            if (parentFile != null) {
                fileDocument.file.renameTo(FilesKt__UtilsKt.resolve(parentFile, this.$name));
                List<String> list = path.relative;
                int size = list.size() - 1;
                if (size < 0) {
                    size = 0;
                }
                if (size >= 0) {
                    if (size == 0) {
                        collection = EmptyList.INSTANCE;
                    } else if (size >= list.size()) {
                        collection = ArraysKt___ArraysKt.toList(list);
                    } else if (size != 1) {
                        ArrayList arrayList = new ArrayList(size);
                        for (Object obj2 : list) {
                            arrayList.add(obj2);
                            i2++;
                            if (i2 == size) {
                                break;
                            }
                        }
                        collection = ArraysKt___ArraysKt.optimizeReadOnlyList(arrayList);
                    } else if (!list.isEmpty()) {
                        collection = Collections.singletonList(list.get(0));
                    } else {
                        throw new NoSuchElementException("List is empty.");
                    }
                    String str2 = this.$name;
                    ArrayList arrayList2 = new ArrayList(collection.size() + 1);
                    arrayList2.addAll(collection);
                    arrayList2.add(str2);
                    return Path.copy$default(path, null, null, arrayList2, 3).toString();
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("Requested element count ", size, " is less than zero.").toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("unable to rename ", document));
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("unable to rename ", document));
    }
}

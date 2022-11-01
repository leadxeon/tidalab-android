package com.tidalab.v2board.clash.util;

import android.content.ContentResolver;
import android.net.Uri;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Content.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.util.ContentKt$copyContentTo$2", f = "Content.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ContentKt$copyContentTo$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    public final /* synthetic */ Uri $source;
    public final /* synthetic */ Uri $target;
    public final /* synthetic */ ContentResolver $this_copyContentTo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentKt$copyContentTo$2(ContentResolver contentResolver, Uri uri, Uri uri2, Continuation<? super ContentKt$copyContentTo$2> continuation) {
        super(2, continuation);
        this.$this_copyContentTo = contentResolver;
        this.$source = uri;
        this.$target = uri2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContentKt$copyContentTo$2(this.$this_copyContentTo, this.$source, this.$target, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        Continuation<? super Long> continuation2 = continuation;
        ContentResolver contentResolver = this.$this_copyContentTo;
        Uri uri = this.$source;
        Uri uri2 = this.$target;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        InputStream openInputStream = contentResolver.openInputStream(uri);
        if (openInputStream != null) {
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(uri2, "rwt");
                if (openOutputStream != null) {
                    Long l = new Long(InputKt.copyTo(openInputStream, openOutputStream, 8192));
                    th = null;
                    InputKt.closeFinally(openOutputStream, th);
                    return new Long(l.longValue());
                }
                throw new FileNotFoundException(uri2 + " not found");
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } else {
            throw new FileNotFoundException(uri + " not found");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        InputStream openInputStream = this.$this_copyContentTo.openInputStream(this.$source);
        if (openInputStream != null) {
            ContentResolver contentResolver = this.$this_copyContentTo;
            Uri uri = this.$target;
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(uri, "rwt");
                if (openOutputStream != null) {
                    Long l = new Long(InputKt.copyTo(openInputStream, openOutputStream, 8192));
                    th = null;
                    InputKt.closeFinally(openOutputStream, th);
                    return new Long(l.longValue());
                }
                throw new FileNotFoundException(uri + " not found");
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } else {
            Uri uri2 = this.$source;
            throw new FileNotFoundException(uri2 + " not found");
        }
    }
}

package androidx.core.provider;

import android.graphics.Typeface;
import android.os.Handler;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontRequestWorker;
/* loaded from: classes.dex */
public class CallbackWithHandler {
    public final FontsContractCompat$FontRequestCallback mCallback;
    public final Handler mCallbackHandler;

    /* renamed from: androidx.core.provider.CallbackWithHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ FontsContractCompat$FontRequestCallback val$callback;
        public final /* synthetic */ Typeface val$typeface;

        public AnonymousClass1(CallbackWithHandler callbackWithHandler, FontsContractCompat$FontRequestCallback fontsContractCompat$FontRequestCallback, Typeface typeface) {
            this.val$callback = fontsContractCompat$FontRequestCallback;
            this.val$typeface = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            FontsContractCompat$FontRequestCallback fontsContractCompat$FontRequestCallback = this.val$callback;
            Typeface typeface = this.val$typeface;
            ResourcesCompat.FontCallback fontCallback = ((TypefaceCompat.ResourcesCallbackAdapter) fontsContractCompat$FontRequestCallback).mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
        }
    }

    /* renamed from: androidx.core.provider.CallbackWithHandler$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements Runnable {
        public final /* synthetic */ FontsContractCompat$FontRequestCallback val$callback;
        public final /* synthetic */ int val$reason;

        public AnonymousClass2(CallbackWithHandler callbackWithHandler, FontsContractCompat$FontRequestCallback fontsContractCompat$FontRequestCallback, int i) {
            this.val$callback = fontsContractCompat$FontRequestCallback;
            this.val$reason = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            FontsContractCompat$FontRequestCallback fontsContractCompat$FontRequestCallback = this.val$callback;
            int i = this.val$reason;
            ResourcesCompat.FontCallback fontCallback = ((TypefaceCompat.ResourcesCallbackAdapter) fontsContractCompat$FontRequestCallback).mFontCallback;
            if (fontCallback != null) {
                fontCallback.onFontRetrievalFailed(i);
            }
        }
    }

    public CallbackWithHandler(FontsContractCompat$FontRequestCallback fontsContractCompat$FontRequestCallback, Handler handler) {
        this.mCallback = fontsContractCompat$FontRequestCallback;
        this.mCallbackHandler = handler;
    }

    public void onTypefaceResult(FontRequestWorker.TypefaceResult typefaceResult) {
        int i = typefaceResult.mResult;
        if (i == 0) {
            this.mCallbackHandler.post(new AnonymousClass1(this, this.mCallback, typefaceResult.mTypeface));
            return;
        }
        this.mCallbackHandler.post(new AnonymousClass2(this, this.mCallback, i));
    }
}

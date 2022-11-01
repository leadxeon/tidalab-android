package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;
/* loaded from: classes.dex */
public final class InputContentInfoCompat {
    public final InputContentInfoCompatImpl mImpl;

    /* loaded from: classes.dex */
    public static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {
        public final Uri mContentUri;
        public final ClipDescription mDescription;
        public final Uri mLinkUri;

        public InputContentInfoCompatBaseImpl(Uri uri, ClipDescription clipDescription, Uri uri2) {
            this.mContentUri = uri;
            this.mDescription = clipDescription;
            this.mLinkUri = uri2;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
        }
    }

    /* loaded from: classes.dex */
    public interface InputContentInfoCompatImpl {
        Uri getContentUri();

        ClipDescription getDescription();

        Object getInputContentInfo();

        Uri getLinkUri();

        void requestPermission();
    }

    public InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }

    /* loaded from: classes.dex */
    public static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        public final InputContentInfo mObject;

        public InputContentInfoCompatApi25Impl(Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }

        public InputContentInfoCompatApi25Impl(Uri uri, ClipDescription clipDescription, Uri uri2) {
            this.mObject = new InputContentInfo(uri, clipDescription, uri2);
        }
    }
}

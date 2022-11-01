package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R$string;
import androidx.appcompat.widget.AppCompatReceiveContentHelper$1;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AppCompatEditText extends EditText implements OnReceiveContentViewBehavior {
    public final AppCompatBackgroundHelper mBackgroundTintHelper;
    public final TextViewOnReceiveContentListener mDefaultOnReceiveContentListener;
    public final AppCompatTextClassifierHelper mTextClassifierHelper;
    public final AppCompatTextHelper mTextHelper;

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.editTextStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.applyCompoundDrawablesTints();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        AppCompatTextClassifierHelper appCompatTextClassifierHelper;
        if (Build.VERSION.SDK_INT >= 28 || (appCompatTextClassifierHelper = this.mTextClassifierHelper) == null) {
            return super.getTextClassifier();
        }
        return appCompatTextClassifierHelper.getTextClassifier();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] strArr;
        InputConnectionWrapper inputConnectionCompat$2;
        final InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.mTextHelper.populateSurroundingTextIfNeeded(this, onCreateInputConnection, editorInfo);
        R$string.onCreateInputConnection(onCreateInputConnection, editorInfo, this);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        String[] strArr2 = (String[]) getTag(R.id.tag_on_receive_content_mime_types);
        if (onCreateInputConnection == null || strArr2 == null) {
            return onCreateInputConnection;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 25) {
            editorInfo.contentMimeTypes = strArr2;
        } else {
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr2);
            editorInfo.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr2);
        }
        final AppCompatReceiveContentHelper$1 appCompatReceiveContentHelper$1 = new AppCompatReceiveContentHelper$1(this);
        if (editorInfo != null) {
            if (i >= 25) {
                inputConnectionCompat$2 = new InputConnectionWrapper(onCreateInputConnection, false) { // from class: androidx.core.view.inputmethod.InputConnectionCompat$1
                    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
                    public boolean commitContent(InputContentInfo inputContentInfo, int i2, Bundle bundle) {
                        InputConnectionCompat$OnCommitContentListener inputConnectionCompat$OnCommitContentListener = appCompatReceiveContentHelper$1;
                        InputContentInfoCompat inputContentInfoCompat = null;
                        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
                            inputContentInfoCompat = new InputContentInfoCompat(new InputContentInfoCompat.InputContentInfoCompatApi25Impl(inputContentInfo));
                        }
                        if (((AppCompatReceiveContentHelper$1) inputConnectionCompat$OnCommitContentListener).onCommitContent(inputContentInfoCompat, i2, bundle)) {
                            return true;
                        }
                        return super.commitContent(inputContentInfo, i2, bundle);
                    }
                };
            } else {
                if (i >= 25) {
                    strArr = editorInfo.contentMimeTypes;
                    if (strArr == null) {
                        strArr = EditorInfoCompat.EMPTY_STRING_ARRAY;
                    }
                } else {
                    Bundle bundle = editorInfo.extras;
                    if (bundle == null) {
                        strArr = EditorInfoCompat.EMPTY_STRING_ARRAY;
                    } else {
                        String[] stringArray = bundle.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
                        strArr = stringArray == null ? editorInfo.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES") : stringArray;
                        if (strArr == null) {
                            strArr = EditorInfoCompat.EMPTY_STRING_ARRAY;
                        }
                    }
                }
                if (strArr.length == 0) {
                    return onCreateInputConnection;
                }
                inputConnectionCompat$2 = new InputConnectionWrapper(onCreateInputConnection, false) { // from class: androidx.core.view.inputmethod.InputConnectionCompat$2
                    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
                    public boolean performPrivateCommand(String str, Bundle bundle2) {
                        boolean z;
                        Throwable th;
                        ResultReceiver resultReceiver;
                        InputContentInfoCompat.InputContentInfoCompatImpl inputContentInfoCompatImpl;
                        InputConnectionCompat$OnCommitContentListener inputConnectionCompat$OnCommitContentListener = appCompatReceiveContentHelper$1;
                        int i2 = 0;
                        if (bundle2 != null) {
                            if (TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", str)) {
                                z = false;
                            } else if (TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", str)) {
                                z = true;
                            }
                            try {
                                resultReceiver = (ResultReceiver) bundle2.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER");
                            } catch (Throwable th2) {
                                th = th2;
                                resultReceiver = null;
                            }
                            try {
                                Uri uri = (Uri) bundle2.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI");
                                ClipDescription clipDescription = (ClipDescription) bundle2.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION");
                                Uri uri2 = (Uri) bundle2.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI");
                                int i3 = bundle2.getInt(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS");
                                Bundle bundle3 = (Bundle) bundle2.getParcelable(z ? "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS" : "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS");
                                if (!(uri == null || clipDescription == null)) {
                                    int i4 = Build.VERSION.SDK_INT;
                                    if (i4 >= 25) {
                                        inputContentInfoCompatImpl = new InputContentInfoCompat.InputContentInfoCompatApi25Impl(uri, clipDescription, uri2);
                                    } else {
                                        inputContentInfoCompatImpl = new InputContentInfoCompat.InputContentInfoCompatBaseImpl(uri, clipDescription, uri2);
                                    }
                                    AppCompatReceiveContentHelper$1 appCompatReceiveContentHelper$12 = (AppCompatReceiveContentHelper$1) inputConnectionCompat$OnCommitContentListener;
                                    if (i4 >= 25 && (i3 & 1) != 0) {
                                        try {
                                            inputContentInfoCompatImpl.requestPermission();
                                            InputContentInfo inputContentInfo = (InputContentInfo) inputContentInfoCompatImpl.getInputContentInfo();
                                            bundle3 = bundle3 == null ? new Bundle() : new Bundle(bundle3);
                                            bundle3.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfo);
                                        } catch (Exception e) {
                                            Log.w("ReceiveContent", "Can't insert content from IME; requestPermission() failed", e);
                                        }
                                    }
                                    ContentInfoCompat.Builder builder = new ContentInfoCompat.Builder(new ClipData(inputContentInfoCompatImpl.getDescription(), new ClipData.Item(inputContentInfoCompatImpl.getContentUri())), 2);
                                    builder.mLinkUri = inputContentInfoCompatImpl.getLinkUri();
                                    builder.mExtras = bundle3;
                                    if (ViewCompat.performReceiveContent(appCompatReceiveContentHelper$12.val$view, new ContentInfoCompat(builder)) == null) {
                                        i2 = 1;
                                    }
                                }
                                if (resultReceiver != null) {
                                    resultReceiver.send(i2, null);
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (resultReceiver != null) {
                                    resultReceiver.send(0, null);
                                }
                                throw th;
                            }
                        }
                        if (i2 != 0) {
                            return true;
                        }
                        return super.performPrivateCommand(str, bundle2);
                    }
                };
            }
            return inputConnectionCompat$2;
        }
        throw new IllegalArgumentException("editorInfo must be non-null");
    }

    /* JADX WARN: Finally extract failed */
    @Override // android.widget.TextView, android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 24 && dragEvent.getLocalState() == null) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (((String[]) getTag(R.id.tag_on_receive_content_mime_types)) != null) {
                Context context = getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        activity = null;
                        break;
                    } else if (context instanceof Activity) {
                        activity = (Activity) context;
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
                if (activity == null) {
                    Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + this);
                } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                    activity.requestDragAndDropPermissions(dragEvent);
                    int offsetForPosition = getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
                    beginBatchEdit();
                    try {
                        Selection.setSelection((Spannable) getText(), offsetForPosition);
                        ViewCompat.performReceiveContent(this, new ContentInfoCompat(new ContentInfoCompat.Builder(dragEvent.getClipData(), 3)));
                        endBatchEdit();
                        z = true;
                    } catch (Throwable th) {
                        endBatchEdit();
                        throw th;
                    }
                }
            }
        }
        if (z) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // androidx.core.view.OnReceiveContentViewBehavior
    public ContentInfoCompat onReceiveContent(ContentInfoCompat contentInfoCompat) {
        return this.mDefaultOnReceiveContentListener.onReceiveContent(this, contentInfoCompat);
    }

    @Override // android.widget.TextView
    public boolean onTextContextMenuItem(int i) {
        int i2 = 0;
        if (i == 16908322 || i == 16908337) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (((String[]) getTag(R.id.tag_on_receive_content_mime_types)) != null) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
                ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
                if (primaryClip != null && primaryClip.getItemCount() > 0) {
                    ContentInfoCompat.Builder builder = new ContentInfoCompat.Builder(primaryClip, 1);
                    if (i != 16908322) {
                        i2 = 1;
                    }
                    builder.mFlags = i2;
                    ViewCompat.performReceiveContent(this, new ContentInfoCompat(builder));
                }
                i2 = 1;
            }
        }
        if (i2 != 0) {
            return true;
        }
        return super.onTextContextMenuItem(i);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundDrawable();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.onSetBackgroundResource(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AppOpsManagerCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.onSetTextAppearance(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        AppCompatTextClassifierHelper appCompatTextClassifierHelper;
        if (Build.VERSION.SDK_INT >= 28 || (appCompatTextClassifierHelper = this.mTextClassifierHelper) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            appCompatTextClassifierHelper.mTextClassifier = textClassifier;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintContextWrapper.wrap(context);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.loadFromAttributes(attributeSet, i);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attributeSet, i);
        appCompatTextHelper.applyCompoundDrawablesTints();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        this.mDefaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }
}

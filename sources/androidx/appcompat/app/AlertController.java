package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.core.widget.NestedScrollView;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class AlertController {
    public ListAdapter mAdapter;
    public int mAlertDialogLayout;
    public final int mButtonIconDimen;
    public Button mButtonNegative;
    public Drawable mButtonNegativeIcon;
    public Message mButtonNegativeMessage;
    public CharSequence mButtonNegativeText;
    public Button mButtonNeutral;
    public Drawable mButtonNeutralIcon;
    public Message mButtonNeutralMessage;
    public CharSequence mButtonNeutralText;
    public int mButtonPanelSideLayout;
    public Button mButtonPositive;
    public Drawable mButtonPositiveIcon;
    public Message mButtonPositiveMessage;
    public CharSequence mButtonPositiveText;
    public final Context mContext;
    public View mCustomTitleView;
    public final AppCompatDialog mDialog;
    public Handler mHandler;
    public Drawable mIcon;
    public ImageView mIconView;
    public int mListItemLayout;
    public int mListLayout;
    public ListView mListView;
    public CharSequence mMessage;
    public TextView mMessageView;
    public int mMultiChoiceItemLayout;
    public NestedScrollView mScrollView;
    public boolean mShowTitle;
    public int mSingleChoiceItemLayout;
    public CharSequence mTitle;
    public TextView mTitleView;
    public View mView;
    public int mViewLayoutResId;
    public int mViewSpacingBottom;
    public int mViewSpacingLeft;
    public int mViewSpacingRight;
    public int mViewSpacingTop;
    public final Window mWindow;
    public boolean mViewSpacingSpecified = false;
    public int mIconId = 0;
    public int mCheckedItem = -1;
    public final View.OnClickListener mButtonHandler = new View.OnClickListener() { // from class: androidx.appcompat.app.AlertController.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Message message;
            Message message2;
            Message message3;
            Message message4;
            AlertController alertController = AlertController.this;
            if (view == alertController.mButtonPositive && (message4 = alertController.mButtonPositiveMessage) != null) {
                message = Message.obtain(message4);
            } else if (view != alertController.mButtonNegative || (message3 = alertController.mButtonNegativeMessage) == null) {
                message = (view != alertController.mButtonNeutral || (message2 = alertController.mButtonNeutralMessage) == null) ? null : Message.obtain(message2);
            } else {
                message = Message.obtain(message3);
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertController alertController2 = AlertController.this;
            alertController2.mHandler.obtainMessage(1, alertController2.mDialog).sendToTarget();
        }
    };

    /* renamed from: androidx.appcompat.app.AlertController$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements NestedScrollView.OnScrollChangeListener {
        public final /* synthetic */ View val$bottom;
        public final /* synthetic */ View val$top;

        public AnonymousClass2(AlertController alertController, View view, View view2) {
            this.val$top = view;
            this.val$bottom = view2;
        }
    }

    /* loaded from: classes.dex */
    public static class AlertParams {
        public ListAdapter mAdapter;
        public final Context mContext;
        public View mCustomTitleView;
        public Drawable mIcon;
        public final LayoutInflater mInflater;
        public boolean mIsSingleChoice;
        public CharSequence mMessage;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public CharSequence mTitle;
        public View mView;
        public int mCheckedItem = -1;
        public boolean mCancelable = true;

        public AlertParams(Context context) {
            this.mContext = context;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }
    }

    /* loaded from: classes.dex */
    public static final class ButtonHandler extends Handler {
        public WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == -3 || i == -2 || i == -1) {
                ((DialogInterface.OnClickListener) message.obj).onClick(this.mDialog.get(), message.what);
            } else if (i == 1) {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int i, int i2, CharSequence[] charSequenceArr) {
            super(context, i, i2, (Object[]) null);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class RecycleListView extends ListView {
        public final int mPaddingBottomNoButtons;
        public final int mPaddingTopNoTitle;

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecycleListView);
            this.mPaddingBottomNoButtons = obtainStyledAttributes.getDimensionPixelOffset(0, -1);
            this.mPaddingTopNoTitle = obtainStyledAttributes.getDimensionPixelOffset(1, -1);
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(appCompatDialog);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = obtainStyledAttributes.getResourceId(0, 0);
        this.mButtonPanelSideLayout = obtainStyledAttributes.getResourceId(2, 0);
        this.mListLayout = obtainStyledAttributes.getResourceId(4, 0);
        this.mMultiChoiceItemLayout = obtainStyledAttributes.getResourceId(5, 0);
        this.mSingleChoiceItemLayout = obtainStyledAttributes.getResourceId(7, 0);
        this.mListItemLayout = obtainStyledAttributes.getResourceId(3, 0);
        this.mShowTitle = obtainStyledAttributes.getBoolean(6, true);
        this.mButtonIconDimen = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        appCompatDialog.getDelegate().requestWindowFeature(1);
    }

    public static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (canTextInput(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public static void manageScrollIndicators(View view, View view2, View view3) {
        int i = 4;
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            if (view.canScrollVertically(1)) {
                i = 0;
            }
            view3.setVisibility(i);
        }
    }

    public final void centerButton(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    public final ViewGroup resolvePanel(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message, Drawable drawable) {
        Message obtainMessage = onClickListener != null ? this.mHandler.obtainMessage(i, onClickListener) : null;
        if (i == -3) {
            this.mButtonNeutralText = charSequence;
            this.mButtonNeutralMessage = obtainMessage;
            this.mButtonNeutralIcon = null;
        } else if (i == -2) {
            this.mButtonNegativeText = charSequence;
            this.mButtonNegativeMessage = obtainMessage;
            this.mButtonNegativeIcon = null;
        } else if (i == -1) {
            this.mButtonPositiveText = charSequence;
            this.mButtonPositiveMessage = obtainMessage;
            this.mButtonPositiveIcon = null;
        } else {
            throw new IllegalArgumentException("Button does not exist");
        }
    }
}

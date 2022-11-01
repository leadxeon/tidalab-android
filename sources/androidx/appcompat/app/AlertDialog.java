package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    public final AlertController mAlert = new AlertController(getContext(), this, getWindow());

    public AlertDialog(Context context, int i) {
        super(context, resolveDialogTheme(context, i));
    }

    public static int resolveDialogTheme(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i) {
        AlertController alertController = this.mAlert;
        Objects.requireNonNull(alertController);
        if (i == -3) {
            return alertController.mButtonNeutral;
        }
        if (i == -2) {
            return alertController.mButtonNegative;
        }
        if (i != -1) {
            return null;
        }
        return alertController.mButtonPositive;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        int i;
        boolean z;
        View view;
        ListAdapter listAdapter;
        final View view2;
        View findViewById;
        super.onCreate(bundle);
        final AlertController alertController = this.mAlert;
        if (alertController.mButtonPanelSideLayout == 0) {
            i = alertController.mAlertDialogLayout;
        } else {
            i = alertController.mAlertDialogLayout;
        }
        alertController.mDialog.setContentView(i);
        View findViewById2 = alertController.mWindow.findViewById(R.id.parentPanel);
        View findViewById3 = findViewById2.findViewById(R.id.topPanel);
        View findViewById4 = findViewById2.findViewById(R.id.contentPanel);
        View findViewById5 = findViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(R.id.customPanel);
        View view3 = alertController.mView;
        int i2 = 0;
        if (view3 == null) {
            view3 = alertController.mViewLayoutResId != 0 ? LayoutInflater.from(alertController.mContext).inflate(alertController.mViewLayoutResId, viewGroup, false) : null;
        }
        boolean z2 = view3 != null;
        if (!z2 || !AlertController.canTextInput(view3)) {
            alertController.mWindow.setFlags(131072, 131072);
        }
        if (z2) {
            FrameLayout frameLayout = (FrameLayout) alertController.mWindow.findViewById(R.id.custom);
            frameLayout.addView(view3, new ViewGroup.LayoutParams(-1, -1));
            if (alertController.mViewSpacingSpecified) {
                frameLayout.setPadding(alertController.mViewSpacingLeft, alertController.mViewSpacingTop, alertController.mViewSpacingRight, alertController.mViewSpacingBottom);
            }
            if (alertController.mListView != null) {
                ((LinearLayout.LayoutParams) ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams())).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById6 = viewGroup.findViewById(R.id.topPanel);
        View findViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View findViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup resolvePanel = alertController.resolvePanel(findViewById6, findViewById3);
        ViewGroup resolvePanel2 = alertController.resolvePanel(findViewById7, findViewById4);
        ViewGroup resolvePanel3 = alertController.resolvePanel(findViewById8, findViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) alertController.mWindow.findViewById(R.id.scrollView);
        alertController.mScrollView = nestedScrollView;
        nestedScrollView.setFocusable(false);
        alertController.mScrollView.setNestedScrollingEnabled(false);
        TextView textView = (TextView) resolvePanel2.findViewById(16908299);
        alertController.mMessageView = textView;
        if (textView != null) {
            CharSequence charSequence = alertController.mMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                alertController.mScrollView.removeView(alertController.mMessageView);
                if (alertController.mListView != null) {
                    ViewGroup viewGroup2 = (ViewGroup) alertController.mScrollView.getParent();
                    int indexOfChild = viewGroup2.indexOfChild(alertController.mScrollView);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(alertController.mListView, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    resolvePanel2.setVisibility(8);
                }
            }
        }
        Button button = (Button) resolvePanel3.findViewById(16908313);
        alertController.mButtonPositive = button;
        button.setOnClickListener(alertController.mButtonHandler);
        if (!TextUtils.isEmpty(alertController.mButtonPositiveText) || alertController.mButtonPositiveIcon != null) {
            alertController.mButtonPositive.setText(alertController.mButtonPositiveText);
            Drawable drawable = alertController.mButtonPositiveIcon;
            if (drawable != null) {
                int i3 = alertController.mButtonIconDimen;
                drawable.setBounds(0, 0, i3, i3);
                alertController.mButtonPositive.setCompoundDrawables(alertController.mButtonPositiveIcon, null, null, null);
            }
            alertController.mButtonPositive.setVisibility(0);
            z = true;
        } else {
            alertController.mButtonPositive.setVisibility(8);
            z = false;
        }
        Button button2 = (Button) resolvePanel3.findViewById(16908314);
        alertController.mButtonNegative = button2;
        button2.setOnClickListener(alertController.mButtonHandler);
        if (!TextUtils.isEmpty(alertController.mButtonNegativeText) || alertController.mButtonNegativeIcon != null) {
            alertController.mButtonNegative.setText(alertController.mButtonNegativeText);
            Drawable drawable2 = alertController.mButtonNegativeIcon;
            if (drawable2 != null) {
                int i4 = alertController.mButtonIconDimen;
                drawable2.setBounds(0, 0, i4, i4);
                alertController.mButtonNegative.setCompoundDrawables(alertController.mButtonNegativeIcon, null, null, null);
            }
            alertController.mButtonNegative.setVisibility(0);
            z |= true;
        } else {
            alertController.mButtonNegative.setVisibility(8);
        }
        Button button3 = (Button) resolvePanel3.findViewById(16908315);
        alertController.mButtonNeutral = button3;
        button3.setOnClickListener(alertController.mButtonHandler);
        if (!TextUtils.isEmpty(alertController.mButtonNeutralText) || alertController.mButtonNeutralIcon != null) {
            alertController.mButtonNeutral.setText(alertController.mButtonNeutralText);
            Drawable drawable3 = alertController.mButtonNeutralIcon;
            if (drawable3 != null) {
                int i5 = alertController.mButtonIconDimen;
                drawable3.setBounds(0, 0, i5, i5);
                view = null;
                alertController.mButtonNeutral.setCompoundDrawables(alertController.mButtonNeutralIcon, null, null, null);
            } else {
                view = null;
            }
            alertController.mButtonNeutral.setVisibility(0);
            z |= true;
        } else {
            alertController.mButtonNeutral.setVisibility(8);
            view = null;
        }
        Context context = alertController.mContext;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (z) {
                alertController.centerButton(alertController.mButtonPositive);
            } else if (z) {
                alertController.centerButton(alertController.mButtonNegative);
            } else if (z) {
                alertController.centerButton(alertController.mButtonNeutral);
            }
        }
        if (!(z)) {
            resolvePanel3.setVisibility(8);
        }
        if (alertController.mCustomTitleView != null) {
            resolvePanel.addView(alertController.mCustomTitleView, 0, new ViewGroup.LayoutParams(-1, -2));
            alertController.mWindow.findViewById(R.id.title_template).setVisibility(8);
        } else {
            alertController.mIconView = (ImageView) alertController.mWindow.findViewById(16908294);
            if (!(!TextUtils.isEmpty(alertController.mTitle)) || !alertController.mShowTitle) {
                alertController.mWindow.findViewById(R.id.title_template).setVisibility(8);
                alertController.mIconView.setVisibility(8);
                resolvePanel.setVisibility(8);
            } else {
                TextView textView2 = (TextView) alertController.mWindow.findViewById(R.id.alertTitle);
                alertController.mTitleView = textView2;
                textView2.setText(alertController.mTitle);
                int i6 = alertController.mIconId;
                if (i6 != 0) {
                    alertController.mIconView.setImageResource(i6);
                } else {
                    Drawable drawable4 = alertController.mIcon;
                    if (drawable4 != null) {
                        alertController.mIconView.setImageDrawable(drawable4);
                    } else {
                        alertController.mTitleView.setPadding(alertController.mIconView.getPaddingLeft(), alertController.mIconView.getPaddingTop(), alertController.mIconView.getPaddingRight(), alertController.mIconView.getPaddingBottom());
                        alertController.mIconView.setVisibility(8);
                    }
                }
            }
        }
        boolean z3 = viewGroup.getVisibility() != 8;
        int i7 = (resolvePanel == null || resolvePanel.getVisibility() == 8) ? 0 : 1;
        boolean z4 = resolvePanel3.getVisibility() != 8;
        if (!z4 && (findViewById = resolvePanel2.findViewById(R.id.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (i7 != 0) {
            NestedScrollView nestedScrollView2 = alertController.mScrollView;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View findViewById9 = (alertController.mMessage == null && alertController.mListView == null) ? view : resolvePanel.findViewById(R.id.titleDividerNoCustom);
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        } else {
            View findViewById10 = resolvePanel2.findViewById(R.id.textSpacerNoTitle);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        }
        ListView listView = alertController.mListView;
        if (listView instanceof AlertController.RecycleListView) {
            AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) listView;
            Objects.requireNonNull(recycleListView);
            if (!z4 || i7 == 0) {
                recycleListView.setPadding(recycleListView.getPaddingLeft(), i7 != 0 ? recycleListView.getPaddingTop() : recycleListView.mPaddingTopNoTitle, recycleListView.getPaddingRight(), z4 ? recycleListView.getPaddingBottom() : recycleListView.mPaddingBottomNoButtons);
            }
        }
        if (!z3) {
            View view4 = alertController.mListView;
            if (view4 == null) {
                view4 = alertController.mScrollView;
            }
            if (view4 != null) {
                if (z4) {
                    i2 = 2;
                }
                int i8 = i7 | i2;
                final View findViewById11 = alertController.mWindow.findViewById(R.id.scrollIndicatorUp);
                View findViewById12 = alertController.mWindow.findViewById(R.id.scrollIndicatorDown);
                int i9 = Build.VERSION.SDK_INT;
                if (i9 >= 23) {
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    if (i9 >= 23) {
                        view4.setScrollIndicators(i8, 3);
                    }
                    if (findViewById11 != null) {
                        resolvePanel2.removeView(findViewById11);
                    }
                    if (findViewById12 != null) {
                        resolvePanel2.removeView(findViewById12);
                    }
                } else {
                    if (findViewById11 != null && (i8 & 1) == 0) {
                        resolvePanel2.removeView(findViewById11);
                        findViewById11 = view;
                    }
                    if (findViewById12 == null || (i8 & 2) != 0) {
                        view2 = findViewById12;
                    } else {
                        resolvePanel2.removeView(findViewById12);
                        view2 = view;
                    }
                    if (!(findViewById11 == null && view2 == null)) {
                        if (alertController.mMessage != null) {
                            alertController.mScrollView.setOnScrollChangeListener(new AlertController.AnonymousClass2(alertController, findViewById11, view2));
                            alertController.mScrollView.post(new Runnable() { // from class: androidx.appcompat.app.AlertController.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    AlertController.manageScrollIndicators(AlertController.this.mScrollView, findViewById11, view2);
                                }
                            });
                        } else {
                            ListView listView2 = alertController.mListView;
                            if (listView2 != null) {
                                listView2.setOnScrollListener(new AbsListView.OnScrollListener(alertController, findViewById11, view2) { // from class: androidx.appcompat.app.AlertController.4
                                    public final /* synthetic */ View val$bottom;
                                    public final /* synthetic */ View val$top;

                                    {
                                        this.val$top = findViewById11;
                                        this.val$bottom = view2;
                                    }

                                    @Override // android.widget.AbsListView.OnScrollListener
                                    public void onScroll(AbsListView absListView, int i10, int i11, int i12) {
                                        AlertController.manageScrollIndicators(absListView, this.val$top, this.val$bottom);
                                    }

                                    @Override // android.widget.AbsListView.OnScrollListener
                                    public void onScrollStateChanged(AbsListView absListView, int i10) {
                                    }
                                });
                                alertController.mListView.post(new Runnable() { // from class: androidx.appcompat.app.AlertController.5
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        AlertController.manageScrollIndicators(AlertController.this.mListView, findViewById11, view2);
                                    }
                                });
                            } else {
                                if (findViewById11 != null) {
                                    resolvePanel2.removeView(findViewById11);
                                }
                                if (view2 != null) {
                                    resolvePanel2.removeView(view2);
                                }
                            }
                        }
                    }
                }
            }
        }
        ListView listView3 = alertController.mListView;
        if (listView3 != null && (listAdapter = alertController.mAdapter) != null) {
            listView3.setAdapter(listAdapter);
            int i10 = alertController.mCheckedItem;
            if (i10 > -1) {
                listView3.setItemChecked(i10, true);
                listView3.setSelection(i10);
            }
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView != null && nestedScrollView.executeKeyEvent(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AlertController alertController = this.mAlert;
        alertController.mTitle = charSequence;
        TextView textView = alertController.mTitleView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        public final AlertController.AlertParams P;
        public final int mTheme;

        public Builder(Context context) {
            int resolveDialogTheme = AlertDialog.resolveDialogTheme(context, 0);
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, resolveDialogTheme)));
            this.mTheme = resolveDialogTheme;
        }

        public AlertDialog create() {
            int i;
            AlertDialog alertDialog = new AlertDialog(this.P.mContext, this.mTheme);
            final AlertController.AlertParams alertParams = this.P;
            final AlertController alertController = alertDialog.mAlert;
            View view = alertParams.mCustomTitleView;
            if (view != null) {
                alertController.mCustomTitleView = view;
            } else {
                CharSequence charSequence = alertParams.mTitle;
                if (charSequence != null) {
                    alertController.mTitle = charSequence;
                    TextView textView = alertController.mTitleView;
                    if (textView != null) {
                        textView.setText(charSequence);
                    }
                }
                Drawable drawable = alertParams.mIcon;
                if (drawable != null) {
                    alertController.mIcon = drawable;
                    alertController.mIconId = 0;
                    ImageView imageView = alertController.mIconView;
                    if (imageView != null) {
                        imageView.setVisibility(0);
                        alertController.mIconView.setImageDrawable(drawable);
                    }
                }
            }
            CharSequence charSequence2 = alertParams.mMessage;
            if (charSequence2 != null) {
                alertController.mMessage = charSequence2;
                TextView textView2 = alertController.mMessageView;
                if (textView2 != null) {
                    textView2.setText(charSequence2);
                }
            }
            CharSequence charSequence3 = alertParams.mPositiveButtonText;
            if (charSequence3 != null) {
                alertController.setButton(-1, charSequence3, alertParams.mPositiveButtonListener, null, null);
            }
            CharSequence charSequence4 = alertParams.mNegativeButtonText;
            if (charSequence4 != null) {
                alertController.setButton(-2, charSequence4, alertParams.mNegativeButtonListener, null, null);
            }
            CharSequence charSequence5 = alertParams.mNeutralButtonText;
            if (charSequence5 != null) {
                alertController.setButton(-3, charSequence5, alertParams.mNeutralButtonListener, null, null);
            }
            if (alertParams.mAdapter != null) {
                AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) alertParams.mInflater.inflate(alertController.mListLayout, (ViewGroup) null);
                if (alertParams.mIsSingleChoice) {
                    i = alertController.mSingleChoiceItemLayout;
                } else {
                    i = alertController.mListItemLayout;
                }
                ListAdapter listAdapter = alertParams.mAdapter;
                if (listAdapter == null) {
                    listAdapter = new AlertController.CheckedItemAdapter(alertParams.mContext, i, 16908308, null);
                }
                alertController.mAdapter = listAdapter;
                alertController.mCheckedItem = alertParams.mCheckedItem;
                if (alertParams.mOnClickListener != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.3
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public void onItemClick(AdapterView<?> adapterView, View view2, int i2, long j) {
                            AlertParams.this.mOnClickListener.onClick(alertController.mDialog, i2);
                            if (!AlertParams.this.mIsSingleChoice) {
                                alertController.mDialog.dismiss();
                            }
                        }
                    });
                }
                if (alertParams.mIsSingleChoice) {
                    recycleListView.setChoiceMode(1);
                }
                alertController.mListView = recycleListView;
            }
            View view2 = alertParams.mView;
            if (view2 != null) {
                alertController.mView = view2;
                alertController.mViewLayoutResId = 0;
                alertController.mViewSpacingSpecified = false;
            }
            alertDialog.setCancelable(this.P.mCancelable);
            if (this.P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            Objects.requireNonNull(this.P);
            alertDialog.setOnCancelListener(null);
            alertDialog.setOnDismissListener(this.P.mOnDismissListener);
            DialogInterface.OnKeyListener onKeyListener = this.P.mOnKeyListener;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public AlertDialog show() {
            AlertDialog create = create();
            create.show();
            return create;
        }

        public Builder(Context context, int i) {
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
            this.mTheme = i;
        }
    }
}

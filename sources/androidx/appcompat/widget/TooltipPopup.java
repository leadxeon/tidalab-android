package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class TooltipPopup {
    public final View mContentView;
    public final Context mContext;
    public final WindowManager.LayoutParams mLayoutParams;
    public final TextView mMessageView;
    public final Rect mTmpDisplayFrame = new Rect();
    public final int[] mTmpAnchorPos = new int[2];
    public final int[] mTmpAppPos = new int[2];

    public TooltipPopup(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.abc_tooltip, (ViewGroup) null);
        this.mContentView = inflate;
        this.mMessageView = (TextView) inflate.findViewById(R.id.message);
        layoutParams.setTitle(TooltipPopup.class.getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = RNCWebViewManager.COMMAND_CLEAR_HISTORY;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 2131886085;
        layoutParams.flags = 24;
    }

    public void hide() {
        if (this.mContentView.getParent() != null) {
            ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
        }
    }
}

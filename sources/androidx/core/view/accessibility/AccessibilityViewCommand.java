package androidx.core.view.accessibility;

import android.view.View;
/* loaded from: classes.dex */
public interface AccessibilityViewCommand {

    /* loaded from: classes.dex */
    public static abstract class CommandArguments {
    }

    /* loaded from: classes.dex */
    public static final class SetProgressArguments extends CommandArguments {
    }

    boolean perform(View view, CommandArguments commandArguments);
}

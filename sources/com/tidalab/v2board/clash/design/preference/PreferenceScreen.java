package com.tidalab.v2board.clash.design.preference;

import android.content.Context;
import android.view.ViewGroup;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: Screen.kt */
/* loaded from: classes.dex */
public interface PreferenceScreen extends CoroutineScope {
    Context getContext();

    ViewGroup getRoot();
}

package com.tidalab.v2board.clash;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.model.ProfileProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NewProfileActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NewProfileActivity$queryProfileProviders$2", f = "NewProfileActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NewProfileActivity$queryProfileProviders$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ProfileProvider>>, Object> {
    public final /* synthetic */ NewProfileActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewProfileActivity$queryProfileProviders$2(NewProfileActivity newProfileActivity, Continuation<? super NewProfileActivity$queryProfileProviders$2> continuation) {
        super(2, continuation);
        this.this$0 = newProfileActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NewProfileActivity$queryProfileProviders$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ProfileProvider>> continuation) {
        return new NewProfileActivity$queryProfileProviders$2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        PackageManager packageManager = this.this$0.getPackageManager();
        Intents intents = Intents.INSTANCE;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent(Intents.ACTION_PROVIDE_URL), 0);
        NewProfileActivity newProfileActivity = this.this$0;
        ArrayList arrayList = new ArrayList(InputKt.collectionSizeOrDefault(queryIntentActivities, 10));
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            CharSequence loadLabel = activityInfo.applicationInfo.loadLabel(newProfileActivity.getPackageManager());
            CharSequence loadLabel2 = activityInfo.loadLabel(newProfileActivity.getPackageManager());
            Drawable loadIcon = activityInfo.loadIcon(newProfileActivity.getPackageManager());
            Intents intents2 = Intents.INSTANCE;
            arrayList.add(new ProfileProvider.External(loadLabel.toString(), loadLabel2.toString(), loadIcon, new Intent(Intents.ACTION_PROVIDE_URL).setComponent(new ComponentName(activityInfo.packageName, activityInfo.name))));
        }
        NewProfileActivity newProfileActivity2 = this.this$0;
        Objects.requireNonNull(newProfileActivity2);
        NewProfileActivity newProfileActivity3 = this.this$0;
        Objects.requireNonNull(newProfileActivity3);
        return ArraysKt___ArraysKt.plus(ArraysKt___ArraysKt.listOf(new ProfileProvider.File(newProfileActivity2), new ProfileProvider.Url(newProfileActivity3)), arrayList);
    }
}

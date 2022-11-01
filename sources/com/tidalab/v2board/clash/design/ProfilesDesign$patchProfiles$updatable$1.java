package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfilesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$updatable$1", f = "ProfilesDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesDesign$patchProfiles$updatable$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public final /* synthetic */ List<Profile> $profiles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesDesign$patchProfiles$updatable$1(List<Profile> list, Continuation<? super ProfilesDesign$patchProfiles$updatable$1> continuation) {
        super(2, continuation);
        this.$profiles = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfilesDesign$patchProfiles$updatable$1(this.$profiles, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        Continuation<? super Boolean> continuation2 = continuation;
        List<Profile> list = this.$profiles;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        boolean z = false;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Profile profile = (Profile) it.next();
                if (Boolean.valueOf(profile.imported && profile.type != Profile.Type.File).booleanValue()) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        List<Profile> list = this.$profiles;
        boolean z = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (Profile profile : list) {
                if (Boolean.valueOf(profile.imported && profile.type != Profile.Type.File).booleanValue()) {
                    break;
                }
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}

package com.tidalab.v2board.clash;

import android.content.Intent;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.ProfilesDesign$requestSave$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.design.ui.ToastDuration;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: ProfilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2", f = "ProfilesActivity.kt", l = {36, 43, 45, 49, 57}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesActivity$main$2$2 extends SuspendLambda implements Function2<ProfilesDesign.Request, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProfilesDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ProfilesActivity this$0;

    /* compiled from: ProfilesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2$1", f = "ProfilesActivity.kt", l = {37, 39}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProfilesActivity$main$2$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public /* synthetic */ Object L$0;
        public Object L$1;
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L_0x0028
                if (r1 == r3) goto L_0x0020
                if (r1 != r2) goto L_0x0018
                java.lang.Object r1 = r7.L$1
                java.util.Iterator r1 = (java.util.Iterator) r1
                java.lang.Object r3 = r7.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r3 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r3
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
                goto L_0x0043
            L_0x0018:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L_0x0020:
                java.lang.Object r1 = r7.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
                goto L_0x003b
            L_0x0028:
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                r1 = r8
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                r7.L$0 = r1
                r7.label = r3
                java.lang.Object r8 = r1.queryAll(r7)
                if (r8 != r0) goto L_0x003b
                return r0
            L_0x003b:
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.Iterator r8 = r8.iterator()
                r3 = r1
                r1 = r8
            L_0x0043:
                r8 = r7
            L_0x0044:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L_0x0069
                java.lang.Object r4 = r1.next()
                com.tidalab.v2board.clash.service.model.Profile r4 = (com.tidalab.v2board.clash.service.model.Profile) r4
                boolean r5 = r4.imported
                if (r5 == 0) goto L_0x0044
                com.tidalab.v2board.clash.service.model.Profile$Type r5 = r4.type
                com.tidalab.v2board.clash.service.model.Profile$Type r6 = com.tidalab.v2board.clash.service.model.Profile.Type.File
                if (r5 == r6) goto L_0x0044
                java.util.UUID r4 = r4.uuid
                r8.L$0 = r3
                r8.L$1 = r1
                r8.label = r2
                java.lang.Object r4 = r3.update(r4, r8)
                if (r4 != r0) goto L_0x0044
                return r0
            L_0x0069:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.ProfilesActivity$main$2$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: ProfilesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2$2", f = "ProfilesActivity.kt", l = {43}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProfilesActivity$main$2$2$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProfilesDesign.Request $it;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ProfilesDesign.Request request, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$it = request;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$it, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$it, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                UUID uuid = ((ProfilesDesign.Request.Update) this.$it).profile.uuid;
                this.label = 1;
                if (((IProfileManager) this.L$0).update(uuid, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ProfilesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2$3", f = "ProfilesActivity.kt", l = {45}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProfilesActivity$main$2$2$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProfilesDesign.Request $it;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(ProfilesDesign.Request request, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$it = request;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 r0 = new AnonymousClass3(this.$it, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass3 r0 = new AnonymousClass3(this.$it, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                UUID uuid = ((ProfilesDesign.Request.Delete) this.$it).profile.uuid;
                this.label = 1;
                if (((IProfileManager) this.L$0).delete(uuid, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: ProfilesActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2$4", f = "ProfilesActivity.kt", l = {51, 53}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.ProfilesActivity$main$2$2$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ProfilesDesign $design;
        public final /* synthetic */ ProfilesDesign.Request $it;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(ProfilesDesign.Request request, ProfilesDesign profilesDesign, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$it = request;
            this.$design = profilesDesign;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 r0 = new AnonymousClass4(this.$it, this.$design, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass4 r0 = new AnonymousClass4(this.$it, this.$design, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                IProfileManager iProfileManager = (IProfileManager) this.L$0;
                ProfilesDesign.Request request = this.$it;
                if (((ProfilesDesign.Request.Active) request).profile.imported) {
                    Profile profile = ((ProfilesDesign.Request.Active) request).profile;
                    this.label = 1;
                    if (iProfileManager.setActive(profile, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    ProfilesDesign profilesDesign = this.$design;
                    Profile profile2 = ((ProfilesDesign.Request.Active) request).profile;
                    this.label = 2;
                    Object showToast = profilesDesign.showToast(R.string.active_unsaved_tips, ToastDuration.Long, new ProfilesDesign$requestSave$2(profilesDesign, profile2), this);
                    if (showToast != coroutineSingletons) {
                        showToast = Unit.INSTANCE;
                    }
                    if (showToast == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            } else if (i == 1 || i == 2) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesActivity$main$2$2(ProfilesActivity profilesActivity, ProfilesDesign profilesDesign, Continuation<? super ProfilesActivity$main$2$2> continuation) {
        super(2, continuation);
        this.this$0 = profilesActivity;
        this.$design = profilesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProfilesActivity$main$2$2 profilesActivity$main$2$2 = new ProfilesActivity$main$2$2(this.this$0, this.$design, continuation);
        profilesActivity$main$2$2.L$0 = obj;
        return profilesActivity$main$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ProfilesDesign.Request request, Continuation<? super Unit> continuation) {
        ProfilesActivity$main$2$2 profilesActivity$main$2$2 = new ProfilesActivity$main$2$2(this.this$0, this.$design, continuation);
        profilesActivity$main$2$2.L$0 = request;
        return profilesActivity$main$2$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            ProfilesDesign.Request request = (ProfilesDesign.Request) this.L$0;
            if (Intrinsics.areEqual(request, ProfilesDesign.Request.Create.INSTANCE)) {
                this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(NewProfileActivity.class)));
            } else if (Intrinsics.areEqual(request, ProfilesDesign.Request.UpdateAll.INSTANCE)) {
                AnonymousClass1 r9 = new AnonymousClass1(null);
                this.label = 1;
                if (InputKt.withProfile$default(null, r9, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof ProfilesDesign.Request.Update) {
                AnonymousClass2 r1 = new AnonymousClass2(request, null);
                this.label = 2;
                if (InputKt.withProfile$default(null, r1, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof ProfilesDesign.Request.Delete) {
                AnonymousClass3 r12 = new AnonymousClass3(request, null);
                this.label = 3;
                if (InputKt.withProfile$default(null, r12, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof ProfilesDesign.Request.Edit) {
                ProfilesActivity profilesActivity = this.this$0;
                Intent intent = PathParser.getIntent(Reflection.getOrCreateKotlinClass(PropertiesActivity.class));
                PathParser.setUUID(intent, ((ProfilesDesign.Request.Edit) request).profile.uuid);
                profilesActivity.startActivity(intent);
            } else if (request instanceof ProfilesDesign.Request.Active) {
                AnonymousClass4 r13 = new AnonymousClass4(request, this.$design, null);
                this.label = 4;
                if (InputKt.withProfile$default(null, r13, this, 1) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (request instanceof ProfilesDesign.Request.Duplicate) {
                ProfilesActivity$main$2$2$uuid$1 profilesActivity$main$2$2$uuid$1 = new ProfilesActivity$main$2$2$uuid$1(request, null);
                this.label = 5;
                obj = InputKt.withProfile$default(null, profilesActivity$main$2$2$uuid$1, this, 1);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
                ProfilesActivity profilesActivity2 = this.this$0;
                Intent intent2 = PathParser.getIntent(Reflection.getOrCreateKotlinClass(PropertiesActivity.class));
                PathParser.setUUID(intent2, (UUID) obj);
                profilesActivity2.startActivity(intent2);
            }
            return Unit.INSTANCE;
        } else if (i == 1 || i == 2 || i == 3 || i == 4) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else if (i == 5) {
            InputKt.throwOnFailure(obj);
            ProfilesActivity profilesActivity22 = this.this$0;
            Intent intent22 = PathParser.getIntent(Reflection.getOrCreateKotlinClass(PropertiesActivity.class));
            PathParser.setUUID(intent22, (UUID) obj);
            profilesActivity22.startActivity(intent22);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

package com.tidalab.v2board.clash;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.AppSettingsDesign;
import com.tidalab.v2board.clash.design.model.Behavior;
import kotlin.jvm.internal.Reflection;
/* compiled from: AppSettingsActivity.kt */
/* loaded from: classes.dex */
public final class AppSettingsActivity extends BaseActivity<AppSettingsDesign> implements Behavior {
    @Override // com.tidalab.v2board.clash.design.model.Behavior
    public boolean getAutoRestart() {
        return getPackageManager().getComponentEnabledSetting(PathParser.getComponentName(Reflection.getOrCreateKotlinClass(RestartReceiver.class))) == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0067  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            boolean r1 = r12 instanceof com.tidalab.v2board.clash.AppSettingsActivity$main$1
            if (r1 == 0) goto L_0x0015
            r1 = r12
            com.tidalab.v2board.clash.AppSettingsActivity$main$1 r1 = (com.tidalab.v2board.clash.AppSettingsActivity$main$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.label = r2
            goto L_0x001a
        L_0x0015:
            com.tidalab.v2board.clash.AppSettingsActivity$main$1 r1 = new com.tidalab.v2board.clash.AppSettingsActivity$main$1
            r1.<init>(r11, r12)
        L_0x001a:
            java.lang.Object r12 = r1.result
            int r2 = r1.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 == r4) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            goto L_0x002f
        L_0x0027:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x002f:
            java.lang.Object r2 = r1.L$1
            com.tidalab.v2board.clash.design.AppSettingsDesign r2 = (com.tidalab.v2board.clash.design.AppSettingsDesign) r2
            java.lang.Object r4 = r1.L$0
            com.tidalab.v2board.clash.AppSettingsActivity r4 = (com.tidalab.v2board.clash.AppSettingsActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            goto L_0x0061
        L_0x003b:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            com.tidalab.v2board.clash.design.AppSettingsDesign r2 = new com.tidalab.v2board.clash.design.AppSettingsDesign
            com.tidalab.v2board.clash.design.store.UiStore r7 = r11.getUiStore()
            com.tidalab.v2board.clash.service.store.ServiceStore r8 = new com.tidalab.v2board.clash.service.store.ServiceStore
            r8.<init>(r11)
            boolean r10 = r11.getClashRunning()
            r5 = r2
            r6 = r11
            r9 = r11
            r5.<init>(r6, r7, r8, r9, r10)
            r1.L$0 = r11
            r1.L$1 = r2
            r1.label = r4
            java.lang.Object r12 = r11.setContentDesign(r2, r1)
            if (r12 != r0) goto L_0x0060
            return r0
        L_0x0060:
            r4 = r11
        L_0x0061:
            boolean r12 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r4)
            if (r12 == 0) goto L_0x009b
            r1.L$0 = r4
            r1.L$1 = r2
            r1.label = r3
            kotlinx.coroutines.selects.SelectBuilderImpl r12 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r12.<init>(r1)
            kotlinx.coroutines.channels.Channel<com.tidalab.v2board.clash.BaseActivity$Event> r5 = r4.events     // Catch: all -> 0x0090
            kotlinx.coroutines.selects.SelectClause1 r5 = r5.getOnReceive()     // Catch: all -> 0x0090
            com.tidalab.v2board.clash.AppSettingsActivity$main$2$1 r6 = new com.tidalab.v2board.clash.AppSettingsActivity$main$2$1     // Catch: all -> 0x0090
            r7 = 0
            r6.<init>(r4, r7)     // Catch: all -> 0x0090
            r5.registerSelectClause1(r12, r6)     // Catch: all -> 0x0090
            kotlinx.coroutines.channels.Channel<R> r5 = r2.requests     // Catch: all -> 0x0090
            kotlinx.coroutines.selects.SelectClause1 r5 = r5.getOnReceive()     // Catch: all -> 0x0090
            com.tidalab.v2board.clash.AppSettingsActivity$main$2$2 r6 = new com.tidalab.v2board.clash.AppSettingsActivity$main$2$2     // Catch: all -> 0x0090
            r6.<init>(r7)     // Catch: all -> 0x0090
            r5.registerSelectClause1(r12, r6)     // Catch: all -> 0x0090
            goto L_0x0094
        L_0x0090:
            r5 = move-exception
            r12.handleBuilderException(r5)
        L_0x0094:
            java.lang.Object r12 = r12.getResult()
            if (r12 != r0) goto L_0x0061
            return r0
        L_0x009b:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.AppSettingsActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.design.model.Behavior
    public void setAutoRestart(boolean z) {
        getPackageManager().setComponentEnabledSetting(PathParser.getComponentName(Reflection.getOrCreateKotlinClass(RestartReceiver.class)), z ? 1 : 2, 1);
    }
}

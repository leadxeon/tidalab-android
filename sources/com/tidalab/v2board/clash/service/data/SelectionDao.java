package com.tidalab.v2board.clash.service.data;

import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: SelectionDao.kt */
/* loaded from: classes.dex */
public interface SelectionDao {
    Object querySelections(UUID uuid, Continuation<? super List<Selection>> continuation);

    void removeSelected(UUID uuid, String str);

    Object removeSelections(UUID uuid, List<String> list, Continuation<? super Unit> continuation);

    void setSelected(Selection selection);
}

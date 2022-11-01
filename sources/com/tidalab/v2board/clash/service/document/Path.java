package com.tidalab.v2board.clash.service.document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Path.kt */
/* loaded from: classes.dex */
public final class Path {
    public final List<String> relative;
    public final Scope scope;
    public final UUID uuid;

    /* compiled from: Path.kt */
    /* loaded from: classes.dex */
    public enum Scope {
        Configuration,
        Providers
    }

    /* compiled from: Path.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = {1, 2};

        static {
            Scope.values();
        }
    }

    public Path(UUID uuid, Scope scope, List<String> list) {
        this.uuid = uuid;
        this.scope = scope;
        this.relative = list;
    }

    public static Path copy$default(Path path, UUID uuid, Scope scope, List list, int i) {
        if ((i & 1) != 0) {
            uuid = path.uuid;
        }
        if ((i & 2) != 0) {
            scope = path.scope;
        }
        if ((i & 4) != 0) {
            list = path.relative;
        }
        Objects.requireNonNull(path);
        return new Path(uuid, scope, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Path)) {
            return false;
        }
        Path path = (Path) obj;
        return Intrinsics.areEqual(this.uuid, path.uuid) && this.scope == path.scope && Intrinsics.areEqual(this.relative, path.relative);
    }

    public int hashCode() {
        UUID uuid = this.uuid;
        int i = 0;
        int hashCode = (uuid == null ? 0 : uuid.hashCode()) * 31;
        Scope scope = this.scope;
        int hashCode2 = (hashCode + (scope == null ? 0 : scope.hashCode())) * 31;
        List<String> list = this.relative;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        String str;
        UUID uuid = this.uuid;
        if (uuid == null) {
            return "/";
        }
        Scope scope = this.scope;
        if (scope == null) {
            return Intrinsics.stringPlus("/", uuid);
        }
        int i = scope == null ? -1 : WhenMappings.$EnumSwitchMapping$0[scope.ordinal()];
        if (i == 1) {
            str = "config.yaml";
        } else if (i == 2) {
            str = "providers";
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (this.relative == null) {
            return '/' + this.uuid + '/' + str;
        }
        return '/' + this.uuid + '/' + str + '/' + ArraysKt___ArraysKt.joinToString$default(this.relative, "/", null, null, 0, null, null, 62);
    }
}

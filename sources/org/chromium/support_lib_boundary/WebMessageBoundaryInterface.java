package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
/* loaded from: classes.dex */
public interface WebMessageBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    String getData();

    InvocationHandler[] getPorts();
}

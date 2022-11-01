package com.facebook.react.modules.debug;

import androidx.recyclerview.R$dimen;
import com.facebook.fbreact.specs.NativeSourceCodeSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = SourceCodeModule.NAME)
/* loaded from: classes.dex */
public class SourceCodeModule extends NativeSourceCodeSpec {
    public static final String NAME = "SourceCode";

    public SourceCodeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeSourceCodeSpec
    public Map<String, Object> getTypedExportedConstants() {
        HashMap hashMap = new HashMap();
        String sourceURL = getReactApplicationContext().getCatalystInstance().getSourceURL();
        R$dimen.assertNotNull(sourceURL, "No source URL loaded, have you initialised the instance?");
        hashMap.put("scriptURL", sourceURL);
        return hashMap;
    }
}

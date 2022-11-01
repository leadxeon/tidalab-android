package com.facebook.react.bridge.queue;

import androidx.recyclerview.R$dimen;
/* loaded from: classes.dex */
public class ReactQueueConfigurationSpec {
    private static final long LEGACY_STACK_SIZE_BYTES = 2000000;
    private final MessageQueueThreadSpec mJSQueueThreadSpec;
    private final MessageQueueThreadSpec mNativeModulesQueueThreadSpec;

    /* loaded from: classes.dex */
    public static class Builder {
        private MessageQueueThreadSpec mJSQueueSpec;
        private MessageQueueThreadSpec mNativeModulesQueueSpec;

        public ReactQueueConfigurationSpec build() {
            MessageQueueThreadSpec messageQueueThreadSpec = this.mNativeModulesQueueSpec;
            R$dimen.assertNotNull(messageQueueThreadSpec);
            MessageQueueThreadSpec messageQueueThreadSpec2 = this.mJSQueueSpec;
            R$dimen.assertNotNull(messageQueueThreadSpec2);
            return new ReactQueueConfigurationSpec(messageQueueThreadSpec, messageQueueThreadSpec2);
        }

        public Builder setJSQueueThreadSpec(MessageQueueThreadSpec messageQueueThreadSpec) {
            R$dimen.assertCondition(this.mJSQueueSpec == null, "Setting JS queue multiple times!");
            this.mJSQueueSpec = messageQueueThreadSpec;
            return this;
        }

        public Builder setNativeModulesQueueThreadSpec(MessageQueueThreadSpec messageQueueThreadSpec) {
            R$dimen.assertCondition(this.mNativeModulesQueueSpec == null, "Setting native modules queue spec multiple times!");
            this.mNativeModulesQueueSpec = messageQueueThreadSpec;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ReactQueueConfigurationSpec createDefault() {
        return builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("js")).setNativeModulesQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("native_modules")).build();
    }

    public MessageQueueThreadSpec getJSQueueThreadSpec() {
        return this.mJSQueueThreadSpec;
    }

    public MessageQueueThreadSpec getNativeModulesQueueThreadSpec() {
        return this.mNativeModulesQueueThreadSpec;
    }

    private ReactQueueConfigurationSpec(MessageQueueThreadSpec messageQueueThreadSpec, MessageQueueThreadSpec messageQueueThreadSpec2) {
        this.mNativeModulesQueueThreadSpec = messageQueueThreadSpec;
        this.mJSQueueThreadSpec = messageQueueThreadSpec2;
    }
}

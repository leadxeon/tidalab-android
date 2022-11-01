package com.tidalab.v2board.clash.design;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.design.databinding.AdapterAppBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterEditableTextListBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterEditableTextMapBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterFileBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterLogMessageBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterProfileBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterProfileProviderBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterProviderBindingImpl;
import com.tidalab.v2board.clash.design.databinding.AdapterSideloadProviderBindingImpl;
import com.tidalab.v2board.clash.design.databinding.CommonRecyclerListBindingImpl;
import com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBindingImpl;
import com.tidalab.v2board.clash.design.databinding.ComponentActionTextFieldBindingImpl;
import com.tidalab.v2board.clash.design.databinding.ComponentLargeActionLabelBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignAboutBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignAccessControlBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignAppCrashedBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignFilesBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignLogcatBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignLogsBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignMainBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignNewProfileBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignProfilesBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignPropertiesBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignProvidersBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignProxyBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsCommonBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DesignSettingsOverideBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogEditableMapTextFieldBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogFetchStatusBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogFilesMenuBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogPreferenceListBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogProfilesMenuBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogSearchBindingImpl;
import com.tidalab.v2board.clash.design.databinding.DialogTextFieldBindingImpl;
import com.tidalab.v2board.clash.design.databinding.PreferenceCategoryBindingImpl;
import com.tidalab.v2board.clash.design.databinding.PreferenceClickableBindingImpl;
import com.tidalab.v2board.clash.design.databinding.PreferenceSwitchBindingImpl;
import com.tidalab.v2board.clash.design.databinding.PreferenceTipsBindingImpl;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(39);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.adapter_app, 1);
        sparseIntArray.put(R.layout.adapter_editable_text_list, 2);
        sparseIntArray.put(R.layout.adapter_editable_text_map, 3);
        sparseIntArray.put(R.layout.adapter_file, 4);
        sparseIntArray.put(R.layout.adapter_log_message, 5);
        sparseIntArray.put(R.layout.adapter_profile, 6);
        sparseIntArray.put(R.layout.adapter_profile_provider, 7);
        sparseIntArray.put(R.layout.adapter_provider, 8);
        sparseIntArray.put(R.layout.adapter_sideload_provider, 9);
        sparseIntArray.put(R.layout.common_recycler_list, 10);
        sparseIntArray.put(R.layout.component_action_label, 11);
        sparseIntArray.put(R.layout.component_action_text_field, 12);
        sparseIntArray.put(R.layout.component_large_action_label, 13);
        sparseIntArray.put(R.layout.design_about, 14);
        sparseIntArray.put(R.layout.design_access_control, 15);
        sparseIntArray.put(R.layout.design_app_crashed, 16);
        sparseIntArray.put(R.layout.design_files, 17);
        sparseIntArray.put(R.layout.design_logcat, 18);
        sparseIntArray.put(R.layout.design_logs, 19);
        sparseIntArray.put(R.layout.design_main, 20);
        sparseIntArray.put(R.layout.design_new_profile, 21);
        sparseIntArray.put(R.layout.design_profiles, 22);
        sparseIntArray.put(R.layout.design_properties, 23);
        sparseIntArray.put(R.layout.design_providers, 24);
        sparseIntArray.put(R.layout.design_proxy, 25);
        sparseIntArray.put(R.layout.design_settings, 26);
        sparseIntArray.put(R.layout.design_settings_common, 27);
        sparseIntArray.put(R.layout.design_settings_overide, 28);
        sparseIntArray.put(R.layout.dialog_editable_map_text_field, 29);
        sparseIntArray.put(R.layout.dialog_fetch_status, 30);
        sparseIntArray.put(R.layout.dialog_files_menu, 31);
        sparseIntArray.put(R.layout.dialog_preference_list, 32);
        sparseIntArray.put(R.layout.dialog_profiles_menu, 33);
        sparseIntArray.put(R.layout.dialog_search, 34);
        sparseIntArray.put(R.layout.dialog_text_field, 35);
        sparseIntArray.put(R.layout.preference_category, 36);
        sparseIntArray.put(R.layout.preference_clickable, 37);
        sparseIntArray.put(R.layout.preference_switch, 38);
        sparseIntArray.put(R.layout.preference_tips, 39);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/adapter_app_0".equals(tag)) {
                        return new AdapterAppBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_app is invalid. Received: ", tag));
                case 2:
                    if ("layout/adapter_editable_text_list_0".equals(tag)) {
                        return new AdapterEditableTextListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_editable_text_list is invalid. Received: ", tag));
                case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    if ("layout/adapter_editable_text_map_0".equals(tag)) {
                        return new AdapterEditableTextMapBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_editable_text_map is invalid. Received: ", tag));
                case 4:
                    if ("layout/adapter_file_0".equals(tag)) {
                        return new AdapterFileBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_file is invalid. Received: ", tag));
                case 5:
                    if ("layout/adapter_log_message_0".equals(tag)) {
                        return new AdapterLogMessageBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_log_message is invalid. Received: ", tag));
                case 6:
                    if ("layout/adapter_profile_0".equals(tag)) {
                        return new AdapterProfileBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_profile is invalid. Received: ", tag));
                case 7:
                    if ("layout/adapter_profile_provider_0".equals(tag)) {
                        return new AdapterProfileProviderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_profile_provider is invalid. Received: ", tag));
                case 8:
                    if ("layout/adapter_provider_0".equals(tag)) {
                        return new AdapterProviderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_provider is invalid. Received: ", tag));
                case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                    if ("layout/adapter_sideload_provider_0".equals(tag)) {
                        return new AdapterSideloadProviderBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for adapter_sideload_provider is invalid. Received: ", tag));
                case 10:
                    if ("layout/common_recycler_list_0".equals(tag)) {
                        return new CommonRecyclerListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for common_recycler_list is invalid. Received: ", tag));
                case 11:
                    if ("layout/component_action_label_0".equals(tag)) {
                        return new ComponentActionLabelBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for component_action_label is invalid. Received: ", tag));
                case 12:
                    if ("layout/component_action_text_field_0".equals(tag)) {
                        return new ComponentActionTextFieldBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for component_action_text_field is invalid. Received: ", tag));
                case 13:
                    if ("layout/component_large_action_label_0".equals(tag)) {
                        return new ComponentLargeActionLabelBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for component_large_action_label is invalid. Received: ", tag));
                case 14:
                    if ("layout/design_about_0".equals(tag)) {
                        return new DesignAboutBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_about is invalid. Received: ", tag));
                case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
                    if ("layout/design_access_control_0".equals(tag)) {
                        return new DesignAccessControlBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_access_control is invalid. Received: ", tag));
                case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                    if ("layout/design_app_crashed_0".equals(tag)) {
                        return new DesignAppCrashedBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_app_crashed is invalid. Received: ", tag));
                case 17:
                    if ("layout/design_files_0".equals(tag)) {
                        return new DesignFilesBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_files is invalid. Received: ", tag));
                case 18:
                    if ("layout/design_logcat_0".equals(tag)) {
                        return new DesignLogcatBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_logcat is invalid. Received: ", tag));
                case 19:
                    if ("layout/design_logs_0".equals(tag)) {
                        return new DesignLogsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_logs is invalid. Received: ", tag));
                case 20:
                    if ("layout/design_main_0".equals(tag)) {
                        return new DesignMainBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_main is invalid. Received: ", tag));
                case 21:
                    if ("layout/design_new_profile_0".equals(tag)) {
                        return new DesignNewProfileBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_new_profile is invalid. Received: ", tag));
                case 22:
                    if ("layout/design_profiles_0".equals(tag)) {
                        return new DesignProfilesBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_profiles is invalid. Received: ", tag));
                case 23:
                    if ("layout/design_properties_0".equals(tag)) {
                        return new DesignPropertiesBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_properties is invalid. Received: ", tag));
                case 24:
                    if ("layout/design_providers_0".equals(tag)) {
                        return new DesignProvidersBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_providers is invalid. Received: ", tag));
                case 25:
                    if ("layout/design_proxy_0".equals(tag)) {
                        return new DesignProxyBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_proxy is invalid. Received: ", tag));
                case 26:
                    if ("layout/design_settings_0".equals(tag)) {
                        return new DesignSettingsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_settings is invalid. Received: ", tag));
                case 27:
                    if ("layout/design_settings_common_0".equals(tag)) {
                        return new DesignSettingsCommonBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_settings_common is invalid. Received: ", tag));
                case 28:
                    if ("layout/design_settings_overide_0".equals(tag)) {
                        return new DesignSettingsOverideBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for design_settings_overide is invalid. Received: ", tag));
                case 29:
                    if ("layout/dialog_editable_map_text_field_0".equals(tag)) {
                        return new DialogEditableMapTextFieldBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_editable_map_text_field is invalid. Received: ", tag));
                case 30:
                    if ("layout/dialog_fetch_status_0".equals(tag)) {
                        return new DialogFetchStatusBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_fetch_status is invalid. Received: ", tag));
                case 31:
                    if ("layout/dialog_files_menu_0".equals(tag)) {
                        return new DialogFilesMenuBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_files_menu is invalid. Received: ", tag));
                case WebSocketProtocol.B0_FLAG_RSV2 /* 32 */:
                    if ("layout/dialog_preference_list_0".equals(tag)) {
                        return new DialogPreferenceListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_preference_list is invalid. Received: ", tag));
                case 33:
                    if ("layout/dialog_profiles_menu_0".equals(tag)) {
                        return new DialogProfilesMenuBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_profiles_menu is invalid. Received: ", tag));
                case 34:
                    if ("layout/dialog_search_0".equals(tag)) {
                        return new DialogSearchBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_search is invalid. Received: ", tag));
                case 35:
                    if ("layout/dialog_text_field_0".equals(tag)) {
                        return new DialogTextFieldBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for dialog_text_field is invalid. Received: ", tag));
                case 36:
                    if ("layout/preference_category_0".equals(tag)) {
                        return new PreferenceCategoryBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for preference_category is invalid. Received: ", tag));
                case 37:
                    if ("layout/preference_clickable_0".equals(tag)) {
                        return new PreferenceClickableBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for preference_clickable is invalid. Received: ", tag));
                case 38:
                    if ("layout/preference_switch_0".equals(tag)) {
                        return new PreferenceSwitchBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for preference_switch is invalid. Received: ", tag));
                case 39:
                    if ("layout/preference_tips_0".equals(tag)) {
                        return new PreferenceTipsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport.outline7("The tag for preference_tips is invalid. Received: ", tag));
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}

package com.example.twittersharehelper.util;

import android.content.Context;
import android.provider.Settings;

import androidx.annotation.NonNull;

public class SettingsUtil {

    private SettingsUtil() {
    }

    public static boolean isDeveloperMode(@NonNull Context context) {
        return Settings.Global.getInt(context.getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1;
    }
}

package ndk.utils_android19;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import ndk.utils_android9.SharedPreferencesUtils9;

public class SharedPreferenceUtils19 extends SharedPreferencesUtils9 {

    public static boolean isFirstRun(String applicationName, Context currentApplicationContext, SharedPreferences sharedPreferences, FirstRunActions firstRunActions) {

        final String isFirstRunKey = "isFirstRun";
        if (sharedPreferences.getString(isFirstRunKey, String.valueOf(true)).equals(String.valueOf(true))) {

            firstRunActions.onFirstRun();

            if (!(SharedPreferenceUtils19Kt.commitSharedPreferences(sharedPreferences, Map.of(isFirstRunKey, String.valueOf(false))))) {

                SharedPreferenceUtils19Kt.debugCommitSharedPreferencesErrorWithIndicatorMessageOnGui(applicationName, null, null, currentApplicationContext);
            }

            return true;
        }
        return false;
    }

    public static boolean isFirstRun(String applicationName, Context currentApplicationContext, FirstRunActions firstRunActions) {
        return isFirstRun(applicationName, currentApplicationContext, currentApplicationContext.getSharedPreferences(applicationName, Context.MODE_PRIVATE), firstRunActions);
    }

    public interface FirstRunActions {

        void onFirstRun();
    }
}

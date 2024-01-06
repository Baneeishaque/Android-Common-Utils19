package ndk.utils_android19;

import android.content.Context;
import android.content.SharedPreferences;

import ndk.utils_android16.SharedPreferenceUtils16;
import ndk.utils_android19.models.PairOfStringsModel;

class SharedPreferenceUtils19 extends SharedPreferenceUtils16 {

    public static boolean isFirstRun(SharedPreferences sharedPreferences, FirstRunActions firstRunActions) {

        String isFirstRunKey = "isFirstRun";
        if (sharedPreferences.getString(isFirstRunKey, String.valueOf(true)).equals(String.valueOf(true))) {

            firstRunActions.onFirstRun();

            commitSharedPreferences(sharedPreferences, new PairOfStringsModel[]{new PairOfStringsModel(isFirstRunKey, String.valueOf(false))});
            return true;
        }
        return false;
    }

    public static boolean isFirstRun(Context context, String applicationName, FirstRunActions firstRunActions) {
        return isFirstRun(context.getSharedPreferences(applicationName, Context.MODE_PRIVATE), firstRunActions);
    }
}

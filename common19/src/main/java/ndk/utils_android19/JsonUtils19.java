package ndk.utils_android19;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import ndk.utils_android1.ErrorUtils;
import ndk.utils_android1.LogUtils1;
import ndk.utils_android16.JsonUtils;

public class JsonUtils19 extends JsonUtils {
    public static void jsonObjectFieldsToSharedPreferences(JSONObject jsonObject, boolean fieldsToIgnoreFlag, String[] fieldsToIgnore, Context currentApplicationContext, String applicationName) {

        for (int i = 0; i < Objects.requireNonNull(jsonObject.names()).length(); i++) {

            try {

                LogUtils1.debug(applicationName, "key = " + Objects.requireNonNull(jsonObject.names()).getString(i) + " value = " + jsonObject.get(Objects.requireNonNull(jsonObject.names()).getString(i)), currentApplicationContext);

                String key = Objects.requireNonNull(jsonObject.names()).getString(i);

                if (fieldsToIgnoreFlag && Arrays.asList(fieldsToIgnore).contains(key)) {
                    continue;
                }

                if (SharedPreferenceUtils19Kt.commitSharedPreferences(currentApplicationContext, applicationName, Map.of(key, jsonObject.getString(key)))) {

                    SharedPreferenceUtils19Kt.debugCommitSharedPreferencesErrorWithIndicatorMessageOnGui(applicationName, null, null, currentApplicationContext);
                }

            } catch (JSONException jsonException) {

                ErrorUtils.displayException(currentApplicationContext, jsonException, applicationName);
            }
        }
    }

    public static void jsonObjectFieldsToSharedPreferencesWithoutFieldsToIgnore(JSONObject jsonObject, Context applicationContext, String applicationName) {
        jsonObjectFieldsToSharedPreferences(jsonObject, false, new String[]{}, applicationContext, applicationName);
    }

    public static void jsonObjectFieldsToSharedPreferencesWithFieldsToIgnore(JSONObject jsonObject, String[] fieldsToIgnore, Context applicationContext, String applicationName) {
        jsonObjectFieldsToSharedPreferences(jsonObject, true, fieldsToIgnore, applicationContext, applicationName);
    }

    public static void jsonArrayToSharedPreferencesWithFieldsToIgnore(JSONArray jsonArray, String[] fieldsToIgnore, Context applicationContext, String applicationName) {

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                jsonObjectFieldsToSharedPreferencesWithFieldsToIgnore(jsonArray.getJSONObject(i), fieldsToIgnore, applicationContext, applicationName);

            } catch (JSONException jsonException) {

                ErrorUtils.displayException(applicationContext, jsonException, applicationName);
            }
        }
    }
}

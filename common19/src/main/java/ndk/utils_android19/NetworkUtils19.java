package ndk.utils_android19;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.NetworkUtils1;
import ndk.utils_android1.TextClearUtils;
import ndk.utils_android1.ToastUtils1;
import ndk.utils_android14.ActivityUtils14;
import ndk.utils_android14.NetworkUtils14;

public class NetworkUtils19 extends NetworkUtils14 {
    public static void handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(String[] networkActionResponseArray, AppCompatActivity currentActivity, Class toSwitchActivity, EditText[] editTextsToClear, View viewToFocusOnError, String tag, int actionFlag, Pair[] nextClassExtras, FurtherActions furtherActions) {

        NetworkUtils14.furtherActions = furtherActions;

        LogUtils1.debug(tag, "Network Action Response Index 0 : " + networkActionResponseArray[0], currentActivity);
        LogUtils1.debug(tag, "Network Action Response Index 1 : " + networkActionResponseArray[1], currentActivity);

        if (networkActionResponseArray[0].equals("1")) {

            Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
            LogUtils1.debug(tag, "Error, Network Action Response Index 1 : " + networkActionResponseArray[1], currentActivity);

        } else {

            try {
                JSONObject json = new JSONObject(networkActionResponseArray[1]);

                switch (json.getString("status")) {

                    case "0":

                        Toast.makeText(currentActivity, "OK", Toast.LENGTH_LONG).show();

                        switch (actionFlag) {

                            case 1: // finish and switch
                                ActivityUtils14.startActivityForClassWithFinish(currentActivity, toSwitchActivity);
                                break;

                            case 2: // clear fields
                                TextClearUtils.resetFields(editTextsToClear);
                                break;

                            case 3: // self finish
                                currentActivity.finish();
                                break;

                            case 4: // finish and switch with extras
                                ActivityUtils19.startActivityWithStringExtrasAndFinish(currentActivity, toSwitchActivity, nextClassExtras);
                                break;

                            case 5: // No Action
                                LogUtils1.debug(tag, "Further Action...", currentActivity);
                                furtherActions.onSuccess();
                                break;

                            case 6: // clear fields & further actions
                                LogUtils1.debug(tag, "Further Action...", currentActivity);
                                TextClearUtils.resetFields(editTextsToClear);
                                furtherActions.onSuccess();
                                break;
                        }
                        break;

                    case "1":
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils1.debug(tag, "Error : " + json.getString("error"), currentActivity);
                        viewToFocusOnError.requestFocus();
                        break;

                    default:
                        Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                        LogUtils1.debug(tag, "Error : Application_Utils json", currentActivity);
                }
            } catch (JSONException e) {
                Toast.makeText(currentActivity, "Error...", Toast.LENGTH_LONG).show();
                LogUtils1.debug(tag, "Error : " + e.getLocalizedMessage(), currentActivity);
            }
        }
    }

    public static void checkNetworkThenStartActivityWithStringExtras(Context context, Class activity, Pair[] extras, boolean forResultFlag, int requestCode) {
        if (NetworkUtils1.isOnline(context)) {
            ActivityUtils19.startActivityForClassWithStringExtras(context, activity, extras);
        } else {
            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void startActivityWithNetworkAndStringExtras(Context activityContext, Class activity, Pair[] stringExtras) {

        if (NetworkUtils1.isOnline(activityContext)) {

            ActivityUtils19.startActivityForClassWithStringExtras(activityContext, activity, stringExtras);

        } else {

            ToastUtils1.longToast(activityContext, "Internet is unavailable...");
        }
    }

    public static void startActivityWithNetworkStringExtrasAndRequestCode(Context activityContext, Class activity, Pair[] stringExtras, int requestCode) {

        if (NetworkUtils1.isOnline(activityContext)) {
            ActivityUtils19.startActivityForResultWithStringExtras(activityContext, activity, stringExtras, requestCode);
        } else {
            ToastUtils1.longToast(activityContext, "Internet is unavailable...");
        }
    }

    public static void handleJsonInsertionResponseAndSwitchWithFinishAndToggleView(String[] networkActionResponseArray, Class toSwitchActivity, View viewToFocusOnError, View viewToToggle, String tag, AppCompatActivity currentActivity) {

        handleJsonInsertionResponseAndSwitchWithFinishOrClearFields(networkActionResponseArray, currentActivity, toSwitchActivity, new EditText[]{}, viewToFocusOnError, tag, 1, new Pair[]{}, furtherActions);
        viewToToggle.setEnabled(true);
    }
}

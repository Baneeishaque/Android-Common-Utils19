package ndk.utils_android19.network_task.update;

import android.content.Context;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ndk.utils_android1.ErrorUtils;
import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.NetworkUtils1;
import ndk.utils_android1.ToastUtils1;
import ndk.utils_android1.UpdateUtils;
import ndk.utils_android14.ActivityUtils14;
import ndk.utils_android16.ServerUtils;

import static ndk.utils_android1.NetworkUtils1.displayFriendlyExceptionMessage;
import static ndk.utils_android16.update.UpdateApplication.updateApplication;

//TODO : Not compatiable with SDK 14
// import static ndk.utils_android14.UpdateUtils14.getServerVersionFireStore;

public class CheckAndUpdateTaskFireStore extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity currentActivity;
    private Class nextActivity;
    private String URL, applicationName, updateUrl;
    private boolean securityFlag, tabIndexFlag;
    private int tabIndex;
    private Pair[] nextActivityExtras;
    private FirebaseFirestore firebaseFirestoreDb;
    private Context applicationContext;

    public CheckAndUpdateTaskFireStore(String applicationName, AppCompatActivity currentActivity, String URL, String updateUrl, Class nextActivity, boolean securityFlag, boolean tabIndexFlag, int tabIndex, Pair[] nextActivityExtras, FirebaseFirestore firebaseFirestoreDb, Context applicationContext) {

        this.currentActivity = currentActivity;
        this.URL = URL;
        this.applicationName = applicationName;
        this.updateUrl = updateUrl;
        this.nextActivity = nextActivity;
        this.securityFlag = securityFlag;
        this.tabIndexFlag = tabIndexFlag;
        this.tabIndex = tabIndex;
        this.nextActivityExtras = nextActivityExtras;
        this.firebaseFirestoreDb = firebaseFirestoreDb;
        this.applicationContext = applicationContext;
    }

    @Override
    protected String[] doInBackground(Void... params) {

        //TODO : Not compatiable with SDK 14
        // FireStoreRequestResponse fireStoreRequestResponse = getServerVersionFireStore(firebaseFirestoreDb, applicationName, applicationContext);
        // switch (fireStoreRequestResponse.getStatus()) {

        //     case 2:
        //         return new String[]{"1", "Action not performed yet..."};

        //     case -1:
        //         return new String[]{"1", "Exception : " + fireStoreRequestResponse.getException().getLocalizedMessage()};

        //     case 1:
        //         return new String[]{"1", "No document..."};

        //     case 0:
        //         JSONArray jsonArray = new JSONArray();
        //         jsonArray.put(new JSONObject(fireStoreRequestResponse.getData()));
        //         return new String[]{"0", String.valueOf(jsonArray)};

        //     default:
        //         return new String[]{"1", "Unknown error..."};
        // }
        return new String[]{"1", "Unknown error..."};
    }

    @Override
    protected void onPostExecute(final String[] networkActionResponseArray) {

        NetworkUtils1.displayNetworkActionResponse(applicationName, networkActionResponseArray);

        if (networkActionResponseArray[0].equals("1")) {

            displayFriendlyExceptionMessage(currentActivity, networkActionResponseArray[1]);
            currentActivity.finish();

        } else {

            try {

                JSONArray jsonArray = new JSONArray(networkActionResponseArray[1]);
                checkAndUpdate(jsonArray);

            } catch (JSONException e) {

                ErrorUtils.displayException(currentActivity, e, applicationName);
            }
        }
    }

    public void checkAndUpdate(JSONArray jsonArray) {

        try {
            JSONObject tempJsonObject = jsonArray.getJSONObject(0);

            if (ServerUtils.checkSystemStatus(currentActivity, tempJsonObject.getString("system_status"), applicationName)) {

                if (Integer.parseInt(tempJsonObject.getString("version_code")) != UpdateUtils.getVersionCode(currentActivity) || Float.parseFloat(tempJsonObject.getString("version_name")) != UpdateUtils.getVersionName(currentActivity)) {

                    updateApplication(applicationName, currentActivity, Float.parseFloat(tempJsonObject.getString("version_name")), updateUrl);

                } else {

                    LogUtils1.debug(applicationName, "Latest Version...");

                    if (!securityFlag) {

                        ToastUtils1.shortToast(currentActivity, "Latest Version...");
                    }
                    // After completing http call will close this activity and launch main activity
                    if (tabIndexFlag) {

                        //TODO : Tab Index with Other extras
                        ActivityUtils14.startActivityWithFinishAndTabIndex(currentActivity, nextActivity, tabIndex);

                    } else {

                        if (nextActivityExtras.length == 0) {

                            ActivityUtils14.startActivityForClassWithFinish(currentActivity, nextActivity);

                        } else {

                            ActivityUtils14.startActivityWithStringExtrasAndFinish(currentActivity, nextActivity, nextActivityExtras);
                        }
                    }
                }
            }
        } catch (JSONException e) {

            ErrorUtils.displayException(currentActivity, e, applicationName);
        }
    }
}

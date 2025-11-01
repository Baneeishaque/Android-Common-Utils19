package ndk.utils_android19.network_task.update;

import android.content.Context;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import ndk.utils_android19.models.PairOfStringsModel;

// TODO : Not compatiable with SDK 14
// import static ndk.utils_android14.UpdateUtils14.getServerVersionFireStore;

public class CheckAndUpdateTaskFireStore extends AsyncTask<Void, Void, String[]> {

    private AppCompatActivity currentActivity;
    private Class nextActivity;
    private String URL, applicationName, updateUrl;
    private boolean securityFlag, tabIndexFlag;
    private int tabIndex;
    private PairOfStringsModel[] nextActivityExtras;
    private FirebaseFirestore firebaseFirestoreDb;
    private Context applicationContext;

    public CheckAndUpdateTaskFireStore(String applicationName, AppCompatActivity currentActivity, String URL, String updateUrl, Class nextActivity, boolean securityFlag, boolean tabIndexFlag, int tabIndex, PairOfStringsModel[] nextActivityExtras, FirebaseFirestore firebaseFirestoreDb, Context applicationContext) {

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

        //TODO : Not compatible with SDK 14
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
    }
}

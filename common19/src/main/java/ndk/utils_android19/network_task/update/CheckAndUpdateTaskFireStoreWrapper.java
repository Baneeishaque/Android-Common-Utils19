package ndk.utils_android19.network_task.update;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.firebase.firestore.FirebaseFirestore;

public class CheckAndUpdateTaskFireStoreWrapper {

    //TODO : Get Task Objects

    public static void executeCheckAndUpdateWithTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, int tabIndex, Pair[] nextClassExtras) {
        CheckAndUpdateTask checkAndUpdateTask = new CheckAndUpdateTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, true, tabIndex, nextClassExtras);

        checkAndUpdateTask.execute();
    }

    public static void executeCheckAndUpdateWithZeroTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras) {
        executeCheckAndUpdateWithTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, 0, nextClassExtras);
    }

    public static void executeCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras, FirebaseFirestore firebaseFirestoreDb, Context applicationContext) {

        getCheckAndUpdateWithoutTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, nextClassExtras, firebaseFirestoreDb, applicationContext).execute();
    }

    public static CheckAndUpdateTaskFireStore getCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class nextActivity, boolean securityFlag, Pair[] nextClassExtras, FirebaseFirestore firebaseFirestoreDb, Context applicationContext) {

        return new CheckAndUpdateTaskFireStore(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, false, 0, nextClassExtras, firebaseFirestoreDb, applicationContext);
    }
}

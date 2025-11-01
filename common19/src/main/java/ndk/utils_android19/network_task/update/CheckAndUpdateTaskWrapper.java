package ndk.utils_android19.network_task.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android19.models.PairOfStringsModel;

public class CheckAndUpdateTaskWrapper {

    //TODO : Get Task Objects

    public static void executeCheckAndUpdateWithTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class<? extends AppCompatActivity> nextActivity, boolean securityFlag, int tabIndex, PairOfStringsModel[] nextClassExtras) {
        CheckAndUpdateTask checkAndUpdateTask = new CheckAndUpdateTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, true, tabIndex, nextClassExtras);

        checkAndUpdateTask.execute();
    }

    public static void executeCheckAndUpdateWithZeroTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class<? extends AppCompatActivity> nextActivity, boolean securityFlag, PairOfStringsModel[] nextClassExtras) {
        executeCheckAndUpdateWithTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, 0, nextClassExtras);
    }

    public static void executeCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class<? extends AppCompatActivity> nextActivity, boolean securityFlag, PairOfStringsModel[] nextClassExtras) {

        getCheckAndUpdateWithoutTabIndexTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, nextClassExtras).execute();
    }

    public static CheckAndUpdateTask getCheckAndUpdateWithoutTabIndexTask(String applicationName, AppCompatActivity currentActivity, String url, String updateUrl, Class<? extends AppCompatActivity> nextActivity, boolean securityFlag, PairOfStringsModel[] nextClassExtras) {

        return new CheckAndUpdateTask(applicationName, currentActivity, url, updateUrl, nextActivity, securityFlag, false, 0, nextClassExtras);
    }
}

package ndk.utils_android19.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android19.network_task.update.CheckAndUpdateTaskFireStoreWrapper;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateFireStoreActivity extends SplashWithAutomatedUpdateActivity {

    public void checkThenPerformUpdateIfNeeded() {

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configureGetConfigurationUrl(), configureApplicationName(), jsonArray -> CheckAndUpdateTaskFireStoreWrapper.getCheckAndUpdateWithoutTabIndexTask(configureApplicationName(), (AppCompatActivity) currentActivityContext, configureGetConfigurationUrl(), configureUpdateUrl(), configureNextActivityClass(), configureSecurityFlag(), configureNextActivityClassExtras(), configureFireStoreDb(), getApplicationContext()).execute());
    }

    public abstract FirebaseFirestore configureFireStoreDb();
}

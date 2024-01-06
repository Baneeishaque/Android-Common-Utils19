package ndk.utils_android19.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android14.ActivityWithContexts14;
import ndk.utils_android16.activities.LoginBundleActivity;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.network_task.update.CheckAndUpdateTaskWrapper;
import ndk.utils_android19.models.PairOfStringsModel;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateActivity extends ActivityWithContexts14 {

    public abstract String configure_GET_CONFIGURATION_URL();

    public abstract String configure_UPDATE_URL();

    public abstract Class<LoginBundleActivity> configureNextActivityClass();

    public abstract PairOfStringsModel[] configureNextActivityClassExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initializeScreen();

        checkThenPerformUpdateIfNeeded();
    }

    public void checkThenPerformUpdateIfNeeded() {

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configure_GET_CONFIGURATION_URL(), configure_APPLICATION_NAME(), jsonArray -> CheckAndUpdateTaskWrapper.getCheckAndUpdateWithoutTabIndexTask(configure_APPLICATION_NAME(), (AppCompatActivity) currentActivityContext, configure_GET_CONFIGURATION_URL(), configure_UPDATE_URL(), configureNextActivityClass(), configure_SECURITY_FLAG(), configureNextActivityClassExtras()).execute());
    }

    public void initializeScreen() {

        setContentView(ndk.utils_android14.R.layout.splash);
    }

    public abstract boolean configure_SECURITY_FLAG();

    public abstract String configure_APPLICATION_NAME();
}

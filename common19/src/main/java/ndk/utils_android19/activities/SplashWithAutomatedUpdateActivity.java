package ndk.utils_android19.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android14.ActivityWithContexts14;
import ndk.utils_android16.activities.LoginBundleActivity;
import ndk.utils_android16.network_task.HttpApiSelectTaskWrapper;
import ndk.utils_android16.network_task.update.CheckAndUpdateTaskWrapper;
import ndk.utils_android19.models.PairOfStringsModel;

//TODO : Full screen splash
//TODO : Implement hiding of fields - in case of layout
//TODO : Develop tests

public abstract class SplashWithAutomatedUpdateActivity extends ActivityWithContexts14 {

    public abstract String configureGetConfigurationUrl();

    public abstract String configureUpdateUrl();

    public abstract Class<LoginBundleActivity> configureNextActivityClass();

    public abstract PairOfStringsModel[] configureNextActivityClassExtras();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initializeScreen();

        checkThenPerformUpdateIfNeeded();
    }

    public void checkThenPerformUpdateIfNeeded() {

        HttpApiSelectTaskWrapper.performSplashScreenThenReturnJsonArray(this, configureGetConfigurationUrl(), configureApplicationName(), jsonArray -> CheckAndUpdateTaskWrapper.getCheckAndUpdateWithoutTabIndexTask(configureApplicationName(), (AppCompatActivity) currentActivityContext, configureGetConfigurationUrl(), configureUpdateUrl(), configureNextActivityClass(), configureSecurityFlag(), configureNextActivityClassExtras()).execute());
    }

    public void initializeScreen() {

        setContentView(ndk.utils_android14.R.layout.splash);
    }

    public abstract boolean configureSecurityFlag();

    public abstract String configureApplicationName();
}

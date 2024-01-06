package ndk.utils_android19.tests;

import androidx.core.util.Pair;

import ndk.utils_android1.DebugUtils;
import ndk.utils_android16.activities.LoginBundleActivity;
import ndk.utils_android16.constants.IntentExtraFields;
import ndk.utils_android19.activities.SplashWithAutomatedUpdateActivity;
import ndk.utils_android19.models.PairOfStringsModel;

//TODO : Develop Backend - Single Method with operation variants
//TODO : Add Mismatch version URL
//TODO : Add System maintenance URL
//TODO : Add Login Test with backend API

public class Splash_Version_OK extends SplashWithAutomatedUpdateActivity {

    @Override
    public String configure_GET_CONFIGURATION_URL() {

        return "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_Configuration.php";
    }

    @Override
    public String configure_UPDATE_URL() {

        return "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/builds/app-debug.apk";
    }

    @Override
    public Class<LoginBundleActivity> configureNextActivityClass() {
        return LoginBundleActivity.class;
    }

    @Override
    public String configure_APPLICATION_NAME() {

        return Application_Specification.APPLICATION_NAME;
    }

    @Override
    public PairOfStringsModel[] configureNextActivityClassExtras() {

        return new PairOfStringsModel[]{new PairOfStringsModel(IntentExtraFields.APPLICATION_NAME, Application_Specification.APPLICATION_NAME),
                new PairOfStringsModel(IntentExtraFields.NEXT_ACTIVITY_CLASS, "Splash_Version_OK"), new PairOfStringsModel(IntentExtraFields.SELECT_USER_URL,
                "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php")};
    }

    @Override
    public boolean configure_SECURITY_FLAG() {

        return DebugUtils.isDebugBuild(getApplicationContext());
    }
}

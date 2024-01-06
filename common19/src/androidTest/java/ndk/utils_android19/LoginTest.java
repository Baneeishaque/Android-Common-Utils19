package ndk.utils_android19;

import android.content.Context;
import android.content.Intent;

import androidx.core.util.Pair;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ndk.utils_android16.constants.IntentExtraFields;
import ndk.utils_android19.activities.LoginBundleActivity;

/**
 * Instrumented test, which will executePostThenReturnJsonObject on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    //TODO : Application_Utils Network Response
    //TODO : Application_Utils Next Screen

    @Rule
    public ActivityTestRule<LoginBundleActivity> mActivityRule = new ActivityTestRule<LoginBundleActivity>(LoginBundleActivity.class) {
        @Override
        protected Intent getActivityIntent() {

            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

            return ActivityUtils19.constructIntentWithStringExtras(targetContext, LoginBundleActivity.class, new Pair[]{new Pair<>(IntentExtraFields.APPLICATION_NAME, Application_Specification.APPLICATION_NAME), new Pair<>(IntentExtraFields.NEXT_ACTIVITY_CLASS, "Splash_Version_OK"), new Pair<>(IntentExtraFields.SELECT_USER_URL, "http://vfmob.com.md-in-64.webhostbox.net/wp-production/account_ledger_server/http_API/select_User.php")});
        }
    };

    @Test
    public void check_login() {

        // Type text and then press the button.
        Espresso.onView(ViewMatchers.withId(ndk.utils_android16.R.id.editTextUsername)).perform(ViewActions.typeText("banee_10_5"));
        Espresso.onView(ViewMatchers.withId(ndk.utils_android16.R.id.editTextPassword)).perform(ViewActions.typeText("9895204814"));
        Espresso.onView(ViewMatchers.withId(ndk.utils_android16.R.id.buttonSignIn)).perform(ViewActions.click());
    }
}

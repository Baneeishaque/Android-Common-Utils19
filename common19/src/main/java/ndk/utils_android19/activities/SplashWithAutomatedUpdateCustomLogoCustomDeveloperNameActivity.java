package ndk.utils_android19.activities;

import android.widget.TextView;

public abstract class SplashWithAutomatedUpdateCustomLogoCustomDeveloperNameActivity extends SplashWithAutomatedUpdateCustomLogoActivity {

    //TODO : Multiple inheritance
    protected abstract String configure_DEVELOPER_NAME();

    @Override
    public void initializeScreen() {
        super.initializeScreen();

        TextView text_developer = findViewById(ndk.utils_android14.R.id.text_Developer);
        text_developer.setText(configure_DEVELOPER_NAME());
    }
}

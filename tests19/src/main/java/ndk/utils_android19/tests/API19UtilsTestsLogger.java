package ndk.utils_android19.tests;

import android.content.Context;

public class API19UtilsTestsLogger extends LogUtilsWrapper {

    public API19UtilsTestsLogger(Context currentApplicationContext) {
        super(ApplicationSpecification.applicationName, currentApplicationContext);
    }
}

package ndk.utils_android19;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.Arrays;

import ndk.utils_android1.ExceptionUtils1;

//TODO : Change API

public class ExceptionUtils19 extends ExceptionUtils1 {

    @NonNull
    public static String getExceptionDetails19(@NonNull Exception e) {

        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception Suppressed : " + Arrays.toString(e.getSuppressed())
                + "\n" + "Exception : " + e;
    }

    public static void handleException19(boolean isGuiPresent, Context applicationContext, final String tag, Exception exception) {

        handleException(isGuiPresent, applicationContext, tag, getExceptionDetails19(exception));
    }

    public static void handleExceptionOnGui19(Context applicationContext, final String tag, Exception exception) {

        handleException19(true, applicationContext, tag, exception);
    }
}

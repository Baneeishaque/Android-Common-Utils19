package ndk.utils_android19.tests;

import android.content.Context;

import ndk.utils_android1.LogUtils1;

public class LogUtilsWrapper {

    private static String tag;
    private static Context context;

    LogUtilsWrapper(String _tag, Context _context) {
        tag = _tag;
        context =_context;
    }

    public static void debug(String message) {
        LogUtils1.debug(tag, message, context);
    }
}

package ndk.utils_android19.network_task;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils_android1.ProgressBarUtils1;
import ndk.utils_android1.ToastUtils1;

import static ndk.utils_android1.NetworkUtils1.isOnline;
import static ndk.utils_android14.NetworkUtils14.FurtherActions;

public class RestInsertTaskWrapper {

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, Class nextActivity) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressView, formView);

            new RestInsertTask(taskUrl, currentActivity, progressView, formView, applicationName, nameValuePairs, viewToFocusOnError, nextActivity).execute();

        } else {
            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, Class nextActivity, Pair[] nextClassExtras) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, nextActivity, nextClassExtras);

            restInsertTask.execute();

        } else {
            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, EditText[] editTextsToClear) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, editTextsToClear);

            restInsertTask.execute();

        } else {
            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, EditText[] editTextsToClear, FurtherActions furtherActions) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressBarView, formView);

            new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, editTextsToClear, furtherActions).execute();

        } else {

            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressBarView, formView);

            RestInsertTask restInsertTask = new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError);

            restInsertTask.execute();

        } else {

            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(Context context, String taskUrl, AppCompatActivity currentActivity, View progressBarView, View formView, String applicationName, Pair[] nameValuePairs, View viewToFocusOnError, FurtherActions furtherActions) {

        Log.d(applicationName, "REST Insert TASK URL : " + taskUrl);

        if (isOnline(context)) {

            ProgressBarUtils1.showProgress(true, context, progressBarView, formView);

            new RestInsertTask(taskUrl, currentActivity, progressBarView, formView, applicationName, nameValuePairs, viewToFocusOnError, furtherActions).execute();

        } else {

            ToastUtils1.longToast(context, "Internet is unavailable");
        }
    }
}

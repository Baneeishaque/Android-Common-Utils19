package ndk.utils_android19

import android.content.Context
import android.widget.Toast
import ndk.utils_android1.LogUtils1
import ndk.utils_android1.ToastUtils1

class LogUtils19 {

    companion object {

        @JvmStatic
        fun debugWithIndicatorMessageOnGui(

            applicationTag: String,
            message: String,
            indicatorMessage: String? = "Something Went Wrong...",
            currentApplicationContext: Context

        ) {
            ToastUtils1.longToast(currentApplicationContext, indicatorMessage!!)
            LogUtils1.debug(applicationTag, message, currentApplicationContext)
        }
    }
}

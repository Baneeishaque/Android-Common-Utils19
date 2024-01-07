package ndk.utils_android19

import android.content.Context
import android.content.SharedPreferences
import ndk.utils_android1.SharedPreferencesUtils1
import java.io.IOException

class SharedPreferenceUtils19Kt {

    companion object {

        @JvmStatic
        fun getIntFromSharedPreferences(

            sharedPreferences: SharedPreferences,
            key: String

        ): Triple<Boolean, Int?, String?> {

            return try {
                if (sharedPreferences.contains(key)) {
                    Triple(
                        first = true,
                        second = sharedPreferences.getInt(key, 0),
                        third = null
                    )
                } else {
                    Triple(
                        first = false,
                        second = null,
                        third = "key not exist"
                    )
                }
            } catch (classCastException: ClassCastException) {
                Triple(
                    first = false,
                    second = null,
                    third = classCastException.localizedMessage
                )
            }
        }

        @JvmStatic
        fun checkUnsignedIntValueIncludingAsTextOnSharedPreferences(

            sharedPreferences: SharedPreferences,
            key: String

        ): Pair<Boolean, String?> {

            return try {
                if (sharedPreferences.contains(key)) {
                    sharedPreferences.getInt(key, 0)
                    Pair(
                        first = true,
                        second = null
                    )
                } else {
                    Pair(
                        first = false,
                        second = "key not exist"
                    )
                }
            } catch (classCastException: ClassCastException) {

                try {
                    (sharedPreferences.getString(key, "")!!).toUInt()

                    return Pair(
                        first = true,
                        second = null
                    )

                } catch (classCastExceptionOrNumberFormatException: Exception) {

                    return Pair(
                        first = false,
                        second = classCastExceptionOrNumberFormatException.localizedMessage
                    )
                }
            }
        }

        @JvmStatic
        fun <T> commitSharedPreferences(

            sharedPreferences: SharedPreferences,
            sharedPreferencePairs: Map<String, T>

        ): Boolean {

            var result = true
            if (sharedPreferencePairs.isNotEmpty()) {

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                sharedPreferencePairs.forEach { sharedPreference: Map.Entry<String, T> ->

                    when (sharedPreference.value) {

                        is String -> editor.putString(
                            sharedPreference.key,
                            sharedPreference.value as String
                        )

                        is Int -> editor.putInt(sharedPreference.key, sharedPreference.value as Int)
                        else -> result = false
                    }
                }
                if (result) {

                    result = editor.commit()
                }
                return result
            }
            return false
        }

        @JvmStatic
        fun <T> commitSharedPreferences(

            applicationContext: Context,
            applicationName: String,
            sharedPreferencePairs: Map<String, T>

        ): Boolean {
            return commitSharedPreferences(
                sharedPreferences = SharedPreferencesUtils1.getSharedPreferences(
                    applicationContext,
                    applicationName
                ),
                sharedPreferencePairs = sharedPreferencePairs
            )
        }

        @JvmStatic
        fun debugCommitSharedPreferencesErrorWithIndicatorMessageOnGui(

            applicationTag: String,
            message: String? = "SharedPreferences Commit Error...",
            indicatorMessage: String? = "Something Went Wrong...",
            currentApplicationContext: Context

        ) {
            LogUtils19.debugWithIndicatorMessageOnGui(

                applicationTag = applicationTag,
                message = message!!,
                indicatorMessage = indicatorMessage,
                currentApplicationContext = currentApplicationContext
            )
        }
    }
}

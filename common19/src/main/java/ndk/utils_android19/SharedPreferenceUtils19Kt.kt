package ndk.utils_android19

import android.content.SharedPreferences

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
        fun checkIntOnSharedPreferences(

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
                Pair(
                    first = false,
                    second = classCastException.localizedMessage
                )
            }
        }
    }
}

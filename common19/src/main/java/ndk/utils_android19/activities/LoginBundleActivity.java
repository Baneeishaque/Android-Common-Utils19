package ndk.utils_android19.activities;

import java.util.Objects;

import ndk.utils_android1.ErrorUtils;
import ndk.utils_android16.constants.IntentExtendedDataItemNames;

public class LoginBundleActivity extends LoginBaseActivity {

    @Override
    public String configure_SELECT_USER_URL() {

        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_SELECT_USER_URL);
    }

    @Override
    public String configureApplicationName() {

        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_APPLICATION_NAME);
    }

    @Override
    public Class configureNextActivityClass() {

        try {

            return Class.forName(Objects.requireNonNull(getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_NEXT_ACTIVITY_CLASS)));

        } catch (ClassNotFoundException e) {

            ErrorUtils.displayException(this, e, configureApplicationName());
            return null;
        }
    }

    @Override
    public String configureTestUsername() {

        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_TEST_USERNAME);
    }

    @Override
    public String configureSharedPreferenceKeyForUserId() {
        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_SHARED_PREFERENCES_KEY_USER_ID);
    }

    @Override
    public String configureTestPassword() {

        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_TEST_PASSWORD);
    }
}

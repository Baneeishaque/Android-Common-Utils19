package ndk.utils_android19;

import java.util.Objects;

import ndk.utils_android16.constants.IntentExtendedDataItemNames;

public abstract class PassBookBundle extends PassBookActivityBase {

    @Override
    public boolean isV2() {

        return Objects.equals(getIntent().getStringExtra("V2_FLAG"), String.valueOf(true));
    }

    @Override
    public boolean isSortingAvailable() {

        return Objects.equals(getIntent().getStringExtra("SORT_FLAG"), String.valueOf(true));
    }

    @Override
    public String configureUserId() {

        return getIntent().getStringExtra(IntentExtendedDataItemNames.INTENT_EXTENDED_DATA_ITEM_NAME_SHARED_PREFERENCES_KEY_USER_ID);
    }

    @Override
    public String configureApplicationName() {

        return getIntent().getStringExtra("application_name");
    }

    @Override
    public String configurePassBookUrl() {

        return getIntent().getStringExtra("URL");
    }

    @Override
    public String configureCurrentAccountId() {

        return getIntent().getStringExtra("current_account_id");
    }
}

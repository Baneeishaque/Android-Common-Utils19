package ndk.utils_android19;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {

    private Context mContext;
    private FurtherActions mFurtherActions;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context context, FurtherActions furtherActions) {
        mContext = context;
        mFurtherActions = furtherActions;
    }

    @JavascriptInterface
    public void doActions() {
        mFurtherActions.furtherActions();
    }
}

package ndk.utils_android19;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ViewUtils19 {

    /**
     * Show a view
     * @param view the view to show
     */
    public static void show(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hide a view (INVISIBLE)
     * @param view the view to hide
     */
    public static void hide(View view) {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Remove a view from layout (GONE)
     * @param view the view to remove
     */
    public static void gone(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Toggle visibility between VISIBLE and GONE
     * @param view the view to toggle
     */
    public static void toggleVisibility(View view) {
        if (view != null) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Check if a view is visible
     * @param view the view to check
     * @return true if visible, false otherwise
     */
    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    /**
     * Enable a view
     * @param view the view to enable
     */
    public static void enable(View view) {
        if (view != null) {
            view.setEnabled(true);
        }
    }

    /**
     * Disable a view
     * @param view the view to disable
     */
    public static void disable(View view) {
        if (view != null) {
            view.setEnabled(false);
        }
    }

    /**
     * Enable multiple views
     * @param views the views to enable
     */
    public static void enableViews(View... views) {
        for (View view : views) {
            enable(view);
        }
    }

    /**
     * Disable multiple views
     * @param views the views to disable
     */
    public static void disableViews(View... views) {
        for (View view : views) {
            disable(view);
        }
    }

    /**
     * Show multiple views
     * @param views the views to show
     */
    public static void showViews(View... views) {
        for (View view : views) {
            show(view);
        }
    }

    /**
     * Hide multiple views
     * @param views the views to hide
     */
    public static void hideViews(View... views) {
        for (View view : views) {
            hide(view);
        }
    }

    /**
     * Remove multiple views from layout
     * @param views the views to remove
     */
    public static void goneViews(View... views) {
        for (View view : views) {
            gone(view);
        }
    }

    /**
     * Show keyboard for an EditText
     * @param context the context
     * @param editText the EditText to focus
     */
    public static void showKeyboard(Context context, EditText editText) {
        if (editText != null && context != null) {
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    /**
     * Hide keyboard
     * @param context the context
     * @param view the view that currently has focus
     */
    public static void hideKeyboard(Context context, View view) {
        if (view != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Set margins for a view
     * @param view the view to set margins for
     * @param left left margin in pixels
     * @param top top margin in pixels
     * @param right right margin in pixels
     * @param bottom bottom margin in pixels
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view != null && view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(left, top, right, bottom);
            view.setLayoutParams(params);
        }
    }

    /**
     * Set padding for a view
     * @param view the view to set padding for
     * @param left left padding in pixels
     * @param top top padding in pixels
     * @param right right padding in pixels
     * @param bottom bottom padding in pixels
     */
    public static void setPadding(View view, int left, int top, int right, int bottom) {
        if (view != null) {
            view.setPadding(left, top, right, bottom);
        }
    }

    /**
     * Get text from EditText as String
     * @param editText the EditText
     * @return text as String, or empty string if EditText is null
     */
    public static String getText(EditText editText) {
        if (editText != null && editText.getText() != null) {
            return editText.getText().toString().trim();
        }
        return "";
    }

    /**
     * Set text to EditText
     * @param editText the EditText
     * @param text the text to set
     */
    public static void setText(EditText editText, String text) {
        if (editText != null) {
            editText.setText(text);
        }
    }

    /**
     * Clear text from EditText
     * @param editText the EditText to clear
     */
    public static void clearText(EditText editText) {
        if (editText != null) {
            editText.setText("");
        }
    }

    /**
     * Clear text from multiple EditTexts
     * @param editTexts the EditTexts to clear
     */
    public static void clearTexts(EditText... editTexts) {
        for (EditText editText : editTexts) {
            clearText(editText);
        }
    }

    /**
     * Check if EditText is empty
     * @param editText the EditText to check
     * @return true if empty, false otherwise
     */
    public static boolean isEmpty(EditText editText) {
        return getText(editText).isEmpty();
    }

    /**
     * Check if any of the EditTexts is empty
     * @param editTexts the EditTexts to check
     * @return true if any is empty, false otherwise
     */
    public static boolean isAnyEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (isEmpty(editText)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set click listener for multiple views
     * @param listener the click listener
     * @param views the views to set listener for
     */
    public static void setOnClickListener(View.OnClickListener listener, View... views) {
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * Remove all views from a ViewGroup
     * @param viewGroup the ViewGroup to clear
     */
    public static void removeAllViews(ViewGroup viewGroup) {
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    /**
     * Get the width of a view in pixels
     * @param view the view
     * @return width in pixels, or 0 if view is null
     */
    public static int getWidth(View view) {
        return view != null ? view.getWidth() : 0;
    }

    /**
     * Get the height of a view in pixels
     * @param view the view
     * @return height in pixels, or 0 if view is null
     */
    public static int getHeight(View view) {
        return view != null ? view.getHeight() : 0;
    }

    /**
     * Request focus on a view
     * @param view the view to focus
     */
    public static void requestFocus(View view) {
        if (view != null) {
            view.requestFocus();
        }
    }

    /**
     * Clear focus from a view
     * @param view the view to clear focus from
     */
    public static void clearFocus(View view) {
        if (view != null) {
            view.clearFocus();
        }
    }
}

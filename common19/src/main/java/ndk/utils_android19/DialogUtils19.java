package ndk.utils_android19;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class DialogUtils19 {

    /**
     * Show a simple alert dialog with a title, message, and OK button
     * @param context the context
     * @param title dialog title
     * @param message dialog message
     */
    public static void showAlertDialog(Context context, String title, String message) {
        showAlertDialog(context, title, message, null);
    }

    /**
     * Show an alert dialog with a title, message, and custom positive button action
     * @param context the context
     * @param title dialog title
     * @param message dialog message
     * @param positiveListener listener for positive button click
     */
    public static void showAlertDialog(Context context, String title, String message, 
                                      DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setMessage(message)
               .setPositiveButton("OK", positiveListener)
               .show();
    }

    /**
     * Show a confirmation dialog with Yes/No buttons
     * @param context the context
     * @param title dialog title
     * @param message dialog message
     * @param positiveListener listener for Yes button click
     * @param negativeListener listener for No button click
     */
    public static void showConfirmationDialog(Context context, String title, String message,
                                             DialogInterface.OnClickListener positiveListener,
                                             DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setMessage(message)
               .setPositiveButton("Yes", positiveListener)
               .setNegativeButton("No", negativeListener)
               .show();
    }

    /**
     * Show a confirmation dialog with Yes/No buttons and custom button labels
     * @param context the context
     * @param title dialog title
     * @param message dialog message
     * @param positiveButtonText positive button text
     * @param negativeButtonText negative button text
     * @param positiveListener listener for positive button click
     * @param negativeListener listener for negative button click
     */
    public static void showConfirmationDialog(Context context, String title, String message,
                                             String positiveButtonText, String negativeButtonText,
                                             DialogInterface.OnClickListener positiveListener,
                                             DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setMessage(message)
               .setPositiveButton(positiveButtonText, positiveListener)
               .setNegativeButton(negativeButtonText, negativeListener)
               .show();
    }

    /**
     * Show a single choice list dialog
     * @param context the context
     * @param title dialog title
     * @param items array of items to choose from
     * @param listener listener for item selection
     */
    public static void showSingleChoiceDialog(Context context, String title, String[] items,
                                             DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setItems(items, listener)
               .show();
    }

    /**
     * Show a single choice list dialog with radio buttons
     * @param context the context
     * @param title dialog title
     * @param items array of items to choose from
     * @param checkedItem index of initially checked item
     * @param listener listener for item selection
     * @param positiveListener listener for positive button click
     */
    public static void showSingleChoiceDialog(Context context, String title, String[] items,
                                             int checkedItem, DialogInterface.OnClickListener listener,
                                             DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setSingleChoiceItems(items, checkedItem, listener)
               .setPositiveButton("OK", positiveListener)
               .setNegativeButton("Cancel", null)
               .show();
    }

    /**
     * Show a multi-choice list dialog with checkboxes
     * @param context the context
     * @param title dialog title
     * @param items array of items to choose from
     * @param checkedItems boolean array indicating which items are initially checked
     * @param listener listener for item selection
     * @param positiveListener listener for positive button click
     */
    public static void showMultiChoiceDialog(Context context, String title, String[] items,
                                            boolean[] checkedItems, 
                                            DialogInterface.OnMultiChoiceClickListener listener,
                                            DialogInterface.OnClickListener positiveListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setMultiChoiceItems(items, checkedItems, listener)
               .setPositiveButton("OK", positiveListener)
               .setNegativeButton("Cancel", null)
               .show();
    }

    /**
     * Show an input dialog with a single EditText
     * @param context the context
     * @param title dialog title
     * @param hint hint text for the EditText
     * @param listener listener for positive button click with the input text
     */
    public static void showInputDialog(Context context, String title, String hint,
                                      final InputDialogListener listener) {
        final EditText editText = new EditText(context);
        editText.setHint(hint);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setView(editText)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if (listener != null) {
                           listener.onInput(editText.getText().toString());
                       }
                   }
               })
               .setNegativeButton("Cancel", null)
               .show();
    }

    /**
     * Show an input dialog with a prefilled EditText
     * @param context the context
     * @param title dialog title
     * @param hint hint text for the EditText
     * @param defaultValue default value for the EditText
     * @param listener listener for positive button click with the input text
     */
    public static void showInputDialog(Context context, String title, String hint, String defaultValue,
                                      final InputDialogListener listener) {
        final EditText editText = new EditText(context);
        editText.setHint(hint);
        editText.setText(defaultValue);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
               .setView(editText)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       if (listener != null) {
                           listener.onInput(editText.getText().toString());
                       }
                   }
               })
               .setNegativeButton("Cancel", null)
               .show();
    }

    /**
     * Show a progress dialog
     * @param context the context
     * @param title dialog title
     * @param message dialog message
     * @param cancelable whether the dialog is cancelable
     * @return the ProgressDialog instance
     */
    @SuppressWarnings("deprecation")
    public static ProgressDialog showProgressDialog(Context context, String title, String message, boolean cancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
        return progressDialog;
    }

    /**
     * Show a simple progress dialog
     * @param context the context
     * @param message dialog message
     * @return the ProgressDialog instance
     */
    @SuppressWarnings("deprecation")
    public static ProgressDialog showProgressDialog(Context context, String message) {
        return showProgressDialog(context, null, message, false);
    }

    /**
     * Dismiss a dialog safely
     * @param dialog the dialog to dismiss
     */
    public static void dismissDialog(AlertDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * Dismiss a progress dialog safely
     * @param progressDialog the progress dialog to dismiss
     */
    @SuppressWarnings("deprecation")
    public static void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Interface for input dialog listener
     */
    public interface InputDialogListener {
        void onInput(String input);
    }

    /**
     * Show an error dialog
     * @param context the context
     * @param message error message
     */
    public static void showErrorDialog(Context context, String message) {
        showAlertDialog(context, "Error", message);
    }

    /**
     * Show a success dialog
     * @param context the context
     * @param message success message
     */
    public static void showSuccessDialog(Context context, String message) {
        showAlertDialog(context, "Success", message);
    }

    /**
     * Show an info dialog
     * @param context the context
     * @param message info message
     */
    public static void showInfoDialog(Context context, String message) {
        showAlertDialog(context, "Information", message);
    }

    /**
     * Show a warning dialog
     * @param context the context
     * @param message warning message
     */
    public static void showWarningDialog(Context context, String message) {
        showAlertDialog(context, "Warning", message);
    }
}

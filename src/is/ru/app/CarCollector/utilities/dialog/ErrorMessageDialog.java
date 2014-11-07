package is.ru.app.CarCollector.utilities.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import is.ru.app.CarCollector.R;

/**
 * <h1>dialog</h1>
 * <h2>is.ru.app.CarCollector.utilities</h2>
 * <p></p>
 * Created on 6.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class ErrorMessageDialog extends AbstractDialog {

    public interface ErrorDialogListener {
        public void onErrorDialogPositiveClick(DialogFragment dialog);
        public void onErrorDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.errorQueryMessage)
                .setPositiveButton(R.string.errorQueryRetry, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        errorDialogListener.onErrorDialogPositiveClick(ErrorMessageDialog.this);
                    }
                })
                .setNegativeButton(R.string.errorQueryCancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        errorDialogListener.onErrorDialogNegativeClick(ErrorMessageDialog.this);
                    }
                })
                .setTitle(R.string.errorQueryTitle);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

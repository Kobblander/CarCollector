package is.ru.app.CarCollector.utilities.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import is.ru.app.CarCollector.R;

/**
 * <h1>CarExistsDialog</h1>
 * <h2>is.ru.app.CarCollector.utilities.dialog</h2>
 * <p></p>
 * Created on 6.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarExistsDialog extends AbstractDialog {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.errorCarExistsMessage)
                .setPositiveButton(R.string.errorCarexistsClose, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        errorDialogListener.onErrorDialogNegativeClick(CarExistsDialog.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

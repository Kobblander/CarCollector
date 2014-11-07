package is.ru.app.CarCollector.utilities.dialog;

import android.app.Activity;
import android.app.DialogFragment;

/**
 * <h1>AbstractDialog</h1>
 * <h2>is.ru.app.CarCollector.utilities.dialog</h2>
 * <p></p>
 * Created on 6.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public abstract class AbstractDialog extends DialogFragment {

    protected ErrorDialogListener errorDialogListener;

    /**
     * If you want to add additional functions that the activities can handle.
     * Simply add them to this interface.
     */
    public interface ErrorDialogListener {
        public void onErrorDialogPositiveClick(DialogFragment dialog);
        public void onErrorDialogNegativeClick(DialogFragment dialog);
        public void onResetDialogPositiveClick(DialogFragment dialog);
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            errorDialogListener = (ErrorDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}

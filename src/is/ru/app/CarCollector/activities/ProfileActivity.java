package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import is.ru.app.CarCollector.R;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/30/2014
 * Time : 22:22
 */
public class ProfileActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.profile);
    }
}

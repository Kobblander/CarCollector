package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.hardware.Camera;
import android.view.View;
import android.widget.FrameLayout;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.utilities.CameraPreview;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/1/2014
 * Time : 19:51
 */
public class CameraActivity extends Activity {
    private Camera mCamera;
    private CameraPreview mPreview;

    public void setCamera(View view) {
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraFrame);
        preview.addView(mPreview);
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;

        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            e.printStackTrace();
        }

        return c; // returns null if camera is unavailable
    }
}

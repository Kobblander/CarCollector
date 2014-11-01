package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import android.widget.Toast;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.models.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQueryException;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.utilities.CameraPreview;

public class MainActivity extends Activity implements RestCallback {

    private Camera mCamera;
    private CameraPreview mPreview;
    private CarService carService = new CarServiceData(this);
    private boolean isCollectable = true;
    private static ProgressDialog progressDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.main);

        try {
            carService.addCar("SK109", this);

            /*List<Car> car1 = carService.getCarsBySubType("SUPERB", null);
            List<Car> car2 = carService.getCarsByType("SKODA", null);
            Car car3 = carService.getCarByRegistryNumber("MF078");
            Log.i("TEST_1: ", car1.get(0).toString());so
            Log.i("TEST_2: ", car2.get(0).toString());
            Log.i("TEST_3: ", car3.toString());
            */
        } catch (CarExistsException cee) {
            isCollectable = false;
            // TODO:
        } catch (Exception e) {
            // TODO: Handle CarServiceException. Basically means something went wrong when receiving the data.
            e.printStackTrace();
        }

    }

    @Override
    public void preExecute() {
        this.showProgressDialog("Retrieving the car.");
    }

    @Override
    public void postExecute(Car response, RestQueryException exception) {
        Log.i("MainActivity - CarService", "addCar");
        // If there is not an exception thrown.
        if (exception == null) {
            try {
                carService.addCarCallback(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.hideProgressDialog();
		//Toast.makeText(this, (CharSequence) response, Toast.LENGTH_LONG).show();
    }

    @Override
    public String inExecute() {
        return null;
    }

    @Override
    public void cancelExecute() {

    }

    public void profile(View view){
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }

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


	/**
	 * Shows a Progress Dialog with a Cancel Button
	 *
	 * @param msg
	 */
	public void showProgressDialog(String msg)
	{
		final RestCallback callback = this;

		// check for existing progressDialog
		if (progressDialog == null) {
			// create a progress Dialog
			progressDialog = new ProgressDialog(this);

			// remove the ability to hide it by tapping back button
			progressDialog.setIndeterminate(true);

			progressDialog.setCancelable(false);

			progressDialog.setMessage(msg);

			if (callback != null) {
				progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new Dialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Cancel the Task when user hits Cancel Button
							callback.cancelExecute();
						}
				});
			}
		}

		// now display it.
		progressDialog.show();
	}


	/**
	 * Hides the Progress Dialog
	 */
	public void hideProgressDialog() {

		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		progressDialog = null;
	}
}

package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.utilities.CameraPreview;

import java.util.List;

public class MainActivity extends Activity implements RestCallback {

    private Camera mCamera;
    private CameraPreview mPreview;
    CarService carService = new CarServiceData(this);

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
            //carService.addCar("MF078", this);
            List<Car> car1 = carService.getCarsBySubType("SUPERB", null);
            List<Car> car2 = carService.getCarsByType("SKODA", null);
            Car car3 = carService.getCarByRegistryNumber("MF078");
            Log.i("TEST_1: ", car1.get(0).toString());
            Log.i("TEST_2: ", car2.get(0).toString());
            Log.i("TEST_3: ", car3.toString());
        } catch (Exception e) {
            // TODO: Handle CarServiceException. Basically means something went wrong when receiving the data.
            e.printStackTrace();
        }

    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(Car response) {
        Log.i("MainActivity - CarService", "addCar");
        try {
            carService.addCarCallback(response);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle this error, basically means something went wrong when adding to the database.
        }
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
        }

        return c; // returns null if camera is unavailable
    }
}

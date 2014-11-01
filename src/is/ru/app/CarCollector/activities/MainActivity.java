package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.models.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.utilities.CameraPreview;

public class MainActivity extends Activity implements RestCallback {

    private Camera mCamera;
    private CameraPreview mPreview;
    private CarService carService = new CarServiceData(this);
    private boolean isCollectable = true;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.main);

        // Set search
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getCar(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
        });
    }

    private void getCar(String query) {
        try {
            carService.addCar(query, this);
        } catch (CarExistsException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
            e.printStackTrace();
        }

        return c; // returns null if camera is unavailable
    }
}

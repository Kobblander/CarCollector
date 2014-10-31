package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.cars.service.CarServiceException;

import java.util.List;

public class MainActivity extends Activity implements RestCallback {


    CarService carService = new CarServiceData(this);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}

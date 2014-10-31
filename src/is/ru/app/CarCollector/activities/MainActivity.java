package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;

public class MainActivity extends Activity implements RestCallback {


    CarService carService = CarServiceData.getInstance();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        carService.addCar("MF078", this);



    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(Car response) {
        Log.i("MainActivity - CarService", "addCar");
        carService.addCarCallback(response);
    }

    @Override
    public String inExecute() {
        return null;
    }

    @Override
    public void cancelExecute() {

    }
}

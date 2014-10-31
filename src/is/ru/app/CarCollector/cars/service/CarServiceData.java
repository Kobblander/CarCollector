package is.ru.app.CarCollector.cars.service;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.utilities.LogToFile;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>CarServiceData</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarServiceData implements CarService {

    private static CarService instance = null;

    private CarServiceData() {
    }

    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarServiceData();
        }
        return instance;
    }

    @Override
    public void addCar(String registryNumber, RestCallback callback) {
        Log.i("MainActivity - CarService", "addCar");
        RestQuery.getInstance().getCar(registryNumber, callback);

    }

    @Override
    public void addCarCallback(Car car) {
        Log.i("MainActivity - CarService", car.toString());
    }

    @Override
    public Car getCar(String registryNumber) {
        return null;
    }

}

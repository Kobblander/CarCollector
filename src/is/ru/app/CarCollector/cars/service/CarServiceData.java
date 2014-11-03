package is.ru.app.CarCollector.cars.service;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.data.CarData;
import is.ru.app.CarCollector.cars.data.CarDataGateway;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;

import java.util.List;

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

    CarDataGateway carDataGateway;

    public CarServiceData(Context ctx) {
        this.carDataGateway = new CarData(ctx);
    }

    @Override
    public void addCar(String registryNumber, RestCallback callback) throws CarExistsException, CarServiceException {
        // TODO: CHECK THE REGISTRY NUMBER STRING!!!
        try {
            if (carDataGateway.getCarByRegistryNumber(registryNumber) != null) {
                String msg = "The car with registry number: " + registryNumber + ", already " +
                        "exists in the database.";
                Log.i("CarServiceData", msg);
                throw new CarExistsException(msg);
            }
        } catch (SQLException e) {
            String msg = "Car not in database. Everything is OK. Nested exception is: " + e.getClass() + ": " + e.getMessage();
            Log.i("CarServiceData - addCar", msg);
            throw new CarServiceException(msg);
        }
        RestQuery.getInstance().getCar(registryNumber, callback);
    }

    @Override
    public void addCarCallback(Car car) throws CarServiceException {
        try {
            carDataGateway.addCar(car);
        } catch (SQLException e) {
            String msg = "Fatal error in CarService. Nested exception is: " + e.getMessage();
            Log.i("CarServiceData", msg);
            throw new CarServiceException(msg);
        }
    }

    @Override
    public List<Car> getCars(String limit) throws CarServiceException {
        try {
            return carDataGateway.getCars(limit);
        } catch (SQLException e) {
            String msg = "Fatal error in CarService. Nested exception is: " + e.getMessage();
            Log.i("CarServiceData", msg);
            throw new CarServiceException(msg);
        }
    }

    @Override
    public Car getCarByRegistryNumber(String registryNumber) throws CarServiceException {
        try {
            return carDataGateway.getCarByRegistryNumber(registryNumber);
        } catch (SQLException e) {
            String msg = "Fatal error in CarService. Nested exception is: " + e.getMessage();
            Log.i("CarServiceData", msg);
            throw new CarServiceException(msg);
        }
    }

    @Override
    public List<Car> getCarsByType(String type, String limit) throws CarServiceException {
        try {
            return carDataGateway.getCarsByType(type, limit);
        } catch (SQLException e) {
            String msg = "Fatal error in CarService. Nested exception is: " + e.getMessage();
            Log.i("CarServiceData", msg);
            throw new CarServiceException(msg);
        }
    }

    @Override
    public List<Car> getCarsBySubType(String subType, String limit) throws CarServiceException {
        try {
            return carDataGateway.getCarsBySubType(subType, limit);
        } catch (SQLException e) {
            String msg = "Fatal error in CarService. Nested exception is: " + e.getMessage();
            Log.i("CarServiceData", msg);
            throw new CarServiceException(msg);
        }
    }

    @Override
    public void addImage(String type, String subType, String color, RestCallback callback) {
        RestQuery.getInstance().queryImage(type, subType, color, callback);
    }


}

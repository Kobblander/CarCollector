package is.ru.app.CarCollector.cars.service;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;

/**
 * <h1>CarService</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface CarService {

    /**
     * This function adds a single car to the database given a registry number.
     * @param registryNumber The given registryNumber.
     */
    public void addCar(String registryNumber, RestCallback callback);

    /**
     * This function adds a single car to the database.
     * @param car
     */
    public void addCarCallback(Car car);

    /**
     * This function returns a single car given its licence plate number.
     * @param registryNumber
     * @return
     */
    public Car getCar(String registryNumber);

}

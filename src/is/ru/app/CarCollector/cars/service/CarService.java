package is.ru.app.CarCollector.cars.service;

import is.ru.app.CarCollector.cars.data.dto.Car;

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
     * This function adds a single car to the database given its licence plate.
     * @param registryNumber The given licence plate number.
     */
    public void addCar(String registryNumber);

    /**
     * This function returns a single car given its licence plate number.
     * @param registryNumber
     * @return
     */
    public Car getCar(String registryNumber);
}

package is.ru.app.CarCollector.cars.data.rest;

import is.ru.app.CarCollector.cars.data.dto.Car;

/**
 * <h1>RestQuery</h1>
 * <h2>is.ru.app.CarCollector.cars.data.rest</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface RestQuery {

    /**
     * Gets a car
     * @param licencePlate
     */
    public Car getCar(String licencePlate);
}

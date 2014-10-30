package is.ru.app.CarCollector.cars.data.gateway;

import is.ru.app.CarCollector.cars.data.dto.Car;

/**
 * <h1>CarDataGateway</h1>
 * <h2>is.ru.app.CarCollector.cars.data.database</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface CarDataGateway {

    public void addCar(String registryNumber);

    public Car getCarByRegistryNumber(String registryNumber);
}

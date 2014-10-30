package is.ru.app.CarCollector.cars.data.gateway;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;

/**
 * <h1>CarData</h1>
 * <h2>is.ru.app.CarCollector.cars.data.database</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarData implements CarDataGateway {

    RestQuery restQuery;

    public CarData() {
    }

    @Override
    public void addCar(String registryNumber) {

    }

    @Override
    public Car getCarByRegistryNumber(String registryNumber) {
        return null;
    }

    public void setRestQuery(RestQuery restQuery) {
        this.restQuery = restQuery;
    }

}

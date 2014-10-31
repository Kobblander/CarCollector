package is.ru.app.CarCollector.cars.data.gateway;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.utilities.LogToFile;

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

    public CarData(RestQuery restQuery) {
        this.restQuery = restQuery;
    }

    @Override
    public void addCar(String registryNumber) {
        LogToFile.log(null, "info", "CarDataCarLogged");
    }

    @Override
    public Car getCarByRegistryNumber(String registryNumber) {
        return null;
    }

}

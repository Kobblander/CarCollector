package is.ru.app.CarCollector.cars.data;

import is.ru.app.CarCollector.cars.models.Car;

import java.util.List;

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

    public void addCar(Car car);

    public Car getCarByRegistryNumber(String registryNumber);

    public List<Car> getCarsByType(String type, String limit);

    public List<Car> getCarsBySubType(String subType, String limit);

    public List<Car> getCars(String limit);
}

package is.ru.app.CarCollector.cars.service;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.data.gateway.CarDataGateway;

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

    private CarDataGateway carDataGateway;

    public CarServiceData(CarDataGateway carDataGateway) {
        this.carDataGateway = carDataGateway;
    }


    @Override
    public void addCar(String registryNumber) {
        
    }

    @Override
    public Car getCar(String registryNumber) {
        return null;
    }
}

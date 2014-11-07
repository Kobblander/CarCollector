package is.ru.app.CarCollector.cars.service;

import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;

import java.util.List;

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
     * @param callback This is the activity that is supposed to be called back to.
     * @throws CarExistsException Is thrown when the car already exists in the database.
     */
    public Car addCar(String registryNumber, RestCallback callback) throws CarExistsException, CarServiceException;

    /**
     * This function adds a single car to the database.
     * @param car The given car to add.
     */
    public void addCarCallback(Car car) throws CarServiceException;

    /**
     * This function returns all cars given a limit.
     * @param limit How many cars to receive, if null gets ALL cars.
     * @return A cursor that can be used to access the data.
     */
    public List<Car> getCars(String limit) throws CarServiceException;

    /**
     * This function returns a single car given its licence plate number.
     * @param registryNumber The given registryNumber to query by.
     * @return A cursor that can be used to access the data.
     */
    public Car getCarByRegistryNumber(String registryNumber) throws CarServiceException;

    /**
     * This function returns all cars given their type and a limit.
     * @param type The type to query the cars by.
     * @param limit How many cars to receive, if null gets ALL cars.
     * @return A cursor that can be used to access the data.
     */
    public List<Car> getCarsByType(String type, String limit) throws CarServiceException;

    /**
     * This function returns all cars given their type and a limit.
     * @param subType The subtype to query the cars by.
     * @param limit How many cars to receive, if null gets ALL cars.
     * @return A cursor that can be used to access the data.
     */
    public List<Car> getCarsBySubType(String subType, String limit) throws CarServiceException;

    /**
     * Query image in Google API
     * @param type Car type
     * @param subType Car subtype
     * @param color Car color
     * @param restCallback callback
     */
    public void addImage(String type, String subType, String color, String registered, RestCallback restCallback);

}

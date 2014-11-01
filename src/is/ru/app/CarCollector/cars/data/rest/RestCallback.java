package is.ru.app.CarCollector.cars.data.rest;

import is.ru.app.CarCollector.cars.data.models.Car;

/**
 * <h1>RestCallback</h1>
 * <h2>is.ru.app.CarCollector.cars.data.rest</h2>
 * <p></p>
 * Created on 30.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface RestCallback {
    public void preExecute();
    public void postExecute(Car response);
    public String inExecute();
    public void cancelExecute();
}

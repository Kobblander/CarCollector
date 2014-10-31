package is.ru.app.CarCollector.cars.service;

/**
 * <h1>CarExistsException</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarExistsException extends Exception {

    public CarExistsException() {
    }

    public CarExistsException(String detailMessage) {
        super(detailMessage);
    }

    public CarExistsException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CarExistsException(Throwable throwable) {
        super(throwable);
    }
}

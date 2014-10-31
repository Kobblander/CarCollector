package is.ru.app.CarCollector.cars.service;

/**
 * <h1>CarServiceException</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarServiceException extends Exception {

    public CarServiceException() {
    }

    public CarServiceException(String detailMessage) {
        super(detailMessage);
    }

    public CarServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CarServiceException(Throwable throwable) {
        super(throwable);
    }
}

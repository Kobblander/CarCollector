package is.ru.app.CarCollector.cars.service;

/**
 * <h1>NoCarFoundException</h1>
 * <h2>is.ru.app.CarCollector.cars.data.rest</h2>
 * <p></p>
 * Created on 30.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException() {
    }

    public CarNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public CarNotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CarNotFoundException(Throwable throwable) {
        super(throwable);
    }
}

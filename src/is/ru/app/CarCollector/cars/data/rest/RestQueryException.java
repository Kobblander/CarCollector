package is.ru.app.CarCollector.cars.data.rest;

/**
 * <h1>RestQueryException</h1>
 * <h2>is.ru.app.CarCollector.cars.data.rest</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class RestQueryException extends RuntimeException {

    public RestQueryException() {
    }

    public RestQueryException(String detailMessage) {
        super(detailMessage);
    }

    public RestQueryException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RestQueryException(Throwable throwable) {
        super(throwable);
    }
}

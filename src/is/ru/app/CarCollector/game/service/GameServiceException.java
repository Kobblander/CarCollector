package is.ru.app.CarCollector.game.service;

/**
 * <h1>GameServiceException</h1>
 * <h2>is.ru.app.CarCollector.game.service</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class GameServiceException extends Exception {

    public GameServiceException() {
    }

    public GameServiceException(String detailMessage) {
        super(detailMessage);
    }

    public GameServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public GameServiceException(Throwable throwable) {
        super(throwable);
    }
}

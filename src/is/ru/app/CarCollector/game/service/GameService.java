package is.ru.app.CarCollector.game.service;

import is.ru.app.CarCollector.cars.data.models.Car;

/**
 * <h1>GameService</h1>
 * <h2>is.ru.app.CarCollector.game.service</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface GameService {

    /**
     * This function updates the statistics of the player.
     * It calculates it based on type and subtype.
     * @param car The car which was added toward the stats.
     */
    public void updateStats(Car car);

    public void getStats();

}

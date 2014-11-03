package is.ru.app.CarCollector.game.models;

import java.util.List;

/**
 * <h1>Statistics</h1>
 * <h2>is.ru.app.CarCollector.game.models</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Statistics {

    private Player player;
    private List<CarSubType> carSubTypes;
    private List<CarType> carTypes;

    public Statistics() {
    }

    public Statistics(Player player, List<CarSubType> carSubTypes, List<CarType> carTypes) {
        this.player = player;
        this.carSubTypes = carSubTypes;
        this.carTypes = carTypes;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<CarSubType> getCarSubTypes() {
        return carSubTypes;
    }

    public void setCarSubTypes(List<CarSubType> carSubTypes) {
        this.carSubTypes = carSubTypes;
    }

    public List<CarType> getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(List<CarType> carTypes) {
        this.carTypes = carTypes;
    }
}

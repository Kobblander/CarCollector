package is.ru.app.CarCollector.game.xp;

import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Player;

/**
 * <h1>XpSystem</h1>
 * <h2>is.ru.app.CarCollector.game.xp</h2>
 * <p></p>
 * Created on 4.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface XpSystem {

    public void calculateXp(Player player, CarType carType, CarSubType carSubType);
}

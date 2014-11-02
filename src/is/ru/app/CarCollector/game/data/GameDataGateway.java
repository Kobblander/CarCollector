package is.ru.app.CarCollector.game.data;

import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Player;
import is.ru.app.CarCollector.game.models.Statistics;

import java.util.List;

/**
 * <h1>GameDataGateway</h1>
 * <h2>is.ru.app.CarCollector.game.data</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public interface GameDataGateway {

    public void addPlayer(Player player);

    public void addCarType(CarType carType);

    public void addCarSubType(CarSubType carSubType);

    public List<CarType> getCarTypes();

    public List<CarSubType> getCarSubTypes();

    public List<CarType> getCarTypesByName(String carTypeName);

    public List<CarSubType> getCarSubTypesByName(String carSubTypeName);

    public List<CarType> getCarTypesByNameAndPlayer(String carTypeName, String playerName);

    public List<CarSubType> getCarSubTypesByNameAndPlayer(String carSubTypeName, String playerName);

}

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

    public Player addPlayer(Player player);

    public CarType addCarType(CarType carType);

    public CarSubType addCarSubType(CarSubType carSubType);

    public Player getPlayer(String playerName);

    public List<CarType> getCarTypes();

    public List<CarSubType> getCarSubTypesByTypeName(String typeName);

    public List<CarSubType> getCarSubTypes();

    public CarType getCarTypeByName(String carTypeName);

    public CarSubType getCarSubTypeByName(String carSubTypeName);

    public List<CarType> getCarTypesByNameAndPlayer(String carTypeName, String playerName);

    public List<CarSubType> getCarSubTypesByNameAndPlayer(String carSubTypeName, String playerName);

    public CarType getCarTypeByNameAndPlayer(String carTypeName, String playerName);

    public CarSubType getCarSubTypeByNameAndPlayer(String carSubTypeName, String playerName);

    public List<CarSubType> getCarSubTypesByTypeId(int typeId);

    public Long updateCarType(CarType carType);

    public Long updateCarSubType(CarSubType carSubType);

}

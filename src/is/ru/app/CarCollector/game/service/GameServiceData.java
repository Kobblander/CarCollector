package is.ru.app.CarCollector.game.service;

import android.content.Context;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.game.data.GameData;
import is.ru.app.CarCollector.game.data.GameDataGateway;
import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Statistics;
import is.ru.app.CarCollector.game.xp.XpCalculator;
import is.ru.app.CarCollector.game.xp.XpSystem;

/**
 * <h1>GameServiceData</h1>
 * <h2>is.ru.app.CarCollector.game.service</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class GameServiceData implements GameService {

    GameDataGateway gameDataGateway;
    XpSystem xpSystem;

    public GameServiceData(Context ctx) {
        this.gameDataGateway = new GameData(ctx);
        this.xpSystem = new XpCalculator();
    }

    @Override
    public void updateStats(Car car) throws GameServiceException {
        String typeName = car.getType();
        String subTypeName = car.getSubType();
        try {

            CarType carType = gameDataGateway.getCarTypeByName(typeName);
            CarSubType carSubType = gameDataGateway.getCarSubTypeByName(subTypeName);
            if (carType == null && carSubType == null) {
                carType = new CarType(typeName);
                carSubType = new CarSubType(typeName, subTypeName);

                xpSystem.calculateXp(null, carType, carSubType);

                Log.i("GameServiceData", "Adding both Type and Subtype to database.");
                gameDataGateway.addCarType(carType);
                gameDataGateway.addCarSubType(carSubType);
            } else if (carSubType == null) {
                carSubType = new CarSubType(typeName, subTypeName);

                xpSystem.calculateXp(null, carType, carSubType);

                Log.i("GameServiceData", "Updating carType and adding carSubType.");
                gameDataGateway.updateCarType(carType);
                gameDataGateway.addCarSubType(carSubType);
            } else {

                xpSystem.calculateXp(null, carType, carSubType);

                Log.i("GameServiceData", "Updating botch carType and carSubType.");
                gameDataGateway.updateCarType(carType);
                gameDataGateway.updateCarSubType(carSubType);
            }

        } catch (Exception e) {
            String msg = "Fatal error in GameService; Nested exception is: " + e.getMessage();
            throw new GameServiceException(msg, e);
        }
    }

    @Override
    public Statistics getStats() throws GameServiceException {
        Statistics stats = new Statistics();
        try {
            stats.setPlayer(null);
            stats.setCarTypes(gameDataGateway.getCarTypes());
            stats.setCarSubTypes(gameDataGateway.getCarSubTypes());
        } catch (Exception e) {
            String msg = "Fatal error in GameService; Nested exception is: " + e.getMessage();
            throw new GameServiceException(msg, e);
        }
        return stats;
    }

    @Override
    public Statistics getLevelXpScreenData() {
        return null;
    }

}

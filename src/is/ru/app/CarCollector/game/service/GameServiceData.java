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
    XpCalculator xpCalculator = XpCalculator.getInstance();

    public GameServiceData(Context ctx) {
        this.gameDataGateway = new GameData(ctx);
    }

    @Override
    public void updateStats(Car car) throws GameServiceException {
        String typeName = car.getType();
        String subTypeName = car.getSubType();
        try {

            CarType carType = gameDataGateway.getCarTypeByName(typeName);
            CarSubType carSubType = gameDataGateway.getCarSubTypeByName(subTypeName);
            if (carType == null && carSubType == null) {
                Log.i("GameServiceData", "Adding both Type and Subtype to database.");
                carType = new CarType(typeName);
                carSubType = new CarSubType(typeName, subTypeName);

                xpCalculator.initCarType(carType);
                xpCalculator.initCarSubType(carSubType);

                gameDataGateway.addCarType(carType);
                gameDataGateway.addCarSubType(carSubType);
            } else if (carSubType == null) {
                Log.i("GameServiceData", "Updating carType and adding carSubType.");
                carSubType = new CarSubType(typeName, subTypeName);

                xpCalculator.updateCarType(carType);
                xpCalculator.initCarSubType(carSubType);

                gameDataGateway.updateCarType(carType);
                gameDataGateway.addCarSubType(carSubType);
            } else {
                Log.i("GameServiceData", "Updating botch carType and carSubType.");

                xpCalculator.updateCarType(carType);
                xpCalculator.updateCarSubType(carSubType);

                gameDataGateway.updateCarType(carType);
                gameDataGateway.updateCarSubType(carSubType);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

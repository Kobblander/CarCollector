package is.ru.app.CarCollector.game.service;

import android.content.Context;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.game.data.GameData;
import is.ru.app.CarCollector.game.data.GameDataGateway;
import is.ru.app.CarCollector.game.models.*;
import is.ru.app.CarCollector.game.xp.XpCalculator;

import java.util.ArrayList;
import java.util.List;

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
    public void updateStats(Car car, Player player) throws GameServiceException {
        String playerName = player.getPlayerName();
        String typeName = car.getType();
        String subTypeName = car.getSubType();
        try {

            CarType carType = gameDataGateway.getCarTypeByNameAndPlayer(typeName, playerName);
            CarSubType carSubType = gameDataGateway.getCarSubTypeByNameAndPlayer(subTypeName, playerName);
            if (carType == null && carSubType == null) {
                Log.i("GameServiceData", "Adding both Type and Subtype to database.");
                carType = new CarType(playerName, typeName);
                carSubType = new CarSubType(playerName, typeName, subTypeName);

                xpCalculator.initCarType(carType);
                xpCalculator.initCarSubType(carSubType);

                xpCalculator.updateCarType(carType);
                xpCalculator.updateCarSubType(carSubType);

                gameDataGateway.addCarType(carType);
                gameDataGateway.addCarSubType(carSubType);
            } else if (carSubType == null) {
                Log.i("GameServiceData", "Updating carType and adding carSubType.");
                carSubType = new CarSubType(playerName, typeName, subTypeName);

                xpCalculator.initCarSubType(carSubType);

                xpCalculator.updateCarType(carType);
                xpCalculator.updateCarSubType(carSubType);

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
        List<TypeStats> typeStats = new ArrayList<TypeStats>();
        try {
            Player player = gameDataGateway.getPlayer("Captain America");
            stats.setPlayer(player);


            List<CarType> carTypeList = gameDataGateway.getCarTypes();
            for (CarType aCarTypeList : carTypeList) {
                TypeStats ts = new TypeStats();
                ts.setCarType(aCarTypeList);
                String typeName = aCarTypeList.getTypeName();
                ts.setCarSubTypes(gameDataGateway.getCarSubTypesByTypeName(typeName));
                typeStats.add(ts);
            }
            stats.setTypeStats(typeStats);

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

    @Override
    public Player addPlayer(Player player) throws GameServiceException {
        try {
            player = gameDataGateway.addPlayer(player);
            xpCalculator.initPlayer(player);
        } catch (Exception e) {
            String msg = "Fatal error in GameService; Nested exception is: " + e.getMessage();
            throw new GameServiceException(msg, e);
        }
        return player;
    }

}

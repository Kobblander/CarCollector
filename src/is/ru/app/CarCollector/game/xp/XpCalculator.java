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
public class XpCalculator implements XpSystem {

    private Player player;
    private CarType carType;
    private CarSubType carSubType;

    // Starting baseXp and starting xpForLevel
    private final double baseXpStart = 0;
    private final double baseXpForLevel = 100;

    // Min amount of xp you can gain
    private final double xpMin = 20;

    // Factor which xpForLevel will be increased each levelUp
    private final double xpForLevelFactor = 0.2;

    /**
     * Current implementation will not calculate xp for carSubType, simply add to number of cars.
     * It will increase xp for carType and from that increase xp for player.
     * @param _player The player to be updated.
     * @param _carType The carType to be updated.
     * @param _carSubType The carSubType to be updated.
     */
    @Override
    public void calculateXp(Player _player, CarType _carType, CarSubType _carSubType) {
        player = _player;
        carType = _carType;
        carSubType = _carSubType;

        calculateCarSubType();
        calculateCarType();
        calculatePlayer();
    }

    private static void calculatePlayer() {

    }

    private void calculateCarType() {
        int level = carType.getLevelCur();
        double levelXp = carType.getLevelXpCur();
        double xpForNextLevel = carType.getXpForNextLevelCur();
        double totalXp = carType.getTotalXpCur();

        // Set the old values
        carType.setLevelOld(level);
        carType.setLevelXpOld(xpForNextLevel);
        carType.setTotalXpOld(totalXp);
        carType.setXpForNextLevelOld(xpForNextLevel);

        // Calculate new values
        double newXp = levelXp + xpMin;
        if (xpForNextLevel < newXp) {
            newXp = newXp - xpForNextLevel;
            level++;
            xpForNextLevel = xpForNextLevel * xpForLevelFactor;
        }



    }

    private void calculateCarSubType() {
        int currentTotalCars = carSubType.getTotalCarsCur();
        carSubType.setTotalCarsCur(currentTotalCars + 1);
        carSubType.setTotalCarsOld(currentTotalCars);
    }
}

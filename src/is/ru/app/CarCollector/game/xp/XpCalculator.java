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
public class XpCalculator {

    private static XpCalculator instance = new XpCalculator();

    public static XpCalculator getInstance() { return instance; }

    private XpCalculator() {

    }

    // Starting baseXp and starting xpForLevel
    private final double baseXpStart = 5;
    private final double basePlayerXpForLevel = 300;
    private final double baseCarTypeXpForLevel = 100;

    // Min amount of xp you can gain
    private final double xpMin = 20;

    // Factor which xpForLevel will be increased each levelUp
    private final double carTypeLevelFactor = 0.2;
    private final double playerLevelFactor = 0.2;

    public void updatePlayer(Player player) {
        // Get the current values
        int level = player.getLevelCur();
        double levelXp = player.getLevelXpCur();
        double xpForNextLevel = player.getXpForNextLevelCur();
        double totalXp = player.getTotalXpCur();

        // Set the old values
        player.setLevelOld(level);
        player.setLevelXpOld(xpForNextLevel);
        player.setTotalXpOld(totalXp);
        player.setXpForNextLevelOld(xpForNextLevel);

        // Calculate new xp values
        levelXp += xpMin;
        totalXp += xpMin;

        // If level up, change some values.
        if (xpForNextLevel < levelXp) {
            levelXp -= xpForNextLevel;
            level++;
            xpForNextLevel += xpForNextLevel * playerLevelFactor;
        }

        // Set the new values
        player.setLevelCur(level);
        player.setLevelXpCur(levelXp);
        player.setTotalXpCur(totalXp);
        player.setXpForNextLevelCur(xpForNextLevel);

    }

    public void updateCarType(CarType carType) {
        // Get the current values
        int level = carType.getLevelCur();
        double levelXp = carType.getLevelXpCur();
        double xpForNextLevel = carType.getXpForNextLevelCur();
        double totalXp = carType.getTotalXpCur();

        // Set the old values
        carType.setLevelOld(level);
        carType.setLevelXpOld(xpForNextLevel);
        carType.setTotalXpOld(totalXp);
        carType.setXpForNextLevelOld(xpForNextLevel);

        // Calculate new xp values
        levelXp += xpMin;
        totalXp += xpMin;

        // If level up, change some values.
        if (xpForNextLevel < levelXp) {
            levelXp -= xpForNextLevel;
            level++;
            xpForNextLevel += xpForNextLevel * carTypeLevelFactor;
        }

        // Set the new values
        carType.setLevelCur(level);
        carType.setLevelXpCur(levelXp);
        carType.setTotalXpCur(totalXp);
        carType.setXpForNextLevelCur(xpForNextLevel);

    }

    public void updateCarSubType(CarSubType carSubType) {
        int currentTotalCars = carSubType.getTotalCarsCur();
        carSubType.setTotalCarsCur(currentTotalCars + 1);
        carSubType.setTotalCarsOld(currentTotalCars);
    }

    public void initPlayer(Player player) {
        player.setLevelCur(0);
        player.setLevelOld(0);
        player.setLevelXpCur(baseXpStart);
        player.setLevelXpOld(baseXpStart);
        player.setTotalXpCur(baseXpStart);
        player.setTotalXpOld(baseXpStart);
        player.setXpForNextLevelCur(basePlayerXpForLevel);
        player.setXpForNextLevelOld(basePlayerXpForLevel);
    }

    public void initCarType(CarType carType) {
        carType.setLevelCur(0);
        carType.setLevelOld(0);
        carType.setLevelXpCur(baseXpStart);
        carType.setLevelXpOld(baseXpStart);
        carType.setTotalXpCur(baseXpStart);
        carType.setTotalXpOld(baseXpStart);
        carType.setXpForNextLevelCur(baseCarTypeXpForLevel);
        carType.setXpForNextLevelOld(baseCarTypeXpForLevel);
    }

    public void initCarSubType(CarSubType carSubType) {
        carSubType.setLevelCur(0);
        carSubType.setLevelOld(0);
        carSubType.setLevelXpCur(0);
        carSubType.setLevelXpOld(0);
        carSubType.setTotalXpCur(0);
        carSubType.setTotalXpOld(0);
        carSubType.setXpForNextLevelCur(baseCarTypeXpForLevel);
        carSubType.setXpForNextLevelOld(baseCarTypeXpForLevel);
        carSubType.setTotalCarsCur(0);
        carSubType.setTotalCarsOld(0);
    }
}

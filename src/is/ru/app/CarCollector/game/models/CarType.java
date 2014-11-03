package is.ru.app.CarCollector.game.models;

/**
 * <h1>TypeXp</h1>
 * <h2>is.ru.app.CarCollector.cars.data.dto</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarType {

    private int _id;
    private String playerName;
    private String typeName;
    private int level;
    private float xpForNextLevel;
    private float levelXp;
    private float totalXp;

    public CarType() {
    }

    public CarType(String typeName) {
        this.typeName = typeName;
    }

    public CarType(String playerName, String typeName) {
        this.playerName = playerName;
        this.typeName = typeName;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPlayerId() {
        return playerName;
    }

    public void setPlayer(String playerName) {
        this.playerName = playerName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getXpForNextLevel() {
        return xpForNextLevel;
    }

    public void setXpForNextLevel(float xpForNextLevel) {
        this.xpForNextLevel = xpForNextLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public float getLevelXp() {
        return levelXp;
    }

    public void setLevelXp(float levelXp) {
        this.levelXp = levelXp;
    }

    public float getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(float totalXp) {
        this.totalXp = totalXp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarType)) return false;

        CarType carType = (CarType) o;

        if (_id != carType._id) return false;
        if (level != carType.level) return false;
        if (playerName != carType.playerName) return false;
        if (xpForNextLevel != carType.xpForNextLevel) return false;
        if (typeName != null ? !typeName.equals(carType.typeName) : carType.typeName != null) return false;

        return true;
    }

}

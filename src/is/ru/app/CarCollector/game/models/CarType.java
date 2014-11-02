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
    private int playerId;
    private String typeName;
    private int level;
    private float xpForNextLevel;

    public CarType() {
    }

    public CarType(int _id, String typeName, int playerId, int level, float xpForNextLevel) {
        this._id = _id;
        this.typeName = typeName;
        this.playerId = playerId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
    }

    public CarType(String typeName, int playerId, int level, float xpForNextLevel) {
        this.typeName = typeName;
        this.playerId = playerId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarType)) return false;

        CarType carType = (CarType) o;

        if (_id != carType._id) return false;
        if (level != carType.level) return false;
        if (playerId != carType.playerId) return false;
        if (xpForNextLevel != carType.xpForNextLevel) return false;
        if (typeName != null ? !typeName.equals(carType.typeName) : carType.typeName != null) return false;

        return true;
    }

}

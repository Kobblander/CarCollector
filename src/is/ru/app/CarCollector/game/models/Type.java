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
public class Type {

    private int _id;

    private String typeName;
    private int playerId;
    private int level;
    private int xpForNextLevel;

    public Type(int _id, String typeName, int playerId, int level, int xpForNextLevel) {
        this._id = _id;
        this.typeName = typeName;
        this.playerId = playerId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
    }

    public Type(String typeName, int playerId, int level, int xpForNextLevel) {
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

    public int getXpForNextLevel() {
        return xpForNextLevel;
    }

    public void setXpForNextLevel(int xpForNextLevel) {
        this.xpForNextLevel = xpForNextLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;

        Type type = (Type) o;

        if (_id != type._id) return false;
        if (level != type.level) return false;
        if (playerId != type.playerId) return false;
        if (xpForNextLevel != type.xpForNextLevel) return false;
        if (typeName != null ? !typeName.equals(type.typeName) : type.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + playerId;
        result = 31 * result + level;
        result = 31 * result + xpForNextLevel;
        return result;
    }
}

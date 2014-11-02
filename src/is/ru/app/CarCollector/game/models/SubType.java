package is.ru.app.CarCollector.game.models;

/**
 * <h1>SybType</h1>
 * <h2>is.ru.app.CarCollector.cars.data.dto</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class SubType {

    private int _id;

    private String subTypeName;
    private int typeId;
    private int level;
    private int xpForNextLevel;
    private int totalCars;

    public SubType(int _id, String subTypeName, int typeId, int level, int xpForNextLevel, int totalCars) {
        this._id = _id;
        this.subTypeName = subTypeName;
        this.typeId = typeId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
        this.totalCars = totalCars;
    }

    public SubType(String subTypeName, int typeId, int level, int xpForNextLevel, int totalCars) {
        this.subTypeName = subTypeName;
        this.typeId = typeId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
        this.totalCars = totalCars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public int getTotalCars() {
        return totalCars;
    }

    public void setTotalCars(int totalCars) {
        this.totalCars = totalCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubType)) return false;

        SubType subType = (SubType) o;

        if (_id != subType._id) return false;
        if (level != subType.level) return false;
        if (totalCars != subType.totalCars) return false;
        if (typeId != subType.typeId) return false;
        if (xpForNextLevel != subType.xpForNextLevel) return false;
        if (subTypeName != null ? !subTypeName.equals(subType.subTypeName) : subType.subTypeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (subTypeName != null ? subTypeName.hashCode() : 0);
        result = 31 * result + typeId;
        result = 31 * result + level;
        result = 31 * result + xpForNextLevel;
        result = 31 * result + totalCars;
        return result;
    }
}

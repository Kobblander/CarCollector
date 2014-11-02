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
public class CarSubType {

    private int _id;
    private int typeId;
    private String subTypeName;
    private int level;
    private float xpForNextLevel;
    private int totalCars;

    public CarSubType() {
    }

    public CarSubType(int _id, String subTypeName, int typeId, int level, float xpForNextLevel, int totalCars) {
        this._id = _id;
        this.subTypeName = subTypeName;
        this.typeId = typeId;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
        this.totalCars = totalCars;
    }

    public CarSubType(String subTypeName, int typeId, int level, float xpForNextLevel, int totalCars) {
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

    public float getXpForNextLevel() {
        return xpForNextLevel;
    }

    public void setXpForNextLevel(float xpForNextLevel) {
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
        if (!(o instanceof CarSubType)) return false;

        CarSubType carSubType = (CarSubType) o;

        if (_id != carSubType._id) return false;
        if (level != carSubType.level) return false;
        if (totalCars != carSubType.totalCars) return false;
        if (typeId != carSubType.typeId) return false;
        if (xpForNextLevel != carSubType.xpForNextLevel) return false;
        if (subTypeName != null ? !subTypeName.equals(carSubType.subTypeName) : carSubType.subTypeName != null) return false;

        return true;
    }

}

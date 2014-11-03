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
    private String typeName;
    private String subTypeName;
    private int level;
    private float xpForNextLevel;
    private float levelXp;
    private float totalXp;
    private int totalCars;

    public CarSubType() {
    }

    public CarSubType(String typeName, String subTypeName) {
        this.typeName = typeName;
        this.subTypeName = subTypeName;
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

    public String getTypeId() {
        return typeName;
    }

    public void setTypeId(String typeName) {
        this.typeName = typeName;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        if (!(o instanceof CarSubType)) return false;

        CarSubType carSubType = (CarSubType) o;

        if (_id != carSubType._id) return false;
        if (level != carSubType.level) return false;
        if (totalCars != carSubType.totalCars) return false;
        if (typeName != carSubType.typeName) return false;
        if (xpForNextLevel != carSubType.xpForNextLevel) return false;
        if (subTypeName != null ? !subTypeName.equals(carSubType.subTypeName) : carSubType.subTypeName != null) return false;

        return true;
    }

}

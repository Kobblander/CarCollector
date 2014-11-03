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
    private int levelCur;
    private int levelOld;
    private float xpForNextLevelCur;
    private float xpForNextLevelOld;
    private float levelXpCur;
    private float levelXpOld;
    private float totalXpCur;
    private float totalXpOld;
    private int totalCarsCur;
    private int totalCarsOld;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLevelCur() {
        return levelCur;
    }

    public void setLevelCur(int levelCur) {
        this.levelCur = levelCur;
    }

    public int getLevelOld() {
        return levelOld;
    }

    public void setLevelOld(int levelOld) {
        this.levelOld = levelOld;
    }

    public float getXpForNextLevelCur() {
        return xpForNextLevelCur;
    }

    public void setXpForNextLevelCur(float xpForNextLevelCur) {
        this.xpForNextLevelCur = xpForNextLevelCur;
    }

    public float getXpForNextLevelOld() {
        return xpForNextLevelOld;
    }

    public void setXpForNextLevelOld(float xpForNextLevelOld) {
        this.xpForNextLevelOld = xpForNextLevelOld;
    }

    public float getLevelXpCur() {
        return levelXpCur;
    }

    public void setLevelXpCur(float levelXpCur) {
        this.levelXpCur = levelXpCur;
    }

    public float getLevelXpOld() {
        return levelXpOld;
    }

    public void setLevelXpOld(float levelXpOld) {
        this.levelXpOld = levelXpOld;
    }

    public float getTotalXpCur() {
        return totalXpCur;
    }

    public void setTotalXpCur(float totalXpCur) {
        this.totalXpCur = totalXpCur;
    }

    public float getTotalXpOld() {
        return totalXpOld;
    }

    public void setTotalXpOld(float totalXpOld) {
        this.totalXpOld = totalXpOld;
    }

    public int getTotalCarsCur() {
        return totalCarsCur;
    }

    public void setTotalCarsCur(int totalCarsCur) {
        this.totalCarsCur = totalCarsCur;
    }

    public int getTotalCarsOld() {
        return totalCarsOld;
    }

    public void setTotalCarsOld(int totalCarsOld) {
        this.totalCarsOld = totalCarsOld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarSubType)) return false;

        CarSubType that = (CarSubType) o;

        if (_id != that._id) return false;
        if (levelCur != that.levelCur) return false;
        if (levelOld != that.levelOld) return false;
        if (Float.compare(that.levelXpCur, levelXpCur) != 0) return false;
        if (Float.compare(that.levelXpOld, levelXpOld) != 0) return false;
        if (totalCarsCur != that.totalCarsCur) return false;
        if (totalCarsOld != that.totalCarsOld) return false;
        if (Float.compare(that.totalXpCur, totalXpCur) != 0) return false;
        if (Float.compare(that.totalXpOld, totalXpOld) != 0) return false;
        if (Float.compare(that.xpForNextLevelCur, xpForNextLevelCur) != 0) return false;
        if (Float.compare(that.xpForNextLevelOld, xpForNextLevelOld) != 0) return false;
        if (subTypeName != null ? !subTypeName.equals(that.subTypeName) : that.subTypeName != null) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (subTypeName != null ? subTypeName.hashCode() : 0);
        result = 31 * result + levelCur;
        result = 31 * result + levelOld;
        result = 31 * result + (xpForNextLevelCur != +0.0f ? Float.floatToIntBits(xpForNextLevelCur) : 0);
        result = 31 * result + (xpForNextLevelOld != +0.0f ? Float.floatToIntBits(xpForNextLevelOld) : 0);
        result = 31 * result + (levelXpCur != +0.0f ? Float.floatToIntBits(levelXpCur) : 0);
        result = 31 * result + (levelXpOld != +0.0f ? Float.floatToIntBits(levelXpOld) : 0);
        result = 31 * result + (totalXpCur != +0.0f ? Float.floatToIntBits(totalXpCur) : 0);
        result = 31 * result + (totalXpOld != +0.0f ? Float.floatToIntBits(totalXpOld) : 0);
        result = 31 * result + totalCarsCur;
        result = 31 * result + totalCarsOld;
        return result;
    }

    @Override
    public String toString() {
        return "CarSubType{" +
                "_id=" + _id +
                ", typeName='" + typeName + '\'' +
                ", subTypeName='" + subTypeName + '\'' +
                ", levelCur=" + levelCur +
                ", levelOld=" + levelOld +
                ", xpForNextLevelCur=" + xpForNextLevelCur +
                ", xpForNextLevelOld=" + xpForNextLevelOld +
                ", levelXpCur=" + levelXpCur +
                ", levelXpOld=" + levelXpOld +
                ", totalXpCur=" + totalXpCur +
                ", totalXpOld=" + totalXpOld +
                ", totalCarsCur=" + totalCarsCur +
                ", totalCarsOld=" + totalCarsOld +
                '}';
    }
}

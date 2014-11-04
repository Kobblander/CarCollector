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
    private double xpForNextLevelCur;
    private double xpForNextLevelOld;
    private double levelXpCur;
    private double levelXpOld;
    private double totalXpCur;
    private double totalXpOld;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
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

    public double getXpForNextLevelCur() {
        return xpForNextLevelCur;
    }

    public void setXpForNextLevelCur(double xpForNextLevelCur) {
        this.xpForNextLevelCur = xpForNextLevelCur;
    }

    public double getXpForNextLevelOld() {
        return xpForNextLevelOld;
    }

    public void setXpForNextLevelOld(double xpForNextLevelOld) {
        this.xpForNextLevelOld = xpForNextLevelOld;
    }

    public double getLevelXpCur() {
        return levelXpCur;
    }

    public void setLevelXpCur(double levelXpCur) {
        this.levelXpCur = levelXpCur;
    }

    public double getLevelXpOld() {
        return levelXpOld;
    }

    public void setLevelXpOld(double levelXpOld) {
        this.levelXpOld = levelXpOld;
    }

    public double getTotalXpCur() {
        return totalXpCur;
    }

    public void setTotalXpCur(double totalXpCur) {
        this.totalXpCur = totalXpCur;
    }

    public double getTotalXpOld() {
        return totalXpOld;
    }

    public void setTotalXpOld(double totalXpOld) {
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

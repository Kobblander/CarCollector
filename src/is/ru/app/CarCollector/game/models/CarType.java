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
    private int levelCur;
    private int levelOld;
    private double xpForNextLevelCur;
    private double xpForNextLevelOld;
    private double levelXpCur;
    private double levelXpOld;
    private double totalXpCur;
    private double totalXpOld;

    public CarType() {
    }

    public CarType(String typeName) {
        this.typeName = typeName;
        this.xpForNextLevelCur = 0.0f;
        this.xpForNextLevelOld = 0.0f;
        this.levelXpCur = 0.0f;
        this.levelXpOld = 0.0f;
        this.totalXpCur = 0.0f;
        this.totalXpOld = 0.0f;
    }

    public CarType(String playerName, String typeName) {
        this.playerName = playerName;
        this.typeName = typeName;
        this.xpForNextLevelCur = 0.0f;
        this.xpForNextLevelOld = 0.0f;
        this.levelXpCur = 0.0f;
        this.levelXpOld = 0.0f;
        this.totalXpCur = 0.0f;
        this.totalXpOld = 0.0f;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

}

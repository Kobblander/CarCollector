package is.ru.app.CarCollector.game.models;

/**
 * <h1>PlayerXp</h1>
 * <h2>is.ru.app.CarCollector.cars.data.dto</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Player {

    private int _id;
    private String playerName;
    private int levelCur;
    private int levelOld;
    private float xpForNextLevelCur;
    private float xpForNextLevelOld;
    private float levelXpCur;
    private float levelXpOld;
    private float totalXpCur;
    private float totalXpOld;

    public Player() {
    }

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Player(int _id, String playerName) {
        this._id = _id;
        this.playerName = playerName;
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

    public float getTotalXpOld() {
        return totalXpOld;
    }

    public void setTotalXpOld(float totalXpOld) {
        this.totalXpOld = totalXpOld;
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

    @Override
    public String toString() {
        return "Player{" +
                "_id=" + _id +
                ", playerName='" + playerName + '\'' +
                ", levelCur=" + levelCur +
                ", levelOld=" + levelOld +
                ", xpForNextLevelCur=" + xpForNextLevelCur +
                ", xpForNextLevelOld=" + xpForNextLevelOld +
                ", levelXpCur=" + levelXpCur +
                ", levelXpOld=" + levelXpOld +
                ", totalXpCur=" + totalXpCur +
                ", totalXpOld=" + totalXpOld +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (_id != player._id) return false;
        if (levelCur != player.levelCur) return false;
        if (levelOld != player.levelOld) return false;
        if (Float.compare(player.levelXpCur, levelXpCur) != 0) return false;
        if (Float.compare(player.levelXpOld, levelXpOld) != 0) return false;
        if (Float.compare(player.totalXpCur, totalXpCur) != 0) return false;
        if (Float.compare(player.totalXpOld, totalXpOld) != 0) return false;
        if (Float.compare(player.xpForNextLevelCur, xpForNextLevelCur) != 0) return false;
        if (Float.compare(player.xpForNextLevelOld, xpForNextLevelOld) != 0) return false;
        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + levelCur;
        result = 31 * result + levelOld;
        result = 31 * result + (xpForNextLevelCur != +0.0f ? Float.floatToIntBits(xpForNextLevelCur) : 0);
        result = 31 * result + (xpForNextLevelOld != +0.0f ? Float.floatToIntBits(xpForNextLevelOld) : 0);
        result = 31 * result + (levelXpCur != +0.0f ? Float.floatToIntBits(levelXpCur) : 0);
        result = 31 * result + (levelXpOld != +0.0f ? Float.floatToIntBits(levelXpOld) : 0);
        result = 31 * result + (totalXpCur != +0.0f ? Float.floatToIntBits(totalXpCur) : 0);
        result = 31 * result + (totalXpOld != +0.0f ? Float.floatToIntBits(totalXpOld) : 0);
        return result;
    }
}

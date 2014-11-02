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
    private int level;
    private double xpForNextLevel;

    public Player(int _id, String playerName, int level, double xpForNextLevel) {
        this._id = _id;
        this.playerName = playerName;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
    }

    public Player(String playerName, int level, double xpForNextLevel) {
        this.playerName = playerName;
        this.level = level;
        this.xpForNextLevel = xpForNextLevel;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getXpForNextLevel() {
        return xpForNextLevel;
    }

    public void setXpForNextLevel(double xpForNextLevel) {
        this.xpForNextLevel = xpForNextLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (_id != player._id) return false;
        if (level != player.level) return false;
        if (Double.compare(player.xpForNextLevel, xpForNextLevel) != 0) return false;
        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = _id;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + level;
        temp = Double.doubleToLongBits(xpForNextLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

package is.ru.app.CarCollector.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Statistics</h1>
 * <h2>is.ru.app.CarCollector.game.models</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Statistics {

    private Player player;
    private List<TypeStats> typeStats;

    public Statistics() {
        typeStats = new ArrayList<TypeStats>();
    }

    public Statistics(Player player, List<TypeStats>  typeStats) {
        this.player = player;
        this.typeStats = typeStats;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<TypeStats> getTypeStats() {
        return typeStats;
    }

    public void setTypeStats(List<TypeStats> typeStats) {
        this.typeStats = typeStats;
    }
}

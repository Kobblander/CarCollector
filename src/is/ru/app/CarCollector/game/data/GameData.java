package is.ru.app.CarCollector.game.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.game.GameAdapter;
import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>GameData</h1>
 * <h2>is.ru.app.CarCollector.game.data</h2>
 * <p></p>
 * Created on 1.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class GameData implements GameDataGateway {

    Context ctx;
    GameAdapter gameAdapter;

    public GameData(Context ctx) {
        this.ctx = ctx;
        this.gameAdapter = new GameAdapter(ctx);
    }

    @Override
    public void addPlayer(Player player) {
        gameAdapter.insertPlayer(player);
        Log.i("GameData", "Player successfully added to database: " + player.toString());
    }

    @Override
    public void addCarType(CarType carType) {
        gameAdapter.insertCarType(carType);
        Log.i("GameData", "CarType successfully added to database: " + carType.toString());
    }

    @Override
    public void addCarSubType(CarSubType carSubType) {
        gameAdapter.insertCarSubType(carSubType);
        Log.i("GameData", "CarSubType successfully added to database: " + carSubType.toString());
    }

    @Override
    public List<CarType> getCarTypes() {
        Cursor cursor = gameAdapter.queryCarTypes();
        return getCarTypesFromCursor(cursor);
    }

    @Override
    public List<CarSubType> getCarSubTypes() {
        Cursor cursor = gameAdapter.queryCarSubTypes();
        return getCarSubTypesFromCursor(cursor);
    }

    @Override
    public List<CarType> getCarTypesByName(String carTypeName) {
        Cursor cursor = gameAdapter.queryCarTypesByTypeName(carTypeName);
        return getCarTypesFromCursor(cursor);
    }

    @Override
    public List<CarSubType> getCarSubTypesByName(String carSubTypeName) {
        Cursor cursor = gameAdapter.queryCarSubTypesBySubTypeName(carSubTypeName);
        return getCarSubTypesFromCursor(cursor);
    }

    @Override
    public List<CarType> getCarTypesByNameAndPlayer(String carTypeName, String playerName) {
        Cursor cursor = gameAdapter.queryCarTypesByTypeNameAndPlayerName(carTypeName, playerName);
        return getCarTypesFromCursor(cursor);
    }

    @Override
    public List<CarSubType> getCarSubTypesByNameAndPlayer(String carSubTypeName, String playerName) {
        Cursor cursor = gameAdapter.queryCarSubTypesByTypeNameAndPlayerName(carSubTypeName, playerName);
        return getCarSubTypesFromCursor(cursor);
    }

    private List<CarType> getCarTypesFromCursor(Cursor cursor) {
        List<CarType> list = new ArrayList<CarType>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            CarType ct = new CarType();
            // The Cursor is now set to the right position
            ct.set_id(cursor.getInt(0));
            ct.setPlayerId(cursor.getInt(1));
            ct.setTypeName(cursor.getString(2));
            ct.setLevel(cursor.getInt(3));
            ct.setXpForNextLevel(cursor.getFloat(4));

            list.add(ct);
        }

        return list;
    }

    private List<Player> getPlayersFromCursor(Cursor cursor) {
        List<Player> list = new ArrayList<Player>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Player ct = new Player();
            // The Cursor is now set to the right position
            ct.set_id(cursor.getInt(0));
            ct.setPlayerName(cursor.getString(1));
            ct.setLevel(cursor.getInt(2));
            ct.setXpForNextLevel(cursor.getFloat(3));

            list.add(ct);
        }

        return list;
    }

    private List<CarSubType> getCarSubTypesFromCursor(Cursor cursor) {
        List<CarSubType> list = new ArrayList<CarSubType>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            CarSubType ct = new CarSubType();
            // The Cursor is now set to the right position
            ct.set_id(cursor.getInt(0));
            ct.setTypeId(cursor.getInt(1));
            ct.setSubTypeName(cursor.getString(2));
            ct.setLevel(cursor.getInt(3));
            ct.setXpForNextLevel(cursor.getFloat(4));
            ct.setTotalCars(cursor.getInt(5));

            list.add(ct);
        }

        return list;
    }

}

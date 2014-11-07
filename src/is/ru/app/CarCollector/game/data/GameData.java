package is.ru.app.CarCollector.game.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
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
    public Player addPlayer(Player player) {
        int id = ((Long) gameAdapter.insertPlayer(player)).intValue();
        player.set_id(id);
        return player;
    }

    @Override
    public CarType addCarType(CarType carType) {
        int id = ((Long)gameAdapter.insertCarType(carType)).intValue();
        carType.set_id(id);
        return carType;
    }

    @Override
    public CarSubType addCarSubType(CarSubType carSubType) {
        int id = ((Long)gameAdapter.insertCarSubType(carSubType)).intValue();
        carSubType.set_id(id);
        return carSubType;
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
    public CarType getCarTypeByName(String carTypeName) {
        Cursor cursor = gameAdapter.queryCarTypesByTypeName(carTypeName);
        List<CarType> carTypeList = getCarTypesFromCursor(cursor);
        if (carTypeList.isEmpty()) {
            return null;
        }
        return carTypeList.get(0);
    }

    @Override
    public CarSubType getCarSubTypeByName(String carSubTypeName) {
        Cursor cursor = gameAdapter.queryCarSubTypesBySubTypeName(carSubTypeName);
        List<CarSubType> carSubTypeList = getCarSubTypesFromCursor(cursor);
        if (carSubTypeList.isEmpty()) {
            return null;
        }
        return carSubTypeList.get(0);
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

    @Override
    public CarType getCarTypeByNameAndPlayer(String carTypeName, String playerName) {
        Cursor cursor = gameAdapter.queryCarTypesByTypeNameAndPlayerName(carTypeName, playerName);
        List<CarType> carTypeList = getCarTypesFromCursor(cursor);
        if (carTypeList.isEmpty()) {
            return null;
        }
        return carTypeList.get(0);
    }

    @Override
    public CarSubType getCarSubTypeByNameAndPlayer(String carSubTypeName, String playerName) {
        Cursor cursor = gameAdapter.queryCarSubTypesByTypeNameAndPlayerName(carSubTypeName, playerName);
        List<CarSubType> carSubTypeList = getCarSubTypesFromCursor(cursor);
        if (carSubTypeList.isEmpty()) {
            return null;
        }
        return carSubTypeList.get(0);
    }

    @Override
    public List<CarSubType> getCarSubTypesByTypeId(int typeId) {
        Cursor cursor = gameAdapter.queryCarSubTypesByTypeId(typeId);
        return getCarSubTypesFromCursor(cursor);
    }

    @Override
    public Long updateCarType(CarType carType) {
        return gameAdapter.updateCarType(carType);
    }

    @Override
    public Long updateCarSubType(CarSubType carSubType) {
        return  gameAdapter.updateCarSubType(carSubType);
    }

    private List<CarType> getCarTypesFromCursor(Cursor cursor) {
        List<CarType> list = new ArrayList<CarType>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            CarType ct = new CarType();
            // The Cursor is now set to the right position
            ct.set_id(cursor.getInt(0));
            ct.setPlayerName(cursor.getString(1));
            ct.setTypeName(cursor.getString(2));
            ct.setLevelCur(cursor.getInt(3));
            ct.setLevelOld(cursor.getInt(4));
            ct.setXpForNextLevelCur(cursor.getFloat(5));
            ct.setXpForNextLevelOld(cursor.getFloat(6));
            ct.setLevelXpCur(cursor.getFloat(7));
            ct.setLevelXpOld(cursor.getFloat(8));
            ct.setTotalXpCur(cursor.getFloat(9));
            ct.setTotalXpOld(cursor.getFloat(10));

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
            ct.setLevelCur(cursor.getInt(2));
            ct.setLevelOld(cursor.getInt(3));
            ct.setXpForNextLevelCur(cursor.getFloat(4));
            ct.setXpForNextLevelOld(cursor.getFloat(5));
            ct.setLevelXpCur(cursor.getFloat(6));
            ct.setLevelXpOld(cursor.getFloat(7));
            ct.setTotalXpCur(cursor.getFloat(8));
            ct.setTotalXpOld(cursor.getFloat(9));

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
            ct.setPlayerName(String.valueOf(cursor.getString(1)));
            ct.setTypeName(String.valueOf(cursor.getString(2)));
            ct.setSubTypeName(cursor.getString(3));
            ct.setLevelCur(cursor.getInt(4));
            ct.setLevelOld(cursor.getInt(5));
            ct.setXpForNextLevelCur(cursor.getFloat(6));
            ct.setXpForNextLevelOld(cursor.getFloat(7));
            ct.setLevelXpCur(cursor.getFloat(8));
            ct.setLevelXpOld(cursor.getFloat(9));
            ct.setTotalXpCur(cursor.getFloat(10));
            ct.setTotalXpOld(cursor.getFloat(11));
            ct.setTotalCarsCur(cursor.getInt(12));
            ct.setTotalCarsOld(cursor.getInt(13));

            list.add(ct);
        }

        return list;
    }

}

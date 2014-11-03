package is.ru.app.CarCollector.game.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Player;
import is.ru.app.CarCollector.utilities.DbHelper;


/**
 * <h1>GameAdapter</h1>
 * <h2>is.ru.app.CarCollector.game</h2>
 * <p></p>
 * Created on 2.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class GameAdapter {

    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private Context context;
    private String tablePlayers = DbHelper.TablePlayers;
    private String tableCarTypes = DbHelper.TableCarTypes;
    private String tableCarSubTypes = DbHelper.TableCarSubTypes;
    private String[] playersCols = DbHelper.TablePlayersCols;
    private String[] typesCols = DbHelper.TableCarTypesCols;
    private String[] subTypesCols = DbHelper.TableCarSubTypesCols;

    public GameAdapter(Context c) {
        context = c;
    }

    public GameAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public GameAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertPlayer(Player p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( playersCols[1], p.getPlayerName());
        contentValues.put( playersCols[2], p.getLevel());
        contentValues.put( playersCols[3], p.getXpForNextLevel());
        contentValues.put( playersCols[4], p.getLevelXp());
        contentValues.put( playersCols[5], p.getTotalXp());
        openToWrite();
        long value = db.insert(tablePlayers, null, contentValues );
        close();
        return value;
    }

    public long insertCarType(CarType ct) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( typesCols[1], ct.getPlayerId());
        contentValues.put( typesCols[2], ct.getTypeName());
        contentValues.put( typesCols[3], ct.getLevel());
        contentValues.put( typesCols[4], ct.getXpForNextLevel());
        contentValues.put( typesCols[5], ct.getLevelXp());
        contentValues.put( typesCols[6], ct.getTotalXp());
        openToWrite();
        long value = db.insert(tableCarTypes, null, contentValues );
        close();
        return value;
    }

    public long insertCarSubType(CarSubType cst) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( subTypesCols[1], cst.getTypeId());
        contentValues.put( subTypesCols[2], cst.getSubTypeName());
        contentValues.put( subTypesCols[3], cst.getLevel());
        contentValues.put( subTypesCols[4], cst.getXpForNextLevel());
        contentValues.put( subTypesCols[5], cst.getLevelXp());
        contentValues.put( subTypesCols[6], cst.getTotalXp());
        contentValues.put( subTypesCols[7], cst.getTotalCars());
        openToWrite();
        long value = db.insert(tableCarSubTypes, null, contentValues );
        close();
        return value;
    }

    public long updatePlayer(Player p) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( playersCols[1], p.getPlayerName());
        contentValues.put( playersCols[2], p.getLevel());
        contentValues.put( playersCols[3], p.getXpForNextLevel());
        contentValues.put( playersCols[4], p.getLevelXp());
        contentValues.put( playersCols[5], p.getTotalXp());
        openToWrite();
        long value = db.update(tablePlayers, contentValues, playersCols[0] + "=" + p.get_id(), null );
        close();
        return value;
    }

    public long updateCarType(CarType ct) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( typesCols[1], ct.getPlayerId());
        contentValues.put( typesCols[2], ct.getTypeName());
        contentValues.put( typesCols[3], ct.getLevel());
        contentValues.put( typesCols[4], ct.getXpForNextLevel());
        contentValues.put( typesCols[5], ct.getLevelXp());
        contentValues.put( typesCols[6], ct.getTotalXp());
        openToWrite();
        long value = db.update(tableCarTypes, contentValues, typesCols[0] = "=" + ct.get_id(), null );
        close();
        return value;
    }

    public long updateCarSubType(CarSubType cst) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( subTypesCols[1], cst.getTypeId());
        contentValues.put( subTypesCols[2], cst.getSubTypeName());
        contentValues.put( subTypesCols[3], cst.getLevel());
        contentValues.put( subTypesCols[4], cst.getXpForNextLevel());
        contentValues.put( subTypesCols[5], cst.getLevelXp());
        contentValues.put( subTypesCols[6], cst.getTotalXp());
        contentValues.put( subTypesCols[7], cst.getTotalCars());
        openToWrite();
        long value = db.update(tableCarSubTypes, contentValues, subTypesCols[0] = "=" + cst.get_id(), null);
        close();
        return value;
    }

    /**
     * Query the database for CarTypes given the CarType name and PlayerId
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarTypes() {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarTypes;
        try {
            cursor = db.rawQuery(query, null);
        } catch(Exception e) {
            String msg = "No carTypes found in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarTypes given the CarType name and PlayerId
     * @param carTypeName The given name of the carType.
     * @param playerName The id of the player.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarTypesByTypeNameAndPlayerName(String carTypeName, String playerName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarTypes+" where carTypeName = ? && playerName = ?";
        try {
            cursor = db.rawQuery(query, new String[] {carTypeName, playerName});
        } catch(Exception e) {
            String msg = "No carTypes found with carTypeName: '" + carTypeName + "' and playerName: '"+ playerName +"'in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarTypes given the CarType name and PlayerId
     * @param carTypeName The given name of the carType.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarTypesByTypeName(String carTypeName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarTypes+" where carTypeName = ?";
        try {
            cursor = db.rawQuery(query, new String[] {carTypeName});
        } catch(Exception e) {
            String msg = "No carTypes found with carTypeName: '" + carTypeName + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarTypes given a PlayerName.
     * @param playerName The name of the player.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarTypesByPlayerName(String playerName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarTypes+" where playerName =?";
        try {
            cursor = db.rawQuery(query, new String[] {String.valueOf(playerName)});
        } catch(Exception e) {
            String msg = "No carTypes found with playerName: '" + playerName + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarTypes given the CarType name and PlayerId
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarSubTypes() {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarSubTypes;
        try {
            cursor = db.rawQuery(query, null);
        } catch(Exception e) {
            String msg = "No carSubTypes found in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarSubTypes given the CarSubType name and PlayerId.
     * @param carSubTypeName The given name of the carType.
     * @param playerName The name of the player.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarSubTypesByTypeNameAndPlayerName(String carSubTypeName, String playerName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarSubTypes+" where carSubTypeName = ? && playerName = ?";
        try {
            cursor = db.rawQuery(query, new String[] {carSubTypeName, playerName});
        } catch(Exception e) {
            String msg = "No carTypes found with carTypeName: '" + carSubTypeName + "' and playerName: '"+ playerName +"'in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarSubTypes given a name of a subType.
     * @param  carSubTypeName name of the subType.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarSubTypesBySubTypeName(String carSubTypeName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarSubTypes+" where carSubTypeName = ?";
        try {
            cursor = db.rawQuery(query, new String[] {carSubTypeName});
        } catch(Exception e) {
            String msg = "No carTypes found with carSubTypeName: '" + carSubTypeName + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarSubTypes given a name of a subType.
     * @param  carTypeId name of the subType.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarSubTypesByTypeId(int carTypeId) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarSubTypes+" where typeId = ?";
        try {
            cursor = db.rawQuery(query, new String[] {String.valueOf(carTypeId)});
        } catch(Exception e) {
            String msg = "No carSubTypes found with carTypeId: '" + carTypeId + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarSubTypes given a PlayerName.
     * @param playerName The name of the player.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryCarSubTypesByPlayerName(String playerName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tableCarSubTypes+" where carPlayerName = ?";
        try {
            cursor = db.rawQuery(query, new String[] {playerName});
        } catch(Exception e) {
            String msg = "No carTypes found with playerName: '" + playerName + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }

    /**
     * Query the database for CarSubTypes given a PlayerId.
     * @param playerName The id of the player.
     * @return A cursor which can be used to store the data.
     * @throws SQLException
     */
    public Cursor queryPlayerByPlayerName(String playerName) {
        openToRead();
        Cursor cursor;
        String query = "select * from "+tablePlayers+" where playerName =?";
        try {
            cursor = db.rawQuery(query, new String[] {playerName});
        } catch(Exception e) {
            String msg = "No carTypes found with playerName: '" + playerName + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg, e);
        }
        return cursor;
    }
}

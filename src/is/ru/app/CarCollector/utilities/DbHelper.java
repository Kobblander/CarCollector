package is.ru.app.CarCollector.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <h1>DbHelper</h1>
 * <h2>is.ru.app.CarCollector.cars.data.database</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "CAR_DB";
    public static final int DB_VERSION = 20;


    public static final String TableCars = "cars";
    public static final String TablePlayers = "players";
    public static final String TableCarTypes = "carTypes";
    public static final String TableCarSubTypes = "carSubTypes";

    /**
     * The cols are identical in the tables
     */
    public static final String[] TableCarsCols = { "_id", "registryNumber", "number", "factoryNumber",
                                                   "type", "subType", "color", "registeredAt", "status",
                                                   "nextCheck", "pollution", "weight" };

    public static final String[] TablePlayersCols = { "_id",                 " playerName",
                                                      "levelCur",            " levelOld",
                                                      "xpForNextLevelCur",   " xpForNextLevelOld",
                                                      "levelXpCur",          " levelXpOld",
                                                      "totalXpCur",          " totalXpOld" };

    public static final String[] TableCarTypesCols = { "_id",
                                                       "playerName",           "carTypeName",
                                                       "levelCur",             "levelOld",
                                                       "xpForNextLevelCur",    "xpForNextLevelOld",
                                                       "levelXpCur",           "levelXpOld",
                                                       "totalXpCur",           "totalXpOld"};

    public static final String[] TableCarSubTypesCols = { "_id",                "playerName",
                                                          "carTypeName",        "carSubTypeName",
                                                          "levelCur",           "levelOld",
                                                          "xpForNextLevelCur",  "xpForNextLevelOld",
                                                          "levelXpCur",         "levelXpOld",
                                                          "totalXpCur",         "totalXpOld",
                                                          "totalCarsCur",       "totalCarsOld"};

    public static final String[] TablePlayersColsCreate = { "_id", " playerName TEXT",
                                                            "levelCur INTEGER",            "levelOld NUMERIC",
                                                            "xpForNextLevelCur NUMERIC",   "xpForNextLevelOld NUMERIC",
                                                            "levelXpCur NUMERIC",          "levelXpOld NUMERIC",
                                                            "totalXpCur NUMERIC",          "totalXpOld NUMERIC" };

    public static final String[] TableCarTypesColsCreate = { "_id INTEGER PRIMARY KEY AUTOINCREMENT",
                                                             "playerName TEXT",            "carTypeName TEXT",
                                                             "levelCur NUMERIC",           "levelOld NUMERIC",
                                                             "xpForNextLevelCur NUMERIC",  "xpForNextLevelOld NUMERIC",
                                                             "levelXpCur NUMERIC",         "levelXpOld NUMERIC",
                                                             "totalXpCur NUMERIC",         "totalXpOld NUMERIC"};

    public static final String[] TableCarSubTypesColsCreate = { "_id INTEGER PRIMARY KEY AUTOINCREMENT", "playerName TEXT",
                                                                "carTypeName TEXT",             "carSubTypeName TEXT",
                                                                "levelCur NUMERIC",             "levelOld NUMERIC",
                                                                "xpForNextLevelCur NUMERIC",    "xpForNextLevelOld NUMERIC",
                                                                "levelXpCur NUMERIC",           "levelXpOld NUMERIC",
                                                                "totalXpCur NUMERIC",           "totalXpOld NUMERIC",
                                                                "totalCarsCur NUMERIC",         "totalCarsOld NUMERIC"};

    private static final String sqlCreateTableCars =
            "CREATE TABLE "+TableCars+"(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " registryNumber TEXT," +
                    " number TEXT," +
                    " factoryNumber TEXT," +
                    " type TEXT," +
                    " subType TEXT," +
                    " color TEXT," +
                    " registeredAt TEXT," +
                    " status TEXT," +
                    " nextCheck TEXT," +
                    " pollution TEXT," +
                    " weight TEXT" +
                    ");";

    private static final String sqlCreateTablePlayers =
            "CREATE TABLE "+TablePlayers+"(" +
                    " "+TablePlayersColsCreate[0]+"," +
                    " "+TablePlayersColsCreate[1]+"," +
                    " "+TablePlayersColsCreate[2]+"," +
                    " "+TablePlayersColsCreate[3]+"," +
                    " "+TablePlayersColsCreate[4]+"," +
                    " "+TablePlayersColsCreate[5]+"," +
                    " "+TablePlayersColsCreate[6]+"," +
                    " "+TablePlayersColsCreate[7]+"," +
                    " "+TablePlayersColsCreate[8]+"," +
                    " "+TablePlayersColsCreate[9]+
                    ");";

    private static final String sqlCreateTableCarTypes =
            "CREATE TABLE "+TableCarTypes+"(" +
                    " "+TableCarTypesColsCreate[0]+"," +
                    " "+TableCarTypesColsCreate[1]+"," +
                    " "+TableCarTypesColsCreate[2]+"," +
                    " "+TableCarTypesColsCreate[3]+"," +
                    " "+TableCarTypesColsCreate[4]+"," +
                    " "+TableCarTypesColsCreate[5]+"," +
                    " "+TableCarTypesColsCreate[6]+"," +
                    " "+TableCarTypesColsCreate[7]+"," +
                    " "+TableCarTypesColsCreate[8]+"," +
                    " "+TableCarTypesColsCreate[9]+"," +
                    " "+TableCarTypesColsCreate[10]+
                    ");";

    private static final String sqlCreateTableCarSubTypes =
            "CREATE TABLE "+TableCarSubTypes+"(" +
                    " "+TableCarSubTypesColsCreate[0]+"," +
                    " "+TableCarSubTypesColsCreate[1]+"," +
                    " "+TableCarSubTypesColsCreate[2]+"," +
                    " "+TableCarSubTypesColsCreate[3]+"," +
                    " "+TableCarSubTypesColsCreate[4]+"," +
                    " "+TableCarSubTypesColsCreate[5]+"," +
                    " "+TableCarSubTypesColsCreate[6]+"," +
                    " "+TableCarSubTypesColsCreate[7]+"," +
                    " "+TableCarSubTypesColsCreate[8]+"," +
                    " "+TableCarSubTypesColsCreate[9]+"," +
                    " "+TableCarSubTypesColsCreate[10]+"," +
                    " "+TableCarSubTypesColsCreate[11]+"," +
                    " "+TableCarSubTypesColsCreate[12]+"," +
                    " "+TableCarSubTypesColsCreate[13]+
                    ");";

    private static final String sqlDropTableCars =
            "DROP TABLE IF EXISTS "+TableCars+";";

    private static final String sqlDropTablePlayers =
            "DROP TABLE IF EXISTS "+TablePlayers+";";

    private static final String sqlDropTableCarTypes =
            "DROP TABLE IF EXISTS "+TableCarTypes+";";

    private static final String sqlDropTableCarSubTypes =
            "DROP TABLE IF EXISTS "+TableCarSubTypes+";";


    public DbHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    public void resetDatabase(SQLiteDatabase db) {
        db.execSQL( sqlDropTableCars );
        db.execSQL( sqlDropTablePlayers );
        db.execSQL( sqlDropTableCarTypes );
        db.execSQL( sqlDropTableCarSubTypes );
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( sqlCreateTableCars );
        db.execSQL( sqlCreateTableCarTypes );
        db.execSQL( sqlCreateTableCarSubTypes );
        db.execSQL( sqlCreateTablePlayers );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( sqlDropTableCars );
        db.execSQL( sqlDropTablePlayers );
        db.execSQL( sqlDropTableCarTypes );
        db.execSQL( sqlDropTableCarSubTypes );
        onCreate( db );
    }}

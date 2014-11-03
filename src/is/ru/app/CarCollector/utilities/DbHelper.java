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
    public static final int DB_VERSION = 17;


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

    public static final String[] TablePlayersCols = { "_id INTEGER PRIMARY KEY AUTOINCREMENT", "playerName TEXT",
                                                      "levelCur INTEGER",          "levelOld INTEGER",
                                                      "xpForNextLevelCur FLOAT",   "xpForNextLevelOld FLOAT",
                                                      "levelXpCur FLOAT",          "levelXpOld FLOAT",
                                                      "totalXpCur FLOAT",          "totalXpOld FLOAT" };

    public static final String[] TableCarTypesCols = { "_id INTEGER PRIMARY KEY AUTOINCREMENT",
                                                       "playerName TEXT",          "carTypeName TEXT",
                                                       "levelCur INTEGER",         "levelOld INTEGER",
                                                       "xpForNextLevelCur FLOAT",  "xpForNextLevelOld FLOAT",
                                                       "levelXpCur FLOAT",         "levelXpOldFLOAT",
                                                       "totalXpCur FLOAT",         "totalXpOld FLOAT"};

    public static final String[] TableCarSubTypesCols = { "_id INTEGER PRIMARY KEY AUTOINCREMENT",
                                                          "carTypeName TEXT", "carSubTypeName TEXT",
                                                          "levelCur INTEGER",         "levelOld INTEGER",
                                                          "xpForNextLevelCur FLOAT",  "xpForNextLevelOld FLOAT",
                                                          "levelXpCur FLOAT",         "levelXpOld FLOAT",
                                                          "totalXpCur FLOAT",         "totalXpOld FLOAT",
                                                          "totalCarsCur INTEGER",     "totalCarsOld INTEGER"};

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
                    " "+TablePlayersCols[0]+" ," +
                    " "+TablePlayersCols[1]+" ," +
                    " "+TablePlayersCols[2]+" ," +
                    " "+TablePlayersCols[3]+" ," +
                    " "+TablePlayersCols[4]+" ," +
                    " "+TablePlayersCols[5]+" ," +
                    " "+TablePlayersCols[6]+" ," +
                    " "+TablePlayersCols[7]+" ," +
                    " "+TablePlayersCols[8]+" ," +
                    " "+TablePlayersCols[9]+" " +
                    ");";

    private static final String sqlCreateTableCarTypes =
            "CREATE TABLE "+TableCarTypes+"(" +
                    " "+TableCarTypesCols[0]+ " ," +
                    " "+TableCarTypesCols[1]+ " ," +
                    " "+TableCarTypesCols[2]+ " ," +
                    " "+TableCarTypesCols[3]+ " ," +
                    " "+TableCarTypesCols[4]+ " ," +
                    " "+TableCarTypesCols[5]+ " ," +
                    " "+TableCarTypesCols[7]+ " ," +
                    " "+TableCarTypesCols[8]+ " ," +
                    " "+TableCarTypesCols[9]+ " ," +
                    " "+TableCarTypesCols[10]+" " +
                    ");";

    private static final String sqlCreateTableCarSubTypes =
            "CREATE TABLE "+TableCarSubTypes+"(" +
                    " "+TableCarSubTypesCols[0]+ " ," +
                    " "+TableCarSubTypesCols[1]+ " ," +
                    " "+TableCarSubTypesCols[2]+ " ," +
                    " "+TableCarSubTypesCols[3]+ " ," +
                    " "+TableCarSubTypesCols[4]+ " ," +
                    " "+TableCarSubTypesCols[5]+ " ," +
                    " "+TableCarSubTypesCols[6]+ " ," +
                    " "+TableCarSubTypesCols[7]+ " ," +
                    " "+TableCarSubTypesCols[8]+ " ," +
                    " "+TableCarSubTypesCols[9]+ " ," +
                    " "+TableCarSubTypesCols[10]+" ," +
                    " "+TableCarSubTypesCols[11]+" ," +
                    " "+TableCarSubTypesCols[12]+" " +
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

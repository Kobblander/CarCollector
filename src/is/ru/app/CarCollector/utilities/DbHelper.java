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
    public static final int DB_VERSION = 9;


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

    public static final String[] TablePlayersCols = { "_id", "playerName", "level", "xpForNextLevel" };

    public static final String[] TableCarTypesCols = { "_id", "playerId", "carTypeName", "level", "xpForNextLevel" };

    public static final String[] TableCarSubTypesCols = { "_id", "typeId", "carSubTypeName", "level", "xpForNextLevel",
                                                          "totalCars"};

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
                    " "+TablePlayersCols[0]+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " "+TablePlayersCols[1]+" TEXT," +
                    " "+TablePlayersCols[2]+" INTEGER," +
                    " "+TablePlayersCols[3]+" FLOAT" +
                    ");";

    private static final String sqlCreateTableCarTypes =
            "CREATE TABLE "+TableCarTypes+"(" +
                    " "+TableCarTypesCols[0]+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " "+TableCarTypesCols[1]+" INTEGER," +
                    " "+TableCarTypesCols[2]+" TEXT," +
                    " "+TableCarTypesCols[3]+" INTEGER," +
                    " "+TableCarTypesCols[4]+" FLOAT" +
                    ");";

    private static final String sqlCreateTableCarSubTypes =
            "CREATE TABLE "+TableCarSubTypes+"(" +
                    " "+TableCarSubTypesCols[0]+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " "+TableCarSubTypesCols[1]+" INTEGER," +
                    " "+TableCarSubTypesCols[2]+" TEXT," +
                    " "+TableCarSubTypesCols[3]+" INTEGER," +
                    " "+TableCarSubTypesCols[4]+" FLOAT" +
                    " "+TableCarSubTypesCols[5]+" INTEGER," +
                    ");";

    private static final String sqlDropTableCars =
            "DROP TABLE IF EXISTS "+sqlCreateTableCars+";";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( sqlDropTableCars );
        db.execSQL( sqlDropTablePlayers );
        db.execSQL( sqlDropTableCarTypes );
        db.execSQL( sqlDropTableCarSubTypes );
        onCreate( db );
    }}

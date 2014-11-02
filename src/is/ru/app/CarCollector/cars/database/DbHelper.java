package is.ru.app.CarCollector.cars.database;

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

    /**
     * The cols are identical in the tables
     */
    public static final String[] TableCarsCols = { "_id", "registryNumber", "number", "factoryNumber",
                                                   "type", "subType", "color", "registeredAt", "status",
                                                   "nextCheck", "pollution", "weight" };


    private static final String sqlCreateTableCars =
            "CREATE TABLE cars(" +
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

    private static final String sqlDropTableCars =
            "DROP TABLE IF EXISTS cars;";


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
        onCreate( db );
    }}

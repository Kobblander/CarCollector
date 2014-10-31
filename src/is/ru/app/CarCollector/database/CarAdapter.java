package is.ru.app.CarCollector.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import is.ru.app.CarCollector.cars.data.dto.Car;

/**
 * <h1>CarAdapter</h1>
 * <h2>is.ru.app.CarCollector.database</h2>
 * <p></p>
 * Created on 31.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarAdapter {
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    private Context context;
    private String[] cols = DbHelper.TableCarsCols;

    public CarAdapter( Context c ) {
        context = c;
    }

    public CarAdapter openToRead() {
        dbHelper = new DbHelper( context );
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public CarAdapter openToWrite() {
        dbHelper = new DbHelper( context );
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    /**
     * Updates the database with given values
     * @param car The car to be added to the database.
     */
    public long insertCar(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[1], car.getRegistryNumber());
        contentValues.put( cols[2], car.getNumber());
        contentValues.put( cols[3], car.getFactoryNumber());
        contentValues.put( cols[4], car.getType());
        contentValues.put( cols[5], car.getSubType());
        contentValues.put( cols[6], car.getColor());
        contentValues.put( cols[7], String.valueOf(car.getRegisteredAt()));
        openToWrite();
        long value = db.insert("cars", null, contentValues );
        close();
        return value;
    }

    /**
     * Updates the database with given values
     * @param car The car to be updated in the database.
     */
    public long updateCar(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[1], car.getRegistryNumber());
        contentValues.put( cols[2], car.getNumber());
        contentValues.put( cols[3], car.getFactoryNumber());
        contentValues.put( cols[4], car.getType());
        contentValues.put( cols[5], car.getSubType());
        contentValues.put( cols[6], car.getColor());
        contentValues.put( cols[7], String.valueOf(car.getRegisteredAt()));
        openToWrite();
        long value = db.update("cars", contentValues, cols[1] + "=" + car.getRegistryNumber(), null);
        close();
        return value;
    }

    public Cursor queryCars() {
        openToRead();
        Cursor cursor = db.query( "cars",
                cols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryRegistryNumber(String registryNumber) {
        openToRead();
        Cursor cursor = db.query( "cars",
                cols, cols[1] + "=" + registryNumber, null, null, null, null, null);
        return cursor;
    }

    /**
     * Query the database by car type.
     * @param type The type of the car.
     * @param limit The limit of how many cars to get.
     * @return A cursor which can be used to access the data.
     */
    public Cursor queryType(String type, String limit) {
        openToRead();
        Cursor cursor = db.query( "cars",
                cols, cols[4] + "=" + type, null, null, null, null, limit);
        return cursor;
    }

    /**
     * Query the database by car subtype.
     * @param subType The subtype of the car.
     * @param limit The limit of how many cars to get.
     * @return A cursor which can be used to access the data.
     */
    public Cursor querySubType(String subType, String limit) {
        openToRead();
        Cursor cursor = db.query( "cars",
                cols, cols[5] + "=" + subType, null, null, null, null, limit);
        return cursor;
    }
}

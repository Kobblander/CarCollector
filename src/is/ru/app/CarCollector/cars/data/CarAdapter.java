package is.ru.app.CarCollector.cars.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.utilities.DbHelper;


/**
 * <h1>CarAdapter</h1>
 * <h2>is.ru.app.CarCollector.cars.data.database</h2>
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
        contentValues.put( cols[7], car.getRegisteredAt());
        contentValues.put( cols[8], car.getStatus());
        contentValues.put( cols[9], car.getNextCheck());
        contentValues.put( cols[10], car.getPollution());
        contentValues.put( cols[11], car.getWeight());
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
        contentValues.put( cols[7], car.getRegisteredAt());
        contentValues.put( cols[8], car.getStatus());
        contentValues.put( cols[9], car.getNextCheck());
        contentValues.put( cols[10], car.getPollution());
        contentValues.put( cols[11], car.getWeight());
        openToWrite();
        long value = db.update("cars", contentValues, cols[1] + "=" + car.getRegistryNumber(), null);
        close();
        return value;
    }

    /**
     * Query the database for all cars.
     * @param limit The limit of how many cars to get.
     * @return A cursor which can be used to access the data.
     */
    public Cursor queryCars(String limit) {
        openToRead();
        Cursor cursor;
        try {
            cursor = db.query( "cars", cols, null, null, null, null, limit);
        } catch(Exception e) {
            String msg = "No cars were found in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapter - queryCars", msg);
            throw new SQLException(msg);
        }
        return cursor;
    }

    /**
     * Query the database by registry number.
     * @param registryNumber The registry number of the car to receive.
     * @return A cursor which can be used to access the data.
     */
    public Cursor queryRegistryNumber(String registryNumber) {
        openToRead();
        Cursor cursor;
        String query = "select * from cars where registryNumber =?";
        try {
            cursor = db.rawQuery(query, new String[] {registryNumber});
        } catch(Exception e) {
            String msg = "No cars found with registry number: '" + registryNumber + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapter - queryRegistryNumber", msg);
            throw new SQLException(msg);
        }
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
        Cursor cursor;
        String query = "select * from cars where type =?";
        try {
            cursor = db.rawQuery(query, new String[] {type});
        } catch(Exception e) {
            String msg = "No cars found of car type: '" + type + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg);
        }
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
        Cursor cursor;
        String query = "select * from cars where subType =?";
        try {
            cursor = db.rawQuery(query, new String[] {subType});
        } catch(Exception e) {
            String msg = "No cars found of car subType: '" + subType + "' in the database. Nested exception is: " + e.getMessage();
            Log.i("CarAdapterExceptionThrown", msg);
            throw new SQLException(msg);
        }
        return cursor;
    }
}

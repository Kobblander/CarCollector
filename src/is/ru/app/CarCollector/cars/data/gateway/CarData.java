package is.ru.app.CarCollector.cars.data.gateway;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import is.ru.app.CarCollector.cars.data.models.Car;
import is.ru.app.CarCollector.cars.database.CarAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>CarData</h1>
 * <h2>is.ru.app.CarCollector.cars.data.database</h2>
 * <p></p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class CarData implements CarDataGateway {

    Context ctx;
    CarAdapter carAdapter;

    public CarData(Context ctx) {
        this.ctx = ctx;
        this.carAdapter = new CarAdapter(ctx);
    }

    @Override
    public void addCar(Car car) {
        carAdapter.insertCar(car);
        Log.i("CarData", "Car successfully added to database: " + car.toString());
    }

    @Override
    public Car getCarByRegistryNumber(String registryNumber) {
        Cursor cursor = carAdapter.queryRegistryNumber(registryNumber);
        List<Car> carList = getListFromCursor(cursor);
        if (carList.isEmpty()) {
            return null;
        }
        return carList.get(0);
    }

    @Override
    public List<Car> getCarsByType(String type, String limit) {
        Cursor cursor = carAdapter.queryType(type, limit);
        return getListFromCursor(cursor);
    }


    @Override
    public List<Car> getCarsBySubType(String subType, String limit) {
        Cursor cursor = carAdapter.querySubType(subType, limit);
        return getListFromCursor(cursor);
    }

    @Override
    public List<Car> getCars(String limit) {
        Cursor cursor = carAdapter.queryCars(limit);
        return getListFromCursor(cursor);
    }

    private List<Car> getListFromCursor(Cursor cursor) {
        List<Car> carList = new ArrayList<Car>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Car car = new Car();
            // The Cursor is now set to the right position
            car.setRegistryNumber(cursor.getString(1));
            car.setNumber(cursor.getString(2));
            car.setFactoryNumber(cursor.getString(3));
            car.setType(cursor.getString(4));
            car.setSubType(cursor.getString(5));
            car.setColor(cursor.getString(6));
            car.setRegisteredAt(cursor.getString(7));
            car.setStatus(cursor.getString(8));
            car.setNextCheck(cursor.getString(9));
            car.setPollution(cursor.getString(10));
            car.setWeight(cursor.getString(11));
            carList.add(car);
        }

        return carList;
    }

}

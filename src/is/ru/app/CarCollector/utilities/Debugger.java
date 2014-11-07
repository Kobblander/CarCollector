package is.ru.app.CarCollector.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.cars.service.CarServiceException;
import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Player;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;
import is.ru.app.CarCollector.game.service.GameServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Debugger</h1>
 * <h2>is.ru.app.CarCollector.utilities</h2>
 * <p></p>
 * Created on 3.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class Debugger {

    private static Debugger instance = new Debugger();

    public static Debugger getInstance() { return instance; }

    public static List<String> testDataToyota = new ArrayList<String>();

    public static List<Car> cars = new ArrayList<Car>();;

    public static List<CarSubType> carSubTypes = new ArrayList<CarSubType>();
    public static Player player;

    private Debugger() {

    }

    public static void addDummyData(Context context) {

		cars = new ArrayList<Car>();

		// SETTU DUMMY DATA HINGAÐ STEINAR
		Car car1 = new Car();
		car1.setNumber("MF078");
		car1.setRegistryNumber("MF078");
		car1.setType("SKODA");
		car1.setSubType("SUPERB");
		car1.setWeight("1480");
		car1.setColor("Blar");
		car1.setStatus("I lagi");
		car1.setFactoryNumber("!dsda");
		car1.setRegisteredAt("11.06.2004");

		Car car2 = new Car();
		car2.setNumber("LOLA");
		car2.setRegistryNumber("LOLA");
		car2.setType("SKODA");
		car2.setSubType("OCTAVIA");
		car2.setWeight("1480");
		car2.setRegisteredAt("11.06.2010");

		Car car3 = new Car();
		car3.setNumber("HOLA");
		car3.setType("SKODA");
		car3.setSubType("YETI");
		car3.setWeight("1480");
		car3.setRegisteredAt("11.06.2009");

		Car car4 = new Car();
		car4.setNumber("ELO");
		car4.setType("TESLA MOTORS");
		car4.setSubType("MODEL S");
		car4.setWeight("2150");

		Car car5 = new Car();
		car5.setNumber("ELOX");
		car5.setType("TESLA MOTORS");
		car5.setSubType("MODEL x");
		car5.setWeight("2250");

		Car car6 = new Car();
		car6.setNumber("LAMB");
		car6.setType("LAMBORGHINI");
		car6.setSubType("AVENTADOR");
		car6.setWeight("1750");

		Car car7 = new Car();
		car7.setNumber("RAPID");
		car7.setType("SKODA");
		car7.setSubType("RAPID");
		car7.setWeight("2150");
		car7.setRegisteredAt("11.06.2013");

		Car car8 = new Car();
		car8.setNumber("FABU");
		car8.setType("SKODA");
		car8.setSubType("FABIA");
		car8.setWeight("2150");

		Car car9 = new Car();
		car9.setNumber("ROOMIE");
		car9.setType("SKODA");
		car9.setSubType("ROOMSTER");
		car9.setWeight("2150");

		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		cars.add(car6);
		cars.add(car7);
		cars.add(car8);
		cars.add(car9);

		CarService carService = new CarServiceData(context);
        GameService gameService = new GameServiceData(context);
		player = new Player("Captain America");

        for (Car car : cars) {
            try {
				carService.addCarCallback(car);
                gameService.updateStats(car, player);
            } catch (GameServiceException e) {
            } catch (CarServiceException e) {
				e.printStackTrace();
			}
		}

    }

    public static void resetDatabase(Context context) {
        Log.i("Debugger", "Resetting the database...");
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.resetDatabase(db);
    }
}

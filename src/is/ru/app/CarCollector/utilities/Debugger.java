package is.ru.app.CarCollector.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
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

    public static List<Car> cars = new ArrayList<Car>();

    public static List<CarSubType> carSubTypes = new ArrayList<CarSubType>();
    public static Player player = new Player();

    private Debugger() {

        // SETTU DUMMY DATA HINGAÐ STEINAR
        cars.add(new Car());

        player.setPlayerName("Kobblander");


    }

    public static void addDummyData(Context context) {
        GameService gameService = new GameServiceData(context);

        for (Car car : cars) {
            try {
                gameService.updateStats(car, player);
            } catch (GameServiceException e) {
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

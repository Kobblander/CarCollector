package is.ru.app.CarCollector.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import is.ru.app.CarCollector.cars.models.Car;

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

    private Debugger() {
        /*
        testDataToyota.add("MG292");
        testDataToyota.add("SL297");
        */
    }


    public static void resetDatabase(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.resetDatabase(db);
    }
}

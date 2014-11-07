package is.ru.app.CarCollector.cars.data.rest;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;

import java.util.List;

/**
 * <h1>RestQuery</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p>This class receives a GET request from apis.is/car?"licenceplate"</p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class RestQuery {
    private static RestQuery instance = null;
    private static AsyncTask<Void, Void, Car> carTask = null;
    private static AsyncTask<Void, Void, List<Bitmap>> imageTask = null;

    /**
     * Create get or create instance of RestQuery
     * @return RestQuery instance
     */
    public static RestQuery getInstance() {
        if (instance == null) {
            instance = new RestQuery();
        }

        return instance;
    }

    /**
     * Executes the car task
     * @param registryNumber car plate number
     * @param c callback
     */
    public void getCar(String registryNumber, RestCallback c) {
        Log.i("RestQuery", "getCar.");

        final String url = "http://apis.is/car?number=" + registryNumber;

        carTask = new CarTask(url, c);
        carTask.execute();
    }

    /**
     * Executes the image task
     * @param type car type
     * @param subType car subtype
     * @param color car color
     * @param c callback
     */
    public void queryImage(String type, String subType, String color, String registered,  RestCallback c) {
        Log.i("RestQuery", "queryImage.");
		String year;
		if(registered != null && !registered.isEmpty())
			year = registered.substring(registered.length() - 4);
		else
			year = "";
        final String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + type + " " + subType + " " + year;

        imageTask = new ImageTask(url, c);
        imageTask.execute();
    }

    /**
     * Cancel the img task
     */
    public void cancelImageTask() {
        Log.i("RestQuery", "CancelImageTask.");
        if(imageTask != null) {
            imageTask.cancel(true);
        }
    }

    /**
     * Cancel the car task
     */
    public void cancelCarTask() {
        Log.i("RestQuery", "CancelCarTask.");
        if (carTask != null) {
            carTask.cancel(true);
        }
    }
}

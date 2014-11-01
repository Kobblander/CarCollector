package is.ru.app.CarCollector.cars.data.rest;

import android.os.AsyncTask;
import android.util.Log;
import is.ru.app.CarCollector.cars.data.models.Car;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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

    private static AsyncTask<Void, Void, Car> currentTask = null;

    private RestQuery() {
    }

    public static RestQuery getInstance() {
        if (instance == null) {
            instance = new RestQuery();
        }
        return instance;
    }

    public void getCar(String registryNumber, RestCallback c) {
        Log.i("RestQuery", "getCar.");
        final String url = "http://apis.is/car?number=" + registryNumber;
        currentTask = new CarTask(url, c);
        currentTask.execute();
    }

    private class CarTask extends AsyncTask<Void, Void, Car> {

        private final String url;
        private final RestCallback callback;

        private CarTask(String url, RestCallback callback) {
            this.url = url;
            this.callback = callback;
        }

        @Override
        protected Car doInBackground(Void... params) {
            Log.i("CarTask", "DoInBackground.");
            Car car = new Car();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            try {
                car = restTemplate.getForObject(url, Car.class);
            } catch (Exception e) {
                Log.i("MainActivity - RestQuery", "Failed receiving json from: " + url + ". NestedException is: " + e.getMessage());
            }

            return car;
        }

        @Override
        protected void onPreExecute() {
            Log.i("CarTask", "PreExecute.");
            callback.preExecute();
        }

        @Override
        protected void onPostExecute(Car s) {
            Log.i("CarTask", "PostExecute.");
            callback.postExecute(s);
        }
    }

    public void cancelTalk() {
        if (currentTask != null) {
            currentTask.cancel(true);
        }
    }
}

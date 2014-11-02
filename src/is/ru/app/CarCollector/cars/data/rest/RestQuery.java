package is.ru.app.CarCollector.cars.data.rest;

import android.os.AsyncTask;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

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
        private RestQueryException exception = null;

        private CarTask(String url, RestCallback callback) {
            this.url = url;
            this.callback = callback;
        }

        @Override
        protected Car doInBackground(Void... params) {
            Log.i("CarTask", "DoInBackground.");
            Car car = new Car();

            try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders requestHeaders = new HttpHeaders();

                requestHeaders.add("Content-Type", "application/text; charset=utf-8");

                HttpEntity entity = new HttpEntity(requestHeaders);

                requestHeaders.set("Connection", "Close");
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                Log.i("CarTask", "Before Exchange.");
                HttpEntity<String> response = restTemplate.exchange( url, HttpMethod.GET, entity, String.class );
                Log.i("CarTask", "After Exchange.");
                String jsonString = response.getBody();
                //jsonString = restTemplate.getForObject(url, String.class);


                JSONObject jsonRoot = new JSONObject(jsonString);
                JSONArray jsonResults = (JSONArray) jsonRoot.get("results");

                for (int i = 0; i < jsonResults.length(); i++) {
                    JSONObject jsonCar = (JSONObject) jsonResults.get(i);
                    car.setNumber(jsonCar.get("number").toString());
                    car.setFactoryNumber(jsonCar.get("factoryNumber").toString());
                    car.setRegisteredAt(jsonCar.get("registeredAt").toString());
                    car.setSubType(jsonCar.get("subType").toString());
                    car.setType(jsonCar.get("type").toString());
                    car.setColor(jsonCar.get("color").toString());
                    car.setRegistryNumber(jsonCar.get("registryNumber").toString());
                    car.setStatus(jsonCar.get("status").toString());
                    car.setNextCheck(jsonCar.get("nextCheck").toString());
                    car.setPollution(jsonCar.get("pollution").toString());
                    car.setWeight(jsonCar.get("weight").toString());

                }
            } catch (Exception e) {
                String msg = "Failed receiving json from: " + url + ". NestedException is: " + e.getMessage();
                Log.i("MainActivity - RestQuery", msg);
                exception = new RestQueryException(msg, e);
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
            callback.postExecute(s, exception);
        }
    }

    public void cancelTask() {
        if (currentTask != null) {
            currentTask.cancel(true);
        }
    }
}

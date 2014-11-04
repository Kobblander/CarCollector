package is.ru.app.CarCollector.cars.data.rest;

import android.os.AsyncTask;
import android.util.Log;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.utilities.RestHelper;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
* Created with IntelliJ IDEA
* User : Oli
* Date : 11/3/2014
* Time : 15:52
*/
class CarTask extends AsyncTask<Void, Void, Car> {
    private final String url;
    private final RestCallback callback;
    private final int readTimeOutSeconds = 7;
    private final int connectionTimeOutSeconds = 5;
    private RestQueryException exception = null;

    CarTask(String url, RestCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    @Override
    protected Car doInBackground(Void... params) {
        Log.i("CarTask", "DoInBackground.");
        Car car = new Car();
        System.setProperty("http.keepAlive", "false");
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Accept-Encoding", "");

            requestHeaders.set("Connection", "Close");
            requestHeaders.add("Content-Type", "application/text; charset=utf-8");
            System.out.println(requestHeaders);
            HttpEntity entity = new HttpEntity(requestHeaders);

            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(connectionTimeOutSeconds * 1000);
            factory.setReadTimeout(readTimeOutSeconds * 1000);
            restTemplate.setRequestFactory(factory);
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            Log.i("CarTask", "Before Exchange.");
            HttpEntity<String> response = restTemplate.exchange( url, HttpMethod.GET, entity, String.class );
            Log.i("CarTask", "After Exchange.");
            String jsonString = response.getBody();

            jsonString = RestHelper.toUTF8(jsonString);

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
            Log.i("CarTask", msg);
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

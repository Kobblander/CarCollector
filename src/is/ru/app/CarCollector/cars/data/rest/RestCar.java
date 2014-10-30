package is.ru.app.CarCollector.cars.data.rest;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.utilities.LogToFile;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * <h1>RestQuery</h1>
 * <h2>is.ru.app.CarCollector.cars.service</h2>
 * <p>This class receives a GET request from apis.is/car?"licenceplate"</p>
 * Created on 29.10.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class RestCar implements RestQuery {

    @Override
    public Car getCar(String licencePlate) {
        Car car = new Car();

        String url = "apis.is/car?number=" + licencePlate;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try {
            String result = restTemplate.getForObject(url, String.class, "Android");
        } catch (RestClientException e) {
            LogToFile.log(e, "severe", "Error in receiving data from URL. " + e.getMessage());
        }

        return car;
    }
}

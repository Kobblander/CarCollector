package is.ru.app.CarCollector.cars.data.rest;

import is.ru.app.CarCollector.cars.data.dto.Car;
import is.ru.app.CarCollector.cars.service.CarNotFoundException;
import is.ru.app.CarCollector.utilities.LogToFile;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestClientException;
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
public class RestCar implements RestQuery {

    @Override
    public Car getCar(String registryNumber) {
        Car car = new Car();
        String result;

        String url = "apis.is/car?number=" + registryNumber;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try {
            result = restTemplate.getForObject(url, String.class, "Android");
        } catch (RestClientException e) {
            String msg = "Error in receiving data from URL. ";
            LogToFile.log(e, "severe", msg + e.getMessage());
            throw new CarNotFoundException(msg, e);
        }

        System.out.println(result);

        return car;
    }
}

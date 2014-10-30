package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.os.Bundle;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.service.CarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainActivity extends Activity {

    ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/CarService.xml");
    CarService carService;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        carService = (CarService) ctx.getBean("carService");
    }
}

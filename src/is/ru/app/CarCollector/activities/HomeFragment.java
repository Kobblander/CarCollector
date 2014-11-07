package is.ru.app.CarCollector.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;

/**
 * <h1>HomeFragment</h1>
 * <h2>is.ru.app.CarCollector.activities</h2>
 * <p></p>
 * Created on 7.11.2014.
 *
 * @author jakob
 * @version 1.1
 */
public class HomeFragment extends Fragment {

    private CarService carService;
    private GameService gameService;

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_layout, container, false);

        carService = new CarServiceData(rootView.getContext());
        gameService = new GameServiceData(rootView.getContext());

        return rootView;
    }
}

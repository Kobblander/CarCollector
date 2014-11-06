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
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/6/2014
 * Time : 18:20
 */
public class MainFragment extends Fragment {
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

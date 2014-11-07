package is.ru.app.CarCollector.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Statistics;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;
import is.ru.app.CarCollector.utilities.cards.CardManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/30/2014
 * Time : 22:22
 */
public class StatsFragment extends Fragment {
    private View rootView;
    private CarService carService;
    private GameService gameService;

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profile, container, false);

        carService = new CarServiceData(rootView.getContext());
        gameService = new GameServiceData(rootView.getContext());
        setCardlist();

        return rootView;
    }

    private void setCardlist() {
        Statistics stats = new Statistics();
        List<CarType> carTypes = new ArrayList<CarType>();

        try {
            stats = gameService.getStats();
        } catch (Exception e) {
            Log.i("ProfileActivity", "Exception from gameService; Nested exception is: " + e.getMessage());
            e.printStackTrace();
        }

        CardManager manager = new CardManager(rootView, stats);
        manager.spawnTypeCars(R.id.carContainer);
    }
}

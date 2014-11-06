package is.ru.app.CarCollector.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Statistics;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/30/2014
 * Time : 22:22
 */
public class ProfileFragment extends Fragment {
    private CarService carService;
    private GameService gameService;

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile, container, false);

        carService = new CarServiceData(rootView.getContext());
        gameService = new GameServiceData(rootView.getContext());

        return rootView;
    }

    private void setCarlist() {

        //List<Car> cars = new ArrayList<Car>();
        Statistics stats = new Statistics();
        List<CarType> carTypes = new ArrayList<CarType>();
        final ListView listview = (ListView) getView().findViewById(R.id.carList);

        try {
            //cars = carService.getCars(null);
            stats = gameService.getStats();
            carTypes = stats.getCarTypes();
        } catch (Exception e) {
            Log.i("ProfileActivity", "Exception from gameService; Nested exception is: " + e.getMessage());
            e.printStackTrace();
        }

        final ArrayList<String> list = new ArrayList<String>();
        /*
        for (int i = 0; i < cars.size(); ++i) {
            list.add(cars.get(i).getNumber());
        }
        */
        for (int i = 0; i < carTypes.size(); i++) {
            list.add(carTypes.get(i).getTypeName());
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(getView().getContext(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}

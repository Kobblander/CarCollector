package is.ru.app.CarCollector.utilities;

import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import is.ru.app.CarCollector.cars.service.CarService;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/5/2014
 * Time : 18:33
 */
public class myQueryTextListener implements SearchView.OnQueryTextListener {
    private CarService service;

    private MenuItem menuItem;
    private SearchView input;

    public myQueryTextListener(MenuItem searchMenuItem, SearchView searchView, CarService carService) {
        this.menuItem = searchMenuItem;
        this.input = searchView;
        this.service = carService;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Remove keyboard
        input.setVisibility(View.INVISIBLE);
        input.setVisibility(View.VISIBLE);

        // Clear query
        input.setQuery("", false);

        // Remove focus, to icon
        input.clearFocus();
        input.setIconified(true);

        // Get car
        /*
        try {
            service.addCar(query, restCallback);
        } catch (CarExistsException e1) {
            isCollectable = false;
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

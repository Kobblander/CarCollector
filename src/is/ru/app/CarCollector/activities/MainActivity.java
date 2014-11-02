package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQueryException;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;

public class MainActivity extends Activity implements RestCallback {
    private CarService carService = new CarServiceData(this);
    private RestCallback restCallback = this;
    private boolean isCollectable = true;
    private static ProgressDialog progressDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.main);

        // Set search
        final SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Remove keyboard
                searchView.setVisibility(View.INVISIBLE);
                searchView.setVisibility(View.VISIBLE);

                // Clear query
                searchView.setQuery("", false);

                // Remove focus, to icon
                searchView.clearFocus();
                searchView.setIconified(true);

                // Get car
                getCar(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
        });
    }

    public void profile(View view){
        Intent myIntent = new Intent(this, ProfileActivity.class);
        startActivity(myIntent);
    }

    private void getCar(String query) {
        try {
            carService.addCar(query, this);
        } catch (CarExistsException cee) {
            isCollectable = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preExecute() {
        this.showProgressDialog("Retrieving the car.");
    }

    @Override
    public void postExecute(Car response, RestQueryException exception) {
        Log.i("MainActivity - CarService", "addCar");
        displayCar(response);

        // If there is not an exception thrown.
        if (exception == null) {
            try {
                carService.addCarCallback(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.hideProgressDialog();
    }

    @Override
    public String inExecute() {
        return null;
    }

    @Override
    public void cancelExecute() {
        RestQuery.getInstance().cancelTask();
    }

    /**
     * Set car to view
     * @param response the car gotten from the api
     */
    public void displayCar(Car response) {
        ImageView carImage = (ImageView) findViewById(R.id.carimage);
        // carImage.setImageBitmap(image);

        // Set car type
        TextView type = (TextView) findViewById(R.id.type);
        type.setText(response.getType());

        // Set car subtype
        TextView subType = (TextView) findViewById(R.id.subtype);
        subType.setText(response.getSubType());

        // Set car numberplate
        TextView plateNumber = (TextView) findViewById(R.id.platenumber);
        plateNumber.setText(response.getNumber());

        // Set Pollution
        TextView pollution = (TextView) findViewById(R.id.pollution);
        pollution.setText(response.getPollution());

        // Set Weight
        TextView weight = (TextView) findViewById(R.id.weight);
        weight.setText(response.getWeight());

        // Set Status
        TextView status = (TextView) findViewById(R.id.status);
        status.setText(response.getStatus());
    }

	/**
	 * Shows a Progress Dialog with a Cancel Button
	 *
	 * @param msg
	 */
	public void showProgressDialog(String msg)
	{
		final RestCallback callback = this;

		// check for existing progressDialog
		if (progressDialog == null) {
			// create a progress Dialog
			progressDialog = new ProgressDialog(this);

			// remove the ability to hide it by tapping back button
			progressDialog.setIndeterminate(true);

			progressDialog.setCancelable(false);

			progressDialog.setMessage(msg);

			if (callback != null) {
				progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
					new Dialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Cancel the Task when user hits Cancel Button
							callback.cancelExecute();
						}
				});
			}
		}

		// now display it.
		progressDialog.show();
	}


	/**
	 * Hides the Progress Dialog
	 */
	public void hideProgressDialog() {

		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		progressDialog = null;
	}
}

package is.ru.app.CarCollector.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import android.widget.SearchView.OnQueryTextListener;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.cars.data.rest.RestQueryException;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;
import is.ru.app.CarCollector.utilities.DbHelper;
import is.ru.app.CarCollector.utilities.Debugger;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements RestCallback {
    private CarService carService = new CarServiceData(this);
    private GameService gameService = new GameServiceData(this);
    private RestCallback restCallback = this;
    private String currentQuery;
    private boolean isCollectable = true;
    private static ProgressDialog progressDialog;
	private LinearLayout myGallery;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Debugger.getInstance().resetDatabase(this);

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
                try {
                    currentQuery = query;
                    carService.addCar(query, restCallback);
                } catch (CarExistsException e1) {
                    isCollectable = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    public void camera(View view) {
        Intent myIntent = new Intent(this, ProfileListActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void preExecute() {
        this.showProgressDialog("Retrieving the car.");
    }

    @Override
    public void postExecute(Object response, Throwable exception) {
        Log.i("MainActivity", "postExecute");

        if (exception != null) {
            handleAsyncException(exception);
            return;
        }

        try {

            if (response.getClass() == Car.class) {
                carService.addCarCallback((Car) response);
                //gameService.updateStats((Car) response);
                displayCar((Car) response);
                Log.i("MainActivity", "postExecute - displaying car");
            }

            // Response is images
            if (response.getClass() == ArrayList.class) {
                displayImages((List<Bitmap>) response);
            }

            Log.i("MainActivity", "postExecute - adding car");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.hideProgressDialog();
    }

    public void handleAsyncException(Throwable exception) {
        Log.i("MainActivity", "postExecuteExceptionMessage - " + exception.getMessage());
        this.cancelExecute();
        if (exception.getCause().getClass() == UnknownHostException.class) {
            // TODO: DO STUFF
        }
        if (exception.getClass() == RestQueryException.class) {
            Log.i("MainActivity", "Retrying getting car");
            try {
                carService.addCar(currentQuery, restCallback);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        this.hideProgressDialog();
    }

    @Override
    public String inExecute() {
        return null;
    }

    @Override
    public void cancelExecute() {
        RestQuery.getInstance().cancelCarTask();
        RestQuery.getInstance().cancelImageTask();
    }

    /**
     * Set car to view
     * @param response the car gotten from the api
     */
    public void displayCar(Car response) {
        carService.addImage(response.getType(), response.getSubType(), response.getColor(), restCallback);

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
        TextView pollution = (TextView) findViewById(R.id.pollutionAns);
        pollution.setText(response.getPollution() + " g/km");

        // Set Weight
        TextView weight = (TextView) findViewById(R.id.weightAns);
        weight.setText(response.getWeight() + " kg");

        // Set registered
        TextView registered = (TextView) findViewById(R.id.registerdAns);
        registered.setText(response.getRegisteredAt());

        // Set Status
        TextView status = (TextView) findViewById(R.id.status);
        status.setText(response.getStatus());
        status.setTextColor(Color.GREEN);

        RelativeLayout carView = (RelativeLayout) findViewById(R.id.main);
        carView.setVisibility(View.VISIBLE);
    }

    private void displayImages(List<Bitmap> bmap) {
		myGallery = (LinearLayout)findViewById(R.id.mygallery);

		for(Bitmap map : bmap) {
			myGallery.addView(insertPhoto(map));
		}
    }

	View insertPhoto(Bitmap bm){

		LinearLayout layout = new LinearLayout(getApplicationContext());
		layout.setLayoutParams(new LayoutParams(300, 250));
		layout.setGravity(Gravity.CENTER);

		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setLayoutParams(new LayoutParams(270, 220));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageBitmap(bm);

		layout.addView(imageView);
		return layout;
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

package is.ru.app.CarCollector.activities;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.cars.data.rest.RestCallback;
import is.ru.app.CarCollector.cars.data.rest.RestQuery;
import is.ru.app.CarCollector.cars.data.rest.RestQueryException;
import is.ru.app.CarCollector.cars.models.Car;
import is.ru.app.CarCollector.cars.service.CarExistsException;
import is.ru.app.CarCollector.cars.service.CarService;
import is.ru.app.CarCollector.cars.service.CarServiceData;
import is.ru.app.CarCollector.game.models.Player;
import is.ru.app.CarCollector.game.service.GameService;
import is.ru.app.CarCollector.game.service.GameServiceData;
import is.ru.app.CarCollector.game.service.GameServiceException;
import is.ru.app.CarCollector.utilities.Debugger;
import is.ru.app.CarCollector.utilities.dialog.CarExistsDialog;
import is.ru.app.CarCollector.utilities.dialog.ErrorMessageDialog;
import is.ru.app.CarCollector.utilities.navbar.NavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements RestCallback, ErrorMessageDialog.ErrorDialogListener {
    private CarService carService = new CarServiceData(this);
    private GameService gameService = new GameServiceData(this);
    private RestCallback restCallback = this;
    private String currentQuery;
    private boolean isCollectable = true;
    private static ProgressDialog progressDialog;
    private static AlertDialog errorDialog;
    private NavigationDrawer nav;
	private LinearLayout myGallery;
    private Player currentPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Player player = new Player("Captain America");
        Debugger.getInstance().resetDatabase(this);
        try {
            currentPlayer = gameService.addPlayer(player);
        } catch (GameServiceException e) {
        }


        nav = new NavigationDrawer(this);
        nav.setup();

        // Display main view
        nav.displayView(0);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean queryTextFocused) {
                if(!queryTextFocused) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Remove keyboard
                currentQuery = query;
                searchView.setVisibility(View.INVISIBLE);
                searchView.setVisibility(View.VISIBLE);

                // Clear query
                searchView.setQuery("", false);

                // Remove focus, to icon
                searchView.clearFocus();
                searchView.setIconified(true);

                // Set the main fragment
                nav.displayView(0);

                // Load main fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();

                RelativeLayout carView = (RelativeLayout) findViewById(R.id.main);
                carView.setVisibility(View.INVISIBLE);

				// Strip spaces
				query.replaceAll("\\s","");

                // Get car
                getCar(query);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void getCar(String query) {
        try {
            carService.addCar(query, restCallback);
        } catch (CarExistsException e1) {
            showCarExistsDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        nav.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        nav.syncToggle();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        nav.onToggleConfigChange(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean navItemPressed = nav.onOptionsItemSelected(item);

        // If nav item was pressed we want to return true
        if(navItemPressed) return navItemPressed;

        return super.onOptionsItemSelected(item);
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
            this.hideProgressDialog();
            handleAsyncException(exception);
            return;
        }

        try {

            if (response.getClass() == Car.class) {
                Car car = (Car) response;
                carService.addCarCallback(car);
                Car test = carService.getCarByRegistryNumber(car.getRegistryNumber());
                gameService.updateStats(car, currentPlayer);
                displayCar(car);
                carService.addImage(car.getType(), car.getSubType(), car.getColor(), car.getRegisteredAt(), restCallback);
                this.hideProgressDialog();

                Log.i("MainActivity", "postExecute - displaying car");
            }

            // Response is images
            if (response.getClass() == ArrayList.class) {
                displayImages((List<Bitmap>) response);

                // Don't hide progress dialog until after images have arrived.
            }

            Log.i("MainActivity", "postExecute - adding car");
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentQuery = "";

    }

    public void handleAsyncException(Throwable exception) {
        Log.i("MainActivity", "postExecuteExceptionMessage - " + exception.getMessage());
        exception.printStackTrace();
        this.cancelExecute();
        if (exception.getClass() == RestQueryException.class) {
            Log.i("MainActivity", "Showing errorDialog.");
            this.showErrorDialog();
            return;
        }
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
        /*
        // Set Status
        TextView status = (TextView) findViewById(R.id.status);
        status.setText(response.getStatus());
        status.setTextColor(Color.GREEN);*/

		RelativeLayout carView = (RelativeLayout) findViewById(R.id.main);
		carView.setVisibility(View.VISIBLE);
    }

    private void displayImages(List<Bitmap> bmap) {
		myGallery = (LinearLayout)findViewById(R.id.mygallery);
		myGallery.removeAllViews();

		for(Bitmap map : bmap) {
			Log.i("Loading image", Integer.toString(bmap.size()));
			myGallery.addView(insertPhoto(map));
		}
    }

	View insertPhoto(Bitmap bm){

		LinearLayout layout = new LinearLayout(getApplicationContext());

		layout.setGravity(Gravity.CENTER);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(bm.getWidth(), bm.getHeight());
		params.setMargins(10, 20, 10, 30);
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setLayoutParams(params);
		imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
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
        Log.i("MainActivity", "Showing progress dialog.");
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
        Log.i("MainActivity", "Hiding progress dialog.");

		if (progressDialog != null) {
			progressDialog.dismiss();
		}

		progressDialog = null;
	}

    public void showCarExistsDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new CarExistsDialog();
        dialog.show(getFragmentManager(), "CarExistsErrorDialog");
    }

    public void showErrorDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new ErrorMessageDialog();
        dialog.show(getFragmentManager(), "ErrorDialog");
    }

    @Override
    public void onErrorDialogPositiveClick(DialogFragment dialog) {
        Log.i("Main activity", "ErrorDialog positive click. Getting car again.");
        getCar(currentQuery);
    }

    @Override
    public void onErrorDialogNegativeClick(DialogFragment dialog) {
        Log.i("Main activity", "ErrorDialog negative click.");
    }
}

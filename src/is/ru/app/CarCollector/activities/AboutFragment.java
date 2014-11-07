package is.ru.app.CarCollector.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.utilities.navbar.NavigationDrawer;

/**
 * Created by steinar on 7.11.2014.
 */
public class AboutFragment extends Fragment {

	private NavigationDrawer nav;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.about, container, false);

		return rootView;
	}
}

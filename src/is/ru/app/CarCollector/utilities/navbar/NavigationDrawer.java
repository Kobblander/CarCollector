package is.ru.app.CarCollector.utilities.navbar;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.activities.CarFragment;
import is.ru.app.CarCollector.activities.HomeFragment;
import is.ru.app.CarCollector.activities.StatsFragment;
import is.ru.app.CarCollector.utilities.dialog.ResetDatabaseDialog;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/5/2014
 * Time : 16:19
 */
public class NavigationDrawer {
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavList;
    private TypedArray navMenuIcons;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private Activity activity;

    public NavigationDrawer(Activity activity) {
        // Get Activity title
        mTitle = mDrawerTitle = activity.getTitle();

        // Get string list of nav items
        mNavList = activity.getResources().getStringArray(R.array.nav_drawer_items);

        navMenuIcons = activity.getResources().obtainTypedArray(R.array.nav_drawer_icons);

        // Get draw layout widget
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        // Get nav list
        mDrawerList = (ListView) activity.findViewById(R.id.left_drawer);

        // Set activity
        this.activity = activity;
    }

    public void setup() {
        // Set navigation list adapter and click listener
        mDrawerList.setAdapter(setupAdapter());
        mDrawerList.setOnItemClickListener(new NavClickListener());

        // Set toggle
        mDrawerToggle = new ActionBarDrawerToggle(
                activity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                activity.getActionBar().setTitle(mTitle);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                activity.getActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set toggle listener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private NavAdapter setupAdapter() {
        ArrayList<NavItem> navDrawerItems = new ArrayList<NavItem>();

        // Home
        navDrawerItems.add(new NavItem(mNavList[0], navMenuIcons.getResourceId(0, -1)));

        // Stats
        navDrawerItems.add(new NavItem(mNavList[1], navMenuIcons.getResourceId(1, -1)));

        // Settings
        //navDrawerItems.add(new NavItem(mNavList[2], navMenuIcons.getResourceId(2, -1)));

        // Delete
        navDrawerItems.add(new NavItem(mNavList[2], navMenuIcons.getResourceId(3, -1)));

        return new NavAdapter(activity.getApplicationContext(), navDrawerItems);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        // Hide / show menu
       // menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
    }

    public void syncToggle() {
        mDrawerToggle.syncState();
    }

    public void onToggleConfigChange(Configuration newConfig) {
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    private class NavClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    public void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new StatsFragment();
                break;
            case 2:
                showResetDatabaseDialog();
                // fragment = new StatsFragment();
                break;
            case 3:
                break;
            case 4:
                fragment = new CarFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack( "tag" ).commit();

            if (fragment.getClass() != CarFragment.class) {
                // update selected item and title, then close the drawer
                mDrawerList.setItemChecked(position, true);
                mDrawerList.setSelection(position);
                activity.setTitle(mNavList[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void showResetDatabaseDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new ResetDatabaseDialog();
        dialog.show(activity.getFragmentManager(), "ErrorDialog");
    }
}

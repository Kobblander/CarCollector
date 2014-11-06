package is.ru.app.CarCollector.utilities.navbar;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import is.ru.app.CarCollector.R;

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
        // Set navigation list adapter
        mDrawerList.setAdapter(setupAdapter());

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

        // Profile
        navDrawerItems.add(new NavItem(mNavList[0], navMenuIcons.getResourceId(0, -1)));

        // Stats
        navDrawerItems.add(new NavItem(mNavList[1], navMenuIcons.getResourceId(1, -1)));

        // Settings
        navDrawerItems.add(new NavItem(mNavList[2], navMenuIcons.getResourceId(2, -1)));

        // Delete
        navDrawerItems.add(new NavItem(mNavList[3], navMenuIcons.getResourceId(3, -1)));

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
}

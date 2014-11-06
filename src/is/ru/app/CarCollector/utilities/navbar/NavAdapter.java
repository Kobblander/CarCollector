package is.ru.app.CarCollector.utilities.navbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import is.ru.app.CarCollector.R;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/5/2014
 * Time : 17:26
 */
public class NavAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavItem> navItems;

    public NavAdapter(Context context, ArrayList<NavItem> navItems){
        this.context = context;
        this.navItems = navItems;
    }

    @Override
    public int getCount() {
        return navItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(navItems.get(position).getIcon());
        txtTitle.setText(navItems.get(position).getTitle());

        return convertView;
    }
}

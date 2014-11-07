package is.ru.app.CarCollector.utilities.cards;

import android.content.Context;
import android.view.*;
import android.widget.*;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/7/2014
 * Time : 03:11
 */
public class CardManager {
    private View view;
    private Statistics stats;
    private Display screen;
    private List<Integer> ids = new ArrayList<Integer>();

    public CardManager(View rootView, Statistics stats) {
        this.view = rootView;
        this.stats = stats;

        // Set the init id
        ids.add(0);

        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        screen = wm.getDefaultDisplay();
    }

    public void spawnTypeCars(int containerID) {
        RelativeLayout container = (RelativeLayout) view.findViewById(containerID);
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout card = null;

        List<CarType> carTypes = stats.getCarTypes();

        for (CarType carType : carTypes) {
            int aboveId = ids.get(ids.size() - 1);
            int cardId = aboveId + 1;
            card = (LinearLayout) inflater.inflate(R.layout.temp_card, null);

            setCardPos(card, aboveId);

            card.setId(cardId);
            ids.add(cardId);

            setCardInfo(carType, card);

            container.addView(card);
        }
    }

    private void setCardPos(LinearLayout card, int id) {
        RelativeLayout.LayoutParams paramsCard = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);

        if(id > 0)
            paramsCard.addRule(RelativeLayout.BELOW, id);

        card.setLayoutParams(paramsCard);
    }

    private void setCardInfo(CarType carType, LinearLayout card) {
        HorizontalScrollView scroll = (HorizontalScrollView) card.getChildAt(0);
        RelativeLayout innerCard = (RelativeLayout) scroll.getChildAt(0);

        TextView typeName = (TextView) innerCard.getChildAt(3);
        ImageView logo = (ImageView) innerCard.getChildAt(0);
        TextView lvl = (TextView) innerCard.getChildAt(4);
        ProgressBar lvlBar = (ProgressBar) innerCard.getChildAt(1);
        TextView lvlStats = (TextView) innerCard.getChildAt(2);

        // Set Type name
        typeName.setText(carType.getTypeName());

        // Set lvl
        String lvlText = Integer.toString(carType.getLevelCur());
        lvl.setText(lvlText);

        // Set lvl progress
        lvlBar.setProgress((int) carType.getLevelXpCur());

		String toLower = carType.getTypeName().toLowerCase();

		String mDrawableName = toLower.replace(" ", "_");
		int resID = view.getContext().getResources().getIdentifier(mDrawableName , "drawable", view.getContext().getPackageName());

		logo.setImageResource(resID);

        // Set lvl stats text
        String stats = Integer.toString ((int) carType.getLevelXpCur()) + "/" + Integer.toString((int) carType.getXpForNextLevelCur());
        lvlStats.setText(stats);
    }

    private void setMargin() {

    }



}

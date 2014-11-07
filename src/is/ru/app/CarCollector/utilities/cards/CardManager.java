package is.ru.app.CarCollector.utilities.cards;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.*;
import android.widget.*;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.game.models.CarSubType;
import is.ru.app.CarCollector.game.models.CarType;
import is.ru.app.CarCollector.game.models.Statistics;
import is.ru.app.CarCollector.game.models.TypeStats;

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
    final private Statistics stats;
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

        final List<TypeStats> carTypes = stats.getTypeStats();

        for (final TypeStats carType : carTypes) {
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

	void showSubDialog(List<CarSubType> subTypes) {
		Dialog builder = new Dialog(view.getContext());
		builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		builder.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialogInterface) {
				//nothing;
			}
		});

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		RelativeLayout container = new RelativeLayout(view.getContext());
		container.setLayoutParams(params);
		ScrollView sLay = new ScrollView(view.getContext());
		sLay.setLayoutParams(params);
		container.addView(sLay);

		RelativeLayout.LayoutParams cardParama = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		RelativeLayout cardHolder = new RelativeLayout(view.getContext());

		sLay.addView(cardHolder);

		LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout card = null;

		for (CarSubType subType : subTypes) {
			int aboveId = ids.get(ids.size() - 1);
			int cardId = aboveId + 1;
			card = (LinearLayout) inflater.inflate(R.layout.temp_subcard, null);

			if(aboveId > 0)
				setCardPos(card, aboveId);

			card.setId(cardId);
			ids.add(cardId);

			setCardSubInfo(subType, card);

			cardHolder.addView(card);
		}

		builder.addContentView(container, cardParama);

		builder.show();
	}

	private void setCardSubInfo(CarSubType subType, LinearLayout card) {
		HorizontalScrollView scroll = (HorizontalScrollView) card.getChildAt(0);
		RelativeLayout innerCard = (RelativeLayout) scroll.getChildAt(0);

		TextView subName = (TextView) innerCard.getChildAt(2);
		ProgressBar lvlBar = (ProgressBar) innerCard.getChildAt(0);
		TextView lvlStats = (TextView) innerCard.getChildAt(1);

		subName.setText(subType.getSubTypeName());

		lvlBar.setProgress((int) subType.getLevelXpCur());

		// Set lvl stats text
		String stats = Integer.toString ((int) subType.getLevelXpCur()) + "/" + Integer.toString((int) subType.getXpForNextLevelCur());
		lvlStats.setText(stats);
	}


    private void setCardPos(LinearLayout card, int id) {

		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);

		int height = Math.round(125 * displayMetrics.density);

        RelativeLayout.LayoutParams paramsCard = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, height);

        if(id > 0)
            paramsCard.addRule(RelativeLayout.BELOW, id);

        card.setLayoutParams(paramsCard);
    }

    private void setCardInfo(final TypeStats tStat, LinearLayout card) {
        HorizontalScrollView scroll = (HorizontalScrollView) card.getChildAt(0);
        RelativeLayout innerCard = (RelativeLayout) scroll.getChildAt(0);

		CarType carType = tStat.getCarType();

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
		int resID = view.getContext().getResources().getIdentifier(mDrawableName, "drawable", view.getContext().getPackageName());

		logo.setImageResource(resID);

        // Set lvl stats text
        String stats = Integer.toString ((int) carType.getLevelXpCur()) + "/" + Integer.toString((int) carType.getXpForNextLevelCur());
        lvlStats.setText(stats);

		logo.setClickable(true);
		logo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				showSubDialog(tStat.getCarSubTypes());
			}
		});

    }
}

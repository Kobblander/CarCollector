package is.ru.app.CarCollector.utilities.cards;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import is.ru.app.CarCollector.R;
import is.ru.app.CarCollector.activities.MainActivity;
import is.ru.app.CarCollector.game.models.CarSubType;
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
    final private Statistics stats;
    private List<Integer> ids = new ArrayList<Integer>();

    public CardManager(View rootView, Statistics stats) {
        this.view = rootView;
        this.stats = stats;

        // Set the init id
        ids.add(0);
    }

    public void spawnTypeCars(int containerID) {
        RelativeLayout container = (RelativeLayout) view.findViewById(containerID);
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout card = null;

        List<CarType> carTypes = stats.getCarTypes();

        for (final CarType carType : carTypes) {
            int aboveId = ids.get(ids.size() - 1);
            int cardId = aboveId + 1;
            card = (LinearLayout) inflater.inflate(R.layout.temp_card, null);

            if(aboveId > 0)
                setCardPos(card, aboveId);

            card.setId(cardId);
            ids.add(cardId);

            setCardInfo(carType, card);

            container.addView(card);
			card.setClickable(true);
			card.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					showImageDialog(stats.getCarSubTypes());
				}
			});
        }
    }

	void showImageDialog(List<CarSubType> subTypes) {
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

		LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout card = null;

		for (CarSubType subType : subTypes) {
			int aboveId = ids.get(ids.size() - 1);
			int cardId = aboveId + 1;
			card = (LinearLayout) inflater.inflate(R.layout.temp_card, null);

			if(aboveId > 0)
				setCardPos(card, aboveId);

			card.setId(cardId);
			ids.add(cardId);

			setCardSubInfo(subType, card);

			container.addView(card);
		}

		builder.addContentView(container, params);

		builder.show();
	}

	private void setCardSubInfo(CarSubType subType, LinearLayout card) {
		RelativeLayout innerCard = (RelativeLayout) card.getChildAt(0);


		TextView subName = (TextView) innerCard.getChildAt(0);
		ImageView logo = (ImageView) innerCard.getChildAt(1);
		TextView lvl = (TextView) innerCard.getChildAt(3);
		ProgressBar lvlBar = (ProgressBar) innerCard.getChildAt(4);
		TextView lvlStats = (TextView) innerCard.getChildAt(5);

		subName.setText(subType.getSubTypeName());

		String lvlText = Integer.toString(subType.getLevelCur());
		lvl.setText(lvlText);

		lvlBar.setProgress((int) subType.getLevelXpCur());

		// Set lvl stats text
		String stats = Integer.toString ((int) subType.getLevelXpCur()) + "/" + Integer.toString((int) subType.getXpForNextLevelCur());
		lvlStats.setText(stats);
	}

	private void setCardPos(LinearLayout card, int id) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.BELOW, id);

        card.setLayoutParams(params);
    }

    private void setCardInfo(CarType carType, LinearLayout card) {
        RelativeLayout innerCard = (RelativeLayout) card.getChildAt(0);


        TextView typeName = (TextView) innerCard.getChildAt(0);
        ImageView logo = (ImageView) innerCard.getChildAt(1);
        TextView lvl = (TextView) innerCard.getChildAt(3);
        ProgressBar lvlBar = (ProgressBar) innerCard.getChildAt(4);
        TextView lvlStats = (TextView) innerCard.getChildAt(5);

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
    }

}

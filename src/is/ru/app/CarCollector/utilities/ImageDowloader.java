package is.ru.app.CarCollector.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/1/2014
 * Time : 21:19
 */
public class ImageDowloader {

    public static Bitmap getImage(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;

        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

}

package is.ru.app.CarCollector.activities;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Window;
import is.ru.app.CarCollector.R;
/*
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.widget.Toast;
import com.googlecode.tesseract.android.TessBaseAPI;
import android.util.Log;
import android.media.ExifInterface;
import java.io.IOException;
*/



/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/1/2014
 * Time : 19:51
 */
public class CameraActivity extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

	private CameraCallback cameraCallback;

	public interface CameraCallback {
		public void getCarByImage(String searchString);
	}

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		Intent intent = getIntent();

        // Hide the action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.camera);
        dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

			String _path = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/numer.jpg";

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			Bitmap bitmap = BitmapFactory.decodeFile(_path, options);


			ExifInterface exif = null;
			try {
				exif = new ExifInterface(_path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int exifOrientation = 0;
			if (exif != null) {
				exifOrientation = exif.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);
			}

			int rotate = 0;

			switch (exifOrientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
			}

			if (rotate != 0) {
				int w = bitmap.getWidth();
				int h = bitmap.getHeight();

				// Setting pre rotate
				Matrix mtx = new Matrix();
				mtx.preRotate(rotate);

				// Rotating Bitmap & convert to ARGB_8888, required by tess
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false);
			}

			TessBaseAPI baseApi = new TessBaseAPI();

			String dPath = Environment.getExternalStorageDirectory().toString() + "/";

			baseApi.init(dPath, "isl");
			baseApi.setImage(bitmap);
			String recognizedText = baseApi.getUTF8Text();
			baseApi.end();

			String newString = recognizedText.trim();
			newString.replaceAll("[_\\W]", "");

			char[] isl = {'Á', 'É', 'Ð', 'Í', 'Ó', 'Ú', 'Ý', 'Þ', 'Æ', 'Ö'};

			StringBuilder stringBuilder = new StringBuilder();
			for(int i = 0; i < newString.length(); i++) {
				String c = newString.substring(i, i+1);
				char cChar = newString.charAt(i);

				if(c.matches("[A-Z0-9]"))
					stringBuilder.append(c);
				for(int j = 0; j < isl.length; j++) {
					if(cChar == isl[j])
						stringBuilder.append(cChar);
				}
			}
			String finalString = stringBuilder.toString();
			Log.i("Camera reader", finalString);
			Toast toast = Toast.makeText(this, "From camera: " + finalString, 20);
			toast.show();
			File file = new File(_path);
			boolean deleted = file.delete();
			Intent myIntent = new Intent(this, CameraActivity.class);
			myIntent.putExtra("query", finalString);
			setResult(1, myIntent);
            finish();
        }*/
    }

    private void dispatchTakePictureIntent() {
		String file = Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera/numer.jpg";
		Uri uriSavedImage = Uri.fromFile(new File(file));

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;

        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            e.printStackTrace();
        }

        return c; // returns null if camera is unavailable
    }

}

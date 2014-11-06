package is.ru.app.CarCollector.cars.data.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import is.ru.app.CarCollector.utilities.RestHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA
* User : Oli
* Date : 11/3/2014
* Time : 15:45
*/
class ImageTask extends AsyncTask<Void, Void, List<Bitmap>> {
    private final String url;
    private final RestCallback callback;
    private RestQueryException exception = null;

    ImageTask(String url, RestCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    @Override
    protected List<Bitmap> doInBackground(Void... params) {
        Log.i("ImageTask", "DoInBackground.");

        // Setup request
        RestTemplate restTemplate = RestHelper.getJsonTemplate();
        HttpEntity entity = new HttpEntity(setHeaders());
        List<Bitmap> bMap = new ArrayList<Bitmap>();

        try {
            // Send google image api request
            Log.i("ImageTask", "Before Exchange.");
            HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            Log.i("ImageTask", "After Exchange.");

            // Convert the request to UTF8
            String json = RestHelper.toUTF8(response.getBody());

            // Get the img urls from the json
            List<String> urls = getUrls(json);

            // Setup image request
            RestTemplate restTemplateImg = RestHelper.getImgTemplate();

            // Get the image in bitmap form
            for (int i = 0; i < urls.size(); i++) {
                bMap.add(urlToBitmap(restTemplateImg, entity, urls.get(i)));
            }
        } catch (Exception e) {
            String msg = "Failed receiving image from: " + url + ". NestedException is: " + e.getMessage();
            exception = new RestQueryException(msg, e);
        }

        return bMap;
    }

    /**
     * Reads the json to get the images urls
     * @param json google api json
     * @return list of img urls
     */
    private List<String> getUrls(String json) {
        List<String> urlList = new ArrayList<String>();

        try {
            JSONObject jsonRoot = new JSONObject(json);
            JSONObject result = (JSONObject) jsonRoot.get("responseData");
            JSONArray imgList = (JSONArray) result.get("results");

            for (int i = 0; i < imgList.length(); i++) {
                JSONObject img = (JSONObject) imgList.get(i);
                urlList.add((String) img.get("url"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return urlList;
    }

    /**
     * Gets the image by the url and returns it in bitmap form
     * @param rest restTemplate used to query for the image
     * @param entity query header
     * @param url image url
     * @return image in bitmap form
     */
    private Bitmap urlToBitmap(RestTemplate rest, HttpEntity entity, String url) {
        ResponseEntity<Resource> respond = rest.exchange(url, HttpMethod.GET, entity, Resource.class);
        Bitmap map;
		Bitmap scaledMap;
		double scale;
		int height;
		int width;

        try {
			map = BitmapFactory.decodeStream(respond.getBody().getInputStream());
			scale = calculateScale(map);
			height = (int) (scale * map.getHeight());
			width = (int) (scale * map.getWidth());
			scaledMap = Bitmap.createScaledBitmap(map, width, height, true);
        } catch (IOException e) {
            e.printStackTrace();
			return null;
        }
        return scaledMap;
    }

	double calculateScale(Bitmap map) {
		double height = map.getHeight();

		return 150/height;
	}

    /**
     * Setups the request header
     * @return the request header
     */
    private HttpHeaders setHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        requestHeaders.set("Accept-Encoding", "gzip,deflate,sdch");
        requestHeaders.set("Connection", "Close");
        requestHeaders.add("Content-Type", "application/text; charset=utf-8");

        return requestHeaders;
    }


    @Override
    protected void onPostExecute(List<Bitmap> s) {
        Log.i("ImageTask", "PostExecute.");
        callback.postExecute(s, exception);
    }
}
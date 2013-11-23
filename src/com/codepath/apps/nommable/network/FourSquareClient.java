package com.codepath.apps.nommable.network;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.util.Log;

import com.codepath.apps.nommable.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 
 * FourSquare allows userless access to their search API endpoints (i.e. no OAuth required).
 * Use this class to organize async calls to FourSquare.
 * 
 */

public class FourSquareClient {
    private String clientId;
    private String clientSecret;
    private static FourSquareClient instance;

    private static final String BASE_URL = "https://api.foursquare.com/v2/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public FourSquareClient(Context context) {
    	Resources res = context.getResources();
    	clientId = res.getString(R.string.clientid);
    	clientSecret = res.getString(R.string.clientsecret);
    }
    
    /**
     * Makes a get request to FourSquare for a list of locations.
     * The HTTP response is an array of compact venues.
     * See https://developer.foursquare.com/docs/venues/search
     * 
     * @param location the user's current location
     * @param handler an AsyncHttpResponseHandler that performs some action upon receiving a response
     */
    public void search(Location location, AsyncHttpResponseHandler handler) {
    	Log.d("DEBUG", "Starting FourSquare search");
    	String apiUrl = BASE_URL + "venues/search";
    	RequestParams params = new RequestParams();
    	params.put("limit", "30");
    	params.put("intent", "browse");
    	params.put("ll", Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude()));
    	params.put("radius", "1600"); // ~1 mile
    	params.put("client_id", clientId);
    	params.put("v", "20131123"); // see https://developer.foursquare.com/overview/versioning
    	params.put("client_secret", clientSecret);
    	client.get(apiUrl, params, handler);
    }
    
    /**
     * Makes a get request to FourSquare for recommended venues near a location.
     * This is similar to their search endpoint, but offers more optional parameters.
     * See https://developer.foursquare.com/docs/venues/explore
     * 
     * @param location the user's current location
     * @param handler an AsyncHttpResponseHandler for managing the response
     */
    public void explore(Location location, AsyncHttpResponseHandler handler) {
    	Log.d("DEBUG", "Starting FourSquare explore");
    	String apiUrl = BASE_URL + "venues/explore";
    	RequestParams params = new RequestParams();
    	params.put("section", "food");
    	params.put("limit", "30");
    	params.put("ll", Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude()));
    	params.put("radius", "1600"); // ~1 mile
    	params.put("venuePhotos", "1");
    	params.put("openNow", "1"); // comment this out when testing at 3am
    	params.put("v", "20131123"); // see https://developer.foursquare.com/overview/versioning
    	params.put("client_id", clientId);
    	params.put("client_secret", clientSecret);
    	client.get(apiUrl, params, handler);
    }
    
    /**
     * Makes a get request to FourSquare for an array of their current list of categories.
     * See https://developer.foursquare.com/docs/venues/categories
     * 
     * @param handler an AsyncHttpResponseHandler that performs some action upon receiving a response
     */
    public static void getCategories(AsyncHttpResponseHandler handler) {
    	Log.d("DEBUG", "Getting FourSquare venue categories");  	
    }
    
    /**
     * Your standard singleton getter
     * 
     * @param context The current context
     * @return FourSquareClient a FourSquareClient singleton object
     */
    
    public static FourSquareClient getInstance(Context context) {
    	if (instance == null) {
    		instance = new FourSquareClient(context);
    	}
		return instance;
    }

}
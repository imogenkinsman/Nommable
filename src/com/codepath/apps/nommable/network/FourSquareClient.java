package com.codepath.apps.nommable.network;

import android.app.KeyguardManager;
import android.location.Location;
import android.util.Log;

import com.codepath.apps.nommable.helpers.Keymaster;
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
    public static final String CLIENT_ID = Keymaster.getClientId();
    public static final String CLIENT_SECRET = Keymaster.getClientSecret();

    private static final String BASE_URL = "https://api.foursquare.com/v2/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Makes a get request to FourSquare for a list of locations.
     * The HTTP response is an array of compact venues.
     * See https://developer.foursquare.com/docs/venues/search
     * 
     * @param location the user's current location
     * @param handler an AsyncHttpResponseHandler that performs some action upon receiving a response
     */
    public static void search(Location location, AsyncHttpResponseHandler handler) {
    	Log.d("DEBUG", "Starting FourSquare search");
    	String apiUrl = BASE_URL + "venues/search";
    	RequestParams params = new RequestParams();
    	params.put("limit", "30");
    	params.put("intent", "browse");
    	params.put("ll", Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude()));
    	params.put("radius", "1600"); // ~1 mile
    	params.put("client_id", CLIENT_ID);
    	params.put("client_secret", CLIENT_SECRET);
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

}
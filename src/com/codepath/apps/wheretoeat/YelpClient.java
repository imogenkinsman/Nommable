package com.codepath.apps.wheretoeat;

import org.scribe.builder.api.Api;
import org.scribe.model.Token;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 *
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes:
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 *
 */
public class YelpClient extends OAuthBaseClient {
<<<<<<< HEAD
    public static final Class<? extends Api> REST_API_CLASS = YelpApi2.class; // Change this
    public static final String REST_URL = "http://api.yelp.com/v2"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "tJ3VZbopuTYqDeP_ryMifQ";       // Change this
    public static final String TOKEN = "_5hTM7yu_Dt9JnA5DMxP5UNJwV6XJxDQ";
    public static final String TOKEN_SECRET = "e0y3wvIL2N-F-1D_ohqemGCcfgk";
    public static final String REST_CONSUMER_SECRET = "4o4nw2Q_UB-EQThHNa71HqSW-HA"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://findmerestaurant"; // Change this (here and in manifest)

=======
    public static final Class<? extends Api> REST_API_CLASS = YelpApi2.class; 
    public static final String REST_URL = "http://api.yelp.com/v2"; 
    public static final String REST_CONSUMER_KEY = "WwVnz4cduQpvMt8QDd50MA";     
    public static final String REST_CONSUMER_SECRET = "9hqo5hZAy8Lw4cLalQpAwXF3sDw"; 
    public static final String TOKEN = "_5hTM7yu_Dt9JnA5DMxP5UNJwV6XJxDQ";
    public static final String TOKEN_SECRET = "e0y3wvIL2N-F-1D_ohqemGCcfgk";
    public static final String REST_CALLBACK_URL = "oauth://cpyelp"; 
    
>>>>>>> 3f5de1e... MapsFragment and some little changes
    public YelpClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
        this.client.setAccessToken(new Token(TOKEN, TOKEN_SECRET));
    }

    public void search(String term, Location location, AsyncHttpResponseHandler handler) {
    	// http://api.yelp.com/v2/search?term=food&location=San+Francisco
<<<<<<< HEAD
    	Log.d("DEBUG", "starting search");
=======
>>>>>>> 3f5de1e... MapsFragment and some little changes
    	String apiUrl = getApiUrl("search");
        RequestParams params = new RequestParams();
       // params.put("category_filter", "restaurant");
        params.put("term", term);
<<<<<<< HEAD
        params.put("ll", location.getLatitude() + "," + location.getLongitude());
=======
        params.put("location", location);
>>>>>>> 3f5de1e... MapsFragment and some little changes
        params.put("sort", "2"); // "Highest Rated"
        client.get(apiUrl, params, handler);
    }


    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}
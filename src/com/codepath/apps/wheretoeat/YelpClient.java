package com.codepath.apps.wheretoeat;

import org.scribe.builder.api.Api;
import android.content.Context;

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
    public static final Class<? extends Api> REST_API_CLASS = YelpApi2.class; 
    public static final String REST_URL = "http://api.yelp.com/v2"; 
    public static final String REST_CONSUMER_KEY = "WwVnz4cduQpvMt8QDd50MA";     
    public static final String REST_CONSUMER_SECRET = "9hqo5hZAy8Lw4cLalQpAwXF3sDw"; 
    public static final String TOKEN = "QV1fR6bpacFJdFBIwBaRYIndO5iy0h2I";
    public static final String TOKEN_SECRET = "KdVBk5gzCC39UA0sRsaJesh6MxE";
    public static final String REST_CALLBACK_URL = "oauth://cpyelp"; 
    
    public YelpClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void search(String term, String location, AsyncHttpResponseHandler handler) {
    	// http://api.yelp.com/v2/search?term=food&location=San+Francisco
    	String apiUrl = getApiUrl("search");
        RequestParams params = new RequestParams();
       // params.put("category_filter", "restaurant");
        params.put("term", term);
        params.put("location", location);
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

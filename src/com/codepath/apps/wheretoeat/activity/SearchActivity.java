package com.codepath.apps.wheretoeat.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.YelpApp;
import com.codepath.apps.wheretoeat.models.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class SearchActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {

	private LocationClient locationClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
        // check Google Play service APK is available and up to date.
        // see http://developer.android.com/google/play-services/setup.html
        final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
        	// directs user to update google play services if no workie
        	GooglePlayServicesUtil.getErrorDialog(result, this, 0).show();
        }

        locationClient = new LocationClient(this, this, this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
    @Override
    protected void onResume() {
            super.onResume();

            locationClient.connect();
    }

    @Override
    protected void onPause() {
            super.onPause();

            locationClient.disconnect();
    }

	public void findRestaurant(View v) {
		YelpApp.getRestClient().search("restaurant", locationClient.getLastLocation(), new JsonHttpResponseHandler() {
			
			@Override
			public void onSuccess(int httpResponse, JSONObject jsonResponse) {
				try {
					Log.d("DEBUG", jsonResponse.getJSONArray("businesses").toString());
					ArrayList<Restaurant> restaurants = Restaurant.fromJson(jsonResponse.getJSONArray("businesses"));
					Intent i = new Intent(SearchActivity.this, SearchResultActivity.class);
					i.putExtra("restaurants", restaurants);
					//send curr location data too
					i.putExtra("latitude", locationClient.getLastLocation().getLatitude());
					i.putExtra("longitude", locationClient.getLastLocation().getLongitude());
					startActivity(i);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.d("DEBUG", e.getMessage());
				}
			}
						
			@Override
			public void onFailure(Throwable e, JSONObject error) {
				Log.e("ERROR", e.toString());
				Toast.makeText(SearchActivity.this, "Unable to access Yelp", Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Location loc = locationClient.getLastLocation();
				
		if (loc != null){
			Log.d("DEBUG", "location=" + loc.toString());
		} else {
			// TODO: Handle this better
			Toast.makeText(this, "Unable to access current location", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
	}
	
	public void onHistory(MenuItem mi) {
		Intent i = new Intent(this, HistoryActivity.class);
		startActivity(i);
	}
}



package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.nommable.NommableApp;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class LaunchActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
	
	private LocationClient locationClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_launch);
		
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
		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void launch(View v) {
		Location loc = locationClient.getLastLocation();
		
		if (loc == null){
			Toast.makeText(this, "Unable to access current location", Toast.LENGTH_LONG).show();
			return;
		}
		
		NommableApp.getRestClient().explore(loc, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonResponse) {
				try {
					
					ArrayList<Restaurant> restaurants = Restaurant.fromJson(jsonResponse.getJSONObject("response").getJSONArray("groups")
							.getJSONObject(0).getJSONArray("items"));
					
					Intent i = new Intent(LaunchActivity.this, ResultsActivity.class);
					i.putExtra("restaurants", restaurants);
					startActivity(i);
					
				} catch (JSONException e) {
					Log.d("DEBUG", e.getMessage());
				}
			}
		});
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

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

}

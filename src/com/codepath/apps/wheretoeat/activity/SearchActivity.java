package com.codepath.apps.wheretoeat.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.wheretoeat.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

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
        	// direct user to update google play services if no workie
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

	// find 
	public void findRestaurant(View v) {
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Location loc = locationClient.getLastLocation();
		Log.d("DEBUG", "location=" + loc.toString());
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
	}
}

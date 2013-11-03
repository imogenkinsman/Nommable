package com.codepath.apps.wheretoeat.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.wheretoeat.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class LaunchActivity extends Activity {
/**
 * LoginActivity renamed as LauncActivity
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	//button attached to Launch/Intro Screen
	public void launch(View v) {
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
                if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
                	int requestCode = 10;
                	Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
                	dialog.show();
 
        	} else {
			Intent i = new Intent(this, SearchActivity.class);
			startActivity(i);
	}
}

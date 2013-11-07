package com.codepath.apps.wheretoeat.activity;

import java.util.zip.Inflater;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.models.Restaurant;

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
		Intent i = new Intent(this, SearchActivity.class);
		startActivity(i);
	}

}

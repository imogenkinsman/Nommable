package com.codepath.apps.wheretoeat.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.adapters.RestaurantAdapter;
import com.codepath.apps.wheretoeat.models.Restaurant;

public class HistoryActivity extends Activity {
	RestaurantAdapter rAdapter;
	ArrayList<Restaurant> restaurants;
	ListView lvRestaurants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		setupViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}
	
	private void setupViews() {
		restaurants = Restaurant.getHistory();
		rAdapter = new RestaurantAdapter(getApplicationContext(), restaurants);
		lvRestaurants = (ListView) findViewById(R.id.lvRestaurants);
		lvRestaurants.setAdapter(rAdapter);
	}
	
}

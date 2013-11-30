package com.codepath.apps.nommable.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.RestaurantAdapter;
import com.codepath.apps.nommable.models.Restaurant;

@SuppressLint("NewApi")
public class FavoritesActivity extends Activity {
	RestaurantAdapter rAdapter;
	List<Restaurant> restaurants;
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

package com.codepath.apps.wheretoeat.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.models.Restaurant;

public class SearchResultActivity extends Activity {
	
	TextView tvRestName;
	TextView tvRestPhone;
	TextView tvStreetAddress;
	TextView tvCityState;
	ArrayList<Restaurant> restaurants;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		setup();
		setRestaurantToView(restaurants.get(0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}
	
	private void setup() {
		tvRestName = (TextView) findViewById(R.id.tvRestName);
		tvRestPhone = (TextView) findViewById(R.id.tvRestPhone);
		tvStreetAddress = (TextView) findViewById(R.id.tvStreetAddress);
		tvCityState = (TextView) findViewById(R.id.tvCityState);
		
		restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
	}
	
	private void setRestaurantToView(Restaurant rest) {
		tvRestName.setText(rest.getName());
		tvRestPhone.setText(rest.getDisplayPhone());
		tvStreetAddress.setText(rest.getAdress());
		tvCityState.setText(rest.getCity() + ", " + rest.getState());
	}
}

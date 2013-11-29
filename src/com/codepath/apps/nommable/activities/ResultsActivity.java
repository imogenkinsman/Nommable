package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.ResultsPagerAdapter;
import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment.OnRestaurantChangedListener;
import com.codepath.apps.nommable.models.Restaurant;

public class ResultsActivity extends FragmentActivity implements OnRestaurantChangedListener {
	ArrayList<Restaurant> restaurants;	
	ResultsPagerAdapter rpAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		getActionBar().hide();

		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
		rpAdapter = new ResultsPagerAdapter(getSupportFragmentManager(), restaurants);
		pager.setAdapter(rpAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}

	@Override
	public void onRestaurantChanged(Restaurant rest) {
		DetailsFragment details = (DetailsFragment) rpAdapter.getFragment(1);
		details.updateText(rest);
	}
	
}

package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.view.Menu;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.ResultsPagerAdapter;
import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment.OnRestaurantChangedListener;
import com.codepath.apps.nommable.models.Restaurant;

public class ResultsActivity extends SherlockFragmentActivity implements OnRestaurantChangedListener {
	ArrayList<Restaurant> restaurants;	
	ResultsPagerAdapter rpAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
		rpAdapter = new ResultsPagerAdapter(getSupportFragmentManager(), restaurants);
		pager.setAdapter(rpAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	@Override
	public void onRestaurantChanged(Restaurant rest) {
		DetailsFragment details = (DetailsFragment) rpAdapter.getFragment(1);
		details.updateText(rest);
	}
	
}
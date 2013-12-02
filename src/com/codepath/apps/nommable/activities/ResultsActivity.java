package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.ResultsPagerAdapter;
import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment.OnRestaurantChangedListener;
import com.codepath.apps.nommable.fragments.MenuFragment;
import com.codepath.apps.nommable.models.Restaurant;

public class ResultsActivity extends SherlockFragmentActivity implements OnRestaurantChangedListener {
	ArrayList<Restaurant> restaurants;	
	ResultsPagerAdapter rpAdapter;
	Restaurant currentRestaurant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
		currentRestaurant = restaurants.get(0);
		rpAdapter = new ResultsPagerAdapter(getSupportFragmentManager(), restaurants);
		pager.setAdapter(rpAdapter);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 1:
					DetailsFragment details = (DetailsFragment) rpAdapter.getFragment(1);
					details.updateView(currentRestaurant);
					break;
				case 2:
					MenuFragment menu = (MenuFragment) rpAdapter.getFragment(2);
					menu.getMenu(currentRestaurant);
					break;
				}			
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	@Override
	public void onRestaurantChanged(Restaurant rest) {
		currentRestaurant = rest;
	}
	
	public void onFavorites(MenuItem item) {
		Intent i = new Intent(this, FavoritesActivity.class);
		startActivity(i);
	}
	
}
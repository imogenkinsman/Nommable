package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;

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
	ViewPager pager;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		pager = (ViewPager) findViewById(R.id.viewPager);
		restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
		currentRestaurant = restaurants.get(0);
		rpAdapter = new ResultsPagerAdapter(getSupportFragmentManager(), restaurants);
		pager.setAdapter(rpAdapter);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if (position == 1) {
					DetailsFragment details = (DetailsFragment) rpAdapter.getFragment(1);
					DisplayMetrics metrics = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(metrics);
					if (details != null) {
						details.update(metrics, currentRestaurant);
					}
				} else if (position == 2) {
					MenuFragment menu = (MenuFragment) rpAdapter.getFragment(2);
					if (menu != null) {
						menu.getMenu(currentRestaurant);
					}
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && pager.getCurrentItem() != 0) {
			if (pager.getCurrentItem() == 1) {
				pager.setCurrentItem(0);
			} else if (pager.getCurrentItem() == 2) {
				pager.setCurrentItem(1);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	
	public void onFavorites(MenuItem item) {
		Intent i = new Intent(this, FavoritesActivity.class);
		startActivity(i);
	}
	
}
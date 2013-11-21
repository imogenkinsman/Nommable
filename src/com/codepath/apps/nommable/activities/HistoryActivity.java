package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

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
public class HistoryActivity extends Activity {
	RestaurantAdapter rAdapter;
	ArrayList<Restaurant> restaurants;
	ListView lvRestaurants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		setupViews();
		setupTabs();
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
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tab1 = actionBar.newTab().setText("History").setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		});
		Tab tab2 = actionBar.newTab().setText("Favorites").setTabListener(new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		});
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.selectTab(tab1);
	}
	
}

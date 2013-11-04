package com.codepath.apps.wheretoeat.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.models.Restaurant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchResultActivity extends FragmentActivity {
	
	TextView tvRestName;
	TextView tvRestPhone;
	TextView tvStreetAddress;
	TextView tvCityState;
	ArrayList<Restaurant> restaurants;
	GoogleMap map;
	Marker marker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		setup();
		
		// insert smart algorithm here - for now, just random from results
		Random random = new Random();
		setRestaurantToView(restaurants.get(random.nextInt(restaurants.size())));
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
		
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		marker = null;
	}
	
	private void setRestaurantToView(Restaurant rest) {
		tvRestName.setText(rest.getName());
		tvRestPhone.setText(rest.getDisplayPhone());
		tvStreetAddress.setText(rest.getAddress());
		tvCityState.setText(rest.getCity() + ", " + rest.getState());
		
		Geocoder gcd = new Geocoder(this, Locale.ENGLISH);
		
		List<Address> addresses = null;
		try {
			Log.d("DEBUG", rest.getAddress() + ", " + rest.getCity() + ", " + rest.getState());
			addresses = gcd.getFromLocationName(
					rest.getAddress() + ", " + rest.getCity() + ", " + rest.getState(), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (addresses != null && addresses.size() > 0) {
			Log.d("DEBUG", "got address");
			Address returnedAddress = addresses.get(0);
			
			if (marker != null) {
				marker.remove();
			}
			LatLng addressPosition = new LatLng(returnedAddress.getLatitude(), returnedAddress.getLongitude());
			marker = map.addMarker(new MarkerOptions().position(addressPosition).title(rest.getName()));
		}
	}
}

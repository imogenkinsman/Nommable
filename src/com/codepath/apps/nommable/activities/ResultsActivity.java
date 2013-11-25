package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.ResultsPagerAdapter;
import com.codepath.apps.nommable.models.Restaurant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class ResultsActivity extends FragmentActivity {
	TextView tvRestName;
	TextView tvRestPhone;
	TextView tvStreetAddress;
	TextView tvCityState;
	ArrayList<Restaurant> restaurants;
	static Restaurant restaurant = null;

	//GoogleMap map;
	GoogleMap map;
	Marker marker;
	Marker target_marker;
	LatLng addressPosition;
	LatLng mLatLng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		getActionBar().hide();

		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new ResultsPagerAdapter(getSupportFragmentManager()));
		
		setup();
		// insert smart algorithm here - for now, just random from results
//		Random random = new Random();
//		restaurant = restaurants.get(random.nextInt(restaurants.size()));
//		setRestaurantToView(restaurant);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}
//	@SuppressWarnings("unchecked")
	private void setup() {
//		tvRestName = (TextView) findViewById(R.id.tvRestName);
//		tvRestPhone = (TextView) findViewById(R.id.tvRestPhone);
//		tvStreetAddress = (TextView) findViewById(R.id.tvStreetAddress);
//		tvCityState = (TextView) findViewById(R.id.tvCityState);
//
		Bundle b = getIntent().getExtras();
		try {
			restaurants = (ArrayList<Restaurant>) getIntent().getSerializableExtra("restaurants");
//			double curr_latitude = (Double) b.get("latitude");
//			double curr_longitude = (Double) b.get("longitude");
//			mLatLng = new LatLng(curr_latitude, curr_longitude);
//			Log.d("DEBUG", "bundle success"+ curr_latitude + "," + curr_longitude);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ERROR", "Error at bundle");
		}
//		//MAP and current LOCATION SETUP
//		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//		// Do a null check to confirm that we have not already instantiated the map.
//		if (map == null) {
//			Toast.makeText(this, "Google Maps not available",
//					Toast.LENGTH_LONG).show();
//		}
//		map.setMyLocationEnabled(true);
//		map.setInfoWindowAdapter(new InfoWindowAdapter() {
//			@Override
//			public View getInfoContents(Marker m) {
//				View v = getLayoutInflater().inflate(R.layout.windowlayout, null);			
//				TextView r_name = (TextView) v.findViewById(R.id.tvRestaurantName);
//				TextView r_rating = (TextView) v.findViewById(R.id.tvRating);
//				ImageView ivRating = (ImageView) v.findViewById(R.id.ivRatingSmall);
//				TextView miles_data = (TextView) v.findViewById(R.id.tvMilesAway);
//				TextView min_data = (TextView) v.findViewById(R.id.tvMinFar);
//				if (restaurant == null) {
//					Log.i("GetInfoContents", "restaurant is null");
//					return v;
//				}
//				r_name.setText(restaurant.getName());
////				r_rating.setText(" Rating: " + String.valueOf(restaurant.getRating()));
////				ImageLoader.getInstance().displayImage(restaurant.getRatingImageUrl(), ivRating);
////				Log.i("GetInfoContents", "rating image url:"+restaurant.getRatingImageUrl());
////				miles_data.setText(String.valueOf(restaurant.getDistance()/1000));
//				//Log.i("GetInfoContents", String.valueOf(restaurant.getDistance()));
////				min_data.setText(calculateHowLong(restaurant.getDistance()/1000));
//				return v;
//			}
//
//			@Override
//			public View getInfoWindow(Marker arg0) {
//				return null;
//			}
//
//		});
//		marker = null;
	}
//
//	private void setRestaurantToView(Restaurant rest) {
//		rest.setViewed();
//
//		tvRestName.setText(rest.getName());
//		tvRestPhone.setText(rest.getDisplayPhone());
//		tvStreetAddress.setText(rest.getAddress());
//		tvCityState.setText(rest.getCity() + ", " + rest.getState());
//
//		addressPosition = new LatLng(rest.getLatitude(), rest.getLongitude());
//		showRestaurantOnMap(rest);
//		
//	}
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//
//		switch (item.getItemId()) {
//
//		case R.id.menu_setsatellite:
//			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//			break;
//		case R.id.menu_showtraffic:
//			map.setTrafficEnabled(true);
//			break;
//		case R.id.menu_zoomin:
//			map.animateCamera(CameraUpdateFactory.zoomIn());
//			break;
//		case R.id.menu_zoomout:
//			map.animateCamera(CameraUpdateFactory.zoomOut());
//			break;
//		case R.id.menu_lineconnecttwopoints:
//			map.addPolyline(new PolylineOptions()
//			.add(addressPosition, mLatLng).width(5).color(Color.RED));
//			break;
//		case R.id.menu_closefeatures:
//			map.setTrafficEnabled(false);
//			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//		}
//
//		return true;
//	}
//	public void showRestaurantOnMap(Restaurant r) {
//		target_marker = map.addMarker(new MarkerOptions().position(addressPosition)
//				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//		marker = map.addMarker(new MarkerOptions().position(mLatLng)
//				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//		LatLngBounds.Builder builder = new LatLngBounds.Builder();
//		builder.include(addressPosition);
//		builder.include(mLatLng);
//		map.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 25, 25, 0));
//		//I set current location marker invisible otherwise restaurant infos shows for it too
//		marker.setVisible(false);
//	}

//	public void onYelpReview(View v) {
////		String uriString = restaurant.getYelpUrl();
////		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uriString))); 
//	}
//	public void onNavigateMe(View v) {
//		Intent intent = new Intent().setAction(Intent.ACTION_VIEW);
//		String data = String.format("geo:%s,%s", addressPosition.latitude, addressPosition.longitude);
//		intent.setData(Uri.parse(data));
//		startActivity(intent);
//	}
//	public void onTryAgain(View v) {
//		Random random = new Random();
//		restaurant = restaurants.get(random.nextInt(restaurants.size()));
//		setRestaurantToView(restaurant);
//	}
}

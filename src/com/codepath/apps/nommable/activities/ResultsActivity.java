package com.codepath.apps.nommable.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.ResultsPagerAdapter;
import com.codepath.apps.nommable.fragments.MapFragment;
import com.codepath.apps.nommable.models.Restaurant;

public class ResultsActivity extends FragmentActivity {
	ArrayList<Restaurant> restaurants;
	static Restaurant restaurant = null;

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
	
	private void setup() {
//		MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		

	}
	
	/**
	 * Provides a reference to fragments in the viewpager.
	 * 
	 * @param position the position of the fragment in the viewpager
	 * @return a reference to the fragment
	 */
//	public Fragment findFragmentByPosition(int position) {
//	    return getSupportFragmentManager().findFragmentByTag(
//	            "android:switcher:" + pager.getId() + ":" + rpAdapter.getItemId(position));
//	}
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

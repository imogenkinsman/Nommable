package com.codepath.apps.nommable.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements ConnectionCallbacks, OnConnectionFailedListener, OnMarkerClickListener {
	TextView tvRestName;
	TextView tvRestPhone;
	TextView tvStreetAddress;
	TextView tvCityState;
	GoogleMap map;

	private HashMap<Marker, Restaurant> markerToRestaurant = new HashMap<Marker, Restaurant>();
	private Marker selectedMarker;
	private OnRestaurantChangedListener listener;
	
	private static LocationClient locationClient;
	private static View view;
	
	private static final float SELECTED_MARKER_COLOR = BitmapDescriptorFactory.HUE_RED;
	private static final float MARKER_COLOR = BitmapDescriptorFactory.HUE_AZURE;
	
	/**
	 * A common pattern for creating a Fragment with arguments.
	 * Use this for instantiating the fragment in order to set the initial restaurants.
	 * 
	 * @param restaurants
	 * @return
	 */
	
	public static MapFragment newInstance(ArrayList<Restaurant> restaurants) {
		MapFragment mapFrag = new MapFragment();
		Bundle args = new Bundle();
		args.putSerializable("restaurants", restaurants);
		mapFrag.setArguments(args);
		return mapFrag;
	}
	
	/**
	 * An activity that MapFragment attaches to MUST implement this as a means of notifying the activity as to which
	 * restaurant is currently selected.
	 */
	public interface OnRestaurantChangedListener {
		public void onRestaurantChanged(Restaurant rest);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnRestaurantChangedListener) {
			listener = (OnRestaurantChangedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement OnRestaurantChangedListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		locationClient = new LocationClient(getActivity(), this, this);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.map, menu);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}
		try {
			view = inflater.inflate(R.layout.fragment_map, container, false);
		} catch (InflateException e) {
			/* map is already there, just return view as it is */
		}
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setup();
	}
	
	private void setup() {
		tvRestName = (TextView) getActivity().findViewById(R.id.tvRestName);
		tvRestPhone = (TextView) getActivity().findViewById(R.id.tvRestPhone);
		tvStreetAddress = (TextView) getActivity().findViewById(R.id.tvStreetAddress);
		tvCityState = (TextView) getActivity().findViewById(R.id.tvCityState);
		
		map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		map.setOnMarkerClickListener(this);
		updateRestaurants((ArrayList<Restaurant>) getArguments().getSerializable("restaurants"));
	}
	
	/**
	 * Wipe all currently displayed restaurants and update it with new restaurants.
	 * Use this after getting a new response from FourSquare.
	 * 
	 * @param restaurants the new ArrayList of restaurants
	 */
	
	public void updateRestaurants(ArrayList<Restaurant> restaurants) {
		if (restaurants == null || restaurants.size() == 0){
			Log.e("ERROR", "attempted to update restaurants with empty or null arraylist");
			return;
		}
				
		map.clear();
		markerToRestaurant.clear();
		
		// set our initial "selected" restaurant
		updateRestarauntText(restaurants.get(0));
		selectedMarker = addRestaurantToMap(restaurants.get(0), SELECTED_MARKER_COLOR);
		markerToRestaurant.put(selectedMarker, restaurants.get(0));
		
		for (int i = 1; i < restaurants.size(); i++){
			Marker marker = addRestaurantToMap(restaurants.get(i), MARKER_COLOR);
			markerToRestaurant.put(marker, restaurants.get(i));
		}
	}
	
	/**
	 * Add a single restaurant to the map.
	 * 
	 * @param restaurant
	 */
	
	private Marker addRestaurantToMap(Restaurant rest, float color) {			
		return map.addMarker(new MarkerOptions()
			.position(new LatLng(rest.getLatitude(), rest.getLongitude()))
			.icon(BitmapDescriptorFactory.defaultMarker(color))
		);		
	}
	
	/**
	 * Update TextViews with chosen restaurant's information.
	 * 
	 * @param restaurant
	 */
	
	private void updateRestarauntText(Restaurant rest) {
		tvRestName.setText(rest.getName());
		tvRestPhone.setText(rest.getDisplayPhone());
		tvStreetAddress.setText(rest.getAddress());
		tvCityState.setText(rest.getCity() + ", " + rest.getState());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
        locationClient.connect();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
        locationClient.disconnect();
	}
	
	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Location curLocation = locationClient.getLastLocation();
		LatLng curLatLng = new LatLng(curLocation.getLatitude(), curLocation.getLongitude());		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(curLatLng, 13.5f));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.menu_setsatellite:
			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.menu_showtraffic:
			map.setTrafficEnabled(true);
			break;
		}
		
		return true;
	}

	@Override
	public void onDisconnected() {
	}
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		if (!marker.equals(selectedMarker)) {
			selectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(MARKER_COLOR));
			marker.setIcon(BitmapDescriptorFactory.defaultMarker(SELECTED_MARKER_COLOR));
			selectedMarker = marker;
			
			Restaurant rest = markerToRestaurant.get(selectedMarker);
			updateRestarauntText(rest);
			listener.onRestaurantChanged(rest);
		}
		return false;
	}
	
}
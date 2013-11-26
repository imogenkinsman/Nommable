package com.codepath.apps.nommable.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {	
	TextView tvRestName;
	TextView tvRestPhone;
	TextView tvStreetAddress;
	TextView tvCityState;
	ArrayList<Restaurant> restaurants;
	GoogleMap map;
	
	private static View view;
	
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
	}
	
	/**
	 * Wipe all currently displayed restaurants and update it with new restaurants.
	 * Use this after getting a new response from FourSquare.
	 * 
	 * @param restaurants the new ArrayList of restaurants
	 */
	
	public void updateRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
		
		// set our initial "selected" restaurant
		
	}
	
	/**
	 * Add a single restaurant to the map.
	 * 
	 * @param restaurant
	 */
	
	private void addRestaurantToMap(Restaurant rest) {
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
	
}
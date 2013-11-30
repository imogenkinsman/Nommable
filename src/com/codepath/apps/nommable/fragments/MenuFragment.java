package com.codepath.apps.nommable.fragments;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.apps.nommable.NommableApp;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MenuFragment extends Fragment {
	Restaurant currentRestaurant;
	TextView tvMenuName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_menu, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tvMenuName = (TextView) getActivity().findViewById(R.id.tvMenuName);
	}
	
	public void update(final Restaurant rest) {
		// don't spam unnecessary requests if we don't need to
		if (rest != currentRestaurant){
			tvMenuName.setText(rest.getName());
			Log.d("DEBUG", tvMenuName.getText().toString());
			
			NommableApp.getRestClient().getMenu(rest.getFourSquareId(), new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject jsonResponse) {
					currentRestaurant = rest;
					Log.d("DEBUG", jsonResponse.toString());
				}
			});
		}
	}
}

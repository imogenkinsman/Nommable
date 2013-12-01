package com.codepath.apps.nommable.fragments;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.apps.nommable.NommableApp;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Menu;
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
	
	/**
	 * Grabs the menu for a restaurant from FourSquare and updates the view accordingly.
	 * 
	 * @param rest The currently selected restaurant
	 */
	public void getMenu(final Restaurant rest) {
		// don't spam unnecessary requests
		if (rest != currentRestaurant){
			tvMenuName.setText(rest.getName());
			
			NommableApp.getRestClient().getMenu(rest.getFourSquareId(), new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject jsonResponse) {
					
					try {
						ArrayList<Menu> menus = Menu.fromJson(jsonResponse.getJSONObject("response")
								.getJSONObject("menu").getJSONObject("menus").getJSONArray("items"));
						updateView(menus);
						currentRestaurant = rest;
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			});
		}
	}
	
	/**
	 * Updates the ListView to display menu information.
	 * 
	 * @param menus
	 */
	private void updateView(ArrayList<Menu> menus) {
		
	}
}

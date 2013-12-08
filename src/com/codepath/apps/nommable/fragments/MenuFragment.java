package com.codepath.apps.nommable.fragments;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.codepath.apps.nommable.NommableApp;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.adapters.MenuAdapter;
import com.codepath.apps.nommable.models.Menu;
import com.codepath.apps.nommable.models.MenuRow;
import com.codepath.apps.nommable.models.Restaurant;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MenuFragment extends Fragment {
	Restaurant currentRestaurant;
	MenuAdapter mAdapter;
	ListView lvMenu;
	Menu menu;
	TextView tvAttribution;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_menu, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lvMenu = (ListView) getActivity().findViewById(R.id.lvMenu);
		
		// Footer needs to be added before setting adapter in order to be compatible with order API
		View footer = View.inflate(getActivity(), R.layout.menu_footer, null);
		tvAttribution = (TextView) footer.findViewById(R.id.tvAttribution);
		lvMenu.addFooterView(footer, null, true);
		
		mAdapter = new MenuAdapter(getActivity(), new ArrayList<MenuRow>());
		lvMenu.setAdapter(mAdapter);
		if (savedInstanceState != null && savedInstanceState.containsKey("menu")){
			menu = (Menu) savedInstanceState.getSerializable("menu");
			updateView(menu);
		}
	}
	
	/**
	 * Grabs the menu for a restaurant from FourSquare and updates the view accordingly.
	 * 
	 * @param rest The currently selected restaurant
	 */
	public void getMenu(final Restaurant rest) {
		// don't spam unnecessary requests
		if (rest != currentRestaurant){
			
			NommableApp.getRestClient().getMenu(rest.getFourSquareId(), new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject jsonResponse) {
					
					try {
						menu = Menu.fromJson(jsonResponse.getJSONObject("response")
								.getJSONObject("menu"));
						updateView(menu);
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
	private void updateView(Menu menu) {
		mAdapter.clear();
		mAdapter.addAll(menu.getMenuRows());
		
		tvAttribution.setText(menu.getAttributionText());
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable("menu", menu);
	}
	
}

package com.codepath.apps.nommable.adapters;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment;
import com.codepath.apps.nommable.fragments.MenuFragment;
import com.codepath.apps.nommable.models.Restaurant;

public class ResultsPagerAdapter extends FragmentStatePagerAdapter {
	
	private static final String[] titles = {"Map", "Details", "Menu"};
	
	SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
	ArrayList<Restaurant> restaurants;

	public ResultsPagerAdapter(FragmentManager fm, ArrayList<Restaurant> restaurants) {
		super(fm);
		
		this.restaurants = restaurants;
	}

	@Override
	public Fragment getItem(int pos) {
		switch(pos) {
			case 0: return MapFragment.newInstance(restaurants);
			case 1: return new DetailsFragment();
			case 2: return new MenuFragment();
			default: return new DetailsFragment();
		}
	}

	@Override
	public int getCount() {
		return 3;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = (Fragment) super.instantiateItem(container, position);
		registeredFragments.put(position, fragment);
		Log.d("DEBUG", "calling instantiateItem");
		return fragment;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		registeredFragments.remove(position);
		Log.d("DEBUG", "destroyItem");
		super.destroyItem(container, position, object);
	}
	
	public Fragment getRegisteredFragment(int position) {
		return registeredFragments.get(position);
	}

}

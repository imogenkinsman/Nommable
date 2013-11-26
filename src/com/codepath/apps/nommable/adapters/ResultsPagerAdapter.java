package com.codepath.apps.nommable.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment;
import com.codepath.apps.nommable.fragments.MenuFragment;

public class ResultsPagerAdapter extends FragmentStatePagerAdapter {
	
	private static final String[] titles = {"Map", "Details", "Menu"};

	public ResultsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int pos) {
		switch(pos) {
			case 0: return new MapFragment();
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

}

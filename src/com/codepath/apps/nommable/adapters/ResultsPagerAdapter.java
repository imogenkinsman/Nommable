package com.codepath.apps.nommable.adapters;

import com.codepath.apps.nommable.fragments.DetailsFragment;
import com.codepath.apps.nommable.fragments.MapFragment;
import com.codepath.apps.nommable.fragments.MenuFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ResultsPagerAdapter extends FragmentPagerAdapter {

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

}

package com.codepath.apps.nommable.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;

public class DetailsFragment extends Fragment {

	TextView tvDetailsName;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_details, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tvDetailsName = (TextView) getActivity().findViewById(R.id.tvDetailsName);
	}
	
	public void updateView(Restaurant rest) {
		tvDetailsName.setText(rest.getName());
	}
	
}
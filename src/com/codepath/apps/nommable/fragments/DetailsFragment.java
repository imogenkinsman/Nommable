package com.codepath.apps.nommable.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.nommable.NommableApp;
import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.Restaurant;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailsFragment extends Fragment {

	TextView tvDetailsName;
	ImageView ivPhoto;
	Restaurant currentRest;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_details, container, false);
		
		// TODO: implement OnClickListener with the fragment and move all code to an onClick override once this gets messy
		Button btnFavorite = (Button) v.findViewById(R.id.btnFavorite);
		btnFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleFavorite();
			}
		});
		
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tvDetailsName = (TextView) getActivity().findViewById(R.id.tvDetailsName);
		ivPhoto = (ImageView) getActivity().findViewById(R.id.ivPhoto);
	}
	
	public void update(final DisplayMetrics metrics, Restaurant rest) {
		currentRest = rest;
		tvDetailsName.setText(rest.getName());
		
		NommableApp.getRestClient().getPhoto(rest.getFourSquareId(), new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				try {
					JSONObject items = json.getJSONObject("response").getJSONObject("photos").getJSONArray("items").getJSONObject(0);
					String imgUrl = items.getString("prefix") + metrics.widthPixels + "x" + (metrics.heightPixels / 4) + items.getString("suffix");
			        ImageLoader.getInstance().displayImage(imgUrl, ivPhoto);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void toggleFavorite() {
		Restaurant.isSaved(currentRest);
	}
	
}
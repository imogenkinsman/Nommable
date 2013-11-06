package com.codepath.apps.wheretoeat.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.wheretoeat.R;
import com.codepath.apps.wheretoeat.models.Restaurant;
import com.nostra13.universalimageloader.core.ImageLoader;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

	public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
		super(context, 0, restaurants);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.restaurant_item, null);
        }
        
        final Restaurant restaurant = getItem(position);
        
        ImageView ivRestaurant = (ImageView) view.findViewById(R.id.ivRestaurant);
        ImageLoader.getInstance().displayImage(restaurant.getImageUrl(), ivRestaurant);
        
        TextView tvRestName = (TextView) view.findViewById(R.id.tvRestName);
        tvRestName.setText(restaurant.getName());
        
        TextView tvRestAddress = (TextView) view.findViewById(R.id.tvRestAddress);
        tvRestAddress.setText(restaurant.getFullAddress());
        
        TextView tvRestPhone = (TextView) view.findViewById(R.id.tvRestPhone);
        tvRestPhone.setText(restaurant.getDisplayPhone());
        
        return view;
	}
}

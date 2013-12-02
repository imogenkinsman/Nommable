package com.codepath.apps.nommable.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.MenuEntry;

public class MenuAdapter extends ArrayAdapter<MenuEntry> {

	public MenuAdapter(Context context, List<MenuEntry> entries) {
		super(context, 0, entries);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_entry, null);
		}
		
		final MenuEntry entry = getItem(position);
		
		TextView tvName = (TextView) view.findViewById(R.id.tvName);
		tvName.setText(entry.getName());
		
		return view;
	}
}

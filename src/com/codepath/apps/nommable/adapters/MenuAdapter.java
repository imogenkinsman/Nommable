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
import com.codepath.apps.nommable.models.MenuEntry.Type;

public class MenuAdapter extends ArrayAdapter<MenuEntry> {

	public MenuAdapter(Context context, List<MenuEntry> entries) {
		super(context, 0, entries);
	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}
	
	@Override
	public int getItemViewType(int position) {
		Type type = (Type) getItem(position).getType();
				
		switch (type) {
		case MENU:
			return 0;
		case SECTION:
			return 1;
		case ENTRY:
			return 2;
		default:
			return 0;
		}
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			int type =  getItemViewType(position);
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			final MenuEntry entry = getItem(position);
			TextView tvName;
			
			switch (type) {
			case 0:
				view = inflater.inflate(R.layout.item_entry, null);
				tvName = (TextView) view.findViewById(R.id.tvName);
				tvName.setText(entry.getName());
				break;
			case 1:
				view = inflater.inflate(R.layout.item_category, null);
				TextView tvCategory = (TextView) view.findViewById(R.id.tvCategory);
				tvCategory.setText(entry.getName());
				break;
			case 2:
				view = inflater.inflate(R.layout.item_entry, null);
				tvName = (TextView) view.findViewById(R.id.tvName);
				tvName.setText(entry.getName());
				TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
				tvDescription.setText(entry.getDescription());
				TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
				tvPrice.setText(entry.getPrice());
				break;
			default:
				view = inflater.inflate(R.layout.item_entry, null);
				tvName = (TextView) view.findViewById(R.id.tvName);
				tvName.setText(entry.getName());
				break;
			}			
		}		
		return view;
	}
}

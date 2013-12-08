package com.codepath.apps.nommable.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.models.MenuRow;
import com.codepath.apps.nommable.models.MenuRow.Type;

public class MenuAdapter extends ArrayAdapter<MenuRow> {

	private LayoutInflater inflater;
	
	public MenuAdapter(Context context, List<MenuRow> entries) {
		super(context, 0, entries);
		inflater = LayoutInflater.from(context);
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
	
	//TODO: create a viewholder class to improve view creation speed
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		int type =  getItemViewType(position);

		if (view == null) {
			switch (type) {
			case 0:
				view = inflater.inflate(R.layout.item_menu_type, null);
				break;
			case 1:
				view = inflater.inflate(R.layout.item_category, null);
				break;
			case 2:
				view = inflater.inflate(R.layout.item_entry, null);
				break;
			default:
				view = inflater.inflate(R.layout.item_entry, null);
				break;
			}
		}
		MenuRow row = getItem(position);
		if (row != null) {
			if (type == 0) {
				TextView tvName = (TextView) view.findViewById(R.id.tvName);
				tvName.setText(row.getName());
			} else if (type == 1) {
				TextView tvCategory = (TextView) view.findViewById(R.id.tvCategory);
				tvCategory.setText(row.getName());
			} else if (type == 2) {
				TextView tvName = (TextView) view.findViewById(R.id.tvName);
				tvName.setText(row.getName());
				TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
				tvPrice.setText(row.getPrice());
				TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
				if (row.getDescription() == null) {
					tvDescription.setVisibility(View.GONE);
				} else {
					tvDescription.setVisibility(View.VISIBLE);
					tvDescription.setText(row.getDescription());
				}
			}

		}
		return view;
	}
}

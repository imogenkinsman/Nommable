package com.codepath.apps.nommable.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Menu implements Serializable {
	
	private String attributionImage;
	private String attributionLink;
	private String attributionText;
	private ArrayList<MenuRow> menuRows;
	
	public String getAttributionImage() {
		return attributionImage;
	}

	public String getAttributionLink() {
		return attributionLink;
	}

	public String getAttributionText() {
		return attributionText;
	}

	public ArrayList<MenuRow> getMenuRows() {
		return menuRows;
	}

	public static Menu fromJson(JSONObject jsonObject) {
		Menu menu = new Menu();
	
		try {
			JSONObject provider = jsonObject.getJSONObject("provider");
			menu.attributionImage = provider.getString("attributionImage");
			menu.attributionLink = provider.getString("attributionLink");
			menu.attributionText = provider.getString("attributionText");
			menu.menuRows = MenuRow.fromJsonArray(jsonObject.getJSONObject("menus").getJSONArray("items"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return menu;
	}
}

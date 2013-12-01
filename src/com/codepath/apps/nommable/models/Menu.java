package com.codepath.apps.nommable.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu {

	private String name;
	private String description;
	private ArrayList<Category> categories = new ArrayList<Category>();
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	public static Menu fromJson(JSONObject jsonObject) {
		Menu m = new Menu();
		JSONArray json;
		
		try {
			m.name = jsonObject.getString("name");
			m.description = jsonObject.getString("description");
			json = jsonObject.getJSONObject("entries").getJSONArray("items");
			m.categories = Category.fromJson(json);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return m;
	}
	
	public static ArrayList<Menu> fromJson(JSONArray jsonArray) {
		ArrayList<Menu> menus = new ArrayList<Menu>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = null;
			try {
				json = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			Menu menu = Menu.fromJson(json);
			if (menu != null) {
				menus.add(menu);
			}
		}
		return menus;
	}
	
}

package com.codepath.apps.nommable.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Category {

	private String name;
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Entry> getEntries() {
		return entries;
	}
	
	public static Category fromJson(JSONObject jsonObject) {
		Category c = new Category();
		JSONArray json;
		
		try {
			c.name = jsonObject.getString("name");
			json = jsonObject.getJSONObject("entries").getJSONArray("items");
			c.entries = Entry.fromJson(json);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return c;
	}
	
	public static ArrayList<Category> fromJson(JSONArray jsonArray) {
		ArrayList<Category> categories = new ArrayList<Category>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = null;
			try {
				json = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			Category cat = Category.fromJson(json);
			if (cat != null) {
				categories.add(cat);
			}
		}
		return categories;
	}
	
}

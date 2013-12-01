package com.codepath.apps.nommable.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// TODO: add support for multiple prices, options, and additions
// TODO: use something like jackson json parsing to cut down on class code
public class Entry {

	private String name;
	private String description;
	private String price;
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPrice() {
		return price;
	}
	
	public static Entry fromJson(JSONObject jsonObject) {
		Entry entry = new Entry();
		
		try {
			entry.name = jsonObject.getString("name");
			entry.description = jsonObject.getString("description");
			entry.price = jsonObject.getString("price");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return entry;
	}
	
	public static ArrayList<Entry> fromJson(JSONArray jsonArray) {
		ArrayList<Entry> entries = new ArrayList<Entry>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = null;
			try {
				json = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			Entry entry = Entry.fromJson(json);
			if (entry != null) {
				entries.add(entry);
			}
		}
		return entries;
	}
}

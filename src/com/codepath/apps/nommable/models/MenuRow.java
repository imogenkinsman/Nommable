package com.codepath.apps.nommable.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuRow implements Serializable {

	private static final long serialVersionUID = 1849235577855101819L;
	
	private String name;
	private String price;
	private String description;
	private Type type;
	
	public enum Type {
		MENU,
		SECTION,
		ENTRY
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Type getType() {
		return type;
	}

	public static ArrayList<MenuRow> fromJsonArray(JSONArray jsonArray) {
		return fromJsonArray(jsonArray, new ArrayList<MenuRow>(), 0);
	}
	
	private static ArrayList<MenuRow> fromJsonArray(JSONArray jsonArray, ArrayList<MenuRow> rows, int type) {
		final Type[] typeArray = {Type.MENU, Type.SECTION, Type.ENTRY};
		
		for (int i = 0; i < jsonArray.length(); i++) {
						
			try {
				JSONObject json = jsonArray.getJSONObject(i);
				
				MenuRow row = fromJsonObject(json, typeArray[type]);
				
				if (row != null) {
					rows.add(row);
				}
			
				if (type < (typeArray.length - 1)) {
					fromJsonArray(json.getJSONObject("entries").getJSONArray("items"), rows, type + 1);
				}				
			} catch (JSONException e) {
				e.printStackTrace();
				return rows;
			}
		}
		
		return rows;
	}
	
	private static MenuRow fromJsonObject(JSONObject json, Type type) {
		MenuRow m = new MenuRow();
		
		try {
			m.type = type;
			m.name = json.getString("name");
			
			if (json.has("price")) {
				m.price = json.getString("price");
			}
			if (json.has("description")) {
				m.description = json.getString("description");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		return m;
	}
}
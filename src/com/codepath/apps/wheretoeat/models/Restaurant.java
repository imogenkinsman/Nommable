package com.codepath.apps.wheretoeat.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name = "Restaurant")
public class Restaurant extends Model{

	@Column(name="name")
	private String name;
	@Column(name="business_id")
	private String id;
	@Column(name="address1")
	private String address1;
	@Column(name="address2")
	private String address2;
	@Column(name="address3")
	private String address3;
	@Column(name="latitute")
	private double latitute;
	@Column(name="longitude")
	private double longitude;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="zip")
	private String zip;
	@Column(name="display_phone")
	private String display_phone;
	@Column(name="categories")
	private String categories;
	@Column(name="is_closed")
	private boolean is_closed;
	@Column(name="distance")
	private long distance;
	@Column(name="rating")
	private double rating;
	@Column(name="review_count")
	private int review_count;
	@Column(name="image_url")
	private String image_url;
	@Column(name="url")
	private String yelp_url;

	public Restaurant() {
		super();
	}
	public String getName() {
		return name;
	}
	public String getYelpId() {
		return id;
	}
	public String getAdress() {
		return address1 + address2 + address3;
	}
	public String getState() {
		return state;
	}
	public String getCity() {
		return city;
	}
	public boolean isClosed() {
		return is_closed;
	}
	public long getDistance() {
		return distance;
	}
	public String getDisplayPhone() {
		return display_phone;
	}
	public double getRating() {
		return rating;
	}
	public double getLatitude() {
		return latitute;
	}
	public double getLongitude() {
		return longitude;
	}
	public String getImageUrl() {
		return image_url;
	}
	public String getYelpUrl() {
		return yelp_url;
	}
	
	public static Restaurant fromJson(JSONObject jsonObject) {
		Restaurant r = new Restaurant();
		// Deserialize json into object fields
		try {
			r.id = jsonObject.getString("id");
			r.name = jsonObject.getString("name");
			r.display_phone = jsonObject.getString("display_phone");
			r.image_url = jsonObject.getString("image_url");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		// Return new object
		return r;
	}
	// Decodes array of retaurant json results into restaurant model objects
	public static ArrayList<Restaurant> fromJson(JSONArray jsonArray) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>(jsonArray.length());
		// Process each result in json array, decode and convert to restaurant object
		for (int i=0; i < jsonArray.length(); i++) {
			JSONObject json = null;
			try {
				json = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Restaurant restaurant = Restaurant.fromJson(json);
			if (restaurant != null) {
				restaurants.add(restaurant);
			}
		}
		return restaurants;
	}
}

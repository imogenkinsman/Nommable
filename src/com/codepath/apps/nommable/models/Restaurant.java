package com.codepath.apps.nommable.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table (name = "Restaurant")
public class Restaurant extends Model implements Serializable {


	private static final long serialVersionUID = -7256102743331094491L;
	@Column(name = "name")
	private String name;
	@Column(name = "business_id")
	private String id;
	@Column(name="address")
	private String address;
	@Column(name = "latitute")
	private double latitute;
	@Column(name = "longitude")
	private double longitude;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "zip")
	private String zip;
	@Column(name = "formattedphone")
	private String formattedphone;
	@Column(name = "categories")
	private String categories;
	@Column(name = "image_url")
	private String image_url;
	@Column(name = "viewedAt")
	private Long viewedAt;

	/**
	 * ActiveAndroid requires you to use the superclass constructor.
	 */
	public Restaurant() {
		super();
	}
	public String getName() {
		return name;
	}
	public String getYelpId() {
		return id;
	}
	public String getAddress() {
		return address;
	}	
	public String getState() {
		return state;
	}
	public String getCity() {
		return city;
	}
	public String getDisplayPhone() {
		return formattedphone;
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
	
	public String getFullAddress() {
		return address + ", " + city + ", " + state;
	}
	
	public void setViewed() {
		this.viewedAt = System.currentTimeMillis();
		this.save();
	}
	
	/**
	 * Maps JSON response to a Restaurant object
	 * @param jsonObject
	 * @return
	 */
	public static Restaurant fromJson(JSONObject jsonObject) {
		Restaurant r = new Restaurant();
		try {
			
			JSONObject venue = jsonObject.getJSONObject("venue");
			JSONObject location = venue.getJSONObject("location");
			JSONObject photo = venue.getJSONObject("photos").getJSONArray("groups").getJSONObject(0).getJSONArray("items").getJSONObject(0);
			
			r.id = venue.getString("id");
			r.name = venue.getString("name");
			r.formattedphone = venue.getJSONObject("contact").getString("formattedPhone");
			r.image_url = photo.getString("prefix") + photo.getInt("width") + "x" + photo.getInt("height") + photo.getString("suffix");
			r.address = location.getString("address");
			r.state = location.getString("state");
			r.city = location.getString("city");
			r.latitute = location.getDouble("lat");
			r.longitude = location.getDouble("lng");
			r.zip = location.getString("postalCode");
//			r.userRating = -1; // sentinel value for "not set yet"
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public static List<Restaurant> getHistory() {
		return new Select().from(Restaurant.class).orderBy("viewedAt DESC").limit("30").execute();
	}
	
	public static Restaurant getMostRecentViewed() {
		return new Select().from(Restaurant.class).orderBy("viewedAt DESC").where("userRating").limit("1").executeSingle();
	}
}

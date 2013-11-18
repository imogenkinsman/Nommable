package com.codepath.apps.nommable.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
@Table (name = "User")
public class User extends Model {
	@Column(name="user_id")
	private long userId;
	@Column(name="user_name")
    private String name;
	@Column(name="fav_restaurant")
    private Restaurant fav_restaurant;
	@Column(name="user_history")
    private Restaurant user_history;
 
	public User() {
		super();
	}
	public String getUserName() {
		return name;
	}
	public long getUserId() {
		return userId;
	}
	public void setFavRestaurant(Restaurant r) {
		if(r != null) {
			this.fav_restaurant = r;
			fav_restaurant.save();
		}
	}
	public List<Restaurant> getAllFavRestaurants(User user) {
		return new Select().from(User.class).where("fav_restaurant = ?", user.getFavRestaurant())
				.orderBy("Name ASC")
				.execute();
	}
	private Restaurant getFavRestaurant() {
		return fav_restaurant;
	}
	
	public void saveUserHistory(Restaurant r){
		if( r != null) {
			this.user_history = r;
			user_history.save();
		}
	}
	public List<Restaurant> getLast30UserHistory(User user) {
		return new Select().from(User.class).where("user_history = ?" , user.getUserHistory())
				.limit("30")
				.execute();
		
	}
	private Restaurant getUserHistory() {
		return user_history;
	}
}
	
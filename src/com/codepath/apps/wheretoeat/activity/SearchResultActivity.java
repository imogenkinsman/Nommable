package com.codepath.apps.wheretoeat.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.codepath.apps.wheretoeat.R;

public class SearchResultActivity extends Activity {
	
//ListView lvResult;
//ArrayAdapter<String> res_adap;
//ArrayList<Restaurant> res_list;
//LocationClient location;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		//lvResult = (ListView) findViewById(R.id.lvResult);
		//String curr_loc = location.getLastLocation().toString();
		//getSearchResult(curr_loc);
		
	}
	/**
	public void getSearchResult(String curr_loc) {
		YelpClient client = YelpApp.getRestClient();
		//http://api.yelp.com/v2/search?term=food&ll=37.788022,-122.399797
		client.search("steak", curr_loc , new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray jsonRestaurants) {
				res_list.addAll(Restaurant.fromJson(jsonRestaurants));
				res_adap.notifyDataSetChanged();
				lvResult.setAdapter(res_adap);
			}
			@Override
			public void onFailure(Throwable e) {
				e.printStackTrace();
				Toast.makeText(getBaseContext(), "FAIL", Toast.LENGTH_LONG).show();
			}
		});
		
	}
  */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result, menu);
		return true;
	}
}

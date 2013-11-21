package com.codepath.apps.nommable.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.nommable.R;
import com.codepath.apps.nommable.network.YelpClient;
import com.codepath.oauth.OAuthLoginActivity;

/**
 * Interesting thing about the Yelp OAuth process - they don't do login, at all. Both the Token and the Token Secret are
 * provided when you register for API access, so the login step is unnecessary. I'm gutting them for now, and eventually we may want to
 * call this something other than "LoginActivity" (probably just start with our search activity).
 */

public class LoginActivity extends OAuthLoginActivity<YelpClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
    @Override
    public void onLoginSuccess() {
    	Intent i = new Intent(this, SearchActivity.class);
    	startActivity(i);
    }
    
    // OAuth authentication flow failed, handle the error
    // i.e Display an error dialog or toast
    @Override
    public void onLoginFailure(Exception e) {
        e.printStackTrace();
    }
    
    // Click handler method for the button used to start OAuth flow
    // Uses the client to initiate OAuth authorization
    // This should be tied to a button used to login
    public void loginToRest(View view) {
    	getClient().connect();
    }

}

package com.cg.sjb_map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class YourRatedRoadsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your_rated_roads);
		
		getRatings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.your_rated_roads, menu);
		return true;
	}
	
	public void getRatings() {
		new GetRatingHTTP().execute();
	}

}

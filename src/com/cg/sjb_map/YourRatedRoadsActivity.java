package com.cg.sjb_map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class YourRatedRoadsActivity extends Activity {

	String rating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your_rated_roads);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.your_rated_roads, menu);
		return true;
	}
	
	public void getRatings() {
		GetRatingHTTP g = new GetRatingHTTP();
		g.execute();
		rating = g.getRating();	
	}

	@Override
    protected void onStart() {
        super.onStart();
        
        getRatings();
		
		TextView t = (TextView) findViewById(R.id.yourRatedRoadsText);
        
    }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		getRatings();
		
		TextView t = (TextView) findViewById(R.id.yourRatedRoadsText);
		
		t.append(" " + rating);
	}
	
}

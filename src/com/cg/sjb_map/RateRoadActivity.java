package com.cg.sjb_map;

import java.util.ArrayList;

import com.cg.sjb_map.sliding_menu_model.RateRoadItem;
import com.cg.sjb_map_sliding_menu_adapter.RateRoadListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RateRoadActivity extends Activity implements android.view.View.OnClickListener{

	private String[] rateOptions;
	private ArrayList<RateRoadItem> RateRoadItems;
	private RateRoadListAdapter adapter;
	
	Button submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_road);
		
		submit = (Button) findViewById(R.id.submit);

	    // set a listener
	    submit.setOnClickListener(this);
		
		rateOptions = getResources().getStringArray(R.array.rate_road_items);
		
		ListView listview = (ListView) findViewById(R.id.rateOptions);
	    
		
		RateRoadItems = new ArrayList<RateRoadItem>();
	    
        // adding nav drawer items to array
        // Material
        RateRoadItems.add(new RateRoadItem(false, rateOptions[0], 0));
        // Number of Bumps
        RateRoadItems.add(new RateRoadItem(false, rateOptions[1], 0));
        // Traffic Signs
        RateRoadItems.add(new RateRoadItem(false, rateOptions[2], 0));
        // Markers
        RateRoadItems.add(new RateRoadItem(false, rateOptions[3], 0));
        
        // setting the nav drawer list adapter
        adapter = new RateRoadListAdapter(getApplicationContext(),
                RateRoadItems);
        
        listview.setAdapter(adapter);
		
	}
	
	@Override
	  public void onClick(View v) {
	     switch (v.getId()) {
	    
	    	case R.id.submit:
	    		float finalRating = 0;
	    		for (int i=0; i<4; i++) {
	    			finalRating += RateRoadItems.get(i).getRating() * (4-i);
	    		}
	    		
	    		finalRating = finalRating/10;
	    		
	    		TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
	    		String imei = mngr.getDeviceId();
	    		
	    		Toast.makeText(this, "Am apasat " + finalRating + " " + imei, Toast.LENGTH_LONG).show();
	    		//sendPointsToServer();
	    		new SendRatingHTTP().execute(finalRating+"", imei);
	    	default:	
	    }
	  }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate_road, menu);
		return true;
	}
}

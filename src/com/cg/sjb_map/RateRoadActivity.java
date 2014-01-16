package com.cg.sjb_map;

import java.util.ArrayList;

import com.cg.sjb_map.sliding_menu_model.RateRoadItem;
import com.cg.sjb_map_sliding_menu_adapter.RateRoadListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RateRoadActivity extends Activity {

	private String[] rateOptions;
	private ArrayList<RateRoadItem> RateRoadItems;
	private RateRoadListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_road);
		
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
	    
	    
        listview.setOnItemClickListener(new SlideMenuClickListener());
        
        // setting the nav drawer list adapter
        adapter = new RateRoadListAdapter(getApplicationContext(),
                RateRoadItems);
        
        //aici e problema!!!!!!!!!!
        listview.setAdapter(adapter);
		
	}
	
    private class SlideMenuClickListener implements
    	ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
		        long id) {
		    // display view for selected nav drawer item
		    displayView(position);
		}
    }
    
    private void displayView(int position) {
        // update the main content by replacing fragments
        switch (position) {
        case 0:
        	
        	Intent intent = new Intent(this, FindRoadActivity.class);
        	startActivity(intent);
        	
        	break;
        case 1:
            
            Intent intent1 = new Intent(this, RateRoadActivity.class);
        	startActivity(intent1);
            
            break;
        /*case 2:
            fragment = new PhotosFragment();
            break;
        case 3:
            fragment = new CommunityFragment();
            break;*/
 
        default:
            break;
        }
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate_road, menu);
		return true;
	}

}

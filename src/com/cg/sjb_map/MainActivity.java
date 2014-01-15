package com.cg.sjb_map;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cg.sjb_map.sliding_menu_model.NavDrawerItem;
import com.cg.sjb_map_sliding_menu_adapter.NavDrawerListAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
													  GooglePlayServicesClient.OnConnectionFailedListener{

	// Google Map
    private GoogleMap googleMap;
    LocationClient mLocationClient;
    
    //for the sliding menu
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates",
                    "Google Play services is available.");
        }
        else
        	Toast.makeText(this, "Google Play services is not available.", Toast.LENGTH_LONG).show();
		
		mLocationClient = new LocationClient(this, this, this);
		
		//adding elements to the sliding menu list
		mTitle = mDrawerTitle = getTitle();
		 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Find Road
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Rate Road
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Your Rated Roads
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Options
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
         
 
        // Recycle the typed array
        navMenuIcons.recycle();
 
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //displayView(0);
        }
	}
 
	/**
	 * click listener for the sliding menu
	 */
	/**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
 
     /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        switch (position) {
        case 0:
        	
        	Intent intent = new Intent(this, FindRoadActivity.class);
        	startActivity(intent);
        	
        	break;
        /*case 1:
            fragment = new FindPeopleFragment();
            break;
        case 2:
            fragment = new PhotosFragment();
            break;
        case 3:
            fragment = new CommunityFragment();
            break;
        case 4:
            fragment = new PagesFragment();
            break;*/
 
        default:
            break;
        }
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	/**
     * Function to load map. If map is not created it will create it for you
     * */
    private void initializeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            
            /*map type*/
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            
            /*a button to get you back home*/
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            
            /*one can see the current location*/
            //googleMap.setMyLocationEnabled(true);
            
            /*check if map is created successfully or not*/
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_LONG).show();
            }
        }
    }
	
    public void setMarkerAtCurrentLocation() {
    	Location mCurrentLocation = mLocationClient.getLastLocation();
    	
    	double latitude = mCurrentLocation.getLatitude();
    	double longitude = mCurrentLocation.getLongitude();
    	
    	/*move camera to current position*/
    	CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(15).build();
 
    	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    	
    	/*create marker*/
    	MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").visible(true);
    	
    	/*change to violet colour*/
    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    	
    	/*add marker to map*/
    	googleMap.addMarker(marker);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        //Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		Log.d("Succes", "connected");

    }
	
	@Override
    public void onDisconnected() {
        // Display the connection status
        Toast.makeText(this, "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
    }
	
	@Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
  
	}
	
	@Override
    protected void onStart() {
        super.onStart();
        /*connect the client*/
        mLocationClient.connect();
    }
	
	@Override
    protected void onResume() {
        super.onResume();
        //initializeMap();
    }
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		/*load map*/
		try {
            initializeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		/*show current location*/
		try {
            setMarkerAtCurrentLocation();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
    protected void onStop() {
        /*disconnecting the client invalidates it*/
        mLocationClient.disconnect();
        super.onStop();
    }
}

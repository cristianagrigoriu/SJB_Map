package com.cg.sjb_map;

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

import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
													  GooglePlayServicesClient.OnConnectionFailedListener{

	// Google Map
    private GoogleMap googleMap;
    LocationClient mLocationClient;
	
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
	}
	
	/**
     * function to load map. If map is not created it will create it for you
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
                new LatLng(latitude, longitude)).zoom(13).build();
 
    	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    	
    	/*create marker*/
    	MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").visible(true);
    	
    	/*change to violet colour*/
    	marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
    	
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

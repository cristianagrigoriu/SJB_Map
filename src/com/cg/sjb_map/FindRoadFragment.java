package com.cg.sjb_map;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
	 
public class FindRoadFragment extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,
	  GooglePlayServicesClient.OnConnectionFailedListener {
	     
		// Google Map
	    private GoogleMap googleMap;
	    LocationClient mLocationClient;
		
	    public FindRoadFragment(){}
	    	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.fragment_find_road);
	        
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
			
	        
	        Location mCurrentLocation = mLocationClient.getLastLocation();
        	
        	double latitude = mCurrentLocation.getLatitude();
        	double longitude = mCurrentLocation.getLongitude();
        	double latitude2 = 47.175012;
        	double longitude2 = 27.5716;
        	
        	Polyline line = googleMap.addPolyline(new PolylineOptions()
            .add(new LatLng(latitude, longitude), new LatLng(latitude2, longitude2))
            .width(5)
            .color(Color.BLACK));
        	
        	line.setGeodesic(false);
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
	}
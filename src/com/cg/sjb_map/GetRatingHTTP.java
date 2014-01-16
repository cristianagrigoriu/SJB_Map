package com.cg.sjb_map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
public class GetRatingHTTP extends AsyncTask<Void, Void, Void> {

	static String needed = "10";
	
	@Override
    protected Void doInBackground(Void... arg) {
    	
        HttpResponse response = null;

        //Log.d("Am intrat in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
        //create client
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://students.info.uaic.ro/~cristiana.grigoriu/myWay/points/GetRatings.php");
        
        //Log.d("Am facut clientul in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
        //Log.d("Am setat pairs in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
		try {
			response = client.execute(post);
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
		if(response == null){
		    Log.d("Server respnse", "No response");
		}

		HttpEntity responseEntity = response.getEntity();

		try {
		    String jsonString = EntityUtils.toString(responseEntity);
		    Log.d("Server respnse", jsonString);
		    
		    needed = jsonString.substring(jsonString.indexOf("#") + 1, jsonString.lastIndexOf("#") - 1);
		    int firstSpace = needed.indexOf(" ");
		    needed = needed.substring(firstSpace + 1);
		    needed = needed.substring(0, needed.indexOf(" "));
		    
		    Log.d("I NEED THIS", "#" + needed + "#");
		    
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
        return null;
    }

	public String getRating() {
		return needed;
	}
	
    protected void onPostExecute(String result) {
        
    }
}



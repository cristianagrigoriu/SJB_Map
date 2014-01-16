package com.cg.sjb_map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendRatingHTTP extends AsyncTask<String, Void, String> {
	
    @Override
    protected String doInBackground(String... arg) {
    	
        String rating = arg[0];
        String imei = arg[1];
        HttpResponse response = null;

        Log.d("Am intrat in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
        //create client
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://students.info.uaic.ro/~cristiana.grigoriu/myWay/points/Ratings.php");
        
        Log.d("Am facut clientul in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
        try {
	  		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	  		pairs.add(new BasicNameValuePair("rating", rating.toString()));
	  		pairs.add(new BasicNameValuePair("imei", imei.toString()));
	  		post.setEntity(new UrlEncodedFormEntity(pairs));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
        
        Log.d("Am setat pairs in HTTPR", "Aici rating: " + rating + " imei: " + imei);
        
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

		Log.d("Am trimis in HTTPR", "Aici rating: " + rating + " imei: " + imei);
		
		
		if(response == null){
		    Log.d("Server respnse", "No response");
		}
		
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        
    }
}


package com.cg.sjb_map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
	 
	public class FindRoadFragment extends Fragment {
	     
	    public FindRoadFragment(){}
	     
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	  
	        View rootView = inflater.inflate(R.layout.fragment_find_road, container, false);
	          
	        return rootView;
	    }
	}
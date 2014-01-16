package com.cg.sjb_map_sliding_menu_adapter;

import com.cg.sjb_map.R;
import com.cg.sjb_map.RateRoadActivity;
import com.cg.sjb_map.sliding_menu_model.NavDrawerItem;
import com.cg.sjb_map.sliding_menu_model.RateRoadItem;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class RateRoadListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<RateRoadItem> RateRoadItems;
    
    public RateRoadListAdapter(Context context, ArrayList<RateRoadItem> RateRoadItems){
        this.context = context;
        this.RateRoadItems = RateRoadItems;
    }
 
    @Override
    public int getCount() {
        return RateRoadItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return RateRoadItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.rate_road_list_item, null);
        }
        
        final CheckBox isChecked = (CheckBox) convertView.findViewById(R.id.checkBox1);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.rateOption);
        final EditText txtCount = (EditText) convertView.findViewById(R.id.rating);
        final int myPos = position;  
        
        //isChecked.setOnCheckedChangeListener(null);
        int[] ratings = {0, 0, 0, 0};
        
        isChecked.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                Log.d("Succes", "DA");
                if (isChecked.isChecked() == true) {
                	RateRoadItems.get(myPos).setisChecked(true);
                	txtCount.setEnabled(true);
                }
                else {
                	RateRoadItems.get(myPos).setisChecked(false);
                	RateRoadItems.get(myPos).setRating(0);
                	txtCount.setText(0+"");
                	txtCount.setEnabled(false);
                }
            }
             
        });
        
        txtCount.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
                Log.d("Succes", "DA EDIT");
                int n = Integer.parseInt(txtCount.getText().toString());
                RateRoadItems.get(myPos).setRating(n);   
            }
             
        });
        
        isChecked.setChecked(RateRoadItems.get(position).getIsChecked());        
        txtTitle.setText(String.valueOf(RateRoadItems.get(position).getRateOption()));
        txtCount.setText(String.valueOf(RateRoadItems.get(position).getRating()), TextView.BufferType.EDITABLE);
         
        return convertView;
    }
 
}

package com.cg.sjb_map_sliding_menu_adapter;

import com.cg.sjb_map.R;
import com.cg.sjb_map.sliding_menu_model.NavDrawerItem;
import com.cg.sjb_map.sliding_menu_model.RateRoadItem;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
 
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
          
        CheckBox isChecked = (CheckBox) convertView.findViewById(R.id.checkBox1);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.rateOption);
        EditText txtCount = (EditText) convertView.findViewById(R.id.rating);
          
        isChecked.setChecked(RateRoadItems.get(position).getIsChecked());        
        txtTitle.setText(RateRoadItems.get(position).getRateOption());
        txtCount.setText(RateRoadItems.get(position).getrating());
         
        return convertView;
    }
 
}

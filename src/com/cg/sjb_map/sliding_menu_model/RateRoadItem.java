package com.cg.sjb_map.sliding_menu_model;

public class RateRoadItem{

    private boolean isChecked = false;
    private String rateOption;
    private int rating = 0;
    // boolean to set visiblity of the counter
    private boolean isCounterVisible = false;
     
    public RateRoadItem(boolean isChecked, String rateOption, int rating){
        this.isChecked = isChecked;
        this.rateOption = rateOption;
        this.rating = rating;
    }
     
    public RateRoadItem(){}
    
    public boolean getIsChecked(){
        return this.isChecked;
    }
     
    public String getRateOption(){
        return this.rateOption;
    }
     
    public int getrating(){
        return this.rating;
    }
     
    public boolean getCounterVisibility(){
        return this.isCounterVisible;
    }
     
    public void setisChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
     
    public void setRateOption(String rateOption){
        this.rateOption = rateOption;
    }
     
    public void setCount(int rating){
        this.rating = rating;
    }
     
    public void setCounterVisibility(boolean isCounterVisible){
        this.isCounterVisible = isCounterVisible;
    }
}


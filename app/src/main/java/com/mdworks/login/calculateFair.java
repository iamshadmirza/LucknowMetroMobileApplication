package com.mdworks.login;



import java.util.List;

public class calculateFair {

    public String getFair(String source, String destination){
        float s = Float.parseFloat(source);
        float d = Float.parseFloat(destination);
        float ans= 3*(Math.abs(s-d));
        return Float.toString(ans);
    }

    public int getStationCount(List list){
        return list.size();
    }











}

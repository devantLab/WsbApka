package com.example.peethr.wsbtest.models.weather;

public class Globals{
    private static Globals instance;

    // Global variable
    private int temperature;
    private String summary;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }
    public int getTemperature(){
        return this.temperature;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }

    public static synchronized Globals getInstance(){
        if(instance == null){
            instance = new Globals();
        }
        return instance;
    }
}